package fr.eni.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import fr.eni.dal.CategorieDAO;
import fr.eni.dal.UtilisateurDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Categorie;
import fr.eni.model.Retrait;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;
import javafx.scene.shape.Arc;

@WebServlet("/vendreUnArticle")
@MultipartConfig(location="imgUpload", maxFileSize=1048576L) // 10Mo.
public class VendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VendreUnArticle() {
    }

    // parametres de l’upload
 	private static final int TAILLE_BUFFER=10240;
 	private static final String TYPE_CONTENU="content-disposition";
 	private static final String NOM_TYPE_CONTENU="filename";
 	private static final boolean MODE_MULTIPART=true;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("connexion");
	    	return;
	    } else {
	    	request.getSession().removeAttribute( "recordInsertedSuccessfully" );
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.set(Calendar.HOUR_OF_DAY, 0);
	    	calendar.add(Calendar.DATE, 1);
	    	Date tommorrow = calendar.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String tommorowFormated = sdf.format(tommorrow);
	    	List<Categorie> categories = new ArrayList<>();
	    	try {
				categories = CategorieDAO.lister();
				request.setAttribute("categories", categories);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	@SuppressWarnings("unchecked")
			Map<String, String> userInfos= (HashMap<String, String>)request.getSession().getAttribute("utilisateur");
	    	try {
				Utilisateur user = UtilisateurDAO.getUserByLogin(userInfos.get("pseudo"));
				request.setAttribute("utilisateur", user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    	request.setAttribute("dateJour", tommorowFormated);
	    	this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/vendreUnArticle.jsp" ).forward( request, response );
		}
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des éléments du formulaire
		if (request.getSession().getAttribute("recordInsertedSuccessfully") == null ){
			
			String nomArticle = request.getParameter("nomArticle");
	        String description = request.getParameter("desc");
	        String prix = request.getParameter("price");
	        String dateFinEnchere = request.getParameter("finEnchere");
	        
	        String boxRetrait = request.getParameter("boxRetrait");
	        Retrait retrait = new Retrait();
	        if(boxRetrait !=null) {
	        	@SuppressWarnings("unchecked")
				Map<String, String> userInfos= (HashMap<String, String>)request.getSession().getAttribute("utilisateur");
	        	try {
					Utilisateur user = UtilisateurDAO.getUserByLogin(userInfos.get("pseudo"));
					String rue = user.getRue();
		            String codePostal = user.getCodePostal();
		            String ville = user.getVille();
		            retrait.setRue(rue);
		            retrait.setCodePostal(codePostal);
		            retrait.setVille(ville);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }else {
	        	String rue = request.getParameter("rue");
	            String codePostal = request.getParameter("codePostal");
	            String ville = request.getParameter("ville");
	            retrait.setRue(rue);
	            retrait.setCodePostal(codePostal);
	            retrait.setVille(ville);
	        }
	        
	        Vente vente = new Vente();
	        vente.setNomArticle(nomArticle);
	        vente.setDescription(description);
	        vente.setMiseAPrix(Integer.parseInt(prix));
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try {
				vente.setDateFinEnchere(formatter.parse(dateFinEnchere));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        vente.setRetrait(retrait);
	        
	        @SuppressWarnings("unchecked")
			Map<String, String> userInfos= (HashMap<String, String>)request.getSession().getAttribute("utilisateur");
	        Utilisateur vendeur;
			try {
				vendeur = UtilisateurDAO.getUserByLogin(userInfos.get("pseudo"));
				vente.setVendeur(vendeur);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			Categorie categorie;
			try {
				categorie = CategorieDAO.recherche(Integer.parseInt(request.getParameter("categorie")));
				vente.setCategorie(categorie);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				long key = VenteDAO.ajouter(vente);
				// parcourir chaque paramètre reçu
				for (Part p: request.getParts())
				{
					String typeContenu=p.getContentType();				
					if(typeContenu!=null)
					{
					  // upload
						Part part=this.uploadFichier(p, key);
						request.setAttribute("part", part);
					}
				}
				request.setAttribute( "publication", "Votre annonce a bien été publiée" );
				request.getSession().setAttribute("recordInsertedSuccessfully","true");
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listeEncheres");
				dispatcher.forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		} else {
			request.setAttribute( "publication", "Cette annonce est déja publiée" );			
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listeEncheres");
			dispatcher.forward(request,response);
		}
	}	
	
	private Part uploadFichier(Part part, long key) throws IOException
	{
		// retrouver le nom du fichier uploadé
		String filename=this.getNomFichier(part);
		// mise en forme du nom
		String sKey = Long.toString(key);
		String prefix=sKey;
		String suffix="";
		if (filename.contains(".")) {
			suffix=filename.substring(filename.lastIndexOf('.'));
		}
		// écrire le fichier
		File file=File.createTempFile(prefix + "_", suffix, new File(this.getClass().getAnnotation(MultipartConfig.class).location()));
		
		// copie simple
		if (MODE_MULTIPART) {
			part.write(file.getName());
		}
		// copie streaming
		else
		{
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new BufferedInputStream(part.getInputStream(), 
					TAILLE_BUFFER);
				output = new BufferedOutputStream(new FileOutputStream(file), TAILLE_BUFFER);
				byte[] buffer = new byte[TAILLE_BUFFER];
				for (int length = 0; ((length = input.read(buffer)) > 0);) 
				{
					output.write(buffer, 0, length);
				}
			} finally {
				if (output != null) try { output.close(); } catch 
			(IOException e1) { /**/ }
			if (input != null) try { input.close(); } catch 
				(IOException e2) { /**/ }
			}
		}
		// détruire la copie de l’objet
		part.delete();
			// retourner l’objet
	return part;
	}
	
	// retourner le nom d’un fichier envoyé
	private String getNomFichier(Part part) {
		for (String cd : part.getHeader(TYPE_CONTENU).split(";")) {
			if (cd.trim().startsWith(NOM_TYPE_CONTENU)) {
				return cd.substring(cd.indexOf('=') + 
					1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
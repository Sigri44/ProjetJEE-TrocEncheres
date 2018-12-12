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
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
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
public class VendreUnArticle extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "C:\\repupload";
	private static final long serialVersionUID = 1L;

    
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
			Map<String, String> parametres = new HashMap<>();
			long key = 0;
			if(ServletFileUpload.isMultipartContent(request)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
					
					for(FileItem item : multiparts) {
						if (item.isFormField()){							
							parametres.put(item.getFieldName(), item.getString());							
						}
					}
					String nomArticle = parametres.get("nomArticle");
			        String description = parametres.get("description");
			        String prix = parametres.get("prix");					        
			        String dateFinEnchere = parametres.get("finEnchere");	        
			        String boxRetrait = parametres.get("boxRetrait");
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
			        	String rue = parametres.get("rue");
			            String codePostal = parametres.get("codePostal");
			            String ville = parametres.get("ville");
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
						categorie = CategorieDAO.recherche(Integer.parseInt(parametres.get("categorie")));
						vente.setCategorie(categorie);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					key = VenteDAO.ajouter(vente);
					// Récupération de l'image si uploadée
					for(FileItem item : multiparts) {
						if(!item.isFormField()) {
							String name = new File(item.getName()).getName();
							if(name != "") {
								name = key +"_"+ name;							}
							item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
							request.setAttribute("photoname", name);
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
			
			
			
			
			request.setAttribute( "publication", "Votre annonce a bien été publiée" );
			request.getSession().setAttribute("recordInsertedSuccessfully","true");
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listeEncheres");
			dispatcher.forward(request,response);
			
		} else {
			request.setAttribute( "publication", "Cette annonce est déja publiée" );			
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listeEncheres");
			dispatcher.forward(request,response);
		}
	}	
	
	
}
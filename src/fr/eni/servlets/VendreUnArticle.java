package fr.eni.servlets;

import java.io.IOException;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private static final long serialVersionUID = 1L;

    public VendreUnArticle() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("connexion");
	    	return;
	    } else {
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
        System.out.println("getRetrait dans la servlet : "+vente.getRetrait().toString());
        
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
			VenteDAO.ajouter(vente);
			request.setAttribute( "inscription", "Vous êtes inscrit" );
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
			dispatcher.forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        
        
	}
}
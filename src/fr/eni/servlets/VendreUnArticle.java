package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.dal.UtilisateurDAO;
import fr.eni.model.Retrait;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;

@WebServlet("/VendreUnArticle")
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
        if(boxRetrait.equals("on")) {
        	String rue = request.getParameter("rueH");
            String codePostal = request.getParameter("codePostalH");
            String ville = request.getParameter("villeH");
            retrait.setRue(rue);
            retrait.setCodePostal(codePostal);
            retrait.setCodePostal(codePostal);
        }else {
        	String rue = request.getParameter("rue");
            String codePostal = request.getParameter("codePostal");
            String ville = request.getParameter("ville");
            retrait.setRue(rue);
            retrait.setCodePostal(codePostal);
            retrait.setCodePostal(codePostal);
        }
        
        Vente vente = new Vente();
        vente.setNomArticle(nomArticle);
        vente.setDescription(description);
        vente.setMiseAPrix(Integer.parseInt(prix));
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
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
        
		//vente.setCategorie(categorie);
        
        
	}
}
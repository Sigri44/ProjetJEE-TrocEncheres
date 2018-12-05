package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
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
import fr.eni.model.Utilisateur;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Connexion() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connexion.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des éléments du formulaire
		String identifiant = request.getParameter("identifiant");
        String mdp = request.getParameter("password");
        
        //Map des données saisies
        Map<String, String> saisie = new HashMap<>();
        saisie.put("identifiant", identifiant);
        saisie.put("mdp", mdp);
        
        //Map d'erreurs
        Map<String, String> erreurs = new HashMap<>();
        
        //Vérification de l'existence de l'identifiant (mail ou pseudo)
        try {
			boolean existPseudo = UtilisateurDAO.existByPseudo(identifiant);
			boolean existMail = UtilisateurDAO.existByMail(identifiant);
			if(!existPseudo && !existMail) {				
				erreurs = setErreur(erreurs, "notExistIdentifiant", "L'identifiant saisi n'existe pas");											
			}
			else {
				boolean checkLogin = UtilisateurDAO.passMatchId(identifiant,mdp);
				if(!checkLogin) {
					erreurs = setErreur(erreurs, "wrongPass", "Mauvais mot de passe");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}      
        
        if(!erreurs.isEmpty()) {
        	request.setAttribute( "erreurs", erreurs );  
        	request.setAttribute("saisie", saisie);
        	request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);		
        }else {
        	try {
    			Utilisateur user = UtilisateurDAO.getUserByLogin(identifiant);
    			HttpSession session = request.getSession();
    			Map<String, Object> userInfos = new HashMap<>();
    			userInfos.put("pseudo", user.getPseudo());
    			userInfos.put("isAdmin", user.isAdmin());
    			session.setAttribute("utilisateur", userInfos);
            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listeEncheres");
    			dispatcher.forward(request,response);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
	
	
	
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private Map<String, String> setErreur(Map<String, String> erreurs, String champ, String message ) {
	    erreurs.put( champ, message );
		return erreurs;	    
	}
}
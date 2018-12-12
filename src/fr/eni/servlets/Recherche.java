package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dal.UtilisateurDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recherche() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des éléments du formulaire
		Map<String, String> parametres = new HashMap<>();
		String nomArticle = parametres.get("nomArticle");
		// Récupération des checkbox
		String boxMesVentes = parametres.get("mySales");
		String boxMesEncheres = parametres.get("ongoingAuctions");
		String boxMesAcquisitions = parametres.get("myPurchases");
		String boxAutresEncheres = parametres.get("otherAuctions");
		//Vente vente = new Vente();
		
		// Récupération de l'id user
		@SuppressWarnings("unchecked")
		Map<String, String> userInfos = (HashMap<String, String>)request.getSession().getAttribute("utilisateur");
		
		// Initialisation des Ventes
		Map<String, List<Vente>> resultVentes = new HashMap<>();
		ArrayList<Vente>ventesByNom = null;
		ArrayList<Vente>mesVentes = null;
		ArrayList<Vente>mesEncheres = null;
		ArrayList<Vente>mesAcquisitions = null;
		// Vérification de l'existence des critères définis
		try {
			Utilisateur user = UtilisateurDAO.getUserByLogin(userInfos.get("no_utilisateur"));
			// Recherche par nom d'article
			if (nomArticle != null) {
        		ventesByNom = VenteDAO.listVenteByName(nomArticle);
        		resultVentes.put("ventesByNom", ventesByNom);
        	} else {
        		System.out.println("Aucun nom d'article n'as été écrit !");
        	}
			// Lister mes ventes
			if(boxMesVentes != null) {
				mesVentes = VenteDAO.getVenteByIdUser(user.getNoUtilisateur());
				resultVentes.put("mesVentes", mesVentes);
        	} else {
        		System.out.println("Le choix 'Mes Ventes' n'as pas été choisi !");
        	}
			// Lister mes enchères
			if(boxMesEncheres != null) {
				mesEncheres = VenteDAO.getEnchereByIdUser(user.getNoUtilisateur());
				resultVentes.put("mesEncheres", mesEncheres);
			} else {
        		System.out.println("Le choix 'Mes Enchères' n'as pas été choisi !");
        	}
			// Lister mes acquisitions
			if(boxMesAcquisitions != null) {
				mesAcquisitions = VenteDAO.getAcquisitionByIdUser(user.getNoUtilisateur());
				resultVentes.put("mesAcquisitions", mesAcquisitions);
			} else {
        		System.out.println("Le choix 'Mes Acquisitions' n'as pas été choisi !");
        		
        	}
			// Lister les autres enchères 
			if(boxAutresEncheres != null) {
				System.out.println("Coucou tu as selectionné les autres enchères !");
			} else {
        		System.out.println("Le choix 'Autres Enchères' n'as pas été choisi !");
        	}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String filtre = "oui";
		request.setAttribute("filtres", filtre);
		request.setAttribute("ventesMap", resultVentes);
	}
}
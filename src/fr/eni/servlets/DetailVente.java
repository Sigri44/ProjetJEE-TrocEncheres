package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dal.EnchereDAO;
import fr.eni.dal.UtilisateurDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Enchere;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;

/**
 * Servlet implementation class DetailVendeur
 */
@WebServlet("/detailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("connexion");
	    	return;
	      } else {
	    	  System.out.println(request.getParameter("noVente"));
	    	  int id = Integer.parseInt(request.getParameter("noVente"));
	    	  try {
	    		Vente vente =  VenteDAO.getVenteById(id);			
				request.setAttribute("vente", vente);
				request.setAttribute("minOffre", vente.getPrixVente()+1);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/detailVente.jsp" ).forward( request, response );
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		int idVente = Integer.parseInt(request.getParameter("hiddenId"));
		try {
			Vente vente = VenteDAO.getVenteById(idVente);
			if(proposition > vente.getPrixVente()) {
				VenteDAO.modifierPrixVente(vente, proposition);
				
				//Si l'utilisateur a déja enchéri 
				@SuppressWarnings("unchecked")
				Map<String, String> userInfos= (HashMap<String, String>)request.getSession().getAttribute("utilisateur");
				Utilisateur user = UtilisateurDAO.getUserByLogin(userInfos.get("pseudo"));
				int aEncheri = EnchereDAO.checkExist(idVente, user.getNoUtilisateur());
				System.out.println(aEncheri);
				Enchere enchere = new Enchere();
				if(aEncheri == 0 ) {
					enchere.setDateEnchere(new Date());	
					System.out.println(enchere.getDateEnchere());
					enchere.setEncherisseur(user);
					enchere.setVente(vente);
					EnchereDAO.ajouter(enchere);
				}
				else {
					enchere.setDateEnchere(new Date());	
					System.out.println(enchere.getDateEnchere());
					enchere.setEncherisseur(user);
					enchere.setVente(vente);
					EnchereDAO.modifier(enchere);
				}
				
				request.setAttribute("successEnchere", "Enchère réussie");
				request.setAttribute("noVente", idVente);
				vente = VenteDAO.getVenteById(idVente);
				request.setAttribute("vente", vente);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
				dispatcher.forward(request,response);
			}
			else{
				request.setAttribute("failEnchere", "l'enchère n'a pas pu être enregistrée (proposition trop basse)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

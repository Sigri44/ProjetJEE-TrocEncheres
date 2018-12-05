package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.dal.UtilisateurDAO;
import fr.eni.model.Utilisateur;

/**
 * Servlet implementation class SupprProfil
 */
@WebServlet("/SupprProfil")
public class SupprProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null){
	    	this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connexion.jsp" ).forward( request, response );
	    	return;
	      } else {
	    	  Map<String, Object> userInfos = new HashMap<String, Object>();
	    	  userInfos = (Map<String, Object>) request.getSession().getAttribute("utilisateur");
	    	  try {
				Utilisateur user = UtilisateurDAO.getUserByLogin((String) userInfos.get("pseudo"));
				UtilisateurDAO.supprimer(user);
				request.setAttribute("suppressionCompte", "Votre compte a bien été supprimé");
				HttpSession session = request.getSession();
				session.removeAttribute("utilisateur");
				session.invalidate();
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connexion.jsp" ).forward( request, response );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

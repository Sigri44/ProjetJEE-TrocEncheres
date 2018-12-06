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

import fr.eni.dal.UtilisateurDAO;
import fr.eni.model.Utilisateur;

@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Profil() {
    	
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("connexion");
	    	return;
	      } else {
	    	  Map<String, Object> userInfos = new HashMap<String, Object>();
	    	  userInfos = (HashMap<String, Object>)request.getSession().getAttribute("utilisateur");
	    	  try {
				Utilisateur user = UtilisateurDAO.getUserByLogin((String) userInfos.get("pseudo"));
				request.setAttribute("user", user);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/profil.jsp" ).forward( request, response );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
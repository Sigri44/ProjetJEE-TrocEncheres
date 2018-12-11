package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.dal.CategorieDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Categorie;
import fr.eni.model.Vente;

import java.sql.SQLException;

@WebServlet("/listeEncheres")
public class ListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListeEncheres() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    if (request.getSession().getAttribute("utilisateur") == null){
	    	response.sendRedirect("connexion");
	    	return;
	      } else {
	    	  try {
	    		  ArrayList<Categorie>categorie = CategorieDAO.lister();
					request.setAttribute("toutesCategorie", categorie);
	    		  ArrayList<Vente>ventes = VenteDAO.lister();
	    		  request.setAttribute("toutesVentes", ventes);
	    		  this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listeEncheres.jsp" ).forward( request, response );
		    	  
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
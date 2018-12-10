package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dal.CategorieDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Categorie;
import fr.eni.model.Vente;

/**
 * Servlet implementation class CategirieListe
 */
@WebServlet("/CategirieListe")
public class CategirieListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategirieListe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if (request.getSession().getAttribute("utilisateur") == null){
		    	response.sendRedirect("connexion");
		    	return;
		      } else {
		    	  try {
		    		  ArrayList<Categorie>categorie = CategorieDAO.lister();
		    		  request.setAttribute("toutesCategorie", categorie);
		    		  System.out.println(categorie.get(0).toString());
		    		  this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listeEncheres.jsp" ).forward( request, response );
			    	  
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

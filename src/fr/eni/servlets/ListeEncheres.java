package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListeEncheres")
public class ListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListeEncheres() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    if (request.getSession().getAttribute("utilisateur") == null){
	    	this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connexion.jsp" ).forward( request, response );
	    	return;
	      } else {
	    	  this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listeEncheres.jsp" ).forward( request, response );
	    }		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
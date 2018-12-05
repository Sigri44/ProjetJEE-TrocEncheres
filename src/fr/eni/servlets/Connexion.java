package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Connexion() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connexion.jsp" ).forward( request, response );
		
		/* Cr�ation ou r�cup�ration de la session */
		HttpSession session = request.getSession();

		/* Mise en session d'une cha�ne de caract�res */
		String exemple = "abc";
		session.setAttribute( "chaine", exemple );

		/* R�cup�ration de l'objet depuis la session */
		String chaine = (String) session.getAttribute( "chaine" );
		
		System.out.println(chaine);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
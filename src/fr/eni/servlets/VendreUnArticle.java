package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
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
import fr.eni.model.Utilisateur;

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
	    	request.setAttribute("dateJour", tommorowFormated);
	    	this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/vendreUnArticle.jsp" ).forward( request, response );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
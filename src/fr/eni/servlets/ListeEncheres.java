package fr.eni.servlets;

import java.io.File;
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

import fr.eni.dal.CategorieDAO;
import fr.eni.dal.UtilisateurDAO;
import fr.eni.dal.VenteDAO;
import fr.eni.model.Categorie;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;

@WebServlet("/listeEncheres")
public class ListeEncheres extends HttpServlet {
	private final String UPLOAD_DIRECTORY = System.getProperty("user.home") + File.separator + "git" + File.separator + "ProjetJEE-TrocEncheres" + File.separator + "WebContent" + File.separator + "WEB-INF" + File.separator + "upload";
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
				
				/*if (request.getAttribute("filtres").equals("oui")) {
					@SuppressWarnings("unchecked")
					Map<String, List<Vente>> ventesMap = (Map<String, List<Vente>>) request.getAttribute("ventesMap");
					
				} else {*/
					ArrayList<Vente>ventes = VenteDAO.lister();
					request.setAttribute("toutesVentes", ventes);
					
					/*
					// Lister toutes les images dans le folder
					Map<String, String> fichiers = new HashMap<>();
					File folder = new File(UPLOAD_DIRECTORY);
					File[] listOfFiles = folder.listFiles();
					for (int i = 0; i < listOfFiles.length; i++) {
					    if (listOfFiles[i].isFile()) {					    	
						    fichiers.add(listOfFiles[i].getName());
					    }
					}
					
					for(Vente v : ventes) {
						int id = v.getNoVente();						
					}
					*/
					
				/*}*/
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
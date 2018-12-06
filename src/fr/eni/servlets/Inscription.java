package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dal.UtilisateurDAO;
import fr.eni.model.Utilisateur;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/inscription.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//R�cup�ration des �l�ments du formulaire
		String email = request.getParameter("email");
        String motDePasse = request.getParameter("mdp");
        String confirmation = request.getParameter("mdpConf");
        String pseudo = request.getParameter("pseudo");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");
        
        //Map des donn�es saisies
        Map<String, String> saisie = new HashMap<>();
        saisie.put("email", email);
        saisie.put("pseudo", pseudo);
        saisie.put("prenom", prenom);
        saisie.put("nom", nom);
        saisie.put("telephone", telephone);
        saisie.put("rue", rue);
        saisie.put("codePostal", codePostal);
        saisie.put("ville", ville);
        
        //Map d'erreurs
        Map<String, String> erreurs = new HashMap<>();
        
        //V�rification de l'unicit� du pseudo, du mail et de num�ro de t�l�phone
        try {
			boolean exist = UtilisateurDAO.existByPseudo(pseudo);
			if(exist) {
				erreurs = setErreur(erreurs, "existPseudo", "Ce pseudo est d�ja pris");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try {
			boolean exist = UtilisateurDAO.existByMail(email);
			if(exist) {
				erreurs = setErreur(erreurs, "existMail", "Cet email est d�ja utilis�");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try {
			boolean exist = UtilisateurDAO.existByTel(telephone);
			if(exist) {
				erreurs = setErreur(erreurs, "existTel", "Cet num�ro de t�l�phone est d�ja utilis�");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        //Cr�ation d'un utilisateur
        Utilisateur utilisateur = new Utilisateur();
        
        //V�rification de la validit� du mail
        try {
            validationEmail( email );
            utilisateur.setMail( email );
        } catch ( Exception e ) {
            erreurs  = setErreur(erreurs, "email", e.getMessage() );
        }
        
        //V�rification du mot de passe
        try {
            validationMotsDePasse( motDePasse, confirmation );
            utilisateur.setMotDePasse( motDePasse );
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "mdp", e.getMessage() );
            erreurs = setErreur(erreurs, "mdpConf", null );
        }
        
        //V�rification du pseudo
        try {
            validationPseudo( pseudo );
            utilisateur.setPseudo( pseudo );
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "pseudo", e.getMessage() );
        }
        
        //V�rification du nom
        try {
            validationString( nom );
            utilisateur.setNom(nom);
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "nom", e.getMessage() );
        }
        
        //V�rification du prenom
        try {
            validationString( prenom );
            utilisateur.setPrenom( prenom );
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "prenom", e.getMessage() );
        }
        
        //V�rification de la ville
        try {
            validationString( ville );
            utilisateur.setVille(ville);
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "ville", e.getMessage() );
        }
        
        //V�rification de la rue
        try {
            validationString( rue );
            utilisateur.setRue(rue);
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "rue", e.getMessage() );
        }
        
        //V�rification du telephone
        try {
            validationTelephone(telephone);
            utilisateur.setTelephone(telephone);
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "telephone", e.getMessage() );
        }
        
        //V�rification du code postal
        try {
            validationCodePostal(codePostal);
            utilisateur.setCodePostal(codePostal);
        } catch ( Exception e ) {
            erreurs = setErreur(erreurs, "codePostal", e.getMessage() );
        }
        
        if(!erreurs.isEmpty()) {
        	request.setAttribute( "erreurs", erreurs );  
        	request.setAttribute("saisie", saisie);
        	request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);		
        } else {
        	try {
    			UtilisateurDAO.ajouter(utilisateur);
    			request.setAttribute( "inscription", "Vous �tes inscrit" );
            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
    			dispatcher.forward(request,response);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
	
	private void validationEmail( String email ) throws Exception {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	
	private void validationMotsDePasse( String mdp, String mdpConf ) throws Exception {
	    if ( mdp != null && mdpConf != null ) {
	        if ( !mdp.equals( mdpConf ) ) {
	            throw new Exception( "Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau." );
	        } else if ( mdp.length() < 3 ) {
	            throw new Exception( "Le mot de passe doit contenir au moins 3 caract�res." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }
	}
	
	private void validationPseudo( String pseudo ) throws Exception {
	    if ( pseudo != null && pseudo.trim().length() < 3 ) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caract�res." );
	    }
	}
	
	private void validationString( String string ) throws Exception {
	    if ( string.trim().length() < 1 ) {
	        throw new Exception( "Champ non valide" );
	    }
	}
	
	public void validationTelephone(String telephone) throws Exception{		 
		if(!telephone.matches("[0-9]{10}")) {
			throw new Exception( "Telephone non valide" );
		}
	}
	
	public void validationCodePostal(String codePostal) throws Exception{	
		if(!codePostal.matches("[0-9]{5}")) {
			throw new Exception("Code postal non valide");
		}	 
	}
	
	/*
	 * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
	 */
	private Map<String, String> setErreur(Map<String, String> erreurs, String champ, String message ) {
	    erreurs.put( champ, message );
		return erreurs;	    
	}
}
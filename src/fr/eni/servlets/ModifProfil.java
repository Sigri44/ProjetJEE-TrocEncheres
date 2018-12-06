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
import sun.security.util.Length;

/**
 * Servlet implementation class ModifProfil
 */
@WebServlet("/ModifProfil")
public class ModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/profil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des éléments du formulaire
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
        
      //Map des données saisies
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
        
        //Récupération de l'utilisateur connecté
        Map<String, Object> userInfos = new HashMap<String, Object>();
  	  	userInfos = (HashMap<String, Object>)request.getSession().getAttribute("utilisateur");
  	  	try {
			Utilisateur connectedUser = UtilisateurDAO.getUserByLogin((String) userInfos.get("pseudo"));
			//Vérification de l'unicité du pseudo, du mail et de numéro de téléphone, s'ils ont été modifiés
			boolean exist;
	  	  	if(!connectedUser.getPseudo().equals(pseudo)) {		  	  	
				exist = UtilisateurDAO.existByPseudo(pseudo);
				if(exist) {
					erreurs = setErreur(erreurs, "existPseudo", "Ce pseudo est déja pris");	
				}else {
					//Vérification de la conformité du pseudo
			        try {
			            validationPseudo( pseudo );
			            connectedUser.setPseudo( pseudo );
			        } catch ( Exception e ) {
			            erreurs = setErreur(erreurs, "pseudo", e.getMessage() );
			        }
				}
	  	  	}
	  	    if(!connectedUser.getMail().equals(email)) {	  	    	
				exist = UtilisateurDAO.existByMail(email);
				if(exist) {
					erreurs = setErreur(erreurs, "existMail", "Cet email est déja utilisé");
				}else {
					//Vérification de la conformité du mail
			        try {
			            validationEmail( email );
			            connectedUser.setMail( email );
			        } catch ( Exception e ) {
			            erreurs  = setErreur(erreurs, "email", e.getMessage() );
			        }
				}
	  	    }
	  	    if(!connectedUser.getTelephone().equals(telephone)) {	  	    	
				exist = UtilisateurDAO.existByTel(telephone);
				if(exist) {
					erreurs = setErreur(erreurs, "existTel", "Cet numéro de téléphone est déja utilisé");
				}
				else {
					//Vérification du telephone
			        try {
			            validationTelephone(telephone);
			            connectedUser.setTelephone(telephone);
			        } catch ( Exception e ) {
			            erreurs = setErreur(erreurs, "telephone", e.getMessage() );
			        }
				}
	  	    }
	  	    
	  	    //Vérification du nom
	        try {
	            validationString( nom );
	            connectedUser.setNom(nom);
	        } catch ( Exception e ) {
	            erreurs = setErreur(erreurs, "nom", e.getMessage() );
	        }
	        
	        //Vérification du prenom
	        try {
	            validationString( prenom );
	            connectedUser.setPrenom( prenom );
	        } catch ( Exception e ) {
	            erreurs = setErreur(erreurs, "prenom", e.getMessage() );
	        }
	        
	        //Vérification de la ville
	        try {
	            validationString( ville );
	            connectedUser.setVille(ville);
	        } catch ( Exception e ) {
	            erreurs = setErreur(erreurs, "ville", e.getMessage() );
	        }
	        
	        //Vérification de la rue
	        try {
	            validationString( rue );
	            connectedUser.setRue(rue);
	        } catch ( Exception e ) {
	            erreurs = setErreur(erreurs, "rue", e.getMessage() );
	        }
	        
	      //Vérification du code postal
	        try {
	            validationCodePostal(codePostal);
	            connectedUser.setCodePostal(codePostal);
	        } catch ( Exception e ) {
	            erreurs = setErreur(erreurs, "codePostal", e.getMessage() );
	        }
	        
	        //Vérification du mot de passe
	        if(!(motDePasse.trim().length()<1)) {
	        	try {
		            validationMotsDePasse( motDePasse, confirmation );
		            if(!connectedUser.getMotDePasse().equals(motDePasse)) {	
		            	connectedUser.setMotDePasse( motDePasse );
		            }else {
		            	erreurs = setErreur(erreurs, "mdp", "Vous avez saisi votre mot de passe actuel");
		            }		            
		        } catch ( Exception e ) {
		            erreurs = setErreur(erreurs, "mdp", e.getMessage() );
		            erreurs = setErreur(erreurs, "mdpConf", null );
		        }
	        }
	        
	        if(!erreurs.isEmpty()) {
	        	request.setAttribute( "erreurs", erreurs );  
	        	request.setAttribute("saisie", saisie);
	        	request.setAttribute("user", connectedUser);
	        	request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);		
	        }else {
	        	try {
	    			UtilisateurDAO.modifier(connectedUser);;
	    			request.setAttribute( "modification", "Vous avez modifié votre profil" );
	            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profil");
	    			dispatcher.forward(request,response);
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	        }
	        
	        
  	  	} catch (SQLException e) {
  	  		e.printStackTrace();
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
	            throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
	        } else if ( mdp.length() < 3 ) {
	            throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }
	}
	
	private void validationPseudo( String pseudo ) throws Exception {
	    if ( pseudo != null && pseudo.trim().length() < 3 ) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
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
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private Map<String, String> setErreur(Map<String, String> erreurs, String champ, String message ) {
	    erreurs.put( champ, message );
		return erreurs;	    
	}
}

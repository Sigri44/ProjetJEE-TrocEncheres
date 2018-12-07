package fr.eni.dal;

import fr.eni.model.Utilisateur;
import fr.eni.util.DbConnection;

import java.sql.*;
import java.util.*;

public class UtilisateurDAO {
	private static final String AJOUTER = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SUPPRIMER = "delete from UTILISATEURS where pseudo = ?";
	private static final String MODIFIER = "update UTILISATEURS set pseudo = ?, nom = ?, email = ?, telephone = ?, rue = ?, "
			+ "code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? , prenom = ? where no_utilisateur = ?";
	private static final String SEARCHBYID = "select * from UTILISATEURS where no_utilisateur = ?";
	private static final String SEARCHBYLOGIN = "select *  from UTILISATEURS where pseudo = ? or email = ?";
	private static final String SEARCHBYPSEUDO = "select pseudo  from UTILISATEURS where pseudo = ?";
	private static final String SEARCHBYMAIL = "select email from UTILISATEURS where email = ?";
	private static final String SEARCHBYTEL = "select telephone from UTILISATEURS where telephone = ?";
	private static final String LISTER 	= "select * from UTILISATEURS";
	private static final String LOGINMAIL = "select * from UTILISATEURS where email=? and mot_de_passe=?";
	private static final String LOGINPSEUDO = "select * from UTILISATEURS where pseudo=? and mot_de_passe=?";
	private static final String CHECKLOGIN = "select *  from UTILISATEURS where (pseudo = ? or email = ?) and mot_de_passe=?";
	
	public static void ajouter (Utilisateur utilisateur) throws SQLException {		
		Connection cnx = null;
		PreparedStatement rqt =  null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER);			
			rqt.setString(1, utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			rqt.setString(3, utilisateur.getPrenom());
			rqt.setString(4, utilisateur.getMail());
			rqt.setString(5, utilisateur.getTelephone());
			rqt.setString(6, utilisateur.getRue());
			rqt.setString(7, utilisateur.getCodePostal());
			rqt.setString(8, utilisateur.getVille());
			rqt.setString(9, utilisateur.getMotDePasse());
			rqt.setInt(10, 0);
			rqt.setBoolean(11, false);
			rqt.executeUpdate();
			rqt.close();
			cnx.close();
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		}
	}

	public static void modifier (Utilisateur utilisateur) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt =  null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(MODIFIER);
			rqt.setString(1, utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			rqt.setString(3, utilisateur.getMail());
			rqt.setString(4, utilisateur.getTelephone());
			rqt.setString(5, utilisateur.getRue());
			rqt.setString(6, utilisateur.getCodePostal());
			rqt.setString(7, utilisateur.getVille());
			rqt.setString(8, utilisateur.getMotDePasse());
			rqt.setInt(9, utilisateur.getCredit());
			rqt.setString(10, utilisateur.getPrenom());
			rqt.setInt(11, utilisateur.getNoUtilisateur());
			rqt.executeUpdate();
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static int supprimer (Utilisateur user) throws SQLException {
		Connection cnx=null;
		PreparedStatement rqt=null;
		int nbreEnrgt = 0;
		try{
			cnx=DbConnection.seConnecter();
			rqt=cnx.prepareStatement(SUPPRIMER);
			rqt.setString(1, user.getPseudo());
			nbreEnrgt= rqt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbreEnrgt;
	}

	public static ArrayList<Utilisateur> lister() throws SQLException{
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		try {
			cnx= DbConnection.seConnecter();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(LISTER);
			Utilisateur utilisateur;
			while (rs.next()) {
				utilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getString("prenom"), rs.getBoolean("administrateur"));
				listeUtilisateurs.add(utilisateur);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeUtilisateurs;
	}
	
	public static Utilisateur rechercherParId (int id) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYID);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				if (utilisateur == null) utilisateur = new Utilisateur(); 
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setMail(rs.getString("email"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setAdmin(rs.getBoolean("administrateur"));
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return utilisateur;
	}
	
	public static boolean existByPseudo (String pseudo) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYPSEUDO);
			rqt.setString(1, pseudo);
			rs= rqt.executeQuery();			
			while (rs.next()) {
				exist = true;
			}
		} finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return exist;
	}
	
	public static boolean existByMail (String email) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYMAIL);
			rqt.setString(1, email);
			rs= rqt.executeQuery();			
			while (rs.next()) {
				exist = true;
			}
		} finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return exist;
	}
	
	public static boolean existByTel (String telephone) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYTEL);
			rqt.setString(1, telephone);
			rs= rqt.executeQuery();			
			while (rs.next()) {
				exist = true;
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return exist;
	}

	public static Utilisateur getUserByLogin (String identifiant) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYLOGIN);
			rqt.setString(1, identifiant);
			rqt.setString(2, identifiant);
			rs= rqt.executeQuery();
			while (rs.next()) {
				if (utilisateur == null) utilisateur = new Utilisateur(); 
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setMail(rs.getString("email"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setAdmin(rs.getBoolean("administrateur"));
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return utilisateur;
	}

	public static boolean passMatchId(String identifiant, String mdp) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean loginOK = false;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(CHECKLOGIN);
			rqt.setString(1, identifiant);
			rqt.setString(2, identifiant);
			rqt.setString(3, mdp);
			rs= rqt.executeQuery();			
			while (rs.next()) {
				loginOK = true;
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return loginOK;
	}
}

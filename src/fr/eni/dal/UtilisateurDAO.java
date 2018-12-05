package fr.eni.dal;

import fr.eni.model.Utilisateur;
import fr.eni.util.DbConnection;


import java.sql.*;
import java.util.*;

public class UtilisateurDAO {
	private static final String AJOUTER = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SUPPRIMER 	= "delete from UTILISATEURS where id = ?";
	private static final String MODIFIER = "update UTILISATEURS set pseudo = ?, nom = ?, email = ?, telephone = ?, rue = ?, "
			+ "code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? , prenom = ? where id = ?";
	private static final String RECHERCHER ="select pseudo ,nom,email,telephone,rue ,code_postal,ville,mot_de_passe,credit,prenom from UTILISATEURS where id = ?";
	private static final String LISTER 	= "select pseudo ,nom,email,telephone,rue ,code_postal,ville,mot_de_passe,credit,prenom from UTILISATEURS";
	private static final String LOGIN ="select * from UTILISATEURS where pseudo=? and mot_de_passe=?";
	
	public static void ajouter (Utilisateur utilisateur) throws SQLException {
		
		try {
			Connection cnx = DbConnection.seConnecter();
			PreparedStatement rqt = cnx.prepareStatement(AJOUTER);
			rqt.setString(1, utilisateur.getPseudo());
			System.out.println(utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			System.out.println(utilisateur.getNom());
			rqt.setString(3, utilisateur.getPrenom());
			System.out.println(utilisateur.getPrenom());
			rqt.setString(4, utilisateur.getMail());
			System.out.println(utilisateur.getMail());
			rqt.setString(5, utilisateur.getTelephone());
			System.out.println(utilisateur.getTelephone());
			rqt.setString(6, utilisateur.getRue());
			System.out.println(utilisateur.getRue());
			rqt.setString(7, utilisateur.getCodePostal());
			System.out.println(utilisateur.getCodePostal());
			rqt.setString(8, utilisateur.getVille());
			System.out.println(utilisateur.getVille());
			rqt.setString(9, utilisateur.getMotDePasse());
			System.out.println(utilisateur.getMotDePasse());
			rqt.setInt(10, 0);
			rqt.setBoolean(11, false);
			System.out.println(rqt.toString());
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
			rqt.setInt(10, utilisateur.getNoUtilisateur());
			rqt.executeUpdate();
		} finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	public static int supprimer (int id) throws SQLException {
		Connection cnx=null;
		PreparedStatement rqt=null;
		int nbreEnrgt = 0;
		try{
			cnx=DbConnection.seConnecter();
			rqt=cnx.prepareStatement(SUPPRIMER);
			rqt.setInt(1, id);
			nbreEnrgt= rqt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}finally{
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
				utilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getString("prenom"));

				listeUtilisateurs.add(utilisateur);
			}
		} finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}


		return listeUtilisateurs;

	}
	public static Utilisateur rechercher (int id) throws SQLException {
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(RECHERCHER);
			rqt.setInt(1, id);
			rs= rqt.executeQuery();
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


			}
		} finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return utilisateur;


	}
}

package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.model.Enchere;
import fr.eni.util.DbConnection;

public class EnchereDAO {
	private static final String AJOUTER = "INSERT INTO ENCHERES (date_enchere, no_utilisateur, no_vente) VALUES (?,?,?)";
	private static final String MODIFIER = "UPDATE ENCHERES SET date_enchere = ? WHERE no_vente = ? AND no_utilisateur = ?";
	//private static final String SUPPRIMER = "DELETE FROM ENCHERES WHERE no_vente = ?";
	private static final String SEARCHBYVENTEIDANDUSERID = "SELECT * FROM ENCHERES WHERE no_vente = ? AND no_utilisateur = ?";
	//private static final String SEARCHBESTBIDDER = "SELECT UTILISATEURS.no_utilisateur FROM UTILISATEURS  join ENCHERES on UTILISATEURS.no_utilisateur=ENCHERES.no_utilisateur WHERE no_vente = 88 ORDER BY ENCHERES.date_enchere DESC";
	
	public static void ajouter (Enchere enchere) throws SQLException {		
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER);
			rqt.setDate(1, new java.sql.Date(enchere.getDateEnchere().getTime()));
			rqt.setInt(2, enchere.getEncherisseur().getNoUtilisateur());
			rqt.setInt(3, enchere.getVente().getNoVente());
			rqt.executeUpdate();
			rqt.close();
			cnx.close();
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		}
	}
	
	public static int checkExist (int idVente, int idUtilisateur) throws SQLException {		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int res = 0;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYVENTEIDANDUSERID);
			rqt.setInt(1, idVente);
			rqt.setInt(2, idUtilisateur);
			rs = rqt.executeQuery();
			if (rs.next()) {
				res = 1;
			}
			rqt.close();
			cnx.close();
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		}
		return res;
	}
	
	public static void modifier (Enchere enchere) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(MODIFIER);
			rqt.setTimestamp(1, new java.sql.Timestamp(enchere.getDateEnchere().getTime()));			
			rqt.setInt(2, enchere.getVente().getNoVente());
			rqt.setInt(3, enchere.getEncherisseur().getNoUtilisateur());
			rqt.executeUpdate();
			rqt.close();
			cnx.close();
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		}
	}
	
	public static int supprimer (Enchere enchere) throws SQLException {
		return 1;
	}
}

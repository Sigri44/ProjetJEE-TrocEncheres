package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.model.Enchere;
import fr.eni.util.DbConnection;

public class EnchereDAO {
	private static final String AJOUTER = "INSERT INTO ENCHERES (date_enchere, no_utilisateur, no_vente) VALUES (?,?,?)";
	private static final String MODIFIER = "UPDATE ENCHERES SET date_enchere = ?, no_utilisateur = ?, no_vente = ?";
	private static final String SUPPRIMER = "DELETE FROM ENCHERES WHERE no_vente = ?";
	private static final String SEARCHBYVENTE = "SELECT * FROM ENCHERES WHERE no_vente = ?";

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
	
	public static void modifier (Enchere enchere) throws SQLException {
		
	}
	
	public static int supprimer (Enchere enchere) throws SQLException {
		
		return 1;
	}
}

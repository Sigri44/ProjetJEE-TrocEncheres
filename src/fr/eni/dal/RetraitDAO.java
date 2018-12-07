package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.model.Retrait;
import fr.eni.util.DbConnection;

public class RetraitDAO {
	private static final String AJOUTER = "INSERT INTO RETRAITS (rue, code_postal, ville) VALUES (?,?,?)";
	private static final String SUPPRIMER = "DELETE FROM RETRAITS WHERE no_vente = ?";
	private static final String MODIFIER = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_vente = ?";
	private static final String SEARCHBYID = "SELECT * FROM RETRAITS WHERE no_vente = ?";
	private static final String LISTER = "SELECT * FROM RETRAITS";
	
	public static void ajouter (Retrait retrait) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER);
			rqt.setString(1, retrait.getRue());
			rqt.setString(2, retrait.getCodePostal());
			rqt.setString(3, retrait.getVille());
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static void modifier (Retrait retrait) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt =  null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(MODIFIER);
			rqt.setString(1, retrait.getRue());
			rqt.setString(2, retrait.getCodePostal());
			rqt.setString(3, retrait.getVille());
			rqt.executeUpdate();
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static Retrait rechercherParId (int id) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Retrait retrait = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYID);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				if (retrait == null) retrait = new Retrait();
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return retrait;
	}
	
	public static ArrayList<Retrait> lister() throws SQLException{
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<Retrait> listeRetraits = new ArrayList<Retrait>();
		try {
			cnx= DbConnection.seConnecter();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(LISTER);
			Retrait retrait;
			while (rs.next()) {
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				listeRetraits.add(retrait);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeRetraits;
	}
	
	public static int supprimer (int id) throws SQLException {
		Connection cnx=null;
		PreparedStatement rqt=null;
		int nbreEnrgt = 0;
		try {
			cnx=DbConnection.seConnecter();
			rqt=cnx.prepareStatement(SUPPRIMER);
			rqt.setInt(1, id);
			nbreEnrgt= rqt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbreEnrgt;
	}
}
package fr.eni.dal;

import java.sql.*;
import java.util.*;

import fr.eni.model.Categorie;
import fr.eni.util.DbConnection;

public class CategorieDAO {

	private static final String AJOUTER = "insert into CATEGORIES (libelle) values (?)";
	private static final String SUPPRIMER = "delete from CATEGORIES where no_categorie = ?";
	private static final String MODIFIER = "update CATEGORIES set libelle =? where no_categorie = ?";
	private static final String RECHERCHER = "select * from CATEGORIES where no_categorie = ?";
	private static final String LISTER 	="select * from CATEGORIES ";	

	/*Ajouter une categorie*/
	public static void ajouter (Categorie categirie) throws SQLException {
		try {
			Connection cnx = DbConnection.seConnecter();
			PreparedStatement rqt =cnx.prepareStatement(AJOUTER);
			rqt.setString(1, categirie.getLibelle());
			rqt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			new SQLException(e.getMessage());
		}
	}

	public static void modifier (Categorie categorie) throws SQLException {
		Connection cnx =  null;
		PreparedStatement rqt = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt =cnx.prepareStatement(MODIFIER);
			rqt.setString(1, categorie.getLibelle());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public  static int supprimer (Categorie categorie)throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		int nbrsupp = 0;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SUPPRIMER);
			rqt.setString(1, categorie.getLibelle());
			nbrsupp =rqt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbrsupp;
	}
	
	public static ArrayList<Categorie> lister() throws SQLException{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();
		try {
			cnx= DbConnection.seConnecter();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(LISTER);
			Categorie categorie;
			while (rs.next()) {
				categorie = new Categorie(rs.getString("libelle"));
				listeCategorie.add(categorie);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeCategorie;
	}
	
	public static Categorie recherche(int id) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Categorie categorie = null;
		try {
			cnx =DbConnection.seConnecter();
			rqt =cnx.prepareStatement(RECHERCHER);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			while (rs.next()) {
				if (categorie == null) categorie = new Categorie();
				categorie.setNoCategorie(Integer.parseInt(rs.getString("no_categorie")));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return categorie;
	}
}

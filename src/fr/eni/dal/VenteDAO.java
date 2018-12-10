package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.model.Categorie;
import fr.eni.model.Retrait;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;
import fr.eni.util.DbConnection;


public class VenteDAO {
	private static final String AJOUTER = "insert into VENTES (nomarticle,description,date_fin_encheres,"
			+ "prix_initial,no_utilisateur,no_categorie) "
			+ "values (?,?,?,?,?,?)";
	private static final String SUPPRIMER = "delete from VENTES where no_vente = ?";
	private static final String MODIFIER = "update VENTES set nomarticle =?, description = ?, date_fin_encheres = ?, prix_initial = ?, "
			+ "prix_vente = ?, no_utilisateur = ?, "
			+ "no_categorie = ? ,"
			+ "where no_vente = ?";
	private static final String RECHERCHER ="select * from VENTES where no_vente = ?";
	private static final String LISTER 	= "select * from VENTES";
	private static final String LASTID 	= "SELECT TOP 1 no_vente from VENTES ORDER BY no_vente DESC";
			
	
	public static int ajouter (Vente vente) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int nbre = 0 ;	
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER);
			rqt.setString(1, vente.getNomArticle());
			rqt.setString(2, vente.getDescription());
			rqt.setDate(3, new java.sql.Date(vente.getDateFinEnchere().getTime()));
			rqt.setInt(4, vente.getMiseAPrix());
			rqt.setInt(5, vente.getVendeur().getNoUtilisateur());
			rqt.setInt(6, vente.getCategorie().getNoCategorie());
			rqt.executeUpdate();
			/*
			Retrait retrait = vente.getRetrait();
			RetraitDAO.ajouter(retrait);
			*/
				
		}  catch (SQLException e) {
			new SQLException(e.getMessage());
		}
		finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbre;
	}
	
	public static ArrayList<Vente> lister() throws SQLException{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Vente> listeVente = new ArrayList<Vente>();
		try {
			cnx= DbConnection.seConnecter();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(LISTER);
			Vente vente;
			
			while (rs.next()) {
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente = new Vente(rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_vente"), categorie, retrait, acheteur,vendeur);
				listeVente.add(vente);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeVente;
	}

	public static int supprimer (int id) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
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
	
	public static Vente getVenteById(int id) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Vente vente = null;
		try {
			cnx =DbConnection.seConnecter();
			rqt = cnx.prepareStatement(RECHERCHER);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			
			while (rs.next()) {
				if (vente == null)vente = new Vente();
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente.setNoVente(rs.getInt("no_vente"));
				vente.setNomArticle(rs.getString("nomArticle"));
				vente.setDescription(rs.getString("description"));
				vente.setDateFinEnchere(rs.getDate("date_fin_encheres"));
				vente.setMiseAPrix(rs.getInt("prix_vente"));
				vente.setCategorie(categorie);
				vente.setAcheteur(acheteur);
				vente.setVendeur(vendeur);
				vente.setRetrait(retrait);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return vente;
	}
	
	/*
	public static Utilisateur getAcheteurByVenteId(int venteId) {
		Utilisateur acheteur = new Utilisateur();
		acheteur=EnchereDAO.getWinnerByVenteId(venteId);
		return acheteur;
	}*/
}

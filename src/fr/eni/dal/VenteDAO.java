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
			+ "prix_initial,prix_vente,no_utilisateur,no_categorie, image) "
			+ "values (?,?,?,?,?,?,?,?)";
	private static final String SUPPRIMER = "delete from VENTES where no_vente = ?";
	private static final String MODIFIER = "update VENTES set nomarticle =?, description = ?, date_fin_encheres = ?, prix_initial = ?, "
			+ "prix_vente = ?, no_utilisateur = ?, "
			+ "no_categorie = ? ,"
			+ "where no_vente = ?";
	private static final String MODIFIERPRIXVENTE = "update VENTES set prix_vente =? where no_vente = ?";
	private static final String MODIFIERIMAGE = "update VENTES set image =? where no_vente = ?";
	private static final String RECHERCHER = "select * from VENTES where no_vente = ?";
	private static final String SEARCHBYNAME = "SELECT * FROM VENTES WHERE nomarticle LIKE ('%?%')";
	private static final String SEARCHBYIDUSER = "SELECT * FROM VENTES WHERE no_utilisateur = ? AND no_vente = ?";
	private static final String SEARCHMYAUCTIONS = "SELECT v.no_vente, v.nomarticle, v.description, v.date_fin_encheres, v.prix_initial, v.prix_vente, v.no_utilisateur, v.no_categorie FROM VENTES v JOIN ENCHERES e ON (v.no_vente = e.no_vente) WHERE e.no_utilisateur = ? AND e.date_enchere > GETDATE()";
	private static final String SEARCHMYOLDAUCTIONS = "SELECT v.no_vente, v.nomarticle, v.description, v.date_fin_encheres, v.prix_initial, v.prix_vente, v.no_utilisateur, v.no_categorie FROM VENTES v JOIN ENCHERES e ON (v.no_vente = e.no_vente) WHERE e.no_utilisateur = ? AND e.date_enchere < GETDATE()";
	private static final String LISTER = "select * from VENTES ORDER BY no_vente DESC";
	private static final String LASTID = "SELECT TOP 1 no_vente from VENTES ORDER BY no_vente DESC";
	
	public static long modifierPrixVente (Vente vente, int proposition) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		int nbr = 0;
		ResultSet rs = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(MODIFIERPRIXVENTE);
			rqt.setInt(1, proposition);
			rqt.setInt(2, vente.getNoVente());
			nbr = rqt.executeUpdate();			
		}  catch (SQLException e) {
			new SQLException(e.getMessage());
		}
		finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbr;
	}
	
	public static long modifierImage (int noVente, String image) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		int nbr = 0;
		ResultSet rs = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(MODIFIERIMAGE);
			rqt.setString(1, image);
			rqt.setInt(2, noVente);
			nbr = rqt.executeUpdate();			
		}  catch (SQLException e) {
			new SQLException(e.getMessage());
		}
		finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return nbr;
	}
	
	public static long ajouter (Vente vente) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		long key = -1L;
		ResultSet rs = null;
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, vente.getNomArticle());
			rqt.setString(2, vente.getDescription());
			rqt.setDate(3, new java.sql.Date(vente.getDateFinEnchere().getTime()));
			rqt.setInt(4, vente.getMiseAPrix());
			rqt.setInt(5, vente.getMiseAPrix());
			rqt.setInt(6, vente.getVendeur().getNoUtilisateur());
			rqt.setInt(7, vente.getCategorie().getNoCategorie());
			rqt.setString(8, vente.getImage());
			rqt.executeUpdate();
			rs = rqt.getGeneratedKeys();
			if (rs.next()) {	
				key=rs.getInt(1);
			    Retrait retrait = vente.getRetrait();
			    retrait.setNoVente((int) key);
			    RetraitDAO.ajouter(retrait);
			}				
		}  catch (SQLException e) {
			new SQLException(e.getMessage());
		}
		finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return key;
	}
	
	public static ArrayList<Vente> lister() throws SQLException {
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
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), categorie, retrait, acheteur, vendeur, rs.getString("image"));
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
			cnx = DbConnection.seConnecter();
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
				vente.setMiseAPrix(rs.getInt("prix_initial"));
				vente.setPrixVente(rs.getInt("prix_vente"));
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
	
	public static ArrayList<Vente> listVenteByName(String nomArticle) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Vente> listeVenteByName = new ArrayList<Vente>();
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYNAME);
			rqt.setString(1, nomArticle);
			rs= rqt.executeQuery();
			Vente vente;
			while (rs.next()) {
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),rs.getInt("prix_vente"), categorie, retrait, acheteur, vendeur, rs.getString("image"));
				listeVenteByName.add(vente);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeVenteByName;
	}
	
	public static ArrayList<Vente> getVenteByIdUser(int no_utilisateur) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Vente> listeMesVentes = new ArrayList<Vente>();
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHBYIDUSER);
			rqt.setInt(1, no_utilisateur);
			rs = rqt.executeQuery();
			Vente vente;
			
			while (rs.next()) {
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), categorie, retrait, acheteur, vendeur, rs.getString("image"));
				listeMesVentes.add(vente);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeMesVentes;
	}
	
	public static ArrayList<Vente> getEnchereByIdUser(int no_utilisateur) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Vente> listeMesEncheres = new ArrayList<Vente>();
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHMYAUCTIONS);
			rqt.setInt(1, no_utilisateur);
			rs = rqt.executeQuery();
			Vente vente;
			
			while (rs.next()) {
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), categorie, retrait, acheteur, vendeur, rs.getString("image"));
				listeMesEncheres.add(vente);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeMesEncheres;
	}
	
	public static ArrayList<Vente> getAcquisitionByIdUser(int no_utilisateur) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArrayList<Vente> listeMesAcquisitions = new ArrayList<Vente>();
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(SEARCHMYOLDAUCTIONS);
			rqt.setInt(1, no_utilisateur);
			rs = rqt.executeQuery();
			Vente vente;
			
			while (rs.next()) {
				Categorie categorie = CategorieDAO.recherche(Integer.parseInt(rs.getString("no_categorie")));
				Retrait retrait = RetraitDAO.rechercherParId(Integer.parseInt(rs.getString("no_vente")));
				Utilisateur acheteur = new Utilisateur();
				Utilisateur vendeur = UtilisateurDAO.rechercherParId(Integer.parseInt(rs.getString("no_utilisateur")));
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"), rs.getInt("prix_vente"), categorie, retrait, acheteur, vendeur, rs.getString("image"));
				listeMesAcquisitions.add(vente);
			}
		} finally {
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeMesAcquisitions;
	}
	
	/*
	public static Utilisateur getAcheteurByVenteId(int venteId) {
		Utilisateur acheteur = new Utilisateur();
		acheteur=EnchereDAO.getWinnerByVenteId(venteId);
		return acheteur;
	}*/
}

package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.model.Categorie;
import fr.eni.model.Retrait;
import fr.eni.model.Utilisateur;
import fr.eni.model.Vente;
import fr.eni.util.DbConnection;


public class VenteDAO {
	private static final String AJOUTER = "insert into VENTES (nomarticle,description,date_fin_encheres,"
			+ "prix_initial,prix_vente,no_utilisateur,no_categorie) "
			+ "values (?,?,?,?,?,?)";
	private static final String SUPPRIMER 	= "delete from VENTES where id = ?";
	private static final String MODIFIER = "update VENTES set nomarticle =?, description = ?, date_fin_encheres = ?, prix_initial = ?, "
			+ "prix_vente = ?, no_utilisateur = ?, "
			+ "no_categorie = ? ,"
			+ "where id = ?";
	private static final String RECHERCHER ="select nomarticle,description,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie from VENTES where id = ?";
	private static final String LISTER 	= 
			"select nomarticle,description,date_fin_encheres,prix_vente,RETRAITS.rue,libelle,UTILISATEURS.pseudo from VENTES,UTILISATEURS,CATEGORIES,RETRAITS  where VENTES.no_utilisateur = UTILISATEURS.no_utilisateur and VENTES.no_categorie = CATEGORIES.no_categorie and VENTES.no_vente= RETRAITS.no_vente;";

     private static final String ListerRetrit = "select rue,code_postal,ville from RETRAITS";
	
	public static int ajouter (Vente vente ) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		int nbre =0 ;	
		try {
			cnx = DbConnection.seConnecter();
			rqt = cnx.prepareStatement(AJOUTER);
			rqt.setString(1, vente.getNomArticle());
			rqt.setString(2, vente.getDescription());
			rqt.setDate(2, new java.sql.Date(vente.getDateFinEnchere().getTime()));
			rqt.setDouble(4, vente.getMiseAPrix());
			rqt.setInt(5, vente.getCategorie().getNoCategorie());
			rqt.setString(6, vente.getRetrait().getRue());
			rqt.setInt(7, vente.getVendeur().getNoUtilisateur());
			rqt.setInt(8, vente.getAcheteur().getNoUtilisateur());
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
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<Vente> listeVente = new ArrayList<Vente>();
		try {
			cnx= DbConnection.seConnecter();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(LISTER);
			Vente vente;
			
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getString("libelle"));
				Retrait retrait = new Retrait(rs.getString("rue"));
				 Utilisateur acheteur = new Utilisateur(rs.getString("pseudo"));
				vente = new Vente(rs.getString("nomArticle"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getDouble("prix_vente"), categorie, retrait, acheteur);
				listeVente.add(vente);
			}
		} finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeVente;
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
	/*Modifier le contenue à faire */	
}

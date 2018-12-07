package fr.eni.model;

import java.util.Date;

public class Vente {

	private int noVente;
	private String nomArticle;
	private String description;
	private Date dateFinEnchere;
	private double miseAPrix;
	private Categorie categorie;
	private Retrait retrait;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	
	public Vente() {
	}

	
	public Vente(String nomArticle, String description, Date dateFinEnchere, double miseAPrix) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
	}
	


	public Vente(String nomArticle, String description, Date dateFinEnchere, double miseAPrix, Categorie categorie,
			Retrait retrait, Utilisateur acheteur,Utilisateur vendeur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.retrait = retrait;
		this.acheteur = acheteur;
		this.vendeur= vendeur;
	}


	public Vente(String nomArticle) {
		super();
		this.nomArticle = nomArticle;
	}


	public Vente(int noVente, String nomArticle, String description, Date dateFinEnchere, double miseAPrix, Categorie categorie, Retrait retrait) {
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	public int getNoVente() {
		return noVente;
	}
	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateFinEnchere() {
		return dateFinEnchere;
	}
	public void setDateFinEnchere(Date dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}
	public double getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(double miseAPrix) {
		this.miseAPrix = miseAPrix;
	}	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Retrait getRetrait() {
		return retrait;
	}
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	
	
	
}

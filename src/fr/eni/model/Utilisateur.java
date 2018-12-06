package fr.eni.model;

public class Utilisateur{

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String mail;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private String prenom;
	private boolean admin;
	
	public Utilisateur(String pseudo, String nom, String mail, String telephone, String rue, String codePostal, String ville,
			String motDePasse, int credit, String prenom, boolean admin) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.mail = mail;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.prenom = prenom;
		this.setAdmin(admin);
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String mail, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, String prenom, boolean admin) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.mail = mail;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.setCredit(credit);
		this.setPrenom(prenom);
		this.setAdmin(admin);
	}	
	
	public Utilisateur() {
	}	

	
	public Utilisateur(String nom) {
		super();
		this.nom = nom;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", mail=" + mail
				+ ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", motDePasse=" + motDePasse + "]";
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}

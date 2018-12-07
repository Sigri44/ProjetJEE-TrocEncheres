package fr.eni.dal;

public class CategorieDAO {

	private static final String AJOUTER = "insert into CATEGORIES (libelle) values (?)";
	private static final String SUPPRIMER = "delete from CATEGORIES where no_categorie = ?";
	private static final String MODIFIER = "update CATEGORIES set libelle =? where no_categorie = ?";
	private static final String RECHERCHER = "select * from CATEGORIES where no_categorie = ?";
	private static final String LISTER 	="select  * from CATEGORIES ";	
			
	
}

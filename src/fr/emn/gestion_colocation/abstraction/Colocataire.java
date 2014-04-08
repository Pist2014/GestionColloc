package fr.emn.gestion_colocation.abstraction;

public class Colocataire {
	
	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */
	
	private String pseudo;
	private String nom;
	private String prenom;
	
	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEURS
	 *----------------------------------------------------------------------------------------- */
	
	public Colocataire(String pseudo, String nom, String prenom) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */
	
	public String getPseudo() {
		return this.pseudo;
	}

	public void setEmail(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String toString() {
		return this.getNom() + ", " + this.getPrenom() + ", " + this.getPseudo();
	}
}

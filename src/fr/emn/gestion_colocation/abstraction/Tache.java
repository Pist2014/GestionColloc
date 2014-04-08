package fr.emn.gestion_colocation.abstraction;

public class Tache {
	
	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */
	
	private int valeur;
	private String nom;
	private String type;
	
	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEURS
	 *----------------------------------------------------------------------------------------- */
	
	public Tache(int valeur, String nom, String type) {
		super();
		this.valeur = valeur;
		this.nom = nom;
		this.type = type;
	}
	
	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */
	
	public int getValeur() {
		return this.valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}

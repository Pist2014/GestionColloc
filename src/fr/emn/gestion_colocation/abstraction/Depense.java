package fr.emn.gestion_colocation.abstraction;

import fr.emn.gestion_colocation.abstraction.Date;

public class Depense {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */
	
	private String intitule;
	private float montant;
	private Date date;
	private boolean alimentaire; // pour le prix moyen du repas
	private Colocataire colocataire;
	
	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEURS
	 *----------------------------------------------------------------------------------------- */

	public Depense(String intitule, float montant, Date date, boolean alimentaire, Colocataire colocataire) {
		super();
		this.intitule = intitule;
		this.montant = montant;
		this.date = date;
		this.alimentaire = alimentaire;
		this.colocataire = colocataire;
	}
	
	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */
	
	public String getIntitule() {
		return this.intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Colocataire getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(Colocataire colocataire) {
		this.colocataire = colocataire;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAlimentaire() {
		return this.alimentaire;
	}

	public void setAlimentaire(boolean alimentaire) {
		this.alimentaire = alimentaire;
	}
	
}

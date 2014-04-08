package fr.emn.gestion_colocation.abstraction;

import java.util.Date;

public class Corvee {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */
	
	private Tache tache;
	private Date date;
	private boolean definitive;
	private Colocataire colocataire;
	
	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEURS
	 *----------------------------------------------------------------------------------------- */
	public Corvee(Tache tache, Date date, Colocataire colocataire) {
		super();
		this.tache = tache;
		this.date = date;
		this.definitive = false;
		this.colocataire = colocataire;
	}
	
	public Corvee(Tache tache, Date date, boolean definitive, Colocataire colocataire) {
		super();
		this.tache = tache;
		this.date = date;
		this.definitive = definitive;
		this.colocataire = colocataire;
	}
	
	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */
	
	public Tache getTache() {
		return this.tache;
	}
	
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isDefinitive() {
		return this.definitive;
	}
	
	public void setDefinitive(boolean definitive) {
		this.definitive = definitive;
	}
	
	public Colocataire getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(Colocataire colocataire) {
		this.colocataire = colocataire;
	}
	
}

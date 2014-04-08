package fr.emn.gestion_colocation.abstraction;

public class Date {

	private int jour;
	private int mois;
	private int annee;
	
	public Date(int j, int m, int a){
		this.jour = j;
		this.mois = m;
		this.annee = a;
	}
	
	public static Date convert(String s){
		
		int j = Integer.parseInt(s.substring(0,2));
		int m = Integer.parseInt(s.substring(3,5));
		int a = Integer.parseInt(s.substring(6,10));
		
		return new Date(j,m,a);
		
	}
	
	public String toString(){
		
		String s ="";
		
		if(this.jour < 10){
			s = s + "0";
		}
		s = s + jour + "/";
		
		if(this.mois < 10){
			s = s + "0";
		}
		s = s + mois + "/";
		s = s + annee;		
		
		return s;
	}
}


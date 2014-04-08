package fr.emn.gestion_colocation.abstraction;

public class Remboursements {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private double[][] dettes;
	private Colocataire[] colocataires;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEURS
	 *----------------------------------------------------------------------------------------- */

	public Remboursements (double[][] dettes, Colocataire[] colocataires) {
		this.dettes = dettes;
		this.colocataires = colocataires;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */
	public Colocataire[] getColocataire() {
		return this.colocataires;
	}

	public Colocataire getColocataire(int i) {
		return this.colocataires[i];
	}

	public void setColocataire(int i, Colocataire colocataire) {
		this.colocataires[i] = colocataire;
	}

	public double[][] getDette() {
		return this.dettes;
	}

	public double getDette(int i, int j) {
		return this.dettes[i][j];
	}

	public void setDette(int i, int j, double dette) {
		this.dettes[i][j] = dette;
	}

	public String toString() {
		String res = "";
		for(int i=0; i<this.getDette().length; i++){
			for(int j=0; j<this.getDette().length; j++){
				res = res+this.getDette(i, j)+"  ";
			}
			res = res+"\n";
		}
		return  res;
	}

	public double getEntrees(int j) {
		double res = 0;
		for(int i=0; i<this.getDette().length; i++) {
			res += this.getDette(i, j);
		}
		return res;
	}

	public double getSorties(int i) {
		double res = 0;
		for(int j=0; j<this.getDette().length; j++) {
			res += this.getDette(i, j);
		}
		return res;
	}

	public boolean isDeficitaire(int i) {
		return this.getEntrees(i)-this.getSorties(i)<0;
	}

	public boolean isBeneficiaire(int j) {
		return this.getEntrees(j)-this.getSorties(j)>0;
	}

	public Remboursements simplification(int nbColocataires) {

		Remboursements res = new Remboursements(new double[nbColocataires][nbColocataires], this.getColocataire()); 

		for(int i=0; i<this.getDette().length; i++){
			for(int j=0; j<this.getDette().length; j++){
				res.setDette(i, j, this.getDette(i, j));
			}
		}

		for(int i=0; i<this.getDette().length; i++){
			for(int j=0; j<this.getDette().length; j++){
				if(this.getDette(i, j) !=0 && this.getDette(j, i) !=0){
					if(this.getDette(i, j) > this.getDette(j, i)) {
						res.setDette(i, j, this.getDette(i, j)-this.getDette(j, i));
						res.setDette(j, i, 0);
					}
					if(this.getDette(i, j) < this.getDette(j, i)) {
						res.setDette(i, j, this.getDette(j, i)-this.getDette(i, j));
						res.setDette(i, j, 0);
					}
					if(this.getDette(i, j) == this.getDette(j, i)) {
						res.setDette(j, i, 0);
						res.setDette(i, j, 0);
					}
				}
			}
		}
		return res;
	}

	public String aide() {
		String res = "";
		for(int i=0; i<this.getDette().length; i++){
			if(this.getEntrees(i)-this.getSorties(i)>0){
				res += this.getColocataire(i).getPrenom()+" doit recevoir "+(this.getEntrees(i)-this.getSorties(i))+" €"+"\n";
			}
			if(this.getEntrees(i)-this.getSorties(i)<0){
				res += this.getColocataire(i).getPrenom()+" doit donner "+(-this.getEntrees(i)+this.getSorties(i))+" €"+"\n";
			}
		}
		return res;
	}

	public static double[][] classement(double[] tableau){
		double[][] tableauClassement = new double[tableau.length][2];
		double[] copie = new double[tableau.length];

		for(int l = 0; l<tableau.length; l++){
			copie[l]=tableau[l];
		}

		for(int j = 0; j<copie.length; j++){	
			double res = 0;
			int k = 0;
			for(int i=0; i<copie.length; i++) {
				if(copie[i]>res){
					res = copie[i];
					k = i;
				}
			}
			copie[k]=0;
			tableauClassement[j][0] = k;
			tableauClassement[j][1] = res;

		}
		return tableauClassement;

	}

	// utiliser les méta heuristiques, plutôt que de simple heuristique
	public Remboursements remboursementOptimal(int nbColocataires) {

		Remboursements res = new Remboursements(new double[nbColocataires][nbColocataires], this.getColocataire()); 

		double[] debitRestant = new double[nbColocataires];
		double[] creditRestant = new double[nbColocataires];

		for(int k=0; k<nbColocataires; k++){
			if(this.isBeneficiaire(k)) {
				debitRestant[k] = 0;
				creditRestant[k] = this.getEntrees(k)-this.getSorties(k);
			}

			if(this.isDeficitaire(k)) {
				debitRestant[k] = this.getSorties(k)-this.getEntrees(k);
				creditRestant[k] = 0 ;
			}	
		}

		double[][] debitRestantDecroissant = classement(debitRestant);
		double[][] creditRestantDecroissant = classement(creditRestant);

		int j = 0;
		int i=0;

			while(debitRestantDecroissant[i][1]!=0 && j<creditRestantDecroissant.length && i<debitRestantDecroissant.length){

				double diff = debitRestantDecroissant[i][1]-creditRestantDecroissant[j][1];

				if(diff>0){
					res.setDette((int)(debitRestantDecroissant[i][0]), (int)(creditRestantDecroissant[j][0]), creditRestantDecroissant[j][1]);
					creditRestantDecroissant[j][1] = 0;
					debitRestantDecroissant[i][1] -= creditRestantDecroissant[j][1];
					j++;
				}
				
				else if(diff<=0){
					res.setDette((int)(debitRestantDecroissant[i][0]), (int)(creditRestantDecroissant[j][0]), debitRestantDecroissant[i][1]);
					creditRestantDecroissant[j][1] -= debitRestantDecroissant[i][1];
					debitRestantDecroissant[i][1] = 0;
					i++;
				}
			}
		return res;
	}

	public String bilan() {
		String res = "";
		for(int i=0; i<this.getDette().length; i++){
			for(int j=0; j<this.getDette().length; j++){
				if(this.getDette(i, j)!=0){
					res += this.getColocataire(i).getPrenom()+" doit "+this.getDette(i, j)+" € à "+this.getColocataire(j).getPrenom()+"\n";
				}
			}
		}
		if(res.equals("")){
			return "il n'y a pas de dette";
		}
		return res;
	}

	public static void main(String[] args) {
		int nbColocataires = 5;
		double[][] dettes = new double[nbColocataires][nbColocataires];
		dettes[0][0] = 0;
		dettes[0][1] = 3;
		dettes[0][2] = 100;
		dettes[0][3] = 4;
		dettes[0][4] = 24;
		dettes[1][0] = 12;
		dettes[1][1] = 0;
		dettes[1][2] = 25;
		dettes[1][3] = 0;
		dettes[1][4] = 0;
		dettes[2][0] = 2;
		dettes[2][1] = 32;
		dettes[2][2] = 0;
		dettes[2][3] = 18;
		dettes[2][4] = 0;
		dettes[3][0] = 4;
		dettes[3][1] = 1;
		dettes[3][2] = 8;
		dettes[3][3] = 0;
		dettes[3][4] = 0;
		dettes[4][0] = 5;
		dettes[4][1] = 1;
		dettes[4][2] = 0;
		dettes[4][3] = 12;
		dettes[4][4] = 0;


		Colocataire[] colocataires = new Colocataire[nbColocataires];

		colocataires[0] = new Colocataire("cloise12", "Loiseau", "Corentin");
		colocataires[1] = new Colocataire("gpain12", "Pain", "Guillaume");
		colocataires[2] = new Colocataire("bmenou12", "Menoud", "Bertrand");
		colocataires[3] = new Colocataire("agoy12", "Goy", "Alexis");
		colocataires[4] = new Colocataire("asavry13", "Savry", "Amandine");

		Remboursements test = new Remboursements(dettes, colocataires);

		System.out.println(test);
		System.out.println(test.aide());

		System.out.println(test.simplification(nbColocataires));
		System.out.println(test.simplification(nbColocataires).aide());


		System.out.println(test.remboursementOptimal(nbColocataires));
		System.out.println(test.remboursementOptimal(nbColocataires).bilan());

		//		double[] dettes = new double[6];
		//		dettes[0]=1;
		//		dettes[1]=3;
		//		dettes[2]=1;
		//		dettes[3]=1;
		//		dettes[4]=4;
		//		dettes[5]=0;
		//		
		//		double[][] dette = classement(dettes);
		//		for(int i =0; i<6;i++){
		//			System.out.println(dette[i][0]+ " " +dette[i][1]);
		//		}



	}
}




package fr.emn.gestion_colocation.abstraction;


import java.util.Observable;
import java.util.Vector;

	public abstract class Colocation extends Observable {
		
		public static final Integer CHANGEMENT_DEPENSES = new Integer(1);
		public static final Integer CHANGEMENT_COLOC = new Integer(2);
		
		public Colocation() {
			super();
		}
		
		public abstract boolean isCree();
		
		public abstract void saveColocataire(Colocataire n);
		
		public abstract Vector<String> findAllRemboursements();
		
		public abstract Object[][] findEntreesSortiesAsTable();
		
		public abstract Colocataire[] findAllColocataires();
		
		public abstract double[][] allRemboursementsAsTable();

		public abstract Vector<String> allRemboursementsOptimaux();
		
		public abstract void saveDepense(Depense d, Vector<Colocataire> profiteurs, boolean creation);
		
		protected class ImportException extends Exception {

			private static final long serialVersionUID = 1L;
			
			public ImportException(){
				super();
			}
			
		}

		public abstract void deleteColocation();
	}

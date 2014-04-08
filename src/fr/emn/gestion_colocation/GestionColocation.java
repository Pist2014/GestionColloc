package fr.emn.gestion_colocation;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.abstraction.ColocationJDBC;
import fr.emn.gestion_colocation.presentation.MainWindow;



public class GestionColocation {

		public static void main(String[] args) {
			
			Colocation colocation = new ColocationJDBC();

			MainWindow fen = new MainWindow(colocation);
			fen.setVisible(true);	
		}
}

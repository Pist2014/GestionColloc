package fr.emn.gestion_colocation.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.abstraction.Depense;
import fr.emn.gestion_colocation.presentation.DepenseDialog;

public class ControlJButtonValiderDepense implements ActionListener{



	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private Colocation modele;
	private boolean creation;
	private DepenseDialog parent;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlJButtonValiderDepense(DepenseDialog parent, Colocation modele, boolean creation) {
		this.modele = modele;
		this.parent = parent;
		this.creation = creation;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	public void actionPerformed(ActionEvent e) {
		if(parent.getMontant()>0 && !parent.getPayeur().equals("") && !parent.getProfiteur().equals("")){
			Depense d = new Depense(parent.getIntitule(), parent.getMontant(), parent.getDate(), parent.isAlimentaire(), parent.getPayeur());
			try {
				modele.saveDepense(d, parent.getProfiteur(), creation);
				parent.dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(parent, "On ne peut pas!", "Erreur!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			Icon im = new ImageIcon("images"+File.separator+"info.png");
			JOptionPane.showMessageDialog(parent,"Veuillez préciser tous les champs","",JOptionPane.INFORMATION_MESSAGE, im);
		}
	}
}

package fr.emn.gestion_colocation.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.ConfirmationJOptionPane;

public class ControlMenuDissolution implements ActionListener  {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private Colocation modele;
	private Frame parent;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlMenuDissolution(Frame parent, Colocation modele) {
		this.modele = modele;
		this.parent = parent;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		new ConfirmationJOptionPane("dissolution", this.modele);
		System.exit(0);
	}
}

package fr.emn.gestion_colocation.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.emn.gestion_colocation.presentation.DepenseDialog;

public class ControlJButtonAnnuler implements ActionListener{



	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private DepenseDialog parent;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlJButtonAnnuler(DepenseDialog parent) {
		this.parent = parent;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	public void actionPerformed(ActionEvent e) {
		parent.dispose();
	}
}

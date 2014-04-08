package fr.emn.gestion_colocation.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.ColocataireDialog;
import fr.emn.gestion_colocation.presentation.RoundButton;

public class ControlMenuCreation implements ActionListener  {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private Colocation modele;
	private Frame parent;
	private JMenuItem nouveau;
	private JMenuItem fin;
	private RoundButton plusDepense;
	private RoundButton plusCorvee;
	private RoundButton plusRemboursement;


	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlMenuCreation(Frame parent, Colocation modele, 
			JMenuItem nouveau, JMenuItem fin, RoundButton plusDepense,
			RoundButton plusCorvee, RoundButton plusRemboursement) {
		this.modele = modele;
		this.parent = parent;
		this.nouveau = nouveau;
		this.fin =fin;
		this.plusDepense = plusDepense;
		this.plusCorvee = plusCorvee;
		this.plusRemboursement = plusRemboursement;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ColocataireDialog colocataireDialog = new ColocataireDialog(parent, modele, nouveau, fin, plusDepense, plusCorvee, plusRemboursement);
		colocataireDialog.setVisible(true);
	}
}

package fr.emn.gestion_colocation.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.emn.gestion_colocation.presentation.ColocataireDialog;
import fr.emn.gestion_colocation.presentation.RoundButton;

public class ControlJButtonCreerColocation implements ActionListener {



	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private ColocataireDialog parent;
	private JMenuItem nouveau;
	private JMenuItem fin;
	private RoundButton plusDepense;
	private RoundButton plusCorvee;
	private RoundButton plusRemboursement;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlJButtonCreerColocation(ColocataireDialog parent, JMenuItem nouveau,
			JMenuItem fin, RoundButton plusDepense,
			RoundButton plusCorvee, RoundButton plusRemboursement) {
		this.parent = parent;
		this.nouveau=nouveau;
		this.fin =fin;
		this.plusDepense = plusDepense;
		this.plusCorvee = plusCorvee;
		this.plusRemboursement = plusRemboursement;
		}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	public void actionPerformed(ActionEvent e) {
		try {
			parent.setVisible(false);
			nouveau.setEnabled(false);
			fin.setEnabled(true);
			plusDepense.setEnabled(true);
			plusCorvee.setEnabled(true);
			plusRemboursement.setEnabled(true);
		} 
		catch (Exception e1) {
			JOptionPane.showMessageDialog(parent, "On ne peut pas!", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
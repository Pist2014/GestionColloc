package fr.emn.gestion_colocation.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.RemboursementDialog;

public class ControlJButtonRemboursement implements ActionListener {

	private Colocation modele;
	private Frame parent;

	public ControlJButtonRemboursement(Frame parent, Colocation modele) {
		this.modele = modele;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RemboursementDialog remboursementDialog = new RemboursementDialog(parent, modele);
		remboursementDialog.setVisible(true);
	}

}

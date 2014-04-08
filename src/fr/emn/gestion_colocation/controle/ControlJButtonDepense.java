package fr.emn.gestion_colocation.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.DepenseDialog;

public class ControlJButtonDepense implements ActionListener {

	private Colocation modele;
	private Frame parent;

	public ControlJButtonDepense(Frame parent, Colocation modele) {
		this.modele = modele;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DepenseDialog depenseDialog = new DepenseDialog(parent, modele, true);
		depenseDialog.setVisible(true);
	}
}

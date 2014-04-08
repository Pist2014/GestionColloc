package fr.emn.gestion_colocation.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.CorveeDialog;

public class ControlJButtonCorvee  implements ActionListener {

	private Colocation modele;
	private Frame parent;

	public ControlJButtonCorvee(Frame parent, Colocation modele) {
		this.modele = modele;
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {
		CorveeDialog corveeDialog = new CorveeDialog(parent, modele, true);
		corveeDialog.setVisible(true);
	}
}

package fr.emn.gestion_colocation.presentation;

import java.awt.Frame;

import javax.swing.JDialog;

import fr.emn.gestion_colocation.abstraction.Colocation;

public class CorveeDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public CorveeDialog(Frame parent, Colocation modele, boolean creation) {
		super(parent, "T‰ches", true);
		this.setLocationRelativeTo(this.getParent());
	}
}

package fr.emn.gestion_colocation.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.emn.gestion_colocation.abstraction.Colocataire;
import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.presentation.ColocataireDialog;

public class ControlJButtonSaveColocataire implements ActionListener{



	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private Colocation modele;
	private ColocataireDialog parent;
	private JList listeColocataires;
	private JTextField nom;
	private JTextField prenom;
	private JTextField pseudo;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public ControlJButtonSaveColocataire(ColocataireDialog parent, Colocation modele, JList listeColocataires, JTextField nom, JTextField prenom, JTextField pseudo ) {
		this.modele = modele;
		this.parent = parent;
		this.listeColocataires=listeColocataires;
		this.nom=nom;
		this.prenom=prenom;
		this.pseudo=pseudo;
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	public void actionPerformed(ActionEvent e) {
		Colocataire n = new Colocataire(parent.getPseudo(), parent.getNom(),
				parent.getPrenom());
		try {
			modele.saveColocataire(n);
			listeColocataires.setListData(ColocataireDialog.colocatairesAsString(modele.findAllColocataires()));
			nom.setText("");
			prenom.setText("");
			pseudo.setText("");
		} 
		catch (Exception e1) {
			JOptionPane.showMessageDialog(parent, "On ne peut pas!", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

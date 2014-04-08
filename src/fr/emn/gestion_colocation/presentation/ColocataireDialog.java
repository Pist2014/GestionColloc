package fr.emn.gestion_colocation.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.emn.gestion_colocation.abstraction.Colocataire;
import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.controle.ControlJButtonCreerColocation;
import fr.emn.gestion_colocation.controle.ControlJButtonSaveColocataire;

public class ColocataireDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nom;
	private JTextField prenom;
	private JTextField pseudo;

	public String getNom() {
		return nom.getText();
	}

	public String getPrenom() {
		return prenom.getText();
	}

	public String getPseudo() {
		return pseudo.getText();
	}

	Vector<Colocataire> allColoc = new Vector<Colocataire>();
	JList listeColocataires;

	public ColocataireDialog(Frame parent, Colocation modele, JMenuItem nouveau,
			JMenuItem fin, RoundButton plusDepense,RoundButton plusCorvee, 
			RoundButton plusRemboursement) {
		super(parent, "Création colocation");
		this.getContentPane().setLayout(new GridLayout(1, 2));
		this.setMinimumSize(new Dimension(200, 100));

		JPanel form = new JPanel(new GridLayout(7, 1));
		form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		form.add(new JLabel("Nom"));
		nom = new JTextField();
		form.add(nom);
		form.add(new JLabel("Prenom"));
		prenom = new JTextField();
		form.add(prenom);
		form.add(new JLabel("Pseudo"));
		pseudo = new JTextField();
		form.add(pseudo);
		
		JPanel buttons = new JPanel();
		JButton save = new JButton("Ajouter -->");
		listeColocataires = new JList();
		save.addActionListener(new ControlJButtonSaveColocataire(this, modele, listeColocataires, nom, prenom, pseudo));
		buttons.add(save);
		form.add(buttons);
		
		JPanel listePanel = new JPanel(new BorderLayout());
		listePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
		
		listePanel.add(new JLabel("Les colocataires"), BorderLayout.NORTH);
		listeColocataires.setListData(colocatairesAsString(modele.findAllColocataires()));
		listePanel.add(new JScrollPane(listeColocataires), BorderLayout.CENTER);
		
		JButton creerColoc = new JButton("Créer la colocation !");
		creerColoc.addActionListener(new ControlJButtonCreerColocation(this, nouveau, fin, plusDepense, plusCorvee, plusRemboursement));
		listePanel.add(creerColoc, BorderLayout.SOUTH);
		
		this.getContentPane().add(form);
		this.getContentPane().add(listePanel);
		
		this.setLocationRelativeTo(parent);
		this.pack();
	}

	public static Vector<String> colocatairesAsString(Colocataire[] colocataires) {
		Vector<String> result = new Vector<String>();
		for (int i=0; i<colocataires.length; i++) {
			result.add(colocataires[i].toString());
		}
		return result;
	}
}

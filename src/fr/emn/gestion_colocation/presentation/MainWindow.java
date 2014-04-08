package fr.emn.gestion_colocation.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.controle.ControlJButtonCorvee;
import fr.emn.gestion_colocation.controle.ControlJButtonDepense;
import fr.emn.gestion_colocation.controle.ControlJButtonRemboursement;
import fr.emn.gestion_colocation.controle.ControlMenuCreation;
import fr.emn.gestion_colocation.controle.ControlMenuDissolution;

public class MainWindow extends JFrame {

	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private static final long serialVersionUID = 1L;
	public static final int LABEL_SIZE = 16;
	private Colocation modele;
	private boolean isCree;
	private JMenuItem nouveau;
	private JMenuItem fin;
	private RoundButton plusDepense;
	private RoundButton plusCorvee;
	private RoundButton plusRemboursement;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public MainWindow(Colocation modele) {

		super("Gestionnaire de Colocation");
		this.modele = modele;
		isCree=this.modele.isCree();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.creerMenu(modele);
		this.creerHaut(modele);
		ControlMenuCreation controlCreation = new ControlMenuCreation(
				this, this.modele, nouveau, fin, plusDepense, plusCorvee, plusRemboursement);
		nouveau.addActionListener(controlCreation);
		this.creerCentre(modele);
		this.creerBas(modele);
		ControlMenuDissolution controlDissolution = new ControlMenuDissolution(
				this, this.modele);
		fin.addActionListener(controlDissolution);
		this.pack();
		this.setLocationRelativeTo(this.getParent());
		Image icone = Toolkit.getDefaultToolkit().getImage("images"+File.separator+"logo.png");
		this.setIconImage(icone);
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    CREATION DE L'INTERFACE
	 *----------------------------------------------------------------------------------------- */

	private void creerMenu(Colocation modele) {
		JMenuBar barreMenu = new JMenuBar();

		// === Fichier ===
		JMenu fichier = new JMenu("Fichier");

		// - Creer
		nouveau = new JMenuItem("Créer la colocation");
		nouveau.setEnabled(!isCree);

		fichier.add(nouveau);

		// - Dissoudre

		fin = new JMenuItem("Dissoudre la colocation");
		fin.setEnabled(isCree);
		fichier.add(fin);

		// - Quitter

		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}			
		});
		fichier.add(quitter);
		
		barreMenu.add(fichier);

		// === Aide ===
		JMenu aide = new JMenu("Aide");

		// - Guide d'utilisation
		JMenuItem guide = new JMenuItem("Guide d'utilisation");
		aide.add(guide);

		// - Depenses
		JMenuItem depenses = new JMenuItem("Les dépenses");
		aide.add(depenses);

		// - Taches
		JMenuItem taches = new JMenuItem("Les tâches");
		aide.add(taches);

		// - Remboursements
		JMenuItem remboursements = new JMenuItem("Les remboursements");
		aide.add(remboursements);

		barreMenu.add(aide);

		this.setJMenuBar(barreMenu);
	}

	private void creerHaut(Colocation modele) {
		JPanel panneauHaut = new JPanel(new GridLayout(1, 3));

		// === Depenses ===
		JPanel panelDepenses = new JPanel(new BorderLayout());
		panelDepenses.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
		
		// - Titre du bouton depenses
		JLabel labelDepenses = new JLabel("Dépenses",SwingConstants.CENTER);
		labelDepenses.setFont(new Font(labelDepenses.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelDepenses.add(labelDepenses, BorderLayout.NORTH);

		// - Bouton des depenses
		plusDepense = new RoundButton(new ImageIcon("images"+File.separator+"euro.png"));
		plusDepense.setToolTipText("saisir une nouvelle dépense");
		ControlJButtonDepense controlPlusDepense = new ControlJButtonDepense(
				this, this.modele);
		plusDepense.addActionListener(controlPlusDepense);
		plusDepense.setEnabled(isCree);
		panelDepenses.add(plusDepense, BorderLayout.CENTER);
		panneauHaut.add(panelDepenses);

		// === Taches ===
		JPanel panelCorvees = new JPanel(new BorderLayout());
		panelCorvees.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		
		// - Titre du bouton tache
		JLabel labelCorvees = new JLabel("Tâches",SwingConstants.CENTER);
		labelCorvees.setFont(new Font(labelCorvees.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelCorvees.add(labelCorvees, BorderLayout.NORTH);

		// - Bouton des taches
		plusCorvee = new RoundButton(new ImageIcon("images"+File.separator+"corvees.png"));
		plusCorvee.setToolTipText("saisir une nouvelle tâche");
		ControlJButtonCorvee controlPlusCorvee = new ControlJButtonCorvee(
				this, this.modele);
		plusCorvee.addActionListener(controlPlusCorvee);
		plusCorvee.setEnabled(isCree);
		panelCorvees.add(plusCorvee, BorderLayout.CENTER);
		panneauHaut.add(panelCorvees);

		// === Remboursements ===
		JPanel panelRemboursements = new JPanel(new BorderLayout());
		panelRemboursements.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));
		
		// - Titre du bouton remboursements
		JLabel labelRemboursements = new JLabel("Remboursements",SwingConstants.CENTER);
		labelRemboursements.setFont(new Font(labelRemboursements.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelRemboursements.add(labelRemboursements, BorderLayout.NORTH);

		// - Bouton des remboursements
		plusRemboursement = new RoundButton(new ImageIcon("images"+File.separator+"remboursements.png"));
		plusRemboursement.setToolTipText("effectuer un remboursement");
		ControlJButtonRemboursement controlPlusRemboursement = new ControlJButtonRemboursement(
				this, this.modele);
		plusRemboursement.addActionListener(controlPlusRemboursement);
		plusRemboursement.setEnabled(isCree);
		panelRemboursements.add(plusRemboursement, BorderLayout.CENTER);
		panneauHaut.add(panelRemboursements);

		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
	}

	private void creerCentre(Colocation modele) {
		JPanel panneauCentre = new JPanel(new GridLayout(1, 2));

		// === Classements Dépenses ===
		JPanel panelClassementD = new JPanel(new BorderLayout());
		panelClassementD.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));

		// - Titre (nord)
		JLabel labelClassementD = new JLabel("Classement Dépenses",SwingConstants.CENTER);
		labelClassementD.setFont(new Font(labelClassementD.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelClassementD.add(labelClassementD, BorderLayout.NORTH);

		// - JTextField (sud)
		JTextField classementD = new JTextField("");
		JScrollPane spD = new JScrollPane( classementD );
		spD.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spD.setPreferredSize(new Dimension(100, 100));
		classementD.setEditable(false);
		panelClassementD.add(spD, BorderLayout.CENTER);
		panneauCentre.add(panelClassementD);

		// === Classements Taches ===
		JPanel panelClassementC = new JPanel(new BorderLayout());
		panelClassementC.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));

		// - Titre (nord)
		JLabel labelClassementC = new JLabel("Classement Tâches",SwingConstants.CENTER);
		labelClassementC.setFont(new Font(labelClassementC.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelClassementC.add(labelClassementC, BorderLayout.NORTH);

		// - JTextField (sud)
		JTextField classementC = new JTextField("");
		JScrollPane spC = new JScrollPane( classementC );
		spC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spC.setPreferredSize(new Dimension(100, 100));
		classementC.setEditable(false);
		panelClassementC.add(spC, BorderLayout.CENTER);
		panneauCentre.add(panelClassementC);

		this.getContentPane().add(panneauCentre, BorderLayout.CENTER);
	}

	private void creerBas(Colocation modele) {
		JPanel panneauBas = new JPanel(new BorderLayout());

		// === Pense-bete ===
		JPanel panelpenseB = new JPanel(new BorderLayout());
		panelpenseB.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

		// - Titre (nord)
		JLabel labelpenseB = new JLabel("Pense-bête",SwingConstants.CENTER);
		labelpenseB.setFont(new Font(labelpenseB.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panelpenseB.add(labelpenseB, BorderLayout.NORTH);

		// - TextArea (sud)
		JTextArea penseB = new JTextArea("");
		JScrollPane spB = new JScrollPane( penseB );
		spB.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spB.setPreferredSize(new Dimension(100, 100));
		penseB.setEditable(true);
		panelpenseB.add(spB, BorderLayout.CENTER);
		panneauBas.add(panelpenseB);

		this.getContentPane().add(panneauBas, BorderLayout.SOUTH);
	}
}

package fr.emn.gestion_colocation.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;

import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.controle.ControlJButtonAlgoRemboursement;

public class RemboursementDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	public static final int LABEL_SIZE = 14;

	Vector<String> allRemboursements = new Vector<String>();
	JTextArea lesRemboursements;

	Vector<String> allEntreesSorties = new Vector<String>();
	JTable lesEntreesSorties;

	public RemboursementDialog(Frame parent, Colocation modele) {
		super(parent, "Remboursements");

		this.getContentPane().setLayout(new GridLayout(3,1));
		this.creerHaut(modele);
		this.creerBas(modele);
		this.setSize(parent.getSize());
		this.setLocationRelativeTo(this.getParent());
	}

	private void creerHaut(Colocation modele) {

		// === Liste des dettes ===

		JPanel panneauListe = new JPanel(new BorderLayout());
		panneauListe.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

		JLabel labelDettes = new JLabel("Dettes enregistrées",SwingConstants.CENTER);
		labelDettes.setFont(new Font(labelDettes.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panneauListe.add(labelDettes,BorderLayout.NORTH);

		lesRemboursements = new JTextArea();
		lesRemboursements.setEditable(false);
		allRemboursements = modele.findAllRemboursements();
		lesRemboursements.setText(remboursementsAsString(allRemboursements));

		JScrollPane spDettes = new JScrollPane( lesRemboursements );
		spDettes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spDettes.setPreferredSize(new Dimension(100, 100));
		lesRemboursements.setEditable(false);
		panneauListe.add(spDettes,BorderLayout.CENTER);

		this.getContentPane().add(panneauListe);

		// === Tableau des entrees sorties ===
		
		JPanel panneauTableau = new JPanel(new BorderLayout());
		panneauTableau.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JLabel labelEntreesSorties = new JLabel("Bilan individuel : argent avancé - argent dû",SwingConstants.CENTER);
		labelEntreesSorties.setFont(new Font(labelEntreesSorties.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panneauTableau.add(labelEntreesSorties, BorderLayout.NORTH);

		Object[][] dettes = modele.findEntreesSortiesAsTable();
		Object[] titre = {"Colocataire", "Avances", "Dettes"};
		lesEntreesSorties = new JTable(dettes,titre);
		lesEntreesSorties.getColumnModel().getColumn(0).setPreferredWidth(30);
		lesEntreesSorties.getColumnModel().getColumn(1).setPreferredWidth(30); 
		lesEntreesSorties.getColumnModel().getColumn(2).setPreferredWidth(30); 
		lesEntreesSorties.setEnabled(false);
		JScrollPane spEntreesSorties = new JScrollPane(lesEntreesSorties);
		spEntreesSorties.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spEntreesSorties.setPreferredSize(new Dimension(100, 100));
		panneauTableau.add(spEntreesSorties, BorderLayout.CENTER);
		
		
		this.getContentPane().add(panneauTableau);
	}

	private void creerBas(Colocation modele) {
		
		// === Les checkpoints archives ===
		JPanel panneauArchives = new JPanel(new BorderLayout());
		panneauArchives.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

		// - titre
		JLabel labelLesRemboursements = new JLabel("Remboursements à effectuer",SwingConstants.CENTER);
		labelLesRemboursements.setFont(new Font(labelLesRemboursements.getFont().getName(), Font.BOLD, LABEL_SIZE));
		panneauArchives.add(labelLesRemboursements, BorderLayout.NORTH);

		JPanel archives = new JPanel();
		archives.setLayout(new BoxLayout(archives, BoxLayout.Y_AXIS));
		Vector<String> procedure = modele.allRemboursementsOptimaux();
		for(int i=procedure.size()-1; i>=0; i-- ) {
			final JCheckBox check = new JCheckBox(procedure.get(i));
			archives.add(check);
			check.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					check.setEnabled(false);
				}
			});
		}
		JScrollPane spArchives = new JScrollPane(archives);
		spArchives.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spArchives.setPreferredSize(new Dimension(100, 100));
		panneauArchives.add(spArchives, BorderLayout.CENTER);
		
		JPanel panneauActions = new JPanel();
		panneauActions.setLayout(new BoxLayout(panneauActions, BoxLayout.Y_AXIS));
		
		// === Le bouton de l'algorithme d'optimisation ===
		
		RoundButton optimum = new RoundButton(new ImageIcon("images"+File.separator+"calculator.png"));
		optimum.setToolTipText("Obtenir une procédure optimisée de remboursement et remettre les compteurs à zéro");
		panneauActions.add(optimum);

		optimum.addActionListener(new ControlJButtonAlgoRemboursement(this,modele));
		
		// === Le bouton pour supprimer les archives ===
		
		RoundButton delete = new RoundButton(new ImageIcon("images"+File.separator+"bin.png"));
		delete.setToolTipText("Supprimer les archives");
		panneauActions.add(delete);
		
		panneauArchives.add(panneauActions, BorderLayout.EAST);
		this.getContentPane().add(panneauArchives);

	}

	private String remboursementsAsString(Vector<String> allRemboursements) {
		// TODO Auto-generated method stub
		String res = "";
		for(int i=allRemboursements.size()-1; i>=0; i-- ) {
			res += allRemboursements.get(i)+"\n"+"";
		}
		return res;
	}


}

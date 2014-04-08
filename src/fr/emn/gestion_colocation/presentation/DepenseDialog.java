package fr.emn.gestion_colocation.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.emn.gestion_colocation.abstraction.Colocataire;
import fr.emn.gestion_colocation.abstraction.Date;
import fr.emn.gestion_colocation.abstraction.Colocation;
import fr.emn.gestion_colocation.abstraction.ColocationJDBC;
import fr.emn.gestion_colocation.controle.ControlJButtonAnnuler;
import fr.emn.gestion_colocation.controle.ControlJButtonValiderDepense;

public class DepenseDialog extends JDialog{



	/*-----------------------------------------------------------------------------------------
	 *                                    VARIABLES D'INSTANCES
	 *----------------------------------------------------------------------------------------- */

	private static final long serialVersionUID = 1L;
	private JTextField montant;
	private JCheckBox alimentaire;
	private JCheckBox tous;
	private JCheckBox[] colocataires;
	private JCheckBox[] colocataires2;
	private JTextField intitule;
	private JTextField date;
	private Colocataire[] allColoc;
	private Colocataire payeur;
	private Vector<Colocataire> profiteur;

	/*-----------------------------------------------------------------------------------------
	 *                                    CONSTRUCTEUR
	 *----------------------------------------------------------------------------------------- */

	public DepenseDialog(Frame parent,Colocation modele, boolean creation){

		super(parent,true);
		this.setTitle("Dépenses");
		this.setLocationRelativeTo(this.getParent());
		this.getContentPane().setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(300, 300));
		this.creerGauche(modele,creation);
		this.creerCentre(modele,creation);
		this.creerDroite(modele,creation);
		this.creerBas(modele,creation);
		this.pack();
		super.setLocationRelativeTo(parent);
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    CREATION DE L'INTERFACE
	 *----------------------------------------------------------------------------------------- */

	private void creerGauche(Colocation modele,boolean creation){

		JPanel gauche = new JPanel(new BorderLayout());

		// - Titre du panel gauche
		JLabel quoi = new JLabel("Quoi ?");
		quoi.setFont(new Font(quoi.getFont().getName(), Font.BOLD, 14));
		gauche.add(quoi);
		gauche.add(new JLabel(" "));

		gauche.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
		gauche.setLayout(new BoxLayout(gauche, BoxLayout.Y_AXIS));

		// - Caractéristiques de la depense
		gauche.add(new JLabel("Intitulé"));
		intitule = new JTextField("");
		gauche.add(intitule);
		gauche.add(new JLabel("Montant Dépensé"));
		montant = new JTextField("");
		gauche.add(montant);
		gauche.add(new JLabel("Date"));
		java.util.Date maDateAvecFormat= new java.util.Date(); 
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy"); 
		date=new JTextField (dateStandard.format(maDateAvecFormat));
		gauche.add(date);
		alimentaire = new JCheckBox();
		alimentaire.setText("alimentaire");
		gauche.add(alimentaire);

		this.getContentPane().add(gauche, BorderLayout.WEST);

	}

	private void creerCentre(Colocation modele,boolean creation){

		JPanel centre = new JPanel(new BorderLayout());

		// - Titre du panel centre
		JLabel qui = new JLabel("Qui ?");
		qui.setFont(new Font(qui.getFont().getName(), Font.BOLD, 14));
		centre.add(qui, BorderLayout.NORTH);
		centre.add(new JLabel(" "),BorderLayout.CENTER);

		centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
		centre.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

		// - Checkbox choix du payeur
		allColoc = modele.findAllColocataires();

		colocataires = new JCheckBox[allColoc.length];
		payeur = new Colocataire("","","");

		for(int i = 0;i<allColoc.length;i++){
			if(allColoc[i]!=null){
				JCheckBox colocataire = new JCheckBox();
				colocataire.setText(colocAsString(allColoc[i]));
				colocataires[i]=colocataire;

				colocataires[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for(int i = 0; i<allColoc.length; i++){
							if(colocataires[i].isSelected()
									&& colocataires[0].isEnabled() && colocataires[1].isEnabled()){
								for(int j = 0; j<allColoc.length; j++){
									if(j!=i){
										colocataires[j].setEnabled(false);
									}
								}
								colocataires[i].setEnabled(true);
								payeur = allColoc[i];
							}

							int k=0;

							for(int j = 0; j<allColoc.length; j++){
								if(colocataires[j].isSelected()){
									k=k+1;
								}
							}

							if(k==0){
								for(int j = 0; j<allColoc.length; j++){
									colocataires[j].setEnabled(true);
								}
							}
						}					
					}
				});
				centre.add(colocataires[i],BorderLayout.SOUTH);
			}
			this.getContentPane().add(centre, BorderLayout.CENTER);
		}		
	}

	private void creerDroite(Colocation modele,boolean creation){

		JPanel droite = new JPanel(new BorderLayout());

		// - Titre du panel droit
		JLabel pourqui = new JLabel("Pour qui ?");
		pourqui.setFont(new Font(pourqui.getFont().getName(), Font.BOLD, 14));
		droite.add(pourqui, BorderLayout.NORTH);
		droite.add(new JLabel(" "),BorderLayout.CENTER);

		droite.setLayout(new BoxLayout(droite, BoxLayout.Y_AXIS));
		droite.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));		

		// - Checkbox du choix des profiteurs
		tous = new JCheckBox();
		tous.setText("tous");

		colocataires2 = new JCheckBox[allColoc.length];
		profiteur = new Vector<Colocataire>();

		tous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tous.isSelected()){
					for(int i = 0; i<allColoc.length; i++){
						colocataires2[i].setSelected(true);
						colocataires2[i].setEnabled(false);
						profiteur.add(allColoc[i]);
					}
				}
				else{
					for(int i = 0; i<allColoc.length; i++){
						colocataires2[i].setEnabled(true);
						colocataires2[i].setSelected(false);
						profiteur.remove(allColoc[i]);
					}
				}
			}
		});

		droite.add(tous, BorderLayout.NORTH);

		for(int j = 0;j<allColoc.length;j++){
			if(allColoc[j]!=null){
				JCheckBox colocataire = new JCheckBox();
				colocataire.setText(colocAsString(allColoc[j]));
				colocataires2[j]=colocataire;
				colocataire.setEnabled(!tous.isSelected());


				colocataires2[j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						int k=0;

						for(int i = 0; i<allColoc.length; i++){
							if(!colocataires2[i].isSelected()){
								k=k+1;
							}
						}

						if(k!=allColoc.length){
							tous.setEnabled(false);
						}

						if(k==allColoc.length){
							tous.setEnabled(true);
						}

						for(int i = 0; i<allColoc.length;i++){
							if(colocataires2[i].isSelected()
									&& !containsProfiteur(profiteur, allColoc[i])){
								profiteur.add(allColoc[i]);
							}
							if(!colocataires2[i].isSelected() 
									&& containsProfiteur(profiteur, allColoc[i])){
								profiteur.remove(allColoc[i]);
							}
						}
					}
				});

				droite.add(colocataires2[j]);
			}
		}
		this.getContentPane().add(droite, BorderLayout.EAST);		

	}

	public void creerBas(Colocation modele,boolean creation) {
		JPanel bas = new JPanel(new GridLayout(1,2));
		JButton save = new JButton("Valider");
		save.addActionListener(new ControlJButtonValiderDepense(this, modele, creation));
		bas.add(save);
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ControlJButtonAnnuler(this));
		bas.add(annuler);
		this.getContentPane().add(bas, BorderLayout.SOUTH);
	}

	/*-----------------------------------------------------------------------------------------
	 *                                    SERVICES
	 *----------------------------------------------------------------------------------------- */

	public String getIntitule() {
		return this.intitule.getText();
	}

	public float getMontant(){
		if(!this.montant.getText().equals("")) {
			return Float.parseFloat(montant.getText());
		}
		else return 0;
	}

	public boolean isAlimentaire(){
		return alimentaire.isSelected();
	}

	public boolean isCommun(){
		return tous.isSelected();
	}

	public Date getDate() {
		return Date.convert(this.date.getText());
	}

	public Colocataire getPayeur(){
		return this.payeur;
	}

	public Vector<Colocataire> getProfiteur(){
		return this.profiteur;
	}

	private static String colocAsString(Colocataire coloc) {

		if(coloc != null){
			String result = coloc.getPseudo();
			return result;
		}
		else{
			return"";
		}

	}

	private static boolean containsProfiteur(Vector<Colocataire> colocataire, Colocataire profiteur) {
		for (Colocataire c : colocataire)
			if (profiteur.equals(c))
				return true;
		return false;
	}

	public static void main(String[] args) {
		Frame parent = new Frame();
		Colocation modele = new ColocationJDBC();

		DepenseDialog test = new DepenseDialog(parent,modele,true);
		test.setVisible(true);
	}
}


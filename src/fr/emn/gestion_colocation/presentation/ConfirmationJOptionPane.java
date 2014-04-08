package fr.emn.gestion_colocation.presentation;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.emn.gestion_colocation.abstraction.Colocation;

public class ConfirmationJOptionPane extends JFrame {

	private static final long serialVersionUID = 1L;

	public ConfirmationJOptionPane(String fen, Colocation modele) {
		if(fen.equals("algoRemboursement")){
			int choix = JOptionPane.showConfirmDialog(this, "Voulez-vous réellement effacer les dettes de la base de donnée"+ "\n"
					+"et ajouter aux archives la procédure optimale de remboursement ?", "Demande de confirmation", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("images"+File.separator+"question.png"));
			switch (choix) {
			case JOptionPane.YES_OPTION : /* algo*/ ;break;
			case JOptionPane.NO_OPTION : /* retour*/ ;break;
			case JOptionPane.CLOSED_OPTION : /* close*/ ;break;
			}
		}

		if(fen.equals("dissolution")){
			int choix = JOptionPane.showConfirmDialog(this, "Voulez-vous réellement supprimer la colocation ? (toute donnée sera perdue)", "Demande de confirmation", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("images"+File.separator+"question.png"));
			switch (choix) {
			case JOptionPane.YES_OPTION :  modele.deleteColocation() ;break;
			case JOptionPane.NO_OPTION : ;break;
			case JOptionPane.CLOSED_OPTION : ;break;
			}
		}

	}
}

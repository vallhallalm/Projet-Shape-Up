package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilisé pour les entrées de nom.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnName {
	/**
	 * Cet attribut correspond au compteur d'appui sur le bouton.
	 */
	int compteur=0; 
	
	/**
	 * Cette méthode permet de régir le bouton. 
	 * @param jeu
	 * 			Cela permet d'intéragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'intéragir avec l'interface.
	 */
	public ControleurBtnName(Jeu jeu, Interface interface1) {
		interface1.getBtnName().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setName((String)interface1.getReponse().getText());
				compteur++; 
				if (compteur<jeu.getNbJoueurReel()) jeu.setName(); 
				else jeu.ordreDePassage(); 
			}
		});
	}
}

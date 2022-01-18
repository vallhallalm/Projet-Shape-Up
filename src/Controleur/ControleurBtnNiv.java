package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilisé pour les entrées de niveau des IAs.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnNiv {
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
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnNiv(Jeu jeu, Interface interface1) {
		interface1.getBtnNiv().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setNiv((String)interface1.getComboStr().getSelectedItem());
				compteur++; 
				if (compteur<jeu.getNbIa()) jeu.setNiv(); 
				else {
					jeu.addNameIa();
					jeu.setName();
				}
			}
		});
	}
}

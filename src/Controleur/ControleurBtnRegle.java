package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Modele.Jeu;

/**
 * Cette classe correspond au controleur utilisé pour les boutons "OK" permettant de continuer la partie au niveau des règles.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnRegle {
	
	/**
	 * Cette méthode permet de régir le bouton. 
	 * @param jeu
	 * 			Cela permet d'intéragir avec le jeu.
	 * @param btnRegle
	 * 			Cela permet d'intéragir avec le btnRegle.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnRegle(Jeu jeu, JButton btnRegle) {
		btnRegle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setNbJoueur(); 
			}
		});
	}
}

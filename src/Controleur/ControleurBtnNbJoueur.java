package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilis� pour les entr�es de nombre de joueur.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnNbJoueur {

	/**
	 * Cette m�thode permet de r�gir le bouton. 
	 * @param jeu
	 * 			Cela permet d'int�ragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'int�ragir avec l'interface.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnNbJoueur(Jeu jeu, Interface interface1) {
		interface1.getBtnNbJoueur().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setNbJoueur((int)interface1.getComboInt().getSelectedItem()); 
				jeu.setNbIa(); 
			}
		});
	}
}

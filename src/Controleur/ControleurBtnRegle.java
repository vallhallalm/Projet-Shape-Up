package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Modele.Jeu;

/**
 * Cette classe correspond au controleur utilis� pour les boutons "OK" permettant de continuer la partie au niveau des r�gles.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnRegle {
	
	/**
	 * Cette m�thode permet de r�gir le bouton. 
	 * @param jeu
	 * 			Cela permet d'int�ragir avec le jeu.
	 * @param btnRegle
	 * 			Cela permet d'int�ragir avec le btnRegle.
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

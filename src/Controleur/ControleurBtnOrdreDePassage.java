package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilis� pour les boutons "OK" permettant de continuer la partie au niveau de l'ordre de passage.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnOrdreDePassage {

	/**
	 * Cette m�thode permet de r�gir le bouton. 
	 * @param jeu
	 * 			Cela permet d'int�ragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'int�ragir avec l'interface.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnOrdreDePassage(Jeu jeu, Interface interface1) {
		interface1.getBtnOrdreDePassage().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setJoueurs(); 
				interface1.getTexte().setText(jeu.ordreDePassageToString());
				interface1.getTexte().setVisible(true);
				interface1.getBtnSetJoueurs().setVisible(true);
			}
		});
	}
}

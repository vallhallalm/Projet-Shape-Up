package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilis� pour les boutons permettant de choisir les joueurs.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnSetJoueurs {

	/**
	 * Cette m�thode permet de r�gir le bouton. 
	 * @param jeu
	 * 			Cela permet d'int�ragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'int�ragir avec l'interface.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnSetJoueurs(Jeu jeu, Interface interface1) {
		interface1.getBtnSetJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interface1.getFrame().getContentPane().removeAll();
				interface1.getFrame().revalidate();
				interface1.getFrame().repaint();
				interface1.FenetreJeu(); 
				jeu.lancerPartie();
			}
		});
	}

}

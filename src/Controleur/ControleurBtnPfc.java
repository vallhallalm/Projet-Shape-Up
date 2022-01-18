package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilisé pour les boutons permettant de choisir les symboles au shifumi.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnPfc {
	
	/**
	 * Cette méthode permet de régir le bouton. 
	 * @param jeu
	 * 			Cela permet d'intéragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'intéragir avec l'interface.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnPfc(Jeu jeu, Interface interface1) {
		interface1.getBtnPfc().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getPfc().setChoiceJ1(interface1.getComboStr().getSelectedIndex()+1);
				jeu.getPfc().setChoice("Human");
			}
		});
	}
}

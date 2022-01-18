package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilisé pour les entrées de nombre d'IA.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnNbIa {
	
	/**
	 * Cette méthode permet de régir le bouton. 
	 * @param jeu
	 * 			Cela permet d'intéragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'intéragir avec l'interface.
	 * @see Modele.Jeu
	 * @see Vue.Interface
	 */
	public ControleurBtnNbIa(Jeu jeu, Interface interface1) {
		interface1.getBtnNbIa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.setNbIa((int)interface1.getComboInt().getSelectedItem()); 
				jeu.setNiv(); 
			}
		});
	}
}

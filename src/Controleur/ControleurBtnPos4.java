package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Coordonnee;
import Modele.Forme;
import Modele.Joueur;
import Modele.TapisCarte;
import Vue.Interface;
/**
 * Cette classe correspond au controleur utilis� pour le bouton du tapis de carte situ� en position 4.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnPos4 {
	
	/**
	 * Cette m�thode permet de r�gir le bouton. 
	 * @param joueur
	 * 			Cela permet d'int�ragir avec le joueur.
	 * @param tapisCarte
	 * 			Cela permet d'int�ragir avec le Tapis de carte
	 * @param interface1
	 * 			Cela permet d'int�ragir avec l'interface.
	 * @see Modele.Joueur
	 * @see Modele.TapisCarte
	 * @see Vue.Interface
	 */
	public ControleurBtnPos4(Joueur joueur, TapisCarte tapisCarte,  Interface interface1) {
		interface1.getBtnPos0().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tapisCarte.getForme() == Forme.carre) joueur.poserCarteEnMain(new Coordonnee(0, 4), tapisCarte);
			}
		});
	}
}

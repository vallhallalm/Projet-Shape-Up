package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Modele.Jeu;
import Vue.Interface;

/**
 * Cette classe correspond au controleur utilisé pour les boutons pour le shifumi.
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class ControleurBtnPfc2 {
	
	/**
	 * Cette méthode permet d'appeller les fonctions du shifumi et de les utiliser pour définir des cas.
	 * @param jeu
	 * 			Cela permet d'intéragir avec le jeu.
	 * @param interface1
	 * 			Cela permet d'intéragir avec l'interface.
	 * @param cas
	 * 			Cela permet de savoir si nous sommes dans le cas d'un IA ou d'un humain.
	 * @param compteur
	 * 			Il s'agit du compteur du bouton.
	 * @see Vue.Interface
	 * @see Modele.Jeu
	 * @see Modele.shifumi.Shifumi
	 */
	public ControleurBtnPfc2(Jeu jeu, Interface interface1, String cas, int compteur) {
		interface1.getBtnPfc2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cas=="Ia") {
					Random random = new Random();
					jeu.getPfc().setChoiceJ1(random.nextInt(3-1)+1); 
				}
				
				jeu.getPfc().setChoiceJ2(interface1.getComboStr().getSelectedIndex()+1);
				jeu.getPfc().resultat(); 
				interface1.getBtnSetJoueurs().setVisible(true);
				interface1.getTexte().setVisible(true);
				interface1.getTexte().setText("\nLe choix de "+jeu.getName().get(0)+" est "+jeu.getPfc().playersSelection(jeu.getPfc().getChoiceJ1())+". Le choix de "+jeu.getName().get(1)+" est "+jeu.getPfc().playersSelection(jeu.getPfc().getChoiceJ2())+".\n");
				if (jeu.getPfc().getResultat()!=0) {
					if (jeu.getPfc().getResultat()==1) interface1.getTexte().setText( interface1.getTexte().getText() + jeu.getName().get(0)+" a gagné, c'est lui qui commencera.");
					else interface1.getTexte().setText( interface1.getTexte().getText() + jeu.getName().get(1)+" a gagné, c'est lui qui commencera.");
					jeu.setJoueurs();
				}
				else jeu.getPfc().setChoice(cas);
			}
		});
	}
}

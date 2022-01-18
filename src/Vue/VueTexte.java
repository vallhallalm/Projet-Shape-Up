package Vue;

import java.util.*;
import Modele.Jeu;
import Modele.Joueur;

/**
 * Cette classe permet de lancer dans un Thread à part une partie qui se déroulera en Vue Texte. Cela permet de jouer dans la console
 * @author louis, mathieu
 * @version 1.0
 */
public class VueTexte implements Observer, Runnable{
	/**
	 * Cet attribut permet d'instancier un nouveau jeu.
	 */
	private Jeu jeu ;

	/**
	 * Cette méthode intialise le jeu et lance le thread
	 * @param jeu
	 * 			On met en paramètre l'attribut jeu qu'on initialisera 
	 */
	public VueTexte(Jeu jeu) {
		this.jeu = jeu; 
		Collection<Joueur> joueurs = this.jeu.getJoueurs().values(); 
		Iterator<Joueur> it = joueurs.iterator(); 
		while (it.hasNext()) {
			it.next().addObserver(this); 
		}
		jeu.getPioche().addObserver(this); 
		jeu.getTapisCarte().addObserver(this); 

		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Dans cette méthode on lance simplement le constructeur de jeu en vue texte.
	 */
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		jeu.constructeurTexte();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

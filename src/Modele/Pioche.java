package Modele;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

/**
 * Cette classe repr�sente la pioche dans une partie physique elle contient donc au d�but de la partie les 15 cartes du jeu.
 * 
 * @author louis, mathieu
 * @version 1.0
 */
public class Pioche extends Observable{
	/**
	 * Ce param�tre repr�sente la pioche � proprement parler il s'agit donc d'une queue de carte qui sera m�lang�e dans le constructeur de Pioche.
	 * @see Pioche#Pioche()
	 */
	private Queue<Carte> list = new LinkedList<Carte>(); 
	/**
	 * Ici on va donc initialiser toutes les cartes du jeu afin de les inclure dans la pioche dans le constructeur de Pioche.
	 * @see Pioche#Pioche()
	 */
	//On instance toute les cartes du jeu : 
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c1 = new Carte(true, Couleur.bleu, Forme.triangle); 
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c2 = new Carte(true, Couleur.bleu, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c3 = new Carte(true, Couleur.bleu, Forme.carre); 
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c4 = new Carte(true, Couleur.rouge, Forme.triangle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c5 = new Carte(true, Couleur.rouge, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c6 = new Carte(true, Couleur.rouge, Forme.carre);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c7 = new Carte(true, Couleur.vert, Forme.triangle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c8 = new Carte(true, Couleur.vert, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c9 = new Carte(true, Couleur.vert, Forme.carre);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c10 = new Carte(false, Couleur.bleu, Forme.triangle); 
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c11 = new Carte(false, Couleur.bleu, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c12 = new Carte(false, Couleur.bleu, Forme.carre); 
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c13 = new Carte(false, Couleur.rouge, Forme.triangle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c14 = new Carte(false, Couleur.rouge, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c15 = new Carte(false, Couleur.rouge, Forme.carre);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c16 = new Carte(false, Couleur.vert, Forme.triangle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c17 = new Carte(false, Couleur.vert, Forme.cercle);
	
	/**
	 * Cet attribut correspond � une carte
	 */
	Carte c18 = new Carte(false, Couleur.vert, Forme.carre);
	/**
	 * Cette m�thode est le constructeur de Pioche. Elle permet de mettre chacune des cartes du jeu dans la pioche et de les m�langer.
	 * 
	 */
	public Pioche() {
		list.add(c1); 
		list.add(c2); 
		list.add(c3); 
		list.add(c4); 
		list.add(c5);
		list.add(c6); 
		list.add(c7); 
		list.add(c8); 
		list.add(c9); 
		list.add(c10); 
		list.add(c11); 
		list.add(c12); 
		list.add(c13); 
		list.add(c14); 
		list.add(c15); 
		list.add(c16);
		list.add(c17); 
		list.add(c18); 
		Collections.shuffle((List<?>) list); //Permet de m�langer la pioche
	}
	/**
	 * Cette m�thode permet d'afficher la pioche via la m�thode toString de la classe Carte.
	 * @see Carte#toString()
	 */
	public void affichagePioche() {
		String str; 
		Iterator<Carte> it = list.iterator();
		str = it.next().toString(); 
		System.out.println(str);

		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}	 
	}
	/**
	 * Cette m�thode permet de retirer une carte de la pioche. La carte est donc retir� de la queue et retourner pour �tre utilis� lors de la partie.	
	 * @return
	 * 			La m�thode retourne donc la carte retir�e de la pioche.
	 */
	public Carte retirerCarte() {
		return list.remove(); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut list.	
	 * @return
	 * 			La m�thode retourne donc le contenu de l'attribut list.
	 */
	public Queue<Carte> getList() {
		return this.list; 
	}
}

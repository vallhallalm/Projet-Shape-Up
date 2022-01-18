package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Random;

/**
 * Cette classe est l'impl�mentation de PoserCarteStrategy appliqu� � un joueur virtuel . Cela permet de faire poser une carte � un joueur virtuel de fa�on random. 
 * @author louis, mathieu
 * @version 1.0
 * @see PoserCarteStrategy
 */

public class IaRandomPoserCarte implements PoserCarteStrategy {

	/**
	 * Cette m�thode est la m�thode principale permettant � un joueur virtuel de poser sa carte de fa�on random.
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce param�tre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte � la coordonn�e souhait�e
	 * @param pioche
	 * 			Ce param�tre permet de piocher la carte qui sera plac� en fin de m�thode
	 */

	public void poserCarte(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		// TODO Auto-generated method stub
		System.out.println("Je suis une Ia random");
		Carte cartePiochee; 
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 
		Iterator<Coordonnee> it; 
		Coordonnee coord=null; 			//Stocke les coordonn�e o� l'ia posera sa carte
		Random rand = new Random();
		int indAleatoire; 
		int i=0;
		boolean premierTour=true; 

		//L'ia pioche la carte : 
		cartePiochee = pioche.retirerCarte(); 	

		//Il y a deux cas possible : l'ia est la premiere � poser une carte ou non. On d�termine donc s'il y a une carte pos�e sur le tapis
		premierTour = tapisCarte.CoordonneeCartePosee().isEmpty(); 

		if (premierTour == true) {	//l'ia ne peut poser que en [1, 2] si elle est la premiere � jour 
			coord = new Coordonnee(1, 2); 
			System.out.println("C'est le premier tour l'ia doit poser une carte a la position : "+ coord.toString());
			System.out.println("La carte pos�e : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}

		else { //dans les autres cas 

			//On d�termine quel position sera choisi al�atoirement : 
			indAleatoire = rand.nextInt(placeDispo.size())+1;	//On d�termine l'indice de mani�re al�atoire (compris entre 1 et la taille de placeDispo)
			it = placeDispo.iterator();  
			while (i!=indAleatoire) {		//A la n ieme it�ration, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				coord = it.next();
			}
			System.out.println("L'ia pose une carte a la position : "+ coord.toString());
			System.out.println("La carte pos�e : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}
	}

	/**
	 * Cette m�thode reprend les grandes lignes de la m�thode poserCarte. 
	 * La seule diff�rence vient du fait qu'au lieu de piocher une carte, on appelle la m�thode en ayant d�j� piocher la carte
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce param�tre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte � la coordonn�e souhait�e
	 * @param cartePiochee
	 * 			Ce param�tre est la carte qui sera plac� en fin de m�thode
	 */
	public void poserCarteSpe(Joueur j, TapisCarte tapisCarte, Carte cartePiochee) {
		// TODO Auto-generated method stub 
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 
		Iterator<Coordonnee> it; 
		Coordonnee coord=null; 			//Stocke les coordonn�e o� l'ia posera sa carte
		Random rand = new Random();
		int indAleatoire; 
		int i=0;
		boolean premierTour=true; 

		//Il y a deux cas possible : l'ia est la premiere � poser une carte ou non. On d�termine donc s'il y a une carte pos�e sur le tapis
		premierTour = tapisCarte.CoordonneeCartePosee().isEmpty(); 

		if (premierTour == true) {	//l'ia ne peut poser que en [1, 2] si elle est la premiere � jour 
			coord = new Coordonnee(1, 2); 
			System.out.println("C'est le premier tour l'ia doit poser une carte a la position : "+ coord.toString());
			System.out.println("La carte pos�e : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());

		}

		else { //dans les autres cas 

			//On d�termine quel position sera choisi al�atoirement : 
			indAleatoire = rand.nextInt(placeDispo.size())+1;	//On d�termine l'indice de mani�re al�atoire (compris entre 1 et la taille de placeDispo)
			it = placeDispo.iterator();  
			while (i!=indAleatoire) {		//A la n ieme it�ration, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				coord = it.next();
			}
			System.out.println("L'ia pose une carte a la position : "+ coord.toString());
			System.out.println("La carte pos�e : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}
	}

}

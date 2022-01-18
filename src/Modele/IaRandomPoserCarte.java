package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Random;

/**
 * Cette classe est l'implémentation de PoserCarteStrategy appliqué à un joueur virtuel . Cela permet de faire poser une carte à un joueur virtuel de façon random. 
 * @author louis, mathieu
 * @version 1.0
 * @see PoserCarteStrategy
 */

public class IaRandomPoserCarte implements PoserCarteStrategy {

	/**
	 * Cette méthode est la méthode principale permettant à un joueur virtuel de poser sa carte de façon random.
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce paramètre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte à la coordonnée souhaitée
	 * @param pioche
	 * 			Ce paramètre permet de piocher la carte qui sera placé en fin de méthode
	 */

	public void poserCarte(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		// TODO Auto-generated method stub
		System.out.println("Je suis une Ia random");
		Carte cartePiochee; 
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 
		Iterator<Coordonnee> it; 
		Coordonnee coord=null; 			//Stocke les coordonnée où l'ia posera sa carte
		Random rand = new Random();
		int indAleatoire; 
		int i=0;
		boolean premierTour=true; 

		//L'ia pioche la carte : 
		cartePiochee = pioche.retirerCarte(); 	

		//Il y a deux cas possible : l'ia est la premiere à poser une carte ou non. On détermine donc s'il y a une carte posée sur le tapis
		premierTour = tapisCarte.CoordonneeCartePosee().isEmpty(); 

		if (premierTour == true) {	//l'ia ne peut poser que en [1, 2] si elle est la premiere à jour 
			coord = new Coordonnee(1, 2); 
			System.out.println("C'est le premier tour l'ia doit poser une carte a la position : "+ coord.toString());
			System.out.println("La carte posée : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}

		else { //dans les autres cas 

			//On détermine quel position sera choisi aléatoirement : 
			indAleatoire = rand.nextInt(placeDispo.size())+1;	//On détermine l'indice de manière aléatoire (compris entre 1 et la taille de placeDispo)
			it = placeDispo.iterator();  
			while (i!=indAleatoire) {		//A la n ieme itération, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				coord = it.next();
			}
			System.out.println("L'ia pose une carte a la position : "+ coord.toString());
			System.out.println("La carte posée : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}
	}

	/**
	 * Cette méthode reprend les grandes lignes de la méthode poserCarte. 
	 * La seule différence vient du fait qu'au lieu de piocher une carte, on appelle la méthode en ayant déjà piocher la carte
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce paramètre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte à la coordonnée souhaitée
	 * @param cartePiochee
	 * 			Ce paramètre est la carte qui sera placé en fin de méthode
	 */
	public void poserCarteSpe(Joueur j, TapisCarte tapisCarte, Carte cartePiochee) {
		// TODO Auto-generated method stub 
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 
		Iterator<Coordonnee> it; 
		Coordonnee coord=null; 			//Stocke les coordonnée où l'ia posera sa carte
		Random rand = new Random();
		int indAleatoire; 
		int i=0;
		boolean premierTour=true; 

		//Il y a deux cas possible : l'ia est la premiere à poser une carte ou non. On détermine donc s'il y a une carte posée sur le tapis
		premierTour = tapisCarte.CoordonneeCartePosee().isEmpty(); 

		if (premierTour == true) {	//l'ia ne peut poser que en [1, 2] si elle est la premiere à jour 
			coord = new Coordonnee(1, 2); 
			System.out.println("C'est le premier tour l'ia doit poser une carte a la position : "+ coord.toString());
			System.out.println("La carte posée : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());

		}

		else { //dans les autres cas 

			//On détermine quel position sera choisi aléatoirement : 
			indAleatoire = rand.nextInt(placeDispo.size())+1;	//On détermine l'indice de manière aléatoire (compris entre 1 et la taille de placeDispo)
			it = placeDispo.iterator();  
			while (i!=indAleatoire) {		//A la n ieme itération, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				coord = it.next();
			}
			System.out.println("L'ia pose une carte a la position : "+ coord.toString());
			System.out.println("La carte posée : "+cartePiochee.toString()+"\n");
			tapisCarte.getTapis().put(coord, cartePiochee); 
			System.out.println(tapisCarte.DescriptionTapis());
		}
	}

}

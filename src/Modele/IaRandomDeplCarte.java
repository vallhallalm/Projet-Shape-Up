package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
/**
 * Cette classe est l'implémentation de l'interface DeplacerCarteStrategy dans le cadre d'un joueur virtuel
 * @author louis, mathieu 
 * @version 1.0
 * @see DeplacerCarteStrategy
 */
public class IaRandomDeplCarte implements DeplacerCarteStrategy {
	/**
	 * Cette méthode est la méthode principale permettant de déplacer une carte de façon random.
	 * Ici la carte sera placée à la première place libre en fonction des coordonnées dans notre LinkedHashMap (Tapis de Carte)
	 * @param j
	 * 			Ce paramètre correspond au joueur 
	 * @param tapisCarte
	 * 			Ce paramètre correspond à notre tapis de carte, il nous permet de savoir où poser notre carte en fonction des positions disponibles.
	 * @param pioche
	 * 			Ce paramètre correspond à la pioche.
	 * 
	 */
	public void deplCarte(Joueur j, TapisCarte tapisCarte,Pioche pioche) {
		LinkedHashSet<Coordonnee> coordCartePosee = tapisCarte.CoordonneeCartePosee(); //donne les coordonnees de chaque carte posée sur le tapis
		LinkedHashSet<Coordonnee> coordDispo; 		//stockera les coordonnees où l'on peut placer/poser une carte sur le tapis
		Iterator<Coordonnee> it; 
		Coordonnee coordCartePrise = null; 			//Stocke les coordonnée de la carte prise par l'ia avant de la déplacer
		Coordonnee newCoordCartePrise = null; 		//Stocke les coordonnée de la carte prise par l'ia après l'avoir déplacer
		Carte cartePrise; 							//Stocke la carte situé à la position posCartePrise (autrement dit la carte qui a pour coordonnee coordCartePrise)
		Random rand = new Random();
		int indAleatoire; 
		int i=0;

		//_____________________________Récupération de la carte____________________________________________________________________________________________		
		it = coordCartePosee.iterator(); 
		indAleatoire = rand.nextInt(coordCartePosee.size())+1;	//On détermine l'indice de manière aléatoire (compris entre 1 et la taille de coordCartePosee)

		//On récupère les coordonnée stockées a l'indice posChoixAleatoire dans la linkedhashset coordCartePosee
		it = coordCartePosee.iterator();  
		while (i!=indAleatoire) {		//A la n ieme itération, on sera a l'indice n-1 dans notre LinkedHashSet
			i++; 
			coordCartePrise = it.next();
		}

		cartePrise = tapisCarte.getTapis().remove(coordCartePrise);	//on récupère la carte situé au coordonnee coordCartePrise


		//_____________________________ Poser la carte prise a une nouvelle position____________________________________________________________________________________________		

		coordDispo = tapisCarte.CoordonneePossible();   // donne les coordonnees où l'on peut placer/poser une carte sur le tapis
		do {
			indAleatoire = rand.nextInt(coordDispo.size())+1;	
			it = coordDispo.iterator(); 
			i=0; 
			while (i!=indAleatoire) {		//A la n ieme itération, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				newCoordCartePrise = it.next();
			}		
		}while (newCoordCartePrise.equals(coordCartePrise)); 

		tapisCarte.getTapis().put(newCoordCartePrise, cartePrise); 
		System.out.println("L'ia a déplacé la carte qui était en "+coordCartePrise.toString()+" en "+newCoordCartePrise.toString()+"." );
		System.out.println("La carte déplacée "+cartePrise.toString()+"\n");
		System.out.println(tapisCarte.DescriptionTapis());
	}

}

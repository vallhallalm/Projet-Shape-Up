package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
/**
 * Cette classe est l'impl�mentation de l'interface DeplacerCarteStrategy dans le cadre d'un joueur virtuel
 * @author louis, mathieu 
 * @version 1.0
 * @see DeplacerCarteStrategy
 */
public class IaRandomDeplCarte implements DeplacerCarteStrategy {
	/**
	 * Cette m�thode est la m�thode principale permettant de d�placer une carte de fa�on random.
	 * Ici la carte sera plac�e � la premi�re place libre en fonction des coordonn�es dans notre LinkedHashMap (Tapis de Carte)
	 * @param j
	 * 			Ce param�tre correspond au joueur 
	 * @param tapisCarte
	 * 			Ce param�tre correspond � notre tapis de carte, il nous permet de savoir o� poser notre carte en fonction des positions disponibles.
	 * @param pioche
	 * 			Ce param�tre correspond � la pioche.
	 * 
	 */
	public void deplCarte(Joueur j, TapisCarte tapisCarte,Pioche pioche) {
		LinkedHashSet<Coordonnee> coordCartePosee = tapisCarte.CoordonneeCartePosee(); //donne les coordonnees de chaque carte pos�e sur le tapis
		LinkedHashSet<Coordonnee> coordDispo; 		//stockera les coordonnees o� l'on peut placer/poser une carte sur le tapis
		Iterator<Coordonnee> it; 
		Coordonnee coordCartePrise = null; 			//Stocke les coordonn�e de la carte prise par l'ia avant de la d�placer
		Coordonnee newCoordCartePrise = null; 		//Stocke les coordonn�e de la carte prise par l'ia apr�s l'avoir d�placer
		Carte cartePrise; 							//Stocke la carte situ� � la position posCartePrise (autrement dit la carte qui a pour coordonnee coordCartePrise)
		Random rand = new Random();
		int indAleatoire; 
		int i=0;

		//_____________________________R�cup�ration de la carte____________________________________________________________________________________________		
		it = coordCartePosee.iterator(); 
		indAleatoire = rand.nextInt(coordCartePosee.size())+1;	//On d�termine l'indice de mani�re al�atoire (compris entre 1 et la taille de coordCartePosee)

		//On r�cup�re les coordonn�e stock�es a l'indice posChoixAleatoire dans la linkedhashset coordCartePosee
		it = coordCartePosee.iterator();  
		while (i!=indAleatoire) {		//A la n ieme it�ration, on sera a l'indice n-1 dans notre LinkedHashSet
			i++; 
			coordCartePrise = it.next();
		}

		cartePrise = tapisCarte.getTapis().remove(coordCartePrise);	//on r�cup�re la carte situ� au coordonnee coordCartePrise


		//_____________________________ Poser la carte prise a une nouvelle position____________________________________________________________________________________________		

		coordDispo = tapisCarte.CoordonneePossible();   // donne les coordonnees o� l'on peut placer/poser une carte sur le tapis
		do {
			indAleatoire = rand.nextInt(coordDispo.size())+1;	
			it = coordDispo.iterator(); 
			i=0; 
			while (i!=indAleatoire) {		//A la n ieme it�ration, on sera a l'indice n-1 dans notre LinkedHashSet
				i++; 
				newCoordCartePrise = it.next();
			}		
		}while (newCoordCartePrise.equals(coordCartePrise)); 

		tapisCarte.getTapis().put(newCoordCartePrise, cartePrise); 
		System.out.println("L'ia a d�plac� la carte qui �tait en "+coordCartePrise.toString()+" en "+newCoordCartePrise.toString()+"." );
		System.out.println("La carte d�plac�e "+cartePrise.toString()+"\n");
		System.out.println(tapisCarte.DescriptionTapis());
	}

}

package Modele;

/**
 * Ceci est une interface qui s'implémente dans les différentes classes de pose de carte.
 * @author louis, mathieu
 * @version 1.0
 * @see HumanPoserCarte
 * @see IaRandomPoserCarte
 * @see IaNormalPoserCarte
 */
public interface PoserCarteStrategy {
	
	/**
	 * Cette méthode permet de poser une carte
	 * @param j
	 * 			Il s'agit du joueur qui posera la carte
	 * @param tapis
	 * 			Il s'agit du Tapis de Carte permettant de placer la carte
	 * @param pioche
	 * 			Il s'agit de la pioche permettant de piocher
	 */
    void poserCarte(Joueur j, TapisCarte tapis, Pioche pioche);

}

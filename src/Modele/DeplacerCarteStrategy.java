package Modele;

/**
 * Ceci est une interface qui s'implémente dans les différentes classes de déplacement de carte.
 * @author louis, mathieu
 * @version 1.0
 * @see HumanDeplCarte
 * @see IaRandomDeplCarte
 */
public interface DeplacerCarteStrategy {
	
	/**
	 * Cette méthode permet de déplacer une carte
	 * @param j
	 * 			Il s'agit du joueur qui déplacera la carte
	 * @param tapis
	 * 			Il s'agit du tapis qu'on utilisera pour déplacer la carte
	 * @param pioche
	 * 			Il s'agit de la pioche pour piocher une carte
	 */
    void deplCarte(Joueur j, TapisCarte tapis, Pioche pioche);

}

package Modele;

/**
 * Ceci est une interface qui s'impl�mente dans les diff�rentes classes de d�placement de carte.
 * @author louis, mathieu
 * @version 1.0
 * @see HumanDeplCarte
 * @see IaRandomDeplCarte
 */
public interface DeplacerCarteStrategy {
	
	/**
	 * Cette m�thode permet de d�placer une carte
	 * @param j
	 * 			Il s'agit du joueur qui d�placera la carte
	 * @param tapis
	 * 			Il s'agit du tapis qu'on utilisera pour d�placer la carte
	 * @param pioche
	 * 			Il s'agit de la pioche pour piocher une carte
	 */
    void deplCarte(Joueur j, TapisCarte tapis, Pioche pioche);

}

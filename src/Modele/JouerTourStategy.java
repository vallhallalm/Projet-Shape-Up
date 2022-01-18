package Modele;

/**
 * Ceci est une interface qui s'implémente dans les différentes classes de gestion de tour.
 * @author louis, mathieu
 * @version 1.0
 * @see HumanJouerTour
 * @see IaRandomJouerTour
 */
public interface JouerTourStategy {
    
	/**
	 * Cette méthode permet de jouer un tour
	 * @param j
	 * 			Il s'agit du joueur qui jouera le tour
	 * @param tapisCarte
	 * 			Il s'agit du tapis qu'on utilisera pour jouer le tour
	 * @param pioche
	 * 			Il s'agit de la pioche ou l'on piochera les cartes
	 */
	void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche);

}

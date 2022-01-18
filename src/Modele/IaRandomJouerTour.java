package Modele;
import java.util.*;

/**
 * Cette classe est l'implémentation de JouerTourStrategy appliqué à un joueur virtuel . Cela permet de régir un tour pour un joueur virtuel de façon random. 
 * @author louis, mathieu
 * @version 1.0
 * @see JouerTourStategy
 */

public class IaRandomJouerTour implements JouerTourStategy {

	/**
	 * Cette méthode est la méthode principale régissant un tour pour une IA.
	 * @param j
	 * 			Cela correspond au joueur.  
	 * @param tapisCarte
	 * 			Ce paramètre permet d'envoyer le tapis de carte dans les méthodes le nécessitant.
	 * @param pioche
	 * 			Ce paramètre permet d'envoyer la pioche vers les méthodes necessitant la pioche (IaRandomPoserCarte et IaNormalPoserCarte)
	 */

	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		int nbCartePosée=tapisCarte.CoordonneeCartePosee().size(); 

		if(nbCartePosée <1) {	//L'ordi est le premier à jouer il ne pourra que poser une carte en [1, 2]
			j.poserCarte(j, tapisCarte, pioche);
		}

		else if (nbCartePosée == 1 ) { 	//Cela signifie qu'il n'y a qu'une carte sur la tapis, l'ordi doit forcément poser une carte avant de pouvoir déplacer une carte
			j.poserCarte(j, tapisCarte, pioche);
			Random r = new Random();		//On détermine aléatoirement si l'ia déplacera une carte après
			int choix = r.nextInt(1500000);
			if (choix%2==0) j.deplacerCarte(j, tapisCarte, pioche); 		
		}

		else {
			Random r = new Random();
			int choix = r.nextInt(1500000);
			if (choix%2==0)
			{
				j.deplacerCarte(j, tapisCarte, pioche);
				j.poserCarte(j, tapisCarte, pioche);
			}
			else if (choix%2==1)
			{
				j.poserCarte(j, tapisCarte, pioche);
				int choix2 = r.nextInt(1500001);
				if(choix2%2==0)
				{
					j.deplacerCarte(j, tapisCarte, pioche);
				}
			}
		}
	}
}

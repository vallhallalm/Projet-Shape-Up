package Modele;
import java.util.*;

/**
 * Cette classe est l'impl�mentation de JouerTourStrategy appliqu� � un joueur virtuel . Cela permet de r�gir un tour pour un joueur virtuel de fa�on random. 
 * @author louis, mathieu
 * @version 1.0
 * @see JouerTourStategy
 */

public class IaRandomJouerTour implements JouerTourStategy {

	/**
	 * Cette m�thode est la m�thode principale r�gissant un tour pour une IA.
	 * @param j
	 * 			Cela correspond au joueur.  
	 * @param tapisCarte
	 * 			Ce param�tre permet d'envoyer le tapis de carte dans les m�thodes le n�cessitant.
	 * @param pioche
	 * 			Ce param�tre permet d'envoyer la pioche vers les m�thodes necessitant la pioche (IaRandomPoserCarte et IaNormalPoserCarte)
	 */

	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		int nbCartePos�e=tapisCarte.CoordonneeCartePosee().size(); 

		if(nbCartePos�e <1) {	//L'ordi est le premier � jouer il ne pourra que poser une carte en [1, 2]
			j.poserCarte(j, tapisCarte, pioche);
		}

		else if (nbCartePos�e == 1 ) { 	//Cela signifie qu'il n'y a qu'une carte sur la tapis, l'ordi doit forc�ment poser une carte avant de pouvoir d�placer une carte
			j.poserCarte(j, tapisCarte, pioche);
			Random r = new Random();		//On d�termine al�atoirement si l'ia d�placera une carte apr�s
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

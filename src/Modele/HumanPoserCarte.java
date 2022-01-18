package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Cette classe est l'implémentation de PoserCarteStrategy appliqué à un joueur humain.
 * @author louis, mathieu
 * @version 1.0
 * @see PoserCarteStrategy
 */

public class HumanPoserCarte implements PoserCarteStrategy {

	/**
	 * Cette méthode est la méthode principale permettant à un joueur de poser sa carte.
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce paramètre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte à la coordonnée souhaitée
	 * @param pioche
	 * 			Ce paramètre permet de piocher la carte qui sera placé en fin de méthode
	 */

	public void poserCarte(Joueur j, TapisCarte tapisCarte, Pioche pioche) 
	{
		Carte transition; 		
		int x; 
		int y;
		boolean correspondance=false;
		boolean premierTour;

		//On pioche une carte et on l'affiche
		transition = pioche.retirerCarte(); 
		System.out.println("La carte piochée a : " +transition.toString());	
		Scanner clavier = new Scanner(System.in);		

		//On détermine si c'est le premier tour
		if (tapisCarte.CoordonneeCartePosee().isEmpty())premierTour=true; 
		else premierTour = false; 

		if(premierTour==false) 
		{
			System.out.println("Les coordonnées disponibles sont les suivantes : "+tapisCarte.CoordPossibleString()); //On affiche les coordonées disponibles pour que le joueur poser sa carte dans les règles


			System.out.println("veuillez entrer les coordonnées de l'endroit où placer la carte (x puis y sans crochet ni virgule)");		//on demande à l'utilisateur de rentre les coordonnées de positionement de sa carte
			x=clavier.nextInt();		//on lit les entrées de l'utilisateur
			clavier.nextLine();
			y=clavier.nextInt();
			clavier.nextLine();


			//on vérifie que les coordonnées entrées correspondent à une des coordonnées proposées 
			LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible();
			Coordonnee c = null; 
			Iterator<Coordonnee> it = placeDispo.iterator();
			while (it.hasNext()) 
			{
				c = (Coordonnee) it.next(); 
				if (x==c.getPosX() && y==c.getPosY()) {correspondance=true;}
			}

			while (correspondance == false) //Si ce n'est pas le cas, on redemande à l'utilisateur de rentre des coordonées valides
			{
				System.out.println("votre saisie est incorrecte, veuillez resaisir des coordonées valides");
				x=clavier.nextInt();
				clavier.nextLine();
				y=clavier.nextInt();
				clavier.nextLine();
				it = placeDispo.iterator(); 
				while (it.hasNext())
				{
					c = (Coordonnee) it.next(); 
					if (x==c.getPosX() && y==c.getPosY()) {correspondance=true;}
				}
			}
			tapisCarte.getTapis().put(new Coordonnee(x, y), transition);
			System.out.println("Saisir entrer quand vous avez fini de lire.");
			clavier.nextLine(); 
		}

		else 
		{
			System.out.println("Ceci est le premier tour, votre carte sera placé aux coordonnées [1;2] , Vous ne pourrez pas déplacer de carte.");		//on demande à l'utilisateur de rentre les coordonnées de positionement de sa carte 
			tapisCarte.getTapis().put(new Coordonnee(1, 2), transition); 

		}
	}
}

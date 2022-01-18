package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Cette classe est l'impl�mentation de PoserCarteStrategy appliqu� � un joueur humain.
 * @author louis, mathieu
 * @version 1.0
 * @see PoserCarteStrategy
 */

public class HumanPoserCarte implements PoserCarteStrategy {

	/**
	 * Cette m�thode est la m�thode principale permettant � un joueur de poser sa carte.
	 * @param j
	 * 			Ce parametre permet de connaitre le joueur qui place la carte
	 * @param tapisCarte
	 * 			Ce param�tre permet de connaitre la dispostion actuelle du tapis de carte et de poser la carte � la coordonn�e souhait�e
	 * @param pioche
	 * 			Ce param�tre permet de piocher la carte qui sera plac� en fin de m�thode
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
		System.out.println("La carte pioch�e a : " +transition.toString());	
		Scanner clavier = new Scanner(System.in);		

		//On d�termine si c'est le premier tour
		if (tapisCarte.CoordonneeCartePosee().isEmpty())premierTour=true; 
		else premierTour = false; 

		if(premierTour==false) 
		{
			System.out.println("Les coordonn�es disponibles sont les suivantes : "+tapisCarte.CoordPossibleString()); //On affiche les coordon�es disponibles pour que le joueur poser sa carte dans les r�gles


			System.out.println("veuillez entrer les coordonn�es de l'endroit o� placer la carte (x puis y sans crochet ni virgule)");		//on demande � l'utilisateur de rentre les coordonn�es de positionement de sa carte
			x=clavier.nextInt();		//on lit les entr�es de l'utilisateur
			clavier.nextLine();
			y=clavier.nextInt();
			clavier.nextLine();


			//on v�rifie que les coordonn�es entr�es correspondent � une des coordonn�es propos�es 
			LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible();
			Coordonnee c = null; 
			Iterator<Coordonnee> it = placeDispo.iterator();
			while (it.hasNext()) 
			{
				c = (Coordonnee) it.next(); 
				if (x==c.getPosX() && y==c.getPosY()) {correspondance=true;}
			}

			while (correspondance == false) //Si ce n'est pas le cas, on redemande � l'utilisateur de rentre des coordon�es valides
			{
				System.out.println("votre saisie est incorrecte, veuillez resaisir des coordon�es valides");
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
			System.out.println("Ceci est le premier tour, votre carte sera plac� aux coordonn�es [1;2] , Vous ne pourrez pas d�placer de carte.");		//on demande � l'utilisateur de rentre les coordonn�es de positionement de sa carte 
			tapisCarte.getTapis().put(new Coordonnee(1, 2), transition); 

		}
	}
}

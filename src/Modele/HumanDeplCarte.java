package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Cette classe est l'implémentation de l'interface DeplacerCarteStrategy dans le cadre d'un joueur humain
 * @author louis, mathieu 
 * @version 1.0
 * @see DeplacerCarteStrategy
 */
public class HumanDeplCarte implements DeplacerCarteStrategy {

	/**
	 * Cette méthode est la méthode principale permettant à un joueur humain de déplacer une carte.
	 * Ici on affichera les différentes coordonnées disponibles et on laissera le joueur choisir sur laquelle de ses coordonnées il veut déplacer la carte
	 * @param j
	 * 			Ce paramètre correspond au joueur 
	 * @param tapisCarte
	 * 			Ce paramètre correspond à notre tapis de carte, il nous permet de savoir où poser notre carte en fonction des positions disponibles.
	 * @param pioche
	 * 			Ce paramètre correspond à la pioche.
	 * 
	 */	
	public void deplCarte(Joueur j ,TapisCarte tapisCarte, Pioche pioche) 
	{
		int xInit;		//on définit les variables
		int yInit;
		int xDest;
		int yDest;
		boolean correspondance = false;
		boolean premierTour;
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 		//on cherche puis affiche les coordonées disponibles pour que le joueur pose sa carte dans les règles
		Coordonnee c = null; 
		Iterator<Coordonnee> it = placeDispo.iterator(); 

		//On détermine si c'est le premier tour
		if (tapisCarte.CoordonneeCartePosee().isEmpty())premierTour=true; 
		else premierTour = false; 

		//Cas du premier Tour
		if (premierTour==true) System.out.println("nous sommes au premier tour, aucune carte ne peut être déplacée, vous allez devoir poser une carte");

		//Autre cas (pas le premier tour
		else
		{
			Scanner clavier = new Scanner(System.in);		//on ouvre le périphérique
			System.out.println("veuillez entrer les coordonnées de la carte à déplacer (x (de 0 à 2) puis y (de 0 à 4) sans crochet ni virgule)");		//on demande à l'utilisateur de rentrer la position de la carte à bouger
			xInit=clavier.nextInt();
			clavier.nextLine();
			yInit=clavier.nextInt();
			clavier.nextLine();
			while (xInit>2 || xInit<0) 		//on vérifit que les coordonnées sont valides
			{
				System.out.println("Saisie incorect : veuillez entrer une nouvelle longueur comprise entre 0 et 2 ");
				xInit=clavier.nextInt();
				clavier.nextLine();
			}
			while (yInit>4 || yInit<0)
			{
				System.out.println("Saisie incorect : veuillez entrer une nouvelle largeur comprise entre 0 et 4 ");
				yInit=clavier.nextInt();
				clavier.nextLine();
			}
			System.out.println("Les coordonnées disponibles sont les suivante");
			it = placeDispo.iterator();
			while (it.hasNext())
			{
				c = (Coordonnee) it.next(); 
				System.out.println(c.toString());
			}
			System.out.println("veuillez entre les coordonnées de l'endroit où déplacer la carte (x puis y sans crochet ni virgule)");		//on demande à l'utilisateur où il veut poser sa carte
			xDest=clavier.nextInt();
			clavier.nextLine();
			yDest=clavier.nextInt();
			clavier.nextLine();
			it = placeDispo.iterator();
			while (it.hasNext()) //on vérifie que les coordonnées entrées correspondent à une des coordonnées proposées 
			{
				c = (Coordonnee) it.next(); 
				if (xDest==c.getPosX() && yDest==c.getPosY()) {correspondance=true;}
			}
			while (correspondance == false) //Si ce n'est pas le cas, on redemande à l'utilisateur de rentre des coordonées valides
			{
				System.out.println("votre saisie est incorrecte, veuillez resaisir des coordonées valides");
				xDest=clavier.nextInt();
				clavier.nextLine();
				yDest=clavier.nextInt();
				clavier.nextLine();
				it = placeDispo.iterator();
				while (it.hasNext())
				{
					c = (Coordonnee) it.next(); 
					if (xDest==c.getPosX() && yDest==c.getPosY()) {correspondance=true;}
				}
			}	
			tapisCarte.getTapis().put(new Coordonnee(xDest, yDest), tapisCarte.getTapis().remove(new Coordonnee(xInit, yInit)));	//on déplace la carte
			//clavier.close();		//on ferme le peripherique
		}
	}
}

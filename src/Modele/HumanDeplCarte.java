package Modele;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Cette classe est l'impl�mentation de l'interface DeplacerCarteStrategy dans le cadre d'un joueur humain
 * @author louis, mathieu 
 * @version 1.0
 * @see DeplacerCarteStrategy
 */
public class HumanDeplCarte implements DeplacerCarteStrategy {

	/**
	 * Cette m�thode est la m�thode principale permettant � un joueur humain de d�placer une carte.
	 * Ici on affichera les diff�rentes coordonn�es disponibles et on laissera le joueur choisir sur laquelle de ses coordonn�es il veut d�placer la carte
	 * @param j
	 * 			Ce param�tre correspond au joueur 
	 * @param tapisCarte
	 * 			Ce param�tre correspond � notre tapis de carte, il nous permet de savoir o� poser notre carte en fonction des positions disponibles.
	 * @param pioche
	 * 			Ce param�tre correspond � la pioche.
	 * 
	 */	
	public void deplCarte(Joueur j ,TapisCarte tapisCarte, Pioche pioche) 
	{
		int xInit;		//on d�finit les variables
		int yInit;
		int xDest;
		int yDest;
		boolean correspondance = false;
		boolean premierTour;
		LinkedHashSet<Coordonnee> placeDispo = tapisCarte.CoordonneePossible(); 		//on cherche puis affiche les coordon�es disponibles pour que le joueur pose sa carte dans les r�gles
		Coordonnee c = null; 
		Iterator<Coordonnee> it = placeDispo.iterator(); 

		//On d�termine si c'est le premier tour
		if (tapisCarte.CoordonneeCartePosee().isEmpty())premierTour=true; 
		else premierTour = false; 

		//Cas du premier Tour
		if (premierTour==true) System.out.println("nous sommes au premier tour, aucune carte ne peut �tre d�plac�e, vous allez devoir poser une carte");

		//Autre cas (pas le premier tour
		else
		{
			Scanner clavier = new Scanner(System.in);		//on ouvre le p�riph�rique
			System.out.println("veuillez entrer les coordonn�es de la carte � d�placer (x (de 0 � 2) puis y (de 0 � 4) sans crochet ni virgule)");		//on demande � l'utilisateur de rentrer la position de la carte � bouger
			xInit=clavier.nextInt();
			clavier.nextLine();
			yInit=clavier.nextInt();
			clavier.nextLine();
			while (xInit>2 || xInit<0) 		//on v�rifit que les coordonn�es sont valides
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
			System.out.println("Les coordonn�es disponibles sont les suivante");
			it = placeDispo.iterator();
			while (it.hasNext())
			{
				c = (Coordonnee) it.next(); 
				System.out.println(c.toString());
			}
			System.out.println("veuillez entre les coordonn�es de l'endroit o� d�placer la carte (x puis y sans crochet ni virgule)");		//on demande � l'utilisateur o� il veut poser sa carte
			xDest=clavier.nextInt();
			clavier.nextLine();
			yDest=clavier.nextInt();
			clavier.nextLine();
			it = placeDispo.iterator();
			while (it.hasNext()) //on v�rifie que les coordonn�es entr�es correspondent � une des coordonn�es propos�es 
			{
				c = (Coordonnee) it.next(); 
				if (xDest==c.getPosX() && yDest==c.getPosY()) {correspondance=true;}
			}
			while (correspondance == false) //Si ce n'est pas le cas, on redemande � l'utilisateur de rentre des coordon�es valides
			{
				System.out.println("votre saisie est incorrecte, veuillez resaisir des coordon�es valides");
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
			tapisCarte.getTapis().put(new Coordonnee(xDest, yDest), tapisCarte.getTapis().remove(new Coordonnee(xInit, yInit)));	//on d�place la carte
			//clavier.close();		//on ferme le peripherique
		}
	}
}

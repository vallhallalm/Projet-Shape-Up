package Modele.shifumi;

import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import Modele.Jeu;

/**
 * Cette classe correspond � la simulation de shifumi permettant de d�terminer l'ordre de passage des joueurs.
 * @author louis, mathieu
 * @version 1.0
 */

public class Shifumi extends Observable {
	
	/**
	 * Cet attribut repr�sente le r�sultat sous la forme d'un entier
	 */
	private int resultat; 
	
	/**
	 * Cet attribut correspond au choix du joueur 1 sous forme d'entier
	 */
	private int choiceJ1; 
	
	/**
	 * Cet attribut correspond au choix du joueur 2 sous forme d'entier
	 */
	private int choiceJ2; 
	
	/**
	 * Cet attribut correspond au score du joueur 1 sous forme d'entier
	 */
	private int scoreJ1=0; 
	
	/**
	 * Cet attribut correspond au score du joueur 2 sous forme d'entier 
	 */
	private int scoreJ2=0; 

	/**
	 * Cette m�thode est le constructeur de la classe Shifumi. On n'initialise rien.
	 */
	public Shifumi() {

	}

	/**
	 * Cette m�thode est le constructeur de la classe Shifumi en prenant les deux joueurs. On n'initialise rien.
	 * @param j1
	 * 			Ce parametre correspond au joueur 1
	 * @param j2
	 * 			Ce parametre correspond au joueur 2
	 */
	public Shifumi(String j1, String j2){		


	}	

	/**
	 * Cette m�thode correspond au d�roulement du shifumi en Vue Texte
	 * @param j1
	 * 			Il s'agit du nom du joueur 1
	 * @param j2
	 * 			Il s'agit du nom du joueur 2
	 */
	public void gameTexte(String j1, String j2) 
	{
		Random random = new Random(); 

		System.out.println("\nVous allez jouer � pierre-feuille-ciseau pour d�terminer qui jouera en premier.\n");
		if (j1=="Human" && j2=="Human") {
			while(scoreJ1!=1 && scoreJ2!=1) {
				int choiceJ1, choiceJ2;
				System.out.println("Chacun votre tour vous allez choisir votre signe. j2 ne regarde pas quel signe choisi j1 !");
				choiceJ1 = playerChoice(); 
				System.out.println("Au tour de j2 de choisir son signe.");
				choiceJ2= playerChoice(); 
				switch(game(choiceJ1,choiceJ2)) {
				case 1 : scoreJ1++; break; 
				case 2 : scoreJ2++; break; 
				}
			}
		}

		else{
			while(scoreJ1!=1 && scoreJ2!=1) {
				switch(game(playerChoice(),random.nextInt(3-1)+1)) {
				case 1 : scoreJ1++; break; 
				case 2 : scoreJ2++; break; 
				}
			}
		}
	}


	/**
	 * Cette m�thode permet de r�cup�rer le choix de symbole du joueur humain en vue Texte. 
	 * @return
	 * 			Ici on retoure un entier qui correspond au choix du joueur.
	 */
	public static int playerChoice() {
		Scanner input = new Scanner(System.in); 
		int choice; 

		System.out.println("Saisir : ");
		do {
			System.out.println("1 pour Pierre");
			System.out.println("2 pour Papier");
			System.out.println("3 for Ciseau");
			choice = input.nextInt(); 
		}while (choice>3||choice<1); 
		return choice; 
	}

	/**
	 * Cette m�thode permet de connaitre le symbole choixi en fonction de l'entier qu'il a entr�. 
	 * @param selection
	 * 			Ce param�tre permet de savoir quel entier le joueur a choisi.
	 * @return
	 * 			On renvoit une chaine de caract�re correspondant au symbole.
	 */
	public static String playersSelection(int selection) {
		String object=""; 
		switch (selection) {
		case 1 : object="Pierre"; break; 
		case 2 : object="Papier"; break; 
		case 3 : object="Ciseau"; break; 
		default: 
		}
		return object; 	
	}

	/**
	 * Cette m�thode permet d'afficher les r�sultats en vue Texte.
	 * @param j1Choice
	 * 			Il s'agit du choix du joueur 1.
	 * @param j2Choice
	 * 			Il s'agit du choix du joueur 2.
	 * @return
	 * 			On renvoit le resultat entier.
	 */
	public static int game(int j1Choice, int j2Choice) {
		int result=0; 
		System.out.println("\nLe choix de J1 est "+playersSelection(j1Choice)+". Le choix de J2 (peut etre repr�sent� par une Ia) est "+playersSelection(j2Choice)+".");
		if(j1Choice==1 && j2Choice==3||j1Choice==2 && j2Choice==1||j1Choice==3 && j2Choice==2) {
			System.out.println("J1 a gagn� ! ");
			return result=1; 
		}
		else if(j1Choice==j2Choice) {
			System.out.println("Egalit� ! ");
			result=0; 
		}
		else {
			System.out.println("J2 a gagn� ! ");
			result=2;
		}
		return result; 
	}

	/**
	 * Cette m�thode permet de d�terminer le vainqueur dans le cas d'une partie en Interface Graphique.
	 */
	public void resultat() {
		resultat=0; 
		//	System.out.println("\nLe choix de J1 est "+playersSelection(choiceJ1)+". Le choix de J2 (peut etre repr�sent� par une Ia) est "+playersSelection(choiceJ2)+".");
		if(choiceJ1==1 && choiceJ2==3||choiceJ1==2 && choiceJ2==1||choiceJ1==3 && choiceJ2==2) {
			//System.out.println("J1 a gagn� ! ");
			resultat=1; 
		}
		else if(choiceJ1==choiceJ2) {
			//System.out.println("Egalit� ! ");
			resultat=0; 
		}
		else {
			//System.out.println("J2 a gagn� ! ");
			resultat=2;
		}
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut choiceJ1.
	 * @param choiceJ1
	 * 			Ce param�tre est la future valeure de l'attribut choiceJ1.
	 * @see Shifumi#choiceJ1
	 */
	public void setChoiceJ1(int choiceJ1) {
		this.choiceJ1 = choiceJ1;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut choiceJ2.
	 * @param choiceJ2
	 * 			Ce param�tre est la future valeure de l'attribut choiceJ2.
	 * @see Shifumi#choiceJ2
	 */
	public void setChoiceJ2(int choiceJ2) {
		this.choiceJ2 = choiceJ2;
	}

	/**
	 * Il s'agit de la m�thode permettant de notifier certains observateurs.
	 * @param cas
	 * 			Ce param�tre permet de savoir dans quel cas on se trouve
	 * @see Shifumi#choiceJ1
	 * @see Shifumi#choiceJ2
	 */
	public void setChoice(String cas) {
		// TODO Auto-generated method stub
		setChanged(); 
		if (cas=="Human") notifyObservers("pfc Human"); 
		if (cas=="Ia") notifyObservers("pfc Ia"); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut resultat.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut resultat. 
	 * @see Shifumi#resultat
	 */
	public int getResultat() {
		return resultat;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut choiceJ1.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut choiceJ1. 
	 * @see Shifumi#choiceJ1
	 */
	public int getChoiceJ1() {
		return choiceJ1;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut choiceJ2.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut choiceJ2. 
	 * @see Shifumi#choiceJ2
	 */
	public int getChoiceJ2() {
		return choiceJ2;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut scoreJ2.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut scoreJ2. 
	 * @see Shifumi#scoreJ2
	 */
	public int getScoreJ1() {
		return scoreJ1;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut scoreJ1.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut scoreJ1. 
	 * @see Shifumi#scoreJ1
	 */
	public int getScoreJ2() {
		return scoreJ2;
	}

}


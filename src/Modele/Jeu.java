package Modele;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

import Modele.shifumi.Shifumi;

/**
 * Cette classe et la classe principale qui va initaliser toute la partie. Elle permet notament de s�lectionner le nombre de joueur, le nombre D'IA etc ...
 * @author louis, mathieu
 * @version 1.0
 */

public class Jeu extends Observable{

	/**
	 * Cet attribut permet de stocker tout les joueurs de la partie
	 * @see Jeu#getJoueurs()
	 * @see Jeu#setJoueurs()
	 */
	private LinkedHashMap<String, Joueur> joueurs =  new LinkedHashMap<String, Joueur>();

	/**
	 * Cet attribut permet d'instancier la classe Decompte qui sera appell�e en fin de partie pour compter les points.
	 * @see Jeu#getDecompte()
	 */
	private Decompte decompte; 

	/**
	 * Cet attribut permet d'instancier le Tapis de carte qui permettra au joueur de poser leurs cartes
	 * @see Jeu#getTapisCarte()
	 */
	private TapisCarte tapisCarte = new TapisCarte(); 

	/**
	 * Cet attribut permet d'instancier la pioche qui permettra au joueur de piocher des cartes.
	 * @see Jeu#getPioche()
	 */
	private Pioche pioche = new Pioche();

	/**
	 * Cet attribut permet de stocker le nombre de joueur total de la partie.
	 * @see Jeu#setNbJoueur(int)
	 * @see Jeu#setNbJoueur()
	 * @see Jeu#getNbJoueur()
	 */
	private int nbJoueur; 

	/**
	 * Cet attribut permet de stocker le nombre de joueur virtuel impliqu� dans la partie.
	 * @see Jeu#setNbIa()
	 * @see Jeu#setNbIa(int) 
	 * @see Jeu#getNbIa()
	 */
	private int nbIa; 

	/**
	 * Cet attribut permet de stocker le nombre de joueur r�el impliqu� dans la partie.
	 * @see Jeu#getNbJoueurReel()
	 * @see Jeu#setNbJoueurReel()
	 */
	private int nbJoueurReel; 

	/**
	 * Cet attribut permet de comptabiliser les tours pour chaque joueur.
	 * @see Jeu#getTourJoueur()
	 * @see Jeu#setTourJoueur(int)
	 */
	private int tourJoueur=-1; 

	/**
	 * Cet attribut permet de stocker les niveaux des IAs
	 * @see Jeu#getNiv()
	 * @see Jeu#setNiv()
	 */
	private ArrayList<String> niv = new ArrayList<String>(); 

	/**
	 * Cet attribut permet de stocker les noms des joueurs
	 * @see Jeu#getName()
	 * @see Jeu#setName() 
	 */
	private ArrayList<String> name = new ArrayList<String>();

	/**
	 * Cet attribut instancie la classe Shifumi qui permettra au joueur r�el de jouer pour savoir qui jouera en premier
	 * @see Jeu#getPfc()
	 */
	private Shifumi pfc = new Shifumi(); 

	/**
	 * Cet attribut permet de savoir si le joueur veut continuer � jouer.
	 */
	boolean continuer;



	/**
	 * Cette m�thode est le constructeur de jeu, elle permet de recolter les infos concernant les joueurs et d'appeller les diff�rentes m�thodes afin de d�rouler une partie.
	 * 
	 */
	public Jeu() {

	}

	/**
	 * Cette m�thode est la m�thode permettant d'initialiser le d�but du jeu en Vue texte.
	 * @see Vue.VueTexte#VueTexte(Jeu)
	 */
	public void constructeurTexte ()
	{
		int i; 
		this.continuer = true; 
		System.out.println("Bienvenue sur Shape Up ! \n");

		//On d�finit le nombre de joueur : 
		System.out.println("D�finir le nombre de joueur (2 ou 3). Veuillez saisir un nombre :");
		Scanner sc = new Scanner(System.in);
		nbJoueur = sc.nextInt();
		sc.nextLine();
		setNbIa(); 

		//On d�finit combien de joueur virtuel il y a (et donc cb de vrai joueur)
		System.out.println("Combien y a t'il de joueur virtuel dans cette partie ? Veuillez saisir un nombre (compris entre 0 et " +nbJoueur+ ") :");
		nbIa = sc.nextInt(); 
		sc.nextLine(); 
		nbJoueurReel = nbJoueur - nbIa; 
		setNiv(); 

		//On d�finit "le niveau des ia" puis on stocke leur nom du type ia+numero : 
		i=0;  
		while (i< nbIa) {
			System.out.println("D�finir le niveau de difficult� de l'Ia n�"+(i+1) +". Veuillez saisir 'Normal' pour normal et 'Facile' pour facile : ");
			this.niv.add(sc.nextLine()); 
			this.name.add("Ia".concat(Integer.toString(i+1))); 
			i++; 
		}
		setName(); 
		//On d�termine les noms des joueurs humains qui seront la cl� dans notre liste
		while (i<nbJoueurReel+nbIa) {
			System.out.println("Saisir le nom du joueur n�"+(i+1-nbIa) +".\nRemarque : l'ordre de passage sera d�fini apr�s.");
			this.name.add(sc.nextLine()); 
			i++; 
		}

		this.ordreDePassageTexte();
	}

	/**
	 * Cette m�thode correspond � la determination de l'ordre de passage des diff�rents joueur en Vue texte.
	 */
	public void ordreDePassageTexte(){
		//On d�finit l'ordre de passage (pour l'instant on demande au joueur) 
		//S'il y a 2 joueurs, on jouera � un shifumi pour d�terminer qui jouera le premier, s'il y a 3 joueurs on d�terminera par apport � l'heure du system
		int i;
		Scanner sc = new Scanner(System.in);
		ordreDePassage(); 
		if (nbJoueur==2) {
			if (nbJoueurReel==2) {	//Cas o� il n'y a que des joueurs r�els qui joue
				pfc = new Shifumi("Human", "Human"); 
				if (pfc.getResultat()==1) {
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
					joueurs.put(name.get(0), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
				}

				else {
					joueurs.put(name.get(0), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
				}
			}

			else if (nbJoueurReel==1 && nbIa==1){
				if (pfc.getResultat()==2) {
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
					if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
				}
				else {
					if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
				}
			}

			//Cas o� les deux joueurs sont des ias, l'ordre de passage correspondra � l'ordre de cr�ation des ia.
			else {
				if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
				else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));

				if(niv.get(1).equals("Normal")) joueurs.put(name.get(1), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
				else joueurs.put(name.get(1), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
			}
		}



		else {	//Cas o� il y a 3 joueurs
			Random rand = new Random();

			//On d�termine qui sera le premier joueur
			int[] nombreAleatoire = new int[3]; 
			nombreAleatoire[0] = rand.nextInt(3);

			//On d�termine qui sera le deuxieme joueur
			do {
				nombreAleatoire[1] = rand.nextInt(3);
			}while(nombreAleatoire[1]==nombreAleatoire[0]); //Tant que les deux nombres al�atoires sont les m�mes, on modifie la valeur de nombreAleatoire[1]


			//On d�duit qui est le troisi�me joueur
			nombreAleatoire[2] = 3-nombreAleatoire[1]-nombreAleatoire[0]; 


			//On ins�re nos joueurs dans la liste de mani�re ordonn�e : 
			i=0; 
			while (i<3) {

				if (nombreAleatoire[i] < nbIa) { 	// Cela signifiera que le nom du joueur sera un nom d'ia, cela signifiera �galement qu'on doit r�cupp�rer l'info sur le niveau de l'ordi
					if (niv.get(nombreAleatoire[i]).equals("Normal")) joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));	
				}

				else {	//Cela signifiera que le premier joueur sera un joueur r�el, on doit juste r�cup�rer son nom 
					joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
				}
				i++;
			}
		}
		System.out.println("L'ordre de passage sera le suivant : ");
		Set<String> setNomJoueurs = joueurs.keySet();
		System.out.println(setNomJoueurs);
		System.out.println("Saisir entrer quand vous avez termin� de lire.");
		sc.nextLine(); 
		this.lancerPartieTexte();

	}

	/**
	 * Cette m�thode permet de lancer la partie en Vue Texte
	 */
	public void lancerPartieTexte() {
		Scanner sc = new Scanner(System.in);

		System.out.println("On commence � mettre une carte de c�t� (�a sera la hidden carte).");
		this.pioche.retirerCarte(); 
		System.out.println("Saisir entrer quand vous avez termin� de lire.");
		sc.nextLine(); 

		System.out.println("Chacun des joueurs va piocher une carte qui constituera sa victory card (carte victoire).");
		Set<Map.Entry<String, Joueur>> entries = this.joueurs.entrySet();
		Iterator<Map.Entry<String, Joueur>> iterator; //Via notre iterator on peut se d�placer dans notre variable joueur

		Map.Entry<String, Joueur> entry = null;  
		Carte c = null; 
		iterator = entries.iterator();
		while(iterator.hasNext())
		{		
			entry = iterator.next();
			System.out.println("C'est au tour de "+ entry.getKey() +" de piocher sa victory_card.");
			System.out.println("Saisir entrer quand vous avez termin� de lire.");
			sc.nextLine(); 
			c=this.pioche.retirerCarte(); 
			entry.getValue().setVictoryCard(c);
			if (entry.getKey().contains("Ia")==false) 
			{
				System.out.println("Votre victory card : "+c.toString());
				System.out.println("Saisir entrer quand vous avez termin� de lire.");
				sc.nextLine(); 

				for (int i=0; i<49; i++) 
				{
					System.out.println("\n");
				}
			}
		}
		boolean finLancerPartie=false; 
		while(finLancerPartie==false) {
			iterator = entries.iterator();
			entry=null; 

			while (iterator.hasNext() && finLancerPartie==false) {
				entry=iterator.next(); 
				System.out.println("C'est au tour de "+ entry.getKey() +" de jouer");
				entry.getValue().jouerTour(entry.getValue(), tapisCarte, pioche);
				finLancerPartie=pioche.getList().isEmpty();	
			}
		}

		this.finPartie();
	}	    

	/**
	 * Cette m�thode permet de terminer la partie en Vue Texte.
	 */
	public void finPartieTexte()
	{
		Set<Map.Entry<String, Joueur>> entries = this.joueurs.entrySet();
		Iterator<Map.Entry<String, Joueur>> iterator = entries.iterator(); 
		Map.Entry<String, Joueur> entry = null; 
		int scoreMax=0, compteur=1; 
		Scanner sc = new Scanner(System.in);


		System.out.println("\n\nIl n'y a plus de carte dans la pioche, proc�dons au d�compte.");
		while (iterator.hasNext()) {
			entry = iterator.next(); 
			decompte=new Decompte(entry.getValue());  
			tapisCarte.accept(decompte);
			System.out.println("Le score de "+entry.getKey()+" est de "+entry.getValue().getPoint()+" points.");

			if(scoreMax==entry.getValue().getPoint()) compteur++; 

			if (scoreMax<entry.getValue().getPoint()) {
				scoreMax = entry.getValue().getPoint(); //On enregistre la valeur du meilleur score 
				compteur = 1; 
			}

		}

		if (compteur!=1) {
			System.out.println("\nIl y a �galit� entre : ");
			iterator = entries.iterator(); 
			entry = null; 
			while (iterator.hasNext()) {
				entry = iterator.next(); 
				if (entry.getValue().getPoint() == scoreMax) System.out.println(entry.getKey());; 
			}
		}

		else {
			iterator = entries.iterator(); 
			entry = null; 
			do{
				entry = iterator.next(); 
				if (entry.getValue().getPoint() == scoreMax) System.out.println("\nLe grand gagnant est : " +entry.getKey());; 
			}while(iterator.hasNext() && entry.getValue().getPoint()!=scoreMax); 
		}

	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut joueurs  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut joueurs
	 * @see Jeu#joueurs
	 */
	public LinkedHashMap<String, Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut decompte 
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut decompte
	 * @see Jeu#decompte
	 */
	public Decompte getDecompte() {
		return decompte;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut tapisCarte  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut tapisCarte
	 * @see Jeu#tapisCarte
	 */
	public TapisCarte getTapisCarte() {
		return tapisCarte;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut pioche   
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut pioche
	 * @see Jeu#pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut nbJoueur   
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut nbJoueur
	 * @see Jeu#nbJoueur
	 */
	public int getNbJoueur() {
		return nbJoueur;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut nbJoueur.
	 * @see Jeu#nbJoueur
	 */
	public void setNbJoueur() {
		setChanged(); 
		notifyObservers("NbJoueur"); 
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut nbJoueur.
	 * @param nbJoueur
	 * 			Ce param�tre est la future valeure de l'attribut nbJoueur.
	 * @see Jeu#nbJoueur
	 */
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut nbIa  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut nbIa
	 * @see Jeu#nbIa
	 */
	public int getNbIa() {
		return nbIa;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut nbIa.
	 * @see Jeu#nbIa
	 */
	public void setNbIa() {
		setChanged(); 
		notifyObservers("NbIa"); 
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut nbIa.
	 * @param nbIa
	 * 			Ce param�tre est la future valeure de l'attribut nbIa.
	 * @see Jeu#nbIa
	 */
	public void setNbIa(int nbIa) {
		this.nbIa = nbIa;
		setNbJoueurReel(); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut nbJoueurReel   
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut nbJoueurReel
	 * @see Jeu#nbJoueurReel
	 */
	public int getNbJoueurReel() {
		return nbJoueurReel;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut nbJoueurReel.
	 * @see Jeu#nbJoueurReel
	 */
	private void setNbJoueurReel() {
		// TODO Auto-generated method stub
		nbJoueurReel = nbJoueur - nbIa; 
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut niv.
	 * @see Jeu#niv
	 */
	public void setNiv() {
		setChanged(); 
		if (nbIa!=0) notifyObservers("Niveau"); 	
		else notifyObservers("Name"); 
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut niv.
	 * @param niveau
	 * 			Ce param�tre est la future valeure de l'attribut niv.
	 * @see Jeu#niv
	 */
	public void setNiv(String niveau) {
		niv.add(niveau); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut niv   
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut niv
	 * @see Jeu#niv
	 */
	public ArrayList<String> getNiv() {
		return niv; 
	}

	/**
	 * Cette m�thode permet d'ajouter une IA � l'attribut name.
	 * @see Jeu#name
	 */
	public void addNameIa() {
		int i=0; 
		while (i< nbIa) {
			name.add("Ia".concat(Integer.toString(i+1))); 
			i++; 
		}
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut name    
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut name
	 * @see Jeu#name
	 */
	public ArrayList<String> getName() {
		return name;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut name.
	 * @see Jeu#name
	 */
	public void setName() {
		setChanged(); 
		if (nbJoueurReel!=0) notifyObservers("Name"); 	
		else notifyObservers("ordreDePassage"); //cas o� il n'y a que des ordi. 
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut name.
	 * @param name
	 * 			Ce param�tre est la future valeure de l'attribut name.
	 * @see Jeu#name
	 */
	public void setName(String name) {
		this.name.add(name); 
	}

	/***
	 * Cette m�thode permet de retourner la valeur de l'attribut tourJoueur    
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut tourJoueur
	 * @see Jeu#tourJoueur
	 */
	public Shifumi getPfc() {
		return pfc;
	}

	/**
	 * Cette m�thode va permettre de de d�terminer l'ordre des passages des diff�rents joueurs qu'ils soient des IAs ou encore des joueurs humains
	 */
	public void ordreDePassage(){
		if (nbJoueur==2) {
			if (nbJoueurReel==2) {	//Cas o� il n'y a que des joueurs r�els qui joue
				pfc.setChoice("Human"); 
			}
			else if ((nbJoueurReel==1 && nbIa==1)) {
				pfc.setChoice("Ia");
			}

			else setJoueurs(); 
		}
		else { 
			setChanged();
			notifyObservers("ordreDePassage"); 
		}
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut joueurs.
	 * @see Jeu#joueurs
	 */
	public void setJoueurs() {
		// TODO Auto-generated method stub
		if (nbJoueur==2) {
			if (nbJoueurReel==2) {	//Cas o� il n'y a que des joueurs r�els qui joue
				pfc = new Shifumi("Human", "Human"); 
				if (pfc.getResultat()==1) {
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
					joueurs.put(name.get(0), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
				}

				else {
					joueurs.put(name.get(0), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour()));
				}
			}

			else if (nbJoueurReel==1 && nbIa==1){
				if (pfc.getResultat()==2) {
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
					if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
				}
				else {
					if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
					joueurs.put(name.get(1), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
				}
			}

			//Cas o� les deux joueurs sont des ias, l'ordre de passage correspondra � l'ordre de cr�ation des ia.
			else {
				if(niv.get(0).equals("Normal")) joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
				else joueurs.put(name.get(0), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));

				if(niv.get(1).equals("Normal")) joueurs.put(name.get(1), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
				else joueurs.put(name.get(1), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));
			}
		}



		else {	//Cas o� il y a 3 joueurs
			Random rand = new Random();

			//On d�termine qui sera le premier joueur
			int[] nombreAleatoire = new int[3]; 
			nombreAleatoire[0] = rand.nextInt(3);

			//On d�termine qui sera le deuxieme joueur
			do {
				nombreAleatoire[1] = rand.nextInt(3);
			}while(nombreAleatoire[1]==nombreAleatoire[0]); //Tant que les deux nombres al�atoires sont les m�mes, on modifie la valeur de nombreAleatoire[1]


			//On d�duit qui est le troisi�me joueur
			nombreAleatoire[2] = 3-nombreAleatoire[1]-nombreAleatoire[0]; 


			//On ins�re nos joueurs dans la liste de mani�re ordonn�e : 
			int i=0; 
			while (i<3) {

				if (nombreAleatoire[i] < nbIa) { 	// Cela signifiera que le nom du joueur sera un nom d'ia, cela signifiera �galement qu'on doit r�cupp�rer l'info sur le niveau de l'ordi
					if (niv.get(nombreAleatoire[i])=="Normal") joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new IaRandomDeplCarte(), new IaNormalPoserCarte(), new IaRandomJouerTour()));
					else joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new IaRandomDeplCarte(), new IaRandomPoserCarte(), new IaRandomJouerTour()));	
				}

				else {	//Cela signifiera que le premier joueur sera un joueur r�el, on doit juste r�cup�rer son nom 
					joueurs.put(name.get(nombreAleatoire[i]), new Joueur(new HumanDeplCarte(), new HumanPoserCarte(), new HumanJouerTour())); 
				}
				i++;
			}
		}
	}

	/**
	 * Cette m�thode permet de retourner une chaine de caract�re qui permet d'afficher l'ordre de passage des joueurs
	 * @return
	 * 			On retourne donc une chaine de caract�re d�crivant l'ordre de passage.
	 */
	public String ordreDePassageToString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append("L'ordre de passage sera le suivant : "); 
		Set<String> setNomJoueurs = joueurs.keySet();
		Iterator<String> it = setNomJoueurs.iterator(); 
		while (it.hasNext()) {
			sb.append("\n"); 
			sb.append(it.next()); 		
		}
		return sb.toString(); 
	}

	/**
	 * Cette m�thode permet de lancer la partie. On commencera par mettre de c�t� la hidden carte et on piochera les victory card pour chaque joueur. 
	 * Apr�s cela les tours pourront se d�rouler jusqu'� ce qu'il n'y ait plus de cartes dans la pioche.
	 */
	public void lancerPartie() {
		System.out.println("Carte retir�e de la pioche !"); 
		this.pioche.retirerCarte(); 
		Set<Map.Entry<String, Joueur>> entries = this.joueurs.entrySet();
		Iterator<Map.Entry<String, Joueur>> iterator = entries.iterator(); //Via notre iterator on peut se d�placer dans notre variable joueur
		Map.Entry<String, Joueur> entry = null;  
		Carte c = null; 
		while(iterator.hasNext()){		
			entry = iterator.next();
			c=this.pioche.retirerCarte(); 
			entry.getValue().setVictoryCard(c);
		}

		setChanged(); 
		notifyObservers("partieLancee"); 
		JOptionPane.showMessageDialog(null, "On commence � mettre une carte de c�t� (�a sera la hidden carte).\nChacun des joueurs va piocher une carte qui constituera sa victory card (carte victoire).", "Information", JOptionPane.PLAIN_MESSAGE);
		this.partie(); 
	}

	/**
	 * Cette m�thode permet de compter le nombre de tour jouer.
	 */
	public void partie() {
		tourJoueur++; 
		setChanged(); 
		notifyObservers("Partie");
	}


	/**
	 * Cette m�thode est la m�thode qui viendra conclure la partie. Elle permettra d'effectuer le d�compte et de d�terminer le gagnant ou dans certains cas une �galit�. 
	 * On pourra �galement gr�ce � cette m�thode red�marrer une nouvelle partie. 
	 */

	public void finPartie() {
		Set<Map.Entry<String, Joueur>> entries = this.joueurs.entrySet();
		Iterator<Map.Entry<String, Joueur>> iterator = entries.iterator(); 
		Map.Entry<String, Joueur> entry = null; 
		int scoreMax=0, compteur=1; 
		StringBuffer resultat = new StringBuffer(); 


		resultat.append("Il n'y a plus de carte dans la pioche, proc�dons au d�compte.\n");
		while (iterator.hasNext()) {
			entry = iterator.next(); 
			resultat.append("\nLa victory card de "+entry.getKey()+" : "+entry.getValue().getVictoryCard().toString()+"\n");
		}

		iterator = entries.iterator(); 
		while (iterator.hasNext()) {
			entry = iterator.next(); 
			decompte=new Decompte(entry.getValue());  
			tapisCarte.accept(decompte);
			resultat.append("\nLe score de "+entry.getKey()+" est de "+entry.getValue().getPoint()+" points.");

			if(scoreMax==entry.getValue().getPoint()) compteur++; 

			if (scoreMax<entry.getValue().getPoint()) {
				scoreMax = entry.getValue().getPoint(); //On enregistre la valeur du meilleur score 
				compteur = 1; 
			}

		}

		if (compteur!=1) {
			resultat.append("\n\nIl y a �galit� entre : ");
			iterator = entries.iterator(); 
			entry = null; 
			while (iterator.hasNext()) {
				entry = iterator.next(); 
				if (entry.getValue().getPoint() == scoreMax) resultat.append("\n"+entry.getKey()); 
			}
		}

		else {
			iterator = entries.iterator(); 
			entry = null; 
			do{
				entry = iterator.next(); 
				if (entry.getValue().getPoint() == scoreMax) resultat.append("\nLe grand gagnant est : " +entry.getKey());; 
			}while(iterator.hasNext() && entry.getValue().getPoint()!=scoreMax); 
		}
		System.out.println(resultat.toString());
		setChanged(); 
		notifyObservers(resultat); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut tourJoueur    
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut tourJoueur
	 * @see Jeu#tourJoueur
	 */
	public int getTourJoueur() {
		return tourJoueur;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut tourJoueur.
	 * @param tourJoueur
	 * 			Ce param�tre est la future valeure de l'attribut tourJoueur.
	 * @see Jeu#tourJoueur
	 */
	public void setTourJoueur(int tourJoueur) {
		this.tourJoueur = tourJoueur;
	}
}

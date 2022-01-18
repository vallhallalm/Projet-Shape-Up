package Modele;

import java.util.Observable;

/**
 * Cette classe nous permet d'instancier des objets stockant toutes les informations relatives à un joueur.
 * @author louis, mathieu
 * @version 1.0
 */
public class Joueur extends Observable{

	/**
	 * Il s'agit de l'attribut qui stocke les points du joueur.
	 * @see Joueur#setPoint(int)
	 * @see Joueur#getPoint()
	 */
	private int point;

	/**
	 * Il s'agit des attributs permettant de définir la stratégie sur le déplacement de carte de chaque joueur 
	 * @see DeplacerCarteStrategy
	 */
	private DeplacerCarteStrategy deplacerCarteStrategy;
	/**
	 * Il s'agit des attributs permettant de définir la stratégie sur le depot de carte de chaque joueur
	 * @see PoserCarteStrategy
	 */
	private PoserCarteStrategy poserCarteStrategy;
	/**
	 * Il s'agit des attributs permettant de définir la stratégie sur les tours de  chaque joueur 
	 * @see JouerTourStategy
	 */
	private JouerTourStategy jouerTourStrategy; 

	/**
	 * Il s'agit de l'attribut stockant la carte que l'ordinateur a piochée et désignée comme étant la victory card du joueur.
	 * @see Joueur#setVictoryCard(Carte)
	 * @see Joueur#getVictoryCard()
	 */
	private Carte victory_card;
	/**
	 * Cet attribut permet de connaitre la carte en main du joueur
	 */
	private Carte carteEnMain; 
	/**
	 * Cet attribut permet de savoir si le joueur a posé une carte
	 */
	private boolean aPoseeCarte=false;
	/**
	 * Cet attribut permet de savoir si le joueur a déplacé une carte
	 */
	private boolean aDeplCarte=false; 
	/**
	 * Il s'agit du constructeur de la classe Joueur. Dans celui-ci, on définit la strategy propre au type de joueur. 
	 * Si le joueur est humain on lui attribuera une stratégie humaine et si le joueur est une IA on lui attribuera une stratégie d'IA.  
	 * Les trois paramètres permettent de définir les stratégies et de les mixer dans le cas dun joueur virtuel.
	 * @param deplCarteStrat
	 * 			Il s'agit des différentes façon de déplacer une carte
	 * @param poserCarteStrat
	 * 			Il s'agit des différentes façon de poser une carte
	 * @param jouerTourStrat
	 * 			Il s'agit des différentes façon de jouer un tour
	 */
	public Joueur(DeplacerCarteStrategy deplCarteStrat, PoserCarteStrategy poserCarteStrat, JouerTourStategy jouerTourStrat){
		this.deplacerCarteStrategy = deplCarteStrat; 
		this.poserCarteStrategy = poserCarteStrat; 
		this.jouerTourStrategy = jouerTourStrat; 
		point =0; 
	}

	/**
	 * Cette méthode permet d'appeller la méthode qui permet de déplacer une carte en fonction de la stratégie associée au joueur.
	 * Les paramètres que l'on passe ici sont utiles pour la méthode qu'appelle cette méthode.
	 * @param j
	 * 			On a donc le paramètre du joueur
	 * @param tapis
	 * 			Le paramètre du tapis pour savoir dans quelle mesure il est possible de déplacer une carte
	 * @param pioche
	 * 			Le paramètre de la pioche.
	 */
	public void deplacerCarte(Joueur j, TapisCarte tapis, Pioche pioche) {
		deplacerCarteStrategy.deplCarte(j, tapis, pioche);
		setChanged(); 
		notifyObservers("Tapis"); 
	}
	/**
	 * Cette méthode permet d'appeller la méthode qui permet de poser une carte en fonction de la stratégie associée au joueur.
	 * Les paramètres que l'on passe ici sont utiles pour la méthode qu'appelle cette méthode.
	 * @param j
	 * 			On a donc le paramètre du joueur
	 * @param tapis
	 * 			Le paramètre du tapis pour savoir dans quelle mesure il est possible de poser une carte
	 * @param pioche
	 * 			Le paramètre de la pioche afin de retirer la carte qui sera posée.
	 */
	public void poserCarte(Joueur j, TapisCarte tapis, Pioche pioche) {
		poserCarteStrategy.poserCarte(j, tapis, pioche);
		setChanged(); 
		notifyObservers("Tapis"); 
	}
	/**
	 * Cette méthode permet d'appeller la méthode qui permet de régir un tour en fonction de la stratégie associée au joueur.
	 * Les paramètres que l'on passe ici sont utiles pour les méthode qu'appelle la méthode apellé par cette méthode.
	 * @param j
	 * 			On a donc le paramètre du joueur.
	 * @param tapisCarte
	 * 			Le paramètre du tapis.
	 * @param pioche
	 * 			Le paramètre de la pioche.
	 */    
	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) { 
		jouerTourStrategy.jouerTour(this, tapisCarte, pioche); 
		setChanged(); 
		notifyObservers(); 
	}
	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de la victory card.
	 * @param vCarte
	 * 			Ce paramètre est la future valeure de la victory card.
	 * @see Joueur#victory_card
	 */
	public void setVictoryCard(Carte vCarte) {
		this.victory_card = vCarte; 
	}
	/**
	 * Cette méthode permet de retourner la valeur de la victory card    
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut victory card
	 * @see Joueur#victory_card
	 */
	public Carte getVictoryCard() {
		return this.victory_card; 
	}
	/**
	 * Cette méthode permet de retourner la valeur de l'attribut point
	 * @return
	 * 			Ici on retourne la valeur contenu dans l'attribut point
	 * @see Joueur#point
	 */
	public int getPoint() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.point;
	}
	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut point.
	 * @param value
	 * 			Ce paramètre est la future valeure de l'attribut point.
	 * @see Joueur#victory_card
	 */
	public void setPoint(int value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.point = value;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut deplacerCarteStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut deplacerCarteStrategy.
	 */
	public DeplacerCarteStrategy getDeplacerCarteStrategy() {
		return deplacerCarteStrategy;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut poserCarteStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut poserCarteStrategy.
	 */
	public PoserCarteStrategy getPoserCarteStrategy() {
		return poserCarteStrategy;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut jouerTourStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut jouerTourStrategy.
	 */
	public JouerTourStategy getJouerTourStrategy() {
		return jouerTourStrategy;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut carteEnMain.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut carteEnMain.
	 */
	public Carte getCarteEnMain() {
		return carteEnMain;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut carteEnMain.
	 * @param carteEnMain
	 * 			Ce paramètre est la future valeure de l'attribut carteEnMain.
	 */
	public void setCarteEnMain(Carte carteEnMain) {
		this.carteEnMain = carteEnMain;
		setChanged(); 
		notifyObservers("CarteEnMain"); 
	}

	/**
	 * Cette méthode permet de poser la carte qui est dans la main du joueur
	 * @param coord
	 * 			Ce paramètre permet de connaitre les coordonnées où poser la carte
	 * @param tapisCarte
	 * 			Ce paramètre permet de poser la carte sur notre Tapis de Carte.
	 * @see TapisCarte
	 * @see Coordonnee
	 */
	public void poserCarteEnMain(Coordonnee coord, TapisCarte tapisCarte) {
		tapisCarte.getTapis().put(coord, this.carteEnMain);
		this.carteEnMain = null; 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut aPoseeCarte.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut aPoseeCarte.
	 */
	public boolean getAPoseeCarte() {
		return aPoseeCarte;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut aPoseeCarte.
	 * @param aPoserCarte
	 * 			Ce paramètre est la future valeure de l'attribut aPoseeCarte.
	 */
	public void setAPoseeCarte(boolean aPoserCarte) {
		this.aPoseeCarte = aPoserCarte;
		setChanged(); 
		notifyObservers("A_Posee"); 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut aDeplCarte.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut aDeplCarte.
	 */
	public boolean getADeplCarte() {
		return aDeplCarte;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut aDeplCarte.
	 * @param aDeplCarte
	 * 			Ce paramètre est la future valeure de l'attribut aDeplCarte.
	 */
	public void setADeplCarte(boolean aDeplCarte) {
		this.aDeplCarte = aDeplCarte;
	}
}

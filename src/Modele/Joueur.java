package Modele;

import java.util.Observable;

/**
 * Cette classe nous permet d'instancier des objets stockant toutes les informations relatives � un joueur.
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
	 * Il s'agit des attributs permettant de d�finir la strat�gie sur le d�placement de carte de chaque joueur 
	 * @see DeplacerCarteStrategy
	 */
	private DeplacerCarteStrategy deplacerCarteStrategy;
	/**
	 * Il s'agit des attributs permettant de d�finir la strat�gie sur le depot de carte de chaque joueur
	 * @see PoserCarteStrategy
	 */
	private PoserCarteStrategy poserCarteStrategy;
	/**
	 * Il s'agit des attributs permettant de d�finir la strat�gie sur les tours de  chaque joueur 
	 * @see JouerTourStategy
	 */
	private JouerTourStategy jouerTourStrategy; 

	/**
	 * Il s'agit de l'attribut stockant la carte que l'ordinateur a pioch�e et d�sign�e comme �tant la victory card du joueur.
	 * @see Joueur#setVictoryCard(Carte)
	 * @see Joueur#getVictoryCard()
	 */
	private Carte victory_card;
	/**
	 * Cet attribut permet de connaitre la carte en main du joueur
	 */
	private Carte carteEnMain; 
	/**
	 * Cet attribut permet de savoir si le joueur a pos� une carte
	 */
	private boolean aPoseeCarte=false;
	/**
	 * Cet attribut permet de savoir si le joueur a d�plac� une carte
	 */
	private boolean aDeplCarte=false; 
	/**
	 * Il s'agit du constructeur de la classe Joueur. Dans celui-ci, on d�finit la strategy propre au type de joueur. 
	 * Si le joueur est humain on lui attribuera une strat�gie humaine et si le joueur est une IA on lui attribuera une strat�gie d'IA.  
	 * Les trois param�tres permettent de d�finir les strat�gies et de les mixer dans le cas dun joueur virtuel.
	 * @param deplCarteStrat
	 * 			Il s'agit des diff�rentes fa�on de d�placer une carte
	 * @param poserCarteStrat
	 * 			Il s'agit des diff�rentes fa�on de poser une carte
	 * @param jouerTourStrat
	 * 			Il s'agit des diff�rentes fa�on de jouer un tour
	 */
	public Joueur(DeplacerCarteStrategy deplCarteStrat, PoserCarteStrategy poserCarteStrat, JouerTourStategy jouerTourStrat){
		this.deplacerCarteStrategy = deplCarteStrat; 
		this.poserCarteStrategy = poserCarteStrat; 
		this.jouerTourStrategy = jouerTourStrat; 
		point =0; 
	}

	/**
	 * Cette m�thode permet d'appeller la m�thode qui permet de d�placer une carte en fonction de la strat�gie associ�e au joueur.
	 * Les param�tres que l'on passe ici sont utiles pour la m�thode qu'appelle cette m�thode.
	 * @param j
	 * 			On a donc le param�tre du joueur
	 * @param tapis
	 * 			Le param�tre du tapis pour savoir dans quelle mesure il est possible de d�placer une carte
	 * @param pioche
	 * 			Le param�tre de la pioche.
	 */
	public void deplacerCarte(Joueur j, TapisCarte tapis, Pioche pioche) {
		deplacerCarteStrategy.deplCarte(j, tapis, pioche);
		setChanged(); 
		notifyObservers("Tapis"); 
	}
	/**
	 * Cette m�thode permet d'appeller la m�thode qui permet de poser une carte en fonction de la strat�gie associ�e au joueur.
	 * Les param�tres que l'on passe ici sont utiles pour la m�thode qu'appelle cette m�thode.
	 * @param j
	 * 			On a donc le param�tre du joueur
	 * @param tapis
	 * 			Le param�tre du tapis pour savoir dans quelle mesure il est possible de poser une carte
	 * @param pioche
	 * 			Le param�tre de la pioche afin de retirer la carte qui sera pos�e.
	 */
	public void poserCarte(Joueur j, TapisCarte tapis, Pioche pioche) {
		poserCarteStrategy.poserCarte(j, tapis, pioche);
		setChanged(); 
		notifyObservers("Tapis"); 
	}
	/**
	 * Cette m�thode permet d'appeller la m�thode qui permet de r�gir un tour en fonction de la strat�gie associ�e au joueur.
	 * Les param�tres que l'on passe ici sont utiles pour les m�thode qu'appelle la m�thode apell� par cette m�thode.
	 * @param j
	 * 			On a donc le param�tre du joueur.
	 * @param tapisCarte
	 * 			Le param�tre du tapis.
	 * @param pioche
	 * 			Le param�tre de la pioche.
	 */    
	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) { 
		jouerTourStrategy.jouerTour(this, tapisCarte, pioche); 
		setChanged(); 
		notifyObservers(); 
	}
	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de la victory card.
	 * @param vCarte
	 * 			Ce param�tre est la future valeure de la victory card.
	 * @see Joueur#victory_card
	 */
	public void setVictoryCard(Carte vCarte) {
		this.victory_card = vCarte; 
	}
	/**
	 * Cette m�thode permet de retourner la valeur de la victory card    
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut victory card
	 * @see Joueur#victory_card
	 */
	public Carte getVictoryCard() {
		return this.victory_card; 
	}
	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut point
	 * @return
	 * 			Ici on retourne la valeur contenu dans l'attribut point
	 * @see Joueur#point
	 */
	public int getPoint() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.point;
	}
	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut point.
	 * @param value
	 * 			Ce param�tre est la future valeure de l'attribut point.
	 * @see Joueur#victory_card
	 */
	public void setPoint(int value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.point = value;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut deplacerCarteStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut deplacerCarteStrategy.
	 */
	public DeplacerCarteStrategy getDeplacerCarteStrategy() {
		return deplacerCarteStrategy;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut poserCarteStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut poserCarteStrategy.
	 */
	public PoserCarteStrategy getPoserCarteStrategy() {
		return poserCarteStrategy;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut jouerTourStrategy.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut jouerTourStrategy.
	 */
	public JouerTourStategy getJouerTourStrategy() {
		return jouerTourStrategy;
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut carteEnMain.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut carteEnMain.
	 */
	public Carte getCarteEnMain() {
		return carteEnMain;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut carteEnMain.
	 * @param carteEnMain
	 * 			Ce param�tre est la future valeure de l'attribut carteEnMain.
	 */
	public void setCarteEnMain(Carte carteEnMain) {
		this.carteEnMain = carteEnMain;
		setChanged(); 
		notifyObservers("CarteEnMain"); 
	}

	/**
	 * Cette m�thode permet de poser la carte qui est dans la main du joueur
	 * @param coord
	 * 			Ce param�tre permet de connaitre les coordonn�es o� poser la carte
	 * @param tapisCarte
	 * 			Ce param�tre permet de poser la carte sur notre Tapis de Carte.
	 * @see TapisCarte
	 * @see Coordonnee
	 */
	public void poserCarteEnMain(Coordonnee coord, TapisCarte tapisCarte) {
		tapisCarte.getTapis().put(coord, this.carteEnMain);
		this.carteEnMain = null; 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut aPoseeCarte.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut aPoseeCarte.
	 */
	public boolean getAPoseeCarte() {
		return aPoseeCarte;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut aPoseeCarte.
	 * @param aPoserCarte
	 * 			Ce param�tre est la future valeure de l'attribut aPoseeCarte.
	 */
	public void setAPoseeCarte(boolean aPoserCarte) {
		this.aPoseeCarte = aPoserCarte;
		setChanged(); 
		notifyObservers("A_Posee"); 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut aDeplCarte.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut aDeplCarte.
	 */
	public boolean getADeplCarte() {
		return aDeplCarte;
	}

	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut aDeplCarte.
	 * @param aDeplCarte
	 * 			Ce param�tre est la future valeure de l'attribut aDeplCarte.
	 */
	public void setADeplCarte(boolean aDeplCarte) {
		this.aDeplCarte = aDeplCarte;
	}
}

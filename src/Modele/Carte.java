package Modele;
/**
 * Cette classe représente une carte. Une carte dans le jeu Shape Up est caractérisée par trois attributs que sont le remplissage, la forme et la couleur.
 * @author louis, mathieu
 * @version 1.0
 */

public class Carte {
	/**
	 * Cet attribut est un booléen qui représente le remplissage de notre carte. L'attribut prend la valeur false si la forme et vide et true si la forme est pleine.
	 * @see Carte#getRemplissage()  
	 */
	private boolean remplissage;
	/**
	 * Cet attribut est du type d'une énumération de couleur permettant de savoir quelle est la couleur de la carte (rouge, vert ou bleu)
	 * @see Carte#getCouleur()    
	 */
	private Couleur couleur;
	/**
	 * Cet attribut est du type d'une énumération de forme permettant de savoir quelle est la forme de la carte (carré, rond ou triangle)
	 * @see Carte#getForme()
	 */
	private Forme forme;

	/**
	 * Cette méthode est le constructeur de la classe Carte. Il permet d'initaliser les trois attributs caractéristiques d'une carte.	
	 * @param remplissage
	 * 			Ce paramètre sera le futur remplissage de la carte en cour d'initialisation.
	 * @param couleur
	 * 			Ce paramètre sera la future couleur de la carte en cour d'initialisation.
	 * @param forme
	 * 			Ce paramètre sera la future forme de la carte en cour d'initialisation.
	 */
	public Carte(boolean remplissage, Couleur couleur, Forme forme) {
		this.remplissage = remplissage; 
		this.couleur = couleur;
		this.forme = forme; 
	}
	/**
	 * Cette méthode permet de renvoyer une chaine de caractère décrivant la carte.
	 * @return
	 * 			La méthode renvoit la chaine de caractère contenant les 3 informations caractérisant une carte.	
	 */
	public String toString(){
		String str; 
		if (this.remplissage ==true) str = "\nA pour symbole : " + this.forme.toString()+ ".\nDe couleur : "+this.couleur.toString()+".\nLa forme est remplie." ; 
		else str = "\nA pour symbole : "+ this.forme.toString()+".\nDe couleur : "+ this.couleur.toString()+".\nLa forme est vide." ; 
		return str;	
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut remplissage
	 * @return
	 * 			Ici on retourne donc la valeur du booléen remplissage.
	 */
	public boolean getRemplissage ()
	{
		return this.remplissage;
	}
	/**
	 * Cette méthode permet de retourner la valeur de l'attribut couleur
	 * @return
	 * 			Ici on retourne donc le nom de la couleur stocké dans l'attribut.
	 */	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
	/**
	 * Cette méthode permet de retourner la valeur de l'attribut forme
	 * @return
	 * 			Ici on retourne donc le nom de la forme stocké dans l'attribut.
	 */		
	public Forme getForme()
	{
		return this.forme;
	}

	/**
	 * Cette méthode permet de renvoyer un code propre à la carte afin de fournir un affichage en deux dimensions dans la console	
	 * @return
	 * 			La méthode retourne donc une chaine de caractère spécifique à la carte et permettant de l'identifier.
	 */
	public String getCode() {	//Donne l'initiale de la couleur puis du remplissage et enfin de la forme.
		StringBuffer sb = new StringBuffer();

		//Déterminer l'initiale du type de forme: 
		if (this.forme == Forme.carre) sb.append("C"); 
		else if (this.forme == Forme.cercle) sb.append("R"); //R pour rond et ne pas confondre avec carre
		else sb.append("T"); 

		//Déterminer l'initiale de la couleur : 
		if (this.couleur == Couleur.bleu) sb.append("b"); 
		else if (this.couleur == Couleur.rouge) sb.append("r"); 
		else sb.append("v"); 

		//Déterminer l'initiale du type de remplissage: 
		if (this.remplissage == true) sb.append("R");
		else sb.append("V"); 


		return sb.toString(); 		
	}

	/**
	 * Cette méthode permet d'accepter le visitor soit la classe décompte.
	 * @param v
	 * 			Ici le paramètre correspond au visitor que l'instance devra accepter. En l'occurence décompte.
	 * @see Decompte
	 */
	public void accept(Visitor v) {
		v.visit(this); 
	}
}

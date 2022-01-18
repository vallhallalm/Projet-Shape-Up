package Modele;
/**
 * Cette classe permet d'instancier des objets que nous utilisons en tant que que cl� de notre LinkedHashMap (Tapis de Carte). 
 * Ces coordonn�es sont exprim�es comme des coordonn�es cart�siennes (x,y)
 * @author louis, mathieu
 * @version 1.0
 */
public class Coordonnee{
	/**
	 * Il s'agit de l'attribut qui permet de stocker la valeur de l'abscisse
	 * @see Coordonnee#setPosX(int)
	 * @see Coordonnee#getPosX()
	 */
	private int posX; 
	/**
	 * Il s'agit de l'attribut qui permet de stocker la valeur de l'ordonn�e
	 * @see Coordonnee#setPosY(int)
	 * @see Coordonnee#getPosY()
	 */
	private int posY; 

	/**
	 * Il s'agit du constructeur de la classe. il permet d'initialiser l'abscisse et l'ordon�e
	 * @param posX
	 * 			Il s'agit du param�tre correspondant � la valeur de l'abscisse
	 * @param posY
	 * 			Il s'agit du param�tre correspondant � la valeur de l'ordonn�e
	 */
	public Coordonnee(int posX, int posY) {
		this.posX = posX; 
		this.posY = posY; 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut contenant la valeur de l'abscisse
	 * @return
	 * 			La m�thode retourne la valeur contenu dans l'attribut posX.
	 * @see Coordonnee#posX
	 */
	public int getPosX(){
		return this.posX; 
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut contenant la valeur de l'ordonn�e
	 * @return
	 * 			La m�thode retourne la valeur contenu dans l'attribut posY.
	 * @see Coordonnee#posY
	 */
	public int getPosY(){
		return this.posY; 
	}	
	/**
	 * Cette m�thode permet de modifier la valeur contenue dans l'attribut posX
	 * @param X
	 * 			Ce param�tre contient la future valeur de l'attribut posX
	 * @see Coordonnee#posX
	 */
	public void setPosX (int X)
	{
		this.posX=X;
	}
	/**
	 * Cette m�thode permet de modifier la valeur contenue dans l'attribut posY
	 * @param Y
	 * 			Ce param�tre contient la future valeur de l'attribut posY
	 * @see Coordonnee#posY
	 */	
	public void setPosY (int Y)
	{
		this.posY=Y;
	}

	/**
	 * Cette m�thode permet de comparer une autre instance de la classe Coordonnee � l'instance pr�sente.
	 * @param obj
	 * 			Ce parametre est celui qui sera compar� � ces coordonn�es.
	 * @return
	 * 			On retourne un bool�en qui nous indique le r�sultat de la comparaison.
	 */
	public boolean equals(Object obj) {
		boolean areEquals; 
		if (obj instanceof Coordonnee) {
			Coordonnee c = (Coordonnee)obj; 
			if (c.posX == this.posX && c.posY==this.posY) areEquals = true; 
			else areEquals = false; 
		}
		else areEquals=false; 
		return areEquals; 
	}


	public int hashCode(){
		return this.posX+this.posY;
	}


	/**
	 * Cette m�thod permet de cr�er une chaine de caractere pouvant �tre affich�e.
	 * @return
	 * 			Ici on retourne notre chaine de caract�re contenant notre abscisse et notre ordonn�e entre crochets.
	 */
	public String toString() {
		return "["+this.posX+", "+this.posY+"]"; 
	}
}
package Modele;
/**
 * Cette classe permet d'instancier des objets que nous utilisons en tant que que clé de notre LinkedHashMap (Tapis de Carte). 
 * Ces coordonnées sont exprimées comme des coordonnées cartésiennes (x,y)
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
	 * Il s'agit de l'attribut qui permet de stocker la valeur de l'ordonnée
	 * @see Coordonnee#setPosY(int)
	 * @see Coordonnee#getPosY()
	 */
	private int posY; 

	/**
	 * Il s'agit du constructeur de la classe. il permet d'initialiser l'abscisse et l'ordonée
	 * @param posX
	 * 			Il s'agit du paramètre correspondant à la valeur de l'abscisse
	 * @param posY
	 * 			Il s'agit du paramètre correspondant à la valeur de l'ordonnée
	 */
	public Coordonnee(int posX, int posY) {
		this.posX = posX; 
		this.posY = posY; 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut contenant la valeur de l'abscisse
	 * @return
	 * 			La méthode retourne la valeur contenu dans l'attribut posX.
	 * @see Coordonnee#posX
	 */
	public int getPosX(){
		return this.posX; 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut contenant la valeur de l'ordonnée
	 * @return
	 * 			La méthode retourne la valeur contenu dans l'attribut posY.
	 * @see Coordonnee#posY
	 */
	public int getPosY(){
		return this.posY; 
	}	
	/**
	 * Cette méthode permet de modifier la valeur contenue dans l'attribut posX
	 * @param X
	 * 			Ce paramètre contient la future valeur de l'attribut posX
	 * @see Coordonnee#posX
	 */
	public void setPosX (int X)
	{
		this.posX=X;
	}
	/**
	 * Cette méthode permet de modifier la valeur contenue dans l'attribut posY
	 * @param Y
	 * 			Ce paramètre contient la future valeur de l'attribut posY
	 * @see Coordonnee#posY
	 */	
	public void setPosY (int Y)
	{
		this.posY=Y;
	}

	/**
	 * Cette méthode permet de comparer une autre instance de la classe Coordonnee à l'instance présente.
	 * @param obj
	 * 			Ce parametre est celui qui sera comparé à ces coordonnées.
	 * @return
	 * 			On retourne un booléen qui nous indique le résultat de la comparaison.
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
	 * Cette méthod permet de créer une chaine de caractere pouvant être affichée.
	 * @return
	 * 			Ici on retourne notre chaine de caractère contenant notre abscisse et notre ordonnée entre crochets.
	 */
	public String toString() {
		return "["+this.posX+", "+this.posY+"]"; 
	}
}
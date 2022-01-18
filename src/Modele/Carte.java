package Modele;
/**
 * Cette classe repr�sente une carte. Une carte dans le jeu Shape Up est caract�ris�e par trois attributs que sont le remplissage, la forme et la couleur.
 * @author louis, mathieu
 * @version 1.0
 */

public class Carte {
	/**
	 * Cet attribut est un bool�en qui repr�sente le remplissage de notre carte. L'attribut prend la valeur false si la forme et vide et true si la forme est pleine.
	 * @see Carte#getRemplissage()  
	 */
	private boolean remplissage;
	/**
	 * Cet attribut est du type d'une �num�ration de couleur permettant de savoir quelle est la couleur de la carte (rouge, vert ou bleu)
	 * @see Carte#getCouleur()    
	 */
	private Couleur couleur;
	/**
	 * Cet attribut est du type d'une �num�ration de forme permettant de savoir quelle est la forme de la carte (carr�, rond ou triangle)
	 * @see Carte#getForme()
	 */
	private Forme forme;

	/**
	 * Cette m�thode est le constructeur de la classe Carte. Il permet d'initaliser les trois attributs caract�ristiques d'une carte.	
	 * @param remplissage
	 * 			Ce param�tre sera le futur remplissage de la carte en cour d'initialisation.
	 * @param couleur
	 * 			Ce param�tre sera la future couleur de la carte en cour d'initialisation.
	 * @param forme
	 * 			Ce param�tre sera la future forme de la carte en cour d'initialisation.
	 */
	public Carte(boolean remplissage, Couleur couleur, Forme forme) {
		this.remplissage = remplissage; 
		this.couleur = couleur;
		this.forme = forme; 
	}
	/**
	 * Cette m�thode permet de renvoyer une chaine de caract�re d�crivant la carte.
	 * @return
	 * 			La m�thode renvoit la chaine de caract�re contenant les 3 informations caract�risant une carte.	
	 */
	public String toString(){
		String str; 
		if (this.remplissage ==true) str = "\nA pour symbole : " + this.forme.toString()+ ".\nDe couleur : "+this.couleur.toString()+".\nLa forme est remplie." ; 
		else str = "\nA pour symbole : "+ this.forme.toString()+".\nDe couleur : "+ this.couleur.toString()+".\nLa forme est vide." ; 
		return str;	
	}

	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut remplissage
	 * @return
	 * 			Ici on retourne donc la valeur du bool�en remplissage.
	 */
	public boolean getRemplissage ()
	{
		return this.remplissage;
	}
	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut couleur
	 * @return
	 * 			Ici on retourne donc le nom de la couleur stock� dans l'attribut.
	 */	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
	/**
	 * Cette m�thode permet de retourner la valeur de l'attribut forme
	 * @return
	 * 			Ici on retourne donc le nom de la forme stock� dans l'attribut.
	 */		
	public Forme getForme()
	{
		return this.forme;
	}

	/**
	 * Cette m�thode permet de renvoyer un code propre � la carte afin de fournir un affichage en deux dimensions dans la console	
	 * @return
	 * 			La m�thode retourne donc une chaine de caract�re sp�cifique � la carte et permettant de l'identifier.
	 */
	public String getCode() {	//Donne l'initiale de la couleur puis du remplissage et enfin de la forme.
		StringBuffer sb = new StringBuffer();

		//D�terminer l'initiale du type de forme: 
		if (this.forme == Forme.carre) sb.append("C"); 
		else if (this.forme == Forme.cercle) sb.append("R"); //R pour rond et ne pas confondre avec carre
		else sb.append("T"); 

		//D�terminer l'initiale de la couleur : 
		if (this.couleur == Couleur.bleu) sb.append("b"); 
		else if (this.couleur == Couleur.rouge) sb.append("r"); 
		else sb.append("v"); 

		//D�terminer l'initiale du type de remplissage: 
		if (this.remplissage == true) sb.append("R");
		else sb.append("V"); 


		return sb.toString(); 		
	}

	/**
	 * Cette m�thode permet d'accepter le visitor soit la classe d�compte.
	 * @param v
	 * 			Ici le param�tre correspond au visitor que l'instance devra accepter. En l'occurence d�compte.
	 * @see Decompte
	 */
	public void accept(Visitor v) {
		v.visit(this); 
	}
}

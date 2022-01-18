package Modele;
import java.util.*;

/**
 * Cette classe correspond � la mat�rialisation d'un tapis de carte dans notre jeu.
 * @author louis, mathieu
 * @version 1.0
 */
public class TapisCarte extends Observable implements Visitable {
	/**
	 * Cet attribut permet de connaitre la longueur de notre Tapis de carte. Il aurait �t� utile si nous avions pu traiter les points bonus du sujet de projet.	
	 */
	private int longueur;
	/**
	 * Cet attribut permet de connaitre la largeur de notre Tapis de carte. Il aurait �t� utile si nous avions pu traiter les points bonus du sujet de projet.
	 */
	private int largeur;
	/**
	 * Cet attribut correspond � notre tapis de carte � proprement parler. Il s'agit d'une collection LinkedHashMap qui associe une Carte � une Coordonn�e.
	 * @see Coordonnee
	 * @see Carte
	 */
	private LinkedHashMap<Coordonnee, Carte> tapis = new LinkedHashMap<Coordonnee, Carte>(); 
	/**
	 * Cet attribut aurait du nous permettre de faire moduler la forme de notre tapis de carte si nous avions eu le temps de traiter le sujet plus amplement.	
	 */
	private Forme formeDuTapis;
	/**
	 * Cette m�thode est le constructeur de la classe TapisCarte. Elle permet de donner la forme du tapis (ceci est inutilisable car nous n'avons pas eu le temps de traiter les bonus du sujets).
	 * Ensuite, nous initialisons chaque case du tapis avec des coordonn�es et une carte null.
	 */
	TapisCarte() {
		formeDuTapis = Forme.carre; //Temporaire
		if (this.formeDuTapis == Forme.carre) {
			for (int i=0; i<15; i++) {
				for (int j=0; j<5; j++) {
					this.tapis.put(new Coordonnee(i, j), null); 
				}

			}
		}


	}
	/**
	 * Cette m�thode vient de l'impl�mentation de l'interface visitable. Cela permet d'accepter le visiteur. En l'occurence ici, le visiteur serait Decompte.
	 * @param v
	 * 			Cela permet de connaitre le visiteur qui sera accepter par la classe.
	 * @see Decompte
	 */
	public void accept(Visitor v) {
		v.visit(this); 
	}
	/**
	 * Cette m�thode permet de retourner la valeur stock�e dans l'attribut longueur. 
	 * @return
	 * 			On retourne donc la valeur de l'attribut longueur.
	 */
	public int getLongueur() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.longueur;
	}
	/**
	 * Cette m�thode permet de modifier la valeur de l'attribut longueur. 
	 * @param value
	 * 			Ce param�tre contient la future valeure qui sera contenue dans l'attribut longueur.
	 */
	public void setLongueur(int value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.longueur = value;
	}

	/**
	 * Cette m�thode permet de retourner la valeur stock�e dans l'attribut largeur. 
	 * @return
	 * 			On retourne donc la valeur de l'attribut largeur.
	 */
	public int getLargeur() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.largeur;
	}

	/**
	 * Cette m�thode permet de modifier la valeur de l'attribut largeur. 
	 * @param value
	 * 			Ce param�tre contient la future valeure qui sera contenue dans l'attribut largeur.
	 */
	public void setLargeur(int value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.largeur = value;
	}
	/**
	 * Cette m�thode permet de retourner la valeur stock�e dans l'attribut formeDuTapis. 
	 * @return
	 * 			On retourne donc la valeur de l'attribut formeDuTapis.
	 */
	public Forme getForme ()
	{
		return this.formeDuTapis;
	}
	/**
	 * Cette m�thode permet de modifier la valeur de l'attribut formeDuTapis. 
	 * @param forme
	 * 			Ce param�tre contient la future valeure qui sera contenue dans l'attribut formeDuTapis.
	 */
	public void setForme(Forme forme) {
		this.formeDuTapis = forme; 
	}
	/**
	 * Cette m�thode permet de retourner la linkedHashMap qui repr�sente le tapis. 
	 * @return
	 * 			On retourne donc l'entieret� de notre tapis de carte.
	 */
	public LinkedHashMap<Coordonnee, Carte> getTapis(){
		return this.tapis; 
	}

	/**
	 * Cette m�thode permet de d�terminer et de stocker dans une collection, les coordonn�es disponibles ou il est possible de poser une carte selon les r�gles.
	 * @return
	 * 			On retourne donc une linkedHashSet contenant les coordonn�es disponibles pour le joueur.
	 */
	public  LinkedHashSet<Coordonnee> CoordonneePossible(){
		int i, j; 
		LinkedHashSet<Coordonnee> coordDispo = new LinkedHashSet<Coordonnee>();
		if (formeDuTapis == Forme.carre) {			
			for(i=0;i<3;i++){
				for(j=0;j<5;j++){
					Coordonnee coord = new Coordonnee(i, j); 
					if (tapis.get(coord) != null) {	//Permet de détecter une carte sur le tapis
						if (j!=0 && tapis.get(new Coordonnee(i, j-1)) == null) coordDispo.add(new Coordonnee(i, j-1)); //On vérifie que la position à gauche de la carte qu'on regarde est libre, si oui, on stocke cette coordonnée
						if (j!=4 && tapis.get(new Coordonnee(i, j+1)) == null) coordDispo.add(new Coordonnee(i, j+1));  //On vérifie que la position à droite de la carte qu'on regarde est libre, si oui, on stocke cette coordonnée
						if (i!=0 && tapis.get(new Coordonnee(i-1, j)) == null) coordDispo.add(new Coordonnee(i-1, j)); //Si on n'est pas sur la première ligne, on vérifie que la position au dessus de la carte qu'on regarde est libre, si oui, on stocke cette coordonnée		
						if (i!=2 && tapis.get(new Coordonnee(i+1, j)) == null) coordDispo.add(new Coordonnee(i+1, j)); //Si on n'est pas sur la troisième ligne, on vérifie que la position en dessous de la carte qu'on regarde est libre, si oui, on stocke cette coordonnée
					}
				}
			}
		}
		return coordDispo;	
	}

	/**
	 * Cette m�thode permet de d�terminer les coordonn�es possibles comme la m�thode CoordonneesPossibles mais en retournant une chaine de caractere affichable.
	 * @return
	 * 			On retourne donc les coordonn�es disponibles sous forme de chaine de caract�re.
	 * @see TapisCarte#CoordonneePossible()
	 */
	public String CoordPossibleString() {
		LinkedHashSet<Coordonnee> placeDispo = this.CoordonneePossible(); 
		Coordonnee c = null; 
		Iterator<Coordonnee> it = placeDispo.iterator(); 
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			c = (Coordonnee) it.next(); 
			sb.append(c.toString()+"\t"); 
		}	
		return sb.toString();
	}
	/**
	 * Cette m�thode permet de renvoyer une linkedHashSet des coordonn�es des cartes d�j� pos�es sur le tapis de carte.
	 * @return
	 * 			On renvoit donc la linkedHashSet contenant nos coordonn�es.
	 */
	//Méthode qui renvoie une linkedHashSet de coordonnee. Ces coordonnees correspondent a la position des cartes posées sur le tapis.
	public LinkedHashSet<Coordonnee> CoordonneeCartePosee(){
		int i, j; 
		LinkedHashSet<Coordonnee> coord = new LinkedHashSet<Coordonnee>();
		if (formeDuTapis == Forme.carre) {		
			for(i=0;i<3;i++){
				for(j=0;j<5;j++){
					Coordonnee coordRech = new Coordonnee(i, j); 
					if (tapis.get(coordRech) != null) {
						coord.add(coordRech); 
					}
				}
			}
		}
		return coord; 
	}

	/**
	 * Cette m�thode permet de d�terminer les coordonn�es d�j� occup�es comme la m�thode CoordonneesCartePosee mais en retournant une chaine de caractere affichable.
	 * @return
	 * 			On retourne donc les coordonn�es d�j� occup�s par des cartes sous forme de caract�re.
	 * @see TapisCarte#CoordonneeCartePosee()
	 */
	public String CoordonneeCartePoseeString() {
		LinkedHashSet<Coordonnee> posCartePosee = this.CoordonneeCartePosee(); 
		Coordonnee coord = null; 
		Iterator<Coordonnee> it = posCartePosee.iterator(); 
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			coord = (Coordonnee) it.next(); 
			sb.append(coord.toString()+"\t");
		}	
		return sb.toString(); 
	}

	//Méthode qui permet de récupérer une carte située au coordonnées coord dans l'attribut tapis puis d'affecter la value null. 
	//Elle renvoie la carte précédemment récupérée
	/**
	 * Cette m�thde permet de vider une case du tapis de carte et de r�cup�rer la carte pos�e � la coordonn�e indiqu�e.
	 * Cette m�thode est notamment utilis�e dans le cadre des classes permettant de d�placer des cartes.
	 * @param coord
	 * 			Ce param�tre indique la coordonn�e � laquelle on va retirer la carte.
	 * @return
	 * 			On retourne donc la carte retir�e. 
	 */
	public Carte PrendreCartePosee(Coordonnee coord) {	
		return this.tapis.replace(coord, null); 
	}


	/**
	 * Cette m�thode permet de cr�er une chaine de caract�re qui d�crit le tapis. 
	 * Cette m�thode est utilis�e assez souvent pour tenir le joueur inform� de l'�t�t du tapis.
	 * @return
	 * 			On retourne donc une chaine de caract�re descriptive de notre tapis.
	 */
	public String DescriptionTapis() {
		int i, j; 
		Coordonnee coord; 
		StringBuffer sb = new StringBuffer();
		LinkedHashSet<Coordonnee> placeDispo = this.CoordonneePossible(); 

		if (formeDuTapis == Forme.carre) {		
			sb.append(" \t 0 \t 1 \t 2 \t 3 \t 4\n"); 
			for(i=0;i<3;i++){
				sb.append(i+"\t"); 
				for(j=0;j<5;j++){
					coord = new Coordonnee(i, j);
					if (tapis.get(coord) != null) sb.append(tapis.get(coord).getCode());
					else if (placeDispo.contains(coord)) sb.append(" X ");
					else sb.append(" \u00f8 "); 
					sb.append("\t");
				}
				sb.append("\n");
			}
		}

		return sb.toString(); 
	}


}

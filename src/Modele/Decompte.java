package Modele;
import java.util.*;

/**
 * Cette m�thode permet de faire le d�compte final, ce qui sert � d�finir le vainqueur de la partie. 
 * @author louis, mathieu
 * @version 2.0
 */

public class Decompte implements Visitor {


	/**
	 * Cet attribut permet de r�ceptionner l'objet correspondant au joueur pour qui on va r�aliser le d�compte. 
	 * Cela permet d'avoir acc�s � sa victory card et � ses points.
	 */
	Joueur joueur; 
	/**
	 * Cet attribut permet de stocker le nombre de carte poss�dant les m�mes couleurs � la suite afin de cr�er un interm�diaire avec les points.
	 * @see Decompte#attributionPointCouleur()
	 */
	private int compteCouleur; 
	/**
	 * Cet attribut permet de stocker le nombre de carte poss�dant les m�mes formes � la suite afin de cr�er un interm�diaire avec les points.
	 * @see Decompte#attributionPointForme()
	 */
	private int compteForme; 
	/**
	 * Cet attribut permet de stocker le nombre de carte poss�dant les m�mes remplissages � la suite afin de cr�er un interm�diaire avec les points.
	 * @see Decompte#attributionPointRemplissage()
	 */
	private int compteRemplissage; 
	/**
	 * Cette m�thode est le constructeur de Decompte. Elle permet d'initialiser les 4 attributs contenu dans la classe.
	 * @param joueur
	 * 			Ce param�tre permet de connaitre le joueur pour qui on d�compte les points. On connaitra notamment sa victory card et ses points.
	 */
	Decompte(Joueur joueur){
		this.joueur = joueur; 
		compteCouleur=0; 
		compteRemplissage=0; 
		compteForme=0; 	
	}


	/**
	 * Cette m�thode permet de faire l'interm�diaire entre le nombre de carte similaire sur la couleur pos�es � la suite, stock�es dans l'attribut compteCouleur et les points du score.
	 * @see Decompte#compteCouleur 
	 */
	private void attributionPointCouleur() {
		switch (compteCouleur){
		case 3 : joueur.setPoint(joueur.getPoint()+4); break; 
		case 4 : joueur.setPoint(joueur.getPoint()+5); break; 
		case 5 : joueur.setPoint(joueur.getPoint()+6); break; 
		}	
		compteCouleur=0; 	
	}

	/**
	 * Cette m�thode permet de faire l'interm�diaire entre le nombre de carte similaire sur la forme pos�es � la suite, stock�es dans l'attribut compteCouleur et les points du score.
	 * @see Decompte#compteForme 
	 */
	private void attributionPointForme() {
		switch (compteForme){
		case 2 : joueur.setPoint(joueur.getPoint()+1); break; 
		case 3 : joueur.setPoint(joueur.getPoint()+2); break; 
		case 4 : joueur.setPoint(joueur.getPoint()+3); break; 
		case 5 : joueur.setPoint(joueur.getPoint()+4); break; 
		}	
		compteForme=0; 
	}

	/**
	 * Cette m�thode permet de faire l'interm�diaire entre le nombre de carte similaire sur le remplissage pos�es � la suite, stock�es dans l'attribut compteCouleur et les points du score.
	 * @see Decompte#compteRemplissage 
	 */
	private void attributionPointRemplissage() {
		switch (compteRemplissage){
		case 3 : joueur.setPoint(joueur.getPoint()+3); break; 
		case 4 : joueur.setPoint(joueur.getPoint()+4); break; 
		case 5 : joueur.setPoint(joueur.getPoint()+5); break; 
		}	
		compteRemplissage=0; 
	}

	/**
	 * Cette m�thode permet de compter les points carte par carte.
	 * Cette m�thode sera appell� par visitHorizontal ou visitVertical.
	 * @param carte
	 * 			Ce param�tre correspond � la carte que l'on va visiter.
	 * @see Decompte#visitHorizontal(TapisCarte)
	 * @see Decompte#visitVertical(TapisCarte)
	 * @see Carte
	 */
	@Override
	public void visit(Carte carte) {
		// Concernant la couleur : 
		if (carte.getCouleur() == joueur.getVictoryCard().getCouleur()) compteCouleur++; 
		else attributionPointCouleur(); 

		// Concernant la forme : 
		if (carte.getForme() == joueur.getVictoryCard().getForme()) compteForme++; 
		else attributionPointForme(); 

		// Concernant le remplissage : 
		if (carte.getRemplissage() == joueur.getVictoryCard().getRemplissage()) compteRemplissage++; 
		else attributionPointRemplissage(); 
	}

	/**
	 * Cette m�thode permet de visiter le tapis de carte de fa�on horizontal.
	 * La m�thode permettra de visiter le tableau carte par carte mais toujours dans l'horizontalit�.
	 * Cette m�thode est appell�e par visit(tapisCarte) et appellera entre autre visit(Carte) et les m�thodes permettant de compter les points.
	 * @param tapisCarte
	 * 			Ici ce param�tre permet de connaitre les cartes pos�es sur le tapis.
	 * @see Decompte#visit(Carte)
	 * @see Decompte#visit(TapisCarte)
	 * @see Decompte#attributionPointCouleur()
	 * @see Decompte#attributionPointForme()
	 * @see Decompte#attributionPointRemplissage()
	 */
	@Override
	public void visitHorizontal(TapisCarte tapisCarte) {
		if (tapisCarte.getForme()==Forme.carre) {
			for(int i=0; i<3; i++) {
				for (int j=0; j<5; j++) {
					if (tapisCarte.getTapis().get(new Coordonnee(i, j))!=null) {
						tapisCarte.getTapis().get(new Coordonnee(i, j)).accept(this); 
					}
					else {
						attributionPointForme(); 
						attributionPointRemplissage(); 
						attributionPointCouleur(); 
					}
				}
				attributionPointForme(); 
				attributionPointRemplissage(); 
				attributionPointCouleur(); 
			} 
		}		
	}

	/**
	 * Cette m�thode permet de visiter le tapis de carte de fa�on vertical.
	 * La m�thode permettra de visiter le tableau carte par carte mais toujours dans la verticalit�.
	 * Cette m�thode est appell�e par visit(tapisCarte) et appellera entre autre visit(Carte) et les m�thodes permettant de compter les points.
	 * @param tapisCarte
	 * 			Ici ce param�tre permet de connaitre les cartes pos�es sur le tapis.
	 * @see Decompte#visit(Carte)
	 * @see Decompte#visit(TapisCarte)
	 * @see Decompte#attributionPointCouleur()
	 * @see Decompte#attributionPointForme()
	 * @see Decompte#attributionPointRemplissage()
	 */
	@Override
	public void visitVertical(TapisCarte tapisCarte) {
		if (tapisCarte.getForme()==Forme.carre) {
			for (int j=0; j<5; j++) {
				for(int i=0; i<3; i++) {
					if (tapisCarte.getTapis().get(new Coordonnee(i, j))!=null) {
						tapisCarte.getTapis().get(new Coordonnee(i, j)).accept(this); 
					}
					else {
						attributionPointForme(); 
						attributionPointRemplissage(); 
						attributionPointCouleur(); 
					}
				}
				attributionPointForme(); 
				attributionPointRemplissage(); 
				attributionPointCouleur(); 
			} 
		}		
	}

	/**
	 * Cette m�thode est la premi�re m�thode qui sera appell� afin de faire le d�compte de notre partie.
	 * Elle appellera les m�thodes visitHorizontal et visitVertical.
	 * @param tapisCarte 
	 * 			Ce param�tre permet de connaitre les diff�rentes positions des cartes.
	 * @see Decompte#visitHorizontal(TapisCarte)
	 * @see Decompte#visitVertical(TapisCarte)
	 */
	@Override
	public void visit(TapisCarte tapisCarte) {
		visitHorizontal(tapisCarte); 
		visitVertical(tapisCarte); 
	}
}




package Modele;
import java.util.*;

/**
 * Cette méthode permet de faire le décompte final, ce qui sert à définir le vainqueur de la partie. 
 * @author louis, mathieu
 * @version 2.0
 */

public class Decompte implements Visitor {


	/**
	 * Cet attribut permet de réceptionner l'objet correspondant au joueur pour qui on va réaliser le décompte. 
	 * Cela permet d'avoir accès à sa victory card et à ses points.
	 */
	Joueur joueur; 
	/**
	 * Cet attribut permet de stocker le nombre de carte possédant les mêmes couleurs à la suite afin de créer un intermédiaire avec les points.
	 * @see Decompte#attributionPointCouleur()
	 */
	private int compteCouleur; 
	/**
	 * Cet attribut permet de stocker le nombre de carte possédant les mêmes formes à la suite afin de créer un intermédiaire avec les points.
	 * @see Decompte#attributionPointForme()
	 */
	private int compteForme; 
	/**
	 * Cet attribut permet de stocker le nombre de carte possédant les mêmes remplissages à la suite afin de créer un intermédiaire avec les points.
	 * @see Decompte#attributionPointRemplissage()
	 */
	private int compteRemplissage; 
	/**
	 * Cette méthode est le constructeur de Decompte. Elle permet d'initialiser les 4 attributs contenu dans la classe.
	 * @param joueur
	 * 			Ce paramètre permet de connaitre le joueur pour qui on décompte les points. On connaitra notamment sa victory card et ses points.
	 */
	Decompte(Joueur joueur){
		this.joueur = joueur; 
		compteCouleur=0; 
		compteRemplissage=0; 
		compteForme=0; 	
	}


	/**
	 * Cette méthode permet de faire l'intermédiaire entre le nombre de carte similaire sur la couleur posées à la suite, stockées dans l'attribut compteCouleur et les points du score.
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
	 * Cette méthode permet de faire l'intermédiaire entre le nombre de carte similaire sur la forme posées à la suite, stockées dans l'attribut compteCouleur et les points du score.
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
	 * Cette méthode permet de faire l'intermédiaire entre le nombre de carte similaire sur le remplissage posées à la suite, stockées dans l'attribut compteCouleur et les points du score.
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
	 * Cette méthode permet de compter les points carte par carte.
	 * Cette méthode sera appellé par visitHorizontal ou visitVertical.
	 * @param carte
	 * 			Ce paramètre correspond à la carte que l'on va visiter.
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
	 * Cette méthode permet de visiter le tapis de carte de façon horizontal.
	 * La méthode permettra de visiter le tableau carte par carte mais toujours dans l'horizontalité.
	 * Cette méthode est appellée par visit(tapisCarte) et appellera entre autre visit(Carte) et les méthodes permettant de compter les points.
	 * @param tapisCarte
	 * 			Ici ce paramètre permet de connaitre les cartes posées sur le tapis.
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
	 * Cette méthode permet de visiter le tapis de carte de façon vertical.
	 * La méthode permettra de visiter le tableau carte par carte mais toujours dans la verticalité.
	 * Cette méthode est appellée par visit(tapisCarte) et appellera entre autre visit(Carte) et les méthodes permettant de compter les points.
	 * @param tapisCarte
	 * 			Ici ce paramètre permet de connaitre les cartes posées sur le tapis.
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
	 * Cette méthode est la première méthode qui sera appellé afin de faire le décompte de notre partie.
	 * Elle appellera les méthodes visitHorizontal et visitVertical.
	 * @param tapisCarte 
	 * 			Ce paramètre permet de connaitre les différentes positions des cartes.
	 * @see Decompte#visitHorizontal(TapisCarte)
	 * @see Decompte#visitVertical(TapisCarte)
	 */
	@Override
	public void visit(TapisCarte tapisCarte) {
		visitHorizontal(tapisCarte); 
		visitVertical(tapisCarte); 
	}
}




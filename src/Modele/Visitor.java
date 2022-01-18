package Modele;
/**
 * Ceci est une interface du patron visitor. Elle marche en pair avec l'interface visitable. Cette interface sera implémentée sur la classe TapisCarte
 * @author louis, mathieu
 * @version 1.0
 * @see Modele.Visitable
 * @see Modele.TapisCarte
 */
public interface Visitor {

	/**
	 * Cette méthode visite le tapis de carte horizontalement
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visité
	 */
	void visitHorizontal(TapisCarte tapisCarte);
	/**
	 * Cette méthode visite le tapis de carte verticalement
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visité
	 */
	void visitVertical(TapisCarte tapisCarte);
	/**
	 * Cette méthode visite le tapis de carte
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visité
	 */
	void visit(TapisCarte tapisCarte);
	/**
	 * Cette méthode visite le tapis de carte carte par carte.
	 * @param carte
	 * 			Il s'agit de la carte qui sera visitée.
	 */
	void visit(Carte carte);
    	
}

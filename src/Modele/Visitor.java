package Modele;
/**
 * Ceci est une interface du patron visitor. Elle marche en pair avec l'interface visitable. Cette interface sera impl�ment�e sur la classe TapisCarte
 * @author louis, mathieu
 * @version 1.0
 * @see Modele.Visitable
 * @see Modele.TapisCarte
 */
public interface Visitor {

	/**
	 * Cette m�thode visite le tapis de carte horizontalement
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visit�
	 */
	void visitHorizontal(TapisCarte tapisCarte);
	/**
	 * Cette m�thode visite le tapis de carte verticalement
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visit�
	 */
	void visitVertical(TapisCarte tapisCarte);
	/**
	 * Cette m�thode visite le tapis de carte
	 * @param tapisCarte
	 * 			Il s'agit du tapis qui sera visit�
	 */
	void visit(TapisCarte tapisCarte);
	/**
	 * Cette m�thode visite le tapis de carte carte par carte.
	 * @param carte
	 * 			Il s'agit de la carte qui sera visit�e.
	 */
	void visit(Carte carte);
    	
}

package Modele;
/**
 * Ceci est une interface du patron visitor. Elle marche en pair avec l'interface visitor. Cette interface sera implémentée sur la classe Decompte
 * @author louis, mathieu
 * @version 1.0
 * @see Modele.Visitor
 * @see Modele.Decompte
 */
public interface Visitable {
    
	/**
	 * Cette méthode permet d'accepter un visitor 
	 * @param v
	 * 			Ce paramètre correspond au visitor à accepter.
	 */
	void accept(Visitor v);

}

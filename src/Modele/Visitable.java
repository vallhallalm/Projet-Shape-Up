package Modele;
/**
 * Ceci est une interface du patron visitor. Elle marche en pair avec l'interface visitor. Cette interface sera impl�ment�e sur la classe Decompte
 * @author louis, mathieu
 * @version 1.0
 * @see Modele.Visitor
 * @see Modele.Decompte
 */
public interface Visitable {
    
	/**
	 * Cette m�thode permet d'accepter un visitor 
	 * @param v
	 * 			Ce param�tre correspond au visitor � accepter.
	 */
	void accept(Visitor v);

}

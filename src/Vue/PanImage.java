package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Modele.Carte;
/**
 * Cette classe permet d'ajuster certains composants graphiques dans notre interface
 * @author louis, mathieu
 * @version 1.0
 * @see Vue.Interface
 */
public class PanImage extends JPanel {
	
	/**
	 * Cet attribut correspond � une carte que l'on va ajuster.
	 */
	private Carte carte; 

	/**
	 * Cette m�thode permet de cr�er une carte et de donner une valeur � notre carte.
	 * @param carte
	 * 			Ce param�tre permet de donner sa valeur � l'attribut carte de cette objet.
	 */
	public PanImage(Carte carte){
		this.carte = carte; 
		Border border = BorderFactory.createLineBorder(Color.black);
		this.setBorder(border);
	}
	
	/**
	 * Cette m�thode permet de cr�er une carte et de donner une valeur null � notre carte.
	 */
	public PanImage() {
		this.carte = null;
		Border border = BorderFactory.createLineBorder(Color.black);
		this.setBorder(border);
	}

	/**
	 * Cette m�thode permet de donner une aparence de carte � notre composant graphique. 
	 * @param g
	 * 			Ici notre param�tre g correspond � l'image de notre carte. 
	 */
	public void paintComponent(Graphics g){
		if (carte!=null) {
			StringBuffer lien = new StringBuffer(); 
			lien.append("img/"); 
			lien.append(carte.getCode()); 
			lien.append(".png"); 
			try {
				Image img = ImageIO.read(new File(lien.toString()));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			 g.setColor(Color.LIGHT_GRAY);
			 g.fillRect(0, 0, this.getWidth(), this.getHeight()); 
		}
	}
	
	/**
	 * Cette m�thode permet de retirer une carte du tapis de carte
	 * @return
	 * 			On retourne la carte retir�e.
	 */
	public Carte removeCarte() {
		Carte carteRetiree = this.carte; 
		this.carte = null; 
		return carteRetiree; 
	}
	
	/**
	 * Il s'agit de la m�thode permettant de modifier la valeur de l'attribut carte.
	 * @param carte
	 * 			Ce param�tre est la future valeure de l'attribut carte.
	 * @see PanImage#carte
	 */
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
}

package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import Modele.Carte;
/**
 * Cette classe permet de 
 * @author louis, mathieu
 * @version 1.0
 *
 */
public class BtnImage extends JButton {
	private Carte carte; 

	public BtnImage(Carte carte){
		this.carte = carte; 
	}
	
	public BtnImage() {
		this.carte = null;
	}

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
	
	public Carte removeCarte() {
		Carte carteRetiree = this.carte; 
		this.carte = null; 
		return carteRetiree; 
	}
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
}

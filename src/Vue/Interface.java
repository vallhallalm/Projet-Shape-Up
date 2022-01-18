package Vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.UIManager;


import Controleur.ControleurBtnName;
import Controleur.ControleurBtnNbIa;
import Controleur.ControleurBtnNbJoueur;
import Controleur.ControleurBtnNiv;
import Controleur.ControleurBtnOrdreDePassage;
import Controleur.ControleurBtnPfc;
import Controleur.ControleurBtnPfc2;
import Controleur.ControleurBtnRegle;
import Controleur.ControleurBtnSetJoueurs;
import Modele.Carte;
import Modele.Coordonnee;
import Modele.Couleur;
import Modele.Forme;
import Modele.IaRandomPoserCarte;
import Modele.Jeu;
import Modele.Joueur;
import Modele.shifumi.Shifumi;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * Cette classe correspond à l'implémentation de l'interface graphique.
 * @author louis, mathieu
 * @version 1.0
 */
public class Interface implements Observer {

	private JFrame frame;
	private Jeu jeu; 
	private int compteurNiv=0;
	private int compteurName=0; 
	private int compteurPfc=0; 


	//Déclaration des différentes panel : 
	private JPanel panRegle = new JPanel();
	private JPanel panNbJoueur = new JPanel(); 
	private JPanel panNbIa = new JPanel(); 
	private JPanel panNiv = new JPanel();
	private JPanel panName = new JPanel();
	private JPanel panPfc = new JPanel();
	private JPanel panOrdreDePassage = new JPanel(); 
	private JPanel panShapeUp = new JPanel(); 
	private PanImage panVictoryCard;
	private PanImage panCartePiochee;

	//Déclaration des différents Bouton
	private JButton btnRegle = new JButton("Ok");
	private JButton btnNbJoueur = new JButton("Ok"); 
	private JButton btnNbIa = new JButton("Ok");
	private JButton btnNiv = new JButton("Ok"); 
	private JButton btnName = new JButton("Ok"); 
	private JButton btnOrdreDePassage = new JButton("Ok"); 
	private JButton btnPfc = new JButton("Validez"); 
	private JButton btnPfc2 = new JButton("Validez"); 
	private JButton btnSetJoueurs = new JButton("Ok"); 
	private BtnImage btnPos0;
	private BtnImage btnPos1;
	private BtnImage btnPos2;
	private BtnImage btnPos3;
	private BtnImage btnPos4;
	private BtnImage btnPos5;
	private BtnImage btnPos6;
	private BtnImage btnPos7;
	private BtnImage btnPos8;
	private BtnImage btnPos9;
	private BtnImage btnPos10;
	private BtnImage btnPos11;
	private BtnImage btnPos12;
	private BtnImage btnPos13;
	private BtnImage btnPos14;
	private JButton btnPioche;
	private JButton btnFinTour;

	//Déclaration des JComboBox : 
	private JComboBox<Integer> comboInt = new JComboBox<Integer>();
	private JComboBox<String> comboStr = new JComboBox<String>();

	//Déclaration du JTextField : 
	private JTextField reponse; 

	//Déclaration JTextArea:
	private JTextArea texte = new JTextArea(); 
	private JLabel lbNomJoueur;


	/**
	 * Il s'agit de la première méthode qui sera éxécutée par le programme et qui permet d'instancier le tout.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu jeu = new Jeu(); 
					Interface window = new Interface(jeu);
					VueTexte ligneCommande = new VueTexte(jeu); 
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Cette méthode permet d'ajouter quelques observer et d'instancier certains boutons utiles pour l'interface.
	 * @param jeu
	 * 			Ce paramètre permet d'ajouter des observateur sur certains composant. 
	 */
	public Interface(Jeu jeu) {
		initialize();
		this.jeu= jeu; 

		//Déclarer les observateurs : 
		jeu.addObserver(this);
		jeu.getPfc().addObserver(this);


		//Déclarer les contrôleurs : 
		new ControleurBtnRegle(jeu, btnRegle); 
		new ControleurBtnNbJoueur(jeu, this); 
		new ControleurBtnNbIa(jeu, this); 
		new ControleurBtnNiv(jeu, this);
		new ControleurBtnName(jeu, this);
		new ControleurBtnOrdreDePassage(jeu, this);
		new ControleurBtnSetJoueurs(jeu, this);
	}

	/**
	 * Cette méthode permet d'initialise l'interface graphique.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Paramètre Shape Up");
		JLabel texte = new JLabel("Bienvenu sur Shape Up. Rappel des règles..."); 
		panRegle.add(texte); 
		panRegle.add(btnRegle); 
		frame.setContentPane(panRegle);

	}

	/**
	 * Cette méthode permet d'initialiser une fenêtre de jeu.
	 */
	public void FenetreJeu() {
		frame.setBounds(100, 100, 1090, 774);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panShapeUp); 
		frame.getContentPane().setLayout(null);
		frame.setTitle("Shape Up");

		btnPos0 = new BtnImage();  
		btnPos0.setBackground(Color.LIGHT_GRAY);
		btnPos0.setBounds(255, 40, 85, 122);
		frame.getContentPane().add(btnPos0);

		btnPos1 = new BtnImage();  
		btnPos1.setBackground(Color.LIGHT_GRAY);
		btnPos1.setBounds(375, 40, 85, 122);
		frame.getContentPane().add(btnPos1);

		btnPos2 = new BtnImage();  
		btnPos2.setBackground(Color.LIGHT_GRAY);
		btnPos2.setBounds(495, 40, 85, 122);
		frame.getContentPane().add(btnPos2);

		btnPos3 = new BtnImage();  
		btnPos3.setBackground(Color.LIGHT_GRAY);
		btnPos3.setBounds(615, 40, 85, 122);
		frame.getContentPane().add(btnPos3);

		btnPos4 = new BtnImage();  
		btnPos4.setBackground(Color.LIGHT_GRAY);
		btnPos4.setBounds(735, 40, 85, 122);
		frame.getContentPane().add(btnPos4);

		btnPos5 = new BtnImage();  
		btnPos5.setBackground(Color.LIGHT_GRAY);
		btnPos5.setBounds(255, 190, 85, 122);
		frame.getContentPane().add(btnPos5);

		btnPos6 = new BtnImage();  
		btnPos6.setBackground(Color.LIGHT_GRAY);
		btnPos6.setBounds(375, 190, 85, 122);
		frame.getContentPane().add(btnPos6);

		btnPos7 = new BtnImage();  
		btnPos7.setBackground(Color.LIGHT_GRAY);
		btnPos7.setBounds(495, 190, 85, 122);
		frame.getContentPane().add(btnPos7);

		btnPos8 = new BtnImage();  
		btnPos8.setBackground(Color.LIGHT_GRAY);
		btnPos8.setBounds(615, 190, 85, 122);
		frame.getContentPane().add(btnPos8);

		btnPos9 = new BtnImage();  
		btnPos9.setBackground(Color.LIGHT_GRAY);
		btnPos9.setBounds(735, 190, 85, 122);
		frame.getContentPane().add(btnPos9);

		btnPos10 = new BtnImage();  
		btnPos10.setBackground(Color.LIGHT_GRAY);
		btnPos10.setBounds(255, 340, 85, 122);
		frame.getContentPane().add(btnPos10);

		btnPos11 = new BtnImage();  
		btnPos11.setBackground(Color.LIGHT_GRAY);
		btnPos11.setBounds(375, 340, 85, 122);
		frame.getContentPane().add(btnPos11);

		btnPos12 = new BtnImage();  
		btnPos12.setBackground(Color.LIGHT_GRAY);
		btnPos12.setBounds(495, 340, 85, 122);
		frame.getContentPane().add(btnPos12);

		btnPos13 = new BtnImage();  
		btnPos13.setBackground(Color.LIGHT_GRAY);
		btnPos13.setBounds(615, 340, 85, 122);
		frame.getContentPane().add(btnPos13);

		btnPos14 = new BtnImage();  
		btnPos14.setBackground(Color.LIGHT_GRAY);
		btnPos14.setBounds(735, 340, 85, 122);
		frame.getContentPane().add(btnPos14);

		btnPioche = new JButton("Pioche");
		btnPioche.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPioche.setBounds(45, 190, 85, 122);
		frame.getContentPane().add(btnPioche);

		lbNomJoueur = new JLabel("New label");
		lbNomJoueur.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbNomJoueur.setBounds(259, 592, 133, 65);
		frame.getContentPane().add(lbNomJoueur);

		panVictoryCard = new PanImage();
		panVictoryCard.setBackground(Color.LIGHT_GRAY);
		panVictoryCard.setBounds(435, 570, 85, 122);
		frame.getContentPane().add(panVictoryCard);

		panCartePiochee = new PanImage();
		panCartePiochee.setBackground(Color.LIGHT_GRAY);
		panCartePiochee.setBounds(555, 570, 85, 122);
		frame.getContentPane().add(panCartePiochee);

		btnFinTour = new JButton("Fin Tour");
		btnFinTour.setBackground(Color.RED);
		btnFinTour.setForeground(Color.WHITE);
		btnFinTour.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFinTour.setBounds(675, 597, 97, 65);
		btnFinTour.setEnabled(false);
		frame.getContentPane().add(btnFinTour);

		JLabel lblNewLabel = new JLabel("Victory Card");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(435, 543, 87, 17);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblCarteEnMain = new JLabel("Carte en main");
		lblCarteEnMain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCarteEnMain.setBounds(555, 543, 97, 17);
		frame.getContentPane().add(lblCarteEnMain);
		frame.setVisible(true);  

	}

	/**
	 * Cette méthode permet d'update nos différents observer.
	 */
	@Override
	public void update(Observable instanceObservable, Object arg1) {
		if (instanceObservable instanceof Jeu) {			
			if (arg1 instanceof String) {
				String cas = (String)arg1; 
				JLabel label; 

				switch (cas) {
				case "NbJoueur" :
					label = new JLabel("Définir le nombre de Joueur : "); 
					comboInt.setPreferredSize(new Dimension(100, 20));
					comboInt.addItem(2);
					comboInt.addItem(3);
					panNbJoueur.add(label); 
					panNbJoueur.add(comboInt);
					panNbJoueur.add(btnNbJoueur); 
					frame.setContentPane(panNbJoueur); 
					frame.repaint();
					frame.revalidate();
					break; 

				case "NbIa": 
					label = new JLabel("Définir le nombre d' ia : "); 
					comboInt.removeAllItems();
					for (int i=0; i<=jeu.getNbJoueur();i++) comboInt.addItem(i);
					panNbIa.add(label); 
					panNbIa.add(comboInt);
					panNbIa.add(btnNbIa); 
					frame.setContentPane(panNbIa); 
					frame.repaint();
					frame.revalidate();
					break; 

				case "Niveau" :
					panNiv.removeAll();
					comboStr.removeAllItems();
					compteurNiv++; 
					label = new JLabel("Définir le niveau de l'ia n°"+compteurNiv+" : "); 
					comboStr.addItem("Facile");
					comboStr.addItem("Normal");
					panNiv.add(label); 
					panNiv.add(comboStr);
					panNiv.add(btnNiv); 
					frame.setContentPane(panNiv); 
					frame.repaint();
					frame.revalidate();
					break; 

				case "Name" : 
					panName.removeAll();
					compteurName++; 
					label = new JLabel("Définir le nom du joueur n°"+compteurName+" : "); 
					panName.add(label);
					reponse = new JTextField("J".concat(((Integer)compteurName).toString())); 
					reponse.setPreferredSize(new Dimension(100, 20));
					panName.add(reponse);
					panName.add(btnName); 
					frame.setContentPane(panName); 
					frame.repaint();
					frame.revalidate();
					break; 

				case "ordreDePassage" :
					label = new JLabel("L'ordre de passage va être déterminé aléatoirement."); 
					texte.setText(null);
					texte.setVisible(false);
					texte.setBackground(label.getBackground());
					texte.setFont(label.getFont());
					btnSetJoueurs.setVisible(false);
					panOrdreDePassage.add(label);
					panOrdreDePassage.add(btnOrdreDePassage); 
					panOrdreDePassage.add(texte);
					panOrdreDePassage.add(btnSetJoueurs); 
					frame.setContentPane(panOrdreDePassage); 
					frame.repaint();
					frame.revalidate();
					break; 

				case "partieLancee" : 
					Collection<Joueur> joueurs = jeu.getJoueurs().values(); 
					Iterator<Joueur> itJoueur = joueurs.iterator(); 
					Joueur joueur = null; 
					while(itJoueur.hasNext()) {
						itJoueur.next().addObserver(this);	
					}
					break; 

				case "Partie" : 
					Set<Map.Entry<String, Joueur>> entries = jeu.getJoueurs().entrySet();
					Iterator<Map.Entry<String, Joueur>> iterator = entries.iterator(); 
					Map.Entry<String, Joueur> entry = null;  
					btnFinTour = new JButton("Fin Tour");
					int i=-1; 
					Carte c = null; 
					while (i<jeu.getTourJoueur()) {
						i++; 
						entry=iterator.next(); 	
					}
					StringBuffer sb = new StringBuffer(); 
					sb.append("C'est au tour de "); 
					sb.append(entry.getKey()); 
					sb.append(" de jouer. "); 
					if (entry.getKey().contains("Ia")==false) {
						if(jeu.getNbJoueurReel()==1) sb.append("\nPour rappel, vous devez forcement poser une carte lors de votre tour. \nSi vous le souhaitez, vous avez le droit de déplacer une carte dans votre tour mais ce n'est pas obligatoire."); 
						else if (jeu.getNbJoueurReel()==2) sb.append("L'autre joueur ne regardez pas l'écran si ce n'est pas votre tour !\nPour rappel, vous devez forcement poser une carte lors de votre tour. \nSi vous le souhaitez, vous avez le droit de déplacer une carte dans votre tour mais ce n'est pas obligatoire.");  
						else sb.append("Les autres joueurs ne regardez pas l'écran si ce n'est pas votre tour ! \nPour rappel, vous devez forcement poser une carte lors de votre tour. \nSi vous le souhaitez, vous avez le droit de déplacer une carte dans votre tour mais ce n'est pas obligatoire."); 
					}
					JOptionPane.showMessageDialog(null, sb.toString(), "Information", JOptionPane.PLAIN_MESSAGE);
					lbNomJoueur.setText(entry.getKey());
					panVictoryCard.setCarte(entry.getValue().getVictoryCard()); 
					frame.setContentPane(panShapeUp); 
					frame.repaint();
					frame.revalidate();
					if (jeu.getTourJoueur()==jeu.getNbJoueur()-1) jeu.setTourJoueur(-1); 
					entry.getValue().jouerTour(entry.getValue(), jeu.getTapisCarte(), jeu.getPioche());
					break; 
				}
			}


			if (arg1 instanceof StringBuffer) {
				StringBuffer message = (StringBuffer)arg1; 
				JOptionPane.showMessageDialog(null, message.toString(), "Fin de Partie", JOptionPane.PLAIN_MESSAGE);
				frame.dispose(); 
			}

		}


		if (instanceObservable instanceof Joueur) {
			if (arg1 instanceof String) AffichageTapis(); 
			else {
				//	System.out.println("La pioche est vide : "+jeu.getPioche().getList().isEmpty());
				if (jeu.getPioche().getList().isEmpty()==false) jeu.partie();
				else {
					AffichageTapis(); 	
					jeu.finPartie();
				}
			}
		}

		if (instanceObservable instanceof Shifumi) {
			if (arg1 instanceof String) {
				String cas = (String)arg1; 
				panPfc.removeAll();
				btnPfc = new JButton("Validez"); 
				btnPfc2 = new JButton("Validez"); 
				compteurPfc++; 
				comboStr.removeAllItems();
				comboStr.addItem("Pierre"); 
				comboStr.addItem("Papier");
				comboStr.addItem("Ciseau");
				JPanel pan1 = new JPanel();
				pan1.setLayout(new BoxLayout(pan1, BoxLayout.LINE_AXIS));
				if (compteurPfc<=2)pan1.add(new JLabel("Vous allez jouer à pierre-feuille-ciseau pour déterminer qui jouera en premier."));
				else pan1.add(new JLabel("Egalité ! Vous allez faire une nouvelle partie."));
				JPanel pan3 = new JPanel();	
				JPanel pan4 = new JPanel(); 
				texte = new JTextArea() ; 
				texte.setLineWrap(true); 
				texte.setWrapStyleWord(true);
				texte.setBackground(new Color(238, 238, 238));
				texte.setPreferredSize(new Dimension(300, 75));
				texte.setFont(new Font("Dialog", Font.BOLD, 12));
				texte.setVisible(false);
				btnSetJoueurs.setVisible(false);
				pan4.add(texte); 
				pan4.add(btnSetJoueurs); 


				if (cas=="pfc Human") {	
					JPanel pan2 = new JPanel();
					pan2.setLayout(new BoxLayout(pan2, BoxLayout.LINE_AXIS));
					pan2.add(new JLabel("Chacun votre tour vous allez choisir votre signe. "+jeu.getName().get(1)+" ne regarde pas quel signe choisit "+jeu.getName().get(0)+"!"));

					if (compteurPfc%2!=0) pan3.add(new JLabel(jeu.getName().get(0)+", choisissez votre signe : "));
					else pan3.add(new JLabel(jeu.getName().get(1)+", choisissez votre signe : "));
					pan3.add(comboStr);

					if (compteurPfc%2!=0) pan3.add(btnPfc);
					else pan3.add(btnPfc2); 	


					//On positionne maintenant ces quatres lignes en colonne
					panPfc.setLayout(new BoxLayout(panPfc, BoxLayout.PAGE_AXIS));
					panPfc.add(pan1);
					panPfc.add(pan2);
					panPfc.add(pan3);
					panPfc.add(pan4); 

					new ControleurBtnPfc(jeu, this);
					new ControleurBtnPfc2(jeu, this, "Human", compteurPfc);
				}

				if (cas=="pfc Ia") {	
					pan3.add(new JLabel(jeu.getName().get(1)+", choisissez votre signe : "));
					pan3.add(comboStr);
					pan3.add(btnPfc2); 

					//On positionne maintenant ces quatres lignes en colonne
					panPfc.setLayout(new BoxLayout(panPfc, BoxLayout.PAGE_AXIS));
					panPfc.add(pan1);
					panPfc.add(pan3);
					panPfc.add(pan4);


					new ControleurBtnPfc2(jeu, this, "Ia", compteurPfc); //pensé compteur
				}
				frame.setContentPane(panPfc); 
				frame.repaint();
				frame.revalidate();
			}
		}
	}



	//--------------------------------Getter et Setter------------------------------------------
	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut frame.
	 * @param frame
	 * 			Ce paramètre est la future valeure de l'attribut frame.
	 * @see Interface#frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame; 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut frame. 
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut frame.
	 * @see Interface#frame
	 */
	public JFrame getFrame() {
		return this.frame; 
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnRegle.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnRegle.
	 * @see Interface#btnRegle
	 */
	public JButton getBtnRegle() {
		return btnRegle;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut btnRegle.
	 * @param btnRegle
	 * 			Ce paramètre est la future valeure de l'attribut btnRegle.
	 * @see Interface#btnRegle
	 */
	public void setBtnRegle(JButton btnRegle) {
		this.btnRegle = btnRegle;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnNbJoueur. 
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnNbJoueur. 
	 * @see Interface#btnNbJoueur
	 */
	public JButton getBtnNbJoueur() {
		return btnNbJoueur;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut btnNbJoueur.
	 * @param btnNbJoueur
	 * 			Ce paramètre est la future valeure de l'attribut btnNbJoueur.
	 * @see Interface#btnNbJoueur
	 */
	public void setBtnNbJoueur(JButton btnNbJoueur) {
		this.btnNbJoueur = btnNbJoueur;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut comboInt.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut comboInt.
	 * @see Interface#comboInt
	 */
	public JComboBox<Integer> getComboInt() {
		return comboInt;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut comboInt.
	 * @param comboInt
	 * 			Ce paramètre est la future valeure de l'attribut comboInt.
	 * @see Interface#comboInt
	 */
	public void setComboInt(JComboBox<Integer> comboInt) {
		this.comboInt = comboInt;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnNbIa  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnNbIa
	 * @see Interface#btnNbIa
	 */
	public JButton getBtnNbIa() {
		return btnNbIa;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut btnNbIa.
	 * @param btnNbIa
	 * 			Ce paramètre est la future valeure de l'attribut btnNbIa.
	 * @see Interface#btnNbIa
	 */
	public void setBtnNbIa(JButton btnNbIa) {
		this.btnNbIa = btnNbIa;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnNiv. 
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnNiv.
	 * @see Interface#btnNiv
	 */
	public JButton getBtnNiv() {
		return btnNiv;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut btnNiv.
	 * @param btnNiv
	 * 			Ce paramètre est la future valeure de l'attribut btnNiv.
	 * @see Interface#btnNiv
	 */
	public void setBtnNiv(JButton btnNiv) {
		this.btnNiv = btnNiv;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut comboStr.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut comboStr.
	 * @see Interface#comboStr
	 */
	public JComboBox<String> getComboStr() {
		return comboStr;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut comboStr.
	 * @param comboStr
	 * 			Ce paramètre est la future valeure de l'attribut comboStr.
	 * @see Interface#comboStr
	 */
	public void setComboStr(JComboBox<String> comboStr) {
		this.comboStr = comboStr;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnName. 
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnName. 
	 * @see Interface#btnName
	 */
	public JButton getBtnName() {
		return btnName;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut reponse.  
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut reponse.
	 * @see Interface#reponse
	 */
	public JTextField getReponse() {
		return reponse;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut reponse.
	 * @param reponse
	 * 			Ce paramètre est la future valeure de l'attribut reponse.
	 * @see Interface#reponse
	 */
	public void setReponse(JTextField reponse) {
		this.reponse = reponse;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnOrdreDePassage.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnOrdreDePassage.
	 * @see Interface#btnOrdreDePassage
	 */
	public JButton getBtnOrdreDePassage() {
		return btnOrdreDePassage;
	}

	/**
	 * Il s'agit de la méthode permettant de modifier la valeur de l'attribut nbJoueur.
	 * @param btnOrdreDePassage
	 * 			Ce paramètre est la future valeure de l'attribut nbJoueur.
	 * @see Jeu#nbJoueur
	 */
	public void setBtnOrdreDePassage(JButton btnOrdreDePassage) {
		this.btnOrdreDePassage = btnOrdreDePassage;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPfc.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPfc.
	 */
	public JButton getBtnPfc() {
		return btnPfc;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPfc2.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPfc2.
	 */
	public JButton getBtnPfc2() {
		return btnPfc2;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut texte.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut texte.
	 */
	public JTextArea getTexte() {
		return texte;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnSetJoueurs.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnSetJoueurs.
	 */
	public JButton getBtnSetJoueurs() {
		return btnSetJoueurs;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos0.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos0.
	 */
	public JButton getBtnPos0() {
		return btnPos0;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos1.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos1.
	 */
	public JButton getBtnPos1() {
		return btnPos1;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos2.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos2.
	 */
	public JButton getBtnPos2() {
		return btnPos2;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos3.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos3.
	 */
	public JButton getBtnPos3() {
		return btnPos3;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos4.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos4.
	 */
	public JButton getBtnPos4() {
		return btnPos4;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos5.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos5.
	 */
	public JButton getBtnPos5() {
		return btnPos5;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos6.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos6.
	 */
	public JButton getBtnPos6() {
		return btnPos6;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos7.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos7.
	 */
	public JButton getBtnPos7() {
		return btnPos7;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos8.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos8.
	 */
	public JButton getBtnPos8() {
		return btnPos8;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos9.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos9.
	 */
	public JButton getBtnPos9() {
		return btnPos9;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos10.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos10.
	 */
	public JButton getBtnPos10() {
		return btnPos10;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos11.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos11.
	 */
	public JButton getBtnPos11() {
		return btnPos11;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos12.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos12.
	 */
	public JButton getBtnPos12() {
		return btnPos12;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos13.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos13.
	 */
	public JButton getBtnPos13() {
		return btnPos13;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPos14.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPos14.
	 */
	public JButton getBtnPos14() {
		return btnPos14;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnPioche.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnPioche.
	 */
	public JButton getBtnPioche() {
		return btnPioche;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut lbNomJoueur.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut lbNomJoueur.
	 */
	public JLabel getLbNomJoueur() {
		return lbNomJoueur;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut panVictoryCard.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut panVictoryCard.
	 */
	public JPanel getPanVictoryCard() {
		return panVictoryCard;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut panCartePiochee.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut panCartePiochee.
	 */
	public JPanel getPanCartePiochee() {
		return panCartePiochee;
	}

	/**
	 * Cette méthode permet de retourner la valeur de l'attribut btnFinTour.
	 * @return
	 * 			Ici on retourne donc la valeur contenu dans l'attribut btnFinTour.
	 */
	public JButton getBtnFinTour() {
		return btnFinTour;
	}

	/**
	 * Cette méthode permet d'afficer le tapis de carte sur l'interface graphique.
	 */
	public void AffichageTapis() {
		int i, j; 
		Coordonnee coord; 
		LinkedHashMap<Coordonnee, Carte> tapis = jeu.getTapisCarte().getTapis();
		if (jeu.getTapisCarte().getForme() == Forme.carre) {		
			for(i=0;i<3;i++){
				for(j=0;j<5;j++){
					coord = new Coordonnee(i, j);
					if (coord.equals(new Coordonnee(0, 0))) btnPos0.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(0, 1))) btnPos1.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(0, 2))) btnPos2.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(0, 3))) btnPos3.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(0, 4))) btnPos4.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(1, 0))) btnPos5.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(1, 1))) btnPos6.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(1, 2))) btnPos7.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(1, 3))) btnPos8.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(1, 4))) btnPos9.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(2, 0))) btnPos10.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(2, 1))) btnPos11.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(2, 2))) btnPos12.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(2, 3))) btnPos13.setCarte(tapis.get(coord));
					else if (coord.equals(new Coordonnee(2, 4))) btnPos14.setCarte(tapis.get(coord)); 
				}
			}
		}		
		frame.setContentPane(panShapeUp); 
		frame.repaint();
		frame.revalidate();		
	}

}

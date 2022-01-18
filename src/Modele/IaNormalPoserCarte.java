package Modele;
import java.util.*;

/**
 * Cette classe est l'impl�mentation de PoserCarteStrategy appliqu� � un joueur virtuel intelligent. Cela permet de faire poser une carte � un joueur virtuel de fa�on plus m�thodique 
 * @author louis, mathieu
 * @version 1.0
 * @see PoserCarteStrategy
 */

public class IaNormalPoserCarte implements PoserCarteStrategy {

	/**
	 * Cette m�thode est la m�thode principale permettant � un joueur virtuel de poser une carte de fa�on m�thodique.
	 * @param ia
	 * 			Cela correspond au joueur.  
	 * @param tapisCarte
	 * 			Ce param�tre va permettre d'analyser les placements de cartes afin de trouver les meilleures coordonn�es pour placer la carte.
	 * @param pioche
	 * 			Ce param�tre permet de piocher une carte afin de la placer. 
	 */

	public void poserCarte(Joueur ia, TapisCarte tapisCarte, Pioche pioche) {
		System.out.println("Je suis une Ia normale ! ");
		Carte cartePioche = pioche.retirerCarte(); 
		boolean premierTour=true;
		Coordonnee coord=null;
		//On compara la carte pioch�e avec la victory card de l'ordi, si aucun attribut des deux cartes est similaire (donc si la carte pioch�e ne peut pas rapporter de point a l'ia), l'ia la poser de mani�re al�atoire

		if (ia.getVictoryCard().getCouleur() != cartePioche.getCouleur() && ia.getVictoryCard().getForme() != cartePioche.getForme() && ia.getVictoryCard().getRemplissage() != cartePioche.getRemplissage()) 
		{
			new IaRandomPoserCarte().poserCarteSpe(ia, tapisCarte, cartePioche);
		}

		else 
		{

			premierTour = tapisCarte.CoordonneeCartePosee().isEmpty(); 
			if (premierTour == true)
			{
				coord = new Coordonnee(1, 2); 
				System.out.println("C'est le premier tour l'ia doit poser une carte a la position : "+ coord.toString());
				System.out.println("La carte pos�e : "+cartePioche.toString()+"\n");
				tapisCarte.getTapis().put(coord, cartePioche); 
			}
			else
			{
				if (tapisCarte.getForme()==Forme.carre) 
				{
					int nbCarteParLigne [] = new int [3];
					nbCarteParLigne[0]=5;
					nbCarteParLigne[1]=5;
					nbCarteParLigne[2]=5;
					LinkedHashSet<Coordonnee> placeOccupee = tapisCarte.CoordonneeCartePosee();
					Iterator <Coordonnee> it = placeOccupee.iterator();
					coord = null;
					Coordonnee coordRetenu = null;
					boolean coordTrouve = false;
					while(it.hasNext() && coordTrouve == false)
					{
						coord=it.next();
						System.out.println(coord.toString());
						if (tapisCarte.getTapis().get(coord).getCouleur()==cartePioche.getCouleur())
						{
							coordRetenu = trouverPlace(tapisCarte, coord);
							if (coordRetenu != null)
							{
								coordTrouve=true;
							}
						}
						else if (tapisCarte.getTapis().get(coord).getRemplissage()==cartePioche.getRemplissage())
						{
							coordRetenu = trouverPlace(tapisCarte, coord);
							if (coordRetenu != null)
							{
								coordTrouve=true;
							}
						}
						else if (tapisCarte.getTapis().get(coord).getForme()==cartePioche.getForme())
						{
							coordRetenu = trouverPlace(tapisCarte, coord);
							if (coordRetenu != null)
							{
								coordTrouve=true;
							}
						}
					}	


					if (coordRetenu == null)
					{
						new IaRandomPoserCarte().poserCarteSpe(ia, tapisCarte, cartePioche);
					}
					else
					{
						System.out.println("L'ia pose une carte a la position : "+ coordRetenu.toString());
						System.out.println("La carte pos�e : "+cartePioche.toString()+"\n");
						tapisCarte.getTapis().put(coord, cartePioche);
						System.out.println(tapisCarte.DescriptionTapis());
					}
				}
			}
		}

	}
	/**
	 * Cette m�thode permet de savoir si il y a une place disponible pr�s d'une carte ayant des caract�ristiques communes avec la carte pioch�e.
	 * Cela permet de savoir si L'IA peut poser sa carte pr�s d'une autre carte avantageuse 
	 * @param tapisCarte
	 * 				Ce param�tre permet de visiter les cartes adjacentes � la carte avantageuse
	 * @param coord
	 * 				Ce param�tre correspond au coordonn�e de la carte avantageuse.
	 * @return
	 * 				Cette m�thode permet de retourner soit une coordonn�e ou poser une carte soit null si il n'y a pas de coordonn�es adjacentes � la carte avantageuse.
	 * @see IaNormalPoserCarte#poserCarte(Joueur, TapisCarte, Pioche)
	 */
	public Coordonnee trouverPlace (TapisCarte tapisCarte, Coordonnee coord)
	{
		Coordonnee coordTest=coord;
		coordTest.setPosX(coord.getPosX()+1);
		if (tapisCarte.getTapis().get(coordTest)==null && coordTest.getPosX()<2 && coordTest.getPosX()>0 && coordTest.getPosY()<4 && coordTest.getPosX()>0)
		{
			return coordTest;
		}
		coordTest=coord;
		coordTest.setPosX(coord.getPosX()-1);
		if (tapisCarte.getTapis().get(coordTest)==null && coordTest.getPosX()<2 && coordTest.getPosX()>0 && coordTest.getPosY()<4 && coordTest.getPosX()>0)
		{
			return coordTest;
		}
		coordTest=coord;
		coordTest.setPosX(coord.getPosY()+1);
		if (tapisCarte.getTapis().get(coordTest)==null && coordTest.getPosX()<2 && coordTest.getPosX()>0 && coordTest.getPosY()<4 && coordTest.getPosX()>0)
		{
			return coordTest;
		}
		coordTest=coord;
		coordTest.setPosX(coord.getPosY()-1);
		if (tapisCarte.getTapis().get(coordTest)==null && coordTest.getPosX()<4 && coordTest.getPosX()>0 && coordTest.getPosY()<2 && coordTest.getPosX()>0)
		{
			return coordTest;
		}
		return null;
	}
}

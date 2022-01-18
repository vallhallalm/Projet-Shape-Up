package Modele;
import java.util.*;


/**
 * Cette classe est l'implémentation de JouerTourStrategy appliqué à un joueur humain. Cela permet de régir un tour pour un joueur humain. 
 * @author louis, mathieu
 * @version 1.0
 * @see JouerTourStategy
 */
public class HumanJouerTour extends Observable implements JouerTourStategy {

	/**
	 * Cet attribut permet de savoir si nous sommes au premier tour ou non.
	 */
	private boolean premierTour;

	/**
	 * Cette méthode est la méthode principale régissant un tour pour un humain.
	 * @param j
	 * Cela correspond au joueur.  
	 * param tapisCarte
	 * Ce paramètre permet d'envoyer le tapis de carte dans les méthodes le nécessitant.
	 * @param pioche
	 * Ce paramètre permet d'envoyer la pioche vers les méthodes necessitant la pioche (HumanPoserCarte)
	 */
	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		Scanner clavier = new Scanner(System.in);		//on ouvre le péripherique permettant de lire les entrées de l'utilisateur

		if (tapisCarte.CoordonneeCartePosee().isEmpty()) premierTour=true; 
		else premierTour = false; 

		if (premierTour==false)
		{
			String reponse;
			if (tapisCarte.CoordonneePossible().size()==1)
			{
				System.out.println("Nous sommes au dernier tour. Vous n'aurez pas la possibilité de déplacer une carte après d'en avoir posé une. Voulez-vous d'abbord déplacer une carte ( entrez O pour oui et N pour non)");
				reponse= clavier.nextLine();
				//clavier.nextLine();
				while (!(reponse.equals("O")|| reponse.equals("N")))
				{
					System.out.println("la lettre entrée ne correspond pas, veuillez rentre soit un O pour oui soit un N pour non");
					reponse=clavier.nextLine();
				}
				if (reponse.equals("O"))
				{
					j.deplacerCarte(j, tapisCarte, pioche);
					j.poserCarte(j, tapisCarte, pioche);
					tapisCarte.DescriptionTapis();
				}
				else if (reponse.equals("N"))
				{
					j.poserCarte(j, tapisCarte, pioche);
					tapisCarte.DescriptionTapis();
				}
			}
			else
			{
				
				System.out.println("Voulez vous déplacer une carte ou en piocher une. entrez D pour déplacer ou P pour piocher suivi de la touche entrée.");
				reponse= clavier.nextLine();
				//clavier.nextLine();
				while (!(reponse.equals("P")|| reponse.equals("D")))
				{
					System.out.println("la lettre entrée ne correspond pas, veuillez rentre soit un D pour déplacer une carte, soit un P pour piocher une carte");
					reponse=clavier.nextLine();
				}
				if (reponse.equals("P"))
				{
					j.poserCarte(j, tapisCarte, pioche); 
					tapisCarte.DescriptionTapis();
					System.out.println("voulez vous déplacer une carte ? si oui tapez O et si non tapez N");
					reponse=clavier.nextLine();
					while (!(reponse.equals("O")|| reponse.equals("N")))
					{
						System.out.println("la lettre entrée ne correspond pas, veuillez rentre soit un D pour déplacer une carte, soit un P pour piocher une carte");
						reponse=clavier.nextLine();
					}
					if (reponse.equals("O"))
					{
						j.deplacerCarte(j,tapisCarte, pioche);
					}
				}
				else if (reponse.equals("D"))
				{
					j.deplacerCarte(j,tapisCarte, pioche);
					j.poserCarte(j, tapisCarte, pioche);
				}
				//clavier.close();

				System.out.println(tapisCarte.DescriptionTapis());
			}

		}
		else
		{
			j.poserCarte(j, tapisCarte, pioche);
		}
	}
	
	/**
	 * Cette méthode permet de renvoyer la valeur du booléen premierTour
	 * @return
	 * 			On retourne donc la valeur de premierTour.
	 */
	public boolean isPremierTour() {
		return premierTour;
	}	
	
	
}

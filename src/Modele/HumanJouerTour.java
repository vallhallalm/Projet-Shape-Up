package Modele;
import java.util.*;


/**
 * Cette classe est l'impl�mentation de JouerTourStrategy appliqu� � un joueur humain. Cela permet de r�gir un tour pour un joueur humain. 
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
	 * Cette m�thode est la m�thode principale r�gissant un tour pour un humain.
	 * @param j
	 * Cela correspond au joueur.  
	 * param tapisCarte
	 * Ce param�tre permet d'envoyer le tapis de carte dans les m�thodes le n�cessitant.
	 * @param pioche
	 * Ce param�tre permet d'envoyer la pioche vers les m�thodes necessitant la pioche (HumanPoserCarte)
	 */
	public void jouerTour(Joueur j, TapisCarte tapisCarte, Pioche pioche) {
		Scanner clavier = new Scanner(System.in);		//on ouvre le p�ripherique permettant de lire les entr�es de l'utilisateur

		if (tapisCarte.CoordonneeCartePosee().isEmpty()) premierTour=true; 
		else premierTour = false; 

		if (premierTour==false)
		{
			String reponse;
			if (tapisCarte.CoordonneePossible().size()==1)
			{
				System.out.println("Nous sommes au dernier tour. Vous n'aurez pas la possibilit� de d�placer une carte apr�s d'en avoir pos� une. Voulez-vous d'abbord d�placer une carte ( entrez O pour oui et N pour non)");
				reponse= clavier.nextLine();
				//clavier.nextLine();
				while (!(reponse.equals("O")|| reponse.equals("N")))
				{
					System.out.println("la lettre entr�e ne correspond pas, veuillez rentre soit un O pour oui soit un N pour non");
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
				
				System.out.println("Voulez vous d�placer une carte ou en piocher une. entrez D pour d�placer ou P pour piocher suivi de la touche entr�e.");
				reponse= clavier.nextLine();
				//clavier.nextLine();
				while (!(reponse.equals("P")|| reponse.equals("D")))
				{
					System.out.println("la lettre entr�e ne correspond pas, veuillez rentre soit un D pour d�placer une carte, soit un P pour piocher une carte");
					reponse=clavier.nextLine();
				}
				if (reponse.equals("P"))
				{
					j.poserCarte(j, tapisCarte, pioche); 
					tapisCarte.DescriptionTapis();
					System.out.println("voulez vous d�placer une carte ? si oui tapez O et si non tapez N");
					reponse=clavier.nextLine();
					while (!(reponse.equals("O")|| reponse.equals("N")))
					{
						System.out.println("la lettre entr�e ne correspond pas, veuillez rentre soit un D pour d�placer une carte, soit un P pour piocher une carte");
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
	 * Cette m�thode permet de renvoyer la valeur du bool�en premierTour
	 * @return
	 * 			On retourne donc la valeur de premierTour.
	 */
	public boolean isPremierTour() {
		return premierTour;
	}	
	
	
}

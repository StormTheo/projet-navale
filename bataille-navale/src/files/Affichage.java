/*
 * Affichage.java                                 30/04
 */

package files;

/**
 * 
 * @author INFO1
 * @version 1.0
 *
 */
public class Affichage {

	/**
	 * Texte de titre pour le menu principal
	 */
	private static final String TITRE_MENU_PRINCIPAL = 
			"------------------------------------------------------------------\n"
			+ "|                      MENU PRINCIPAL                            |\n"
			+ "------------------------------------------------------------------\n";

	/**
	 * Liste des options possibles (caract�res) pour le menu principal
	 */
	private static final char[] OPTION_MENU_PRINCIPAL = { 'j', 'a', 'q'};

	/**
	 * Liste des options possible (textes) pour le menu principal
	 */
	private static final String[] LIBELLE_MENU_PRINCIPAL = {"Jouer",
								"Aide",
								"Quitter"};

	/**
	 * Affichage du menu principal
	 */
	public static void menuPrincipal() {
		System.out.println(TITRE_MENU_PRINCIPAL);

		// on affiche toutes les options et les libell�s
		for(int i = 0; i < OPTION_MENU_PRINCIPAL.length; i++) {
			System.out.print("   => " + OPTION_MENU_PRINCIPAL[i] 
					+ " - " + LIBELLE_MENU_PRINCIPAL[i] + "\n");
		}
	}

	/**
	 * @param aTester 
	 * @return un boolean vrai si le character est valide, faux sinon.
	 */
	public static boolean reponseValide(String aTester) {
	    boolean valide;
	    
	    //  la r�ponse ne contient pas un caract�re unique ==> true
	    valide = !(aTester == null || aTester.length() != 1);  
	    if (valide) {
	        /* Pour la premiere it�ration de la boucle */
	        valide = false; 
	        
	        for (int index = 0; index < OPTION_MENU_PRINCIPAL.length 
	                && !valide; index++) {
	            valide = aTester.charAt(0) == OPTION_MENU_PRINCIPAL[index]
	                     || aTester.charAt(0)+32 == OPTION_MENU_PRINCIPAL[index];
	        }
	        
	    }
	    return valide;
	}
	
	/**
	 * m�thode contenant la String pour l'aide
	 */
	public static final String AIDE = 
			"\n------------------------------------------------------------------------\n\n"
			+ "======== Bienvenue dans notre jeu de Bataille navale ! =========\n\n"
			+ "Le but du jeu est de couler les diff�rents bateaux pr�sents \n"
			+ "sur le plateau en entrant des coordonn�es � chaque tour.\n\n"
			+ "Il existe diff�rents types de bateaux qui ont des tailles \n"
			+ "diff�rentes. Ils se situent au minimum � une case l'un de l'autre\n"
			+ "A chaque tour sont affich�es les diff�rentes informations sur la partie\n"
			+ "en cours.\n"
			+ "------------------------------------------------------------------------\n";

	/**
	 * Option disponible pour l'aide
	 */
	private static final char[] OPTION_AIDE = { 'j', 'q'};

	/**
	 * Liste des options possible (textes) pour le menu principal
	 */ 
	private static final String[] LIBELLE_AIDE = {"Jouer",
						      "Quitter"};
	
	/**
	 * Affichage de l'aide
	 */
	public static void afficherAide() {
		System.out.println(AIDE);

		// on affiche toutes les options et les libell�s
		for(int i = 0; i < OPTION_AIDE.length; i++) {
			System.out.print("   => " + OPTION_AIDE[i] 
					+ " - " + LIBELLE_AIDE[i] + "\n");
		}
	}

}

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
    * Liste des options possibles (charact�res) pour le menu principal
    */
    private static final char[] OPTION_MENU_PRINCIPAL = { 'j', 'a', 'q'};
    
    /**
     * Liste des options possible (textes) pour le menu principal
     */
    private static final String[] LIBELLE_MENU_PRINCIPAL = {"Jouer",
            												"Aide",
    														"Quitter"};
	
	public static void menuPrincipale() {
		System.out.println(TITRE_MENU_PRINCIPAL);
        
        // on affiche toutes les options et les libell�s
        for(int i = 0; i < OPTION_MENU_PRINCIPAL.length; i++) {
            System.out.print("   => " + OPTION_MENU_PRINCIPAL[i] 
                             + " - " + LIBELLE_MENU_PRINCIPAL[i] + "\n");
        }
        System.out.print("\n       ====> ");
	}
	
	/**
	 * m�thode contenant la String pour l'aide
	 */
	public static final String AIDE = "fkjheizufhef";
	
	/**
	 * Affichage de l'aide
	 */
	public static void afficherAide() {
		System.out.println(AIDE);
	}
	
    public static void main(String[] args) {
    	 menuPrincipale();
    }
}

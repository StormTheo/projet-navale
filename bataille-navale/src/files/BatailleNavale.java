/*
 * BatailleNavale.java                                                   1 mai 2019
 * IUT info1 2018-2019 groupe 1, pas de droits : ni copyright ni copyleft
 */

package files;
import java.util.Scanner;
import files.Affichage;
import files.Plateau;

/**
 * Classe principale du jeu
 * @author IUT INFO1 groupe 1
 *
 */
public class BatailleNavale {
    
    /**
     * Affiche le Menu principal et en fonction du choix,
     * renvoie � la m�thode appropri�e
     */
    public static void Menu() {
        
        boolean nok;
        String reponse;
        
        Affichage.menuPrincipal();
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while(!nok);
        
        if (reponse.charAt(0) == 'a') {
            aide();
        } else if (reponse.charAt(0) == 'q') {
            // arr�t du programme
        }
    }
    
    
    /**
     * Affiche l'aide et effectue l'action appropri�e
     * en fonction du choix de l'utilisateur
     */
    public static void aide() {
        String reponse;
        boolean nok;
        
        Affichage.afficherAide(); 
        /* saisie du choix de l'utilisateur */
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while(!nok);
        
        if (reponse.charAt(0) == 'a') {
            aide();
        } else if (reponse.charAt(0) == 'q') {
            // arr�t du programme
        }  
        // else lance le jeu dans le main
    }
    
    /**
     * r�cup�re la cha�ne de caract�res entr�e par l'utilisateur
     * @return la cha�ne de caract�res que l'utilisateur a entr�e
     */
    public static String entreeUtilisateur() {
        /* 
         * scanner r�cup�rant les choix et 
         * r�ponses de l'utilisateur 
         */
        Scanner entree = new Scanner(System.in);
        
        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;
        
        nok = false;
        do {
            /* raz de la r�ponse */
            reponse = "";
            System.out.print("\n       ====> ");
            
            if (entree.hasNext()) {
                reponse = entree.nextLine();
                nok = true;
            }
        } while(!nok);
        return reponse;
    }
    
    /**
     * r�cup�ration des coordonn�es qu'entre 
     * le joueur et placement de celles-ci dans deux variables
     *  x et y de type String.
     * @param plateauJeu 
     */
    public static void recupCoord(Plateau plateauJeu) {
        String coordonnees;
        /* coordonn�es s�par�s en 
         * abscisse et ordonn�e */
        char x = '0';
        int y;
        String chaineY = null;
         
        System.out.print("Entrez les coordonn�s sous la forme : caract�re, nombre (ex : A, 11)");
        coordonnees = entreeUtilisateur();
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caract�re minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' 
                && coordonnees.charAt(placement) <= 'z')
                || ( coordonnees.charAt(placement) >= 'A'
                && coordonnees.charAt(placement) <= 'Z')) {
                
                /* x = morceau de cha�ne avec le caract�re */
                x = coordonnees.charAt(placement); 
            }
            if (coordonnees.charAt(placement) >= '1' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur num�rique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement+1);
                } else {
                    chaineY += coordonnees.substring(placement, placement+1);
                }
            }
        }
        /* Si x et y sont existant dans le plateau. */
        y = Integer.parseInt(chaineY);
        if ( ( ( x <= plateauJeu.getDimX()+ 'A' && x >= 'A') 
              || (x <= plateauJeu.getDimX()+ 'a' && x >= 'a') )
             && (y > 0 && y <= plateauJeu.getDimY() ) ) {
            
            // TODO lancer recherche par rapport aux bateaux.
            
        } else {
            System.out.println("Coordonn�es incorrect, votre plateau a une dimension x = "
                                + plateauJeu.getDimX() + " y = " + plateauJeu.getDimY());
            recupCoord(plateauJeu);
        }
    }
    
    /**
     * Lancement des principales fonctions 
     * et cr�ations des objets
     * @param args non utilis�
     */
    public static void main(String[] args) {
        Menu();
        Plateau plateauJeu;
        plateauJeu = new Plateau();
        System.out.println(plateauJeu.toString());
        // TODO placer les bateaux
        // TODO afficher nombre de bateaux et types / longueurs
        // TODO afficher informations de la partie en cours avant 
        // la demande des coordonn�es -> m�thode.
        recupCoord(plateauJeu);
    }

}

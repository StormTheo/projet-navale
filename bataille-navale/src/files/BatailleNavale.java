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
     * renvoie à la méthode appropriée
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
            // arrêt du programme
        }
    }
    
    
    /**
     * Affiche l'aide et effectue l'action appropriée
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
            // arrêt du programme
        }  
        // else lance le jeu dans le main
    }
    
    /**
     * récupère la chaîne de caractères entrée par l'utilisateur
     * @return la chaîne de caractères que l'utilisateur a entrée
     */
    public static String entreeUtilisateur() {
        /* 
         * scanner récupérant les choix et 
         * réponses de l'utilisateur 
         */
        Scanner entree = new Scanner(System.in);
        
        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;
        
        nok = false;
        do {
            /* raz de la réponse */
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
     * récupération des coordonnées qu'entre 
     * le joueur et placement de celles-ci dans deux variables
     *  x et y de type String.
     */
    public static void recupCoord() {
        String coordonnees;
        /* coordonnées séparés en 
         * abscisse et ordonnée */
        String x = null;
        String y = null;
        
        System.out.print("Entrez les coordonnés");
        coordonnees = entreeUtilisateur();
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caractère minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' 
                && coordonnees.charAt(placement) <= 'z')
                || ( coordonnees.charAt(placement) >= 'A'
                && coordonnees.charAt(placement) <= 'Z')) {
                
                /* x = morceau de chaîne avec le caractère */
                x = coordonnees.substring(placement, placement+1); 
            }
            if (coordonnees.charAt(placement) >= '1' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur numérique */
                if (y == null) {
                    y = coordonnees.substring(placement, placement+1);
                } else {
                    y += coordonnees.substring(placement, placement+1);
                }
            }
        }
        System.out.println(x + " = x\n" + y + " = y");
    }
    
    /**
     * Lancement des principales fonctions 
     * et créations des objets
     * @param args non utilisé
     */
    public static void main(String[] args) {
        Menu();
        Plateau plateauJeu;
        plateauJeu = new Plateau();
        System.out.println(plateauJeu.toString());
        // TODO placer les bateaux
        // TODO afficher nombre de bateaux et types / longueurs
        // TODO afficher informations de la partie en cours avant 
        // la demande des coordonnées -> méthode.
        recupCoord();
    }

}

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
 * @author Mathieu Capo
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
            System.out.println("test");
            aide();
        } else if (reponse.charAt(0) == 'q') {
            // TODO fermer le programme
        } else {
            // TODO lancer le jeu
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
        
           
    }
    
    /**
     * r�cup�re la chaine de caract�res entr�e par l'utilisateur
     * @return la chaine de caract�res que l'utilisateur a entr�e
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
                reponse = entree.next();
                nok = true;
            }
            
            entree.nextLine();
        } while(!nok);
        
        return reponse;
    }
    
    /**
     * TODO commenter le r�le de cette m�thode
     * @param args non utilis�
     */
    public static void main(String[] args) {
        Menu();
        // TODO JEU
        
    }

}

/*
 * TestBateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files.tests;

import java.util.ArrayList;
import java.util.Scanner;

import files.Bateau;
/**
 * Classe Bateau permettant de cr�er des bateaux de 
 * @author INFO1
 * Version 1
 */
public class TestBateau {

    /** Objet Scanner pour effectuer les saisies au clavier */
    private static Scanner entree = new Scanner(System.in);

    /**
     * Demande � l'utilisateur d'appuyer sur entr�e pour continuer
     */
    private static void continuer() {
        System.out.println("\n\n\nAppuyez sur entr�e pour continuer les tests.") ;
        entree.nextLine();
        System.out.println("\n\n");
    }

    /** TODO commenter le r�le du champ (attribut, r�le associatif...) */
    private static final String[] BATEAU_NOM = {"sous-marin","Porte-avion","saucisse","chocapic"};
    /** TODO commenter le r�le du champ (attribut, r�le associatif...) */
    private static final int[] BATEAU_TAILLE = {3,4,1,2};

    /**
     * test visuel des constructeurs qui cr�ent 5 bateaux 
     */
    public static void testConstructeur() {
        System.out.println("------------------------------------------------------\n"
                + "Test visuel\n"
                + "------------------------------------------------------\n\n");
        /* constructeur par d�faut */
        new Bateau ();

        /* constructeurs avec param�tres */
        for (int numero = 0; numero < BATEAU_NOM.length; numero++) {
            /* cr�ation de chaque bateau avec les informations des tableaux */
            new Bateau(BATEAU_NOM[numero], BATEAU_TAILLE[numero]);
        }
        for (int liste = 0; liste < Bateau.getFlotte().size()-1; liste++) {
            System.out.println("Bateau suppos� : " + BATEAU_NOM[liste] 
                    + ", taille : " + BATEAU_TAILLE[liste] + "\n");

            System.out.println("==> nom : " + Bateau.getFlotte().get(liste+1).getNom()
                    + ", taille : " + Bateau.getFlotte().get(liste+1).getTaille()
                    + "\n");
        }
        /* affichage du bateau cr�er avec les valeurs par d�faut */
        System.out.println("Par d�faut : \nBateau suppos� : Vedette , taille : 2\n");
        System.out.println("==> nom : " + Bateau.getFlotte().get(0).getNom() 
                + " taille : " + Bateau.getFlotte().get(0).getTaille()
                + "\n");
        continuer();
    }

    /**
     * TODO commenter le r�le de cette m�thode
     * @param args
     */
    public static void main(String[] args) {
        /* System.out.println(Bateau.getFlotte().get(0).getTaille()); */
        testConstructeur();
    }
}

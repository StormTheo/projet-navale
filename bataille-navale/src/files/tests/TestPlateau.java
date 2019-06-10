/*
 * TestPlateau.java						23 avril 2019
 * INFO1 - Groupe de projet Bataille Navale
 */

package files.tests;

import files.Plateau;
import java.util.Scanner;

/**
 * Tests unitaires des m�thodes de la classe Plateau.java Tests unitaires : -
 * Construction d'un plateau avec l'initialisation par d�faut - Construction
 * d'un plateau avec des param�tres valide en entr�e - Construction d'un plateau
 * avec des param�tres invalide en entr�e - Accesseur sur la dimension en
 * abscisse : getDimX - Accesseur sur la dimension en ordonn�e : getDimY - La
 * m�thode toString
 *
 * @author Nicolas Couffin
 */
public class TestPlateau {
     // TODO V�rification g�n�rale avant de le rendre
    /*
     * ***************** M�thodes outils pour g�rer les tests ********************
     */

    /** Objet Scanner pour effectuer les saisies au clavier */
    private static Scanner entree = new Scanner(System.in);

    /**
     * Demande à l'utilisateur d'appuyer sur entr�e pour continuer
     */
    private static void continuer() {
        System.out.println("\n\n\nAppuyez sur entr�e pour continuer les tests.");
        entree.nextLine();
        System.out.println("\n\n");
    }

    /* ********************** Tests Unitaires des m�thodes ********************** */

    /** Couples de dimensions invalides */
    public static final int[][] DIM_INVALIDE = { { -1, 7 }, { 14, 0 }, { 45, 478 }, { 44, -7 }, { -9, 85 }, { 1, 0 }

    };

    /**
     * Couples de dimensions valides (la dimension par d�faut (13*13) est omise pour
     * v�rifier si les dimensions sont consid�r�es comme valides)
     */
    public static final int[][] DIM_VALIDE = { { 13, 25 }, { 3, 4 }, { 2, 2 }, { 26, 26 }, { 6, 6 }, { 15, 6 } };

    /**
     * test m�thode de cr�ation par d�faut du plateau 
     */
    public static void testPlateau() {
    	Plateau plateauDef = new Plateau();
    	System.out.println("cr�ation et affichage d'un plateau par d�faut, donc ayant une taille = 12  \n");
    	plateauDef.afficherGrille(false);
    	System.out.println("Test de cr�ation de plateaux, avec des valeurs valides \n");
    	for (int index = 0; index < DIM_VALIDE.length; index++) {
    		System.out.println("Plateau de taille : ligne = " + DIM_VALIDE[index][0] 
    				                          + " colonne = " + DIM_VALIDE[index][1]);
    		Plateau plateauTestValide = new Plateau(DIM_VALIDE[index][0], DIM_VALIDE[index][1]);
    		plateauTestValide.afficherGrille(false);
    	}
    	continuer();
    	System.out.println("TEST de cr�ation de plateaux, avec des valeurs invalides \n");
    	for (int index = 0; index < DIM_INVALIDE.length; index++) {
    		System.out.println("Plateau de taille : ligne = 12" 
    				                          + " colonne = 12");
    		Plateau plateauTestValide = new Plateau(DIM_INVALIDE[index][0], DIM_INVALIDE[index][1]);
    		plateauTestValide.afficherGrille(false);
    	}
    }

    /**
     * Test de la m�thode getDimX
     */
    public static void testGetDimX() {
        System.out.println(
                "--------------------------\n" + "Test de la m�thode getDimX\n" + "--------------------------\n\n");
        /* Tests avec valeurs valides */
        System.out
                .println("---- Tout les tests doivent afficher la valeur de dimX :" + " Tests valeurs valides ----\n");
        continuer();
        for (int i = 0; i < DIM_VALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println("La m�thode doit renvoyer " + DIM_VALIDE[i][0] + " \n-> La m�thode renvoit : "
                    + jeu.getDimX() + "\n");
        }
        continuer();

        /* Tests avec valeurs invalides */
        System.out.println(
                "---- Tout les test doivent afficher la valeur de dimX par d�faut :" + " Tests valeurs invalides ----\n"
                        + "\n(Note : M�me si la valeur de dimX est valide, celle de dimY est invalide. De ce fait,"
                        + " les deux dimensions sont remplac�es par la dimension par d�faut.\n");
        continuer();
        for (int i = 0; i < DIM_INVALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_INVALIDE[i][0], DIM_INVALIDE[i][1]);
            System.out.println("La valeur est " + DIM_INVALIDE[i][0] + " et la m�thode doit renvoyer 13"
                    + "\n-> La m�thode renvoit " + jeu.getDimX() + "\n");
        }
        System.out.println("\n---- Fin testGetDimX ----\n");
        continuer();
    }

    /**
     * Test de la m�thode getDimY
     */
    public static void testGetDimY() {
        System.out.println(
                "--------------------------\n" + "Test de la m�thode getDimY\n" + "--------------------------\n\n");

        /* Tests avec valeurs valides */
        System.out
                .println("---- Tout les tests doivent afficher la valeur de dimY :" + " Tests valeurs valides ----\n");
        continuer();

        for (int i = 0; i < DIM_VALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println("La m�thode doit renvoyer " + DIM_VALIDE[i][1] + " \n-> La m�thode renvoit : "
                    + jeu.getDimY() + "\n");
        }
        continuer();

        /* Tests avec valeurs invalides */
        System.out.println(
                "---- Tout les test doivent afficher la valeur de dimY par d�faut :" + " Tests valeurs invalides ----\n"
                        + "\n(Note : M�me si la valeur de dimY est valide, celle de dimX est invalide. De ce fait,"
                        + " les deux dimensions sont remplac�es par la dimension par d�faut.\n");
        continuer();

        for (int i = 0; i < DIM_INVALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_INVALIDE[i][0], DIM_INVALIDE[i][1]);
            System.out.println("La valeur est " + DIM_INVALIDE[i][1] + " et la m�thode doit renvoyer 13"
                    + "\n-> La m�thode renvoit " + jeu.getDimY() + "\n");
        }
        System.out.println("\n---- Fin testGetDimY ----\n");
        continuer();
    }

    /**
     * Test de la m�thode toString
     */
    public static void testToString() {
        System.out.println(
                "---------------------------\n" + "Test de la m�thode toString\n" + "---------------------------\n\n");

        for (int i = 0; i < DIM_VALIDE.length; i++) {
            System.out.println("Test n�" + (i + 1) + ":\n");
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println(jeu.toString() + "\n");
        }
        System.out.println("\n---- Fin testToString ----\n");
        continuer();
    }

    /**
     * Tests unitaires des m�thodes
     * 
     * @param args Non utilis�
     */
    public static void main(String[] args) {
    	testPlateau();
        testGetDimX();
        testGetDimY();
        testToString();

    }
}

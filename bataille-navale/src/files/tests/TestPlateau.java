/*
 * TestPlateau.java						23 avril 2019
 * INFO1 - Groupe de projet Bataille Navale
 */

package files.tests;

import files.Plateau;
import java.util.Scanner;

/**
 * Tests unitaires des méthodes de la classe Plateau.java Tests unitaires : -
 * Construction d'un plateau avec l'initialisation par défaut - Construction
 * d'un plateau avec des paramètres valide en entrée - Construction d'un plateau
 * avec des paramètres invalide en entrée - Accesseur sur la dimension en
 * abscisse : getDimX - Accesseur sur la dimension en ordonnée : getDimY - La
 * méthode toString
 *
 * @author Nicolas Couffin
 */
public class TestPlateau {
     // TODO Vérification générale avant de le rendre
    /*
     * ***************** Méthodes outils pour gérer les tests ********************
     */

    /** Objet Scanner pour effectuer les saisies au clavier */
    private static Scanner entree = new Scanner(System.in);

    /**
     * Demande Ã  l'utilisateur d'appuyer sur entrée pour continuer
     */
    private static void continuer() {
        System.out.println("\n\n\nAppuyez sur entrée pour continuer les tests.");
        entree.nextLine();
        System.out.println("\n\n");
    }

    /* ********************** Tests Unitaires des méthodes ********************** */

    /** Couples de dimensions invalides */
    public static final int[][] DIM_INVALIDE = { { -1, 7 }, { 14, 0 }, { 45, 478 }, { 44, -7 }, { -9, 85 }, { 1, 0 }

    };

    /**
     * Couples de dimensions valides (la dimension par défaut (13*13) est omise pour
     * vérifier si les dimensions sont considérées comme valides)
     */
    public static final int[][] DIM_VALIDE = { { 13, 25 }, { 3, 4 }, { 2, 2 }, { 27, 27 }, { 6, 6 }, { 15, 6 } };

    /**
     * test méthode de création par défaut du plateau 
     */
    public static void testPlateau() {
    	Plateau plateauDef = new Plateau();
    	System.out.println("création et affichage d'un plateau par défaut, donc ayant une taille = 12  \n");
    	plateauDef.afficherGrille(false);
    	System.out.println("Test de création du plateaux, avec des valeurs valides \n");
    	for (int index = 0; index < DIM_VALIDE[1].length; index++) {
    		System.out.println("Plateau de taille : ligne = " + DIM_VALIDE[index][0] 
    				                          + " colonne = " + DIM_VALIDE[index][1]);
    		Plateau plateauTestValide = new Plateau(DIM_VALIDE[index][0], DIM_VALIDE[index][1]);
    		plateauTestValide.afficherGrille(false);
    	}
    }

    /**
     * Test de la méthode getDimX
     */
    public static void testGetDimX() {
        System.out.println(
                "--------------------------\n" + "Test de la méthode getDimX\n" + "--------------------------\n\n");
        /* Tests avec valeurs valides */
        System.out
                .println("---- Tout les tests doivent afficher la valeur de dimX :" + " Tests valeurs valides ----\n");
        continuer();
        for (int i = 0; i < DIM_VALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println("La méthode doit renvoyer " + DIM_VALIDE[i][0] + " \n-> La méthode renvoit : "
                    + jeu.getDimX() + "\n");
        }
        continuer();

        /* Tests avec valeurs invalides */
        System.out.println(
                "---- Tout les test doivent afficher la valeur de dimX par défaut :" + " Tests valeurs invalides ----\n"
                        + "\n(Note : Même si la valeur de dimX est valide, celle de dimY est invalide. De ce fait,"
                        + " les deux dimensions sont remplacées par la dimension par défaut.\n");
        continuer();
        for (int i = 0; i < DIM_INVALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_INVALIDE[i][0], DIM_INVALIDE[i][1]);
            System.out.println("La valeur est " + DIM_INVALIDE[i][0] + " et la méthode doit renvoyer 13"
                    + "\n-> La méthode renvoit " + jeu.getDimX() + "\n");
        }
        System.out.println("\n---- Fin testGetDimX ----\n");
        continuer();
    }

    /**
     * Test de la méthode getDimY
     */
    public static void testGetDimY() {
        System.out.println(
                "--------------------------\n" + "Test de la méthode getDimY\n" + "--------------------------\n\n");

        /* Tests avec valeurs valides */
        System.out
                .println("---- Tout les tests doivent afficher la valeur de dimY :" + " Tests valeurs valides ----\n");
        continuer();

        for (int i = 0; i < DIM_VALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println("La méthode doit renvoyer " + DIM_VALIDE[i][1] + " \n-> La méthode renvoit : "
                    + jeu.getDimY() + "\n");
        }
        continuer();

        /* Tests avec valeurs invalides */
        System.out.println(
                "---- Tout les test doivent afficher la valeur de dimY par défaut :" + " Tests valeurs invalides ----\n"
                        + "\n(Note : Même si la valeur de dimY est valide, celle de dimX est invalide. De ce fait,"
                        + " les deux dimensions sont remplacées par la dimension par défaut.\n");
        continuer();

        for (int i = 0; i < DIM_INVALIDE.length; i++) {
            Plateau jeu = new Plateau(DIM_INVALIDE[i][0], DIM_INVALIDE[i][1]);
            System.out.println("La valeur est " + DIM_INVALIDE[i][1] + " et la méthode doit renvoyer 13"
                    + "\n-> La méthode renvoit " + jeu.getDimY() + "\n");
        }
        System.out.println("\n---- Fin testGetDimY ----\n");
        continuer();
    }

    /**
     * Test de la méthode toString
     */
    public static void testToString() {
        System.out.println(
                "---------------------------\n" + "Test de la méthode toString\n" + "---------------------------\n\n");

        for (int i = 0; i < DIM_VALIDE.length; i++) {
            System.out.println("Test nÂ°" + (i + 1) + ":\n");
            Plateau jeu = new Plateau(DIM_VALIDE[i][0], DIM_VALIDE[i][1]);
            System.out.println(jeu.toString() + "\n");
        }
        System.out.println("\n---- Fin testToString ----\n");
        continuer();
    }

    /**
     * Tests unitaires des méthodes
     * 
     * @param args Non utilisé
     */
    public static void main(String[] args) {
    	testPlateau();
        testGetDimX();
        testGetDimY();
        testToString();

    }
}

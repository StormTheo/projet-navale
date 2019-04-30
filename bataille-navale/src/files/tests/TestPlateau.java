/*
 * TestPlateau.java						23 avril 2019
 * INFO1 - Groupe de projet Bataille Navale
 */

package files.tests;

import files.Plateau;
import java.util.Scanner;

/**
 * Tests unitaires des m�thodes de la classe Plateau.java
 * Tests unitaires :
 * 		- Construction d'un plateau avec l'initialisation par d�faut
 * 		- Construction d'un plateau avec des param�tres valide en entr�e
 * 		- Construction d'un plateau avec des param�tres invalide en entr�e
 * 		- Accesseur sur la dimension en abscisse : getDimX
 * 		- Accesseur sur la dimension en ordonn�e : getDimY
 * 		- La m�thode toString
 *
 * @author Nicolas Couffin
 */
public class TestPlateau {
	// TODO V�rification g�n�ral avant de le rendre
	/* ***************** M�thodes outils pour g�rer les tests ******************** */
	
    /** Objet Scanner pour effectuer les saisies au clavier */
    private static Scanner entree = new Scanner(System.in);
	
    /**
     * Demande � l'utilisateur d'appuyer sur entr�e pour continuer
     */
    private static void continuer() {
        System.out.println("\n\n\nAppuyer sur entr�e pour continuer les tests.") ;
        entree.nextLine();
        System.out.println("\n\n");
    }
	
	/* ********************** Tests Unitaires des m�thodes ********************** */
	
	/** Couples de dimensions invalides */
	public static final int [][] DIM_INVALIDE = {
			{-1,7},
			{14,0},
			{45,478},
			{44,-7},
			{-9,85},
			{1,0}
		
	};
	
	/** Couples de dimensions valides
	 * (la dimension par d�faut (13*13) est homise pour v�rifier
	 * si les dimensions sont consid�r� comme valide)
	 */
	public static final int [][] DIM_VALIDE = {
			{13,25},
			{3,4},
			{2,2},
			{27,27},
			{6,6},
			{15,6}
	};
	
	/**
	 * Test du constructeur par d�faut pour l'initialisation (Plateau())
	 * et la construction du plateau (constructPlateau()) par d�faut
	 */
	public static void testPlateauDefaut() {
		System.out.println("------------------------------------------------------\n"
			               + "Test de la m�thode Plateau (initialisation par d�faut)\n"
			               + "------------------------------------------------------\n\n");
		
		String[][] plateau;	// plateau � afficher
		Plateau jeu = new Plateau();
		plateau = jeu.constructPlateau(); 
		
		/* affichage du plateau cr�er */
		System.out.println("Un plateau de 13*13 doit �tre afficher :\n"
							+ "- La premi�re case doit �tre vide\n"
							+ "- La premi�re ligne va de A � L\n"
							+ "- La premi�re colonne va de 1 � 12\n"
							+ "- Les autres cases doivent �tre vides\n"
							+ "- L'affichage du plateau est clair (pas de colonne d�cal�)"
							+ "\n\n");
		continuer();
		for (int i = 0; i < plateau.length; i++) {
			for (String v : plateau[i]) {
				if (i < 10 && v.charAt(0) <= '9' && v.charAt(0) != ' ') {
					System.out.print("| " + v + "|");							// Rajout d'un espace avant le chiffre pour lisibilit� du plateau
				} else {
					System.out.print("|" + v + "|");
				}
			}
			System.out.println();
		}
		System.out.println("\n---- Fin testPlateauDefaut ----\n");
		continuer();
	}
	
	/**
	 * Test du constructeur pour l'initialisation � partir d'argument (Plateau(int,int))
	 * et la construction du plateau (constructPlateau())
	 * Tests avec dimensions valides.
	 */
	public static void testPlateauValide() {
		System.out.println("--------------------------------------------------------------\n"
	               + "Test de la m�thode Plateau (initialisation par argument valide)\n"
	               + "--------------------------------------------------------------\n\n");
		
		/* Tests avec dimensions valides */
		
		String[][] plateau; // plateau � afficher
		
		System.out.println("---- Tests avec dimensions valides ----\n"
							+ "Le test doit afficher 6 plateaux aux dimensions valides indiqu�es\n"
							+ "Si un plateau a une dimension de 13*13 (= dimension par d�faut),"
							+ " il y a une erreur : La dimension a alors �t� consid�r�e comme invalide.\n");
		continuer();
		for (int i = 0; i < DIM_VALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_VALIDE[i][0],DIM_VALIDE[i][1]);
			System.out.println("Plateau de dimension valide : " + DIM_VALIDE[i][0] 
								+ "*" + DIM_VALIDE[i][1]);
			System.out.println("Affichage du plateau :");
			plateau = jeu.constructPlateau();
			/* Affichage du plateau */
			for (int j = 0; j < plateau.length; j++) {
				for (String v : plateau[j]) {
					if (j < 10 && v.charAt(0) <= '9' && v.charAt(0) != ' ') {
						System.out.print("| " + v + "|");							// Rajout d'un espace avant le chiffre pour lisibilit� du plateau
					} else {
						System.out.print("|" + v + "|");
					}
				}
				System.out.println();
			}
			continuer();
		}
		System.out.println("\n---- Fin testPlateauValide ----\n");
		continuer();
	}
	
	/**
	 * Test du constructeur pour l'initialisation � partir d'argument (Plateau(int,int))
	 * et la construction du plateau (constructPlateau())
	 * Test avec dimension invalide.
	 */	
    public static void testPlateauInvalide() {
		System.out.println("-----------------------------------------------------------------\n"
	               + "Test de la m�thode Plateau (initialisation par argument invalide)\n"
	               + "-----------------------------------------------------------------\n\n");

		System.out.println("---- Tests avec dimensions invalides ----\n"
				+ "Le test doit afficher uniquement des plateau de 13*13.\n"
				+ "(Rappel : un plateau de 13*13 est le plateau par d�faut cr�er si les "
				+ "dimensions sont invalides)\n");
		
		/* Tests avec dimensions invalides */
		String[][] plateau; // plateau � afficher
		
		for (int i = 0; i < DIM_INVALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_INVALIDE[i][0],DIM_INVALIDE[i][1]);		
			System.out.println("La dimension " + DIM_INVALIDE[i][0]
								+ "*" + DIM_INVALIDE[i][1] + " est consid�r� comme invalide"
								+ " et doit afficher un tableau de 13*13");
			plateau = jeu.constructPlateau();
			/* Affichage du plateau */
			for (int j = 0; j < plateau.length; j++) {
				for (String v : plateau[j]) {
					if (j < 10 && v.charAt(0) <= '9' && v.charAt(0) != ' ') {
						System.out.print("| " + v + "|");							// Rajout d'un espace avant le chiffre pour lisibilit� du plateau
					} else {
						System.out.print("|" + v + "|");
					}
				}
				System.out.println();
			}
			continuer();
		}
		System.out.println("\n---- Fin testPlateauInvalide ----\n");
		continuer();
	}
	
	/**
	 * Test de la m�thode getDimX
	 */
	public static void testGetDimX() {
		System.out.println("--------------------------\n"
	               + "Test de la m�thode getDimX\n"
	               + "--------------------------\n\n");
		/* Tests avec valeurs valides */
		System.out.println("---- Tout les tests doivent afficher la valeur de dimX :"
				+ " Tests valeurs valides ----\n");
		continuer();
		for (int i = 0; i < DIM_VALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_VALIDE[i][0],DIM_VALIDE[i][1]);
			System.out.println("La m�thode doit renvoyer " + DIM_VALIDE[i][0]
								+ " \n-> La m�thode renvoit : " + jeu.getDimX() + "\n");
        }
		continuer();
		
		/* Tests avec valeurs invalides */
		System.out.println("---- Tout les test doivent afficher la valeur de dimX par d�faut :"
				+ " Tests valeurs invalides ----\n"
				+ "\n(Note : M�me si la valeur de dimX est valide, celle de dimY est invalide. De ce fait,"
				+ " les deux dimensions sont remplac�es par la dimension par d�faut.\n");
		continuer();
		for (int i = 0; i < DIM_INVALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_INVALIDE[i][0],DIM_INVALIDE[i][1]);
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
		System.out.println("--------------------------\n"
	               + "Test de la m�thode getDimY\n"
	               + "--------------------------\n\n");

		/* Tests avec valeurs valides */
		System.out.println("---- Tout les tests doivent afficher la valeur de dimY :"
				+ " Tests valeurs valides ----\n");
		continuer();
		
		for (int i = 0; i < DIM_VALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_VALIDE[i][0],DIM_VALIDE[i][1]);
			System.out.println("La m�thode doit renvoyer " + DIM_VALIDE[i][1]
								+ " \n-> La m�thode renvoit : " + jeu.getDimY() + "\n");
		}
		continuer();
		
		/* Tests avec valeurs invalides */
		System.out.println("---- Tout les test doivent afficher la valeur de dimY par d�faut :"
				+ " Tests valeurs invalides ----\n"
				+ "\n(Note : M�me si la valeur de dimY est valide, celle de dimX est invalide. De ce fait,"
				+ " les deux dimensions sont remplac�es par la dimension par d�faut.\n");
		continuer();
		
		for (int i = 0; i < DIM_INVALIDE.length; i++) {
			Plateau jeu = new Plateau(DIM_INVALIDE[i][0],DIM_INVALIDE[i][1]);
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
		System.out.println("---------------------------\n"
	               + "Test de la m�thode toString\n"
	               + "---------------------------\n\n");
		
		for (int i = 0; i < DIM_VALIDE.length; i++) {
			System.out.println("Test n�" + (i+1) + ":\n");
			Plateau jeu = new Plateau(DIM_VALIDE[i][0],DIM_VALIDE[i][1]);
			System.out.println(jeu.toString() + "\n");
		}
		System.out.println("\n---- Fin testToString ----\n");
		continuer();
	}
	
	/**
	 * Tests unitaires des m�thodes
	 * @param args Non utilis�
	 */
	public static void main (String[] args) {
		testPlateauDefaut();
		testPlateauValide();
		testPlateauInvalide();
		testGetDimX();
		testGetDimY();
		testToString();
	}
}

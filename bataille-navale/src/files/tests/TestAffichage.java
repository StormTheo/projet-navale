/*
 * Test de la classe GestionInterface
 * TestGestionInterface.java                                        01/17
 */
package files.tests;

import files.Affichage;

import java.util.Scanner;


/**
 * 
 * @author INFO1 Semestre 2
 * @version 1.0
 *
 */
public class TestAffichage {
    
    public static void testMenuPrincipale() {
    	Affichage.menuPrincipale();
    }
    /**
     * Programme principal. Point d'entr�e pour lancer les tests
     * @param args  argument non utilis�
     */
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------\n"
                + "     TESTS DE LA CLASSE  Affichage   \n"
                + "-----------------------------------------------\n");
        testMenuPrincipale();
    }
}

/*
 * TestBateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files.tests;

import java.util.ArrayList;

import files.Bateau;
/**
 * Classe Bateau permettant de cr�er des bateaux de 
 * @author Th�o Bouchouieff
 * Version 1
 */
public class TestBateau {
    
    /**
     * TODO commenter le r�le de cette m�thode
     * @param args
     */
    public static void main(String[] args) {
        Bateau test = new Bateau("chocapic", 4);
        Bateau test2 = new Bateau();
        
        System.out.println(Bateau.getFlotte().get(0).getTaille());
    }
}

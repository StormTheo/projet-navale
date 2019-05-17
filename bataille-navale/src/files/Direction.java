/*
 * Direction.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

import java.util.Random;

/**
 * Les quatre directions possibles et utilis�es dans le jeu
 * @author lucas
 *
 */
public enum Direction {
    
    /** Vers le haut */
    haut, 
    /** Vers la gauche */
    gauche,
    /** Vers le bas */
    bas, 
    /** Vers la droite */
    droite;
    
    /**
     * Choisis une direction al�atoire et la renvoie
     * @return la direction choisie al�atoirement
     */
    public static Direction directionAleatoire() {
        /* Le g�n�rateur de nombres pseudo al�atoires */
        Random random = new Random();
        /* Nombre repr�sentant la direction choisie
         * 1 = haut
         * 2 = gauche
         * 3 = bas
         * 4 = droite
         */
        int dirChoisie;
        
        dirChoisie = random.nextInt(3) + 1;
        
        switch(dirChoisie) {
        case 1:
            return haut;
        case 2:
            return gauche;
        case 3:
            return bas;
        case 4:
        default:
            return droite;
        }
    }
}

/*
 * Direction.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

/**
 * Les quatre directions possibles et utilis�es dans le jeu
 * @author INFO1
 *
 */
public class Direction {
    
    /**
     * Renvoie les valeurs ad�quates par rapport au caract�re entr� par l'utilisateur.
     * Modifie la direction
     * @return le sens
     */
    public int directionChoisie(String dirChoisie, int direction) {
        
        switch(dirChoisie) {
        case "n":
        	
        case "N":
        	direction = 1;
        	return 1;
        case "e":
            
        case "E":
        	direction = 2;
        	return 2;
        case "s":
        
        case"S":
        	direction = 1;
        	return 2;
        case "o":
        	
        case "O":	
            direction = 2;
            return 1;
        default:
        	direction = -1;
            return -1;
        }
    }
}

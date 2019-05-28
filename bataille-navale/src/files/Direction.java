/*
 * Direction.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

/**
 * Les quatre directions possibles et utilisées dans le jeu
 * @author INFO1
 *
 */
public class Direction {
    
	private int direction;
    private int sens;
	
    /**
     * Renvoie les valeurs adéquates par rapport au caractère entré par l'utilisateur.
     * Modifie la direction
     * @return le sens
     */
    public void directionChoisie(String dirChoisie) {
        switch(dirChoisie.charAt(0)) {
        case 'n':
        	
        case 'N':
        	this.direction = 1;
        	this.sens =  1;
        	break;
        case 'e':
            
        case 'E':
        	this.direction = 2;
        	this.sens = 2;
        	break;
        case 's':
        
        case'S':
        	this.direction = 1;
        	this.sens = 2;
        	break;
        case 'o':
        	
        case 'O':	
        	this.direction = 2;
        	this.sens = 1;
        	break;
        default:
        	this.direction = -1;
        	this.sens = -1;
        	break;
        }
    }

	public int getDirection() {
		return direction;
	}

	public int getSens() {
		return sens;
	}
    
    
}

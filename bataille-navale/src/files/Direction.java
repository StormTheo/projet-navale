/*
 * Direction.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

/**
 * Les quatre directions possibles et utilisées dans le jeu
 * 
 * @author INFO1
 *
 */
public class Direction {

    /** La direction dans laquelle placer le bateau : 1 = H/B 2 = G/D */
    private int direction;
    /** Le sens dans lequel placer le bateau : 1 = Vers H/G, 2 = Vers G/B */
    private int sens;

    /**
     * Renvoie les valeurs adéquates par rapport au caractère entré par
     * l'utilisateur. Modifie la direction
     * @param dirChoisie la direction à vérifier
     */
    public void directionChoisie(String dirChoisie) {
        switch (dirChoisie.charAt(0)) {
        case 'n':

        case 'N':
            this.direction = 1;
            this.sens = 1;
            break;
        case 'e':

        case 'E':
            this.direction = 2;
            this.sens = 2;
            break;
        case 's':

        case 'S':
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

    /**
     * Renvoie la direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Renvoie le sens
     * @return sens
     */
    public int getSens() {
        return sens;
    }

}

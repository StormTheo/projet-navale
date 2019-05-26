/*
 * coordonnees.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

/**
 * Classe coordonnées permettant de créée les coordonnées des bateaux.
 * @author INFO1
 */
public class Coordonnees {

    /** Valeur par défaut d'une coordonnée dans le tableau */
    private static final String COORD_DEFAUT = null;

    /** une coordonnée en abscisse */
    private String coordX;

    /** une coordonnée en ordonnée */
    private String coordY;

    /** une table contenant X et Y */
    private String[][] coordTable;

    /**
     * TODO commenter le rôle de cette méthode
     * @return coordX
     */
    public String getCoordX() {
        return coordX;
    }

    /**
     * TODO commenter le rôle de cette méthode
     * @return coordY
     */
    public String getCoordY() {
        return coordY;
    }

    /**
     * TODO commenter le rôle de cette méthode
     * @return coordTable
     */
    public String[][] getCoordTable() {
        return coordTable;
    }

    /**
     * Constructeur par défaut
     */
    public Coordonnees() {
        this.coordTable[0][0] = COORD_DEFAUT;
        this.coordTable[1][0] = COORD_DEFAUT;
        this.coordY = COORD_DEFAUT;
        this.coordX = COORD_DEFAUT;
    }

    /**
     * constructeur de l'objet coordonnées avec les paramètres.
     * 
     * @param coordX String contenant une lettre, pour la position en abscisse
     * @param coordY String contenant un nombre, pour la position en ordonnée
     */
    public Coordonnees(String coordX, String coordY) {
        this.coordTable[0][0] = coordX;
        this.coordTable[1][0] = coordY;
        this.coordY = coordY;
        this.coordX = coordX;
    }
}

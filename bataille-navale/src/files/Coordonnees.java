/*
 * coordonnees.java                                                   16 mai 2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright ni copyleft
 */
package files;

/**
 * Classe coordonn�es permettant de cr��e les coordonn�es des bateaux.
 * @author INFO1
 *
 */
public class Coordonnees {
    
    /** Valeur par d�faut d'une coordonn�e dans le tableau  */
    private static final String COORD_DEFAUT = null;
    
    /** une coordonn�e en abscisse  */
    private String coordX;
    
    /** une coordonn�e en ordonn�e */
    private String coordY;
    
    /** une table contenant X et Y */
    private String[][] coordTable;
    
    /**
     * Constructeur par d�faut 
     */
    public Coordonnees() {
        this.coordTable[0][0] = COORD_DEFAUT;
        this.coordTable[1][0] = COORD_DEFAUT;
        this.coordY = COORD_DEFAUT;
        this.coordX = COORD_DEFAUT;
    }
    
    /**
     * constructeur de l'objet coordonn�es avec les param�tres.
     * @param coordX String contenant une lettre, pour la position en abscisse
     * @param coordY String contenant un nombre, pour la position en ordonn�e
     */
    public Coordonnees(String coordX, String coordY) {
        this.coordTable[0][0] = coordX;
        this.coordTable[1][0] = coordY;
        this.coordY = coordY;
        this.coordX = coordX;
    }
       
}

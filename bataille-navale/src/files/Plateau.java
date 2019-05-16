/* Plateau.java							22 avril 2019
 * INFO 1 - Groupe de projet Bataille Navale
 */

package files;

import java.util.ArrayList;
import java.util.List;
import files.Bateau;

/**
 * Classe qui initialise une plateau de jeu pour la bataille navale.
 * Le plateau est un repère ayant en abscisse des lettres (de A à Z max)
 * et en ordonnée des chiffres (de 1 à 26 max). On peut donc voir la
 * formation de cases qui servirons de coordonnées (ex : C14).
 * Les principales opérations sont :
 * 		- Deux constructeurs dont l'un pour l'initialisation par défaut
 * 		- Une méthode pour construire le plateau
 * 		- Un accesseur sur la dimension en abscisse
 * 		- Un accesseur sur la dimension en ordonnée
 * 		- Une méthode toString
 * 
 * @author Nicolas Couffin
 * @version 1.O
 */

public class Plateau {
	
	/** 
	 * Dimension par défaut attribuée aux dimensions en abscisse et
	 * en ordonnée.
	 */
	private static final int DIM_DEFAUT = 13;
	
	/** Dimension minimale */
	private static final int DIM_MIN = 2; // Minimum requis pour avoir au moins une case de jeu sur le plateau.
	
	/** Dimension maximale */
	private static final int DIM_MAX = 27;  // Maximum par rapport au nombre de lettres dans l'alphabet + la première case vide
	
	/** Dimension en abscisse */
	private int dimX;
	
	/** Dimension en ordonnée */
	private int dimY;
	
    /** Liste contenant tous les bateaux, composant une flotte */
    private static List<Bateau> flotte = new ArrayList<Bateau>();
    
	/**
	 * Constructeur par défaut qui initialise les dimensions
	 * avec la valeur par défaut
	 */
	public Plateau() {
		dimX = DIM_DEFAUT;
		dimY = DIM_DEFAUT;
	}
	
	/**
	 * Constructeur pour initialiser avec les valeurs en arguments.
	 * @param xDim entier contenant la dimension en abscisse
	 * @param yDim entier contenant la dimension en ordonnée
	 */
	public Plateau(int xDim, int yDim) {
		this();
		/* Vérification des paramètres*/
		if (yDim >= DIM_MIN && yDim <= DIM_MAX
				&& xDim >= DIM_MIN && xDim <= DIM_MAX) {
			
			this.dimX = xDim;
			this.dimY = yDim;
		}
	}
	
	/**
	 * Méthodes pour la construction du plateau à partir d'un tableau
	 * @return le tableau qui fait office de plateau
	 */
	public String[][] constructPlateau() {
		String[][] plateau = new String[this.dimY][this.dimX]; // création du tableau pour former le plateau
		plateau[0][0] = "  "; // Première case vide
		
		/* Insertion des lettres sur la première ligne */
		for (int i = 0; i < plateau[0].length-1; i++) {
			plateau[0][i+1] = Character.toString((char) ('A' + i));
		}
		
		/* Insertion des chiffes sur la première colonne de chaque lignes */
		for (int i = 0; i < plateau.length-1; i++) {
			if (i+1 < 10) {
				plateau[i+1][0] = Integer.toString(i+1);
			} else {
				plateau[i+1][0] = Integer.toString(i+1);
			}
			for (int j = 1; j < plateau[0].length; j++) {
				plateau[i+1][j] = " "; // Pour les éléments vides sur la même ligne que le chiffre
			}
		}
		return plateau;
	}
	
	/**
	 * Accesseur sur la dimension en abscisse
	 * @return un entier égal à la dimension en abscisse
	 */
	public int getDimX() {
		return this.dimX;
	}
	
	/**
	 * Accesseur sur la dimension en ordonnée
	 * @return un entier égal à la dimension en ordonnée
	 */
	public int getDimY() {
		return this.dimY;
	}
	
	
	
	/**
     * retourne la liste contenant tout les objets Bateau créés
     * 
     * @return flotte
     */
    public static List<Bateau> getFlotte() {
        return new ArrayList<>(flotte);
    }
    
    /** 
     * permet d'ajouter un bateau.
     * @param bateau
     */
    public static void setFlotte(Bateau bateau) {
    	Plateau.flotte.add(bateau);
    }
    
    /**
     * vide la liste des objets créés
     */
    public static void clearFlotte() {
        flotte.clear();
    }
    
    
	/**
	 * Renvoie les dimensions du plateau de jeu sous la forme d'une
	 * chaîne de caractères.
	 * @return Une chaîne contenant la dimension en abscisse et en ordonnée
	 */
	public String toString() {
		return "Dimension en abscisse (dimX) : " + this.dimX
				+ "\nDimension en ordonnée (dimY) : " + this.dimY;
	}
	
	//TODO rework la classe afin qu'elle utilise un tableau de Bateau pour stocker les bateaux
	
}


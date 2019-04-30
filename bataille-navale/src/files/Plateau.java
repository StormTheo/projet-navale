/* Plateau.java							22 avril 2019
 * INFO 1 - Groupe de projet Bataille Navale
 */

package files;

/**
 * Classe qui initialise une plateau de jeu pour la bataille navale.
 * Le plateau est un rep�re ayant en abscisse des lettres (de A � Z max)
 * et en ordonn�e des chiffres (de 1 � 26 max). On peut donc voir la
 * formation de cases qui servirons de coordonn�es (ex : C14).
 * Les principales op�rations sont :
 * 		- Deux constructeurs dont l'un pour l'initialisation par d�faut
 * 		- Une m�thode pour construire le plateau
 * 		- Un accesseur sur la dimension en abscisse
 * 		- Un accesseur sur la dimension en ordonn�e
 * 		- Une m�thode toString
 * 
 * @author Nicolas Couffin
 * @version 1.O
 */

public class Plateau {
	
	/** 
	 * Dimension par d�faut attribu� aux dimensions en abscisse et
	 * en ordonn�e.
	 */
	private static final int DIM_DEFAUT = 13;
	
	/** Dimension minimal */
	private static final int DIM_MIN = 2; // Minimum requis pour faire avoir au moins une case de jeu sur le plateau.
	
	/** Dimension maximal */
	private static final int DIM_MAX = 27;  // Maximum par rapport au nombre de lettre dans l'alphabet + la premi�re case vide
	
	/** Dimension en abscisse */
	private int dimX;
	
	/** Dimension en ordonn�e */
	private int dimY;
	
	/**
	 * Constructeur par d�faut qui initialise les dimensions
	 * avec la valeur par d�faut
	 */
	public Plateau() {
		dimX = DIM_DEFAUT;
		dimY = DIM_DEFAUT;
	}
	
	/**
	 * Constructeur pour initialiser avec les valeurs en arguments.
	 * @param xDim entier contenant la dimension en absisse
	 * @param yDim entier contenant la dimension en ordonn�e
	 */
	public Plateau(int xDim, int yDim) {
		this();
		/* V�rification des param�tres*/
		if (yDim >= DIM_MIN && yDim <= DIM_MAX
				&& xDim >= DIM_MIN && xDim <= DIM_MAX) {
			
			this.dimX = xDim;
			this.dimY = yDim;
		}
	}
	
	/**
	 * M�thodes pour la construction du plateau � partir d'un tableau
	 * @return le tableau qui fait office de plateau
	 */
	public String[][] constructPlateau() {
		String[][] plateau = new String[this.dimY][this.dimX]; // cr�ation du tableau pour former le plateau
		plateau[0][0] = "  "; // Premi�re case vide
		
		/* Insertion des lettres sur la premi�re ligne */
		for (int i = 0; i < plateau[0].length-1; i++) {
			plateau[0][i+1] = Character.toString((char) ('A' + i));
		}
		
		/* Insertion des chiffes sur la premi�re colonne de chaque lignes */
		for (int i = 0; i < plateau.length-1; i++) {
			if (i+1 < 10) {
				plateau[i+1][0] = Integer.toString(i+1);
			} else {
				plateau[i+1][0] = Integer.toString(i+1);
			}
			for (int j = 1; j < plateau[0].length; j++) {
				plateau[i+1][j] = " "; // Pour les �l�ments vides sur la m�me ligne que le chiffre
			}
		}
		return plateau;
	}
		
	/**
	 * Accesseur sur la dimension en abscisse
	 * @return un entier �gale � la dimension en abscisse
	 */
	public int getDimX() {
		return this.dimX;
	}
	
	/**
	 * Accesseur sur la dimension en ordonn�e
	 * @return un entier �gale � la dimension en ordonn�e
	 */
	public int getDimY() {
		return this.dimY;
	}
	
	/**
	 * Renvoie les dimensions du plateau de jeu sous la forme d'une
	 * cha�ne de caract�res.
	 * @return Une cha�ne contenant la dimension en abscisse et en ordonn�e
	 */
	public String toString() {
		return "Dimension en abscisse (dimX) : " + this.dimX
				+ "\nDimension en ordonn�e (dimY) : " + this.dimY;
	}
	
}


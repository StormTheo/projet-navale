/* Plateau.java							22 avril 2019
 * INFO 1 - Groupe de projet Bataille Navale
 */

package files;

import java.util.ArrayList;
import java.util.List;
import files.Bateau;

/**
 * Classe qui initialise une plateau de jeu pour la bataille navale. Le plateau
 * est un repère ayant en abscisse des lettres (de A à Z max) et en ordonnée des
 * chiffres (de 1 à 26 max). On peut donc voir la formation de cases qui
 * servirons de coordonnées (ex : C14). Les principales opérations sont : - Deux
 * constructeurs dont l'un pour l'initialisation par défaut - Une méthode pour
 * construire le plateau - Un accesseur sur la dimension en abscisse - Un
 * accesseur sur la dimension en ordonnée - Une méthode toString
 * 
 * @author Nicolas Couffin
 * @version 1.O
 */

public class Plateau {

    /**
     * Dimension par défaut attribuée aux dimensions en abscisse et en ordonnée.
     */
    private static final int DIM_DEFAUT = 12;

    /** Dimension minimale */
    private static final int DIM_MIN = 2; // Minimum requis pour avoir au moins une case de jeu sur le plateau.

    /** Dimension maximale */
    private static final int DIM_MAX = 27; // Maximum par rapport au nombre de lettres dans l'alphabet + la première
                                           // case vide

    /** Dimension en abscisse */
    private int dimX;

    /** Dimension en ordonnée */
    private int dimY;

    /** Liste contenant tous les bateaux, composant une flotte */
    private List<Bateau> flotte = new ArrayList<Bateau>();
    
    private int[][] grille;

    /**
     * Constructeur par défaut qui initialise les dimensions avec la valeur par
     * défaut
     */
    public Plateau() {
        dimX = DIM_DEFAUT;
        dimY = DIM_DEFAUT;
        grille = new int[dimY][dimX];
        for (int tailleX = 0; tailleX < dimX; tailleX++) {
        	for(int tailleY = 0; tailleY < dimY; tailleY++) {
        		grille[tailleX][tailleY] = -1;
        	}
        }
    }

    /**
     * Constructeur pour initialiser avec les valeurs en arguments.
     * 
     * @param xDim entier contenant la dimension en abscisse
     * @param yDim entier contenant la dimension en ordonnée
     */
    public Plateau(int xDim, int yDim) {
        this();
        /* Vérification des paramètres */
        if (	yDim >= DIM_MIN && yDim <= DIM_MAX 
        	&& 	xDim >= DIM_MIN && xDim <= DIM_MAX) {

            dimX = xDim;
            dimY = yDim;
            grille = new int[dimY][dimX];
            for (int tailleX = 0; tailleX < dimX; tailleX++) {
            	for(int tailleY = 0; tailleY < dimY; tailleY++) {
            		grille[tailleX][tailleY] = -1;
            	}
            }
        }
    }
    
    /**
     * Permet d'ajouter les coordonnées d'un bateau sur le plateau.
     * @param colonne N° de la colonne 
     * @param ligne   N° de la ligne
     */
    public void setGrille(int colonne, int ligne, int idBateau) {
    	grille[ligne][colonne] = idBateau;
    }
    
    public int getGrille(int colonne, int ligne) {
    	return grille[ligne][colonne];
    }
    
    /**
     * Accesseur sur la dimension en abscisse
     * 
     * @return un entier égal à la dimension en abscisse
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Accesseur sur la dimension en ordonnée
     * 
     * @return un entier égal à la dimension en ordonnée
     */
    public int getDimY() {
        return dimY;
    }

    /**
     * retourne la liste contenant tout les objets Bateau créés
     * 
     * @return flotte
     */
    public List<Bateau> getFlotte() {
        return flotte;
    }

    /**
     * permet d'ajouter un bateau.
     * 
     * @param bateau
     */
    public void setFlotte(Bateau bateau) {
        flotte.add(bateau);
    }

    /**
     * vide la liste des objets créés
     */
    public void clearFlotte() {
        flotte.clear();
    }
    
    public void afficherGrille() {
    	String abscisse = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	System.out.print("  |");
    	for (int taille = 0; taille < dimX; taille++) {
    		System.out.print(abscisse.substring(taille,taille+1) + "|");
    	}
    	System.out.println();
    	for (int tailleX = 0; tailleX < dimX; tailleX++) {
    		if (tailleX < 10) {
    			System.out.print(tailleX + " |");
    		} else {
    			System.out.print(tailleX + "|");
    		}
        	for(int tailleY = 0; tailleY < dimY; tailleY++) {
        		if (grille[tailleX][tailleY] != -1) {
        			System.out.print("X|");
        		} else {
        			System.out.print(" |");
        		}
        	}
        	System.out.println();
        } 
    	System.out.println();
    }

    /**
     * Renvoie les dimensions du plateau de jeu sous la forme d'une chaîne de
     * caractères.
     * 
     * @return Une chaîne contenant la dimension en abscisse et en ordonnée
     */
    public String toString() {
        return "Dimension en abscisse (dimX) : " + (dimX)
        		+ "\nDimension en ordonnée (dimY) : " + (dimY) + " (0 - " + (dimY-1) + ")";
    }
    
}

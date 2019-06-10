/* Plateau.java							22 avril 2019
 * INFO 1 - Groupe de projet Bataille Navale
 */

package files;

import java.util.ArrayList;
import java.util.List;
import files.Bateau;

/**
 * Classe qui initialise une plateau de jeu pour la bataille navale. Le plateau
 * est un rep�re ayant en abscisse des lettres (de A � Z max) et en ordonn�e des
 * chiffres (de 1 � 26 max). On peut donc voir la formation de cases qui
 * servirons de coordonn�es (ex : C14). Les principales op�rations sont : - Deux
 * constructeurs dont l'un pour l'initialisation par d�faut - Une m�thode pour
 * construire le plateau - Un accesseur sur la dimension en abscisse - Un
 * accesseur sur la dimension en ordonn�e - Une m�thode toString
 * 
 * @author Nicolas Couffin
 * @version 1.O
 */

public class Plateau {

    /**
     * Dimension par d�faut attribu�e aux dimensions en abscisse et en ordonn�e.
     */
    private static final int DIM_DEFAUT = 12;

    /** Dimension minimale */
    private static final int DIM_MIN = 2; // Minimum requis pour avoir au moins une case de jeu sur le plateau.

    /** Dimension maximale */
    private static final int DIM_MAX = 27; // Maximum par rapport au nombre de lettres dans l'alphabet + la premi�re
                                           // case vide

    /** Dimension en abscisse */
    private int dimX;

    /** Dimension en ordonn�e */
    private int dimY;

    /** Liste contenant tous les bateaux, composant une flotte */
    private List<Bateau> flotte = new ArrayList<Bateau>();

    /** TODO commenter le r�le du champ (attribut, r�le associatif...) */
    private int[][] grille;

    /**
     * Constructeur par d�faut qui initialise les dimensions avec la valeur par
     * d�faut
     */
    public Plateau() {
        dimX = DIM_DEFAUT;
        dimY = DIM_DEFAUT;
        grille = new int[dimY][dimX];
        for (int tailleX = 0; tailleX < dimX; tailleX++) {
            for (int tailleY = 0; tailleY < dimY; tailleY++) {
                grille[tailleX][tailleY] = -1;
            }
        }
    }

    /**
     * Constructeur pour initialiser avec les valeurs en arguments.
     * 
     * @param xDim entier contenant la dimension en abscisse
     * @param yDim entier contenant la dimension en ordonn�e
     */
    public Plateau(int xDim, int yDim) {
        this();
        /* V�rification des param�tres */
        if (yDim >= DIM_MIN && yDim <= DIM_MAX && xDim >= DIM_MIN && xDim <= DIM_MAX) {

            dimX = xDim;
            dimY = yDim;
            grille = new int[dimX][dimY];
            for (int tailleY = 0; tailleY < dimY; tailleY++) {
                for (int tailleX = 0; tailleX < dimX; tailleX++) {
                    grille[tailleX][tailleY] = -1;
                }
            }
        }
    }

    /**
     * Permet d'ajouter les coordonn�es d'un bateau sur le plateau.
     * 
     * @param colonne  N� de la colonne
     * @param ligne    N� de la ligne
     * @param idBateau id du bateau dans la Liste
     */
    public void setGrille(int colonne, int ligne, int idBateau) {
        grille[ligne][colonne] = idBateau;
    }

    /**
     * TODO commenter le r�le de cette m�thode
     * 
     * @param colonne
     * @param ligne
     * @return l'id du bateau si il y en a un de plac�, sinon, renvoie
     */
    public int getGrille(int colonne, int ligne) {
        return grille[ligne][colonne];
    }

    /**
     * Accesseur sur la dimension en abscisse
     * 
     * @return un entier �gal � la dimension en abscisse
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Accesseur sur la dimension en ordonn�e
     * 
     * @return un entier �gal � la dimension en ordonn�e
     */
    public int getDimY() {
        return dimY;
    }
  
    /**
     * retourne la liste contenant tout les objets Bateau cr��s
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

    public void ajouterBateau(Bateau bateau) {
        flotte.add(bateau);
    }

    /**
     * vide la liste des objets cr��s
     */
    public void clearFlotte() {
        flotte.clear();
    }

    /* cheat permet d'afficher la map avec les bateaux apparents */
    /**
     * Affiche le plateau de jeu dans la console quand le joueur tape "cheat" au
     * lieu des coordonn�es
     * 
     * @param cheat d�termine si on doit afficher la position des bateaux ou non
     */
    public void afficherGrille(boolean cheat) {
        String abscisse = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.print("  |");
        for (int taille = 0; taille < dimY; taille++) {
            System.out.print(abscisse.substring(taille, taille + 1) + "|");
        }
        System.out.println();
        for (int tailleX = 0; tailleX < dimX; tailleX++) {
            if (tailleX < 10) {
                System.out.print(tailleX + " |");
            } else {
                System.out.print(tailleX + "|");
            }
            for (int tailleY = 0; tailleY < dimY; tailleY++) {
                if (!cheat && grille[tailleX][tailleY] >= -1) {
                    System.out.print(" |");
                } else if (!cheat && grille[tailleX][tailleY] == -2) {
                    System.out.print("X|");
                } else if (cheat && (grille[tailleX][tailleY] >= 0 || grille[tailleX][tailleY] == -2)) {
                    System.out.print("X|");
                } else if (!cheat) {
                    System.out.print("O|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * V�rifie la disponibilit� d'une case et de ses alentours.
     * 
     * @param coordY l'abscisse du point � v�rifier
     * @param coordX l'ordonn�e du point � v�rifier
     * @return true si la case et ses alentours sont libres
     * @throws IllegalArgumentException quand l'une des coordonn�es est hors du
     *                                  plateau
     */
    public boolean verifierCoordsLibres(int coordX, int coordY) throws IllegalArgumentException {

        if (coordY >= dimX || coordX >= dimY) {
            throw new IllegalArgumentException("Coordonn�es hors du plateau");
        }

        boolean resultat = false;

        /* v�rification de la case voulue */
        if (grille[coordY][coordX] == -1) {
            resultat = true;
        } else {
            return false; // pas besoin de continuer
        }

        /* V�rification du point en haut a gauche */
        if (coordY > 0 && coordX > 0 && grille[coordY - 1][coordX - 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* V�rification du point en haut � droite */        
        if (coordY < dimX - 1 && coordX > 0 && grille[coordY + 1][coordX - 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* v�rification du point en bas a gauche */
        if (coordY > 0 && coordX < dimY - 1 && grille[coordY - 1][coordX + 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* v�rification du point en bas � droite */
        if (coordY < dimX - 1 && coordX < dimY - 1 && grille[coordY + 1][coordX + 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* v�rification du point au dessus */
        if (coordX > 0 && grille[coordY][coordX - 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* v�rification du point au dessous */
        if (coordX < dimY - 1 && grille[coordY][coordX + 1] == -1) {
            resultat = true;
        } else {
            return false;
        }

        /* v�rification du point � gauche */
        if (coordY > 0 && grille[coordY - 1][coordX] == -1) {
            resultat = false;
        } else {
            return false;
        }

        /* v�rification du point � droite */
        if (coordY < dimX - 1 && grille[coordY + 1][coordX] == -1) {
            resultat = true;
        } else {
            return false;
        }

        return resultat;
    }
    


    /**
     * Effectue le placement des bateaux sur le plateau de jeu
     * 
     * @param direction   la direction dans laquelle placer le bateau : H-B (1) ou G-D (2)
     * @param sens        le sens dans lequel est plac� le bateau : 1 = H/G; 2 = B/D
     * @param coordX      l'abscisse du bateau � placer
     * @param coordY      l'ordon�e du bateau � placer
     * @param indexBateau l'index du bateau dans la liste
     * @return true si le bateau a pu �tre plac�, false sinon
     */
    public boolean placement(int direction, int sens, int coordX, int coordY, int indexBateau) {
        boolean placeLibre = true;
        
        int tailleBateau = this.getFlotte().get(indexBateau).getTaille();
        
        // Vers le haut
        if (direction == 1 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && this.verifierCoordsLibres(coordX, coordY - i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    this.setGrille(coordX, coordY - i, indexBateau);
                }
            }
        // Vers le bas
        } else if (direction == 1 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && this.verifierCoordsLibres(coordX, coordY + i);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    this.setGrille(coordX, coordY + i, indexBateau);
                }
            }
        // Vers la gauche
        } else if (direction == 2 && sens == 1) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre = placeLibre && this.verifierCoordsLibres(coordX - i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    this.setGrille(coordX - i, coordY, indexBateau);
                }
            }
        // Vers la droite
        } else if (direction == 2 && sens == 2) {
            for (int i = 0; i < tailleBateau; i++) {
                placeLibre =  placeLibre && this.verifierCoordsLibres(coordX + i, coordY);
            }
            if (placeLibre) {
                for (int i = 0; i < tailleBateau; i++) {
                    this.setGrille(coordX + i, coordY, indexBateau);
                }
            }
        }

        return placeLibre; // Si la place est libre, alors on a pu placer le bateau

    }

    /**
     * Renvoie les dimensions du plateau de jeu sous la forme d'une cha�ne de
     * caract�res.
     * 
     * @return Une cha�ne contenant la dimension en abscisse et en ordonn�e
     */
    public String toString() {
        return "Dimension en abscisse (dimX) : " + (dimX) + "\nDimension en ordonn�e (dimY) : " + (dimY) + " (0 - "
                + (dimY - 1) + ")";
    }
}

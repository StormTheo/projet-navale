/*
 * Bateau.java                                                   28/04/2019
 * IUT info1 2018-2019 groupe 2, pas de droits : ni copyright
 */

package files;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe Bateau permettant de créer des bateaux de 
 * @author INFO 1
 */
public class Bateau {
    
    /** nom par défaut d'un navire */
    private static final String NOM_DEFAUT =  "Vedette";
    
    /** taille par défaut de ce navire */
    private static final int TAILLE_DEFAUT = 2;
    
    /** Nom du bateau (ex : Sous-marin, porte-avions, destroyer etc...) */
    private String nom;

    /** Taille du bateau */
    private int taille;

    /** Vie restante du bateau */
    private int vie;

    /** Nombre de touches sur le bateau */
    private int nbTouche;

    /** Positions du bateau */
    private String pos[][];
    
    /** Servira pour placer les bateaux et parcourir horizontalement la mer */
    private static int i;
    /** Servira pour placer les bateaux et parcourir verticalement la mer */
    private static int j;
 
    

    /** Liste contenant tous les bateaux, composant une flotte */
    private static List<Bateau> flotte = new ArrayList<Bateau>();
    
    /**
     * TODO commenter le rôle de ce constructeur
     */
    public Bateau() {
        this.nom = NOM_DEFAUT;
        this.taille = TAILLE_DEFAUT;
        this.vie = taille;
        this.nbTouche = 0;
        this.i = 0;
        this.j = 0;
        this.pos = new String[taille][taille];
        flotte.add(this);
    }

    /**
     * TODO idem que le précédent, commenter le rôle de ce constructeur
     * @param nom
     * @param taille
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        this.vie = taille;
        this.nbTouche = 0;
        this.i = 0;
        this.j = 0;
        this.pos = new String[taille][taille];
        flotte.add(this);
    }

    /**
     * TODO commenter l'état initial atteint
     * @param nom
     * @param taille
     * @param x
     * @param y
     */
    public Bateau(String nom, int taille, char x, String y) {
        this.nom = nom;
        this.taille = taille;
        this.vie = taille;
        this.nbTouche = 0;
        this.i = 0;
        this.j = 0;
        String chaineX = Integer.toString(x);
        this.pos = new String[2][taille];
        this.pos[0][i] = chaineX;
        this.pos[1][j] = y;
        flotte.add(this);
    }
    
    /**
     * Retourne le nom du bateau
     * @return nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la taille du bateau.
     * @return taille
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Ajoute une touche au bateau et teste s'il est seulement "touché" ou "coulé"
     * Retourne true s'il est coulé ou faux (false) s'il est simplement touché
     * @return true ou false
     */
    public boolean ajoutTouche() {
        if (this.nbTouche+1 == this.vie) {
            this.nbTouche ++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Place un point donné dans le tableau 
     * contenant les positions de ce bateau.
     * Ici le point x ( abscisse ).
     * @param position Un point contenant d'abcisse du bateau 
     */
    public void SetPositionHorizontale(char position) {
        if (i < vie) {
            pos[0][i] = Integer.toString(position);
            i++;
        }
        if (i >= vie) {
            i = 0;
        }
    }

    /**
     * Place un point donné dans le tableau 
     * contenant les positions de ce bateau.
     * Ici le point y ( ordonnée ).
     * @param position
     */
    public void SetPositionVerticale(int position) {
        /* Le nombre de point doit être inférieur à la taille du bateau */
        if(j < vie) {
            pos[1][j] = Integer.toString(position);
            j++;
        }
        if(j >= vie) {
            j = 0;
        }
    }

    /**
     * Retourne le tableau des différentes positions des cases du bateau
     * @return pos
     */
    public String[][] getPositions() {
        return this.pos;
    }
    
    /**
     * affiche la position des bateaux placés
     */
    public void afficherPositions() {
        System.out.println("début");
        for (int x = 0; x < pos.length; x++) {
            for (int y = 0; y < pos[x].length; y++) {
                System.out.print(pos[x][y] + ',');
<<<<<<< HEAD
                //if(x == pos.length - 1) {
                    //System.out.println();
                //}
            }
        }
        System.out.println("\nfin\n");
=======
                if(x == pos.length - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println("fin");
>>>>>>> 0b68e56267f09a97d429ba115b84abbc7d435e1d
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
     * vide la liste des objets créés
     */
    public static void clearFlotte() {
        flotte.clear();
    }
    
    /**
     * Renvoie une chaîne de caractère avec le nom et 
     * la taille de ce bateau
     */
    public String toString() {
        return "Nom : " + this.getNom() 
                + ", Taille : " + this.getTaille() + "\n";  
    } 
    
}

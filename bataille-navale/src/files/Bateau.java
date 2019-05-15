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


    /** Positions du bateau */
    private String pos[][];
    
    /** Servira pour placer les bateaux et parcourir horizontalement la mer */
    private static int i;
    /** Servira pour placer les bateaux et parcourir verticalement la mer */
    private static int j;
    
    /** true si le bateau est en vie, false si sa vie <= 0 */
    private boolean etat;
 
    

    /** Liste contenant tous les bateaux, composant une flotte */
    private static List<Bateau> flotte = new ArrayList<Bateau>();
    
    /**
     * constructeurs de l'objet bateau avec les informations par défaut.
     */
    public Bateau() {
        this.nom = NOM_DEFAUT;
        this.taille = TAILLE_DEFAUT;
        this.vie = taille;
        this.i = 0;
        this.j = 0;
        this.pos = new String[2][taille];
        flotte.add(this);
        this.etat = true;
    }

    /**
     *  constructeur de l'objet bateau, avec les informations nom et taille
     * @param nom
     * @param taille
     */
    public Bateau(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        this.vie = taille;
        this.i = 0;
        this.j = 0;
        this.pos = new String[2][taille];
        flotte.add(this);
        this.etat = true;
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
     * Renvoie le nombre de point de vie restant d'un bateau.
     * @return un int représentant le nbr de points de vie.
     */
    public int getVie() {
        return this.vie;
    }
    
    
    /**
     * @return la valeur de état
     */
    public boolean getEtat() {
        return this.etat;
    }

    /**
     * @param etat la nouvelle valeur de etat
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * Ajoute une touche au bateau et teste s'il est seulement "touché" ou "coulé"
     * @return true s'il est coulé ou false s'il est simplement touché
     */
    public boolean toucher() {
        /* n'est pas sur le point d'être détruit */
        if (this.vie >= 2) {
            this.vie--;
        }
        /* vas être détruit */
        else if(this.vie == 1 && this.getEtat()) {
            /* 
             * on enlève sa dernière vie et on "l'élimine" 
             * en passant son état à false
             */
            this.vie--;
            this.setEtat(false);
        }
        return this.vie == 0;
    }

    
    /**
     * Place un point donné dans le tableau 
     * contenant les positions de ce bateau.
     * Ici le point x ( abscisse ).
     * @param position Un point contenant d'abcisse du bateau 
     */
    public void SetPositionHorizontale(char position) {
        if (i < vie) {
            pos[0][i] = Character.toString(position);
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
                //if(x == pos.length - 1) {
                    //System.out.println();
                //}
            }
        }
        System.out.println("\nfin\n");
    }
    
    /**
     * verifie si les coordonnées arguments correspondent 
     * aux coordonnées d'un bateau
     * @param x char coordonnée
     * @param y int coordonnée
     * @return indexBateau, l'index du bateau qui a été touché, 
     *                      -1 si aucun bateau n'a été touché.
     */
    public static int verifTir(char x, int y) {
        Bateau bateauActuel = null;
        String[][] posBateau = null;
        boolean coordTrouve = false;
        int indexBateau;
        /* récupère chaque bateau un à un */
        for (indexBateau = 0; !coordTrouve && indexBateau < flotte.size(); indexBateau++) {
            bateauActuel = flotte.get(indexBateau);
            posBateau = bateauActuel.getPositions();
            coordTrouve = false;
            /* compare chaque position à l'argument */
            for (int indexPos = 0; !coordTrouve && indexPos < posBateau[0].length
                    ; indexPos++) {
                /* si coordonnées correspondent */
                if ( posBateau[0][indexPos] != null
                        && (x == posBateau[0][indexPos].charAt(0))
                        && ( y == Integer.parseInt(posBateau[1][indexPos]) )
                        && bateauActuel.getEtat()) {
                    
                    coordTrouve = true;
                    /* coordonnées modifiées pour éviter de repasser dans la boucle
                     * ou de retomber sur ces coordonnées.
                     */
                    posBateau[0][indexPos] = null;
                    posBateau[1][indexPos] = null;
                }
            }
        }
        /* si pas de correspondance */
        if (!(coordTrouve && bateauActuel.getEtat())) {
            indexBateau = 0;
        }
        /* -1 de décalage par rapport à l'incrémentation de la boucle */
        return indexBateau-1;
    }
    
    /**
     * recherche si il reste dans bateau étant encore en vie ( vie > 0 )
     * @return true si il reste des bateaux, false sinon
     */
    public static boolean bateauRestant() {
        boolean reste = false;
        for (int index = 0; !reste && index < flotte.size(); index++) {
            reste = flotte.get(index).getEtat();
        }
        return reste;
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

/*
 * IA.java                                                   19 mai 2019
 * IUT info1 2018-2019 groupe 1, pas de droits : ni copyright ni copyleft
 */

package files;

import java.util.Random;

import files.BatailleNavale;

/**
 * Classe permettant de cr�er une IA qui jouera contre le joueur.
 * @author INFO1
 *
 */
public class IA {
	
	/** Niveau par d�faut de l'ordinateur */
	int NIVEAU_DEFAUT = 1;
	
    /** Niveau de l'ordinateur */
    private static int niveau;
	
    
    /**
     * Constructeur de l'objet IA, avec le niveau par d�faut
     */
    public IA() {
    	this.niveau = NIVEAU_DEFAUT;
    }
    
    
	/**
     * Constructeur de l'objet IA, avec le niveau
     * @param niveau
     */
    public IA(int niveau) {
    	this.niveau = niveau;
    }
    
    
    /**
     * Retourne le niveau de l'ordinateur
     *  
     * @return niveau de l'ordinateur
     */
    public static int getNiveau() {
		return niveau;
	}


	/**
     * Lance la v�rification des coordonn�es entr�es, et affiche le r�sultat du tir.
     * 
     * @param plateau 
     */
    public static void tirIA(Plateau plateau) {
    	
    	/* Coordonn�es du tir */
    	int x;
    	int y;
    	
    	/* 
    	 * Indique si un bateau a �t� touch�, donc trouv�
    	 * Servira pour l'IA de niveau 2
    	 */
    	/*boolean bateauTouch� = false;*/
    	
    	/* 
    	 * TODO supprimer ces 3 instructions lors de
    	 * l'impl�mentation de l'IA de niveau 2
    	 */
    	Random random = new Random();
    	x = random.nextInt(plateau.getDimX());
    	y = random.nextInt(plateau.getDimY());
    	
    	/*
    	if (niveau == 1 || bateauTouch� == false) {
    		Random random = new Random();
        	
	    	x = random.nextInt(plateau.getDimX());
	    	y = random.nextInt(plateau.getDimY());
    	} else if (bateauTouch� == true) {
    		do {
    			if (x == 0) {
    				x++;
    			} else if (x == 12) {
    				x--;
    			} else if (y == 0) {
    				y++;
    			} else if (y == 12) {
    				y--;
    			} else {
    				
    			}
    		} while (bateauTouch�);
    		
    	}
    	*/

//    	BatailleNavale.tir(x, y, plateauJoueur);
    }
    
    /* TODO Adapter BatailleNavale pour faire jouer tour � tour le joueur et l'IA */
    
}

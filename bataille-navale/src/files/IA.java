/*
 * IA.java                                                   19 mai 2019
 * IUT info1 2018-2019 groupe 1, pas de droits : ni copyright ni copyleft
 */

package files;

import java.util.Random;

import files.BatailleNavale;

/**
 * Classe permettant de créer une IA qui jouera contre le joueur.
 * @author INFO1
 *
 */
public class IA {
	
	/** Niveau par défaut de l'ordinateur */
	int NIVEAU_DEFAUT = 1;
	
    /** Niveau de l'ordinateur */
    private static int niveau;
	
    
    /**
     * Constructeur de l'objet IA, avec le niveau par défaut
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
     * Lance la vérification des coordonnées entrées, et affiche le résultat du tir.
     * 
     * @param plateau 
     */
    public static void tirIA(Plateau plateau) {
    	
    	/* Coordonnées du tir */
    	int x;
    	int y;
    	
    	/* 
    	 * Indique si un bateau a été touché, donc trouvé
    	 * Servira pour l'IA de niveau 2
    	 */
    	/*boolean bateauTouché = false;*/
    	
    	/* 
    	 * TODO supprimer ces 3 instructions lors de
    	 * l'implémentation de l'IA de niveau 2
    	 */
    	Random random = new Random();
    	x = random.nextInt(plateau.getDimX());
    	y = random.nextInt(plateau.getDimY());
    	
    	/*
    	if (niveau == 1 || bateauTouché == false) {
    		Random random = new Random();
        	
	    	x = random.nextInt(plateau.getDimX());
	    	y = random.nextInt(plateau.getDimY());
    	} else if (bateauTouché == true) {
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
    		} while (bateauTouché);
    		
    	}
    	*/

//    	BatailleNavale.tir(x, y, plateauJoueur);
    }
    
    /* TODO Adapter BatailleNavale pour faire jouer tour à tour le joueur et l'IA */
    
}

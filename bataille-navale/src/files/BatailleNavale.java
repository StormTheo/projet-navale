/*
 * BatailleNavale.java                                                   1 mai 2019
 * IUT info1 2018-2019 groupe 1, pas de droits : ni copyright ni copyleft
 */

package files;

import java.util.Random;
import java.util.Scanner;

import files.Affichage;
import files.Bateau;
import files.Plateau;
import files.IA;

/**
 * Classe principale du jeu
 * 
 * @author IUT INFO1 groupe 1
 *
 */
public class BatailleNavale {

    /** Plateau du joueur */
    private static Plateau plateauJoueur;
    
    /** Plateau de l'ordinateur */
    private static Plateau plateauIA;
    
    /** Indique si c'est au tour du joueur */
    private static boolean tourJoueur;

    /**
     * Affiche le Menu principal et en fonction du choix, renvoie � la m�thode
     * appropri�e
     */
    public static void menu() {

        boolean nok;
        String reponse;
        char reponseChar;

        Affichage.menuPrincipal();
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while (!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar >= 'a' ? reponseChar - 32 : reponseChar);
        switch (reponseChar) {
        case 'A':
            aide();
            break;
        case 'Q':
            System.out.println("Vous avez quitt� le jeu.");
            System.exit(0);
            break;
        }
    }

    /**
     * Affiche l'aide et effectue l'action appropri�e en fonction du choix de
     * l'utilisateur
     */
    public static void aide() {
        String reponse;
        boolean nok;
        char reponseChar;
        Affichage.afficherAide();
        /* saisie du choix de l'utilisateur */
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while (!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar >= 'a' ? reponseChar - 32 : reponseChar);
        if (reponseChar == 'A') {
            aide();
        } else if (reponseChar == 'Q') {
            System.out.println("Vous avez quitt� le jeu.");
            System.exit(0);
        }

        // else lance le jeu dans le main
    }

    /**
     * r�cup�re la cha�ne de caract�res entr�e par l'utilisateur
     * 
     * @return la cha�ne de caract�res que l'utilisateur a entr�e
     */
    public static String entreeUtilisateur() {
        /*
         * scanner r�cup�rant les choix et r�ponses de l'utilisateur
         */
        Scanner entree = new Scanner(System.in);

        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;

        nok = false;
        do {
            /* raz de la r�ponse */
            reponse = "";
            System.out.print("\n       ====> ");

            if (entree.hasNext()) {
                reponse = entree.nextLine();
                nok = true;
            }
        } while (!nok);
        return reponse;
    }

    /**
     * Effectue le placement d'un nombre donn� de bateaux de taille al�atoire
     * (comprise entre 1 et 4) sur le plateau de jeu.
     * 
     * @param plateau le plateau sur lequel placer les bateaux
     * @throws IllegalArgumentException si il est impossible de placer les bateaux
     *                                  (ex: plateau trop petit)
     */
    public static void placement(Plateau plateau) throws IllegalArgumentException {
        Random random = new Random();
        /*
         * Cha�ne comprenant les coordonn�es possibles choix d'un au hasard pour placer
         * un bateau
         */
        int coordBateauX;
        int coordBateauY;
        int alignement;
        int direction; // direction du bateau comprise entre 1 et 4
        // 1 = haut; 2 = gauche; 3 = bas; 4 = droite
        int nbBateau = plateau.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
        	do {
        		// g�n�ration al�atoire des positions x et y des bateaux
        		coordBateauX = random.nextInt(plateau.getDimX());
        		coordBateauY = random.nextInt(plateau.getDimY());
        		/* placement horizontal / vertical */
        		alignement = random.nextInt(2)+1;
        		// direction du bateau al�atoire
        		direction = random.nextInt(2)*2-1;

        	} while (!(placement(plateau, direction, alignement, coordBateauX, coordBateauY, i)));
            
        }
        plateau.afficherGrille(false);
    }
    
    public static boolean placement(Plateau plateau, int direction, int alignement,
    							int coordBateauX, int coordBateauY, int i) {
    	int directionBoucle = 0;
    	Bateau bateauActuel;
    	bateauActuel = plateau.getFlotte().get(i);
    	if (alignement == 1 && (bateauActuel.getTaille()*direction+coordBateauX < 0
    						||  bateauActuel.getTaille()*direction+coordBateauX >= plateau.getDimX())) {
    		return false;
    	}
    	if (alignement == 2 && (bateauActuel.getTaille()*direction+coordBateauY < 0
    						||  bateauActuel.getTaille()*direction+coordBateauY >= plateau.getDimY())) {
    		return false;
    	}
    	plateau.setGrille(coordBateauX, coordBateauY, i);   	
    	for (int index = 1; index < bateauActuel.getTaille(); index++) {
    		if(alignement == 1) {
    			directionBoucle += direction;
    			plateau.setGrille(coordBateauX+directionBoucle, coordBateauY, i);
    		} else if (alignement == 2) {
    			directionBoucle+=direction;
    			plateau.setGrille(coordBateauX, coordBateauY+directionBoucle, i);
    		}
    	}
		return true;
    }

    /**
     * r�cup�ration des coordonn�es qu'entre le joueur et placement de celles-ci
     * dans deux variables x et y de type String.
     * 
     * @param plateauJeu
     */
    public static void recupCoord(Plateau plateau) {
    	
        String coordonnees;
        
        /* coordonn�es s�par�s en abscisse et ordonn�e */
        char x = '0';
        int y;
        String chaineY = null;
        
        /* permet de mettre la condition de chaine valide */
        boolean verification;
        
        System.out.print("\nEntrez les coordonn�s sous la forme : caract�re," + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        
        if (coordonnees.equals("cheat")) {
        	plateauIA.afficherGrille(true);
        	recupCoord(plateau);
        }
        
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caract�re minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' && coordonnees.charAt(placement) <= 'z')
                    || (coordonnees.charAt(placement) >= 'A' && coordonnees.charAt(placement) <= 'Z')) {

                /* x = morceau de cha�ne avec le caract�re */
                x = coordonnees.charAt(placement);
                /* mise en majuscule du caract�re */
                x = (char) (x >= 'a' ? (x - 32) : x);
            }
            if (coordonnees.charAt(placement) >= '0' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur num�rique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement + 1);
                } else {
                    chaineY += coordonnees.substring(placement, placement + 1);
                }
            }
        }
              
        verification = (x != '0' && chaineY != null);
        if (verification) {
        	/* les positions en nombre pour v�rifier dans le plateau */
        	int posX=x-'A';
        	int posY=Integer.parseInt(chaineY);
        	
            /* Si x et y sont existant dans le plateau. */
            verification = (x <= plateau.getDimX() + 'A' && x >= 'A') && (posY >= 0 && posY < plateau.getDimY());
            if (verification) {
                System.out.println("x = " + x + "\ny = " + posY);
                tir(posX, posY, plateauIA);
            }
        }
        
        if (!verification) {
            System.out.println("Coordonn�es incorrect, votre plateau a une dimension x = " + plateau.getDimX()
                    + " y = " + plateau.getDimY());
            recupCoord(plateau);
        }
        
        
        
    }

    /**
     * Lance la v�rification des coordonn�es entr�es, et affiche le r�sultat du tir.
     * 
     * @param x coordonn�e entr�e par l'utilisateur pour l'abscisse
     * @param y coordonn�e entr�e par l'utilisateur pour l'ordonn�e
     * @param plateau sur lequel on effectue le tir
     */
    public static void tir(int x, int y, Plateau plateau) {
    	
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y, plateau);
        
        if (indexBateau >= 0) {
            bateauActuel = plateau.getFlotte().get(indexBateau);
            plateau.setGrille(x, y, -2);
            plateau.afficherGrille(false);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() + " coul� !");
            } else {
                System.out.println("touch� !");
            }
        } else {
        	if (indexBateau == -2) {
        		plateau.setGrille(x, y, -2);
        	} else {
        		plateau.setGrille(x, y, -3);
        	}
        	plateau.afficherGrille(false);
            System.out.println("aucun bateau touch� :(");
        }
        
        if (Bateau.bateauRestant(plateau) && tourJoueur) {
            tourJoueur = false;
            recupCoord(plateauIA);
        } else if (!tourJoueur) {
        	/* Si c'est au tour de l'IA */
        	Random random = new Random();
        	do {
        		x = random.nextInt(plateau.getDimX());
            	y = random.nextInt(plateau.getDimY());
        	} while (   plateauJoueur.getGrille(x, y) == -2
        			 || plateauJoueur.getGrille(x, y) == -3);
        	System.out.println("x : " + x + ", y : " + y);
        	tir(x, y, plateauJoueur);
        } else {
            System.out.println("\n====>Partie termin�e ! tous les bateaux ont �t� coul�s !");
        }
    }
    
    
    /**
     * Lancement des principales fonctions et cr�ations des objets
     * 
     * @param args non utilis�
     *
     */
    public static void main(String[] args) {
        menu();
        
        tourJoueur = true;
        
        /* cr�ation des plateaux de jeu */
        plateauJoueur = new Plateau();
        plateauIA = new Plateau();

        /* Liste des bateaux � placer sur le plateau */
        plateauJoueur.setFlotte(new Bateau("Chocapic", 4));
        plateauJoueur.setFlotte(new Bateau("sous-marineLePen", 3));
        plateauJoueur.setFlotte(new Bateau());
        plateauJoueur.setFlotte(new Bateau("aeroglisseur", 1));
        
        /* Liste des bateaux � placer sur le plateau */
        plateauIA.setFlotte(new Bateau("Porte-avions", 4));
        plateauIA.setFlotte(new Bateau("Destroyer", 3));
        plateauIA.setFlotte(new Bateau());
        plateauIA.setFlotte(new Bateau("Fregate", 1));
        
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJoueur.toString() + "\n");
        placement(plateauJoueur);
        System.out.println("Bateaux pr�sents sur le plateau du joueur");
        
        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < plateauJoueur.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauJoueur.getFlotte().get(aAfficher).toString());
        }
        
        
        /* affichage des infos du plateau de l'IA */
        System.out.println("\n" + plateauIA.toString() + "\n");
        placement(plateauIA);
        System.out.println("Bateaux pr�sents sur le plateau de l'IA");
        
        /* affichage des infos de chaque bateau de l'IA */
        for (int aAfficher = 0; aAfficher < plateauIA.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher + 1, plateauIA.getFlotte().get(aAfficher).toString());
        }
        
        
        recupCoord(plateauIA);
        
    }

}

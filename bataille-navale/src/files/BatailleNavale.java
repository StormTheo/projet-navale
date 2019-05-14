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

/**
 * Classe principale du jeu
 * @author IUT INFO1 groupe 1
 *
 */
public class BatailleNavale {
    
    /**
     * Affiche le Menu principal et en fonction du choix,
     * renvoie � la m�thode appropri�e
     */
    public static void menu() {
        
        boolean nok;
        String reponse;
        char reponseChar;
        
        Affichage.menuPrincipal();
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while(!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar>='a'?reponseChar-32:reponseChar);
        if (reponseChar == 'A' ) {
            aide();
        } else if (reponseChar == 'Q') {
            System.exit(0);
        }
    }

    
    /**
     * Affiche l'aide et effectue l'action appropri�e
     * en fonction du choix de l'utilisateur
     */
    public static void aide() {
        String reponse;
        boolean nok;
        
        Affichage.afficherAide(); 
        /* saisie du choix de l'utilisateur */
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while(!nok);
        
        if (reponse.charAt(0) == 'A') {
            aide();
        } else if (reponse.charAt(0) == 'Q') {
            // arr�t du programme
        }  
        // else lance le jeu dans le main
    }
    
    /**
     * r�cup�re la cha�ne de caract�res entr�e par l'utilisateur
     * @return la cha�ne de caract�res que l'utilisateur a entr�e
     */
    public static String entreeUtilisateur() {
        /* 
         * scanner r�cup�rant les choix et 
         * r�ponses de l'utilisateur 
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
        } while(!nok);
        return reponse;
    }
    
    /**
     * Effectue le placement d'un nombre donn� de bateaux de taille al�atoire
     * (comprise entre 1 et 4) sur le plateau de jeu.
     * @param plateau le plateau sur lequel placer les bateaux
     * @throws IllegalArgumentException si il est impossible de placer les bateaux (ex: plateau trop petit)
     */
    public static void placement(Plateau plateau) throws IllegalArgumentException{
        Random random = new Random();
        /* chaine comprenant les coordonn�es possibles 
         * choix d'un au hasard pour placer un bateau
         */
        String coordPossible = "abcdefghijklmnopqrstuvwxyz";
        char coordBateauX;
        /* sauvegarde du nombre affect� � une lettre */
        int nbrRandX;
        int coordBateauY;
        int direction; //direction du bateau comprise entre 1 et 4
                       // 1 = haut; 2 = gauche; 3 = bas; 4 = droite
        int nbBateau = Bateau.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            Bateau bateauxAPlacer = Bateau.getFlotte().get(i);
            // g�n�ration al�atoire des positions x et y des bateaux
            nbrRandX = random.nextInt(plateau.getDimX());
            coordBateauX = coordPossible.charAt(nbrRandX);
            System.out.println(coordBateauX); //DEBUG
            coordBateauY = random.nextInt(plateau.getDimY()) + 1;
            System.out.println(coordBateauY); //DEBUG
            // direction du bateau al�atoire
            direction = random.nextInt(4) + 1;
            System.out.println(direction); //debug
            // placer le bateau i; PENSER A LA DIRECTION !
            switch(direction) {
            case 1:
            	if (coordBateauY - bateauxAPlacer.getTaille() >= plateau.getDimY()) {
                    placementHaut(i, coordBateauX, coordBateauY);
                }
                break;
            case 2:
                if (coordBateauX + bateauxAPlacer.getTaille() <= plateau.getDimX()) {
                    placementDroite(i, coordBateauX, coordBateauY);
                }
                break;
            case 3:
                if (coordBateauY + bateauxAPlacer.getTaille() <= plateau.getDimY()) {
                    placementBas(i, coordBateauX, coordBateauY);
                }
                break;
            case 4:
                if (coordBateauX - bateauxAPlacer.getTaille() >= plateau.getDimX()) {
                    placementGauche(i, coordBateauX, coordBateauY);
                }
                break;
            }
            bateauxAPlacer.afficherPositions();
        }
    }
    
    
    /**
     * Effectue le placement d'un bateau dans le plateau.
     * Partant de (coordBateauX, coordBateauY) jusqu'a 
     * (coordBateauX, coordBateauY - bateau.getTaille())
     * @param index l'index du bateau dans la liste
     * @param coordBateauX la coordonn�e en abscisse du bateau a placer
     * @param coordBateauY la coordonn�e en ordonn�e du bateau a placer
     */
    private static void placementHaut(int index, char coordBateauX, int coordBateauY) {
    	Bateau bateauActuel;
        bateauActuel = Bateau.getFlotte().get(index);
        bateauActuel.SetPositionHorizontale(coordBateauX);
        bateauActuel.SetPositionVerticale(coordBateauY);
        /* placement des coordonn�es d�cr�mentant y pour allez vers le haut */
        for (int iterat = 1; iterat < bateauActuel.getTaille(); iterat++) {
            System.out.println("char � placer : " + (char) (coordBateauY - iterat));
            bateauActuel.SetPositionHorizontale((char) (coordBateauX));
            bateauActuel.SetPositionVerticale(coordBateauY - iterat);
        }
    }
    
    
    /**
     * Effectue le placement d'un bateau dans le plateau.
     * Partant de (coordBateauX, coordBateauY) jusqu'a 
     * (coordBateauX + bateau.getTaille(), coordBateauY)
     * @param index l'index du bateau dans la liste
     * @param coordBateauX la coordonn�e en abscisse du bateau a placer
     * @param coordBateauY la coordonn�e en ordonn�e du bateau a placer
     */
    private static void placementDroite(int index, char coordBateauX, int coordBateauY) {
        Bateau bateauActuel;
        bateauActuel = Bateau.getFlotte().get(index);
        bateauActuel.SetPositionHorizontale(coordBateauX);
        bateauActuel.SetPositionVerticale(coordBateauY);
        /* placement des coordonn�es incr�mentant x pour allez vers la droite */
        for (int iterat = 1; iterat < bateauActuel.getTaille(); iterat++) {
            System.out.println("char � placer : " + (char) (coordBateauX + iterat));
            bateauActuel.SetPositionHorizontale((char) (coordBateauX + iterat));
            bateauActuel.SetPositionVerticale(coordBateauY);
        }
    }
    
    
    /**
     * Effectue le placement d'un bateau dans le plateau.
     * Partant de (coordBateauX, coordBateauY) jusqu'a 
     * (coordBateauX, coordBateauY + bateau.getTaille())
     * @param index l'index du bateau dans la liste
     * @param coordBateauX la coordonn�e en abscisse du bateau a placer
     * @param coordBateauY la coordonn�e en ordonn�e du bateau a placer
     */
    private static void placementBas(int index, char coordBateauX, int coordBateauY) {
    	Bateau bateauActuel;
    	bateauActuel = Bateau.getFlotte().get(index);
    	bateauActuel.SetPositionHorizontale(coordBateauX);
    	bateauActuel.SetPositionVerticale(coordBateauY);
    	/* placement des coordonn�es incr�mentant y pour allez vers le bas */
    	for (int iterat = 1; iterat < bateauActuel.getTaille(); iterat++) {
    		System.out.println("char � placer : " + (char) (coordBateauY + iterat));
    		bateauActuel.SetPositionHorizontale((char) (coordBateauX));
    		bateauActuel.SetPositionVerticale(coordBateauY + iterat);
    	}
    }
    
    
    /**
     * Effectue le placement d'un bateau dans le plateau.
     * Partant de (coordBateauX, coordBateauY) jusqu'a 
     * (coordBateauX - bateau.getTaille(), coordBateauY)
     * @param index l'index du bateau dans la liste
     * @param coordBateauX la coordonn�e en abscisse du bateau a placer
     * @param coordBateauY la coordonn�e en ordonn�e du bateau a placer
     */
    private static void placementGauche(int index, char coordBateauX, int coordBateauY) {
        Bateau bateauActuel;
        bateauActuel = Bateau.getFlotte().get(index);
        bateauActuel.SetPositionHorizontale(coordBateauX);
        bateauActuel.SetPositionVerticale(coordBateauY);
        /* placement des coordonn�es d�cr�mentant x pour allez vers la gauche */
        for (int iterat = 1; iterat < bateauActuel.getTaille(); iterat++) {
            System.out.println("char � placer : " + (char) (coordBateauX - iterat));
            bateauActuel.SetPositionHorizontale((char) (coordBateauX - iterat));
            bateauActuel.SetPositionVerticale(coordBateauY);
        }
    }
    
    
    /**
     * r�cup�ration des coordonn�es qu'entre 
     * le joueur et placement de celles-ci dans deux variables
     *  x et y de type String.
     * @param plateauJeu 
     */
    public static void recupCoord(Plateau plateauJeu) {
        String coordonnees;
        /* coordonn�es s�par�s en 
         * abscisse et ordonn�e */
        char x = '0';
        int y;
        String chaineY = null;
        /* permet de mettre la condition de chaine valide */
        boolean verification;
        System.out.print("\nEntrez les coordonn�s sous la forme : caract�re,"
                        + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caract�re minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' 
                && coordonnees.charAt(placement) <= 'z')
                || ( coordonnees.charAt(placement) >= 'A'
                && coordonnees.charAt(placement) <= 'Z')) {
                
                /* x = morceau de cha�ne avec le caract�re */
                x = coordonnees.charAt(placement);
                /* mise en majuscule du caract�re */
                x = (char) (x >= 'a'?(x - 32):x);
            }
            if (coordonnees.charAt(placement) >= '0' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur num�rique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement+1);
                } else {
                    chaineY += coordonnees.substring(placement, placement+1);
                }
            }
        }
        verification = (x != '0' && chaineY != null);
        if (verification) {
            y = Integer.parseInt(chaineY); // passage de la valeur en String.
            /* Si x et y sont existant dans le plateau. */
            verification = (x <= plateauJeu.getDimX()+ 'A' && x >= 'A')
                            && (y > 0 && y <= plateauJeu.getDimY());
            if ( verification ) {
                System.out.println("x = " + x + "\ny = " + y); 
                tir(x,y);
            }
        }
        if (!verification) {
            System.out.println("Coordonn�es incorrect, votre plateau a une dimension x = "
                                + plateauJeu.getDimX() + " y = " + plateauJeu.getDimY());
            recupCoord(plateauJeu);
        }
    }
    
    /**
     * Cherche � verifier si les coordonn�es entr�es correspondent 
     * � l'emplacement d'un bateau.
     * @param x coordonn�e entr�e par l'utilisateur pour l'abscisse
     * @param y coordonn�e entr�e par l'utilisateur pour l'ordonn�e
     * @return 0 si le bateau n'est pas touch�, 
     *         1 si il est touch�, 
     *         2 si il est coul�.
     */
    public static int tir(char x, int y) {
        int etat = 0;
        Bateau elementBateau;
        String[][] posBateau;
        /* parcours des coordonn�es */
        int parcoursC;
        /* permet la sortie de la boucle */
        boolean toucher = false;
        
        for (int parcoursB = 0;parcoursB<Bateau.getFlotte().size() && !toucher; parcoursB++) {
            
            /* l'objet bateau actuellement v�rifi� */
            elementBateau = Bateau.getFlotte().get(parcoursB);
            
            /* le tableau contenant les coordonn�es de ce bateau */
            posBateau = elementBateau.getPositions();
            /*
             * tant que la taille est d�finie, et que les coordonn�es
             * ne correspondent pas, on fait tourner.
             */
            for (parcoursC = 0; parcoursC<posBateau[0].length 
                     && posBateau[0][0] != null
                     && (x != posBateau[0][0].charAt(0)
                     && y != Integer.parseInt(posBateau[0][0]));
                     parcoursC++);
            /* si touch� seulement */
            if (parcoursC == 1 && elementBateau.ajoutTouche()) {
                System.out.println("Toucher !");
                toucher = true;
                etat = 1;
            /* si couler */
            } else if (parcoursC == 1 && !elementBateau.ajoutTouche()) {
                System.out.println("Couler !");
                toucher = true;
                etat = 2;
            }
            /* 
             * sinon,message que rien n'a �t� touch� apr�s que tout ait
             * �t� v�rifi�
             */
            
        }
        if (etat == 0) {
            System.out.println("Aucun bateau touch� � cet emplacement");
        }
        return etat;
        //TODO recommencer l'affichage des infos, et saisie des coord
    }
    
    /**
     * Lancement des principales fonctions 
     * et cr�ations des objets
     * @param args non utilis�
     */
    public static void main(String[] args) {
        menu();
        
        /*cr�ation du plateau de jeu */
        Plateau plateauJeu;
        plateauJeu = new Plateau();
        
        /* Liste des bateaux � placer sur le plateau */
        new Bateau("Chocapic", 4);
        new Bateau("sous-marineLePen", 3);
        new Bateau();
        new Bateau("aeroglisseur", 2);
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJeu.toString() + "\n");
        placement(plateauJeu);
        // TODO placer les bateaux
        
        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < Bateau.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher+1,
                               Bateau.getFlotte().get(aAfficher).toString());
        }
        // TODO afficher informations de la partie en cours avant 
        // la demande des coordonn�es -> m�thode.
        recupCoord(plateauJeu);
    }

}

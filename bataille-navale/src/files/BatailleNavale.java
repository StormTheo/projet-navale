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
    
    /** Plateau du joueur */
    private static Plateau plateauJeu;

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
        switch (reponseChar) {
            case 'A':
                aide();
                break;
            case 'Q':
                System.out.println("Vous avez quitter le jeu.");
                System.exit(0);
                break;
        }
    }


    /**
     * Affiche l'aide et effectue l'action appropri�e
     * en fonction du choix de l'utilisateur
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
        } while(!nok);
        reponseChar = reponse.charAt(0);
        reponseChar = (char) (reponseChar>='a'?reponseChar-32:reponseChar);
        if (reponseChar == 'A') {
            aide();
        } else if (reponseChar == 'Q') {
            System.out.println("Vous avez quitter le jeu.");
            System.exit(0);
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
        String coordPossible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char coordBateauX;
        int nbrRandX; // sauvegarde du nombre affect� � une lettre
        int coordBateauY;
        int direction; //direction du bateau comprise entre 1 et 4
        // 1 = haut; 2 = gauche; 3 = bas; 4 = droite
        int nbBateau = Bateau.getFlotte().size();
        for (int i = 0; i < nbBateau; i++) {
            Bateau bateauxAPlacer = Bateau.getFlotte().get(i);
            // g�n�ration al�atoire des positions x et y des bateaux
            nbrRandX = random.nextInt(plateau.getDimX());
            coordBateauX = coordPossible.charAt(nbrRandX);
            System.out.println("X = " + coordBateauX); //DEBUG
            coordBateauY = random.nextInt(plateau.getDimY())+1;
            System.out.println("Y = " + coordBateauY); //DEBUG
            // direction du bateau al�atoire
            direction = random.nextInt(3) + 1;
            System.out.println("direction : " + direction); //debug
            // placer le bateau i; PENSER A LA DIRECTION !
            switch(direction) {
            case 1:
                if (coordBateauY - bateauxAPlacer.getTaille() > 0 ) {
                    System.out.println(" bateau : " + Bateau.getFlotte().get(i).getTaille());
                    placementHaut(i, coordBateauX, coordBateauY);
                } else {
                    placementBas(i, coordBateauX, coordBateauY);
                }
                break;
            case 2:
                if (nbrRandX + bateauxAPlacer.getTaille() <= plateau.getDimX()) {
                    System.out.println(" bateau : " + Bateau.getFlotte().get(i).getTaille());
                    placementDroite(i, coordBateauX, coordBateauY);
                } else {
                    placementGauche(i, coordBateauX, coordBateauY);
                }
                break;
            case 3:
                if (coordBateauY + bateauxAPlacer.getTaille() <= plateau.getDimY()) {
                    System.out.println(" bateau : " + Bateau.getFlotte().get(i).getTaille());
                    placementBas(i, coordBateauX, coordBateauY);
                } else {
                    placementHaut(i, coordBateauX, coordBateauY);
                }
                break;
            case 4:
                if (nbrRandX - bateauxAPlacer.getTaille() > 0) {
                    System.out.println(" bateau : " + Bateau.getFlotte().get(i).getTaille());
                    placementGauche(i, coordBateauX, coordBateauY);
                } else {
                    placementDroite(i, coordBateauX, coordBateauY);
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

        /* placement des coordonn�es en d�cr�mentant y pour allez vers le haut */
        for (int iterat = 0; iterat < bateauActuel.getTaille(); iterat++) {
            System.out.println("char � placer : " + (coordBateauY - iterat));
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
        /* placement des coordonn�es en incr�mentant x pour allez vers la droite */
        for (int iterat = 0; iterat < bateauActuel.getTaille(); iterat++) {
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
        /* placement des coordonn�es en incr�mentant y pour allez vers le bas */
        for (int iterat = 0; iterat < bateauActuel.getTaille(); iterat++) {
            System.out.println("char � placer : " + (coordBateauY + iterat));
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
        /* placement des coordonn�es en d�cr�mentant x pour allez vers la gauche */
        for (int iterat = 0; iterat < bateauActuel.getTaille(); iterat++) {
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
     * Lance la v�rification des coordonn�es entr�es, et affiche 
     * le r�sultat du tir.
     * @param x coordonn�e entr�e par l'utilisateur pour l'abscisse
     * @param y coordonn�e entr�e par l'utilisateur pour l'ordonn�e
     * 
     */
    public static void tir(char x, int y) {
        int indexBateau;
        Bateau bateauActuel;
        indexBateau = Bateau.verifTir(x, y);
           
        if (indexBateau >= 0) {
            bateauActuel = Bateau.getFlotte().get(indexBateau);
            if (bateauActuel.toucher()) {
                System.out.println("Bateau : " + bateauActuel.getNom() +" coul� !");
            } else {
                System.out.println("touch� !");
            }
        } else {
            System.out.println("aucun bateau touch� :(");
        }
        if (Bateau.bateauRestant()) {
            recupCoord(plateauJeu);
        } else {
            System.out.println("\n====>Partie termin�e ! tous les bateaux ont �t� coul�s !");
        }
    }

    /**
     * Lancement des principales fonctions 
     * et cr�ations des objets
     * @param args non utilis�
     */
    public static void main(String[] args) {
        menu();

        /*cr�ation du plateau de jeu */
        plateauJeu = new Plateau();

        /* Liste des bateaux � placer sur le plateau */
        new Bateau("Chocapic", 4);
        new Bateau("sous-marineLePen", 3);
        new Bateau();
        new Bateau("aeroglisseur", 1);
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

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
     * renvoie à la méthode appropriée
     */
    public static void Menu() {
        
        boolean nok;
        String reponse;
        
        Affichage.menuPrincipal();
        do {
            reponse = entreeUtilisateur();
            nok = Affichage.reponseValide(reponse);
        } while(!nok);
        
        if (reponse.charAt(0) == 'a') {
            aide();
        } else if (reponse.charAt(0) == 'q') {
            System.exit(0);
        }
    }

    
    /**
     * Affiche l'aide et effectue l'action appropriée
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
        
        if (reponse.charAt(0) == 'a') {
            aide();
        } else if (reponse.charAt(0) == 'q') {
            // arrêt du programme
        }  
        // else lance le jeu dans le main
    }
    
    /**
     * récupère la chaîne de caractères entrée par l'utilisateur
     * @return la chaîne de caractères que l'utilisateur a entrée
     */
    public static String entreeUtilisateur() {
        /* 
         * scanner récupérant les choix et 
         * réponses de l'utilisateur 
         */
        Scanner entree = new Scanner(System.in);
        
        String reponse;
        /* indicateur de saisie incorrecte */
        boolean nok;
        
        nok = false;
        do {
            /* raz de la réponse */
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
     * Effectue le placement d'un nombre donné de bateaux de taille aléatoire
     * (comprise entre 1 et 4) sur le plateau de jeu.
     * @param plateau le plateau sur lequel placer les bateaux
     * @param bateauxAPlacer liste des bateaux a placer
     * @throws IllegalArgumentException si il est impossible de placer les bateaux (ex: plateau trop petit)
     */
    public static void placement(Plateau plateau, Bateau[] bateauxAPlacer) throws IllegalArgumentException{
        Random random = new Random();
        int coordBateauX;
        int coordBateauY;
        int direction; //direction du bateau comprise entre 1 et 4
                       // 1 = haut; 2 = gauche; 3 = bas; 4 = droite
        
        for (int i = 0; i < bateauxAPlacer.length; i++) {
            // génération aléatoire des positions x et y des bateaux
            coordBateauX = random.nextInt(plateau.getDimX()) + 1;
            System.out.println(coordBateauX); //DEBUG
            coordBateauY = random.nextInt(plateau.getDimY()) + 1;
            System.out.println(coordBateauY); //DEBUG
            // direction du bateau aléatoire
            direction = random.nextInt(4) + 1;
            System.out.println(direction); //debug
            // placer le bateau i; PENSER A LA DIRECTION !
            switch(direction) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                if (coordBateauY + bateauxAPlacer[i].getTaille() <= plateau.getDimY()) {
                    placementBas(bateauxAPlacer[i], coordBateauX, coordBateauY);
                }
                break;
            case 4:
                break;
            }
            bateauxAPlacer[i].afficherPositions();
        }
    }
    
    /**
     * Effectue le placement d'un bateau dans le plateau.
     * Partant de (coordBateauX, coordBateauY) jusqu'a 
     * (coordBateauX, coordBateauY + bateau.getTaille())
     * @param bateau le bateau à placer
     * @param coordBateauX la coordonnée en abscisse du bateau a placer
     * @param coordBateauY la coordonnée en ordonnée du bateau a placer
     */
    private static void placementBas(Bateau bateau, int coordBateauX, int coordBateauY) {
            //bateau.SetPositionHorizontale((char) coordBateauX);
            //bateau.SetPositionVerticale(coordBateauY);
        bateau.getFlotte().get(2).SetPositionHorizontale((char) coordBateauX);
        bateau.getFlotte().get(2).SetPositionVerticale(coordBateauY);
    }


    /**
     * récupération des coordonnées qu'entre 
     * le joueur et placement de celles-ci dans deux variables
     *  x et y de type String.
     * @param plateauJeu 
     */
    public static void recupCoord(Plateau plateauJeu) {
        String coordonnees;
        /* coordonnées séparés en 
         * abscisse et ordonnée */
        char x = '0';
        int y;
        String chaineY = null;
         
        System.out.print("\nEntrez les coordonnés sous la forme : caractère,"
                        + " nombre (ex : A, 11 ou A11)");
        coordonnees = entreeUtilisateur();
        for (int placement = 0; placement < coordonnees.length(); placement++) {
            /* on cherche le caractère minuscule ou majuscule */
            if ((coordonnees.charAt(placement) >= 'a' 
                && coordonnees.charAt(placement) <= 'z')
                || ( coordonnees.charAt(placement) >= 'A'
                && coordonnees.charAt(placement) <= 'Z')) {
                
                /* x = morceau de chaîne avec le caractère */
                x = coordonnees.charAt(placement); 
            }
            if (coordonnees.charAt(placement) >= '1' && coordonnees.charAt(placement) <= '9') {
                /* y = valeur numérique */
                if (chaineY == null) {
                    chaineY = coordonnees.substring(placement, placement+1);
                } else {
                    chaineY += coordonnees.substring(placement, placement+1);
                }
            }
        }
        if (x != '0' && chaineY != null) {
            /* Si x et y sont existant dans le plateau. */
            y = Integer.parseInt(chaineY); // passage de la valeur en String.
            if ( ( ( x <= plateauJeu.getDimX()+ 'A' && x >= 'A') 
                    || (x <= plateauJeu.getDimX()+ 'a' && x >= 'a') )
                    && (y > 0 && y <= plateauJeu.getDimY() ) ) {
            
                System.out.println("x = " + x + "\ny = " + y); 
                tir(x,y);
            }
        } else {
            System.out.println("Coordonnées incorrect, votre plateau a une dimension x = "
                                + plateauJeu.getDimX() + " y = " + plateauJeu.getDimY());
            recupCoord(plateauJeu);
        }
    }
    
    /**
     * Cherche à verifier si les coordonnées entrées correspondent 
     * à l'emplacement d'un bateau.
     * @param x coordonnée entrée par l'utilisateur pour l'abscisse
     * @param y coordonnée entrée par l'utilisateur pour l'ordonnée
     * @return 0 si le bateau n'est pas touché, 
     *         1 si il est touché, 
     *         2 si il est coulé.
     */
    public static int tir(char x, int y) {
        int etat = 0;
        Bateau elementBateau;
        String[][] posBateau;
        /* parcours des coordonnées */
        int parcoursC;
        /* permet la sortie de la boucle */
        boolean toucher = false;
        
        for (int parcoursB = 0;parcoursB<Bateau.getFlotte().size() && !toucher; parcoursB++) {
            
            /* l'objet bateau actuellement vérifié */
            elementBateau = Bateau.getFlotte().get(parcoursB);
            
            /* le tableau contenant les coordonnées de ce bateau */
            posBateau = elementBateau.getPositions();
            /*
             * tant que la taille est définie, et que les coordonnées
             * ne correspondent pas, on fait tourner.
             */
            for (parcoursC = 0; parcoursC<posBateau[0].length 
                     && posBateau[0][0] != null
                     && (x != posBateau[0][0].charAt(0)
                     && y != Integer.parseInt(posBateau[0][0]));
                     parcoursC++);
            /* si touché seulement */
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
             * sinon,message que rien n'a été touché après que tout ait
             * été vérifié
             */
            
        }
        if (etat == 0) {
            System.out.println("Aucun bateau touché à cet emplacement");
        }
        return etat;
        //TODO recommencer l'affichage des infos, et saisie des coord
    }
    
    /**
     * Lancement des principales fonctions 
     * et créations des objets
     * @param args non utilisé
     */
    public static void main(String[] args) {
        Menu();
        
        /*création du plateau de jeu */
        Plateau plateauJeu;
        plateauJeu = new Plateau();
        
        /* Liste des bateaux à placer sur le plateau */
        Bateau[] listeBateaux = {new Bateau("Chocapic", 4),
                                 new Bateau("sous-marineLePen", 3),
                                 new Bateau(),
                                 new Bateau("aeroglisseur", 2)};
        /* affichage des infos du plateau pour le joueur */
        System.out.println("\n" + plateauJeu.toString() + "\n");
        placement(plateauJeu, listeBateaux);
        // TODO placer les bateaux
        
        /* affichage des infos de chaque bateau pour le joueur */
        for (int aAfficher = 0; aAfficher < Bateau.getFlotte().size(); aAfficher++) {
            System.out.printf("%d) %s", aAfficher+1,
                               Bateau.getFlotte().get(aAfficher).toString());
        }
        // TODO afficher informations de la partie en cours avant 
        // la demande des coordonnées -> méthode.
        recupCoord(plateauJeu);
    }

}

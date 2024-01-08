import java.util.Random;
import java.util.Scanner;

public class MMBase_DELUCAElliott_FRANCESKINNathan {

    // .........................................................................
    // OUTILS DE BASE
    // .........................................................................

    // fonctions classiques sur les tableaux

    /**
     * pré-requis : nb >= 0
     * résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tab.length; i++) {
            tab[i] = val;
        }
        return tab;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab) {
        int[] copie = new int[tab.length]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tab.length; i++) {
            copie[i] = tab[i];
        }
        return copie;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la liste des éléments de t entre parenthèses et séparés par des
     * virgules
     */
    public static String listElem(int[] t) {
        String list = "(";
        for (int i = 0; i < t.length - 1; i++) { //tout le tableau sauf la derniere
            list += t[i] + ",";
        }
        list += t[t.length - 1] + ")"; // la derniere (pour pas avoir la virgule)
        return list;
    }

    public static String listElem(char[] t) {
        String list = "(";
        for (int i = 0; i < t.length - 1; i++) { //tout le tableau sauf la derniere
            list += t[i] + ",";
        }
        list += t[t.length - 1] + ")"; // la derniere (pour pas avoir la virgule)
        return list;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : le plus grand indice d'une case de t contenant c s'il existe, -1
     * sinon
     */
    public static int plusGrandIndice(char[] t, char c) {
        int indice = -1;
        boolean stop = false;
        for (int i = t.length - 1; i >= 0 && !stop; i--) { // commence par l'indice le plus élevé puis diminue
            if (t[i] == c) { // si valeur trouvée.
                indice = i;
                stop = true;
            }
        }
        return indice;
    }
    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi c est un élément de t
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c) {
        boolean trouve = false;
        if (plusGrandIndice(t, c) != -1) {
            trouve = true;
        }
        return trouve;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * action : affiche un doublon et 2 de ses indices dans t s'il en existe
     * résultat : vrai ssi les éléments de t sont différents
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t) {
        boolean diff = true;
        for (int i = 0; i < t.length; i++) {
            int PGI = plusGrandIndice(t, t[i]); // cherche l'indice de sa derniere appartition.
            if (PGI != i && PGI > i) { // cherche la premiere apparition de la valeur.
                System.out.println("DOUBLON de " + "'" + t[i] + "'" + " sur les indices " + i + " et " + PGI);
                diff = false; // ne stoppe pas la boucle pour trouver d'autres doublons
            }
        }
        return diff;
    }

    // ______________________________________________

    /**
     * pré-requis : t1.length = t2.length
     * résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {
        boolean meme = true; // pas faux donc vrai
        for (int i = 0; i < t1.length && meme; i++) {
            if (t1[i] != t2[i]) {
                meme = false; // si une valeur est différente alors faux, stoppe la boucle.
            }
        }
        return meme;
    }

    // ______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les
    // paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de
    // tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /**
     * pré-requis : aucun
     * résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] tb = new int[lgCode]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tb.length; i++) {
            tb[i] = randomMinMax(0, nbCouleurs - 1);
        }
        return tb;
    }

    // ____________________________________________________________

    /**
     * pré-requis : aucun
     * action : si codMot n'est pas correct, affiche pourquoi
     * résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne
     * contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        boolean correct = true;
        if (codMot.length() != lgCode) { //verifie si la longueur est n'est pas correcte.
            correct = false;
            System.out.println("La longueur du code doit etre de : " + lgCode);
        } else { //verifie si les caractères sont dans tabCouleurs.
            for (int i = 0; i < lgCode && correct; i++) {
                if (!estPresent(tabCouleurs, codMot.charAt(i))) { // si la valeur est pas présente.
                    correct = false;
                    System.out.println("ERREUR : code couleur incorrect (" + codMot.charAt(i)
                            + " n'entre pas dans la table de couleurs)");
                }
            }
        }
        return correct;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant
     * chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] tb = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++) { // lettre par lettre
            boolean assigner = false;
            for (int j = 0; j < tabCouleurs.length && !assigner; j++) {
                if (codMot.charAt(i) == tabCouleurs[j]) { //cherche l'indice de la couleur codMot.charAt(i) dans tabCouleurs.
                    tb[i] = j; // j indice du tableau tabCouleurs.
                    assigner = true;
                }
            }
        }
        return tb;
    }

    // ____________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition
     * de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     * qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     * résultat : le code saisi sous forme de tableau d'entiers
     */

    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        boolean coupCorrect = false;
        String codeMot = "";
        while (!coupCorrect) {
            System.out.println("Donner un code (de longueur " + lgCode + ") pour la " + (nbCoups + 1) + " fois");
            codeMot = saisirChaine();
            if (codeCorrect(codeMot, lgCode, tabCouleurs)) {
                coupCorrect = true;
            }
        }
        int[] tabCodeMot = motVersEntiers(codeMot, tabCouleurs);
        return tabCodeMot;
    }

    // ____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même
     * indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1
     * (le "0" à l'indice 3)
     */

    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int total = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                total++; // +1 si la valeur cod1[i] == cod2[i]
            }
        }
        return total;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le
     * nombre d'occurrences de i dans cod
     * Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne
     * (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] repet = new int[nbCouleurs];
        for (int i = 0; i < cod.length; i++) {
            repet[cod[i]]++; // cod[i] = indice de la couleur dans tabCouleurs et l'indice de repet = indice de la couleur. +1 par répétition.
        }
        return repet;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à
     * nbCouleurs-1
     * résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de
     * leur position
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3
     * (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] nbCoulCod1 = tabFrequence(cod1, nbCouleurs);
        int[] nbCoulCod2 = tabFrequence(cod2, nbCouleurs);
        int total = 0;
        for (int i = 0; i < nbCouleurs; i++) {
            // Cherche la valeur minimum entre les 2 tableaux et l'ajoute au total (car c'est le nombre commun de cette valeur)
            total += Math.min(nbCoulCod1[i], nbCoulCod2[i]);
        }
        return total;
    }

    // ____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont
     * des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre
     * d'éléments communs de cod1 et cod2
     * se trouvant (resp. ne se trouvant pas) au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne
     * (1,2) : 1 bien placé (le "0" à l'indice 3)
     * et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] tb = new int[2];
        tb[0] = nbBienPlaces(cod1, cod2);
        tb[1] = nbCommuns(cod1, cod2, nbCouleurs) - tb[0];
        return tb;
    }

    // ____________________________________________________________

    // .........................................................................
    // MANCHEHUMAIN
    // .........................................................................

    /**
     * pré-requis : numMache >= 1
     * action : effectue la (numManche)ème manche où l'ordinateur est le codeur et
     * l'humain le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du
     * joueur humain (cf. sujet), s'il n'a toujours pas trouvé au bout du nombre
     * maximum d'essais
     * - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        clearConsole();
        int[] codeSolution = codeAleat(lgCode, tabCouleurs.length);
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        boolean victoire;
        int nbEssais = 0;
        System.out.println("DEBUT DE LA MANCHE NUMERO : " + numManche);
        System.out.println("Couleurs disponibles : " + listElem(tabCouleurs));
        do {
            cod[nbEssais] = propositionCodeHumain(nbEssais, lgCode, tabCouleurs);
            rep[nbEssais] = nbBienMalPlaces(codeSolution, cod[nbEssais], tabCouleurs.length);
            /*affichage de la table */
            clearConsole();
            System.out.println("DEBUT DE LA MANCHE NUMERO : " + numManche);
            System.out.println("Couleurs disponibles : " + listElem(tabCouleurs));
            affichePlateau(cod, rep, tabCouleurs, nbEssais, true);
            /*verifie si il y a une victoire*/
            victoire = sontEgaux(codeSolution, cod[nbEssais]);
            nbEssais++;

        } while (nbEssais < nbEssaisMax && !victoire); // arrete la boucle si code et propH sont égaux ou nbEssais atteint le max.
        int score = 0;
        clearConsole();
        affichePlateau(cod, rep, tabCouleurs, nbEssais-1, true);
        if (victoire) {
            System.out.println("VICTOIRE !");
            score = nbEssais;
        } else {
            System.out.println("DEFAITE ! La solution etait : " + entiersVersMot(codeSolution, tabCouleurs));
            int malus = rep[nbEssais-1][1] + 2 * (lgCode - (rep[nbEssais-1][0] + rep[nbEssais-1][1])); // nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
            score = nbEssais + malus;
        }
        System.out.println("Score de la manche "+numManche+" : " + score);
        System.out.println("Touche entrer pour continuer");
        saisirChaine();
        return score;
    }

    // ____________________________________________________________

    // ...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    // ...................................................................

    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     * résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String codMot = "";
        for (int i = 0; i < cod.length; i++) {
            codMot += tabCouleurs[cod[i]]; // Ajoute la lettre de tabCouleurs (car cod[i] = indice de la couleur) au String
        }
        return codMot;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : rep.length = 2
     * action : si rep n'est pas correcte, affiche pourquoi, sachant que rep[0] et
     * rep[1] sont les nombres de bien et mal placés resp.
     * résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0
     * et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep[0] < 0 || rep[1] < 0) {
            System.out.println("Les valeurs ne peuvent pas etre inferieure a zero.");
            return false;
        }
        if (rep[0] + rep[1] > lgCode) {
            System.out.println("La somme des reponses ne peut etre superieure a la longueur du code.");
            return false;
        }
        return true;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir les nombres de bien et mal
     * placés,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombres de couleurs sont bien placees : ");
            rep[0] = saisirEntier();
            System.out.print("Nombres de couleurs sont mal placees : ");
            rep[1] = saisirEntier();
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * action : met dans cod1 le code qui le suit selon l'ordre lexicographique dans
     * l'ensemble
     * des codes de longueur cod1.length à valeurs de 0 à nbCouleurs-1, si ce code
     * existe
     * résultat : vrai ssi l'action a pu être effectuée
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        /* 
         * Exemple :
         * 5 couleurs donc base 5
         * (3, 2, 3, 4) ==> +1 =
         * (3, 2, 4, 0)
         * suite max = (4, 4, 4, 4) donc suite non realisable (4 = base-1)
         */

        for (int i = cod1.length - 1; i >= 0; i--) {
            cod1[i] = (cod1[i] + 1) % nbCouleurs; // augmente de 1 la valeur et 0 si egal a la base
            if (cod1[i] > 0) { // il n'y a pas de retenu donc fin
                return true;
            }
        }
        return false; // Si que des 0 alors non réalisable.
    }

    // ___________________________________________________________________

    //___________________________________________________________________

    /**CHANGE : ajout du paramètre cod1 et modification des spécifications 
    *********************************************************************  
       pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                   nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
    résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
            c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
           propositions de cod seraient les nbCoups premières réponses de rep resp.
    */
    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        
        /*
        Pour etre compatible cod1 doit avoir le meme nombre de bien et mal placé que les reponses "rep" des codes "cod" avec tous les codes "cod"
        C'est à dire :
        - cod1 doit avoir le meme nombre de bien placé car on veut conserver les biens placés.
        - il doit aussi avec le meme nombre de mal placé car les mal placé sont dans le code solution mais ne sont pas bien placé
        elle ne doivent donc pas etre placé au meme endroit (en gros : les mal placé mal placé sont donc possiblement bien placé).
        Exemple (couleur :{R,V,B}) : avec RRRR (1,0) et RBBB (1,1) le code "cod1" devra donc respecté les 2 donc RBBJ ne peut etre compatible et BRJJ sera compatible.
        */
        for (int i = 0; i < nbCoups; i++) {
            int[] codNbCoupsRep = nbBienMalPlaces(cod1, cod[i], nbCouleurs);
            if (!sontEgaux(codNbCoupsRep, rep[i])) {
                return false;
            }
        }
        return true;
    }

    // ___________________________________________________________________

    //___________________________________________________________________

    /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat, 
                ajout du paramètre cod1 et modification des spécifications 
    **************************************************************************      
           pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                   nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
       action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
    */
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean codeExiste = false;
        do { // il doit le faire au moins une fois car le premier est forcement incompatible.
            codeExiste = passeCodeSuivantLexico(cod1, nbCouleurs);
        } while (!estCompat(cod1, cod, rep, nbCoups, nbCouleurs) && codeExiste);
        return codeExiste;
    }

    // ___________________________________________________________________

    // manche Ordinateur

    /**
     * pré-requis : numManche >= 2
     * action : effectue la (numManche)ème manche où l'humain est le codeur et
     * l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de
     * l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        int[] propCode = new int[lgCode];
        boolean incoherence = false;
        boolean victoire;
        int nbEssais = 0; // nbEssais est à 0 pour facilité la manipulation des tableaux
        do {
            cod[nbEssais] = copieTab(propCode);
            System.out.println("DEBUT DE LA MANCHE NUMERO : " + numManche);
            affichePlateau(cod, rep, tabCouleurs, nbEssais, false);
            rep[nbEssais] = reponseHumain(lgCode);
            victoire = rep[nbEssais][0] == lgCode;
            nbEssais++;
            /*à partir d'ici nbEssais = 2 apres 2 essais, il faut donc retiré 1 avec les tableaux apres le while*/
            if (!victoire) {
                incoherence = !passeCodeSuivantLexicoCompat(propCode, cod, rep, nbEssais, tabCouleurs.length); // si code n'existe pas alors incohérence
                clearConsole();
            }
            /* arrete la boucle si codeSolution et porpCode sont égaux ou nbEssais atteint le max ou si il y a une incoherence */
        } while (nbEssais < nbEssaisMax && !victoire && !incoherence);
        clearConsole();
        int score = 0;
        
        affichePlateau(cod, rep, tabCouleurs, nbEssais-1, true);
        if (incoherence) {
            System.out.println("L'ordinateur a detecte une incoherence dans la saisie des reponses.");
            System.out.println("Couleurs disponibles : " + listElem(tabCouleurs));
            int[] codeSolution = propositionCodeHumain(1, lgCode, tabCouleurs);
            afficheErreurs(entiersVersMot(codeSolution,tabCouleurs),cod,rep,nbEssais-1,lgCode,tabCouleurs);
            System.out.println("La solution etait : " + entiersVersMot(codeSolution, tabCouleurs));
        } else if (victoire) {
            System.out.println("VICTOIRE de l'ordi!");
            score = nbEssais;
        } else {
            System.out.println("DEFAITE de l'ordi !");
            System.out.println("Au joueur de dévoiler le code secret : ");
            System.out.println("Couleurs disponibles : " + listElem(tabCouleurs));
            int[] codeSolution = propositionCodeHumain(1, lgCode, tabCouleurs);
            if (estCompat(codeSolution, cod, rep, nbEssais, tabCouleurs.length)) {
                int malus = rep[nbEssais-1][1] + 2 * (lgCode - (rep[nbEssais-1][0] + rep[nbEssais-1][1])); // nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
                score = nbEssais + malus;
            } else {
                System.out.println("Le code secret ne correspond pas ! :(");
                afficheErreurs(entiersVersMot(codeSolution,tabCouleurs),cod,rep,nbEssais-1,lgCode,tabCouleurs);
            }
        }
        System.out.println("Score de la manche "+numManche+ " : " + score);
        System.out.println("Touche entrer pour continuer");
        saisirChaine();
        return score;
    }

    // ___________________________________________________________________

    // .........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    // .........................................................................

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {
        int saisie = -1;
        do {
            saisie = saisirEntier();
            if (saisie <= 0) {
                System.out.println("la valeur doit etre positive");
            }
        } while (saisie <= 0);
        return saisie;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier pair strictement
     * positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {
        int saisie = -1;
        do {
            System.out.println("Saisir un entier pair et strictement positif");
            saisie = saisirEntier();
        } while (saisie <= 0 && saisie % 2 != 0);
        return saisie;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir le nombre de couleurs (stricement
     * positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : le tableau des initiales des noms de couleurs saisis
     */

    public static char[] saisirCouleurs() {
        /*partie de saisie du nombre de couleur*/
        System.out.println("Saisir le nombre de couleurs.");
        int saisieNbC = saisirEntierPositif();
        /*partie saisie des couleurs avec verification*/
        char[] tab;
        boolean correct;
        do {
            tab = new char[saisieNbC];
            clearConsole();
            correct = true;
            System.out.println("Donner l'initiale des couleurs souhaite (" + saisieNbC + ") sous la forme \"RBVJ\".");
            String couleurChoisie = saisirChaine();
            /*verifie si la chaine de couleur est conforme*/
            if (couleurChoisie.length() == saisieNbC) {

                for (int i = 0; i < couleurChoisie.length() && correct; i++) {

                    if (estPresent(tab, couleurChoisie.charAt(i)) || couleurChoisie.charAt(i) == ' ') { // verifie si couleur déjà uilisée.
                        System.out.println( "ERREUR : " + couleurChoisie.charAt(i) + " a deja etait choisie ou est un espace.");
                        pause(2000); //laisse le temps de lire le msg d'erreur avant le prochain clear.
                        correct = false;
                    } else {
                        tab[i] = couleurChoisie.charAt(i);
                    }
                }

            } else {
                /*elle n'est donc pas conforme*/
                System.out.println("ERREUR : le nombre de couleur doit etre egal a " + saisieNbC + ".");
                pause(2000);
                correct = false;
            }
        } while (!correct);

        clearConsole();
        return tab;
    }
    
    // ___________________________________________________________________

    // .........................................................................
    // EXTENTIONS
    // .........................................................................

    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
    0 <= nbCoups < cod.length, nbCoups < rep.length et
    les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
    action : affiche les nbCoups premières lignes de cod (sous forme
    de mots en utilisant le tableau tabCouleurs) et de rep
    */ 
    public static void affichePlateau(int[][] cod, int[][] rep, char[] tabCouleurs, int nbEssais, boolean derniereRep /*esthétique uniquement*/){
        // affiche la ligne du haut
		String ligne = "";
		for (int i = 0; i < cod[0].length; i++) {
			ligne += "-";
		}
		ligne += "-----B---M--";
		System.out.println(ligne);
        // affiche les codes avec la reponse à droite
		for (int i = 0; i < nbEssais; i++) {
			System.out.print("| "+entiersVersMot(cod[i], tabCouleurs));
			System.out.println(" | "+rep[i][0]+" | "+rep[i][1]+" |");
		}
		System.out.print("| "+entiersVersMot(cod[nbEssais], tabCouleurs));
		// affiche la dernière réponse ou pas, car elle n'est pas toujours déjà set, ça évite d'afficher 0 0 inutilement, c'est simplement esthétique.
        if (derniereRep) {
			System.out.println(" | "+rep[nbEssais][0]+" | "+rep[nbEssais][1]+" |");
		} else {
			System.out.println(" |   |   |");
		}
        // affiche la ligne du bas
		ligne = "";
		for (int i = 0; i < cod[0].length; i++) {
			ligne += "-";
		}
		ligne += "------------";
		System.out.println(ligne);
	}

    /**
    pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
    0 <= nbCoups < cod.length, nbCoups < rep.length,
    les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
    et codMot est incorrect ou incompatible avec les nbCoups
    premières lignes de cod et de rep
    action : affiche les erreurs d’incorrection ou d’incompatibilité
    */
    public static void afficheErreurs(String codMot, int [][] cod, int [][] rep, int nbCoups, int lgCode, char[] tabCouleurs) {
        int[] codeSolution = motVersEntiers(codMot, tabCouleurs);
        for (int i = 0; i <= nbCoups; i++) {
            int[] repValide = nbBienMalPlaces(codeSolution, cod[i], tabCouleurs.length);
            if (!sontEgaux(repValide, rep[i])) {
                System.out.println("  Erreur de saisie dans la reponse du coup num "+(i+1));
                if (rep[i][0] != repValide[0]) {
                    System.out.println("    Le nombres de couleurs bien place est faux");
                }
                if (rep[i][1] != repValide[1]) {
                    System.out.println("    Le nombres de couleurs mal place est faux");
                }
                System.out.println("    La reponse aurait du etre : "+listElem(repValide)+" et non pas "+listElem(rep[i])+"\n");
            }
        }
    }

    /**
     résultat : 
    -le nombre maximum de propositions de codes pour tous les codes secrets possibles
    (ainsi que les codes secrets réalisant ce maximum), et
    -la moyenne des nombres de propositions de codes pour tous les codes secrets possibles.
    */
    public static void statsMasterMindIA(){
        System.out.println("longeur Code :");
        int lgCode = saisirEntierPositif();
        System.out.println("nb couleurs :");
        int nbCouleurs = saisirEntierPositif();
        System.out.println("nb essais max :");
        int nbEssaisMax = saisirEntierPositif();
        int[] codeSolution = new int[lgCode];
        int[] DernierCodeSolution = new int[lgCode];
        int[][] codesMaxEssais = new int[31][lgCode]; // historique des essais max capacité max 31.
        int nbCodesMax = 0; // le nombre d'essais max
        int maxEssais = 0;
        int sommeEssais = 0;
        int nbSolution = 0;
        do { // teste chaque code et prend le max
            
            int[] propCode = new int[lgCode];
            int[][] cod = new int[nbEssaisMax][lgCode];
            int[][] rep = new int[nbEssaisMax][2];
            boolean victoire;
            int nbEssais = 0;
            do { // joue marche ordinateur (allégé)
                cod[nbEssais] = copieTab(propCode);
                rep[nbEssais] = nbBienMalPlaces(codeSolution,cod[nbEssais],nbCouleurs);
                victoire = rep[nbEssais][0] == lgCode;
                nbEssais++;
                if (!victoire) {
                    passeCodeSuivantLexicoCompat(propCode, cod, rep, nbEssais, nbCouleurs);
                }
            } while (nbEssais < nbEssaisMax && !victoire);
            
            if (nbEssais > maxEssais) { // un nouveau max est trouvé.
                maxEssais = nbEssais; // j'applique le nouveau max.
                nbCodesMax = 0; // le nombre d'essais max est de nouveau 0.
            }
            if (nbEssais == maxEssais) {
                codesMaxEssais[nbCodesMax] = copieTab(codeSolution);
                if (nbCodesMax < 30) { // si le nombre d'essais max ne dépassera jamais 31 car ils seront écrasé.
                    nbCodesMax++;
                }
            }

            nbSolution++;
            sommeEssais += nbEssais;
            passeCodeSuivantLexico(codeSolution, nbCouleurs);
            
        } while (!sontEgaux(codeSolution, DernierCodeSolution)); // le dernier codeSolution ne fait que des 0 comme DernierCodeSolution.
        clearConsole();
        System.out.println("Nombre max d'essais : "+maxEssais);
        System.out.println("avec le(s) code(s) :");
        for (int i = 0; i <= nbCodesMax; i++) {
            System.out.println(listElem(codesMaxEssais[i]));
        }
        System.out.println("Moyenne des essais : "+(sommeEssais/nbSolution));
    }
    // ___________________________________________________________________

    // .........................................................................
    // PROGRAMME PRINCIPAL
    // .........................................................................

    /**
     * action : demande à l'utilisateur de saisir les paramètres de la partie
     * (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code et le nombre de couleurs doivent être strictement
     * positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    
    public static void main(String[] args) {
        System.out.println("Stats (1) ; Jeu (2) :");
        int stats;
        do {
            stats = saisirEntierPositif();
        } while (stats > 2);
        if (stats == 1) {
            statsMasterMindIA();
        }else{
            clearConsole();
            //partie commune
            char[] tabCouleurs = saisirCouleurs();
            System.out.println("Le code contiendra combien de caracteres ?");
            int lgCode = saisirEntierPositif();
            clearConsole();
            System.out.println("Combien y aura t'il d'essais max ?");
            int nbEssaisMax = saisirEntierPositif();
            clearConsole();
            System.out.println("Combien y aura t'il de manche(s) ?");
            int nbManche = saisirEntierPositif();
            clearConsole();
            int score = 0;

            //selection du mode
            System.out.println("Quel mode de jeu ?");
            System.out.println("ORDINATEUR : O");
            System.out.println("HUMAIN : H");
            char mode;
            do {
                mode = saisirCaractere();
                if (mode != 'H' && mode != 'O') {
                    System.out.println("Erreur de saisie.");
                }
            } while (mode != 'H' && mode != 'O');

            clearConsole();
            for (int numManche = 1; numManche <= nbManche; numManche++) {
                if (mode == 'H') {
                    //mode humain
                    score += mancheHumain(lgCode, tabCouleurs, numManche, nbEssaisMax);
                } else {
                    //mode ordinateur
                    score += mancheOrdinateur(lgCode, tabCouleurs, numManche, nbEssaisMax);
                }
            }
            System.out.println("FIN DE LA PARTIE");
            System.out.println("Score total : " + score);
        }
        
    } // fin main

    // ___________________________________________________________________

    //FONCTIONS PRATIQUES

	/**
	 * action : Efface la console.
	 * Source : Ut.java / MODIFIE
	 */
	public static void clearConsole() {
		System.out.print("\033[H\033[J");
	}

	/**
	 * résultat : Un int saisie par l'utilisateur.
	 * Source : Ut.java / MODIFIE
	 */
	public static int saisirEntier() {
		boolean test = false;
		int lu = -1;
		while (!test) {
			try {
				Scanner clavier = new Scanner(System.in);
				String s = clavier.nextLine();
				lu = Integer.parseInt(s);
				test = true;
			} catch (NumberFormatException ex) {
				System.out.println("Ce n'est pas un entier valide");
			}
		}
		return lu;
	}

	/**
	 * résultat : une valeur aleatoire entre min et max
	 * Source : Ut.java
	 */
	public static int randomMinMax(int min, int max) {
		Random rand = new Random();
		int res = rand.nextInt(max - min + 1) + min;
		return res;
	}

	/**
	 * résultat : Un String saisie par l'utilisateur.
	 * Source : Ut.java
	 */
	public static String saisirChaine() {
		Scanner clavier = new Scanner(System.in);
		String s = clavier.nextLine();
		return s;
	}

	/**
	 * résultat : Un char saisie par l'utilisateur.
	 * Source : Ut.java
	 */
	public static char saisirCaractere() {
		Scanner clavier = new Scanner(System.in);
		char lu = clavier.next().charAt(0);
		return lu;
	}

	/**
	 * action : Suspend le processus courant pendant timeMilli millisecondes.
	 * Source : Ut.java
	 */
	public static void pause(int timeMilli) {
		try {
			Thread.sleep(timeMilli);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

} // fin MasterMindBase

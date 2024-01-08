import java.util.Random;
import java.util.Scanner;

public class UtMM {

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

	public static boolean estPresent(char[] t, char c) {
        boolean trouve = false;
        if (plusGrandIndice(t, c) != -1) {
            trouve = true;
        }
        return trouve;
    }

	public static int[] copieTab(int[] tab) {
        int[] copie = new int[tab.length]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tab.length; i++) {
            copie[i] = tab[i];
        }
        return copie;
    }

	public static int saisirEntierPositif() {
        int saisie = -1;
        do {
            saisie = saisirEntier();
            if (saisie <= 0) {
                System.out.println("La valeur doit etre positive");
            }
        } while (saisie <= 0);
        return saisie;
    }

    public static int saisirEntierPairPositif() {
        int saisie = -1;
        do {
            System.out.println("Saisir un entier pair et strictement positif");
            saisie = saisirEntier();
        } while (saisie <= 0 || saisie % 2 != 0);
        return saisie;
    }

	public static boolean sontEgaux(int[] t1, int[] t2) {
        boolean meme = true; // pas faux donc vrai
        for (int i = 0; i < t1.length && meme; i++) {
            if (t1[i] != t2[i]) {
                meme = false; // si une valeur est différente alors faux, stoppe la boucle.
            }
        }
        return meme;
    }

	public static void affichePlateau(Code[] cod, int[][] rep, char[] tabCouleurs, int nbEssais, boolean derniereRep /*esthétique uniquement*/){
        // affiche la ligne du haut
		String ligne = "";
		for (int i = 0; i < cod[0].getLgCode(); i++) {
			ligne += "-";
		}
		ligne += "-----B---M--";
		System.out.println(ligne);
        // affiche les codes avec la reponse à droite
		for (int i = 0; i < nbEssais; i++) {
			System.out.print("| "+Couleur.entiersVersMot(cod[i], tabCouleurs));
			System.out.println(" | "+rep[i][0]+" | "+rep[i][1]+" |");
		}
		System.out.print("| "+Couleur.entiersVersMot(cod[nbEssais], tabCouleurs));
		// affiche la dernière réponse ou pas, car elle n'est pas toujours déjà set, ça évite d'afficher 0 0 inutilement, c'est simplement esthétique.
        if (derniereRep) {
			System.out.println(" | "+rep[nbEssais][0]+" | "+rep[nbEssais][1]+" |");
		} else {
			System.out.println(" |   |   |");
		}
        // affiche la ligne du bas
		ligne = "";
		for (int i = 0; i < cod[0].getLgCode(); i++) {
			ligne += "-";
		}
		ligne += "------------";
		System.out.println(ligne);
	}

	public static void afficheErreurs(String codMot, Code[] cod, int [][] rep, int nbCoups, int lgCode, char[] tabCouleurs) {
        int[] codeSolution = Couleur.motVersEntiers(codMot, tabCouleurs);
        for (int i = 0; i <= nbCoups; i++) {
            int[] repValide = Code.nbBienMalPlaces(codeSolution, cod[i].getCodCode(), tabCouleurs.length);
            if (!sontEgaux(repValide, rep[i])) {
                System.out.println("  Erreur de saisie dans la reponse du coup num "+(i+1));
                if (rep[i][0] != repValide[0]) {
                    System.out.println("    Le nombres de couleurs bien placees est faux");
                }
                if (rep[i][1] != repValide[1]) {
                    System.out.println("    Le nombres de couleurs mal placees est faux");
                }
                System.out.println("    La reponse aurait du etre : "+listElem(repValide)+" et non pas "+listElem(rep[i])+"\n");
            }
        }
    }

	public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] tb = new int[lgCode]; // crée un tableau de nb valeur 0
        for (int i = 0; i < tb.length; i++) {
            tb[i] = randomMinMax(0, nbCouleurs - 1);
        }
        return tb;
    }
}

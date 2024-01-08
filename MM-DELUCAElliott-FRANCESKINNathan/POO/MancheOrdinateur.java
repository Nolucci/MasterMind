public class MancheOrdinateur {

    private Plateau p;

    public MancheOrdinateur(Plateau p){
        this.p = p;
    }

    public int joue(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        UtMM.clearConsole();

        Code codeSolution = new Code(Code.propositionCodeHumain(0, lgCode, tabCouleurs),lgCode);
        
        int[] propCodeInit = new int[lgCode];
        Code propCode = new Code(propCodeInit, lgCode);

        boolean incoherence = false;
        boolean victoire;
        int nbEssais = 0; // nbEssais est à 0 pour faciliter la manipulation des tableaux
        do {
            Code nouvCodeEssai = new Code(UtMM.copieTab(propCode.getCodCode()),lgCode);

            this.p.ajoutCodeStock(nouvCodeEssai); 

            System.out.println("DEBUT DE L'ESSAI NUMERO : " + nbEssais);
            UtMM.affichePlateau(this.p.getCodPlateau(), this.p.getRepPlateau(), tabCouleurs, nbEssais, true);

            //rep[nbEssais] = nbBienMalPlaces(codeSolution,cod[nbEssais],tabCouleurs.length);
            this.p.ajoutRepStock(Code.reponseHumain(lgCode));

            victoire = this.p.getRepPlateau()[nbEssais][0] == lgCode;
            nbEssais++;
            /*à partir d'ici nbEssais = 2 apres 2 essais, il faut donc retiré 1 avec les tableaux apres le while*/
            if (!victoire) {
                incoherence = !Code.passeCodeSuivantLexicoCompat(propCode.getCodCode(), this.p.getCodPlateau(), this.p.getRepPlateau(), nbEssais, tabCouleurs.length); // si code n'existe pas alors incohérence
                UtMM.clearConsole();
            }
            this.p.ajoutNbCoups();
            /* arrete la boucle si codeSolution et porpCode sont égaux ou nbEssais atteint le max ou si il y a une incoherence */
        } while (nbEssais < nbEssaisMax && !victoire && !incoherence);
        
        int score = 0;
        UtMM.clearConsole();
        UtMM.affichePlateau(this.p.getCodPlateau(), this.p.getRepPlateau(), tabCouleurs, nbEssais-1, true);
        if (incoherence) {
            System.out.println("L'ordinateur a detecte une incoherence dans la saisie des reponses :");
            UtMM.afficheErreurs(Couleur.entiersVersMot(codeSolution,tabCouleurs),this.p.getCodPlateau(),this.p.getRepPlateau(),nbEssais-1,lgCode,tabCouleurs);
        } else if (victoire) {
            System.out.println("VICTOIRE de l'ordi!");
            score = nbEssais;
        } else {
            System.out.println("DEFAITE de l'ordi! La solution etait : " + Couleur.entiersVersMot(codeSolution, tabCouleurs));
            int malus = this.p.getRepPlateau()[nbEssais-1][1] + 2 * (lgCode - (this.p.getRepPlateau()[nbEssais-1][0] + this.p.getRepPlateau()[nbEssais-1][1])); // nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
            score = nbEssais + malus;
        }
        System.out.println("Score de la manche "+numManche+ " : " + score);
        System.out.println("Touche entrer pour continuer");
        UtMM.saisirChaine();
        return score;
    }
    
}

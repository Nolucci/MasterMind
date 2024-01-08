public class MancheHumain {    

    private Plateau p;

    public MancheHumain(Plateau p){
        this.p = p;
    }

    public int joue(int lgCode, char[] tabCouleurs, int nbEssaisMax) {
        UtMM.clearConsole();
        Code codeSolution = new Code(UtMM.codeAleat(lgCode, tabCouleurs.length), lgCode);
        boolean victoire;
        int nbEssais = 0;
        do {
            System.out.println("DEBUT DE L'ESSAI NUMERO : " + nbEssais);
            System.out.println("Couleurs disponibles : " + UtMM.listElem(tabCouleurs));

            Code nouvCodeEssai = new Code(Code.propositionCodeHumain(nbEssais, lgCode, tabCouleurs),lgCode);
            
            this.p.ajoutCodeStock(nouvCodeEssai);
            this.p.ajoutRepStock(Code.nbBienMalPlaces(codeSolution.getCodCode(), nouvCodeEssai.getCodCode(), tabCouleurs.length));
            /*affichage de la table */
            UtMM.clearConsole();
            UtMM.affichePlateau(this.p.getCodPlateau(), this.p.getRepPlateau(), tabCouleurs, nbEssais, true);
            /*verifie si il y a une victoire*/
            victoire = UtMM.sontEgaux(codeSolution.getCodCode(), nouvCodeEssai.getCodCode());
            nbEssais++;
            this.p.ajoutNbCoups();
        } while (nbEssais < nbEssaisMax && !victoire); // arrete la boucle si code et propH sont égaux ou nbEssais atteint le max.
        int score = 0;
        UtMM.clearConsole();
        UtMM.affichePlateau(this.p.getCodPlateau(), this.p.getRepPlateau(), tabCouleurs, nbEssais-1, true);
        if (victoire) {
            System.out.println("VICTOIRE !");
            score = nbEssais;
        } else {
            System.out.println("DEFAITE ! La solution etait : " + Couleur.entiersVersMot(codeSolution, tabCouleurs));
            int malus = this.p.getRepPlateau()[nbEssais-1][1] + 2 * (lgCode - (this.p.getRepPlateau()[nbEssais-1][0] + this.p.getRepPlateau()[nbEssais-1][1])); // nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
            score = nbEssais + malus;
        }
        System.out.println("Score de la manche : "+ score);
        System.out.println("Touche entrer pour continuer");
        UtMM.saisirChaine();
        return score;
    }
}

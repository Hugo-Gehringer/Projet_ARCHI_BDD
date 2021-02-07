package Metier;

public class Produit implements I_Produit {

    int quantiteStock = 0;
    String nom = null;
    double prixUnitaireHT = 0;
    double tauxTVA = 0.2;


    @Override //Je considère que cette méthode doit ajouter une quantite au stock, et si c'est fait elle retourne TRUE
    public boolean ajouter(int qteAchetee) {
        boolean ajout = false;
        int laQt = this.quantiteStock;
        this.quantiteStock = this.quantiteStock + qteAchetee;
        if(laQt != quantiteStock){
            ajout = true;
        }
        return ajout;
    }

    @Override //Je considère que cette méthode doit retirer une quantite au stock, et si c'est fait elle retourne TRUE
    public boolean enlever(int qteVendue) {
        boolean enleve = false;
        int laQt = this.quantiteStock;
        if(laQt >= qteVendue) {
            this.quantiteStock = this.quantiteStock - qteVendue;
            if (laQt != quantiteStock) {
                enleve = true;
            }
        }
        return enleve;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public int getQuantite() {
        return this.quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return this.prixUnitaireHT;
    }

    @Override
    public double getPrixUnitaireTTC() {
        return this.prixUnitaireHT * (1 + this.tauxTVA);
    }

    @Override
    public double getPrixStockTTC() {
        return this.quantiteStock * (this.prixUnitaireHT * (1 + this.tauxTVA));
    }


}

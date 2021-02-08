package Metier;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {

    private ArrayList<I_Produit> listProduit;

    public Catalogue() {
        this.listProduit =new ArrayList<>();
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        return listProduit.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit newProduit=new Produit(nom,prix,qte);
        return listProduit.add(newProduit);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int nbProduit=0;
        for (I_Produit produit:l) {
            nbProduit++;
            listProduit.add(produit);
        }
        return nbProduit;
    }

    @Override
    public boolean removeProduit(String nom) {
        for (I_Produit produit:listProduit) {
            if (produit.getNom().equals(nom)){
                listProduit.remove(produit);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        for (I_Produit produit:listProduit) {
            if (produit.getNom().equals(nomProduit)){
                produit.ajouter(qteAchetee);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        for (I_Produit produit:listProduit) {
            if (produit.getNom().equals(nomProduit)){
                 produit.enlever(qteVendue);
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getNomProduits() {
        int i=0;
        String[] nomProduits=new String[listProduit.size()];
        for (I_Produit produit:listProduit) {
            nomProduits[i]=produit.getNom();
            i++;
        }
       return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
        double montantTotal=0;
        for (I_Produit produit:listProduit) {
            montantTotal+=produit.getPrixStockTTC();
        }
        return montantTotal;
    }

    @Override
    public void clear() {
        listProduit.removeAll(listProduit);
    }
}

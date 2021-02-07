package Metier;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {

    private ArrayList<Produit> listProduit;

    public Catalogue() {
        this.listProduit =new ArrayList<>();
    }

    @Override
    public boolean addProduit(Produit produit) {
        return listProduit.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {


        Produit newProduit=new Produit(nom,prix,qte);
        return listProduit.add(newProduit);
    }

    @Override
    public int addProduits(List<Produit> l) {
        int nbProduit=0;
        for (Produit produit:l) {
            nbProduit++;
            listProduit.add(produit);
        }
        return nbProduit;
    }

    @Override
    public boolean removeProduit(String nom) {
        for (Produit produit:listProduit) {
            if (produit.getNom().equals(nom)){
                listProduit.remove(produit);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        for (Produit produit:listProduit) {
            if (produit.getNom().equals(nomProduit)){
               // produit.setQte(qteAchetee+produit.getQte());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        for (Produit produit:listProduit) {
            if (produit.getNom().equals(nomProduit)){
                // produit.setQte(produit.getQte()-qteAchetee);
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getNomProduits() {
        int i=0;
        String[] nomProduits=new String[listProduit.size()];
        for (Produit produit:listProduit) {
            nomProduits[i]=produit.getNom();
            i++;
        }
       return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
        double montantTotal=0;
        for (Produit produit:listProduit) {
         //   montantTotal+=produit.getMontantTTC;
        }
        return montantTotal;
    }

    @Override
    public void clear() {
        listProduit.removeAll(listProduit);
    }
}

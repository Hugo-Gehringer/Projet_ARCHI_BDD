package Metier;

import java.util.*;

public class Catalogue implements I_Catalogue {

    private ArrayList<I_Produit> listProduit;

    public Catalogue() {
        this.listProduit =new ArrayList<>();
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if (produit==null || listProduit.contains(produit)) {
            return false;
        }
        return listProduit.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit newProduit=new Produit(nom,prix,qte);
        if (listProduit.contains(newProduit)) {
            return false;
        }
        return listProduit.add(newProduit);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int nbProduit=0;
        if(l != null) {
            if (l.size() != 0) {
                for (I_Produit produit : l) {
                    if (produit.getPrixUnitaireHT() > 0) {
                        nbProduit++;
                        listProduit.add(produit);
                    }
                }
            }
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
        if(qteAchetee >= 1) {
            for (I_Produit produit : listProduit) {
                if (produit.getNom().equals(nomProduit)) {
                    produit.ajouter(qteAchetee);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        if(qteVendue >= 1) {
            for (I_Produit produit : listProduit) {
                if (produit.getNom().equals(nomProduit)) {
                    if (produit.getQuantite() >= qteVendue) {
                        produit.enlever(qteVendue);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String[] getNomProduits() {
        if (listProduit.size()>0) {
            Collections.sort(listProduit, new Comparator<I_Produit>() {
                @Override
                public int compare(I_Produit o1, I_Produit o2) {
                    return o1.getNom().compareTo(o2.getNom());
                }
            });
        }
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
        return Math.round(montantTotal * 100.0) / 100.0;
    }

    @Override
    public void clear() {
        listProduit.removeAll(listProduit);
    }
}

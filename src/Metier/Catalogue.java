package Metier;

import org.junit.platform.commons.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

public class Catalogue implements I_Catalogue {

    public ArrayList<I_Produit> listProduit;

    public Catalogue() {
        this.listProduit =new ArrayList<>();
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if (produit==null || listProduit.contains(produit)|| produit.getPrixUnitaireHT()<=0 || produit.getQuantite()<0) {
            return false;
        }
        for (String s: getNomProduits()){
            if (produit.getNom().trim().equals(s.trim())){
                return false;
            }
        }
        return listProduit.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        String nomCorrige=nom.trim().replaceAll("\\s+"," ");
        Produit newProduit=new Produit(nomCorrige,prix,qte);
        for (I_Produit produit:listProduit) {
            String nomP=produit.getNom();
            if(nomP.equals(nomCorrige)){
                return false;
            }
        }
        if (qte<0 || prix<=0) {
            return false;
        }
        return listProduit.add(newProduit);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        boolean bexiste=false;
        int nbProduit=0;
        if(l != null && !l.isEmpty()) {
            for (I_Produit produit : l) {
                bexiste=false;
                if (produit.getPrixUnitaireHT() > 0 && produit.getQuantite()>=0) {
                    for (String s : getNomProduits()) {
                        if (s.equals(produit.getNom().trim())) {
                            bexiste = true;
                            break;
                        }
                    }
                    if (bexiste == false) {
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
                if (produit.getNom().equals(nomProduit) && produit.getQuantite() >= qteVendue) {
                    produit.enlever(qteVendue);
                    return true;
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

            nomProduits[i] = produit.getNom().trim().replaceAll("\\s+"," ");
           // nomProduits[i] = produit.getNom().replace("\t", " ");
           // System.out.println(nomProduits[i]);
           // nomProduits[i]=produit.getNom().trim();

            System.out.println(nomProduits[i]);
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
        return  Math.round(montantTotal * 100.00) / 100.00;
    }

    @Override
    public void clear() {
        listProduit.removeAll(listProduit);
    }

    @Override
    public String toString() {
        DecimalFormat df=new DecimalFormat("0.00");
        String strResult="";
        for (I_Produit produit:this.listProduit) {
            strResult+=produit.getNom().trim()+" - prix HT : "+df.format(produit.getPrixUnitaireHT())+" € - prix TTC : "+df.format(produit.getPrixUnitaireTTC())+" € - quantité en stock : "+produit.getQuantite()+ "\n";
        }
        strResult+="\n" + "Montant total TTC du stock : "+ df.format(this.getMontantTotalTTC())+" €";
        return strResult;
    }
}

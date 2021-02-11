package Application;

import Metier.Produit;;
import static Presentation.FenetrePrincipale.catalogueF;

public class AjoutSuppressionController {

    public static boolean creerProduit(String nom,String prixHT,String qteStock){
        boolean par = true;
        boolean creation = false;
        int qteS = 0;
        Double prix = 0.00;
        try {
            qteS = Integer.parseInt(qteStock);
            prix = Double.parseDouble(prixHT);
        } catch (final NumberFormatException e) {
            return false;
        }
        if(par){
            Produit produit = new Produit(nom, prix, qteS);
            catalogueF.addProduit(produit);
            creation = true;
        }
        return creation;
    }

    public static boolean removeProduit(String Nom){
        return catalogueF.removeProduit(Nom);
    }



}

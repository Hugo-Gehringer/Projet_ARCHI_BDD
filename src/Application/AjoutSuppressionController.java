package Application;

import Metier.Produit;;
import static Presentation.FenetrePrincipale.catalogueF;

public class AjoutSuppressionController {

    public static boolean creerProduit(String nom,String prixHT,String qteStock){
        boolean creation = false;
        int qteS = 0;
        Double prix = 0.00;

        if(nom.equals(null) || nom == null || nom.trim().isEmpty()) {
            return false;
        }
        try {
            qteS = Integer.parseInt(qteStock);
            prix = Double.parseDouble(prixHT);
        } catch (final NumberFormatException e) {
            return false;
        }
        Produit produit = new Produit(nom, prix, qteS);
        return catalogueF.addProduit(produit);
    }

    public static boolean removeProduit(String Nom){
        return catalogueF.removeProduit(Nom);
    }



}

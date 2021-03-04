package Application;

import Metier.Produit;;
import java.sql.SQLException;

import static Presentation.FenetrePrincipale.catalogueF;

public class AjoutSuppressionController {

    public static boolean creerProduit(String nom,String prixHT,String qteStock) throws SQLException, ClassNotFoundException {
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
        produit.save();
        return catalogueF.addProduit(produit);
    }

    public static boolean removeProduit(String Nom){
        return catalogueF.removeProduit(Nom);
    }



}

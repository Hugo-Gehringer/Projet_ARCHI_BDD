package Application;

import Metier.Produit;;
import static Presentation.FenetrePrincipale.catalogueF;

public class AjoutSuppressionController {

    public static boolean creerProduit(String nom,double prixHT,int qteStock){
        Produit produit=new Produit( nom, prixHT, qteStock);
        return catalogueF.addProduit(produit);
    }

    public static boolean removeProduit(String Nom){
        return catalogueF.removeProduit(Nom);
    }



}

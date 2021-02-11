package Application;
import static Presentation.FenetrePrincipale.catalogueF;

public class AchatVenteController {

    public static boolean Achat(String nom,String qte){

        int qteS = 0;
        try {
            qteS = Integer.parseInt(qte);
        } catch (final NumberFormatException e) {
            return false;
        }
        return catalogueF.acheterStock(nom,qteS);
    }
    public static boolean Vente(String nom,String qte){
        int qteS = 0;
        try {
            qteS = Integer.parseInt(qte);
        } catch (final NumberFormatException e) {
            return false;
        }
        return catalogueF.vendreStock(nom,qteS);
    }
}

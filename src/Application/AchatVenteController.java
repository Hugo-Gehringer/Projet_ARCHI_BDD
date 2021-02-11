package Application;
import static Presentation.FenetrePrincipale.catalogueF;

public class AchatVenteController {

    public static boolean Achat(String nom,int qte){
        return catalogueF.acheterStock(nom,qte);
    }
    public static boolean Vente(String nom,int qte){
        return catalogueF.vendreStock(nom,qte);
    }
}

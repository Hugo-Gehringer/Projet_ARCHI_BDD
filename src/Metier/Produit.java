package Metier;

public interface Produit {

	int quantiteStock = 0;
	String nom = null;
	double prixUnitaireHT = 0;







	public abstract boolean ajouter(int qteAchetee);
	public abstract boolean enlever(int qteVendue);
	public abstract String getNom();
	public abstract int getQuantite();
	public abstract double getPrixUnitaireHT();
	public abstract double getPrixUnitaireTTC();
	public abstract double getPrixStockTTC();
	public abstract String toString();

}
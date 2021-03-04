package DAO;

import Metier.I_Produit;

public interface I_ProduitDAO {

    public abstract boolean create(I_Produit produit);
    public abstract boolean update(I_Produit produit);
    public abstract boolean delete(I_Produit produit);
    public abstract I_Produit read(int idProduit);
}

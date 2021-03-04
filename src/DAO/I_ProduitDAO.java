package DAO;

import Metier.I_Produit;

import java.sql.SQLException;

public interface I_ProduitDAO {

    public abstract boolean create(I_Produit produit) throws SQLException;
    public abstract boolean update(I_Produit produit) throws SQLException;
    public abstract boolean delete(I_Produit produit) throws SQLException;
    public abstract I_Produit read(int idProduit);
}

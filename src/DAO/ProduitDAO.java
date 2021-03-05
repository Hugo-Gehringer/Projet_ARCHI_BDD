package DAO;


import Metier.I_Produit;
import Metier.Produit;

import java.sql.*;
import java.util.ArrayList;

public class ProduitDAO implements I_ProduitDAO{
    private Produit produit;
    private ResultSet  rs;
    Statement st=null;
    private Connection cn;

    public ProduitDAO() throws SQLException, ClassNotFoundException {

        String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String login = "gehringerh";
        String mdp = "oracle123";
        rs = null;
        PreparedStatement pst;
        Class.forName(driver);
        cn = DriverManager.getConnection(url, login, mdp);
        st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       
    }
    
    public ArrayList<I_Produit> load() throws SQLException {
        ArrayList<I_Produit> list=new ArrayList<>();
        try {
            rs = st.executeQuery("SELECT p.* FROM PRODUIT p");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        while (rs.next()!=false){
            list.add(new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4)));
        }
        return list;
    }

    public boolean create(I_Produit produit) throws SQLException {

        String sql = "INSERT INTO PRODUIT(ID, NOM, PRIX_UNITAIRE_HT, QUANTITE) VALUES(Produit_AutoID.NEXTVAL,?,?,?)";
        PreparedStatement statement = cn.prepareStatement(sql);
        statement.setString(1,produit.getNom());
        statement.setDouble(2, produit.getPrixUnitaireHT());
        statement.setInt(3, produit.getQuantite());
        statement.executeUpdate();

        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return false;
        }


    }

    public Produit read (int idProduit){
        
        try {
            String sql = "SELECT p.* FROM PRODUIT p WHERE ID= ?";
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setInt(1, idProduit);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next(); // A quoi sert cette commande ?
            return new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4));

        } catch (SQLException throwables) {
            System.out.println(throwables);
            return null;
        }
    }

    public boolean update(I_Produit produit) throws SQLException {

        String sql = "update PRODUIT set PRIX_UNITAIRE_HT=? , QUANTITE=? where NOM=?";

        PreparedStatement preparedStatement = cn.prepareStatement(sql);

        preparedStatement.setDouble(1, produit.getPrixUnitaireHT());
        preparedStatement.setInt(2, produit.getQuantite());
        preparedStatement.setString(3, produit.getNom());

        int rowsAffected = preparedStatement.executeUpdate();

        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public boolean delete(I_Produit produit) throws SQLException {

        String sql= "DELETE FROM PRODUIT WHERE NOM=?" ;

        PreparedStatement preparedStatement = cn.prepareStatement(sql);

        preparedStatement.setString(1, produit.getNom());

        int rowsAffected = preparedStatement.executeUpdate();
        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return false;
        }
    }
}

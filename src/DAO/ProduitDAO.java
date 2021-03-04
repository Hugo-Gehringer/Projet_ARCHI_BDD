package DAO;


import Metier.Produit;

import java.sql.*;

public class ProduitDAO {
    private Produit produit;
    private ResultSet  rs;
    Statement st=null;

    public ProduitDAO() throws SQLException, ClassNotFoundException {

        String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String login = "gehringerh";
        String mdp = "gogo0974";

        rs = null;
        PreparedStatement pst;

        Class.forName(driver);
        Connection cn = DriverManager.getConnection(url, login, mdp);
        st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        try {
            rs = st.executeQuery("SELECT p.* FROM PRODUIT p");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean create(Produit produit){

        String sql= "INSERT INTO PRODUIT VALUES (" +
                "Produit_AutoID.NEXTVAL,"
                + produit.getNom()+","
                +produit.getPrixUnitaireHT()+","
                +produit.getQuantite()+");";
        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            return false;
        }

    }

    public Produit read (int idProduit){

        try {
            rs = st.executeQuery("SELECT p.* FROM PRODUIT p WHERE ID="+idProduit+";");
            rs.next();
            return new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4));

        } catch (SQLException throwables) {
            return null;
        }
    }

    public boolean update(Produit produit){

        String sql= "UPDATE PRODUIT SET " +
                "PRIX_UNITAIRE_HT="+produit.getPrixUnitaireHT()
                +", QUANTITE="+produit.getQuantite()
                +" WHERE NOM="+produit.getNom()+";" ;

        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public boolean delete(Produit produit){

        String sql= "DELETE FROM PRODUIT WHERE NOM="+produit.getNom()+";" ;

        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            return false;
        }
    }
}

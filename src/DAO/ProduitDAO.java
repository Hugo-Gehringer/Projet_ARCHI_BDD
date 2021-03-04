package DAO;


import Metier.I_Produit;
import Metier.Produit;

import java.sql.*;
import java.util.ArrayList;

public class ProduitDAO implements I_ProduitDAO{
    private Produit produit;
    private ResultSet  rs;
    Statement st=null;

    public ProduitDAO() throws SQLException, ClassNotFoundException {

        String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String login = "gehringerh";
        String mdp = "oracle123";
        rs = null;
        PreparedStatement pst;
        Class.forName(driver);
        Connection cn = DriverManager.getConnection(url, login, mdp);
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

    public boolean create(I_Produit produit) {

        String sql= "INSERT INTO PRODUIT VALUES ("+
                "Produit_AutoID.NEXTVAL,'"
                +produit.getNom()+"',"
                +produit.getPrixUnitaireHT()+","
                +produit.getQuantite()+")";
        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return false;
        }


    }

    public Produit read (int idProduit){
        
        try {
            rs = st.executeQuery("SELECT p.* FROM PRODUIT p WHERE ID="+idProduit+";");
            rs.next();
            return new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4));

        } catch (SQLException throwables) {
            System.out.println(throwables);
            return null;
        }
    }

    public boolean update(I_Produit produit){

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
    public boolean delete(I_Produit produit){

        String sql= "DELETE FROM PRODUIT WHERE NOM="+produit.getNom()+";" ;

        try {
            return st.executeUpdate(sql)!=0;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return false;
        }
    }
}

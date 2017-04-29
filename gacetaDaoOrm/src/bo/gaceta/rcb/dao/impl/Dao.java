
package bo.gaceta.rcb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class Dao<T> {

    public static void setCadenaConexion(String aCadenaConexion) {
        cadenaConexion = aCadenaConexion;
    }

    public static void setUsuarioBd(String aUsuarioBd) {
        usuarioBd = aUsuarioBd;
    }

    public static void setPasswordBd(String aPasswordBd) {
        passwordBd = aPasswordBd;
    }
    
    protected Connection conexion;
    
    public abstract T get(long id) throws SQLException;
    public abstract List<T> list() throws SQLException;
    protected abstract T build(ResultSet rs) throws SQLException;
    
      
    private static String cadenaConexion;
    private static String usuarioBd;
    private static String passwordBd;
    
    
    protected static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex){}
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dtbgacetarcb", "ussGacetaRCB", "#pwkN2BzsS09o");
    }
    
    protected ResultSet executeQuery(String query) throws SQLException{
        if(conexion  == null){
            conexion = Dao.getConnection();
        }
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Object x = new Object();
        return rs;
    }
    
   
    
    @Override
    protected void finalize() throws Throwable { 
        if(conexion != null){
            conexion.close();
        }
        super.finalize();
    }
    
}

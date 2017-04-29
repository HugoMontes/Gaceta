
package bo.gaceta.rcb.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class ABMDao<T> extends Dao<T> {
     
    
    public abstract T add(T obj) throws SQLException;
    public abstract void update(T obj) throws SQLException;
    public abstract void delete(long id) throws SQLException;
    
    
    public int executeUpdate(String query) throws SQLException{
        if(conexion == null){
            conexion = Dao.getConnection();
        }
        PreparedStatement ps = conexion.prepareStatement(query);        
        return ps.executeUpdate();
    }
    
     public long executeAdd(String query,String queryKey) throws SQLException{
        int affectedRows = this.executeUpdate(query);
        if(affectedRows != 1){
            throw new SQLException("No se pudo realizar la insercion");
        }
        ResultSet rs = this.executeQuery(queryKey);
        if(rs.next()){
            return rs.getLong(1);
        }
        throw new SQLException("No se pudo realizar la insercion");
        
    }
     
      public long executeAddAlt(String query) throws SQLException{
        int affectedRows = this.executeUpdate(query);
        if(affectedRows != 1){
            throw new SQLException("No se pudo realizar la insercion");
        }
        return 1;
        
    }
}


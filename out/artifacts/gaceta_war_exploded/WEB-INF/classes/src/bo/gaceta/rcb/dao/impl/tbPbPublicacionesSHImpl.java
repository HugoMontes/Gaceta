package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class tbPbPublicacionesSHImpl extends ABMDao<tbPbPublicaciones> {

  protected Connection conexion;


  protected static Connection getConnection() throws SQLException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
    }
    return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dtbgacetarcb", "ussGacetaRCB", "#pwkN2BzsS09o");
  }

  @Override
  protected ResultSet executeQuery(String query) throws SQLException {
    if (conexion == null) {
      conexion = Dao.getConnection();
    }
    Statement st = conexion.createStatement();
    ResultSet rs = st.executeQuery(query);
    Object x = new Object();
    return rs;
  }

  public List<tbPbPublicaciones> listToday() throws SQLException {
    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    ResultSet rs = this.executeQuery("SELECT * FROM tbpbpublicaciones where fecDisplay='"+fechaActual+"' AND ctrDisplay='1'") ;
    List<tbPbPublicaciones> lista = new ArrayList<>();
    while (rs.next()) {
      lista.add(this.build(rs));
    }
    return lista;
  }
  
   public List<tbPbPublicaciones> listPubFiltro(Date FiltroFecha,Date FiltroFechaFin) throws SQLException {
    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(FiltroFecha);
   String fechaFinal = new SimpleDateFormat("yyyy-MM-dd").format(FiltroFechaFin);
    ResultSet rs = this.executeQuery("SELECT * FROM tbpbpublicaciones where fecDisplay  BETWEEN '"+fechaActual+"' AND '"+fechaFinal+"' AND ctrDisplay='1'") ;
    List<tbPbPublicaciones> lista = new ArrayList<>();
    while (rs.next()) {
      lista.add(this.build(rs));
    }
    return lista;
  }
   
   public List<tbPbPublicaciones> listPubFiltroTxt(String txtFiltro) throws SQLException {
    ResultSet rs = this.executeQuery("SELECT * FROM tbpbpublicaciones where textoPublica like '%"+txtFiltro+"%' AND ctrDisplay='1'") ;
    List<tbPbPublicaciones> lista = new ArrayList<>();
    while (rs.next()) {
      lista.add(this.build(rs));
    }
    return lista;
  }

    public List<tbPbPublicaciones> listPubFiltroMatricula(String txtFiltro) throws SQLException {
        ResultSet rs = this.executeQuery("SELECT * FROM tbpbpublicaciones where idMatricula = '"+txtFiltro+"' AND ctrDisplay='1'") ;
        List<tbPbPublicaciones> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(this.build(rs));
        }
        return lista;
    }
   
    public tbPbPublicaciones getByIdPub(String idPublicacion) throws SQLException {
        ResultSet rs = this.executeQuery("SELECT * FROM tbpbpublicaciones where idPublicacion='"+idPublicacion+"'") ;
        tbPbPublicaciones publicacion = null;
        if (rs.next()) {
            publicacion = this.build(rs);
        }
        return publicacion;
    }

  @Override
  public tbPbPublicaciones add(tbPbPublicaciones obj) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void update(tbPbPublicaciones obj) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(long id) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public tbPbPublicaciones get(long id) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<tbPbPublicaciones> list() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected tbPbPublicaciones build(ResultSet rs) throws SQLException {
     tbPbPublicaciones obj=new tbPbPublicaciones(rs.getString("idPublicacion"),rs.getString("idLoginEmpresa"),rs.getDate("fecPublica")
             ,rs.getInt("tipoDocumento"),rs.getString("nroDocumento"),rs.getString("origenDocumento"),rs.getDate("fechaDocumento")
             ,rs.getInt("ctrTipoPublica"),rs.getString("urlFilePublica"),rs.getString("tituloPublica"),rs.getString("textoPublica")
             ,rs.getString("textoCompleto"),rs.getDate("fecDisplay"),rs.getDate("fecSolicitud"),rs.getDate("fecAprueba"),rs.getString("ctrDisplay")
             ,rs.getString("ctrUserAprueba"),rs.getString("desEstadoPublica"));
        return obj;
  }
}

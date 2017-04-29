package bo.gaceta.rcb.dao.impl;


import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.taEmpresa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class taEmpresaImpl implements Serializable {
  private static final Logger log = LogManager.getLogger(taEmpresaImpl.class);
  public void add(taEmpresa obj) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    Date dateGen = new Date();
    String time = new SimpleDateFormat("yyyyMMddHHmmss").format(dateGen);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    obj.setFechaRegistro(date);
    session.save(obj);
    tx.commit();
    session.close();
  }

  public void update (taEmpresa obj) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.update(obj);
    tx.commit();
    session.close();
  }

  public taEmpresa getByIdEmpresa(String idEmpresa) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM taEmpresa e where e.codigoEmpresa = :_idempr")
          .setParameter("_idempr", idEmpresa);
    taEmpresa entities = (taEmpresa) query.uniqueResult();
    session.close();
    return entities;
  }

  public boolean verifByIdEmpresa(String idEmpresa) {
    boolean resp;
    try {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      Query query = session.createQuery("SELECT e FROM taEmpresa e where e.codigoEmpresa = :_idempr")
            .setParameter("_idempr", idEmpresa);
      taEmpresa entities = (taEmpresa) query.uniqueResult();
      session.close();
      if (entities.getCodigoEmpresa() != null) {
        resp = true;
      } else {
        resp = false;
      }
      return resp;
    }catch (Exception ex)
    {
      log.debug("Error: " + ex);
      return false;
    }
  }

  public List<taEmpresa> getByIdUsuario(String idUsuario) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM taEmpresa e where e.idUsuario = :_codUsuario ORDER BY e.fechaRegistro DESC")
          .setParameter("_codUsuario", idUsuario);
    List<taEmpresa> entities = query.list();
    session.close();
    return entities;
  }

}

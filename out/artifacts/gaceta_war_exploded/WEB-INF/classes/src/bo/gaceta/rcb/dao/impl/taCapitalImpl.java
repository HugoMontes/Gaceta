package bo.gaceta.rcb.dao.impl;


import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.taCapital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class taCapitalImpl implements Serializable{
  private static final Logger log = LogManager.getLogger(taCapitalImpl.class);
  public void add(taCapital obj) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.save(obj);
    tx.commit();
    session.close();
  }

  public void update(taCapital obj) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.update(obj);
    tx.commit();
    session.close();
  }

  public taCapital getByIdEmpresa (String idEmpresa){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM taCapital e where e.codigoEmpresa = :_idempr")
          .setParameter("_idempr", idEmpresa);
    taCapital entities =(taCapital)query.uniqueResult();
    session.close();
    return entities;
  }

  public boolean verifByIdEmpresa(String idEmpresa) {
    boolean resp;
    try {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      Query query = session.createQuery("SELECT e FROM taCapital e where e.codigoEmpresa = :_idempr")
            .setParameter("_idempr", idEmpresa);
      taCapital entities = (taCapital) query.uniqueResult();
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



}

package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.taPublicacionesContent;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;


public class taPublicacionesContentImpl implements Serializable {

  public taPublicacionesContent getByID(int idPublicaAux) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM taPublicacionesContent e  WHERE e.idRegPublicacionContent= :_idpublicacion")
          .setParameter("_idpublicacion", idPublicaAux);
    taPublicacionesContent entities = (taPublicacionesContent) query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public boolean updateEstado(taPublicacionesContent obj) throws Exception {
    try {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = session.beginTransaction();
      session.update(obj);
      tx.commit();
      session.close();
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public boolean add(taPublicacionesContent obj) throws Exception {
    try {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = session.beginTransaction();
      session.save(obj);
      tx.commit();
      Query query = session.createQuery("SELECT e FROM taPublicacionesContent e WHERE e.idRegPublicacionContent = :_idpublica")
            .setParameter("_idpublica", obj.getIdPublicacion());
      taPublicacionesContent entities = (taPublicacionesContent) query.uniqueResult();
      session.close();
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

}

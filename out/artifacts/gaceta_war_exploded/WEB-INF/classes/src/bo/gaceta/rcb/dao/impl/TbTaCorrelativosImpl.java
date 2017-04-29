package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.TbTaCorrelativos;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TbTaCorrelativosImpl implements Serializable {
  
  private static final long serialVersionUID = -1728460811046947674L;
  
  public List<TbTaCorrelativos> list() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM TbTaCorrelativos e");
        List<TbTaCorrelativos> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }
  
  public TbTaCorrelativos getValCorr (Integer idCorr) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM TbTaCorrelativos e WHERE e.idCorrelativo = :_idCorrelativo")
                .setParameter("_idCorrelativo", idCorr);
        TbTaCorrelativos entities = (TbTaCorrelativos) query.uniqueResult();
        Integer corrActual=entities.getNroCorrelativo();
        corrActual++;
        entities.setNroCorrelativo(corrActual);
        session.update(entities);
        tx.commit();
        session.close();
        return entities;
    }
  
}

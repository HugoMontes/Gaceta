package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbTaLugarExp;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class tbTaLugarExpImpl implements Serializable {

  private static final long serialVersionUID = 8677985357888858298L;

  public tbTaLugarExp getById(Integer id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM tbTaLugarExp e where e.idLugarExpedicion = :_idBusca")
            .setParameter("_idBusca", id);
    tbTaLugarExp entities = (tbTaLugarExp) query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<tbTaLugarExp> list() throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM tbTaLugarExp e ORDER by e.idLugarExpedicion");
    List<tbTaLugarExp> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }
}

package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbTaIdentificacion;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class tbTaIdentificacionImpl implements Serializable {


  public tbTaIdentificacion getById(Integer id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM  tbTaIdentificacion e where e.idTipoIdentificacion = :_idBusca")
            .setParameter("_idBusca", id);
    tbTaIdentificacion entities = (tbTaIdentificacion) query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<tbTaIdentificacion> list() {
    try {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      Query query = session.createQuery("SELECT e FROM tbTaIdentificacion e ORDER by e.idTipoIdentificacion");
      List<tbTaIdentificacion> entities = query.list();
      session.getTransaction().commit();
      session.close();
      return entities;
    }
    catch (Exception ex)
    {
      return null;
    }
  }
}

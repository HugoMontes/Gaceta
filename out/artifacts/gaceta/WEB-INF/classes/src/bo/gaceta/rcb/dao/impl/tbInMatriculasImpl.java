package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbInMatriculas;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Session;


public class tbInMatriculasImpl implements Serializable {

  private static final long serialVersionUID = 547457413921702954L;
  
  public tbInMatriculas getById (String id){
            Session session = HibernateUtil.getSessionFactory().openSession();    
            session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM tbInMatriculas e where e.idMatricula = :_idBusca")
                     .setParameter("_idBusca", id);
            tbInMatriculas entities =(tbInMatriculas) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return entities;
    }
}

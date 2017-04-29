package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbFnPerfil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class tbFnPerfilImpl implements Serializable {


  
   public List<tbFnPerfil> list() throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM tbFnPerfil e");
    List<tbFnPerfil> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }
   
   public List<tbFnPerfil> listByPerfil(Integer perfil) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT e FROM tbFnPerfil e where e.ctrMenu = :_idperfil and e.ctrDisplay='1'")
            .setParameter("_idperfil", perfil);
    List<tbFnPerfil> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }
  
}

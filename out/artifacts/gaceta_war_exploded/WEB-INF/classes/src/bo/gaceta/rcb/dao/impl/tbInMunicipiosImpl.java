package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbInMunicipios;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class tbInMunicipiosImpl implements Serializable{

  private static final long serialVersionUID = 2155146785291579210L;
   public List <tbInMunicipios> getByDpto (String login){
            Session session = HibernateUtil.getSessionFactory().openSession();    
            session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM tbInMunicipios e where e.idDepartamento = :_iddpto ORDER BY e.municipio ASC")
                     .setParameter("_iddpto", login);
            List<tbInMunicipios> entities =query.list();
            session.getTransaction().commit();
            session.close();
            return entities;
    }

  public String desMunicipio(String idMunicipio){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM tbInMunicipios e WHERE e.idMunicipio=:_txtBusca")
          .setParameter("_txtBusca", idMunicipio);
    tbInMunicipios entities = (tbInMunicipios)query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return entities.getMunicipio();
  }
  
}

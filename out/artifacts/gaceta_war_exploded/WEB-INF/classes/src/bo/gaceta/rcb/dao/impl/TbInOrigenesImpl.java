package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.TbInOrigenes;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class TbInOrigenesImpl implements Serializable{
     
    public List<TbInOrigenes> list() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM TbInOrigenes e");
        List<TbInOrigenes> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public String desOrigen(int idOrigen){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.flush();
        Query query = session.createQuery("SELECT e FROM TbInOrigenes e WHERE e.idOrigen=:_txtBusca")
              .setParameter("_txtBusca", idOrigen);
        TbInOrigenes entities = (TbInOrigenes)query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities.getOrigen();
    }
}

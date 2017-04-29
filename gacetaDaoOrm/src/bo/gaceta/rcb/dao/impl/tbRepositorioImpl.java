package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbRepositorio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class tbRepositorioImpl implements Serializable{

    public tbRepositorio getImagen (String  codigoPublicacion) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbRepositorio e where e.idPublicacion = :_idBusca")
                .setParameter("_idBusca", codigoPublicacion);
        tbRepositorio entities = (tbRepositorio) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public boolean add(tbRepositorio obj) throws Exception {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            session.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }



}

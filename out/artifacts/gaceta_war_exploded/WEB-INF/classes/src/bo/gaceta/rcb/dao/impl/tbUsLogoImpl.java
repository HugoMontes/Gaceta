package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbRepositorio;
import bo.gaceta.rcb.modelo.tbUsLogo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class tbUsLogoImpl implements Serializable {

    public boolean add(tbUsLogo obj) throws Exception {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Calendar calendar = Calendar.getInstance();
            Date dateSave;
            dateSave = calendar.getTime();
            obj.setFechaCarga(dateSave);
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

    public boolean update(tbUsLogo obj) throws Exception {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Calendar calendar = Calendar.getInstance();
            Date dateSave;
            dateSave = calendar.getTime();
            obj.setFechaCarga(dateSave);
            session.update(obj);
            tx.commit();
            session.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }



    public tbUsLogo getImagen (String  codigoEmpresa) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUsLogo e where e.idLoginEmpresa = :_idBusca")
                .setParameter("_idBusca", codigoEmpresa);
        tbUsLogo entities = (tbUsLogo) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


}

package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.taTablas;
import org.hibernate.Query;
import org.hibernate.Session;
import java.io.Serializable;
import java.util.List;

public class taTablasbImpl implements Serializable {

    public taTablas getById(Integer idReg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM  taTablas e where e.idReg = :_idReg")
                .setParameter("_idReg", idReg);
        taTablas entities = (taTablas) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<taTablas> list(Integer idTabla, String valor) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM  taTablas e where e.idTabla = :_idTabla AND e.idValor=:_valor")
                    .setParameter("_idTabla", idTabla)
                    .setParameter("_valor", valor);
            List<taTablas> entities = query.list();
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

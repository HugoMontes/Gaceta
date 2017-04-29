package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.taSocios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class taSociosImpl implements Serializable{
    public void add(taSocios obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(obj);
        tx.commit();
        session.close();
    }

    public List<taSocios> getByIdUsuario(String codEmpresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM taSocios e where e.codigoEmpresa = :_codEmpresa ORDER by e.nroSocio")
                .setParameter("_codEmpresa", codEmpresa);
        List<taSocios> entities = query.list();
        session.close();
        return entities;
    }
}

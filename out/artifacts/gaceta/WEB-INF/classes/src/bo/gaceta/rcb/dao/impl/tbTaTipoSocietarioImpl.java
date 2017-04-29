package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbTaTipoSocietario;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class tbTaTipoSocietarioImpl implements Serializable {

    private static final long serialVersionUID = 3314323784409480109L;

    public tbTaTipoSocietario getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbTaTipoSocietario e where e.idTipoSocietario = :_idBusca")
                .setParameter("_idBusca", id);
        tbTaTipoSocietario entities = (tbTaTipoSocietario) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbTaTipoSocietario> list() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbTaTipoSocietario e ORDER by e.idTipoSocietario");
        List<tbTaTipoSocietario> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbTaTipoSocietario> listForReg() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbTaTipoSocietario e where e.ctrShowReg='1' ORDER by e.idTipoSocietario");
        List<tbTaTipoSocietario> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbTaTipoSocietario> listForRegNoById(Integer idTipoSocietario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbTaTipoSocietario e where e.ctrShowReg='1' and e.idTipoSocietario!=:idtiposoc ORDER by e.idTipoSocietario")
                .setParameter("idtiposoc", idTipoSocietario);
        List<tbTaTipoSocietario> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


}

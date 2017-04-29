package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class tbPbPublicacionesImpl implements Serializable {

    private static final long serialVersionUID = -7206792978970025320L;

    public List<tbPbPublicaciones> list() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e");
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public tbPbPublicaciones getByID(String idPublica) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e  WHERE e.idPublicacion= :_idpublicacion")
                .setParameter("_idpublicacion", idPublica);
        tbPbPublicaciones entities = (tbPbPublicaciones) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public String getAntID(int idRegPublica, int codPublica) {
        String codAntPub;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // SELECT * FROM tbPbPublicaciones WHERE idRegPublicacion < 7 AND ctrDisplay='1' AND ctrTipoPublica= 2 ORDER BY idRegPublicacion asc
            Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e  WHERE e.idRegPublicacion < :_idpublicacion AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_idcodpublica ORDER BY e.idRegPublicacion desc")
                    .setParameter("_idpublicacion", idRegPublica)
                    .setParameter("_idcodpublica", codPublica).setMaxResults(5);
            List<tbPbPublicaciones> entities = query.list();
            session.close();
            if (entities != null) {
                codAntPub = entities.get(0).getIdPublicacion().toString();
            } else {
                codAntPub = "";
            }
        } catch (Exception dex) {
            codAntPub = "";
        }
        return codAntPub;
    }


    public String getNextID(int idRegPublica, int codPublica) {
        String codAntPub;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // SELECT * FROM tbPbPublicaciones WHERE idRegPublicacion > 7 AND ctrDisplay='1' AND ctrTipoPublica= 2 ORDER BY idRegPublicacion asc
            Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e  WHERE e.idRegPublicacion > :_idpublicacion AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_idcodpublica ORDER BY e.idRegPublicacion asc")
                    .setParameter("_idpublicacion", idRegPublica)
                    .setParameter("_idcodpublica", codPublica).setMaxResults(5);
            List<tbPbPublicaciones> entities = query.list();
            session.close();
            if (entities != null) {
                codAntPub = entities.get(0).getIdPublicacion().toString();
            } else {
                codAntPub = "";
            }
        } catch (Exception dex) {
            codAntPub = "";
        }
        return codAntPub;
    }


    public List<tbPbPublicaciones> listNoAprobadas() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.ctrDisplay='0'");
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listByEmp(String codUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.idLoginEmpresa = :_idBusca")
                .setParameter("_idBusca", codUsuario);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listByEmpEst(String codUsuario, String estado) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.idLoginEmpresa = :_idBusca AND e.ctrDisplay= :_idEstado")
                .setParameter("_idBusca", codUsuario)
                .setParameter("_idEstado", estado);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPagoPendiente(String codUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.idLoginEmpresa = :_idBusca AND e.ctrDisplay='2'")
                .setParameter("_idBusca", codUsuario);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubToday() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();
        Date dateFiltra;
        dateFiltra = calendar.getTime();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay = :_filtroFecha AND e.ctrDisplay='1'")
                .setParameter("_filtroFecha", dateFiltra);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubTodayTip(int tipoPub) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();
        Date dateFiltra;
        dateFiltra = calendar.getTime();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay <= :_filtroFecha AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_tipoPublica ORDER BY e.idRegPublicacion desc")
                .setParameter("_filtroFecha", dateFiltra)
                .setParameter("_tipoPublica", tipoPub);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubFiltroMatricula(String idMatricula) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.idMatricula = :_idMatricula AND e.ctrDisplay='1'")
                .setParameter("_idMatricula", idMatricula);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubFiltroTxt(String txtBusqueda) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE (e.textoPublica like :_txtBusqueda AND e.ctrDisplay='1') OR (e.textoCompleto like :_txtBusqueda AND e.ctrDisplay='1')")
                .setParameter("_txtBusqueda", "%" + txtBusqueda + "%");
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubFiltroTxtTipo(String txtBusqueda, int tipPublica) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE (e.textoPublica like :_txtBusqueda AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_filtroTipo) OR (e.textoCompleto like :_txtBusqueda AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_filtroTipo)")
                .setParameter("_filtroTipo", tipPublica)
                .setParameter("_txtBusqueda", "%" + txtBusqueda + "%");
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubFiltroTipo(Date FiltroFecha, Date FiltroFechaFin, int tipPublica) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay between :_filtroFecha AND  :_filtroFechaFin AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_filtroTipo")
                .setParameter("_filtroFecha", FiltroFecha)
                .setParameter("_filtroFechaFin", FiltroFechaFin)
                .setParameter("_filtroTipo", tipPublica);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public List<tbPbPublicaciones> listToday() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();
        Date dateFiltra;
        dateFiltra = calendar.getTime();

        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay = :_filtroFecha AND e.ctrDisplay='1' order by fecDisplay  DESC ")
                .setParameter("_filtroFecha", dateFiltra);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public List<tbPbPublicaciones> listEmptyDay() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();
        Date dateFiltra;
        dateFiltra = calendar.getTime();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay <= :_filtroFecha AND e.ctrDisplay='1' order by fecDisplay  DESC")
                .setParameter("_filtroFecha", dateFiltra);
        query.setMaxResults(50);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listEmptyDayTip(int tipoPub) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();
        Date dateFiltra;
        dateFiltra = calendar.getTime();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay <= :_filtroFecha AND e.ctrDisplay='1' AND e.ctrTipoPublica= :_tipoPub order by fecDisplay  DESC")
                .setParameter("_filtroFecha", dateFiltra)
                .setParameter("_tipoPub", tipoPub);
        query.setMaxResults(50);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbPbPublicaciones> listPubFiltroDateMat(String idMatricula, Date FiltroFecha, Date FiltroFechaFin) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay between :_filtroFecha AND  :_filtroFechaFin AND e.idLoginEmpresa=:_idMatricula ")
                .setParameter("_idMatricula", idMatricula)
                .setParameter("_filtroFecha", FiltroFecha)
                .setParameter("_filtroFechaFin", FiltroFechaFin);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public List<tbPbPublicaciones> listPubFiltro(Date FiltroFecha, Date FiltroFechaFin) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecDisplay between :_filtroFecha AND  :_filtroFechaFin AND e.ctrDisplay='1'")
                .setParameter("_filtroFecha", FiltroFecha)
                .setParameter("_filtroFechaFin", FiltroFechaFin);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public List<tbPbPublicaciones> listPubFechaAprobacion(Date dateFiltra) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.fecAprueba = :_filtroFecha AND e.ctrDisplay='0'")
                .setParameter("_filtroFecha", dateFiltra);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }


    public List<tbPbPublicaciones> listPubFiltroCont(String contenido) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.textoPublica LIKE %:_filtroContenido% AND e.ctrDisplay='1'")
                .setParameter("_filtroContenido", contenido);
        List<tbPbPublicaciones> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public tbPbPublicaciones add(tbPbPublicaciones obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(obj);
        tx.commit();
        Query query = session.createQuery("SELECT e FROM tbPbPublicaciones e WHERE e.idPublicacion = :_idempresa")
                .setParameter("_idempresa", obj.getIdPublicacion());
        tbPbPublicaciones entities = (tbPbPublicaciones) query.uniqueResult();
        session.close();
        return entities;
    }


    public boolean updateEstado(tbPbPublicaciones obj) throws Exception {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

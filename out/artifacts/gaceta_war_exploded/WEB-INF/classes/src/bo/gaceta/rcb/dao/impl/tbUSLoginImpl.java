package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.tbUSLogin;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class tbUSLoginImpl implements Serializable {

    private static final long serialVersionUID = 6508047930717440317L;
    //static final Logger logger = LogManager.getLogger(tbUSLoginImpl.class.getName());

    public tbUSLogin getByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUSLogin e where e.idLoginEmpresa = :_idempresa")
                .setParameter("_idempresa", login);
        tbUSLogin entities = (tbUSLogin) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public tbUSLogin getByLogin(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUSLogin e where e.idLoginEmpresa = :_idempresa and e.claveIngreso= :_pass")
                .setParameter("_idempresa", login)
                .setParameter("_pass", password);
        tbUSLogin entities = (tbUSLogin) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public List<tbUSLogin> list() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUSLogin e");
        List<tbUSLogin> entities = query.list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public Boolean changePass(String login, String password, String passwordNew) {
        tbUSLogin loginUser=new tbUSLogin();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUSLogin e where e.idLoginEmpresa = :_idempresa")
                .setParameter("_idempresa", login);
        loginUser= (tbUSLogin) query.uniqueResult();
        try {

            if (loginUser.getClaveIngreso().equals(password)) {
                 loginUser.setClaveIngreso(passwordNew);
            session.update(loginUser);
            session.getTransaction().commit();
            return true;
            }
            else
            {
                return false;
            }
           
            
        } catch (Exception ex)  {
             //logger.error("Falla al actualizar la contraseña: " + ex.getMessage());
            return false;
        } finally {
            session.close();
        }

    }

    public String add(tbUSLogin obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            return "Cuenta:" + obj.getIdLoginEmpresa();
        } catch (Exception ex) {
            //logger.error("Falla al Insertar Usuario:" + ex.getMessage());
            return ex.getMessage();
        } finally {
            session.close();
        }
    }

    public Boolean updateLogo (String idUsuario,String pathLogo){
        tbUSLogin loginUser=new tbUSLogin();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM tbUSLogin e where e.idLoginEmpresa = :_idempresa")
                .setParameter("_idempresa", idUsuario);
        loginUser= (tbUSLogin) query.uniqueResult();
        try {

            if (loginUser!=null) {
                loginUser.setPathLogo (pathLogo);
                session.update(loginUser);
                session.getTransaction().commit();
                return true;
            }
            else
            {
                return false;
            }


        } catch (Exception ex)  {
            //logger.error("Falla al registrar el logotipo " + ex.getMessage());
            return false;
        } finally {
            session.close();
        }
    }
}

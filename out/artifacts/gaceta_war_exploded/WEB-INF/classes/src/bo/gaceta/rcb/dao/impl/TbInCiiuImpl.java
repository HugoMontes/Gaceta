/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gaceta.rcb.dao.impl;

import bo.gaceta.rcb.dao.HibernateUtil;
import bo.gaceta.rcb.modelo.TbInCiiu;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class TbInCiiuImpl implements Serializable {

  public List<TbInCiiu> list() throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e");
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<TbInCiiu> listFiltroClase(String txtClase) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.clase LIKE :_txtBusca")
            .setParameter("_txtBusca", "%"+txtClase+"%");
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<TbInCiiu> listSeccion() throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu  e GROUP BY e.idSeccion");
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<TbInCiiu> listDivision(String idSeccion) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idSeccion=:_txtBusca GROUP BY e.idDivision")
            .setParameter("_txtBusca", idSeccion);
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<TbInCiiu> listGrupo(String idDivision) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idDivision=:_txtBusca GROUP BY e.idGrupo")
            .setParameter("_txtBusca", idDivision);
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }


  
  public List<TbInCiiu> listClasebySeccion(String idSeccion) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idSeccion=:_txtBusca")
            .setParameter("_txtBusca", idSeccion);
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }
  
  public List<TbInCiiu> listClasebyDivision(String idDivision) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idDivision=:_txtBusca")
            .setParameter("_txtBusca", idDivision);
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public List<TbInCiiu> listClasebyGroup(String idGrupo) throws Exception {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idGrupo=:_txtBusca")
            .setParameter("_txtBusca", idGrupo);
    List<TbInCiiu> entities = query.list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public String desClase(String idClase){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.flush();
    Query query = session.createQuery("SELECT e FROM TbInCiiu e WHERE e.idClase=:_txtBusca")
          .setParameter("_txtBusca", idClase);
    TbInCiiu entities = (TbInCiiu)query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return entities.getClase();
  }

}

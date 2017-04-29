package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbRepositorioDao;
import bo.gaceta.rcb.modelo.tbRepositorio;

import java.io.Serializable;
import java.util.Calendar;

public class tbRepositorioBll implements Serializable{
    private final tbRepositorioDao publicaDao = new tbRepositorioDao();
    private Object FacesContext;

    public tbRepositorio getPublica(String idPublicacion) throws Exception {
        tbRepositorio publicacion=new tbRepositorio();
        publicacion=publicaDao.getImagen(idPublicacion);
        return publicacion;
    }

    public boolean addPublica(tbRepositorio obj) throws Exception {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        obj.setFechaGeneracion(currentTime);
        return  publicaDao.add(obj);
    }

}

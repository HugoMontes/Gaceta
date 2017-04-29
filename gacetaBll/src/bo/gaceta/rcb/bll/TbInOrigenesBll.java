package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.TbInOrigenesDao;
import bo.gaceta.rcb.modelo.TbInOrigenes;
import java.io.Serializable;
import java.util.List;

public class TbInOrigenesBll implements Serializable {

    private final TbInOrigenesDao PublicaDao = new TbInOrigenesDao();
    private Object FacesContext;

    public List<TbInOrigenes> list() throws Exception {
        return PublicaDao.list();
    }

    public String desByOrigen(int idOrigen){
        return  PublicaDao.desOrigen(idOrigen);
    }
}

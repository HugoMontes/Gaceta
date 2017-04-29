package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.taTablasbDao;
import bo.gaceta.rcb.modelo.taTablas;
import bo.gaceta.rcb.modelo.tbTipoSocab;

import java.io.Serializable;
import java.util.List;

public class taTablasBll implements Serializable {
    private final taTablasbDao identDao = new taTablasbDao();
    private Object FacesContext;

    public taTablas getByid(int id) throws Exception {
        taTablas tablas = identDao.getById(id);
        if (tablas == null) {
            throw new Exception("El código tipo societario no existe");
        }
        return tablas;
    }

    public List<taTablas> list(int idTps, String valor) throws Exception {
        return identDao.list(idTps, valor);
    }

}

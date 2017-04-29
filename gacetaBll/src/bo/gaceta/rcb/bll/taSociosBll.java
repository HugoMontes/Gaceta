package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.taSociosDao;
import bo.gaceta.rcb.modelo.taSocios;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel Zerain on 20/12/2016.
 */
public class taSociosBll implements Serializable {
    private final taSociosDao SociosDao = new taSociosDao();

    public void add(taSocios obj) throws Exception {
        SociosDao.add(obj);
    }

    public void delete(taSocios obj) throws Exception {
        SociosDao.delete(obj);
    }

    public List<taSocios> list(String CodEmpresa) throws Exception {
        return SociosDao.getByIdUsuario(CodEmpresa);
    }

}

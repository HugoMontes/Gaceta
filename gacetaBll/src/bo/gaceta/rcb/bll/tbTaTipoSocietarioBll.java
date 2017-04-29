package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbTaTipoSocietarioDao;
import bo.gaceta.rcb.modelo.tbTaTipoSocietario;

import java.io.Serializable;
import java.util.List;

public class tbTaTipoSocietarioBll implements Serializable {

    private static final long serialVersionUID = -4259228524990204149L;
    private final tbTaTipoSocietarioDao tpsDao = new tbTaTipoSocietarioDao();
    private Object FacesContext;

    public tbTaTipoSocietario getById(int idTps) throws Exception {
        tbTaTipoSocietario resTipoSoc = tpsDao.getById(idTps);
        if (resTipoSoc == null) {
            throw new Exception("El código tipo societario no existe");
        }
        return resTipoSoc;
    }

    public List<tbTaTipoSocietario> list() throws Exception {
        return tpsDao.list();
    }

    public List<tbTaTipoSocietario> listForReg() throws Exception {
        return tpsDao.listForReg();
    }

    public List<tbTaTipoSocietario> listForRegNoById(Integer idTipoSocietario) throws Exception {
        return tpsDao.listForRegNoById(idTipoSocietario);
    }

    public tbTaTipoSocietario listByIdLugarExp(Integer idTipoSocietario) throws Exception {
        tbTaTipoSocietario resTipoSoc = tpsDao.getById(idTipoSocietario);
        if (resTipoSoc == null) {
            throw new Exception("El código tipo societario no existe");
        }
        return resTipoSoc;
    }

}

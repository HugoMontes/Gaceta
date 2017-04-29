package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbUsLogoDao;
import bo.gaceta.rcb.modelo.tbUsLogo;

import java.io.Serializable;

public class tbUsLogoBll implements Serializable{
    private final tbUsLogoDao Dao = new tbUsLogoDao();
    private Object FacesContext;

    public Boolean addLogo(tbUsLogo obj) throws Exception {
        if(obj != null){
            return Dao.add(obj);
        }
        else
        {
            return false;
        }
    }

    public Boolean updateLogo(tbUsLogo obj) throws Exception {
        if(obj != null){
            return Dao.update(obj);
        }
        else
        {
            return false;
        }
    }

    public tbUsLogo getPublica(String idEmpresa) throws Exception {
        tbUsLogo infoLogo = new tbUsLogo();
        infoLogo=Dao.getImagen(idEmpresa);
        if(infoLogo ==null) {
            infoLogo=Dao.getImagen("00000000");
        }
        return infoLogo;
    }

}

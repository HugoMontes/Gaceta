package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.modelo.tbUsLogo;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;

import java.io.Serializable;

public abstract class Vista implements Serializable {

    private static final long serialVersionUID = 4655913064946722815L;
    private tbUSLogin usuarioSesion;
    private tbUsLogo logoSesion;

    public Vista(){
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }


    public tbUsLogo getLogoSesion() {
        return logoSesion;
    }

    public void setLogoSesion(tbUsLogo logoSesion) {
        this.logoSesion = logoSesion;
    }
}

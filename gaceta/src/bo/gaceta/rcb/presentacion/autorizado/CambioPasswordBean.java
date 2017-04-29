package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.bll.tbUSLoginBll;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "cambioPasswordBean")
@ViewScoped
public class CambioPasswordBean implements Serializable {

    private String txtPassword;
    private String txtPasswordNew;
    private tbUSLogin usuarioSesion;

    public CambioPasswordBean() {
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String getTxtPasswordNew() {
        return txtPasswordNew;
    }

    public void setTxtPasswordNew(String txtPasswordNew) {
        this.txtPasswordNew = txtPasswordNew;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public void cambiaClaveIngreso() {
        try {
            usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
            Boolean ctrPass = false;
            tbUSLoginBll lbll = new tbUSLoginBll();
            ctrPass = lbll.cambioPassword(usuarioSesion.getIdLoginEmpresa(), txtPassword, txtPasswordNew);
            if (!ctrPass) {
                UtilsPresentacion.addMessage("Cambio de Contraseña", "No se pudo actualizar la contraseña, verifique que la contraseña actual sea la correcta", FacesMessage.SEVERITY_ERROR);
            } else {
                UtilsPresentacion.addMessage("Cambio de Contraseña", "Contraseña actualizada correctamente", FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception ex) {
            UtilsPresentacion.addMessage("No se pudo actualizar la contraseña, verifique que la contraseña actual sea la correcta", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

}

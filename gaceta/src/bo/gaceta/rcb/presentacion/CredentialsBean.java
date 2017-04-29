package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.tbUSLoginBll;
import bo.gaceta.rcb.modelo.tbUSLogin;
import com.google.common.base.Strings;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "credentialsBean")
@ViewScoped

public class CredentialsBean implements Serializable {

    private String username;

    private String password;

    private boolean loginUser;

    private tbUSLogin usuarioSesion;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    public void ini() {
        loginUser=false;
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        if (usuarioSesion==null) {
            loginUser=false;
        }
        else
        {
            loginUser=true;
        }
    }

    public String iniciarSesion() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage message = null;
            boolean loggedIn = false;
            if(username != null  && password != null ) {
                String txtUserForm = Strings.padStart(username, 8, '0');
                username=txtUserForm;
                tbUSLoginBll usBll = new tbUSLoginBll();
                tbUSLogin user = usBll.iniciarSesion(username, password);
                UtilsPresentacion.setVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO, user);
                return "autorizado/principal?faces-redirect=true";
            }
            else
            {
                UtilsPresentacion.addMessage("Error al iniciar la sesión","Debe ingresar los datos de la cuenta", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception ex) {
            UtilsPresentacion.addMessage("Error al iniciar la sesión", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return null;
    }

    public boolean isLoginUser() {
        return loginUser;
    }

    public void setLoginUser(boolean loginUser) {
        this.loginUser = loginUser;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

}

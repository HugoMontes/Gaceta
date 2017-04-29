package bo.gaceta.rcb.presentacion.autorizado;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean extends Vista {

    private static final long serialVersionUID = 5023918910300398149L;

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}

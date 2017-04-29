package bo.gaceta.rcb.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class UtilsPresentacion {
    public static final String VARIABLE_SESION_USUARIO = "usuarioSesion" ;
    public static final String VARIABLE_SESION_PUBLICA = "publicacion" ;

    public static void addMessage(String titulo, String texto, FacesMessage.Severity severity){
        FacesMessage me = new FacesMessage(severity,titulo,texto );
        FacesContext.getCurrentInstance().addMessage(null, me);
    }

    public static void setVariableSesion(String nombreVariable, Object valorVariable){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(nombreVariable, valorVariable);
    }

    public static void borrarSesion(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
    }

    public static Object getVariableSesion(String nombreVariable){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session.getAttribute(nombreVariable);
    }
}

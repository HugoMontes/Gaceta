package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.bll.utils.EnvioMail;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.utils.EnvioEmail;
import bo.gaceta.rcb.presentacion.utils.generateImage;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Named(value = "cambioLocalBean")
@ViewScoped
public class CambioLocalBean implements Serializable {

    private tbPbPublicaciones publicacionRegistro;
    private tbUSLogin usuarioSesion;
    private String txtDireccion = "";
    private String txtZona = "";
    private String txtPublicacion = "";
    private String txtNro = "";
    private String txtUV = "";
    private String txtMza = "";
    private String txtEdificio = "";
    private String txtPiso = "";
    private String txtOficina = "";
    private boolean vistaPrev;

    private Boolean aceptacion;
    private String CodigoPublicacion;

    public CambioLocalBean() {

    }

    @PostConstruct
    public void init() {
        CodigoPublicacion = "";
        aceptacion = false;
        publicacionRegistro = new tbPbPublicaciones();
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
    }

    public void registraPublicacion() {
        if (aceptacion) {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            if (publicacionRegistro.getFecDisplay().before(now)) {
                UtilsPresentacion.addMessage("Error en fecha de publicación", "La fecha de publicación debe ser posterior a la actual.", FacesMessage.SEVERITY_INFO);
            } else {
                try {
                    tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                    generateImage generador = new generateImage();
                    publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                    String TituloPublica = "NOTIFICACION DE CAMBIO DE LOCAL DE LA EMPRESA: " + usuarioSesion.getSociedadConstituye();
                    publicacionRegistro.setTituloPublica(TituloPublica);
                    String resumenPublica = "La empresa " + usuarioSesion.getSociedadConstituye() + " con Matrícula de Comercio:" + usuarioSesion.getIdMatriculaAsociada() + "  anuncia su cambio de dirección a: Calle / Avenida " +
                            txtDireccion + ", N°" + txtNro;
                    if (txtUV.length() > 0) {
                        txtDireccion += ", Unidad Vecinal:" + txtUV;
                    }
                    if (txtMza.length() > 0) {
                        txtDireccion += ", Mza.:" + txtMza;
                    }
                    if (txtEdificio.length() > 0) {
                        txtDireccion += ", Edificio:" + txtEdificio;
                    }
                    if (txtPiso.length() > 0) {
                        txtDireccion += ", Piso:" + txtPiso;
                    }
                    if (txtOficina.length() > 0) {
                        txtDireccion += " , N°  de Oficina: " + txtOficina;
                    }
                    if (txtZona.length() > 0) {
                        txtDireccion += "  de la  Zona:" + txtZona;
                    }
                    publicacionRegistro.setTextoPublica(resumenPublica);
                    publicacionRegistro.setCtrTipoPublica(2);
                    publicacionRegistro.setTextoCompleto(resumenPublica);
                    publicacionRegistro.setCtrDisplay("2");
                    publicacionRegistro.setDesEstadoPublica("Esperando Pago");
                    String codigoPublica = usBll.add(publicacionRegistro);
                    CodigoPublicacion = codigoPublica;
                    String txtNotificacion = "<br><i>Notificaci&oacute;n de Publicaci&oacute;n</i></br>";
                    txtNotificacion += "<b></b>";
                    txtNotificacion += "<i>El Código de Publicaci&oacute;n es:" + codigoPublica + "</i>";
                    txtNotificacion += "<p>Debe realizar pago correspondiente para proceder con la publicaci&oacute;n</p>";
                    EnvioMail notif = new EnvioMail();
                    notif.envioMailEstado(usuarioSesion.getCorreoElectronico(), txtNotificacion);

                    publicacionRegistro = null;
                    aceptacion = false;
                    txtDireccion = "";
                    txtZona = "";
                    txtPublicacion = "";

                    EnvioEmail sendMail = new EnvioEmail();
                    tbUSLogin user=(tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
                    // String destinatario = "hugo927@hotmail.com";
                    String destinatario = user.getCorreoElectronico();
                    try {
                        String asunto = "Estado de la Publicación - Gaceta Electronica R.C.B.";
                        String message = "";
                        message += "<i>Se&ntilde;or empresaria(o), Se ha generado un código de publicación que a continuación se muestra, el mismo debe ser consignado o escribir en el formulario de Inscripción</i><br>";
                        message += "<b></b><br>";
                        message += "<i>Codigo de Publicación: " + CodigoPublicacion + "</i><br>";
                        message += "<p/>";
                        message += "<p/>";
                        message += "<a href='http://www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml'><span>www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml</span></a>";

                        sendMail.envioMail(destinatario, asunto, message);
                        //sendMail.envioMail(obj.getCorreoElectronico(), message);
                        //sendMail.envioMail(obj.getCorreoElectronico(), obj.getIdLoginEmpresa(), passwordGenerado);
                    } catch (Exception ex1) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Sin notificación por correo electronico");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }

                    String txtMensajeResultado
                            = "<div class=txtLabelNotifica>Se ha generado un código de publicación que a continuación </div>"
                            + "<div class=txtLabelNotifica>se muestra, el mismo debe ser consignado o escribir en el formulario de Inscripción</div><br>"
                            + "<div align=center class=txtCodigoPublica>Codigo de Publicación: <strong>" + CodigoPublicacion + "</strong></div><br>"
                            + "<div align=center class=txtCodigoPublica>El código tambien fue enviado a su cuenta de correo eletrónico <strong>"+destinatario+"</strong></div><br>"
                            + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } catch (Exception ex) {
                    UtilsPresentacion.addMessage("Error en el Registro de Usuario", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        } else {
            UtilsPresentacion.addMessage("Aceptación", "Debe aceptar los términos para realizar la publicacion", FacesMessage.SEVERITY_INFO);
        }
    }

    public void generaPreview() {
        if (!txtDireccion.isEmpty() || txtNro.isEmpty()) {
            vistaPrev = true;
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat tf = new SimpleDateFormat("HH:mm");
            txtPublicacion = "La empresa " + usuarioSesion.getSociedadConstituye() + " con Matrícula de Comercio: " + usuarioSesion.getIdMatriculaAsociada() + " anuncia su cambio de dirección a: Calle/Avenida " +
                    txtDireccion + ", N°" + txtNro;
            if (!txtUV.isEmpty()) {
                txtPublicacion += ", Unidad Vecinal :" + txtUV;
            }
            if (!txtMza.isEmpty()) {
                txtPublicacion += ", Mza.:" + txtMza;
            }
            if (!txtEdificio.isEmpty()) {
                txtPublicacion += ", Edificio:" + txtEdificio;
            }
            if (!txtPiso.isEmpty()) {
                txtPublicacion += ", Piso:" + txtPiso;
            }
            if (!txtOficina.isEmpty()) {
                txtPublicacion += " , N°  de Oficina: " + txtOficina;
            }
            if (!txtZona.isEmpty()) {
                txtPublicacion += "  de la  Zona:" + txtZona;
            }
        } else {
            UtilsPresentacion.addMessage("Vista Previa", "Debe Completar con la información basica para la vista previa", FacesMessage.SEVERITY_INFO);
        }
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        if (txtSig.equals("vistaPrevia")) {
            okVerif = true;
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            if (publicacionRegistro.getFecDisplay().before(now)) {
                UtilsPresentacion.addMessage("Error en fecha de publicación", "La fecha de publicación debe ser posterior a la actual.", FacesMessage.SEVERITY_INFO);
                okVerif = false;
            }
            this.generaPreview();
        }

        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public tbPbPublicaciones getPublicacionRegistro() {
        return publicacionRegistro;
    }

    public void setPublicacionRegistro(tbPbPublicaciones publicacionRegistro) {
        this.publicacionRegistro = publicacionRegistro;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public String getTxtZona() {
        return txtZona;
    }

    public void setTxtZona(String txtZona) {
        this.txtZona = txtZona;
    }

    public String getTxtPublicacion() {
        return txtPublicacion;
    }

    public void setTxtPublicacion(String txtPublicacion) {
        this.txtPublicacion = txtPublicacion;
    }

    public String getTxtNro() {
        return txtNro;
    }

    public void setTxtNro(String txtNro) {
        this.txtNro = txtNro;
    }

    public String getTxtUV() {
        return txtUV;
    }

    public void setTxtUV(String txtUV) {
        this.txtUV = txtUV;
    }

    public String getTxtMza() {
        return txtMza;
    }

    public void setTxtMza(String txtMza) {
        this.txtMza = txtMza;
    }

    public String getTxtEdificio() {
        return txtEdificio;
    }

    public void setTxtEdificio(String txtEdificio) {
        this.txtEdificio = txtEdificio;
    }

    public String getTxtPiso() {
        return txtPiso;
    }

    public void setTxtPiso(String txtPiso) {
        this.txtPiso = txtPiso;
    }

    public String getTxtOficina() {
        return txtOficina;
    }

    public void setTxtOficina(String txtOficina) {
        this.txtOficina = txtOficina;
    }

    public boolean isVistaPrev() {
        return vistaPrev;
    }

    public void setVistaPrev(boolean vistaPrev) {
        this.vistaPrev = vistaPrev;
    }

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getCodigoPublicacion() {
        return CodigoPublicacion;
    }

    public void setCodigoPublicacion(String codigoPublicacion) {
        CodigoPublicacion = codigoPublicacion;
    }
    // </editor-fold>
}

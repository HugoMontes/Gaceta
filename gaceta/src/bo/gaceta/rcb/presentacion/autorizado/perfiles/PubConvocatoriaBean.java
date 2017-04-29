package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.tbInMunicipiosBll;
import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbInMunicipios;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.models.ordenDia;
import bo.gaceta.rcb.presentacion.utils.EnvioEmail;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "pubConvocatoriaBean")
@ViewScoped
public class PubConvocatoriaBean implements Serializable {
    private tbPbPublicaciones publicacionRegistro;
    private tbUSLogin usuarioSesion;
    private Boolean aceptacion;
    private String dptoOrigen = "";
    private String distOrigen = "";
    private List<tbInMunicipios> municipiosDoc;
    @Future(message = "Debe seleccionar una fecha posterior a hoy")
    private Date fecJunta;
    private Date horJunta;
    private String txtOrden;
    private String txtUbicacion;
    private String txtPublicacion;
    private String txtTemaJunta;
    private String txtCaracter;
    private String txtRequisitos;
    private String txtSuspencion;
    @Future(message = "Debe seleccionar una fecha posterior a hoy")
    private Date FecPublica1;
    private boolean vistaPrev;
    private List<ordenDia> contenidoOrden = new ArrayList<>();
    private ordenDia itemOrden;
    private String tipoReunion;

    public PubConvocatoriaBean() {
    }

    @PostConstruct
    public void init() {
        aceptacion = false;
        vistaPrev = false;
        publicacionRegistro = new tbPbPublicaciones();
        setItemOrden(new ordenDia());
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        txtCaracter = "Ordinaria";
        txtRequisitos = "Para concurrir a la citada Asamblea General " + txtCaracter + " de Accionistas, los titulares de acciones nominativas deberán estar debidamente registrados en el Libro de Registro de Acciones de la Sociedad.  \n No podrán concurrir a la Asamblea los accionistas que se encuentren en alguna situación prevista por los Estatutos Sociales considerada como impedimento. ";
        txtSuspencion = "De conformidad con el artículo 294 del Código de Comercio Boliviano, desde el día de la publicación, quedará suspendido el registro de transferencias,\n así como el registro de transmisión de acciones, hasta el día siguiente posterior a la realizacion de la asamblea";

        setTipoReunion("Asamblea");
        if (getUsuarioSesion().getIdTipoSocietario() == 3) {
            setTipoReunion("Asamblea");
        }
        if (getUsuarioSesion().getIdTipoSocietario() == 6) {
            setTipoReunion("Junta");
        }
    }

    public void registraPublicacion() {
        long diasConv;
        diasConv = fecJunta.getTime() - getFecPublica1().getTime();
        diasConv = diasConv / (1000 * 60 * 60 * 24);
        if (diasConv >= 7) {
            if (aceptacion) {
                try {
                    this.generaPublica();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    DateFormat tf = new SimpleDateFormat("HH:mm");
                    tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                    publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                    String TituloPublica = "Convocatoria a la Asamblea General " + txtCaracter + " de Accionistas: " + usuarioSesion.getSociedadConstituye();
                    publicacionRegistro.setTituloPublica(TituloPublica);
                    String resumenPublica = "CONVOCATORIA A LA ASAMBLEA GENERAL " + txtCaracter.toUpperCase() + " DE ACCIONISTAS A LLEVARSE A CABO EL:" + df.format(fecJunta) + ", a horas:" + tf.format(horJunta);
                    publicacionRegistro.setTextoPublica(resumenPublica);
                    publicacionRegistro.setCtrTipoPublica(2);
                    publicacionRegistro.setTextoCompleto(txtPublicacion);
                    publicacionRegistro.setCtrDisplay("2");
                    publicacionRegistro.setDesEstadoPublica("Esperando Pago");
                    publicacionRegistro.setFecDisplay(getFecPublica1());
                    publicacionRegistro.setIdMatricula(usuarioSesion.getIdMatriculaAsociada());
                    publicacionRegistro.setTipoDocumento(2);
                    String codigoPublica1 = usBll.addConv(publicacionRegistro);

                    publicacionRegistro = null;
                    setFecPublica1(null);
                    aceptacion = false;
                    txtOrden = null;
                    txtUbicacion = null;
                    txtPublicacion = null;
                    horJunta = null;
                    fecJunta = null;
                    txtTemaJunta = null;
                    municipiosDoc = null;

                    EnvioEmail sendMail = new EnvioEmail();
                    tbUSLogin user = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
                    // String destinatario = "hugo927@hotmail.com";
                    String destinatario = user.getCorreoElectronico();
                    try {
                        String asunto = "Convocatoria a " + tipoReunion + " - Gaceta Electronica R.C.B.";
                        String message = "<i>A continuación se detalla el Código de la Convocatoria a la " + tipoReunion + " General " + txtCaracter + "</i>";
                        message += "<i>Se&ntilde;or empresaria(o), A continuación se detalla el Código de la Convocatoria a la Asamblea General</i><br>";
                        message += "<b></b><br>";
                        message += "<i>Codigo de Publicación: " + codigoPublica1 + "</i><br>";
                        message += "<i>Recuerde que debe realizar el pago y la publicación al menos siete (7) días previos a la realización de la misma.</i><br>";
                        message += "<p/>";
                        message += "<p/>";
                        message += "<a href='http://www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml'><span>www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml</span></a>";

                        sendMail.envioMail(destinatario, asunto, message);
                    } catch (Exception ex1) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Sin notificación por correo electronico");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }

                    String txtMensajeResultado
                            = "<div class=txtLabelNotifica>A continuación se detalla el Código de la Convocatoria a la " + tipoReunion + " General " + txtCaracter + "</div>"
                            + "<p/>"
                            + "<div align=center class=txtCodigoPublica>Codigo de  Publicación:" + codigoPublica1 + "</div>"
                            + "<p/>"
                            + "<div align=center class=txtCodigoPublica>Recuerde que debe realizar el pago y la publicación al menos siete (7) días previos a la realización de la misma </div><br>"
                            + "<div align=center class=txtCodigoPublica>El código tambien fue enviado a su cuenta de correo eletrónico <strong>" + destinatario + "</strong></div><br>"
                            + "<div align=center><button type=button onclick=redirectUser()>Aceptar</button></div>";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);

                } catch (Exception ex) {
                    UtilsPresentacion.addMessage("Error en el Registro de la Convocatoria", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            } else {
                UtilsPresentacion.addMessage("Aceptación", "Debe aceptar los términos para realizar la Convocatoria", FacesMessage.SEVERITY_INFO);
            }
        } else {
            UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cargaMunipDoc(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            municipiosDoc = munipBll.listByDpto(idDepto);
        } catch (Exception ex) {
            Logger.getLogger(PubConvocatoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generaPreview() {
        txtPublicacion = "";
        vistaPrev = true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        long diasConv;
        diasConv = fecJunta.getTime() - getFecPublica1().getTime();
        if (diasConv >= 7) {
            if (contenidoOrden.size() > 0) {
                txtPublicacion = "";
                txtPublicacion = "CONVOCATORIA A LA ASAMBLEA GENERAL " + txtCaracter.toUpperCase() + " DE ACCIONISTAS\n"
                        + " ----------------------------------------------------\n"
                        + usuarioSesion.getSociedadConstituye() + "\n";
                String txtOrdenCompleto = "";
                for (ordenDia listaOrden : getContenidoOrden()) {
                    txtOrdenCompleto = txtOrdenCompleto + listaOrden.getIdOrden() + "." + listaOrden.getOrdenDia() + "\n";
                }

                String txtDepartamento;
                switch (dptoOrigen) {
                    case ("01"):
                        txtDepartamento = "Chuquisaca";
                        break;
                    case ("02"):
                        txtDepartamento = "La Paz";
                        break;
                    case ("03"):
                        txtDepartamento = "Cochabamba";
                        break;
                    case ("04"):
                        txtDepartamento = "Oruro";
                        break;
                    case ("05"):
                        txtDepartamento = "Potosi";
                        break;
                    case ("06"):
                        txtDepartamento = "Tarija";
                        break;
                    case ("07"):
                        txtDepartamento = "Santa Cruz";
                        break;
                    case ("08"):
                        txtDepartamento = "Beni";
                        break;
                    case ("09"):
                        txtDepartamento = "Pando";
                        break;
                    default:
                        txtDepartamento = "";
                        break;
                }

                txtPublicacion = txtPublicacion + "El Directorio de " + usuarioSesion.getSociedadConstituye() + ", de acuerdo a lo dispuesto por los Estatutos de la Sociedad y\n"
                        + "Artículos 288 y 289 del Código de Comercio Boliviano, convoca a la Asamblea General " + txtCaracter + " de\n"
                        + "Accionistas, que se llevará a cabo el día " + df.format(fecJunta) + " , a horas " + tf.format(horJunta) + ", en el domicilio\n"
                        + "legal de la sociedad, ubicado en " + txtUbicacion + ", de la ciudad de " + distOrigen + ", del departamento de " + txtDepartamento + " \n"
                        + "Estado Plurinacional de Bolivia, para considerar el siguiente Orden del Día:\n"
                        + "Orden del día:\n"
                        + txtOrdenCompleto + "\n"
                        + "Requisitos para Asistir a la Asamblea:\n"
                        + txtRequisitos + "\n"
                        + "\n"
                        + txtSuspencion;
            } else {
                UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void generaPublica() {
        txtPublicacion = "";
        vistaPrev = true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        long diasConv;
        diasConv = fecJunta.getTime() - getFecPublica1().getTime();
        if (diasConv >= 7) {
            if (contenidoOrden.size() > 0) {
                txtPublicacion = "";
                String txtOrdenCompleto = "";
                for (ordenDia listaOrden : getContenidoOrden()) {
                    txtOrdenCompleto = txtOrdenCompleto + "<p>" + listaOrden.getIdOrden() + "." + listaOrden.getOrdenDia() + "</p>";
                }
                txtPublicacion = txtPublicacion + "<p>" + "El Directorio de " + usuarioSesion.getSociedadConstituye() + ", de acuerdo a lo dispuesto por los Estatutos de la Sociedad y\n"
                        + "Artículos 288 y 289 del Código de Comercio Boliviano, convoca a la Asamblea General " + txtCaracter + " de\n"
                        + "Accionistas, que se llevará a cabo el día " + df.format(fecJunta) + " , a horas " + tf.format(horJunta) + ", en el domicilio\n"
                        + "legal de la sociedad, ubicado en " + txtUbicacion + ", del municipio de " + distOrigen + ",\n"
                        + "Estado Plurinacional de Bolivia, para considerar el siguiente Orden del Día:" + "</p>"
                        + txtOrdenCompleto + "<br/>"
                        + "<p>" + "Requisitos para Asistir a la Asamblea:" + "</p>"
                        + "<p>" + txtRequisitos + "</p>"
                        + "<br/>"
                        + txtSuspencion
                        + "</font></div>";
            } else {
                // UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
                UtilsPresentacion.addMessage("La orden del día debe contener mínimamente un ítem", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            // UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
            UtilsPresentacion.addMessage("La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void registraOrden() {
        if (!txtOrden.isEmpty()) {
            Integer indice = getContenidoOrden().size();
            indice++;
            ordenDia tmpOrden = new ordenDia();
            tmpOrden.setIdOrden(indice);
            tmpOrden.setOrdenDia(txtOrden);
            contenidoOrden.add(tmpOrden);
            txtOrden = "";
        } else {
            // UtilsPresentacion.addMessage("Falta de información", "Debe digitar el texto para la orden del día", FacesMessage.SEVERITY_ERROR);
            UtilsPresentacion.addMessage("Debe digitar el texto para la orden del día", "Debe digitar el texto para la orden del día", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void eliminaItem(ordenDia ordenDia) {
        getContenidoOrden().remove(ordenDia);
        Integer idOrden = 1;
        for (ordenDia listaOrden : getContenidoOrden()) {
            listaOrden.setIdOrden(idOrden);
            idOrden++;
        }
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        if (txtSig.equals("vistaPrevia")) {
            long diasConv;
            diasConv = fecJunta.getTime() - getFecPublica1().getTime();
            diasConv = diasConv / (1000 * 60 * 60 * 24);
            if (diasConv < 7) {
                okVerif = false;
                // UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
                UtilsPresentacion.addMessage("La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", "La convocatoria a la asamblea general será publicada al menos siete(7) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
            } else {
                // this.generaPreview();
                if (contenidoOrden.size() > 0) {
                    okVerif = true;
                    this.generaPreview();
                } else {
                    okVerif = false;
                    //UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
                    UtilsPresentacion.addMessage("La orden del día debe contener mínimamente un ítem", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public List<ordenDia> getContenidoOrden() {
        return contenidoOrden;
    }

    public void setContenidoOrden(List<ordenDia> contenidoOrden) {
        this.contenidoOrden = contenidoOrden;
    }

    public ordenDia getItemOrden() {
        return itemOrden;
    }

    public void setItemOrden(ordenDia itemOrden) {
        this.itemOrden = itemOrden;
    }

    public Date getFecPublica1() {
        return FecPublica1;
    }

    public void setFecPublica1(Date fecPublica1) {
        FecPublica1 = fecPublica1;
    }

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

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getDptoOrigen() {
        return dptoOrigen;
    }

    public void setDptoOrigen(String dptoOrigen) {
        this.dptoOrigen = dptoOrigen;
    }

    public String getDistOrigen() {
        return distOrigen;
    }

    public void setDistOrigen(String distOrigen) {
        this.distOrigen = distOrigen;
    }

    public List<tbInMunicipios> getMunicipiosDoc() {
        return municipiosDoc;
    }

    public void setMunicipiosDoc(List<tbInMunicipios> municipiosDoc) {
        this.municipiosDoc = municipiosDoc;
    }

    public Date getFecJunta() {
        return fecJunta;
    }

    public void setFecJunta(Date fecJunta) {
        this.fecJunta = fecJunta;
    }

    public Date getHorJunta() {
        return horJunta;
    }

    public void setHorJunta(Date horJunta) {
        this.horJunta = horJunta;
    }

    public String getTxtOrden() {
        return txtOrden;
    }

    public void setTxtOrden(String txtOrden) {
        this.txtOrden = txtOrden;
    }

    public String getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(String txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    public String getTxtPublicacion() {
        return txtPublicacion;
    }

    public void setTxtPublicacion(String txtPublicacion) {
        this.txtPublicacion = txtPublicacion;
    }

    public String getTxtTemaJunta() {
        return txtTemaJunta;
    }

    public void setTxtTemaJunta(String txtTemaJunta) {
        this.txtTemaJunta = txtTemaJunta;
    }

    public String getTxtCaracter() {
        return txtCaracter;
    }

    public void setTxtCaracter(String txtCaracter) {
        this.txtCaracter = txtCaracter;
    }

    public String getTxtRequisitos() {
        return txtRequisitos;
    }

    public void setTxtRequisitos(String txtRequisitos) {
        this.txtRequisitos = txtRequisitos;
    }

    public String getTxtSuspencion() {
        return txtSuspencion;
    }

    public void setTxtSuspencion(String txtSuspencion) {
        this.txtSuspencion = txtSuspencion;
    }

    public boolean isVistaPrev() {
        return vistaPrev;
    }

    public void setVistaPrev(boolean vistaPrev) {
        this.vistaPrev = vistaPrev;
    }

    public String getTipoReunion() {
        return tipoReunion;
    }

    public void setTipoReunion(String tipoReunion) {
        this.tipoReunion = tipoReunion;
    }
    // </editor-fold>
}

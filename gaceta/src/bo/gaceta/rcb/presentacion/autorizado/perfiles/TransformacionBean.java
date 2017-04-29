package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.*;
import bo.gaceta.rcb.modelo.*;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.models.ordenDia;
import bo.gaceta.rcb.presentacion.registroBeanNew;
import bo.gaceta.rcb.presentacion.utils.EnvioEmail;
import bo.gaceta.rcb.presentacion.utils.StringHelper;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "transformacionBean")
@ViewScoped
public class TransformacionBean implements Serializable {
    private tbPbPublicaciones publicacionRegistro;
    private List<tbTaTipoSocietario> tiposSocietarios;
    private tbTaTipoSocietario tiposSocietarioSel;
    private tbUSLogin usuarioSesion;
    // private boolean vistaPrev;
    private Boolean aceptacion;
    private String CodigoPublicacion;
    /*Variables Acta*/
    private List<tbInMunicipios> municipiosDoc;
    private String distOrigen = "";
    private List<ordenDia> contenidoOrden = new ArrayList<>();
    private ordenDia itemOrden;
    @Past(message = "La fecha del Acta debe ser anterior a la actual")
    private Date fecJunta;
    private String tipoJunta;
    private String dptoOrigen = "";
    private String txtOrden;
    private String tpsEmpresa;
    private int newTPS;

    /*validacion Wizzar*/
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value1;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value2a;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value2;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value3;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value4;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value5;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean vistaPrev;

    /*Variables de contenido*/
    private String introNot;
    private String conclNot;
    private String concoNot;
    private String descr1;
    private String descr2;
    private String txtVistaPrevia;
    private String tipoReunion;

    private String reunion;
    private Map<String, String> reunions = new HashMap<String, String>();


    public TransformacionBean() {

    }

    @PostConstruct
    public void init() {
        setCodigoPublicacion("");
        setAceptacion(false);
        setPublicacionRegistro(new tbPbPublicaciones());
        setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));
        taEmpresaBll empresaBll = new taEmpresaBll();
        tbTaTipoSocietarioBll tpsBll = new tbTaTipoSocietarioBll();
        if (getUsuarioSesion().getIdTipoSocietario() == 3) {
            reunions = new HashMap<String, String>();
            reunions.put("Asamblea Ordinaria de Socios ", "Asamblea Ordinaria de Socios ");
            reunions.put("Asamblea Extraordinaria de Socios ", "Asamblea Extraordinaria de Socios ");
            tipoReunion="Asamblea";
        }
        if (getUsuarioSesion().getIdTipoSocietario() == 6) {
            reunions = new HashMap<String, String>();
            reunions.put("Junta Ordinaria de Accionistas", "Junta Ordinaria de Accionistas");
            reunions.put("Junta Extraordinaria de Accionistas", "Junta Extraordinaria de Accionistas");
            tipoReunion="Junta";
        }
        try {
            tiposSocietarioSel = tpsBll.getById(getUsuarioSesion().getIdTipoSocietario());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // setTiposSocietarios(tpsBll.listForReg());
            setTiposSocietarios(tpsBll.listForRegNoById(usuarioSesion.getIdTipoSocietario()));
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        if (txtSig.equals("intronot")) {
            okVerif = true;
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            if (publicacionRegistro.getFecDisplay().before(now)) {
                UtilsPresentacion.addMessage("Error en fecha de publicación", "La fecha de publicación debe ser posterior a la actual.", FacesMessage.SEVERITY_INFO);
                okVerif = false;
            }
        }

        if (txtSig.equals("tiposoc")) {
            okVerif = true;
        }
        if (txtSig.equals("destext")) {
            okVerif = true;
        }
        if (txtSig.equals("acta")) {

            okVerif = true;

        }
        if (txtSig.equals("conclusion")) {
            if (contenidoOrden.size() > 0) {
                okVerif = true;
            } else {
                okVerif = false;
                // UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
                UtilsPresentacion.addMessage("La orden del día debe contener mínimamente un ítem", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
            }
            // okVerif = true;
        }
        if (txtSig.equals("concordancia")) {
            okVerif = true;
        }
        if (txtSig.equals("preview")) {
            this.generaPreview();
            okVerif = true;
        }
        if (txtSig.equals("aprobacion")) {
            okVerif = true;
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    public void cargaMunipDoc(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            setMunicipiosDoc(munipBll.listByDpto(idDepto));
        } catch (Exception ex) {
            Logger.getLogger(TransformacionBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public void registraPublicacion() {
        if (aceptacion) {
            try {
                this.generaPreview();
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                DateFormat tf = new SimpleDateFormat("HH:mm");
                tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                taPublicacionesContentBll contBll = new taPublicacionesContentBll();
                tbTaTipoSocietarioBll tpsBll = new tbTaTipoSocietarioBll();
                taPublicacionesContent contenido = new taPublicacionesContent();
                tbTaTipoSocietario tiposSocietarioSel = tpsBll.getById(usuarioSesion.getIdTipoSocietario());
                String txtDesOrg = tiposSocietarioSel.getTipoSocietario();
                publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                String TituloPublica = "Transformacion de la Sociedad " + usuarioSesion.getSociedadConstituye();
                publicacionRegistro.setTituloPublica(TituloPublica);
                String resumenPublica = "Transformacion de la Sociedad: " + usuarioSesion.getSociedadConstituye() + " de " + txtDesOrg + " a " + tiposSocietarioSel.getTipoSocietario();
                publicacionRegistro.setTextoPublica(resumenPublica);
                publicacionRegistro.setCtrTipoPublica(1);
                publicacionRegistro.setTipoDocumento(2);
                publicacionRegistro.setTextoCompleto(txtVistaPrevia);
                publicacionRegistro.setCtrDisplay("3");
                publicacionRegistro.setDesEstadoPublica("En Revisión");
                publicacionRegistro.setFecDisplay(publicacionRegistro.getFecDisplay());
                publicacionRegistro.setIdMatricula(usuarioSesion.getIdMatriculaAsociada());

                String CodigoPublicacion = usBll.add(publicacionRegistro);
                contenido.setIdPublicacion(CodigoPublicacion);
                contenido.setIntroNotarial(introNot);
                contenido.setConcluNotarial(conclNot);
                contenido.setConcorNotarial(concoNot);
                contenido.setFecJunta(getFecJunta());
                contenido.setLugarJunta(distOrigen);
                contenido.setDescripcion1(descr1);
                contenido.setTipoJunta(tipoJunta);
                String txtOrdenCompleto = "";
                for (ordenDia listaOrden : getContenidoOrden()) {
                    txtOrdenCompleto = txtOrdenCompleto + listaOrden.getIdOrden() + "." + listaOrden.getOrdenDia() + "\n";
                }
                contenido.setOrdenDia(txtOrdenCompleto);
                contBll.altaPublicaContente(contenido);

                publicacionRegistro = null;
                introNot = null;
                conclNot = null;
                concoNot = null;
                descr1 = null;
                descr2 = null;
                aceptacion = false;
                txtOrden = null;
                setFecJunta(null);
                municipiosDoc = null;

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
                } catch (Exception ex1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Sin notificación por correo electronico");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }

                String txtMensajeResultado
                        = "<div class=txtLabelNotifica>Se ha generado un código de publicación que a continuación </div>"
                        + "<div class=txtLabelNotifica>se muestra, el mismo debe ser consignado o escribir en el formulario de inscripción</div><br>"
                        + "<div align=center class=txtCodigoPublica>Codigo de Publicación: <strong>" + CodigoPublicacion + "</strong></div><br>"
                        + "<div align=center class=txtCodigoPublica>El código tambien fue enviado a su cuenta de correo eletrónico <strong>"+destinatario+"</strong></div><br>"
                        + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro de la Publicacion", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Aceptación", "Debe aceptar los términos para realizar la Asamblea", FacesMessage.SEVERITY_INFO);
        }

    }


    public void generaPreview() throws Exception {
        String txtOrdenCompleto = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

        // taEmpresaBll empresaBll = new taEmpresaBll();
        tbTaTipoSocietarioBll tpsBll = new tbTaTipoSocietarioBll();
        txtVistaPrevia = "PUBLICACION DEL TESTIMONIO DE TRANSFORMACION DE " + tiposSocietarioSel.getTipoSocietario() + "\n"
                + " -----------------------------------------------------------------------------------\n"
                + "INTRODUCCION NOTARIAL" + "\n"
                + introNot + "\n"
                + "NUEVO TIPO SOCIETARIO" + "\n"
                // + "Nuevo Tipo Societario: " + tiposSocietarioSel.getTipoSocietario().toString() + "\n"
                // tiposSocietarioSel = tpsBll.getById(getUsuarioSesion().getIdTipoSocietario());
                + "Nuevo Tipo Societario: " + tpsBll.getById(newTPS).getTipoSocietario()  + "\n"
                + "DESCRIPCION TEXTUAL DE LA TRANSFORMACIÓN CONFORME A LA CLAUSULA RESPECTIVA" + "\n"
                + descr1 + "\n"
                + "DATOS DEL ACTA" + "\n"
                + "Tipo de Reunion:" + tipoJunta + ", llevada a cabo el " + df.format(getFecJunta()) + " en el municipio de " + distOrigen + " del Departamento de: " + txtDepartamento + "\n"
                + "Orden del Día:" + "\n"
                + "--------------" + "\n"
                + txtOrdenCompleto + "\n"
                + "CONCLUSION NOTARIAL" + "\n"
                + conclNot + "\n"
                + "CONCORDANCIA NOTARIAL" + "\n"
                + concoNot;
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

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValue3() {
        return value3;
    }

    public void setValue3(boolean value3) {
        this.value3 = value3;
    }

    public boolean isValue4() {
        return value4;
    }

    public void setValue4(boolean value4) {
        this.value4 = value4;
    }

    public boolean isValue5() {
        return value5;
    }

    public void setValue5(boolean value5) {
        this.value5 = value5;
    }

    public String getIntroNot() {
        return introNot;
    }

    public void setIntroNot(String introNot) {
        this.introNot = introNot;
    }

    public String getConclNot() {
        return conclNot;
    }

    public void setConclNot(String conclNot) {
        this.conclNot = conclNot;
    }

    public String getConcoNot() {
        return concoNot;
    }

    public void setConcoNot(String concoNot) {
        this.concoNot = concoNot;
    }

    public String getDescr1() {
        return descr1;
    }

    public void setDescr1(String descr1) {
        this.descr1 = descr1;
    }

    public String getDescr2() {
        return descr2;
    }

    public void setDescr2(String descr2) {
        this.descr2 = descr2;
    }

    public List<tbInMunicipios> getMunicipiosDoc() {
        return municipiosDoc;
    }

    public void setMunicipiosDoc(List<tbInMunicipios> municipiosDoc) {
        this.municipiosDoc = municipiosDoc;
    }

    public String getDistOrigen() {
        return distOrigen;
    }

    public void setDistOrigen(String distOrigen) {
        this.distOrigen = distOrigen;
    }

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

    public Date getFecJunta() {
        return fecJunta;
    }

    public void setFecJunta(Date fecJunta) {
        this.fecJunta = fecJunta;
    }

    public String getTipoJunta() {
        return tipoJunta;
    }

    public void setTipoJunta(String tipoJunta) {
        this.tipoJunta = tipoJunta;
    }

    public String getDptoOrigen() {
        return dptoOrigen;
    }

    public void setDptoOrigen(String dptoOrigen) {
        this.dptoOrigen = dptoOrigen;
    }

    public String getTxtOrden() {
        return txtOrden;
    }

    public void setTxtOrden(String txtOrden) {
        this.txtOrden = txtOrden;
    }

    public String getTxtVistaPrevia() {
        return txtVistaPrevia;
    }

    public void setTxtVistaPrevia(String txtVistaPrevia) {
        this.txtVistaPrevia = txtVistaPrevia;
    }

    public List<tbTaTipoSocietario> getTiposSocietarios() {
        return tiposSocietarios;
    }

    public void setTiposSocietarios(List<tbTaTipoSocietario> tiposSocietarios) {
        this.tiposSocietarios = tiposSocietarios;
    }

    public tbTaTipoSocietario getTiposSocietarioSel() {
        return tiposSocietarioSel;
    }

    public void setTiposSocietarioSel(tbTaTipoSocietario tiposSocietarioSel) {
        this.tiposSocietarioSel = tiposSocietarioSel;
    }


    public String getTpsEmpresa() {
        return tpsEmpresa;
    }

    public void setTpsEmpresa(String tpsEmpresa) {
        this.tpsEmpresa = tpsEmpresa;
    }

    public int getNewTPS() {
        return newTPS;
    }

    public void setNewTPS(int newTPS) {
        this.newTPS = newTPS;
    }

    public boolean isValue2a() {
        return value2a;
    }

    public void setValue2a(boolean value2a) {
        this.value2a = value2a;
    }

    public String getReunion() {
        return reunion;
    }

    public void setReunion(String reunion) {
        this.reunion = reunion;
    }

    public Map<String, String> getReunions() {
        return reunions;
    }

    public void setReunions(Map<String, String> reunions) {
        this.reunions = reunions;
    }

    public String getTipoReunion() {
        return tipoReunion;
    }

    public void setTipoReunion(String tipoReunion) {
        this.tipoReunion = tipoReunion;
    }
    // </editor-fold>

    public String firstCharTipoSocietario(){
        return StringHelper.convertStringFirstUpperCase(tiposSocietarioSel.getTipoSocietario());
    }
}

package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.bll.tbTaIdentificacionBll;
import bo.gaceta.rcb.bll.tbTaLugarExpBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbTaIdentificacion;
import bo.gaceta.rcb.modelo.tbTaLugarExp;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.autorizado.servicios.ArrayOfRepresentantes;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCB;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap;
import bo.gaceta.rcb.presentacion.registroBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "pubTransUniBean")
@ViewScoped
public class PubTransUniBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ws.fundempresa.org.bo_10080/wsrcbdes/servrcb.asmx.wsdl")
    private WebServiceRCB service;
    Boolean reqOrgId = true;
    Boolean reqOrgNewId = true;
    private tbPbPublicaciones publicacionRegistro;
    private String idDocPropietario;
    private String ciPropietario;
    private String expCiPropietario;
    private String nombrePropietario;
    private String apatPropietario;
    private String amatPropietario;
    private String idDocNewPropietario;
    private String ciNewPropietario;
    private String expCiNewPropietario;
    private String nombreNewPropietario;
    private String apatNewPropietario;
    private String amatNewPropietario;
    private tbUSLogin usuarioSesion;
    private String txtPublicacion;
    private String txtResumenPublicacion;
    private List<tbTaIdentificacion> tiposIdentificacion;
    private List<tbTaLugarExp> lugarExp;
    private boolean aceptacion;
    private boolean vistaPrev;

    public PubTransUniBean() {
    }

    @PostConstruct
    public void init() {
        reqOrgId = true;
        reqOrgNewId = true;
        tbTaIdentificacionBll identBll = new tbTaIdentificacionBll();
        tbTaLugarExpBll lugarExpBll = new tbTaLugarExpBll();
        publicacionRegistro = new tbPbPublicaciones();
        try {
            tiposIdentificacion = identBll.list();
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lugarExp = lugarExpBll.list();
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        ArrayOfRepresentantes Result = port.srvRepresentante("1020457026", "fundempresa1234567890", usuarioSesion.getIdMatriculaAsociada());
        for (int i = 0; i < Result.getRepresentantes().size(); i++) {
            if (Result.getRepresentantes().get(i).getTipoVinculo().equals("PROPIETARIO")) {
                nombrePropietario = Result.getRepresentantes().get(i).getNombreVinculo();
                ciPropietario = Result.getRepresentantes().get(i).getNumId();
                idDocPropietario = Result.getRepresentantes().get(i).getIdClase();
            }
        }
    }

    public void valideTipoId(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            reqOrgId = tpA.equals("Cedula de Identidad");
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valideNewTipoId(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            reqOrgNewId = tpA.equals("Cedula de Identidad");
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registraPublicacion() {
        if (aceptacion) {
            try {
                this.generaPreview();
                this.generaResumen();
                tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                String TituloPublica = "Aviso de transferencia de la Empresa Unipersonal: " + usuarioSesion.getSociedadConstituye();
                publicacionRegistro.setTituloPublica(TituloPublica);
                publicacionRegistro.setTextoPublica(txtResumenPublicacion);
                publicacionRegistro.setCtrTipoPublica(1);
                publicacionRegistro.setTextoCompleto(txtPublicacion);
                publicacionRegistro.setCtrDisplay("2");
                publicacionRegistro.setDesEstadoPublica("Esperando Pago");
                publicacionRegistro.setIdMatricula(usuarioSesion.getIdMatriculaAsociada());
                publicacionRegistro.setCtrTipoPublica(3);
                String codigoPublica = usBll.add(publicacionRegistro);

                publicacionRegistro = null;
                idDocPropietario = "";
                ciPropietario = "";
                expCiPropietario = "";
                nombrePropietario = "";
                apatPropietario = "";
                amatPropietario = "";

                idDocNewPropietario = "";
                ciNewPropietario = "";
                expCiNewPropietario = "";
                nombreNewPropietario = "";
                apatNewPropietario = "";
                amatNewPropietario = "";

                String txtMensajeResultado
                        = "<div class=txtLabelNotifica>Se ha generado un código de publicaci&oacute;n que a continuaci&oacute;n se muestra,</div>"
                        + "<div class=txtLabelNotifica>el mismo que debe ser consignado en el formulario de inscripi&oacute;n</div>"
                        + "<div align=center class=txtCodigoPublica>Codigo de Publicación:" + codigoPublica + "</div>"
                        + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro dela Publicacion", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Debe aceptar los términos para realizar la publicacion", "Debe completar los campos requeridos", FacesMessage.SEVERITY_INFO);
        }
    }

    public void generaPreview() {
        vistaPrev = true;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        txtPublicacion = "";
        String txtExpedicion = "";
        String txtExpedicionNew = "";
        if (expCiPropietario == null) {
            txtExpedicion = "";
        } else {
            txtExpedicion = " ,expedida en " + expCiPropietario;

        }
        if (expCiNewPropietario == null) {
            txtExpedicionNew = "";

        } else {
            txtExpedicionNew = " ,expedida en " + expCiNewPropietario;
        }

        txtPublicacion = "AVISO DE TRANSFERENCIA DE EMPRESA UNIPERSONAL\n"
                + " -------------------------------------------------------\n"
                + nombrePropietario.toUpperCase() + " con " + idDocPropietario.toUpperCase() + ", N°" + ciPropietario + txtExpedicion
                + ", propietario de la empresa unipersonal denominada:" + usuarioSesion.getSociedadConstituye() + ", con Matrícula del Registro de Comercio N°:" + usuarioSesion.getIdMatriculaAsociada()
                + ", en cumplimiento a lo establecido por el Art. 452 Inc. 1) del Código de Comercio Boliviano vigente, comunica a sus posibles acreedores la transferencia de la antes nombrada empresa unipersonal a:"
                + nombreNewPropietario.toUpperCase() + " " + apatNewPropietario.toUpperCase() + " " + amatNewPropietario.toUpperCase() + "con " + idDocNewPropietario.toUpperCase() + ", N°" + ciNewPropietario + txtExpedicionNew
                + "";

    }

    public void generaResumen() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        txtResumenPublicacion = "";
        String txtExpedicion = "";
        String txtExpedicionNew = "";
        if (expCiPropietario == null) {
            txtExpedicion = "";
        } else {
            txtExpedicion = " ,expedida en " + expCiPropietario;

        }
        if (expCiNewPropietario == null) {
            txtExpedicionNew = "";

        } else {
            txtExpedicionNew = " ,expedida en " + expCiNewPropietario;
        }

        txtResumenPublicacion = nombrePropietario.toUpperCase() + " con " + idDocPropietario.toUpperCase() + ", N°" + ciPropietario + txtExpedicion
                + ", propietario de la empresa unipersonal denominada:" + usuarioSesion.getSociedadConstituye() + ", con Matrícula del Registro de Comercio N°:" + usuarioSesion.getIdMatriculaAsociada()
                + ", en cumplimiento a lo establecido por el Art. 452 Inc. 1) del Código de Comercio Boliviano vigente, comunica a sus posibles acreedores la transferencia de la antes nombrada empresa unipersonal a:"
                + nombreNewPropietario.toUpperCase() + " " + apatNewPropietario.toUpperCase() + " " + amatNewPropietario.toUpperCase() + "con " + idDocNewPropietario.toUpperCase() + ", N°" + ciNewPropietario + txtExpedicionNew
                + "";

    }

    private ArrayOfRepresentantes srvRepresentantes(java.lang.String idContrato, java.lang.String keyContrato, java.lang.String idMatricula) {
        bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        return port.srvRepresentante(idContrato, keyContrato, idMatricula);
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        if (txtSig.equals("propietario")) {
            okVerif = true;
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            if (publicacionRegistro.getFecDisplay().before(now)) {
                UtilsPresentacion.addMessage("Error en fecha de publicación", "La fecha de publicación debe ser posterior a la actual.", FacesMessage.SEVERITY_INFO);
                okVerif = false;
            }
        }

        if (txtSig.equals("publicacion")) {
            okVerif = true;
        }

        if (txtSig.equals("vistaprevia")) {
            this.generaPreview();
            okVerif = true;
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public WebServiceRCB getService() {
        return service;
    }

    public void setService(WebServiceRCB service) {
        this.service = service;
    }

    public Boolean getReqOrgId() {
        return reqOrgId;
    }

    public void setReqOrgId(Boolean reqOrgId) {
        this.reqOrgId = reqOrgId;
    }

    public Boolean getReqOrgNewId() {
        return reqOrgNewId;
    }

    public void setReqOrgNewId(Boolean reqOrgNewId) {
        this.reqOrgNewId = reqOrgNewId;
    }

    public tbPbPublicaciones getPublicacionRegistro() {
        return publicacionRegistro;
    }

    public void setPublicacionRegistro(tbPbPublicaciones publicacionRegistro) {
        this.publicacionRegistro = publicacionRegistro;
    }

    public String getIdDocPropietario() {
        return idDocPropietario;
    }

    public void setIdDocPropietario(String idDocPropietario) {
        this.idDocPropietario = idDocPropietario;
    }

    public String getCiPropietario() {
        return ciPropietario;
    }

    public void setCiPropietario(String ciPropietario) {
        this.ciPropietario = ciPropietario;
    }

    public String getExpCiPropietario() {
        return expCiPropietario;
    }

    public void setExpCiPropietario(String expCiPropietario) {
        this.expCiPropietario = expCiPropietario;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getApatPropietario() {
        return apatPropietario;
    }

    public void setApatPropietario(String apatPropietario) {
        this.apatPropietario = apatPropietario;
    }

    public String getAmatPropietario() {
        return amatPropietario;
    }

    public void setAmatPropietario(String amatPropietario) {
        this.amatPropietario = amatPropietario;
    }

    public String getIdDocNewPropietario() {
        return idDocNewPropietario;
    }

    public void setIdDocNewPropietario(String idDocNewPropietario) {
        this.idDocNewPropietario = idDocNewPropietario;
    }

    public String getCiNewPropietario() {
        return ciNewPropietario;
    }

    public void setCiNewPropietario(String ciNewPropietario) {
        this.ciNewPropietario = ciNewPropietario;
    }

    public String getExpCiNewPropietario() {
        return expCiNewPropietario;
    }

    public void setExpCiNewPropietario(String expCiNewPropietario) {
        this.expCiNewPropietario = expCiNewPropietario;
    }

    public String getNombreNewPropietario() {
        return nombreNewPropietario;
    }

    public void setNombreNewPropietario(String nombreNewPropietario) {
        this.nombreNewPropietario = nombreNewPropietario;
    }

    public String getApatNewPropietario() {
        return apatNewPropietario;
    }

    public void setApatNewPropietario(String apatNewPropietario) {
        this.apatNewPropietario = apatNewPropietario;
    }

    public String getAmatNewPropietario() {
        return amatNewPropietario;
    }

    public void setAmatNewPropietario(String amatNewPropietario) {
        this.amatNewPropietario = amatNewPropietario;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getTxtPublicacion() {
        return txtPublicacion;
    }

    public void setTxtPublicacion(String txtPublicacion) {
        this.txtPublicacion = txtPublicacion;
    }

    public String getTxtResumenPublicacion() {
        return txtResumenPublicacion;
    }

    public void setTxtResumenPublicacion(String txtResumenPublicacion) {
        this.txtResumenPublicacion = txtResumenPublicacion;
    }

    public List<tbTaIdentificacion> getTiposIdentificacion() {
        return tiposIdentificacion;
    }

    public void setTiposIdentificacion(List<tbTaIdentificacion> tiposIdentificacion) {
        this.tiposIdentificacion = tiposIdentificacion;
    }

    public List<tbTaLugarExp> getLugarExp() {
        return lugarExp;
    }

    public void setLugarExp(List<tbTaLugarExp> lugarExp) {
        this.lugarExp = lugarExp;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public boolean isVistaPrev() {
        return vistaPrev;
    }

    public void setVistaPrev(boolean vistaPrev) {
        this.vistaPrev = vistaPrev;
    }
    // </editor-fold>
}

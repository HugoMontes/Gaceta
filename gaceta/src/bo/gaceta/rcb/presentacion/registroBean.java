package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.tbTaIdentificacionBll;
import bo.gaceta.rcb.bll.tbTaLugarExpBll;
import bo.gaceta.rcb.bll.tbTaTipoSocietarioBll;
import bo.gaceta.rcb.bll.tbUSLoginBll;
import bo.gaceta.rcb.modelo.*;
import bo.gaceta.rcb.presentacion.autorizado.servicios.ArrayOfMatriculaDatos;
import bo.gaceta.rcb.presentacion.autorizado.servicios.ArrayOfMatriculaDatosOperativos;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCB;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "registroBean")
@ViewScoped
public class registroBean implements Serializable {

    private static final long serialVersionUID = -5320311533473803459L;
    Boolean reqOrgId = true;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ws.fundempresa.org.bo_10080/wsrcbdes/servrcb.asmx.wsdl")
    private WebServiceRCB service;
    private boolean blAcepContrato;
    private String tipUser = "N";
    private List<tbTaTipoSocietario> tiposSocietarios;
    private List<tbTaIdentificacion> tiposIdentificacion;
    private List<tbTaLugarExp> lugarExp;
    private tbInMatriculas infoMatricula;
    private String txtMatricula;
    private String correoMask;
    private tbUSLogin usuarioRegistro;
    private String mailConfirma;

    public registroBean() {

    }

    @PostConstruct
    public void init() {
        blAcepContrato = false;
        usuarioRegistro = new tbUSLogin();
        infoMatricula = new tbInMatriculas();
        blAcepContrato = false;
        reqOrgId = true;

        tbTaIdentificacionBll identBll = new tbTaIdentificacionBll();
        tbTaLugarExpBll lugarExpBll = new tbTaLugarExpBll();
        tbTaTipoSocietarioBll tpsBll = new tbTaTipoSocietarioBll();

        try {
            tiposSocietarios = tpsBll.listForReg();
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }



    public void buscaMatriculaWS() throws Exception {
        WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        ArrayOfMatriculaDatosOperativos Result = port.srvMatriculaDatosOperativos("1020457026", "fundempresa1234567890", txtMatricula);
        txtMatricula = Result.getMatriculaDatosOperativos().get(0).getIdMatricula();
        usuarioRegistro.setSociedadConstituye(Result.getMatriculaDatosOperativos().get(0).getRazonSocial());
        usuarioRegistro.setCorreoElectronico(Result.getMatriculaDatosOperativos().get(0).getCorreoElectronico());
        usuarioRegistro.setIdMatriculaAsociada(Result.getMatriculaDatosOperativos().get(0).getIdMatricula());
        setCorreoMask(usuarioRegistro.getCorreoElectronico().toString());
        String remp="*************";
        if(getCorreoMask().length()>6){
            remp=getCorreoMask().substring(0,6)+remp;
            setCorreoMask(remp);
        }
    }

    public void registrarSolicitud() {
        try {
            tbUSLoginBll usBll = new tbUSLoginBll();
            usBll.add(usuarioRegistro);
            usuarioRegistro = new tbUSLogin();
            String Mensaje = "Se registró la solicitud y se envió la contraseña al correo registrado en el Registro de Comercio. \n"
                    + "En caso de no recibir el correo revise la carpeta de 'Correo no Deseado', caso contrario comuníquese con la línea gratuita 800-10-7990";
            UtilsPresentacion.addMessage(Mensaje, "Registro Exitoso", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            UtilsPresentacion.addMessage("Error en el Registro de Usuario", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void valideTipoId(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            if (tpA.equals("1")) {
                reqOrgId = true;
            } else {
                reqOrgId = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(registroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayOfMatriculaDatos srvMatricula(java.lang.String idContrato, java.lang.String keyContrato, java.lang.String idMatricula) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        return port.srvMatricula(idContrato, keyContrato, idMatricula);
    }

    private ArrayOfMatriculaDatosOperativos srvMatriculaDatosOperativos(java.lang.String idContrato, java.lang.String keyContrato, java.lang.String idMatricula) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        return port.srvMatriculaDatosOperativos(idContrato, keyContrato, idMatricula);
    }

    public String getCorreoMask() {
        return correoMask;
    }

    public void setCorreoMask(String correoMask) {
        this.correoMask = correoMask;
    }

    public String getMailConfirma() {
        return mailConfirma;
    }

    public void setMailConfirma(String mailConfirma) {
        this.mailConfirma = mailConfirma;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getReqOrgId() {
        return reqOrgId;
    }

    public void setReqOrgId(Boolean reqOrgId) {
        this.reqOrgId = reqOrgId;
    }

    public WebServiceRCB getService() {
        return service;
    }

    public void setService(WebServiceRCB service) {
        this.service = service;
    }

    public boolean isBlAcepContrato() {
        return blAcepContrato;
    }

    public void setBlAcepContrato(boolean blAcepContrato) {
        this.blAcepContrato = blAcepContrato;
    }

    public String getTipUser() {
        return tipUser;
    }

    public void setTipUser(String tipUser) {
        this.tipUser = tipUser;
    }

    public List<tbTaTipoSocietario> getTiposSocietarios() {
        return tiposSocietarios;
    }

    public void setTiposSocietarios(List<tbTaTipoSocietario> tiposSocietarios) {
        this.tiposSocietarios = tiposSocietarios;
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

    public tbInMatriculas getInfoMatricula() {
        return infoMatricula;
    }

    public void setInfoMatricula(tbInMatriculas infoMatricula) {
        this.infoMatricula = infoMatricula;
    }

    public String getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(String txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public tbUSLogin getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(tbUSLogin usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
}

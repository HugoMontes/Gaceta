package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.*;
import bo.gaceta.rcb.modelo.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named(value = "registroBeanNew")
@ViewScoped
public class registroBeanNew implements Serializable {
    Boolean reqOrgId = true;
    private boolean blAcepContrato;
    private List<tbTaTipoSocietario> tiposSocietarios;
    private List<tbTaIdentificacion> tiposIdentificacion;
    private List<tbTaLugarExp> lugarExp;
    private List<taTablas> listAbreviacion;

    private tbInMatriculas infoMatricula;
    private String terminacion;

    private tbUSLogin usuarioRegistro;
    private String txtMatricula;

    private String mailConfirma;

    public registroBeanNew() {

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
        taTablasBll abvBll = new taTablasBll();

        try {
            tiposSocietarios = tpsBll.listForReg();
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tiposIdentificacion = identBll.list();
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lugarExp = lugarExpBll.list();
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listAbreviacion = abvBll.list(1, "3");
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void cargaAbreviacion(ValueChangeEvent evento) {
        taTablasBll abvBll = new taTablasBll();
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idTipo = evento.getNewValue().toString();
            listAbreviacion = abvBll.list(1, idTipo);
        } catch (Exception ex) {
            Logger.getLogger(registroBeanNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarSolicitud() {
        if (mailConfirma.equals(usuarioRegistro.getCorreoElectronico())) {
            try {
                tbUSLoginBll usBll = new tbUSLoginBll();
                taTablasBll taBll=new taTablasBll();
                usuarioRegistro.setIdMatriculaAsociada("0");
                String txtRazonFinal = "";
                // txtRazonFinal = usuarioRegistro.getSociedadConstituye() + " " + terminacion;
                txtRazonFinal = usuarioRegistro.getSociedadConstituye() + " " + (taBll.getByid(Integer.parseInt(terminacion)).getTxtValor());
                usuarioRegistro.setSociedadConstituye(txtRazonFinal);
                usBll.add(usuarioRegistro);
                usuarioRegistro = new tbUSLogin();
                mailConfirma = "";
                String Mensaje = "Se registró la solicitud y se envió la contraseña al correo proporcionado. \n"
                        + "En caso de no recibir el correo revise la carpeta de 'Correo no Deseado', caso contrario comuníquese  con la línea gratuita 800-10-7990";
                UtilsPresentacion.addMessage(Mensaje, "Registro Exitoso", FacesMessage.SEVERITY_INFO);
            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro de Usuario", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Error en el Correo Electronico", "Las cuentas de correo electronico deben coincidir", FacesMessage.SEVERITY_ERROR);
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

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public String getMailConfirma() {
        return mailConfirma;
    }

    public void setMailConfirma(String mailConfirma) {
        this.mailConfirma = mailConfirma;
    }

    public Boolean getReqOrgId() {
        return reqOrgId;
    }

    public void setReqOrgId(Boolean reqOrgId) {
        this.reqOrgId = reqOrgId;
    }

    public boolean isBlAcepContrato() {
        return blAcepContrato;
    }

    public void setBlAcepContrato(boolean blAcepContrato) {
        this.blAcepContrato = blAcepContrato;
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

    public List<taTablas> getListAbreviacion() {
        return listAbreviacion;
    }

    public void setListAbreviacion(List<taTablas> listAbreviacion) {
        this.listAbreviacion = listAbreviacion;
    }

    public tbInMatriculas getInfoMatricula() {
        return infoMatricula;
    }

    public void setInfoMatricula(tbInMatriculas infoMatricula) {
        this.infoMatricula = infoMatricula;
    }

    public String getTerminacion() {
        return terminacion;
    }

    public void setTerminacion(String terminacion) {
        this.terminacion = terminacion;
    }

    public tbUSLogin getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(tbUSLogin usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(String txtMatricula) {
        this.txtMatricula = txtMatricula;
    }
    // </editor-fold>
}

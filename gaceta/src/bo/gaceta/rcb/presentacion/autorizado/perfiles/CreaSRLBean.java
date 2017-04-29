package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.*;
import bo.gaceta.rcb.bll.utils.EnvioMail;
import bo.gaceta.rcb.modelo.*;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.models.actividad;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "creaSRLBean")
@ViewScoped
public class CreaSRLBean implements Serializable {
    /*Campos Requeridos en wizzard volver a falsos*/
    Boolean reqAct = true;
    Boolean reqSoc = true;
    Boolean reqOrgId = true;
    Boolean reqBien = false;
    Boolean roBien = true;
    String colorSaldo = "";
    /*Datos del Capital*/
    private int capitalXAsig;
    private int capitalAsig;
    private String tipoAporte;
    /*Datos de los Socios*/
    private String tipoIdentifica;
    private String numIdentifica;

    // <editor-fold defaultstate="collapsed" desc="Getter Setter methods. Click on the + sign on the left to edit the code.">
    public Boolean getReqAct() {
        return reqAct;
    }

    public void setReqAct(Boolean reqAct) {
        this.reqAct = reqAct;
    }

    public Boolean getReqSoc() {
        return reqSoc;
    }

    public void setReqSoc(Boolean reqSoc) {
        this.reqSoc = reqSoc;
    }

    public Boolean getReqOrgId() {
        return reqOrgId;
    }

    public void setReqOrgId(Boolean reqOrgId) {
        this.reqOrgId = reqOrgId;
    }

    public Boolean getReqBien() {
        return reqBien;
    }

    public void setReqBien(Boolean reqBien) {
        this.reqBien = reqBien;
    }

    public Boolean getRoBien() {
        return roBien;
    }

    public void setRoBien(Boolean roBien) {
        this.roBien = roBien;
    }

    public String getColorSaldo() {
        return colorSaldo;
    }

    public void setColorSaldo(String colorSaldo) {
        this.colorSaldo = colorSaldo;
    }

    public String getTipoAporte() {
        return tipoAporte;
    }

    public void setTipoAporte(String tipoAporte) {
        this.tipoAporte = tipoAporte;
    }

    public String getTipoIdentifica() {
        return tipoIdentifica;
    }

    public void setTipoIdentifica(String tipoIdentifica) {
        this.tipoIdentifica = tipoIdentifica;
    }

    public String getNumIdentifica() {
        return numIdentifica;
    }

    public void setNumIdentifica(String numIdentifica) {
        this.numIdentifica = numIdentifica;
    }

    public String getExtIdentifica() {
        return extIdentifica;
    }

    public void setExtIdentifica(String extIdentifica) {
        this.extIdentifica = extIdentifica;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getPaternoSocio() {
        return paternoSocio;
    }

    public void setPaternoSocio(String paternoSocio) {
        this.paternoSocio = paternoSocio;
    }

    public String getMaternoSocio() {
        return maternoSocio;
    }

    public void setMaternoSocio(String maternoSocio) {
        this.maternoSocio = maternoSocio;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfesionSocio() {
        return profesionSocio;
    }

    public void setProfesionSocio(String profesionSocio) {
        this.profesionSocio = profesionSocio;
    }

    public String getDomicilioSocio() {
        return domicilioSocio;
    }

    public void setDomicilioSocio(String domicilioSocio) {
        this.domicilioSocio = domicilioSocio;
    }

    public String getZonaDomicilioSocio() {
        return zonaDomicilioSocio;
    }

    public void setZonaDomicilioSocio(String zonaDomicilioSocio) {
        this.zonaDomicilioSocio = zonaDomicilioSocio;
    }

    public String getNacionalidadSocio() {
        return nacionalidadSocio;
    }

    public void setNacionalidadSocio(String nacionalidadSocio) {
        this.nacionalidadSocio = nacionalidadSocio;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public int getAporteCapital() {
        return aporteCapital;
    }

    public void setAporteCapital(int aporteCapital) {
        this.aporteCapital = aporteCapital;
    }

    public int getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas = numCuotas;
    }

    public double getActuaValNuCuotas() {
        return ActuaValNuCuotas;
    }

    public void setActuaValNuCuotas(double actuaValNuCuotas) {
        ActuaValNuCuotas = actuaValNuCuotas;
    }

    public double getVerifnumCuotas() {
        return VerifnumCuotas;
    }

    public void setVerifnumCuotas(double verifnumCuotas) {
        VerifnumCuotas = verifnumCuotas;
    }

    public String getCodigoMunip() {
        return codigoMunip;
    }

    public void setCodigoMunip(String codigoMunip) {
        this.codigoMunip = codigoMunip;
    }

    public String getCodigoMunipDoc() {
        return codigoMunipDoc;
    }

    public void setCodigoMunipDoc(String codigoMunipDoc) {
        this.codigoMunipDoc = codigoMunipDoc;
    }

    public List<tbInMunicipios> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<tbInMunicipios> municipios) {
        this.municipios = municipios;
    }

    public List<tbInMunicipios> getMunicipiosDoc() {
        return municipiosDoc;
    }

    public void setMunicipiosDoc(List<tbInMunicipios> municipiosDoc) {
        this.municipiosDoc = municipiosDoc;
    }

    public List<TbInCiiu> getListaCiiuSeccion() {
        return listaCiiuSeccion;
    }

    public void setListaCiiuSeccion(List<TbInCiiu> listaCiiuSeccion) {
        this.listaCiiuSeccion = listaCiiuSeccion;
    }

    public String getVarIdSeccion() {
        return varIdSeccion;
    }

    public void setVarIdSeccion(String varIdSeccion) {
        this.varIdSeccion = varIdSeccion;
    }

    public List<TbInCiiu> getListaCiiuDivision() {
        return listaCiiuDivision;
    }

    public void setListaCiiuDivision(List<TbInCiiu> listaCiiuDivision) {
        this.listaCiiuDivision = listaCiiuDivision;
    }

    public String getVarIdDivision() {
        return varIdDivision;
    }

    public void setVarIdDivision(String varIdDivision) {
        this.varIdDivision = varIdDivision;
    }

    public List<TbInCiiu> getListaCiiuGrupo() {
        return listaCiiuGrupo;
    }

    public void setListaCiiuGrupo(List<TbInCiiu> listaCiiuGrupo) {
        this.listaCiiuGrupo = listaCiiuGrupo;
    }

    public String getVarIdGrupo() {
        return varIdGrupo;
    }

    public void setVarIdGrupo(String varIdGrupo) {
        this.varIdGrupo = varIdGrupo;
    }

    public List<TbInCiiu> getListaCiiuClase() {
        return listaCiiuClase;
    }

    public void setListaCiiuClase(List<TbInCiiu> listaCiiuClase) {
        this.listaCiiuClase = listaCiiuClase;
    }

    public String getVarIdClase() {
        return varIdClase;
    }

    public void setVarIdClase(String varIdClase) {
        this.varIdClase = varIdClase;
    }

    public List<TbInCiiu> getListaSeleccionPrev() {
        return listaSeleccionPrev;
    }

    public void setListaSeleccionPrev(List<TbInCiiu> listaSeleccionPrev) {
        this.listaSeleccionPrev = listaSeleccionPrev;
    }

    public taSocios getSocioSel() {
        return socioSel;
    }

    public void setSocioSel(taSocios socioSel) {
        this.socioSel = socioSel;
    }

    public String getTxtBuscaClase() {
        return txtBuscaClase;
    }

    public void setTxtBuscaClase(String txtBuscaClase) {
        this.txtBuscaClase = txtBuscaClase;
    }

    public List<actividad> getActividadClase() {
        return actividadClase;
    }

    public void setActividadClase(List<actividad> actividadClase) {
        this.actividadClase = actividadClase;
    }

    public actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getVistaPreliminar() {
        return vistaPreliminar;
    }

    public void setVistaPreliminar(String vistaPreliminar) {
        this.vistaPreliminar = vistaPreliminar;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getPublicacionCompleta() {
        return PublicacionCompleta;
    }

    public void setPublicacionCompleta(String publicacionCompleta) {
        PublicacionCompleta = publicacionCompleta;
    }

    public tbPbPublicaciones getPublicacionRegistro() {
        return publicacionRegistro;
    }

    public void setPublicacionRegistro(tbPbPublicaciones publicacionRegistro) {
        this.publicacionRegistro = publicacionRegistro;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getNroOrigen() {
        return nroOrigen;
    }

    public void setNroOrigen(String nroOrigen) {
        this.nroOrigen = nroOrigen;
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

    public Date getFecDocumento() {
        return fecDocumento;
    }

    public void setFecDocumento(Date fecDocumento) {
        this.fecDocumento = fecDocumento;
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

    public taEmpresaBll getEmpresaBll() {
        return empresaBll;
    }

    public void setEmpresaBll(taEmpresaBll empresaBll) {
        this.empresaBll = empresaBll;
    }
    // </editor-fold>

    private String extIdentifica;
    private String nombreSocio;
    private String paternoSocio;
    private String maternoSocio;
    private String estadoCivil;
    private String profesionSocio;
    private String domicilioSocio;
    private String zonaDomicilioSocio;
    private String nacionalidadSocio;
    private String descripcionBien;
    private int aporteCapital;
    private int numCuotas;
    private double participacion;
    private double ActuaValNuCuotas;
    /*Validacion de Cuotas de Capital*/
    private double VerifnumCuotas;
    private String codigoMunip;
    private String codigoMunipDoc;
    private List<tbInMunicipios> municipios;
    private List<tbInMunicipios> municipiosDoc;
    private List<TbInOrigenes> origenes;
    /*Variables para la Clasificación CIIU*/
    private List<TbInCiiu> listaCiiuSeccion;
    private String varIdSeccion = "";
    private List<TbInCiiu> listaCiiuDivision;
    private String varIdDivision = "";
    private List<TbInCiiu> listaCiiuGrupo;
    private String varIdGrupo = "";
    private List<TbInCiiu> listaCiiuClase;
    private String varIdClase = "";
    private List<TbInCiiu> listaSeleccionPrev = new ArrayList<>();
    private TbInCiiu itemSeleccionado;
    /*Variables para Registros de Socios*/
    private List<taSocios> listaSocios = new ArrayList<>();
    private taSocios socioReg;
    private taSocios socioSel;
    /*Otras Variables*/
    private String txtBuscaClase = "";
    private List<actividad> actividadClase = new ArrayList<>();
    private actividad actividadSeleccionada;
    private Boolean aceptacion;
    /*Aceptacion*/
    private String vistaPreliminar;
    private tbUSLogin usuarioSesion;
    private String PublicacionCompleta;
    private tbPbPublicaciones publicacionRegistro;
    private String Distrito = "";
    private String origen = "";
    private String nroOrigen = "";
    private String dptoOrigen = "";
    private String distOrigen = "";
    @Past(message = "Debe seleccionar una fecha anterior a la de hoy")
    private Date fecDocumento;

    /*validacion Wizzar*/
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value1;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value2;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value3;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value4;
    //@AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value5;

    private taEmpresa empresaData;
    private taEmpresaBll empresaBll;
    private taCapital capital;


    public CreaSRLBean() {
        setCapital(new taCapital());
        setCapital(new taCapital());
        actividadSeleccionada = new actividad();
        setItemSeleccionado(new TbInCiiu());
    }

    @PostConstruct
    public void ini() throws Exception {
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        TbInOrigenesBll bllOrig = new TbInOrigenesBll();
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        taEmpresaBll empresaBll = new taEmpresaBll();
        try {
            setOrigenes(bllOrig.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            listaCiiuSeccion = bllCiiu.listSeccion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setEmpresaData(new taEmpresa());
        if (empresaBll.verifByIdEmpresa(usuarioSesion.getIdLoginEmpresa())) {
            try {
                setEmpresaData(empresaBll.getIdEmpresa(usuarioSesion.getIdLoginEmpresa()));
                tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
                try {
                    String idDepto = empresaData.getIdDepartamentoDoc();
                    municipiosDoc = munipBll.listByDpto(idDepto);
                } catch (Exception ex) {
                    Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getEmpresaData() != null) {
                fecDocumento = getEmpresaData().getFechaDocumento();
            }

            if (empresaData.getCiiuObjeto() != null) {
                actividadSeleccionada.setClase(bllCiiu.getDescCLaseCIIU(empresaData.getCiiuObjeto()));
            }
        }
        try {
            taCapitalBll capCarga = new taCapitalBll();
            if (capCarga.verifByIdEmpresa(usuarioSesion.getIdLoginEmpresa())) {
                setCapital(capCarga.getIdEmpresa(usuarioSesion.getIdLoginEmpresa()));
            } else {
                getCapital().setCodigoEmpresa(usuarioSesion.getIdLoginEmpresa());
                getCapital().setCapitalSocialNum(0);
                getCapital().setCntCuotasCapital(0);
                capCarga.add(getCapital());
            }
        } catch (Exception exCap) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, exCap);
        }
        try {
            taSociosBll socCarga = new taSociosBll();
            listaSocios = socCarga.list(usuarioSesion.getIdLoginEmpresa());
            this.actuaSaldo(listaSocios);
        } catch (Exception exSoc) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, exSoc);
        }
        publicacionRegistro = new tbPbPublicaciones();
        reqAct = true;
        reqSoc = true;
        reqOrgId = true;
        colorSaldo = "#3CB371";
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();

        if (txtSig.equals("datosSociedad")) {
            if (getEmpresaData().getCodigoEmpresa() == null) {
                taEmpresaBll empresaBll = new taEmpresaBll();
                setEmpresaData(new taEmpresa());
                getEmpresaData().setDenominacion(usuarioSesion.getSociedadConstituye());
                getEmpresaData().setCodigoEmpresa(usuarioSesion.getIdLoginEmpresa());
                getEmpresaData().setIdUsuario(usuarioSesion.getIdLoginEmpresa());
                getEmpresaData().setCtrEstado("datosTestimonio");
                empresaBll.add(getEmpresaData());
                okVerif = true;
            } else {
                taEmpresaBll empresaBll = new taEmpresaBll();
                getEmpresaData().setCtrEstado("datosTestimonio");
                empresaBll.update(empresaData);
                tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
                try {
                    String idDepto = empresaData.getDepartamento();
                    municipios = munipBll.listByDpto(idDepto);
                } catch (Exception ex) {
                    Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    taCapitalBll capIni = new taCapitalBll();
                    setCapital(capIni.getIdEmpresa(usuarioSesion.getIdLoginEmpresa()));
                } catch (Exception ex) {
                    Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                okVerif = true;
            }

            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            if (empresaData.getFechaDocumento().after(now)) {
                UtilsPresentacion.addMessage("La fecha de publicación debe ser anterior o igual a la fecha a la actual.", "La fecha de publicación debe ser anterior o igual a la fecha a la actual.", FacesMessage.SEVERITY_INFO);
                okVerif = false;
            }
        }

        if (txtSig.equals("datosCapital")) {
            if (empresaData.getCiiuObjeto() == null) {
                okVerif = false;
                UtilsPresentacion.addMessage("Debe seleccionar una actividad principal", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            } else {
                taEmpresaBll empresaBll = new taEmpresaBll();
                getEmpresaData().setCtrEstado("datosSociedad");
                empresaBll.update(empresaData);
                okVerif = true;
            }
        }

        if (txtSig.equals("datosSocios")) {
            int valorMod = 0;
            int valorModCuotas = 0;
            getCapital().setCntCuotasCapital(getCapital().getCapitalSocialNum() / getCapital().getVrCuotasCapital());
            VerifnumCuotas = getCapital().getCntCuotasCapital();
            valorMod = getCapital().getVrCuotasCapital() % 100;
            valorModCuotas = getCapital().getCapitalSocialNum() % getCapital().getVrCuotasCapital();
            if (valorModCuotas == 0) {
                if ((getCapital().getCapitalSocialNum() / getCapital().getVrCuotasCapital()) >= 2) {
                    if (valorMod == 0 & getCapital().getCntCuotasCapital() >= 2) {
                        setCapitalAsig(0);
                        this.actuaSaldo(listaSocios);
                        VerifnumCuotas = getCapital().getCntCuotasCapital() - ActuaValNuCuotas;
                        if (getCapital().getCapitalSocialNum() < getCapitalAsig()) {
                            okVerif = false;
                            UtilsPresentacion.addMessage("No se puede reducir el capital, elimine algun socio para poder reducir el capital", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                        } else {
                            setCapitalXAsig(getCapital().getCapitalSocialNum() - getCapitalAsig());
                            taCapitalBll capBll = new taCapitalBll();
                            getCapital().setCodigoEmpresa(empresaData.getCodigoEmpresa());
                            if (capBll.verifByIdEmpresa(empresaData.getCodigoEmpresa())) {
                                capBll.update(getCapital());
                            } else {
                                capBll.add(getCapital());
                            }
                            okVerif = true;
                        }
                    } else {
                        okVerif = false;
                        UtilsPresentacion.addMessage("El capital de la sociedad debe estar dividido en cuotas de igual valor, que serán de cien 00/100 bolivianos (Bs. 100) o  múltiplos  de  cien.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    okVerif = false;
                    UtilsPresentacion.addMessage("El capital de la sociedad debe estar dividido en cuotas de igual valor, que serán de cien 00/100 bolivianos (Bs. 100) o múltiplos de cien y el monto del valor de la cuota deberá permitir la división del capital en 2 o más cuotas, conforme al aporte y participación de cada socio.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                okVerif = false;
                UtilsPresentacion.addMessage("El valor del aporte del socio da como resultado cuotas de capital fraccionadas, revise el monto establecido como aporte para evitar el fraccionamiento de las cuotas de capital.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        }

        if (txtSig.equals("verifica")) {
            if (value5) {
                reqSoc = false;
                if (getListaSocios().size() >= 2 & getListaSocios().size() <= 25) {
                    if (VerifnumCuotas == 0.0) {
                        okVerif = true;
                        reqSoc = false;
                        reqOrgId = false;
                        this.generaPreview();
                    } else {
                        okVerif = false;
                        reqSoc = true;
                        reqOrgId = true;
                        UtilsPresentacion.addMessage("Existe un error al consignar la distribución del capital, verifique los datos consignados.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    okVerif = false;
                    reqSoc = true;
                    reqOrgId = true;
                    UtilsPresentacion.addMessage("La sociedad no podrá estar integrada por menos de dos  socios o más de veinticinco.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                okVerif = false;
                UtilsPresentacion.addMessage("Debe aceptar la verificacion de la informacion declarada.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    public void valideBien(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            if (tpA.equals("B")) {
                reqBien = true;
                roBien = false;
                descripcionBien = "";
            } else {
                reqBien = false;
                roBien = true;
                descripcionBien = "No Aplica";

            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valideTipoId(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            if (tpA.equals("Cedula de Identidad")) {
                reqOrgId = true;
            } else {
                reqOrgId = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSocio(taSocios socioDel) throws Exception {
        //getSociosSociedad().remove(socioDel);
        taSociosBll sociosReg = new taSociosBll();
        sociosReg.delete(socioDel);
        listaSocios = sociosReg.list(usuarioSesion.getIdLoginEmpresa());
        this.actuaSaldo(listaSocios);
    }

    private void actuaSaldo(List<taSocios> sociosCalc) {
        setCapitalAsig(0);
        Integer idSocio = 1;
        ActuaValNuCuotas = 0;
        try {
            for (taSocios listaSocios : sociosCalc) {
                listaSocios.setNroSocio(idSocio);
                ActuaValNuCuotas = ActuaValNuCuotas + listaSocios.getCuotasCapital();
                setCapitalAsig((int) (getCapitalAsig() + listaSocios.getAporteCapitalMon()));
                idSocio++;
            }
            setCapitalXAsig(capital.getCapitalSocialNum() - getCapitalAsig());
            VerifnumCuotas = capital.getCntCuotasCapital() - ActuaValNuCuotas;
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        reqSoc = true;
        reqOrgId = true;
        colorSaldo = "#3CB371";
    }

    public void cargaMunip(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            municipios = munipBll.listByDpto(idDepto);
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaMunipDoc(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            municipiosDoc = munipBll.listByDpto(idDepto);
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaDivision(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idSeccionFilt = evento.getNewValue().toString();
            if (!idSeccionFilt.isEmpty()) {
                listaCiiuDivision = bllCiiu.listDivision(idSeccionFilt);
                listaCiiuClase = null;
                listaCiiuGrupo = null;
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaGrupo(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idDivisionFilt = evento.getNewValue().toString();
            if (idDivisionFilt != null) {
                listaCiiuGrupo = bllCiiu.listGrupo(idDivisionFilt);
                listaCiiuClase = null;
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaClase(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idGrupoFilt = evento.getNewValue().toString();
            if (idGrupoFilt != null | !idGrupoFilt.isEmpty()) {
                listaCiiuClase = bllCiiu.listClaseByGroup(idGrupoFilt);
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscaClase() {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            listaCiiuGrupo = null;
            listaCiiuDivision = null;
            listaCiiuClase = bllCiiu.listFiltroClase(txtBuscaClase);
        } catch (Exception ex) {
            Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowSelect(SelectEvent event) {
        ((TbInCiiu) event.getObject()).getIdClase();
    }

    public void registraSocio() {
        try {
            Integer indice = getListaSocios().size();
            indice++;
            if (aporteCapital > 0) {
                double vrParticipa;
                int valorMod;
                numCuotas = aporteCapital / getCapital().getVrCuotasCapital();
                valorMod = aporteCapital % getCapital().getVrCuotasCapital();
                if (valorMod == 0) {
                    vrParticipa = ((double) aporteCapital / (double) capital.getCapitalSocialNum()) * 100;
                    if ((VerifnumCuotas - numCuotas) < 0) {
                        UtilsPresentacion.addMessage("Revise el valor del aporte para evitar exceder  la cantidad total de cuotas", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    } else {
                        VerifnumCuotas = VerifnumCuotas - numCuotas;
                        setCapitalAsig(getCapitalAsig() + aporteCapital);
                        setCapitalXAsig(capital.getCapitalSocialNum() - getCapitalAsig());
                        setParticipacion(vrParticipa);
                        //socios tmpSocio = new socios();
                        taSocios socioAdd = new taSocios();
                        socioAdd.setCodigoEmpresa(empresaData.getCodigoEmpresa());
                        socioAdd.setNroSocio(indice);
                        socioAdd.setNombreSocio(nombreSocio.toUpperCase());
                        socioAdd.setPaternoSocio(paternoSocio.toUpperCase());
                        socioAdd.setMaternoSocio(maternoSocio.toUpperCase());
                        socioAdd.setTipoidentificacion(tipoIdentifica);
                        socioAdd.setIdentificacion(numIdentifica);
                        socioAdd.setIdExpedido(extIdentifica);
                        socioAdd.setNacionalidad(nacionalidadSocio);
                        socioAdd.setEstadoCivil(estadoCivil);
                        socioAdd.setProfesion(profesionSocio);
                        socioAdd.setDomicilio(domicilioSocio);
                        socioAdd.setAporteCapitalMon((double) aporteCapital);
                        socioAdd.setCuotasCapital((double) numCuotas);
                        socioAdd.setZona(zonaDomicilioSocio);
                        socioAdd.setParticipaPercent(vrParticipa);
                        socioAdd.setCtrTipoAporte(tipoAporte);
                        socioAdd.setCargo("");
                        if (tipoAporte.equals("B")) {
                            socioAdd.setAporteBien(descripcionBien);
                        }
                        // ADICIONANDO NUEVO SOCIO
                        taSociosBll sociosReg = new taSociosBll();
                        sociosReg.add(socioAdd);
                        listaSocios = sociosReg.list(empresaData.getCodigoEmpresa());
                        // LIMPIANDO CAMPOS DE ENTRADA
                        numIdentifica = "";
                        extIdentifica = "";
                        tipoIdentifica = "";
                        nombreSocio = "";
                        paternoSocio = "";
                        maternoSocio = "";
                        estadoCivil = "";
                        profesionSocio = "";
                        domicilioSocio = "";
                        nacionalidadSocio = "";
                        aporteCapital = 0;
                        numCuotas = 0;
                        setParticipacion(0);
                        zonaDomicilioSocio = "";
                        descripcionBien = "";
                        tipoAporte = "";
                        reqBien = false;
                        roBien = true;
                        if (getCapitalAsig() == getCapital().getCapitalSocialNum()) {
                            reqSoc = false;
                            reqOrgId = false;
                            colorSaldo = "#B22222";
                        }
                    }
                } else {
                    UtilsPresentacion.addMessage("El valor del aporte del socio da como resultado cuotas de capital fraccionadas.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                UtilsPresentacion.addMessage("Debe consignar el valor del aporte del socio.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception exAdd) {
            UtilsPresentacion.addMessage(exAdd.getMessage(), "Error en Aplicación", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void registraActividad(TbInCiiu itemSel) {
        actividadSeleccionada.setIdClase(itemSel.getIdClase());
        actividadSeleccionada.setClase(itemSel.getClase());
        empresaData.setCiiuObjeto(itemSel.getIdClase());
    }

    public void registraPublicacion() {
        if (aceptacion) {
            try {
                tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                String TituloPublica = "Escritura Pública de Constitución N°" + publicacionRegistro.getNroDocumento() + " de la empresa: " + usuarioSesion.getSociedadConstituye();
                publicacionRegistro.setTituloPublica(TituloPublica);
                String resumenPublica = "Testimonio de Constitución N°" + publicacionRegistro.getNroDocumento() + " de " + publicacionRegistro.getOrigenDocumento() + " del distrito de" + Distrito + " de la empresa " + usuarioSesion.getSociedadConstituye();
                publicacionRegistro.setTextoPublica(resumenPublica);
                publicacionRegistro.setCtrTipoPublica(1);
                publicacionRegistro.setTextoCompleto(PublicacionCompleta);
                publicacionRegistro.setCtrDisplay("0");
                String codigoPublica = usBll.add(publicacionRegistro);
                publicacionRegistro = null;
                PublicacionCompleta = null;
                aceptacion = false;
                String txtMensajeResultado

                        = "<div class=txtLabelNotifica>Su publicaci&oacute;n ha sido almacenada correctamente para ser revisada por el Registro de Comercio, el c&oacute;digo asignado es:</div>"
                        + "<div align=center class=txtCodigoPublica>" + codigoPublica + "</div>"
                        + "<p/>"
                        + "<div class=txtLabelNotifica>Si su publicaci&oacute;n requiere alguna modificaci&oacute;n, se le enviar&aacute; un mensaje al correo electr&oacute;nico registrado, caso contrario</div>"
                        + "<div class=txtLabelNotifica>se le comunicar&aacute; la fecha de publicaci&oacute;n en la Gaceta de Comercio, junto el c&oacute;digo de identificaci&oacute;n de la publicaci&oacute;n</div>"
                        + "<div class=txtLabelNotifica></div>"
                        + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";

                String txtNotificacion = "<br><i>Notificaci&oacute;n de Publicaci&oacute;n</i> <br>";
                txtNotificacion += "<b></b>";
                txtNotificacion += "<i>El C&oacute;digo de Publicaci&oacute;n es: " + codigoPublica + "</i> <br>";
                txtNotificacion += "<i>El tr&aacute;mite se encuentra en revisi&oacute;n por el Registro de Comercio<i/> <br>";
                txtNotificacion += "</p><b>Esta Publicaci&oacute;n est&aacute; sujeta a la revisi&oacute;n del Registro de Comercio, una vez se apruebe la publicaci&oacute;n se le notificar&aacute; inmediatamente, para que continue con el tr&aacute;mite correspondiente</b>";
                EnvioMail notif = new EnvioMail();
                notif.envioMailEstado(usuarioSesion.getCorreoElectronico(), txtNotificacion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro de la Publicación", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Debe aceptar los términos para realizar la publicacion", "Debe completar los campos requeridos", FacesMessage.SEVERITY_INFO);
        }
    }

    private void generaPreview() {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        TbInOrigenesBll origenBll = new TbInOrigenesBll();

        String capitalTxt = Double.toString(capital.getCapitalSocialNum());
        String cntTxtCuotas = Double.toString(capital.getCntCuotasCapital());
        String vrTxtCuotas = Double.toString(capital.getVrCuotasCapital());
        String txtAñosVig = Integer.toString(empresaData.getDuracionNum());
        vistaPreliminar = "";
        String vistaSocios = "";
        String vistaCiiu = "";
        vistaCiiu = "La Actividad Principal de la Sociedad será:" + "\r\n"
                + "Codigo Clase Ciiu:" + empresaData.getCiiuObjeto() + ", Descripción:" + bllCiiu.getDescCLaseCIIU(empresaData.getCiiuObjeto()) + "\r\n"
                + "La Sociedad tiene las siguientes actividades Secundarias:" + "\r\n"
                + empresaData.getObjetoSocial() + "\r\n" + "\r\n";


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String txtTestimonio = "Conforme a testomonio N°:" + empresaData.getNroTestimonio() + " de fecha " + df.format(empresaData.getFechaDocumento()) + ", de la " + origenBll.desByOrigen(empresaData.getIdOrigenDoc()) + " N°:" + empresaData.getNroNotaria() + " del Distrito Judicial " + munipBll.desByIdMunip(empresaData.getIdMunicipioDoc()) + ". Se constituye una Sociedad de Responsabilidad Limitada con los siguientes datos." + "\r\n" + "\r\n";

        vistaPreliminar = txtTestimonio;
        vistaPreliminar = vistaPreliminar + "La sociedad girará bajo la razón social o denominación de '" + empresaData.getDenominacion() + "'" + "\r\n" + "\r\n"
                + "Tendrá su domicilio en el departamento de " + munipBll.getById(empresaData.getDomicilio()).getDepartamento() + ", " + munipBll.getById(empresaData.getDomicilio()).getMunicipio() + ", Estado Plurinacional de Bolivia" + "\r\n" + "\r\n"
                + "La sociedad tendrá una duración de " + txtAñosVig + " años computables partir de la fecha de inscripción en el Registro de Comercio de Bolivia" + "\r\n" + "\r\n"
                + vistaCiiu
                + "El capital de la Sociedad es de " + capitalTxt + "/100 dividido en " + cntTxtCuotas + " cuotas de capital de " + vrTxtCuotas + "/100 Bolivianos cada una." + "\r\n" + "\r\n"
                + "La sociedad esta compuesta por los siguentes socios(as):" + "\r\n";

        for (taSocios listaSocios : getListaSocios()) {
            vistaSocios = vistaSocios + "Socio(a): " + listaSocios.getNombreSocio() + " " + listaSocios.getPaternoSocio() + " " + listaSocios.getNombreSocio() + ", con " + listaSocios.getTipoidentificacion() + " " + listaSocios.getIdentificacion() + " expedida en " + listaSocios.getIdExpedido()
                    + ", Estado Civil:" + listaSocios.getEstadoCivil() + ", de profesión:" + listaSocios.getProfesion() + ", Nacionalidad:" + listaSocios.getNacionalidad() + ", con domicilio:" + listaSocios.getDomicilio() + " de la zona " + listaSocios.getZona() + "\r\n"
                    + "Aporto un total de " + Double.toString(listaSocios.getCuotasCapital()) + " Cuotas de Capital, con un aporte de Bs." + Double.toString(listaSocios.getAporteCapitalMon()) + " representando el " + Double.toString(listaSocios.getParticipaPercent()) + "% de la participación en la Sociedad" + "\r\n" + "\r\n";
        }
        vistaPreliminar = vistaPreliminar + vistaSocios;
        PublicacionCompleta = vistaPreliminar;
    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public taCapital getCapital() {
        return capital;
    }

    public void setCapital(taCapital capital) {
        this.capital = capital;
    }

    public List<taSocios> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(List<taSocios> listaSocios) {
        this.listaSocios = listaSocios;
    }

    public taSocios getSocioReg() {
        return socioReg;
    }

    public void setSocioReg(taSocios socioReg) {
        this.socioReg = socioReg;
    }

    public double getParticipacion() {
        return participacion;
    }

    public void setParticipacion(double participacion) {
        this.participacion = participacion;
    }

    public TbInCiiu getItemSeleccionado() {
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TbInCiiu itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public taEmpresa getEmpresaData() {
        return empresaData;
    }

    public void setEmpresaData(taEmpresa empresaData) {
        this.empresaData = empresaData;
    }

    public List<TbInOrigenes> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(List<TbInOrigenes> origenes) {
        this.origenes = origenes;
    }

    public int getCapitalXAsig() {
        return capitalXAsig;
    }

    public void setCapitalXAsig(int capitalXAsig) {
        this.capitalXAsig = capitalXAsig;
    }

    public int getCapitalAsig() {
        return capitalAsig;
    }

    public void setCapitalAsig(int capitalAsig) {
        this.capitalAsig = capitalAsig;
    }
    // </editor-fold>

    public Date getTodayPlusThree() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }
}

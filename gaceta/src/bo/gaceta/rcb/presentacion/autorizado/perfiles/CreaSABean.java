package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.*;
import bo.gaceta.rcb.bll.utils.EnvioMail;
import bo.gaceta.rcb.modelo.*;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.models.actividad;
import bo.gaceta.rcb.presentacion.models.empresa;
import bo.gaceta.rcb.presentacion.models.sociosSA;
import lombok.Data;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "creaSABean")
@ViewScoped
@Data
public class CreaSABean implements Serializable {
    private Boolean ctrPagInt = false;
    /*Campos Requeridos en wizzard volver a falsos*/
    private Boolean reqAct = true;
    private Boolean reqSoc = false;
    private Boolean reqOrgId = true;
    private Boolean reqBien = false;
    private Boolean roBien = true;
    private String colorSaldo = "";
    private String colorSaldoPag = "";
    /*Datos del Capital*/
    private double capitalXSus;
    private double capitalSus;
    private double capitalPagadoX;
    private double capitalPagado;
    private String tipoAporte;
    /*Variables para Registros de Socios*/
    private List<taSocios> listaSocios = new ArrayList<>();
    private taSocios socioReg;
    /*Datos de los Socios*/
    private String tipoIdentifica;
    private String numIdentifica;
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
    private double aporteCapitalSuscrito;
    private double aporteCapitalPagado;
    private double numAcciones;
    private double participacion;
    /*Validacion de Cuotas de Capital*/
    private double VerifnumAcciones;
    private String codigoMunip;
    private sociosSA socioNew;
    private empresa empresaNew;
    private List<sociosSA> sociosSociedad = new ArrayList<>();
    private List<tbInMunicipios> municipios;
    private List<tbInMunicipios> municipiosDoc;
    private List<TbInOrigenes> origenes;
    private sociosSA socioSel;
    private String txtBuscaClase = "";
    private List<actividad> actividadClase = new ArrayList<>();
    private actividad actividadSeleccionada;
    //private capitalSA capitalNew; // hhh observado
    /*Aceptacion*/
    private tbUSLogin usuarioSesion;
    private String vistaPreliminar;
    private String PublicacionCompleta;
    private tbPbPublicaciones publicacionRegistro;
    private String Distrito = "";
    private String origen = "";
    private String nroOrigen = "";
    private String dptoOrigen = "";
    private String distOrigen = "";
    private Boolean aceptacion;
    @Past(message = "Debe seleccionar una fecha anterior a la de hoy")
    private Date fecDocumento;

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

    private taEmpresa empresaData;
    private taEmpresaBll empresaBll;
    private taCapital capital;

    /*validacion Wizzar*/
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value1;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value2;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value3;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value4;
    @AssertTrue(message = "Debe aceptar la verificacion de la informacion declarada")
    private boolean value5;


    public CreaSABean() {
        setSocioNew(new sociosSA());
        setEmpresaNew(new empresa());
        //setCapitalNew(new capitalSA());
        setCapital(new taCapital());
        setActividadSeleccionada(new actividad());
        setItemSeleccionado(new TbInCiiu());
    }

    @PostConstruct
    public void ini() {
        setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));
        TbInOrigenesBll bllOrig = new TbInOrigenesBll();
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        taEmpresaBll empresaBll = new taEmpresaBll();
        try {
            setOrigenes(bllOrig.list());
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            setListaCiiuSeccion(bllCiiu.listSeccion());
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
        setEmpresaData(new taEmpresa());
        if (empresaBll.verifByIdEmpresa(getUsuarioSesion().getIdLoginEmpresa())) {
            try {
                setEmpresaData(empresaBll.getIdEmpresa(getUsuarioSesion().getIdLoginEmpresa()));
                tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
                try {
                    String idDepto = getEmpresaData().getIdDepartamentoDoc();
                    setMunicipiosDoc(munipBll.listByDpto(idDepto));
                } catch (Exception ex) {
                    Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getEmpresaData() != null) {
                setFecDocumento(getEmpresaData().getFechaDocumento());
            }

            if (getEmpresaData().getCiiuObjeto() != null) {
                getActividadSeleccionada().setClase(bllCiiu.getDescCLaseCIIU(getEmpresaData().getCiiuObjeto()));
            }
        }
        try {
            taCapitalBll capCarga = new taCapitalBll();
            if (capCarga.verifByIdEmpresa(getUsuarioSesion().getIdLoginEmpresa())) {
                setCapital(capCarga.getIdEmpresa(getUsuarioSesion().getIdLoginEmpresa()));
            } else {
                getCapital().setCodigoEmpresa(getUsuarioSesion().getIdLoginEmpresa());
                getCapital().setCntAccionesNum(0);
                getCapital().setCapitalPagadoNum(0);
                getCapital().setCapitalAutorizadoNum(0);
                getCapital().setCapitalSuscritoNum(0);
                capCarga.add(getCapital());
            }
        } catch (Exception exCap) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, exCap);
        }

        try {
            taSociosBll socCarga = new taSociosBll();
            setListaSocios(socCarga.list(getUsuarioSesion().getIdLoginEmpresa()));
            this.actuaSaldo(getListaSocios());
        } catch (Exception exSoc) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, exSoc);
        }

        setReqAct(true);
        setReqSoc(true);
        setReqOrgId(true);
        setCtrPagInt(false);
        setColorSaldo("#3CB371");
        setColorSaldoPag("#3CB371");
        setPublicacionRegistro(new tbPbPublicaciones());

        getEmpresaNew().setNombreSociedad(getUsuarioSesion().getSociedadConstituye());
        setPublicacionRegistro(new tbPbPublicaciones());

    }

    /*
    public void registraSocio() {
        try {
            if ((getAporteCapitalSuscrito() > 0)) {
                if ((getAporteCapitalSuscrito() < getCapitalNew().getCapitalSuscrito()) & (getAporteCapitalPagado() < getCapitalNew().getCapitalPagado())) {
                    if ((getAporteCapitalSuscrito() % getCapitalNew().getVrAcciones()) == 0) {
                        if (getAporteCapitalPagado() >= (getAporteCapitalSuscrito() * 0.25)) {
                            if (getAporteCapitalPagado() <= getAporteCapitalSuscrito()) {
                                Double vrParticipa = 0.0;
                                //Integer indice = getSociosSociedad().size();
                                Integer indice=listaSocios.size();
                                indice++;
                                vrParticipa = ((double) getAporteCapitalSuscrito() / (double) getCapitalNew().getCapitalSuscrito()) * 100;
                                setNumAcciones(getAporteCapitalSuscrito() / getCapitalNew().getVrAcciones());
                                if ((getVerifnumAcciones() - getNumAcciones()) >= 0) {
                                    if (getCtrPagInt()) {
                                        setAporteCapitalPagado(getAporteCapitalSuscrito());
                                    }
                                    setVerifnumAcciones(getVerifnumAcciones() - getNumAcciones());
                                    setCapitalSus(getCapitalSus() + getAporteCapitalSuscrito());
                                    setCapitalXSus(getCapitalNew().getCapitalSuscrito() - getCapitalSus());
                                    setCapitalPagado(getCapitalPagado() + getAporteCapitalPagado());
                                    setCapitalPagadoX(getCapitalNew().getCapitalPagado() - getCapitalPagado());
                                    setParticipacion(vrParticipa);


//                                    sociosSA tmpSocio = new sociosSA();
//                                    tmpSocio.setIdLinea(indice);
//                                    tmpSocio.setNombre(getNombreSocio().toUpperCase());
//                                    tmpSocio.setPaterno(getPaternoSocio().toUpperCase());
//                                    tmpSocio.setMaterno(getMaternoSocio().toUpperCase());
//                                    tmpSocio.setTipoidentificacion(getTipoIdentifica());
//                                    tmpSocio.setNumIdentifica(getNumIdentifica());
//                                    tmpSocio.setExtIdentifica(getExtIdentifica());
//                                    tmpSocio.setNacionalidad(getNacionalidadSocio());
//                                    tmpSocio.setEstadoCivil(getEstadoCivil());
//                                    tmpSocio.setProfesion(getProfesionSocio());
//                                    tmpSocio.setDomicilio(getDomicilioSocio());
//                                    if (getCtrPagInt()) {
//                                        tmpSocio.setAporteCapitalPagado(getAporteCapitalSuscrito());
//                                    } else {
//                                        tmpSocio.setAporteCapitalPagado(getAporteCapitalPagado());
//                                    }
//                                    tmpSocio.setAporteCapitalSuscrito(getAporteCapitalSuscrito());
//                                    tmpSocio.setNumAcciones(getNumAcciones());
//                                    tmpSocio.setZona(getZonaDomicilioSocio());
//                                    tmpSocio.setParticipacion(vrParticipa);
//                                    tmpSocio.setCtrTipoAporte(getTipoAporte());
//                                    tmpSocio.setCargo("");
//                                    if (getTipoAporte().equals("B")) {
//                                        tmpSocio.setDescripcionBien(getDescripcionBien());
//                                    }
//                                    getSociosSociedad().add(tmpSocio);


                                    taSocios socioAdd = new taSocios();
                                    socioAdd.setCodigoEmpresa(empresaData.getCodigoEmpresa());
                                    socioAdd.setNroSocio(indice);
                                    socioAdd.setNombreSocio(getNombreSocio().toUpperCase());
                                    socioAdd.setPaternoSocio(getPaternoSocio().toUpperCase());
                                    socioAdd.setMaternoSocio(getMaternoSocio().toUpperCase());
                                    socioAdd.setTipoidentificacion(getTipoIdentifica());
                                    socioAdd.setIdentificacion(getNumIdentifica());
                                    socioAdd.setIdExpedido(getExtIdentifica());
                                    socioAdd.setNacionalidad(getNacionalidadSocio());
                                    socioAdd.setEstadoCivil(getEstadoCivil());
                                    socioAdd.setProfesion(getProfesionSocio());
                                    socioAdd.setDomicilio(getDomicilioSocio());
                                    if (getCtrPagInt()) {
                                        socioAdd.setAporteCapitalSus(getAporteCapitalSuscrito());
                                    } else {
                                        socioAdd.setAporteCapitalPag(getAporteCapitalPagado());
                                    }
                                    socioAdd.setAcciones(getNumAcciones());
                                    socioAdd.setZona(getZonaDomicilioSocio());
                                    socioAdd.setParticipaPercent(vrParticipa);
                                    socioAdd.setCtrTipoAporte(getTipoAporte());
                                    socioAdd.setCargo("");
                                    if (getTipoAporte().equals("B")) {
                                        socioAdd.setAporteBien(getDescripcionBien());
                                    }
                                    // ADICIONANDO NUEVO SOCIO
                                    taSociosBll sociosReg = new taSociosBll();
                                    sociosReg.add(socioAdd);
                                    listaSocios = sociosReg.list(empresaData.getCodigoEmpresa());
                                    // LIMPIANDO CAMPOS DE ENTRADA
                                    setNumIdentifica("");
                                    setExtIdentifica("");
                                    setTipoIdentifica("");
                                    setNombreSocio("");
                                    setPaternoSocio("");
                                    setMaternoSocio("");
                                    setEstadoCivil("");
                                    setProfesionSocio("");
                                    setDomicilioSocio("");
                                    setNacionalidadSocio("");
                                    setAporteCapitalPagado(0);
                                    setAporteCapitalSuscrito(0);
                                    setNumAcciones(0);
                                    setParticipacion(0);
                                    setZonaDomicilioSocio("");
                                    setDescripcionBien("");
                                    setTipoAporte("");
                                    setReqBien(false);
                                    setRoBien(true);
                                    if (getCapitalSus() == getCapitalNew().getCapitalSuscrito()) {
                                        setReqSoc(false);
                                        setReqOrgId(false);
                                        setColorSaldo("#B22222");
                                    }
                                    if (getCapitalPagado() == getCapitalNew().getCapitalPagado()) {
                                        setReqSoc(false);
                                        setReqOrgId(false);
                                        setColorSaldo("#B22222");
                                    }
                                } else {
                                    UtilsPresentacion.addMessage("Existe un error al consignar la distribución del Capital, verifique los datos consignados.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                                }
                            } else {
                                UtilsPresentacion.addMessage("El aporte del Capital Pagado no puede ser mayor al aporte suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                            }
                        } else {
                            UtilsPresentacion.addMessage("El aporte del Capital Pagado no puede ser inferior al 25% del aporte suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        UtilsPresentacion.addMessage("El valor del aporte del socio da como resultado acciones fraccionadas", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    UtilsPresentacion.addMessage("El aporte del socio excede el monto establecido como Capital Social o Suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                UtilsPresentacion.addMessage("Debe consignar el aporte del socio", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception exAdd) {
            UtilsPresentacion.addMessage(exAdd.getMessage(), "Error en Aplicación", FacesMessage.SEVERITY_ERROR);
        }
    }
    */

    public void registraSocio() {
        try {
            if ((getAporteCapitalSuscrito() > 0)) {
                if ((getAporteCapitalSuscrito() < getCapital().getCapitalSuscritoNum()) & (getAporteCapitalPagado() < getCapital().getCapitalPagadoNum())) {
                    if ((getAporteCapitalSuscrito() % getCapital().getVrNominalNum()) == 0) {
                        if (getAporteCapitalPagado() >= (getAporteCapitalSuscrito() * 0.25)) {
                            if (getAporteCapitalPagado() <= getAporteCapitalSuscrito()) {
                                Double vrParticipa = 0.0;
                                //Integer indice = getSociosSociedad().size();
                                Integer indice=listaSocios.size();
                                indice++;
                                vrParticipa = ((double) getAporteCapitalSuscrito() / (double) getCapital().getCapitalSuscritoNum()) * 100;
                                setNumAcciones(getAporteCapitalSuscrito() / getCapital().getVrNominalNum());
                                if ((getVerifnumAcciones() - getNumAcciones()) >= 0) {
                                    if (getCtrPagInt()) {
                                        setAporteCapitalPagado(getAporteCapitalSuscrito());
                                    }
                                    setVerifnumAcciones(getVerifnumAcciones() - getNumAcciones());
                                    setCapitalSus(getCapitalSus() + getAporteCapitalSuscrito());
                                    setCapitalXSus(getCapital().getCapitalSuscritoNum() - getCapitalSus());
                                    setCapitalPagado(getCapitalPagado() + getAporteCapitalPagado());
                                    setCapitalPagadoX(getCapital().getCapitalPagadoNum() - getCapitalPagado());
                                    setParticipacion(vrParticipa);

                                    taSocios socioAdd = new taSocios();
                                    socioAdd.setCodigoEmpresa(empresaData.getCodigoEmpresa());
                                    socioAdd.setNroSocio(indice);
                                    socioAdd.setNombreSocio(getNombreSocio().toUpperCase());
                                    socioAdd.setPaternoSocio(getPaternoSocio().toUpperCase());
                                    socioAdd.setMaternoSocio(getMaternoSocio().toUpperCase());
                                    socioAdd.setTipoidentificacion(getTipoIdentifica());
                                    socioAdd.setIdentificacion(getNumIdentifica());
                                    socioAdd.setIdExpedido(getExtIdentifica());
                                    socioAdd.setNacionalidad(getNacionalidadSocio());
                                    socioAdd.setEstadoCivil(getEstadoCivil());
                                    socioAdd.setProfesion(getProfesionSocio());
                                    socioAdd.setDomicilio(getDomicilioSocio());
                                    //if (getCtrPagInt()) {
                                        socioAdd.setAporteCapitalSus(getAporteCapitalSuscrito());
                                    //} else {
                                        socioAdd.setAporteCapitalPag(getAporteCapitalPagado());
                                    //}
                                    socioAdd.setAcciones(getNumAcciones());
                                    socioAdd.setZona(getZonaDomicilioSocio());
                                    socioAdd.setParticipaPercent(vrParticipa);
                                    socioAdd.setCtrTipoAporte(getTipoAporte());
                                    socioAdd.setCargo("");
                                    if (getTipoAporte().equals("B")) {
                                        socioAdd.setAporteBien(getDescripcionBien());
                                    }
                                    // ADICIONANDO NUEVO SOCIO
                                    taSociosBll sociosReg = new taSociosBll();
                                    sociosReg.add(socioAdd);
                                    listaSocios = sociosReg.list(empresaData.getCodigoEmpresa());
                                    // LIMPIANDO CAMPOS DE ENTRADA
                                    setNumIdentifica("");
                                    setExtIdentifica("");
                                    setTipoIdentifica("");
                                    setNombreSocio("");
                                    setPaternoSocio("");
                                    setMaternoSocio("");
                                    setEstadoCivil("");
                                    setProfesionSocio("");
                                    setDomicilioSocio("");
                                    setNacionalidadSocio("");
                                    setAporteCapitalPagado(0);
                                    setAporteCapitalSuscrito(0);
                                    setNumAcciones(0);
                                    setParticipacion(0);
                                    setZonaDomicilioSocio("");
                                    setDescripcionBien("");
                                    setTipoAporte("");
                                    setReqBien(false);
                                    setRoBien(true);
                                    if (getCapitalSus() == getCapital().getCapitalSuscritoNum()) {
                                        setReqSoc(false);
                                        setReqOrgId(false);
                                        setColorSaldo("#B22222");
                                    }
                                    if (getCapitalPagado() == getCapital().getCapitalPagadoNum()) {
                                        setReqSoc(false);
                                        setReqOrgId(false);
                                        setColorSaldo("#B22222");
                                    }
                                } else {
                                    UtilsPresentacion.addMessage("Existe un error al consignar la distribución del Capital, verifique los datos consignados.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                                }
                            } else {
                                UtilsPresentacion.addMessage("El aporte del Capital Pagado no puede ser mayor al aporte suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                            }
                        } else {
                            UtilsPresentacion.addMessage("El aporte del Capital Pagado no puede ser inferior al 25% del aporte suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        UtilsPresentacion.addMessage("El valor del aporte del socio da como resultado acciones fraccionadas", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    UtilsPresentacion.addMessage("El aporte del socio excede el monto establecido como Capital Social o Suscrito", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                UtilsPresentacion.addMessage("Debe consignar el aporte del socio", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception exAdd) {
            UtilsPresentacion.addMessage(exAdd.getMessage(), "Error en Aplicación", FacesMessage.SEVERITY_ERROR);
        }
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        taEmpresaBll empresaBll = new taEmpresaBll();

        if (txtSig.equals("datosSociedad")) {
            if (getEmpresaData().getCodigoEmpresa() == null) {
                setEmpresaData(new taEmpresa());
                getEmpresaData().setDenominacion(usuarioSesion.getSociedadConstituye());
                getEmpresaData().setCodigoEmpresa(usuarioSesion.getIdLoginEmpresa());
                getEmpresaData().setIdUsuario(usuarioSesion.getIdLoginEmpresa());
                getEmpresaData().setCtrEstado("datosTestimonio");
                empresaBll.add(getEmpresaData());
                okVerif = true;
            } else {
                getEmpresaData().setCtrEstado("datosTestimonio");
                empresaBll.update(empresaData);
                tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
                try {
                    String idDepto = empresaData.getDepartamento();
                    municipios = munipBll.listByDpto(idDepto);
                } catch (Exception ex) {
                    Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    taCapitalBll capIni = new taCapitalBll();
                    setCapital(capIni.getIdEmpresa(usuarioSesion.getIdLoginEmpresa()));
                } catch (Exception ex) {
                    Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
                }
                okVerif = true;
            }
        }

        if (txtSig.equals("datosObjeto")) {
            //getEmpresaData().setCtrEstado("datosTestimonio");
            empresaBll.update(empresaData);
            okVerif = true;
        }

        if (txtSig.equals("datosCapital")) {
            if (empresaData.getCiiuObjeto() == null) {
                okVerif = false;
                UtilsPresentacion.addMessage("Debe seleccionar una actividad principal", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            } else {
                getEmpresaData().setCtrEstado("datosSociedad");
                empresaBll.update(empresaData);
                okVerif = true;
            }
        }

        if (txtSig.equals("datosSocios")) {
            if ((getCapital().getCapitalSuscritoNum() <= getCapital().getCapitalAutorizadoNum()) & (getCapital().getCapitalPagadoNum() <= getCapital().getCapitalAutorizadoNum())) {
                if (getCapital().getCapitalPagadoNum() <= getCapital().getCapitalSuscritoNum()) {
                    if (getCapital().getCapitalSuscritoNum() < (getCapital().getCapitalAutorizadoNum() / 2)) {
                        okVerif = false;
                        UtilsPresentacion.addMessage("El monto de Capital Suscrito o Social no puede ser inferior al 50% del Capital Autorizado", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    } else if (getCapital().getCapitalPagadoNum() < (getCapital().getCapitalSuscritoNum() * 0.25)) {
                        okVerif = false;
                        UtilsPresentacion.addMessage("El monto de Capital Pagado no puede ser inferior al 25% del Capital Suscrito o Social", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    } else if ((getCapital().getCapitalSuscritoNum() / getCapital().getVrNominalNum()) >= 3) {
                        int valorMod = 0;
                        getCapital().setCntAccionesNum(getCapital().getCapitalSuscritoNum() / getCapital().getVrNominalNum());
                        setVerifnumAcciones(getCapital().getCntAccionesNum());
                        valorMod = getCapital().getVrNominalNum() % 100;
                        setCapitalSus(0);
                        setCapitalPagado(0);
                        if (valorMod == 0 & getCapital().getCntAccionesNum() >= 3) {
                            okVerif = true;
                            Double ActuaValNuCuotas = 0.0;
                            Integer idSocio = 1;
                            for (sociosSA listaSocios : getSociosSociedad()) { // hhh
                                ActuaValNuCuotas = ActuaValNuCuotas + listaSocios.getNumAcciones();
                                setCapitalSus(getCapitalSus() + listaSocios.getAporteCapitalSuscrito());
                                setCapitalPagado(getCapitalPagado() + listaSocios.getAporteCapitalPagado());
                                idSocio++;
                            }
                            setVerifnumAcciones(getCapital().getCntAccionesNum() - ActuaValNuCuotas);
                            setCapitalXSus(getCapital().getCapitalSuscritoNum() - getCapitalSus());
                            setCapitalPagadoX(getCapital().getCapitalPagadoNum() - getCapitalPagado());
                            if (getCapital().getCapitalSuscritoNum() == getCapital().getCapitalPagadoNum()) {
                                setCtrPagInt(true);
                            }
                                capital.setCodigoEmpresa(empresaData.getCodigoEmpresa());
                                taCapitalBll capBll = new taCapitalBll();
                                capBll.update(capital);
                        } else {
                            okVerif = false;
                            UtilsPresentacion.addMessage("El capital debe ser aportado en bolivianos, en sumas iguales a cien o múltiplos de cien.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                        }
                    } else {
                        okVerif = false;
                        UtilsPresentacion.addMessage("El capital de la sociedad debe estar dividido en acciones de igual valor, que serán de cien 00/100 bolivianos (Bs. 100) o múltiplos de cien y el monto del valor de la acción deberá permitir la división del capital en 3 o más acciones (S.A.) conforme al aporte y participación del accionistas.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    okVerif = false;
                    UtilsPresentacion.addMessage("El Capital Pagado no puede ser superior al Capital Suscrito.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
                getPublicacionRegistro().setCtrDisplay("2");
                getPublicacionRegistro().setDesEstadoPublica("Esperando Pago");
            } else {
                okVerif = false;
                UtilsPresentacion.addMessage("El Capital Suscrito y/o Pagado no puede ser superior al Capital Autorizado.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        }

        if (txtSig.equals("verifica")) {
            setReqSoc(false);
            // if (getSociosSociedad().size() >= 3) {
            if (getListaSocios().size() >= 3) {
                if (getCapitalPagadoX() == 0.0) {
                    if (okVerif) {
                        okVerif = true;
                        setReqSoc(false);
                        setReqOrgId(false);
                        this.generaPreview();
                    } else {
                        okVerif = false;
                        setReqSoc(true);
                        setReqOrgId(true);
                        UtilsPresentacion.addMessage("Existe un error al consignar la distribución del capital, verifique los datos consignados.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                    }
                } else {
                    okVerif = false;
                    setReqSoc(true);
                    setReqOrgId(true);
                    UtilsPresentacion.addMessage("Existe un error al consignar la distribución del capital, verifique los datos consignados.", "Error en Validación", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                okVerif = false;
                setReqSoc(true);
                setReqOrgId(true);
                UtilsPresentacion.addMessage("La sociedad no podrá estar integrada por menos de tres  socios", "Error en Validación", FacesMessage.SEVERITY_ERROR);
            }
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    public void cargaMunip(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            setMunicipios(munipBll.listByDpto(idDepto));
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valideBien(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            if (tpA.equals("B")) {
                setReqBien(true);
                setRoBien(false);
                setDescripcionBien("");
            } else {
                setReqBien(false);
                setRoBien(true);
                setDescripcionBien("No Aplica");
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valideTipoId(ValueChangeEvent evento) {
        try {
            String tpA = evento.getNewValue().toString();
            setReqOrgId(tpA.equals("Cedula de Identidad"));
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generaPreview() {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        /*
        String capital = Double.toString(getCapitalNew().getCapitalAutorizado());
        String cntTxtCuotas = Double.toString(getCapitalNew().getCntAcciones());
        String vrTxtCuotas = Double.toString(getCapitalNew().getVrAcciones());
        String txtAñosVig = Integer.toString(getEmpresaNew().getAnosVigencia());
        */

        String capitalTxt = Double.toString(capital.getCapitalSocialNum());
        String cntTxtCuotas = Double.toString(capital.getCntAccionesNum());
        String vrTxtCuotas = Double.toString(capital.getVrNominalNum());
        String txtAñosVig = Integer.toString(empresaData.getDuracionNum());

        setVistaPreliminar("");
        String vistaSocios = "";

        String vistaCiiu = "";
        vistaCiiu = "La Actividad Principal de la Sociedad será:" + "\r\n"
                + "Codigo Clase Ciiu:" + getActividadSeleccionada().getIdClase() + ", Descripción:" + getActividadSeleccionada().getClase() + "\r\n"
                + "La Sociedad tiene las siguientes actividades Secundarias:" + "\r\n"
                + getEmpresaNew().getTxtActividadSecundaria() + "\r\n" + "\r\n";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String txtTestimonio = "Conforme a testomonio N°:" + getPublicacionRegistro().getNroDocumento() + " de fecha " + df.format(getFecDocumento()) + ", de la " + getPublicacionRegistro().getOrigenDocumento() + " N°:" + getNroOrigen() + " del Distrito Judicial " + getDistOrigen() + ". Se constituye una Sociedad Anonima con los siguientes datos:" + "\r\n";

        setVistaPreliminar(txtTestimonio);


        setVistaPreliminar(getVistaPreliminar() + "La sociedad girará bajo la razón social o denominación de '" + getEmpresaNew().getNombreSociedad() + "'" + "\r\n" + "\r\n"
                //+ "Tendrá su domicilio en " + getEmpresaNew().getDomicilioSociedad() + ", Estado Plurinacional de Bolivia" + "\r\n" + "\r\n"
                + "Tendrá su domicilio en " + munipBll.getById(empresaData.getDomicilio()).getDepartamento() + ", Estado Plurinacional de Bolivia" + "\r\n" + "\r\n"
                + "La sociedad tendrá una duración de " + txtAñosVig + " años computables " + getEmpresaNew().getTipoVigencia() + "\r\n" + "\r\n"
                + vistaCiiu
                + "El capital de la Sociedad es de " + capital + "/100 dividido en " + cntTxtCuotas + " cuotas de capital de " + vrTxtCuotas + "/100 Bolivianos cada una." + "\r\n" + "\r\n"
                + "La sociedad esta compuesta por los siguentes socios:" + "\r\n");
        /*
        for (sociosSA listaSocios : getSociosSociedad()) {
            vistaSocios = vistaSocios + "Socio o Socia: " + listaSocios.getNombre() + " " + listaSocios.getPaterno() + " " + listaSocios.getMaterno() + ", con " + listaSocios.getTipoidentificacion() + " " + listaSocios.getNumIdentifica() + " expedida en " + listaSocios.getExtIdentifica()
                    + ", Estado Civil:" + listaSocios.getEstadoCivil() + ", de profesión:" + listaSocios.getProfesion() + ", Nacionalidad:" + listaSocios.getNacionalidad() + ", con domicilio:" + listaSocios.getDomicilio() + " de la zona " + listaSocios.getZona() + "\r\n"
                    + "Aporto un total de " + Double.toString(listaSocios.getNumAcciones()) + " Acciones, con un aporte pagado de Bs." + Double.toString(listaSocios.getAporteCapitalPagado()) + " representando el " + Double.toString(listaSocios.getParticipacion()) + "% de la participación en la Sociedad" + "\r\n" + "\r\n";
        }
        */
        for (taSocios listaSocios : getListaSocios()) {
            vistaSocios = vistaSocios + "Socio(a): " + listaSocios.getNombreSocio() + " " + listaSocios.getPaternoSocio() + " " + listaSocios.getMaternoSocio() + ", con " + listaSocios.getTipoidentificacion() + " " + listaSocios.getIdentificacion() + " expedida en " + listaSocios.getIdExpedido()
                    + ", Estado Civil:" + listaSocios.getEstadoCivil() + ", de profesión:" + listaSocios.getProfesion() + ", Nacionalidad:" + listaSocios.getNacionalidad() + ", con domicilio:" + listaSocios.getDomicilio() + " de la zona " + listaSocios.getZona() + "\r\n"
                    + "Aporto un total de " + Double.toString(listaSocios.getAcciones()) + " Acciones, con un aporte pagado de Bs." + Double.toString(listaSocios.getAporteCapitalPag()) + " representando el " + Double.toString(listaSocios.getParticipaPercent()) + "% de la participación en la Sociedad" + "\r\n" + "\r\n";
        }

        setVistaPreliminar(getVistaPreliminar() + vistaSocios);
        PublicacionCompleta=vistaPreliminar;
    }

    /*
    public void deleteSocio(sociosSA socioDel) {
        getSociosSociedad().remove(socioDel);
        Double ActuaValNuCuotas = 0.0;
        Integer idSocio = 1;
        for (sociosSA listaSocios : getSociosSociedad()) {
            listaSocios.setIdLinea(idSocio);
            ActuaValNuCuotas = ActuaValNuCuotas + listaSocios.getNumAcciones();
            setCapitalSus(getCapitalSus() + listaSocios.getAporteCapitalSuscrito());
            idSocio++;
        }
        setVerifnumAcciones(getCapitalNew().getCntAcciones() - ActuaValNuCuotas);
        setCapitalXSus(getCapitalNew().getCapitalSuscrito() - getCapitalSus());
        setCapitalPagadoX(getCapitalNew().getCapitalPagado() - getCapitalPagado());
        setReqSoc(true);
        setColorSaldo("#3CB371");
        setColorSaldoPag("#3CB371");
    }
    */
    public void deleteSocio(taSocios socioDel) throws Exception {
        // getSociosSociedad().remove(socioDel);
        taSociosBll sociosReg = new taSociosBll();
        sociosReg.delete(socioDel);
        listaSocios = sociosReg.list(usuarioSesion.getIdLoginEmpresa());
        /*
        Double ActuaValNuCuotas = 0.0;
        Integer idSocio = 1;
        for (taSocios socio : getListaSocios()) {
            socio.setNroSocio(idSocio);
            ActuaValNuCuotas = ActuaValNuCuotas + socio.getAcciones();
            setCapitalSus(getCapitalSus() + socio.getAporteCapitalSus());
            idSocio++;
        }
        setVerifnumAcciones(getCapitalNew().getCntAcciones() - ActuaValNuCuotas);
        setCapitalXSus(getCapitalNew().getCapitalSuscrito() - getCapitalSus());
        setCapitalPagadoX(getCapitalNew().getCapitalPagado() - getCapitalPagado());
        setReqSoc(true);
        setColorSaldo("#3CB371");
        setColorSaldoPag("#3CB371");
        */
        this.actuaSaldo(listaSocios);
    }

    private void actuaSaldo(List<taSocios> sociosCalc) {
        setCapitalSus(0);
        setCapitalPagado(0);
        Integer idSocio = 1;
        numAcciones = 0;
        try {
            for (taSocios listaSocios : sociosCalc) {
                listaSocios.setNroSocio(idSocio);
                numAcciones = numAcciones + listaSocios.getAcciones();
                capitalPagado = capitalPagado + listaSocios.getAporteCapitalPag();
                capitalSus = capitalSus + listaSocios.getAporteCapitalSus();
                idSocio++;
            }
            capitalPagadoX=capital.getCapitalPagadoNum() - capitalPagado;
            capitalXSus=capital.getCapitalSuscritoNum()-capitalSus;
            setVerifnumAcciones(capital.getCntAccionesNum() - numAcciones);
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
        setReqSoc(true);
        setColorSaldo("#3CB371");
        setColorSaldoPag("#3CB371");
    }

     /*
    private void actuaSaldo(List<taSocios> sociosCalc) {
        Double ActuaValNuCuotas = 0.0;
        Integer idSocio = 1;
        for (taSocios socio : sociosCalc) {
            socio.setNroSocio(idSocio);
            ActuaValNuCuotas = ActuaValNuCuotas + socio.getAcciones();
            setCapitalSus(getCapitalSus() + socio.getAporteCapitalSus());
            idSocio++;
        }
        setVerifnumAcciones(getCapitalNew().getCntAcciones() - ActuaValNuCuotas);
        setCapitalXSus(getCapitalNew().getCapitalSuscrito() - getCapitalSus());
        setCapitalPagadoX(getCapitalNew().getCapitalPagado() - getCapitalPagado());
        setReqSoc(true);
        setColorSaldo("#3CB371");
        setColorSaldoPag("#3CB371");
    }
    */

    public void cargaMunipDoc(ValueChangeEvent evento) {
        tbInMunicipiosBll munipBll = new tbInMunicipiosBll();
        try {
            String idDepto = evento.getNewValue().toString();
            setMunicipiosDoc(munipBll.listByDpto(idDepto));
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaDivision(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idSeccionFilt = evento.getNewValue().toString();
            if (!idSeccionFilt.isEmpty()) {
                setListaCiiuDivision(bllCiiu.listDivision(idSeccionFilt));
                setListaCiiuClase(bllCiiu.listClaseBySeccion(idSeccionFilt));
                setListaCiiuGrupo(null);
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaGrupo(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idDivisionFilt = evento.getNewValue().toString();
            if (!idDivisionFilt.isEmpty()) {
                setListaCiiuGrupo(bllCiiu.listGrupo(idDivisionFilt));
                setListaCiiuClase(bllCiiu.listClaseByDivision(idDivisionFilt));
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaClase(ValueChangeEvent evento) {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            String idGrupoFilt = evento.getNewValue().toString();
            if (idGrupoFilt != null | !idGrupoFilt.isEmpty()) {
                setListaCiiuClase(bllCiiu.listClaseByGroup(idGrupoFilt));
            }
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscaClase() {
        TbInCiiuBll bllCiiu = new TbInCiiuBll();
        try {
            setListaCiiuGrupo(null);
            setListaCiiuDivision(null);
            setListaCiiuClase(bllCiiu.listFiltroClase(getTxtBuscaClase()));
        } catch (Exception ex) {
            Logger.getLogger(CreaSABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registraActividad(TbInCiiu itemSel) {
        // getActividadSeleccionada().setIdClase(itemSel.getIdClase());
        // getActividadSeleccionada().setClase(itemSel.getClase());
        actividadSeleccionada.setIdClase(itemSel.getIdClase());
        actividadSeleccionada.setClase(itemSel.getClase());
        empresaData.setCiiuObjeto(itemSel.getIdClase());
    }

    public void deleteSeleccion(actividad itemSel) {
        boolean remove = getActividadClase().remove(itemSel);
    }


    public void onRowSelect(SelectEvent event) {
        ((TbInCiiu) event.getObject()).getIdClase();
    }

    /*
    private void actuaSaldo(List<taSocios> sociosCalc) {
//        setCapitalAsig(0);
        Integer idSocio = 1;
//        ActuaValNuCuotas=0;
//        try {
//            for (taSocios listaSocios : sociosCalc) {
//                listaSocios.setNroSocio(idSocio);
        //               ActuaValNuCuotas = ActuaValNuCuotas + listaSocios.getCuotasCapital();
        //               setCapitalAsig((int) (getCapitalAsig() + listaSocios.getAporteCapitalMon()));
        //               idSocio++;
        //           }
        //           setCapitalXAsig(capital.getCapitalSocialNum() - getCapitalAsig());
        //          VerifnumCuotas = capital.getCntCuotasCapital() - ActuaValNuCuotas;
        //      }catch (Exception ex){
        //           Logger.getLogger(CreaSRLBean.class.getName()).log(Level.SEVERE, null, ex);
        //       }
        reqSoc = true;
        reqOrgId = true;
        colorSaldo = "#3CB371";
    }
    */

    public void registraPublicacion() {
        if (getAceptacion()) {
            try {
                tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                getPublicacionRegistro().setIdLoginEmpresa(getUsuarioSesion().getIdLoginEmpresa());
                String TituloPublica = "Escritura Pública de Constitución N°" + getPublicacionRegistro().getNroDocumento() + " de la empresa: " + getUsuarioSesion().getSociedadConstituye();
                getPublicacionRegistro().setTituloPublica(TituloPublica);
                String resumenPublica = "Testimonio de Constitución N°" + getPublicacionRegistro().getNroDocumento() + " de " + getPublicacionRegistro().getOrigenDocumento() + " del distrito de" + getDistrito() + " de la empresa " + getUsuarioSesion().getSociedadConstituye();
                getPublicacionRegistro().setTextoPublica(resumenPublica);
                getPublicacionRegistro().setCtrTipoPublica(1);
                getPublicacionRegistro().setCtrDisplay("3");
                getPublicacionRegistro().setDesEstadoPublica("Esperando Autorizacion del Registro de Comercio");
                getPublicacionRegistro().setTextoCompleto(getPublicacionCompleta());
                getPublicacionRegistro().setCtrDisplay("0");
                String codigoPublica = usBll.add(getPublicacionRegistro());
                setPublicacionRegistro(null);
                setPublicacionCompleta(null);
                setAceptacion(false);
                String txtMensajeResultado
                        = "<div class=txtLabelNotifica>Su publicaci&oacute;n ha sido almacenada correctamente para ser revisada por el Registro de Comercio, el c&oacute;digo asignado es:</div>"
                        + "<div align=center class=txtCodigoPublica>" + codigoPublica + "</div>"
                        + "<p/><br>"
                        + "<div class=txtLabelNotifica>Si su publicaci&oacute;n requiere alguna modificaci&oacute;n, se le enviar&aacute; un mensaje al correo electr&oacute;nico registrado, caso  contrario</div>"
                        + "<div class=txtLabelNotifica>se le comunicar&aacute; la fecha de publicaci&oacute;n en la Gaceta de Comercio, junto el c&oacute;digo de identificaci&oacute;n de la publicaci&oacute;n</div>"
                        + "<div class=txtLabelNotifica></div>"
                        + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";

                String txtNotificacion = "<br><i>Notificaci&oacute;n de Publicaci&oacute;n</i></br>";
                txtNotificacion += "<b></b>";
                txtNotificacion += "<i>El C&oacute;digo de Publicaci&oacute;n es:" + codigoPublica + "</i>";
                txtNotificacion += "<i>El trámite se encuentra en revisi&oacute;n por el Registro de Comercio<i/>";
                EnvioMail notif = new EnvioMail();
                notif.envioMailEstado(getUsuarioSesion().getCorreoElectronico(), txtNotificacion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro de la publicacion", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Debe aceptar los términos para realizar la publicacion", "Debe completar los campos requeridos", FacesMessage.SEVERITY_INFO);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public Boolean getCtrPagInt() {
        return ctrPagInt;
    }

    public void setCtrPagInt(Boolean ctrPagInt) {
        this.ctrPagInt = ctrPagInt;
    }

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

    public String getColorSaldoPag() {
        return colorSaldoPag;
    }

    public void setColorSaldoPag(String colorSaldoPag) {
        this.colorSaldoPag = colorSaldoPag;
    }

    public double getCapitalXSus() {
        return capitalXSus;
    }

    public void setCapitalXSus(double capitalXSus) {
        this.capitalXSus = capitalXSus;
    }

    public double getCapitalSus() {
        return capitalSus;
    }

    public void setCapitalSus(double capitalSus) {
        this.capitalSus = capitalSus;
    }

    public double getCapitalPagadoX() {
        return capitalPagadoX;
    }

    public void setCapitalPagadoX(double capitalPagadoX) {
        this.capitalPagadoX = capitalPagadoX;
    }

    public double getCapitalPagado() {
        return capitalPagado;
    }

    public void setCapitalPagado(double capitalPagado) {
        this.capitalPagado = capitalPagado;
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

    public double getAporteCapitalSuscrito() {
        return aporteCapitalSuscrito;
    }

    public void setAporteCapitalSuscrito(double aporteCapitalSuscrito) {
        this.aporteCapitalSuscrito = aporteCapitalSuscrito;
    }

    public double getAporteCapitalPagado() {
        return aporteCapitalPagado;
    }

    public void setAporteCapitalPagado(double aporteCapitalPagado) {
        this.aporteCapitalPagado = aporteCapitalPagado;
    }

    public double getNumAcciones() {
        return numAcciones;
    }

    public void setNumAcciones(double numAcciones) {
        this.numAcciones = numAcciones;
    }

    public double getParticipacion() {
        return participacion;
    }

    public void setParticipacion(double participacion) {
        this.participacion = participacion;
    }

    public double getVerifnumAcciones() {
        return VerifnumAcciones;
    }

    public void setVerifnumAcciones(double verifnumAcciones) {
        VerifnumAcciones = verifnumAcciones;
    }

    public String getCodigoMunip() {
        return codigoMunip;
    }

    public void setCodigoMunip(String codigoMunip) {
        this.codigoMunip = codigoMunip;
    }

    public sociosSA getSocioNew() {
        return socioNew;
    }

    public void setSocioNew(sociosSA socioNew) {
        this.socioNew = socioNew;
    }

    public empresa getEmpresaNew() {
        return empresaNew;
    }

    public void setEmpresaNew(empresa empresaNew) {
        this.empresaNew = empresaNew;
    }

    public List<sociosSA> getSociosSociedad() {
        return sociosSociedad;
    }

    public void setSociosSociedad(List<sociosSA> sociosSociedad) {
        this.sociosSociedad = sociosSociedad;
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

    public List<TbInOrigenes> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(List<TbInOrigenes> origenes) {
        this.origenes = origenes;
    }

    public sociosSA getSocioSel() {
        return socioSel;
    }

    public void setSocioSel(sociosSA socioSel) {
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

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getVistaPreliminar() {
        return vistaPreliminar;
    }

    public void setVistaPreliminar(String vistaPreliminar) {
        this.vistaPreliminar = vistaPreliminar;
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

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public Date getFecDocumento() {
        return fecDocumento;
    }

    public void setFecDocumento(Date fecDocumento) {
        this.fecDocumento = fecDocumento;
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

    public taEmpresaBll getEmpresaBll() {
        return empresaBll;
    }

    public void setEmpresaBll(taEmpresaBll empresaBll) {
        this.empresaBll = empresaBll;
    }

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
    // </editor-fold>

}

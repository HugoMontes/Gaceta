package bo.gaceta.rcb.presentacion.autorizado.perfiles;

import bo.gaceta.rcb.bll.tbInMunicipiosBll;
import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbInMunicipios;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.models.ordenDia;
import lombok.Data;
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


@Data
@Named(value = "pubConvocatoriaAsambleaBean")
@ViewScoped
public class PubConvocatoriaAsambleaBean implements Serializable {
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
    private List<ordenDia> contenidoOrden=new ArrayList<>();
    private  ordenDia itemOrden;

    public PubConvocatoriaAsambleaBean() {
    }

    @PostConstruct
    public void init() {
        aceptacion = false;
        vistaPrev=false;
        publicacionRegistro = new tbPbPublicaciones();
        setItemOrden(new  ordenDia());
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        txtCaracter="Ordinaria";
        txtRequisitos="";
        txtSuspencion="";

    }

    public void registraPublicacion() {
        long diasConv;
        diasConv=fecJunta.getTime()- getFecPublica1().getTime();
        diasConv=diasConv / (1000 * 60 * 60 * 24);
        if (diasConv>=8) {
            if (aceptacion) {
                try {
                    this.generaPublica();
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    DateFormat tf = new SimpleDateFormat("HH:mm");
                    tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                    publicacionRegistro.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
                    String TituloPublica = "Convocatoria a Asamblea " + txtCaracter + " de Socios: " + usuarioSesion.getSociedadConstituye();
                    publicacionRegistro.setTituloPublica(TituloPublica);
                    String resumenPublica = "CONVOCATORIA A ASAMBLEA " + txtCaracter.toUpperCase() + " DE SOCIOS A LLEVARSE A CABO EL:" + df.format(fecJunta) + ", a horas:" + tf.format(horJunta);
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
                    horJunta=null;
                    fecJunta=null;
                    txtTemaJunta=null;
                    municipiosDoc=null;

                    String txtMensajeResultado
                          = "<div class=txtLabelNotifica>A continuación se detalla el Código de la Convocatoria a Asamblea " + txtCaracter + "</div>"
                          + "<p/>"
                          + "<div align=center class=txtCodigoPublica>Codigo de  Publicación:" + codigoPublica1 + "</div>"
                          + "<p/>"
                          + "<div align=center class=txtCodigoPublica>Recuerde que debe realizar el pago y la publicación al menos ocho (8) días previos a la realización de la misma </div>"
                          +"<div align=center><button type=button onclick=redirectUser()>Aceptar</button></div>";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);

                } catch (Exception ex) {
                    UtilsPresentacion.addMessage("Error en el Registro de la Asamblea", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            } else {
                UtilsPresentacion.addMessage("Aceptación", "Debe aceptar los términos para realizar la Asamblea", FacesMessage.SEVERITY_INFO);
            }
        }
        else
        {
            UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a Asamblea "+ txtCaracter +"será publicada al menos ocho(8) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
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
        txtPublicacion="";
        vistaPrev=true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        long diasConv;
        diasConv=fecJunta.getTime()- getFecPublica1().getTime();
        if (diasConv>=8) {
            if(contenidoOrden.size()>0) {
                txtPublicacion = "";
                txtPublicacion = "CONVOCATORIA A ASAMBLEA " + txtCaracter.toUpperCase() + " DE SOCIOS\n"
                      + " ----------------------------------------------------\n"
                      + usuarioSesion.getSociedadConstituye() + "\n";
                String txtOrdenCompleto = "";
                for (ordenDia listaOrden : getContenidoOrden()) {
                    txtOrdenCompleto = txtOrdenCompleto + listaOrden.getIdOrden() + "." + listaOrden.getOrdenDia() + "\n";
                }

                txtPublicacion = txtPublicacion + "<p>"+"El Representante Legal convoca a la Asamblea General" + txtCaracter + " de\n"
                      + "Socios, que se llevará a cabo el día " + df.format(fecJunta) + " , a horas " + tf.format(horJunta) + ", en el domicilio\n"
                      + "legal de la sociedad, ubicado en " + txtUbicacion + ", del municipio de " + distOrigen + ",\n"
                      + "Estado Plurinacional de Bolivia, para considerar el siguiente Orden del Día:" + "\n"
                      + txtOrdenCompleto;
            }else{
                UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
            }
        }
        else
        {
            UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a Asamblea General "+txtCaracter+" será publicada al menos ocho(8) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void generaPublica() {
        txtPublicacion="";
        vistaPrev=true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        long diasConv;
        diasConv=fecJunta.getTime()- getFecPublica1().getTime();
        if (diasConv>=7) {
            if(contenidoOrden.size()>0) {
                txtPublicacion = "";
                String txtOrdenCompleto = "";
                for (ordenDia listaOrden : getContenidoOrden()) {
                    txtOrdenCompleto = txtOrdenCompleto +"<p>"+ listaOrden.getIdOrden() + "." + listaOrden.getOrdenDia() + "</p>";
                }
                txtPublicacion = txtPublicacion + "<p>"+"El Representante Legal convoca a la Asamblea General" + txtCaracter + " de\n"
                      + "Socios, que se llevará a cabo el día " + df.format(fecJunta) + " , a horas " + tf.format(horJunta) + ", en el domicilio\n"
                      + "legal de la sociedad, ubicado en " + txtUbicacion + ", del municipio de " + distOrigen + ",\n"
                      + "Estado Plurinacional de Bolivia, para considerar el siguiente Orden del Día:" + "</p>"
                      + txtOrdenCompleto +"<br/>"
                      + "<br/>";
            }else{
                UtilsPresentacion.addMessage("Error en Información", "La orden del día debe contener mínimamente un ítem", FacesMessage.SEVERITY_ERROR);
            }
        }
        else
        {
            UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a Asamblea será publicada al menos ocho(8) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
        }

    }


    public void registraOrden(){
        if (!txtOrden.isEmpty()) {
            Integer indice = getContenidoOrden().size();
            indice++;
            ordenDia tmpOrden = new ordenDia();
            tmpOrden.setIdOrden(indice);
            tmpOrden.setOrdenDia(txtOrden);
            contenidoOrden.add(tmpOrden);
            txtOrden="";
        }
        else{
            UtilsPresentacion.addMessage("Falta de información", "Debe digitar el texto para la orden del día", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void eliminaItem(ordenDia ordenDia){
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
        if(txtSig.equals("vistaPrevia")){
            long diasConv;
            diasConv=fecJunta.getTime()- getFecPublica1().getTime();
            diasConv=diasConv / (1000 * 60 * 60 * 24);
            if (diasConv<8) {
                okVerif =false;
                UtilsPresentacion.addMessage("Error en datos consignados", "La convocatoria a Asamblea será publicada al menos ocho(8) dias previos a la realización de la misma.", FacesMessage.SEVERITY_ERROR);
            }else
            {
                this.generaPreview();
            }
        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
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

    public Date getFecPublica1() {
        return FecPublica1;
    }

    public void setFecPublica1(Date fecPublica1) {
        FecPublica1 = fecPublica1;
    }
}

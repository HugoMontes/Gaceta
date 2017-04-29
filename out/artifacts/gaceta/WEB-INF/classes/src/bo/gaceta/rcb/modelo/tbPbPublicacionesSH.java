package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


public class tbPbPublicacionesSH implements Serializable {

    private static final long serialVersionUID = 802004186471740103L;

    private String idPublicacion;
    private String idLoginEmpresa;
    private Date fecPublica;
    private Integer tipoDocumento;
    private String nroDocumento;
    private String origenDocumento;
    private Date fechaDocumento;
    private Integer ctrTipoPublica;
    private String urlFilePublica;
    private String tituloPublica;
    private String textoPublica;
    private String textoCompleto;
    private Date fecDisplay;
    private Date fecSolicitud;
    private Date fecAprueba;
    private String ctrDisplay;
    private String ctrUserAprueba;
    private String desEstadoPublica;
    private String codigoDeposito;
    private Double montoDeposito;
    private String idMatricula;

    public tbPbPublicacionesSH() {

    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getIdLoginEmpresa() {
        return idLoginEmpresa;
    }

    public void setIdLoginEmpresa(String idLoginEmpresa) {
        this.idLoginEmpresa = idLoginEmpresa;
    }

    public Date getFecPublica() {
        return fecPublica;
    }

    public void setFecPublica(Date fecPublica) {
        this.fecPublica = fecPublica;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getOrigenDocumento() {
        return origenDocumento;
    }

    public void setOrigenDocumento(String origenDocumento) {
        this.origenDocumento = origenDocumento;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Integer getCtrTipoPublica() {
        return ctrTipoPublica;
    }

    public void setCtrTipoPublica(Integer ctrTipoPublica) {
        this.ctrTipoPublica = ctrTipoPublica;
    }

    public String getUrlFilePublica() {
        return urlFilePublica;
    }

    public void setUrlFilePublica(String urlFilePublica) {
        this.urlFilePublica = urlFilePublica;
    }

    public String getTituloPublica() {
        return tituloPublica;
    }

    public void setTituloPublica(String tituloPublica) {
        this.tituloPublica = tituloPublica;
    }

    public String getTextoPublica() {
        return textoPublica;
    }

    public void setTextoPublica(String textoPublica) {
        this.textoPublica = textoPublica;
    }

    public String getTextoCompleto() {
        return textoCompleto;
    }

    public void setTextoCompleto(String textoCompleto) {
        this.textoCompleto = textoCompleto;
    }

    public Date getFecDisplay() {
        return fecDisplay;
    }

    public void setFecDisplay(Date fecDisplay) {
        this.fecDisplay = fecDisplay;
    }

    public Date getFecSolicitud() {
        return fecSolicitud;
    }

    public void setFecSolicitud(Date fecSolicitud) {
        this.fecSolicitud = fecSolicitud;
    }

    public Date getFecAprueba() {
        return fecAprueba;
    }

    public void setFecAprueba(Date fecAprueba) {
        this.fecAprueba = fecAprueba;
    }

    public String getCtrDisplay() {
        return ctrDisplay;
    }

    public void setCtrDisplay(String ctrDisplay) {
        this.ctrDisplay = ctrDisplay;
    }

    public String getCtrUserAprueba() {
        return ctrUserAprueba;
    }

    public void setCtrUserAprueba(String ctrUserAprueba) {
        this.ctrUserAprueba = ctrUserAprueba;
    }

    public String getDesEstadoPublica() {
        return desEstadoPublica;
    }

    public void setDesEstadoPublica(String desEstadoPublica) {
        this.desEstadoPublica = desEstadoPublica;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public Double getMontoDeposito() {
        return montoDeposito;
    }

    public void setMontoDeposito(Double montoDeposito) {
        this.montoDeposito = montoDeposito;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    public tbPbPublicacionesSH(String IdPublicacion, String IdLoginEmpresa, Date FecPublica, Integer TipoDocumento,
                               String NroDocumento, String OrigenDocumento, Date FechaDocumento, Integer CtrTipoPublica, String UrlFilePublica,
                               String TituloPublica, String TextoPublica, String TextoCompleto, Date FecDisplay, Date FecSolicitud, Date FecAprueba, String CtrDisplay, String CtrUserAprueba, String DesEstadoPublica, String IdMatricula) {
        this.idPublicacion = IdPublicacion;
        this.idLoginEmpresa = IdLoginEmpresa;
        this.fecPublica = FecPublica;
        this.tipoDocumento = TipoDocumento;
        this.nroDocumento = NroDocumento;
        this.origenDocumento = OrigenDocumento;
        this.fechaDocumento = FechaDocumento;
        this.ctrTipoPublica = CtrTipoPublica;
        this.urlFilePublica = UrlFilePublica;
        this.tituloPublica = TituloPublica;
        this.textoPublica = TextoPublica;
        this.textoCompleto = TextoCompleto;
        this.fecDisplay = FecDisplay;
        this.fecAprueba = FecAprueba;
        this.fecSolicitud = FecSolicitud;
        this.ctrDisplay = CtrDisplay;
        this.ctrUserAprueba = CtrUserAprueba;
        this.desEstadoPublica = DesEstadoPublica;
        this.idMatricula = IdMatricula;
    }
}

package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

import lombok.Data;

@Data
public class tbPbPublicaciones implements Serializable {
    private int idRegPublicacion;
    private String idPublicacion;
    private String idLoginEmpresa;
    private String idMatricula;
    private Integer tipoDocumento;
    private String nroDocumento;
    private String origenDocumento;
    private Date fechaDocumento;
    private Integer ctrTipoPublica;
    private String tituloPublica;
    private String textoPublica;
    private String textoCompleto;
    private Date fecPublica;
    private Date fecDisplay;
    private Date fecSolicitud;
    private String urlFilePublica;
    private String ctrDisplay;
    private String desEstadoPublica;
    private Date fecAprueba;
    private String ctrUserAprueba;
    private String codigoDeposito;
    private Double montoDeposito;
    private Date fecDeposito;
    private String fileUrlTransit;


    public tbPbPublicaciones() {

    }

    public tbPbPublicaciones(String IdPublicacion, String IdLoginEmpresa, Date FecPublica, Integer TipoDocumento,
                             String NroDocumento, String OrigenDocumento, Date FechaDocumento, Integer CtrTipoPublica, String UrlFilePublica,
                             String TituloPublica, String TextoPublica, String TextoCompleto, Date FecDisplay, Date FecSolicitud,Date FecAprueba, String CtrDisplay, String CtrUserAprueba, String DesEstadoPublica) {
        this.setIdPublicacion(IdPublicacion);
        this.setIdLoginEmpresa(IdLoginEmpresa);
        this.setFecPublica(FecPublica);
        this.setTipoDocumento(TipoDocumento);
        this.setNroDocumento(NroDocumento);
        this.setOrigenDocumento(OrigenDocumento);
        this.setFechaDocumento(FechaDocumento);
        this.setCtrTipoPublica(CtrTipoPublica);
        this.setUrlFilePublica(UrlFilePublica);
        this.setTituloPublica(TituloPublica);
        this.setTextoPublica(TextoPublica);
        this.setTextoCompleto(TextoCompleto);
        this.setFecDisplay(FecDisplay);
        this.setFecAprueba(FecAprueba);
        this.setCtrDisplay(CtrDisplay);
        this.setCtrUserAprueba(CtrUserAprueba);
        this.setDesEstadoPublica(DesEstadoPublica);
        this.setFecSolicitud(FecSolicitud);
    }

    public int getIdRegPublicacion() {
        return idRegPublicacion;
    }

    public void setIdRegPublicacion(int idRegPublicacion) {
        this.idRegPublicacion = idRegPublicacion;
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

    public Date getFecD() {
        return fecSolicitud;
    }

    public void setFecDeposito(Date fecDeposito) {
        this.fecDeposito = fecDeposito;
    }

    public Date getFecDeposito() {
        return fecDeposito;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
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

    public String getFileUrlTransit() {
        return fileUrlTransit;
    }

    public void setFileUrlTransit(String fileUrlTransit) {
        this.fileUrlTransit = fileUrlTransit;
    }


}

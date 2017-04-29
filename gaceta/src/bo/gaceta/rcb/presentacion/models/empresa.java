package bo.gaceta.rcb.presentacion.models;

import java.io.Serializable;
import java.util.Date;


public class empresa implements Serializable {
    private String nombreSociedad;
    private String codigoMunip;
    private String domicilioSociedad;
    private Integer anosVigencia;
    private String departamentoSociedad;
    private String tipoVigencia;
    private String txtActividadSecundaria;
    private Date DateFechaAceptacion;
    private String lugarAceptacion;
    private String fechaAceptacion;

    public String getNombreSociedad() {
        return nombreSociedad;
    }

    public void setNombreSociedad(String nombreSociedad) {
        this.nombreSociedad = nombreSociedad;
    }

    public String getCodigoMunip() {
        return codigoMunip;
    }

    public void setCodigoMunip(String codigoMunip) {
        this.codigoMunip = codigoMunip;
    }

    public String getDomicilioSociedad() {
        return domicilioSociedad;
    }

    public void setDomicilioSociedad(String domicilioSociedad) {
        this.domicilioSociedad = domicilioSociedad;
    }

    public Integer getAnosVigencia() {
        return anosVigencia;
    }

    public void setAnosVigencia(Integer anosVigencia) {
        this.anosVigencia = anosVigencia;
    }

    public String getDepartamentoSociedad() {
        return departamentoSociedad;
    }

    public void setDepartamentoSociedad(String departamentoSociedad) {
        this.departamentoSociedad = departamentoSociedad;
    }

    public String getTipoVigencia() {
        return tipoVigencia;
    }

    public void setTipoVigencia(String tipoVigencia) {
        this.tipoVigencia = tipoVigencia;
    }

    public String getTxtActividadSecundaria() {
        return txtActividadSecundaria;
    }

    public void setTxtActividadSecundaria(String txtActividadSecundaria) {
        this.txtActividadSecundaria = txtActividadSecundaria;
    }

    public Date getDateFechaAceptacion() {
        return DateFechaAceptacion;
    }

    public void setDateFechaAceptacion(Date dateFechaAceptacion) {
        DateFechaAceptacion = dateFechaAceptacion;
    }

    public String getLugarAceptacion() {
        return lugarAceptacion;
    }

    public void setLugarAceptacion(String lugarAceptacion) {
        this.lugarAceptacion = lugarAceptacion;
    }

    public String getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(String fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }
}

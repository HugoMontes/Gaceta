package bo.gaceta.rcb.modelo;


import java.io.Serializable;
import java.util.Date;

public class taEmpresa implements Serializable {
    private String codigoEmpresa;
    private String idUsuario;
    private String denominacion;
    private String departamento;
    private String domicilio;
    private String tipoDuracion;
    private Integer duracionNum;
    private String duracionText;
    private String aceptLugar;
    private String aceptFecha;
    private String organizaJuridica;
    private String categoriaMinuta;
    private String ciiuObjeto;
    private String objetoSocial;
    private Date fechaRegistro;
    private Integer nroTestimonio;
    private Integer idOrigenDoc;
    private Integer nroNotaria;
    private Date fechaDocumento;
    private String idDepartamentoDoc;
    private String idMunicipioDoc;
    private String ctrEstado;

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTipoDuracion() {
        return tipoDuracion;
    }

    public void setTipoDuracion(String tipoDuracion) {
        this.tipoDuracion = tipoDuracion;
    }

    public Integer getDuracionNum() {
        return duracionNum;
    }

    public void setDuracionNum(Integer duracionNum) {
        this.duracionNum = duracionNum;
    }

    public String getDuracionText() {
        return duracionText;
    }

    public void setDuracionText(String duracionText) {
        this.duracionText = duracionText;
    }

    public String getAceptLugar() {
        return aceptLugar;
    }

    public void setAceptLugar(String aceptLugar) {
        this.aceptLugar = aceptLugar;
    }

    public String getAceptFecha() {
        return aceptFecha;
    }

    public void setAceptFecha(String aceptFecha) {
        this.aceptFecha = aceptFecha;
    }

    public String getOrganizaJuridica() {
        return organizaJuridica;
    }

    public void setOrganizaJuridica(String organizaJuridica) {
        this.organizaJuridica = organizaJuridica;
    }

    public String getCategoriaMinuta() {
        return categoriaMinuta;
    }

    public void setCategoriaMinuta(String categoriaMinuta) {
        this.categoriaMinuta = categoriaMinuta;
    }

    public String getObjetoSocial() {
        return objetoSocial;
    }

    public void setObjetoSocial(String objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    public String getCiiuObjeto() {
        return ciiuObjeto;
    }

    public void setCiiuObjeto(String ciiuObjeto) {
        this.ciiuObjeto = ciiuObjeto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getNroTestimonio() {
        return nroTestimonio;
    }

    public void setNroTestimonio(Integer nroTestimonio) {
        this.nroTestimonio = nroTestimonio;
    }

    public Integer getIdOrigenDoc() {
        return idOrigenDoc;
    }

    public void setIdOrigenDoc(Integer idOrigenDoc) {
        this.idOrigenDoc = idOrigenDoc;
    }

    public Integer getNroNotaria() {
        return nroNotaria;
    }

    public void setNroNotaria(Integer idNotaria) {
        this.nroNotaria = idNotaria;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getIdDepartamentoDoc() {
        return idDepartamentoDoc;
    }

    public void setIdDepartamentoDoc(String idDepartamentoDoc) {
        this.idDepartamentoDoc = idDepartamentoDoc;
    }

    public String getIdMunicipioDoc() {
        return idMunicipioDoc;
    }

    public void setIdMunicipioDoc(String idMunicipioDoc) {
        this.idMunicipioDoc = idMunicipioDoc;
    }

    public String getCtrEstado() {
        return ctrEstado;
    }

    public void setCtrEstado(String ctrEstado) {
        this.ctrEstado = ctrEstado;
    }


}

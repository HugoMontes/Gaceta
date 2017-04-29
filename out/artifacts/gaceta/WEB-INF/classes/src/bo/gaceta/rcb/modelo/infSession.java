package bo.gaceta.rcb.modelo;

import java.io.Serializable;

public class infSession implements Serializable {
    private String idLoginEmpresa;
    private String idMatriculaAsociada;
    private Integer idTipoSocietario;
    private String correoElectronico;
    private String nombreRepresentante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
    private Integer idLugarExpedicion;
    private String sociedadConstituye;
    private String pathLogo;
    private String tipoCuenta;

    public String getIdLoginEmpresa() {
        return idLoginEmpresa;
    }

    public void setIdLoginEmpresa(String idLoginEmpresa) {
        this.idLoginEmpresa = idLoginEmpresa;
    }

    public String getIdMatriculaAsociada() {
        return idMatriculaAsociada;
    }

    public void setIdMatriculaAsociada(String idMatriculaAsociada) {
        this.idMatriculaAsociada = idMatriculaAsociada;
    }

    public Integer getIdTipoSocietario() {
        return idTipoSocietario;
    }

    public void setIdTipoSocietario(Integer idTipoSocietario) {
        this.idTipoSocietario = idTipoSocietario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getIdLugarExpedicion() {
        return idLugarExpedicion;
    }

    public void setIdLugarExpedicion(Integer idLugarExpedicion) {
        this.idLugarExpedicion = idLugarExpedicion;
    }

    public String getSociedadConstituye() {
        return sociedadConstituye;
    }

    public void setSociedadConstituye(String sociedadConstituye) {
        this.sociedadConstituye = sociedadConstituye;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}

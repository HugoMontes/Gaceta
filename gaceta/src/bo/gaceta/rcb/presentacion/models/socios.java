package bo.gaceta.rcb.presentacion.models;

import java.io.Serializable;


public class socios implements Serializable {
    private Integer idLinea;
    private String numIdentifica;
    private String extIdentifica;
    private String nombre;
    private String paterno;
    private String materno;
    private String tipoidentificacion;
    private String estadoCivil;
    private String profesion;
    private String domicilio;
    private String zona;
    private String nacionalidad;
    private int aporteCapital;
    private double numCuotas;
    private double participacion;
    private String descripcionBien;
    private String cargo;
    private String ctrTipoAporte;

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(String tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAporteCapital() {
        return aporteCapital;
    }

    public void setAporteCapital(int aporteCapital) {
        this.aporteCapital = aporteCapital;
    }

    public double getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(double numCuotas) {
        this.numCuotas = numCuotas;
    }

    public double getParticipacion() {
        return participacion;
    }

    public void setParticipacion(double participacion) {
        this.participacion = participacion;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCtrTipoAporte() {
        return ctrTipoAporte;
    }

    public void setCtrTipoAporte(String ctrTipoAporte) {
        this.ctrTipoAporte = ctrTipoAporte;
    }
}

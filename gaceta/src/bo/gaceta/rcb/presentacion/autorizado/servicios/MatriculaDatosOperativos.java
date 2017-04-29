package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MatriculaDatosOperativos complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="MatriculaDatosOperativos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CtrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoSocietario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaUltRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UltGestionRenovada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CtrEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CorreoElectronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdCtrOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculaDatosOperativos", propOrder = {
        "ctrResult",
        "idMatricula",
        "razonSocial",
        "tipoSocietario",
        "fechaInscripcion",
        "fechaUltRenovacion",
        "ultGestionRenovada",
        "nit",
        "ctrEstado",
        "correoElectronico",
        "direccion",
        "idCtrOrg"
})
public class MatriculaDatosOperativos {

    @XmlElement(name = "CtrResult")
    protected String ctrResult;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;
    @XmlElement(name = "TipoSocietario")
    protected String tipoSocietario;
    @XmlElement(name = "FechaInscripcion")
    protected String fechaInscripcion;
    @XmlElement(name = "FechaUltRenovacion")
    protected String fechaUltRenovacion;
    @XmlElement(name = "UltGestionRenovada")
    protected String ultGestionRenovada;
    @XmlElement(name = "Nit")
    protected String nit;
    @XmlElement(name = "CtrEstado")
    protected String ctrEstado;
    @XmlElement(name = "CorreoElectronico")
    protected String correoElectronico;
    @XmlElement(name = "Direccion")
    protected String direccion;
    @XmlElement(name = "IdCtrOrg")
    protected String idCtrOrg;

    /**
     * Obtiene el valor de la propiedad ctrResult.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCtrResult() {
        return ctrResult;
    }

    /**
     * Define el valor de la propiedad ctrResult.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCtrResult(String value) {
        this.ctrResult = value;
    }

    /**
     * Obtiene el valor de la propiedad idMatricula.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdMatricula() {
        return idMatricula;
    }

    /**
     * Define el valor de la propiedad idMatricula.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdMatricula(String value) {
        this.idMatricula = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSocietario.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTipoSocietario() {
        return tipoSocietario;
    }

    /**
     * Define el valor de la propiedad tipoSocietario.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTipoSocietario(String value) {
        this.tipoSocietario = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInscripcion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Define el valor de la propiedad fechaInscripcion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFechaInscripcion(String value) {
        this.fechaInscripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaUltRenovacion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFechaUltRenovacion() {
        return fechaUltRenovacion;
    }

    /**
     * Define el valor de la propiedad fechaUltRenovacion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFechaUltRenovacion(String value) {
        this.fechaUltRenovacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ultGestionRenovada.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUltGestionRenovada() {
        return ultGestionRenovada;
    }

    /**
     * Define el valor de la propiedad ultGestionRenovada.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUltGestionRenovada(String value) {
        this.ultGestionRenovada = value;
    }

    /**
     * Obtiene el valor de la propiedad nit.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNit() {
        return nit;
    }

    /**
     * Define el valor de la propiedad nit.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNit(String value) {
        this.nit = value;
    }

    /**
     * Obtiene el valor de la propiedad ctrEstado.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCtrEstado() {
        return ctrEstado;
    }

    /**
     * Define el valor de la propiedad ctrEstado.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCtrEstado(String value) {
        this.ctrEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad correoElectronico.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Define el valor de la propiedad correoElectronico.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

    /**
     * Obtiene el valor de la propiedad direccion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define el valor de la propiedad direccion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Obtiene el valor de la propiedad idCtrOrg.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdCtrOrg() {
        return idCtrOrg;
    }

    /**
     * Define el valor de la propiedad idCtrOrg.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdCtrOrg(String value) {
        this.idCtrOrg = value;
    }

}

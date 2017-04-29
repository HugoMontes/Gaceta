package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MatriculaDatosVigencia complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="MatriculaDatosVigencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CtrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaUltRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UltGestionRenovada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VigenciaMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FecVigenciaMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CtrEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculaDatosVigencia", propOrder = {
        "ctrResult",
        "idMatricula",
        "razonSocial",
        "fechaInscripcion",
        "fechaUltRenovacion",
        "ultGestionRenovada",
        "vigenciaMatricula",
        "fecVigenciaMatricula",
        "nit",
        "ctrEstado"
})
public class MatriculaDatosVigencia {

    @XmlElement(name = "CtrResult")
    protected String ctrResult;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;
    @XmlElement(name = "FechaInscripcion")
    protected String fechaInscripcion;
    @XmlElement(name = "FechaUltRenovacion")
    protected String fechaUltRenovacion;
    @XmlElement(name = "UltGestionRenovada")
    protected String ultGestionRenovada;
    @XmlElement(name = "VigenciaMatricula")
    protected String vigenciaMatricula;
    @XmlElement(name = "FecVigenciaMatricula")
    protected String fecVigenciaMatricula;
    @XmlElement(name = "Nit")
    protected String nit;
    @XmlElement(name = "CtrEstado")
    protected String ctrEstado;

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
     * Obtiene el valor de la propiedad vigenciaMatricula.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVigenciaMatricula() {
        return vigenciaMatricula;
    }

    /**
     * Define el valor de la propiedad vigenciaMatricula.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVigenciaMatricula(String value) {
        this.vigenciaMatricula = value;
    }

    /**
     * Obtiene el valor de la propiedad fecVigenciaMatricula.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFecVigenciaMatricula() {
        return fecVigenciaMatricula;
    }

    /**
     * Define el valor de la propiedad fecVigenciaMatricula.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFecVigenciaMatricula(String value) {
        this.fecVigenciaMatricula = value;
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

}

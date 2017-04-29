package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MatriculaActividad complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="MatriculaActividad"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CtrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TxtActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Seccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Division" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Clase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VersionClasificador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculaActividad", propOrder = {
        "ctrResult",
        "idMatricula",
        "txtActividad",
        "seccion",
        "division",
        "clase",
        "versionClasificador"
})
public class MatriculaActividad {

    @XmlElement(name = "CtrResult")
    protected String ctrResult;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    @XmlElement(name = "TxtActividad")
    protected String txtActividad;
    @XmlElement(name = "Seccion")
    protected String seccion;
    @XmlElement(name = "Division")
    protected String division;
    @XmlElement(name = "Clase")
    protected String clase;
    @XmlElement(name = "VersionClasificador")
    protected String versionClasificador;

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
     * Obtiene el valor de la propiedad txtActividad.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTxtActividad() {
        return txtActividad;
    }

    /**
     * Define el valor de la propiedad txtActividad.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTxtActividad(String value) {
        this.txtActividad = value;
    }

    /**
     * Obtiene el valor de la propiedad seccion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * Define el valor de la propiedad seccion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSeccion(String value) {
        this.seccion = value;
    }

    /**
     * Obtiene el valor de la propiedad division.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDivision() {
        return division;
    }

    /**
     * Define el valor de la propiedad division.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDivision(String value) {
        this.division = value;
    }

    /**
     * Obtiene el valor de la propiedad clase.
     *
     * @return possible object is
     * {@link String }
     */
    public String getClase() {
        return clase;
    }

    /**
     * Define el valor de la propiedad clase.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setClase(String value) {
        this.clase = value;
    }

    /**
     * Obtiene el valor de la propiedad versionClasificador.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersionClasificador() {
        return versionClasificador;
    }

    /**
     * Define el valor de la propiedad versionClasificador.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersionClasificador(String value) {
        this.versionClasificador = value;
    }

}

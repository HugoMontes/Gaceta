package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.*;


/**
 * <p>Clase Java para anonymous complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="keyContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idLibro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "idContrato",
        "keyContrato",
        "idMatricula",
        "idLibro",
        "numReg"
})
@XmlRootElement(name = "SrvImagen")
public class SrvImagen {

    protected String idContrato;
    protected String keyContrato;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    protected String idLibro;
    protected String numReg;

    /**
     * Obtiene el valor de la propiedad idContrato.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdContrato(String value) {
        this.idContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad keyContrato.
     *
     * @return possible object is
     * {@link String }
     */
    public String getKeyContrato() {
        return keyContrato;
    }

    /**
     * Define el valor de la propiedad keyContrato.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKeyContrato(String value) {
        this.keyContrato = value;
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
     * Obtiene el valor de la propiedad idLibro.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdLibro() {
        return idLibro;
    }

    /**
     * Define el valor de la propiedad idLibro.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdLibro(String value) {
        this.idLibro = value;
    }

    /**
     * Obtiene el valor de la propiedad numReg.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumReg() {
        return numReg;
    }

    /**
     * Define el valor de la propiedad numReg.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumReg(String value) {
        this.numReg = value;
    }

}

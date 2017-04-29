package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Representantes complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="Representantes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CtrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombreVinculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdClase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FecRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FecDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NoticiaDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoVinculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdLibro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Representantes", propOrder = {
        "ctrResult",
        "idMatricula",
        "nombreVinculo",
        "numId",
        "idClase",
        "fecRegistro",
        "numDoc",
        "fecDocumento",
        "noticiaDocumento",
        "tipoVinculo",
        "idLibro",
        "numReg"
})
public class Representantes {

    @XmlElement(name = "CtrResult")
    protected String ctrResult;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    @XmlElement(name = "NombreVinculo")
    protected String nombreVinculo;
    @XmlElement(name = "NumId")
    protected String numId;
    @XmlElement(name = "IdClase")
    protected String idClase;
    @XmlElement(name = "FecRegistro")
    protected String fecRegistro;
    @XmlElement(name = "NumDoc")
    protected String numDoc;
    @XmlElement(name = "FecDocumento")
    protected String fecDocumento;
    @XmlElement(name = "NoticiaDocumento")
    protected String noticiaDocumento;
    @XmlElement(name = "TipoVinculo")
    protected String tipoVinculo;
    @XmlElement(name = "IdLibro")
    protected String idLibro;
    @XmlElement(name = "NumReg")
    protected String numReg;

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
     * Obtiene el valor de la propiedad nombreVinculo.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNombreVinculo() {
        return nombreVinculo;
    }

    /**
     * Define el valor de la propiedad nombreVinculo.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNombreVinculo(String value) {
        this.nombreVinculo = value;
    }

    /**
     * Obtiene el valor de la propiedad numId.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumId() {
        return numId;
    }

    /**
     * Define el valor de la propiedad numId.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumId(String value) {
        this.numId = value;
    }

    /**
     * Obtiene el valor de la propiedad idClase.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdClase() {
        return idClase;
    }

    /**
     * Define el valor de la propiedad idClase.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdClase(String value) {
        this.idClase = value;
    }

    /**
     * Obtiene el valor de la propiedad fecRegistro.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFecRegistro() {
        return fecRegistro;
    }

    /**
     * Define el valor de la propiedad fecRegistro.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFecRegistro(String value) {
        this.fecRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad numDoc.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumDoc() {
        return numDoc;
    }

    /**
     * Define el valor de la propiedad numDoc.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumDoc(String value) {
        this.numDoc = value;
    }

    /**
     * Obtiene el valor de la propiedad fecDocumento.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFecDocumento() {
        return fecDocumento;
    }

    /**
     * Define el valor de la propiedad fecDocumento.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFecDocumento(String value) {
        this.fecDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad noticiaDocumento.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNoticiaDocumento() {
        return noticiaDocumento;
    }

    /**
     * Define el valor de la propiedad noticiaDocumento.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNoticiaDocumento(String value) {
        this.noticiaDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoVinculo.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTipoVinculo() {
        return tipoVinculo;
    }

    /**
     * Define el valor de la propiedad tipoVinculo.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTipoVinculo(String value) {
        this.tipoVinculo = value;
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

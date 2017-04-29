package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ImagenDatos complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="ImagenDatos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CtrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdLibro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodImagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RutaImagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagenDatos", propOrder = {
        "ctrResult",
        "idMatricula",
        "numReg",
        "idLibro",
        "codImagen",
        "rutaImagen"
})
public class ImagenDatos {

    @XmlElement(name = "CtrResult")
    protected String ctrResult;
    @XmlElement(name = "IdMatricula")
    protected String idMatricula;
    @XmlElement(name = "NumReg")
    protected String numReg;
    @XmlElement(name = "IdLibro")
    protected String idLibro;
    @XmlElement(name = "CodImagen")
    protected String codImagen;
    @XmlElement(name = "RutaImagen")
    protected String rutaImagen;

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
     * Obtiene el valor de la propiedad codImagen.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCodImagen() {
        return codImagen;
    }

    /**
     * Define el valor de la propiedad codImagen.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCodImagen(String value) {
        this.codImagen = value;
    }

    /**
     * Obtiene el valor de la propiedad rutaImagen.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * Define el valor de la propiedad rutaImagen.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRutaImagen(String value) {
        this.rutaImagen = value;
    }

}

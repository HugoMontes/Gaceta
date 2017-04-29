package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para ArrayOfImagenDatos complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="ArrayOfImagenDatos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ImagenDatos" type="{http://www.fundempresa.org.bo:10080/wsrcb}ImagenDatos" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfImagenDatos", propOrder = {
        "imagenDatos"
})
public class ArrayOfImagenDatos {

    @XmlElement(name = "ImagenDatos", nillable = true)
    protected List<ImagenDatos> imagenDatos;

    /**
     * Gets the value of the imagenDatos property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imagenDatos property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImagenDatos().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImagenDatos }
     */
    public List<ImagenDatos> getImagenDatos() {
        if (imagenDatos == null) {
            imagenDatos = new ArrayList<ImagenDatos>();
        }
        return this.imagenDatos;
    }

}

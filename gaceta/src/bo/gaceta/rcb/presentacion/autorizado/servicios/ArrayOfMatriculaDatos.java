package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para ArrayOfMatriculaDatos complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="ArrayOfMatriculaDatos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MatriculaDatos" type="{http://www.fundempresa.org.bo:10080/wsrcb}MatriculaDatos" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMatriculaDatos", propOrder = {
        "matriculaDatos"
})
public class ArrayOfMatriculaDatos {

    @XmlElement(name = "MatriculaDatos", nillable = true)
    protected List<MatriculaDatos> matriculaDatos;

    /**
     * Gets the value of the matriculaDatos property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matriculaDatos property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatriculaDatos().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatriculaDatos }
     */
    public List<MatriculaDatos> getMatriculaDatos() {
        if (matriculaDatos == null) {
            matriculaDatos = new ArrayList<MatriculaDatos>();
        }
        return this.matriculaDatos;
    }

}

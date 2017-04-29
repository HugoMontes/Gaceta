package bo.gaceta.rcb.presentacion.autorizado.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para ArrayOfMatriculaDatosVigencia complex type.
 * <p>
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;complexType name="ArrayOfMatriculaDatosVigencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MatriculaDatosVigencia" type="{http://www.fundempresa.org.bo:10080/wsrcb}MatriculaDatosVigencia" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMatriculaDatosVigencia", propOrder = {
        "matriculaDatosVigencia"
})
public class ArrayOfMatriculaDatosVigencia {

    @XmlElement(name = "MatriculaDatosVigencia", nillable = true)
    protected List<MatriculaDatosVigencia> matriculaDatosVigencia;

    /**
     * Gets the value of the matriculaDatosVigencia property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matriculaDatosVigencia property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatriculaDatosVigencia().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatriculaDatosVigencia }
     */
    public List<MatriculaDatosVigencia> getMatriculaDatosVigencia() {
        if (matriculaDatosVigencia == null) {
            matriculaDatosVigencia = new ArrayList<MatriculaDatosVigencia>();
        }
        return this.matriculaDatosVigencia;
    }

}

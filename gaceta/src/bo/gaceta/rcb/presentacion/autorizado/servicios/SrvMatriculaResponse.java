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
 *         &lt;element name="SrvMatriculaResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfMatriculaDatos" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvMatriculaResult"
})
@XmlRootElement(name = "SrvMatriculaResponse")
public class SrvMatriculaResponse {

    @XmlElement(name = "SrvMatriculaResult")
    protected ArrayOfMatriculaDatos srvMatriculaResult;

    /**
     * Obtiene el valor de la propiedad srvMatriculaResult.
     *
     * @return possible object is
     * {@link ArrayOfMatriculaDatos }
     */
    public ArrayOfMatriculaDatos getSrvMatriculaResult() {
        return srvMatriculaResult;
    }

    /**
     * Define el valor de la propiedad srvMatriculaResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfMatriculaDatos }
     */
    public void setSrvMatriculaResult(ArrayOfMatriculaDatos value) {
        this.srvMatriculaResult = value;
    }

}

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
 *         &lt;element name="SrvRepresentanteResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfRepresentantes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvRepresentanteResult"
})
@XmlRootElement(name = "SrvRepresentanteResponse")
public class SrvRepresentanteResponse {

    @XmlElement(name = "SrvRepresentanteResult")
    protected ArrayOfRepresentantes srvRepresentanteResult;

    /**
     * Obtiene el valor de la propiedad srvRepresentanteResult.
     *
     * @return possible object is
     * {@link ArrayOfRepresentantes }
     */
    public ArrayOfRepresentantes getSrvRepresentanteResult() {
        return srvRepresentanteResult;
    }

    /**
     * Define el valor de la propiedad srvRepresentanteResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfRepresentantes }
     */
    public void setSrvRepresentanteResult(ArrayOfRepresentantes value) {
        this.srvRepresentanteResult = value;
    }

}

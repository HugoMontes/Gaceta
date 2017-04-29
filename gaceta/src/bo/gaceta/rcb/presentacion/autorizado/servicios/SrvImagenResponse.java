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
 *         &lt;element name="SrvImagenResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfImagenDatos" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvImagenResult"
})
@XmlRootElement(name = "SrvImagenResponse")
public class SrvImagenResponse {

    @XmlElement(name = "SrvImagenResult")
    protected ArrayOfImagenDatos srvImagenResult;

    /**
     * Obtiene el valor de la propiedad srvImagenResult.
     *
     * @return possible object is
     * {@link ArrayOfImagenDatos }
     */
    public ArrayOfImagenDatos getSrvImagenResult() {
        return srvImagenResult;
    }

    /**
     * Define el valor de la propiedad srvImagenResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfImagenDatos }
     */
    public void setSrvImagenResult(ArrayOfImagenDatos value) {
        this.srvImagenResult = value;
    }

}

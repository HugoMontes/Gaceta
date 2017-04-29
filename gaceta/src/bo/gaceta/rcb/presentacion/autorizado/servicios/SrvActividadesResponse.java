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
 *         &lt;element name="SrvActividadesResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfMatriculaActividad" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvActividadesResult"
})
@XmlRootElement(name = "SrvActividadesResponse")
public class SrvActividadesResponse {

    @XmlElement(name = "SrvActividadesResult")
    protected ArrayOfMatriculaActividad srvActividadesResult;

    /**
     * Obtiene el valor de la propiedad srvActividadesResult.
     *
     * @return possible object is
     * {@link ArrayOfMatriculaActividad }
     */
    public ArrayOfMatriculaActividad getSrvActividadesResult() {
        return srvActividadesResult;
    }

    /**
     * Define el valor de la propiedad srvActividadesResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfMatriculaActividad }
     */
    public void setSrvActividadesResult(ArrayOfMatriculaActividad value) {
        this.srvActividadesResult = value;
    }

}

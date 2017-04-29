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
 *         &lt;element name="SrvMatriculaDatosOperativosResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfMatriculaDatosOperativos" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvMatriculaDatosOperativosResult"
})
@XmlRootElement(name = "SrvMatriculaDatosOperativosResponse")
public class SrvMatriculaDatosOperativosResponse {

    @XmlElement(name = "SrvMatriculaDatosOperativosResult")
    protected ArrayOfMatriculaDatosOperativos srvMatriculaDatosOperativosResult;

    /**
     * Obtiene el valor de la propiedad srvMatriculaDatosOperativosResult.
     *
     * @return possible object is
     * {@link ArrayOfMatriculaDatosOperativos }
     */
    public ArrayOfMatriculaDatosOperativos getSrvMatriculaDatosOperativosResult() {
        return srvMatriculaDatosOperativosResult;
    }

    /**
     * Define el valor de la propiedad srvMatriculaDatosOperativosResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfMatriculaDatosOperativos }
     */
    public void setSrvMatriculaDatosOperativosResult(ArrayOfMatriculaDatosOperativos value) {
        this.srvMatriculaDatosOperativosResult = value;
    }

}

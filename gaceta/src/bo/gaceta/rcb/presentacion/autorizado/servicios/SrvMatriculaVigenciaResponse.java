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
 *         &lt;element name="SrvMatriculaVigenciaResult" type="{http://www.fundempresa.org.bo:10080/wsrcb}ArrayOfMatriculaDatosVigencia" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "srvMatriculaVigenciaResult"
})
@XmlRootElement(name = "SrvMatriculaVigenciaResponse")
public class SrvMatriculaVigenciaResponse {

    @XmlElement(name = "SrvMatriculaVigenciaResult")
    protected ArrayOfMatriculaDatosVigencia srvMatriculaVigenciaResult;

    /**
     * Obtiene el valor de la propiedad srvMatriculaVigenciaResult.
     *
     * @return possible object is
     * {@link ArrayOfMatriculaDatosVigencia }
     */
    public ArrayOfMatriculaDatosVigencia getSrvMatriculaVigenciaResult() {
        return srvMatriculaVigenciaResult;
    }

    /**
     * Define el valor de la propiedad srvMatriculaVigenciaResult.
     *
     * @param value allowed object is
     *              {@link ArrayOfMatriculaDatosVigencia }
     */
    public void setSrvMatriculaVigenciaResult(ArrayOfMatriculaDatosVigencia value) {
        this.srvMatriculaVigenciaResult = value;
    }

}

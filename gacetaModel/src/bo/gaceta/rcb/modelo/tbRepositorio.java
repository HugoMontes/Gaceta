package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import java.util.Date;

public class tbRepositorio implements Serializable {
    private String idPublicacion;
    private Date fechaGeneracion;
    private byte[] imgPublicacion;
    private String urlFile;
    private String tipoArchivo;

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public byte[] getImgPublicacion() {
        return imgPublicacion;
    }

    public void setImgPublicacion(byte[] imgPublicacion) {
        this.imgPublicacion = imgPublicacion;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
}

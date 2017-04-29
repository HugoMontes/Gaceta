package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import java.util.Date;

public class tbUsLogo implements Serializable {
    private int  idRegLogo;
    private String idLoginEmpresa;
    private String pathFile;
    private String tipoArchivo;
    private byte[] imgLogo;
    private Date fechaCarga;

    public int getIdRegLogo() {
        return idRegLogo;
    }

    public void setIdRegLogo(int idRegLogo) {
        this.idRegLogo = idRegLogo;
    }

    public String getIdLoginEmpresa() {
        return idLoginEmpresa;
    }

    public void setIdLoginEmpresa(String idLoginEmpresa) {
        this.idLoginEmpresa = idLoginEmpresa;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(byte[] imgLogo) {
        this.imgLogo = imgLogo;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
}

package bo.gaceta.rcb.presentacion.models;

import java.io.Serializable;


public class capitalSRL implements Serializable {
    private int capitalSocial;
    private int cntCuotas;
    private int vrCuotas;

    public int getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(int capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public int getCntCuotas() {
        return cntCuotas;
    }

    public void setCntCuotas(int cntCuotas) {
        this.cntCuotas = cntCuotas;
    }

    public int getVrCuotas() {
        return vrCuotas;
    }

    public void setVrCuotas(int vrCuotas) {
        this.vrCuotas = vrCuotas;
    }
}

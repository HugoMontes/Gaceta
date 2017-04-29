package bo.gaceta.rcb.modelo;

import java.io.Serializable;

public class tbFnPerfil implements Serializable {


    private int  idFuncion;
    private String funcion;
    private String pageFuncion;
    private String icon;
    private int ctrMenu;
    private String  ctrDisplay;


    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getPageFuncion() {
        return pageFuncion;
    }

    public void setPageFuncion(String pageFuncion) {
        this.pageFuncion = pageFuncion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCtrMenu() {
        return ctrMenu;
    }

    public void setCtrMenu(int ctrMenu) {
        this.ctrMenu = ctrMenu;
    }

    public String getCtrDisplay() {
        return ctrDisplay;
    }

    public void setCtrDisplay(String ctrDisplay) {
        this.ctrDisplay = ctrDisplay;
    }
}

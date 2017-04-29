package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.bll.tbFnPerfilBll;
import bo.gaceta.rcb.bll.tbUsLogoBll;
import bo.gaceta.rcb.modelo.tbFnPerfil;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.modelo.tbUsLogo;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "perfilBean")
@ViewScoped
public class PerfilBean implements Serializable {

    private static final long serialVersionUID = -6551946628196697427L;

    private MenuModel model;
    private List<tbFnPerfil> funcionesPerfil;
    private tbFnPerfil fncPerfil;
    private tbUSLogin usuarioSesion;
    private tbUsLogo usuarioLogo;
    private StreamedContent dbImage;

    public PerfilBean() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @PostConstruct
    public void init() throws Exception {
        Integer idNivel = 0;
        setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));

        if (getUsuarioSesion().getIdMatriculaAsociada().equals("0")) {
            if (getUsuarioSesion().getIdTipoSocietario() == 3) {
                idNivel = 98;
            } else if (getUsuarioSesion().getIdTipoSocietario() == 6) {
                idNivel = 99;
            } else {
                idNivel = getUsuarioSesion().getIdTipoSocietario();
            }
        } else {
            idNivel = getUsuarioSesion().getIdTipoSocietario();
        }


        setFncPerfil(new tbFnPerfil());
        tbFnPerfilBll prfLoad = new tbFnPerfilBll();
        try {
            setFuncionesPerfil(prfLoad.listByPerfil(idNivel));
        } catch (Exception ex) {
            Logger.getLogger(PerfilBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        setModel(new DefaultMenuModel());
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Opciones de Publicación");
        for (tbFnPerfil opciones : getFuncionesPerfil()) {
            DefaultMenuItem item = new DefaultMenuItem(opciones.getFuncion());
            item.setUrl(opciones.getPageFuncion());
            item.setIcon(opciones.getIcon());
            firstSubmenu.addElement(item);
        }
        getModel().addElement(firstSubmenu);

        try {
            tbUsLogoBll logoBll = new tbUsLogoBll();
            setUsuarioLogo(logoBll.getPublica(getUsuarioSesion().getIdLoginEmpresa()));
            InputStream is = new ByteArrayInputStream(getUsuarioLogo().getImgLogo());
            setDbImage(new DefaultStreamedContent(is, "image/png"));
        } catch (Exception exLog) {
            Logger.getLogger(PerfilBean.class.getName()).log(Level.SEVERE, null, exLog);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Methods Getter and Setter">
    public tbFnPerfil getFncPerfil() {
        return fncPerfil;
    }

    public void setFncPerfil(tbFnPerfil fncPerfil) {
        this.fncPerfil = fncPerfil;
    }

    public List<tbFnPerfil> getFuncionesPerfil() {
        return funcionesPerfil;
    }

    public void setFuncionesPerfil(List<tbFnPerfil> funcionesPerfil) {
        this.funcionesPerfil = funcionesPerfil;
    }

    public tbUsLogo getUsuarioLogo() {
        return usuarioLogo;
    }

    public void setUsuarioLogo(tbUsLogo usuarioLogo) {
        this.usuarioLogo = usuarioLogo;
    }

    public StreamedContent getDbImage() {
        return dbImage;
    }

    public void setDbImage(StreamedContent dbImage) {
        this.dbImage = dbImage;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    // </editor-fold>

}

package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Named(value = "consultaPubBean")
@ViewScoped
public class ConsultaPubBean implements Serializable {

    private static final long serialVersionUID = 7366812546579173381L;
    private List<tbPbPublicaciones> listaPublicaciones;
    private tbPbPublicaciones publicacionSelecionada;
    private tbUSLogin usuarioSesion;

    public ConsultaPubBean() {

    }

    @PostConstruct
    public void ini() {
        publicacionSelecionada = new tbPbPublicaciones();
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        try {
            listaPublicaciones = pubBll.listByEmp(usuarioSesion.getIdLoginEmpresa());
        } catch (Exception ex) {
            Logger.getLogger(ConsultaPubBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

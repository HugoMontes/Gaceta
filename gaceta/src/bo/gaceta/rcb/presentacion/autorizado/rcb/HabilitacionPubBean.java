package bo.gaceta.rcb.presentacion.autorizado.rcb;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Named(value = "habilitacionPubBean")
@ViewScoped
public class HabilitacionPubBean implements Serializable {

    private static final long serialVersionUID = -6879993206326929972L;

    private tbUSLogin usuarioSesion;
    private List<tbPbPublicaciones> listaPublicaciones;
    private tbPbPublicaciones publicacionSelecionada;
    private Date dateFiltra;

    public HabilitacionPubBean() {

    }

    @PostConstruct
    public void ini() {
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
    }

    public void filtraFecha() {
        String fechaTxtFiltro = "";
        fechaTxtFiltro = new SimpleDateFormat("yyyy-MM-dd").format(dateFiltra);
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listPubFechaAprobacion(dateFiltra);
        } catch (Exception ex) {
            Logger.getLogger(HabilitacionPubBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "publicacionesBean")
@ViewScoped
public class PublicacionesBean implements Serializable {
    Calendar calendar = Calendar.getInstance();
    java.util.Date now = calendar.getTime();
    java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
    private List<tbPbPublicaciones> listaPublicaciones;
    private tbPbPublicaciones publicacionSelecionada;
    private tbUSLogin usuarioSesion;
    private Date dateFiltra;
    private Date dateFiltraFin;
    private String estadoPub;
    String fechaActual = "";
    String fechaFinal = "";

    public PublicacionesBean() {
    }

    @PostConstruct
    public void ini() {
        publicacionSelecionada = new tbPbPublicaciones();
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        fechaFinal= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        dateFiltra = calendar.getTime();
        setDateFiltraFin(calendar.getTime());
        try {
            listaPublicaciones = pubBll.listByEmp(usuarioSesion.getIdLoginEmpresa());
        } catch (Exception ex) {
            Logger.getLogger(PublicacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void filtraFecha() {
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(dateFiltra);
        fechaFinal = new SimpleDateFormat("yyyy/MM/dd").format(getDateFiltraFin());
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listPubFiltroDateMat(usuarioSesion.getIdLoginEmpresa(), dateFiltra, getDateFiltraFin());
        } catch (Exception ex) {
            Logger.getLogger(PublicacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filtroEstado() {
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listByEmpEst(usuarioSesion.getIdLoginEmpresa(),estadoPub);
        } catch (Exception ex) {
            Logger.getLogger(PublicacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAll() {
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listByEmp(usuarioSesion.getIdLoginEmpresa());
        } catch (Exception ex) {
            Logger.getLogger(PublicacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDateFiltraFin() {
        return dateFiltraFin;
    }

    public void setDateFiltraFin(Date dateFiltraFin) {
        this.dateFiltraFin = dateFiltraFin;
    }

    public String getEstadoPub() {
        return estadoPub;
    }

    public void setEstadoPub(String estadoPub) {
        this.estadoPub = estadoPub;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public List<tbPbPublicaciones> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<tbPbPublicaciones> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public tbPbPublicaciones getPublicacionSelecionada() {
        return publicacionSelecionada;
    }

    public void setPublicacionSelecionada(tbPbPublicaciones publicacionSelecionada) {
        this.publicacionSelecionada = publicacionSelecionada;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public Date getDateFiltra() {
        return dateFiltra;
    }

    public void setDateFiltra(Date dateFiltra) {
        this.dateFiltra = dateFiltra;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}

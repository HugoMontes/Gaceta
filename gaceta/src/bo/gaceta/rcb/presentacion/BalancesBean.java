package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbRepositorio;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "balancesBean")
@ViewScoped
public class BalancesBean implements Serializable {
    private Calendar calendar = Calendar.getInstance();
    private Date now = getCalendar().getTime();
    private java.sql.Timestamp currentTime = new java.sql.Timestamp(getNow().getTime());
    private String fechaActual = "";
    private String fechaFinal = "";
    private List<tbPbPublicaciones> listaPublicaciones;
    private Date dateFiltra;
    private Date dateFiltraFin;
    private String txtBusca;
    private String txtMatricula;
    private tbPbPublicaciones publicacionSelecionada;
    private Image imgPublicacion;
    private tbRepositorio regImg;
    private StreamedContent graphicText;

    public BalancesBean() {


    }

    @PostConstruct
    public void ini() {
        setFechaActual(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        setFechaFinal(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        setDateFiltra(getCalendar().getTime());
        setDateFiltraFin(getCalendar().getTime());
        setPublicacionSelecionada(new tbPbPublicaciones());
        setTxtBusca("");
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            setListaPublicaciones(pubBll.listPubTodayTip(4));
            if(getListaPublicaciones().size()<=0){
                setListaPublicaciones(pubBll.listEmptyDayTip(4));
            }
        } catch (Exception ex) {
            Logger.getLogger(BalancesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String  callVisor()  {
        String dataPost="idPublica="+ getPublicacionSelecionada().getIdPublicacion();
        return "visorpdfbal.xhtml?faces-redirect=true&" + dataPost;
    }

    public void filtraFecha() {
        setFechaActual(new SimpleDateFormat("yyyy/MM/dd").format(getDateFiltra()));
        setFechaFinal(new SimpleDateFormat("yyyy/MM/dd").format(getDateFiltraFin()));
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            setListaPublicaciones(pubBll.listPubFiltro(getDateFiltra(), getDateFiltraFin(),4));
        } catch (Exception ex) {
            Logger.getLogger(BalancesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filtraText() {
        if (getTxtBusca().length()>=3)
        {
            tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
            try {
                setListaPublicaciones(pubBll.listPubFiltroTxtTipo(getTxtBusca(),4));
            } catch (Exception ex) {
                Logger.getLogger(BalancesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            UtilsPresentacion.addMessage("Error en la Consulta", "Debe digitar el criterio de busqueda", FacesMessage.SEVERITY_ERROR);
        }
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

    public java.sql.Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(java.sql.Timestamp currentTime) {
        this.currentTime = currentTime;
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

    public List<tbPbPublicaciones> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<tbPbPublicaciones> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public Date getDateFiltra() {
        return dateFiltra;
    }

    public void setDateFiltra(Date dateFiltra) {
        this.dateFiltra = dateFiltra;
    }

    public Date getDateFiltraFin() {
        return dateFiltraFin;
    }

    public void setDateFiltraFin(Date dateFiltraFin) {
        this.dateFiltraFin = dateFiltraFin;
    }

    public String getTxtBusca() {
        return txtBusca;
    }

    public void setTxtBusca(String txtBusca) {
        this.txtBusca = txtBusca;
    }

    public String getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(String txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public tbPbPublicaciones getPublicacionSelecionada() {
        return publicacionSelecionada;
    }

    public void setPublicacionSelecionada(tbPbPublicaciones publicacionSelecionada) {
        this.publicacionSelecionada = publicacionSelecionada;
    }

    public Image getImgPublicacion() {
        return imgPublicacion;
    }

    public void setImgPublicacion(Image imgPublicacion) {
        this.imgPublicacion = imgPublicacion;
    }

    public tbRepositorio getRegImg() {
        return regImg;
    }

    public void setRegImg(tbRepositorio regImg) {
        this.regImg = regImg;
    }

    public StreamedContent getGraphicText() {
        return graphicText;
    }

    public void setGraphicText(StreamedContent graphicText) {
        this.graphicText = graphicText;
    }
}

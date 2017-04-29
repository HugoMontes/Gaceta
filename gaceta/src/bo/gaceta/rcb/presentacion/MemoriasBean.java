package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbRepositorio;
import lombok.Data;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.awt.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "memoriasBean")
@ViewScoped
public class MemoriasBean implements Serializable {
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();
    java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
    String fechaActual = "";
    String fechaFinal = "";
    private List<tbPbPublicaciones> listaPublicaciones;
    private Date dateFiltra;
    private Date dateFiltraFin;
    private String txtBusca;
    private String txtMatricula;

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

    private tbPbPublicaciones publicacionSelecionada;
    private Image imgPublicacion;
    private tbRepositorio regImg;
    private StreamedContent graphicText;

    public MemoriasBean() {


    }

    @PostConstruct
    public void ini() {
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        fechaFinal= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        dateFiltra = calendar.getTime();
        dateFiltraFin=calendar.getTime();
        publicacionSelecionada = new tbPbPublicaciones();
        txtBusca = "";
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listPubTodayTip(3);
            if(listaPublicaciones.size()<=0){
                listaPublicaciones=pubBll.listEmptyDayTip(3);
            }
        } catch (Exception ex) {
            Logger.getLogger(MemoriasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String  callVisor()  {
        String dataPost="idPublica="+publicacionSelecionada.getIdPublicacion();
        return "visorpdf.xhtml?faces-redirect=true&" + dataPost;
    }

    public void filtraFecha() {
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(dateFiltra);
        fechaFinal = new SimpleDateFormat("yyyy/MM/dd").format(dateFiltraFin);
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            listaPublicaciones = pubBll.listPubFiltro(dateFiltra,dateFiltraFin,3);
        } catch (Exception ex) {
            Logger.getLogger(MemoriasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filtraText() {
        if (txtBusca.length()>=3)
        {
            tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
            try {
                listaPublicaciones = pubBll.listPubFiltroTxtTipo(txtBusca,3);
            } catch (Exception ex) {
                Logger.getLogger(MemoriasBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            UtilsPresentacion.addMessage("Error en la Consulta", "Debe digitar el criterio de busqueda", FacesMessage.SEVERITY_ERROR);
        }
    }
}

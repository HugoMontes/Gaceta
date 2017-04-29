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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Named(value = "convocatoriasBean")
@ViewScoped
public class ConvocatoriasBean implements Serializable {
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
    private tbPbPublicaciones publicacionSelecionada;
    private Image imgPublicacion;
    private tbRepositorio regImg;
    private StreamedContent graphicText;

    public ConvocatoriasBean() {


    }

    @PostConstruct
    public void ini() {
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        fechaFinal = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        setDateFiltra(calendar.getTime());
        setDateFiltraFin(calendar.getTime());
        setPublicacionSelecionada(new tbPbPublicaciones());
        setTxtBusca("");
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            setListaPublicaciones(pubBll.listPubTodayTip(2));
            if (getListaPublicaciones().size() <= 0) {
                setListaPublicaciones(pubBll.listEmptyDayTip(2));
            }
        } catch (Exception ex) {
            Logger.getLogger(ConvocatoriasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String callVisor() {
        String dataPost = "idPublica=" + getPublicacionSelecionada().getIdPublicacion();
        return "visor.xhtml?faces-redirect=true&" + dataPost;
    }

    public void filtraFecha() {
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(getDateFiltra());
        fechaFinal = new SimpleDateFormat("yyyy/MM/dd").format(getDateFiltraFin());
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        try {
            setListaPublicaciones(pubBll.listPubFiltro(getDateFiltra(), getDateFiltraFin(), 2));
        } catch (Exception ex) {
            Logger.getLogger(ConvocatoriasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filtraText() throws Exception {
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        if (getTxtBusca().length() >= 3) {
            //tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
            /*
            try {
            */
                setListaPublicaciones(pubBll.listPubFiltroTxtTipo(getTxtBusca(), 2));
            /*
            } catch (Exception ex) {
                Logger.getLogger(ConvocatoriasBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        } else {
            setListaPublicaciones(pubBll.listPubTodayTip(2));
            // UtilsPresentacion.addMessage("Error en la Consulta", "Debe digitar el criterio de busqueda", FacesMessage.SEVERITY_ERROR);
        }
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

    public List<tbPbPublicaciones> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<tbPbPublicaciones> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }
}

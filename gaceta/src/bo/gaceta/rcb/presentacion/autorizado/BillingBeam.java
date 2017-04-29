package bo.gaceta.rcb.presentacion.autorizado;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.utils.generateImage;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named(value = "billingBean")
@ViewScoped
public class BillingBeam implements Serializable {



    Calendar calendar = Calendar.getInstance();
    private List<tbPbPublicaciones> listaPublicaciones;
    private tbPbPublicaciones publicacionSelecionada;
    private tbUSLogin usuarioSesion;
    private String codigoDeposito;
    private Date fechaDeposito;
    private Date dateFiltra;
    private double montoPagado;

    public BillingBeam() {

    }

    @PostConstruct
    public void ini() {
        dateFiltra = calendar.getTime();
        setPublicacionSelecionada(new tbPbPublicaciones());
        tbPbPublicacionesBll pubBll = new tbPbPublicacionesBll();
        setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));
        try {
            setListaPublicaciones(pubBll.listPagoPendiente (getUsuarioSesion().getIdLoginEmpresa()));
        } catch (Exception ex) {
            Logger.getLogger(PublicacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void registraPago() throws Exception {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        Date fecActual=new Date();
        boolean respTrans=false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fechaDeposito);
        cal2.setTime(now);
        if (cal1.before(cal2) | cal1.equals(cal2))
        {
            tbPbPublicacionesBll generaPago=new tbPbPublicacionesBll();
            getPublicacionSelecionada().setCodigoDeposito(codigoDeposito);
            getPublicacionSelecionada().setFecDeposito(fechaDeposito);
            getPublicacionSelecionada().setMontoDeposito(getMontoPagado());
            respTrans=   generaPago.registraPago(getPublicacionSelecionada(),"1","Publicado");
            if (  !respTrans )
                UtilsPresentacion.addMessage("Error en la publicacion", "No se pudo realizar la publicacion", FacesMessage.SEVERITY_ERROR);
            else {

                generateImage imgPub=new generateImage();
                int withImg;
                int heighImg;
                switch (getPublicacionSelecionada().getCtrTipoPublica())
                {
                    case 1:
                        withImg=800;
                        heighImg=1200;
                        imgPub.generaConvocatoriaFile(getPublicacionSelecionada().getTituloPublica(), getPublicacionSelecionada().getIdPublicacion(), getPublicacionSelecionada().getTextoCompleto(),usuarioSesion.getPathLogo(), withImg, heighImg);
                        break;
                    case 2:
                        withImg=800;
                        heighImg=600;
                        int longCont=getPublicacionSelecionada().getTextoCompleto().length();
                        if (longCont < 200){
                            heighImg=200;
                        }else {
                            if (longCont >= 200 & longCont < 500) {
                                heighImg = 320;
                            }else{
                                if (longCont >= 500 & longCont < 600) {
                                    heighImg = 700;
                                }
                            }
                        }
                        imgPub.generaFilePanel(getPublicacionSelecionada().getTituloPublica(), getPublicacionSelecionada().getIdPublicacion(), getPublicacionSelecionada().getTextoCompleto(), withImg, heighImg);
                        break;
                    case 3:
                        imgPub.generaFilePdf(getPublicacionSelecionada().getIdPublicacion(),getPublicacionSelecionada().getFileUrlTransit());
                        break;
                    default:
                        withImg=800;
                        heighImg=1200;
                        imgPub.generaFilePanel(getPublicacionSelecionada().getTituloPublica(), getPublicacionSelecionada().getIdPublicacion(), getPublicacionSelecionada().getTextoCompleto(), withImg, heighImg);
                        break;
                }

                UtilsPresentacion.addMessage("Publicación Correcta", "Se realizó la publicación correctamente", FacesMessage.SEVERITY_INFO);
                String txtMensajeResultado
                      = "<div class=txtLabelNotifica>Se ha registrado el deposito, se realizó la publicación  correctamente</div>"
                      + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro de Depósito", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        }
        else
        {
            UtilsPresentacion.addMessage("Error en la fecha de Deposito", "El deposito debe corresponder una fecha anterior o igual a la actual", FacesMessage.SEVERITY_INFO);
        }
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }
}

package bo.gaceta.rcb.presentacion.autorizado.perfiles;


import bo.gaceta.rcb.bll.TbTaCorrelativosBll;
import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.modelo.TbTaCorrelativos;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.utils.EnvioEmail;
import com.google.common.base.Strings;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

@Named(value = "balanceBean")
@ViewScoped
public class BalanceBean implements Serializable {
    private tbPbPublicaciones publicacionRegistro;
    private tbUSLogin usuarioSesion;
    private String CodigoPublicacion;
    private UploadedFile fileMemoria;
    private String nameFile;
    private String codigoFile;
    private long sizeFile;
    @Min(2010) @Max(2017)
    @Digits(integer=4,fraction=0)
    private  Integer gestionMemoria;
    private Boolean aceptacion;

    @PostConstruct
    public void init() {
        setCodigoPublicacion("");
        setAceptacion(false);
        setPublicacionRegistro(new tbPbPublicaciones());
        setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));
    }

    public void handleFileUpload(FileUploadEvent event) throws Exception {
        UploadedFile uploadedMem=event.getFile();
        String filePath="/var/www/gaceta/repositorio/pubbal/";
        byte[] bytes=null;
        if (null!=uploadedMem) {
            setUsuarioSesion((tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO));
            bytes = uploadedMem.getContents();
            setCodigoFile(generaCodigoArchivo()+".pdf");
            String filename = getCodigoFile();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+filename)));
            stream.write(bytes);
            stream.close();
            setNameFile(uploadedMem.getFileName());
            setSizeFile(uploadedMem.getSize()/1024);
        }
        FacesMessage message = new FacesMessage("Completado Correctamente", event.getFile().getFileName() + " se cargo correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        Boolean okVerif = true;
        String txtSig = event.getNewStep();
        if(txtSig.equals("datosCarga")){
            okVerif=true;
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            if (getPublicacionRegistro().getFecDisplay().before(now))
            {
                UtilsPresentacion.addMessage("Error en fecha de publicación", "La fecha de publicación debe ser posterior a la actual.", FacesMessage.SEVERITY_INFO);
                okVerif=false;
            }
        }
        if(txtSig.equals("publicacion")) {
            if (getSizeFile() <= 0)
            {
                UtilsPresentacion.addMessage("Falta Archivo PDF del Balance", "Debe cargar previamente el archivo en formato PDF.", FacesMessage.SEVERITY_INFO);
                okVerif=false;
            }else
            {
            okVerif=true;}

        }
        if (okVerif) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }

    }

    public void registraPublicacion() {
        if (getAceptacion()) {
            try {
                tbPbPublicacionesBll usBll = new tbPbPublicacionesBll();
                getPublicacionRegistro().setIdLoginEmpresa(getUsuarioSesion().getIdLoginEmpresa());
                String TituloPublica = "Publicación de Balance Anual para la Emision de Bonos de la Sociedad: " + getUsuarioSesion().getSociedadConstituye();
                getPublicacionRegistro().setTituloPublica(TituloPublica);
                getPublicacionRegistro().setTextoPublica(TituloPublica + " de la Gestión:" + getGestionMemoria().toString());
                getPublicacionRegistro().setCtrTipoPublica(4);
                getPublicacionRegistro().setTextoCompleto(TituloPublica + " de la Gestión:" + getGestionMemoria().toString());
                getPublicacionRegistro().setCtrDisplay("2");
                getPublicacionRegistro().setDesEstadoPublica("Esperando Pago");
                getPublicacionRegistro().setIdMatricula(getUsuarioSesion().getIdMatriculaAsociada());
                getPublicacionRegistro().setFileUrlTransit(getCodigoFile());
                String codigoPublica = usBll.add(getPublicacionRegistro());
                setPublicacionRegistro(null);

                EnvioEmail sendMail = new EnvioEmail();
                tbUSLogin user=(tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
                // String destinatario = "hugo927@hotmail.com";
                String destinatario = user.getCorreoElectronico();
                try {
                    String asunto = "Estado de la Publicación - Gaceta Electronica R.C.B.";
                    String message = "";
                    message += "<i>Se&ntilde;or empresaria(o), Se ha generado un código de publicación que a continuación se muestra, el mismo debe ser consignado o escribir en el formulario de Inscripción</i><br>";
                    message += "<b></b><br>";
                    message += "<i>Codigo de Publicación: " + CodigoPublicacion + "</i><br>";
                    message += "<p/>";
                    message += "<p/>";
                    message += "<a href='http://www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml'><span>www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml</span></a>";

                    sendMail.envioMail(destinatario, asunto, message);
                } catch (Exception ex1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Sin notificación por correo electronico");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }

                String txtMensajeResultado
                        = "<div class=txtLabelNotifica>Se ha generado un código de publicación que a continuación </div>"
                        + "<div class=txtLabelNotifica>se muestra, el mismo debe ser consignado o escribir en el formulario de inscripción</div><br>"
                        + "<div align=center class=txtCodigoPublica>Codigo de Publicación: <strong>" + CodigoPublicacion + "</strong></div><br>"
                        + "<div align=center class=txtCodigoPublica>El código tambien fue enviado a su cuenta de correo eletrónico <strong>"+destinatario+"</strong></div><br>"
                        + "<div align=center><button type=button onclick=redirectUser()  \">Aceptar</button></div>";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de Publicacion", txtMensajeResultado);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } catch (Exception ex) {
                UtilsPresentacion.addMessage("Error en el Registro de la Publicacion", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
        } else {
            UtilsPresentacion.addMessage("Debe aceptar los términos para realizar la publicacion", "Debe completar los campos requeridos", FacesMessage.SEVERITY_INFO);
        }
    }

    public String generaCodigoArchivo() throws Exception {
        String CodigoArchivo = "";
        TbTaCorrelativosBll genBll = new TbTaCorrelativosBll();
        TbTaCorrelativos dtGenerador;
        dtGenerador = genBll.ObtieneCorr(5);
        String leftPaddedString = Strings.padStart(dtGenerador.getNroCorrelativo().toString(), 5, '0');
        CodigoArchivo = dtGenerador.getPref() + relleno()  + leftPaddedString;
        return CodigoArchivo;
    }

    public String relleno() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < 4) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }



    public tbPbPublicaciones getPublicacionRegistro() {
        return publicacionRegistro;
    }

    public void setPublicacionRegistro(tbPbPublicaciones publicacionRegistro) {
        this.publicacionRegistro = publicacionRegistro;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getCodigoPublicacion() {
        return CodigoPublicacion;
    }

    public void setCodigoPublicacion(String codigoPublicacion) {
        CodigoPublicacion = codigoPublicacion;
    }

    public UploadedFile getFileMemoria() {
        return fileMemoria;
    }

    public void setFileMemoria(UploadedFile fileMemoria) {
        this.fileMemoria = fileMemoria;
    }

    public Integer getGestionMemoria() {
        return gestionMemoria;
    }

    public void setGestionMemoria(Integer gestionMemoria) {
        this.gestionMemoria = gestionMemoria;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public long getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(long sizeFile) {
        this.sizeFile = sizeFile;
    }

    public String getCodigoFile() {
        return codigoFile;
    }

    public void setCodigoFile(String codigoFile) {
        this.codigoFile = codigoFile;
    }
}

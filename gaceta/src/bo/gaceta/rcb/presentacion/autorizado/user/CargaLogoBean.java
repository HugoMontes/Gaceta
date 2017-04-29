package bo.gaceta.rcb.presentacion.autorizado.user;

import bo.gaceta.rcb.bll.tbUSLoginBll;
import bo.gaceta.rcb.bll.tbUsLogoBll;
import bo.gaceta.rcb.modelo.tbUSLogin;
import bo.gaceta.rcb.modelo.tbUsLogo;
import bo.gaceta.rcb.presentacion.UtilsPresentacion;
import bo.gaceta.rcb.presentacion.autorizado.PerfilBean;
import bo.gaceta.rcb.presentacion.autorizado.servicios.ArrayOfMatriculaDatos;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCB;
import bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "cargaLogoBean")
@ViewScoped
public class CargaLogoBean implements Serializable {
    public WebServiceRCB getService() {
        return service;
    }

    public void setService(WebServiceRCB service) {
        this.service = service;
    }

    public tbUSLogin getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(tbUSLogin usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(String txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ws.fundempresa.org.bo_10080/wsrcbdes/servrcb.asmx.wsdl")
    private WebServiceRCB service;
    private tbUSLogin usuarioSesion;
    private tbUsLogo usuarioLogo;
    String  txtMatricula;
    String razonSocial;
    private StreamedContent dbImage;
    private boolean logoReg;

    @PostConstruct
    public void init() throws Exception {
        usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
        WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        ArrayOfMatriculaDatos Result = port.srvMatricula("1020457026", "fundempresa1234567890", usuarioSesion.getIdMatriculaAsociada());
        txtMatricula = Result.getMatriculaDatos().get(0).getIdMatricula();
        razonSocial=Result.getMatriculaDatos().get(0).getRazonSocial();
        try
        {
            tbUsLogoBll logoBll =new tbUsLogoBll();
            setUsuarioLogo(logoBll.getPublica(usuarioSesion.getIdLoginEmpresa()));
            InputStream is = new ByteArrayInputStream(usuarioLogo.getImgLogo());
            setDbImage(new DefaultStreamedContent(is, "image/png"));
            logoReg=true;
        }catch (Exception ex){
            Logger.getLogger(PerfilBean.class.getName()).log(Level.SEVERE, null, ex);
            logoReg=false;
        }
    }


    public void uploadPhoto(FileUploadEvent e) throws Exception {

        UploadedFile uploadedPhoto=e.getFile();
        String filePath="/var/www/gaceta/repositorio/fileLogo/";
        byte[] bytes=null;
        if (null!=uploadedPhoto) {
            int lenFile=uploadedPhoto.getFileName().length();
            String ext2=uploadedPhoto.getContentType();
            String extension=uploadedPhoto.getFileName().substring(lenFile-4,lenFile);
            usuarioSesion = (tbUSLogin) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_USUARIO);
            bytes = uploadedPhoto.getContents();
            String filename = usuarioSesion.getIdMatriculaAsociada()+extension;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+filename)));
            stream.write(bytes);
            stream.close();
            tbUSLoginBll usBll = new tbUSLoginBll();
            usBll.updateLogo(usuarioSesion.getIdLoginEmpresa(),filename);
            tbUsLogo usLogoFile=new tbUsLogo();
            tbUsLogoBll logoBll=new tbUsLogoBll();
            usLogoFile.setTipoArchivo(uploadedPhoto.getContentType());
            usLogoFile.setIdLoginEmpresa(usuarioSesion.getIdLoginEmpresa());
            usLogoFile.setImgLogo(bytes);
            usLogoFile.setPathFile(filename);
            if(!logoReg) {
                logoBll.addLogo(usLogoFile);
            }else
            {
                logoBll.updateLogo(usLogoFile);
            }
        }
        FacesMessage message = new FacesMessage("Completado Correctamente", e.getFile().getFileName() + " se cargo correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private ArrayOfMatriculaDatos srvMatricula(java.lang.String idContrato, java.lang.String keyContrato, java.lang.String idMatricula) {
        bo.gaceta.rcb.presentacion.autorizado.servicios.WebServiceRCBSoap port = service.getWebServiceRCBSoap();
        return port.srvMatricula(idContrato, keyContrato, idMatricula);
    }

    public StreamedContent getDbImage() {
        return dbImage;
    }

    public void setDbImage(StreamedContent dbImage) {
        this.dbImage = dbImage;
    }

    public tbUsLogo getUsuarioLogo() {
        return usuarioLogo;
    }

    public void setUsuarioLogo(tbUsLogo usuarioLogo) {
        this.usuarioLogo = usuarioLogo;
    }

    public boolean isLogoReg() {
        return logoReg;
    }

    public void setLogoReg(boolean logoReg) {
        this.logoReg = logoReg;
    }
}

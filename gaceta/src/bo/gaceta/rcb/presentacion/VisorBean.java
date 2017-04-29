package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.bll.tbPbPublicacionesBll;
import bo.gaceta.rcb.bll.tbRepositorioBll;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import bo.gaceta.rcb.modelo.tbRepositorio;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

@Named(value = "visorBean")
@RequestScoped
public class VisorBean implements Serializable {
    private tbPbPublicaciones publicacionSelecionada;
    private tbRepositorio regImg;
    private String idPublica;
    private Image image;
    private String urlImagen;
    private String urlFile;
    private String pubNext;
    private String pubBack;

    private boolean existeNext;
    private boolean existBack;
    private boolean recarga = false;

    private StreamedContent dbImage;
    private StreamedContent graphicText;

    @PostConstruct
    public void init() throws Exception {
        Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.size() == 1) {
            //try {
            setRecarga(true);
            setIdPublica((String) params.get("idPublica"));
            String txtCodPublica = getIdPublica();
            UtilsPresentacion.setVariableSesion(UtilsPresentacion.VARIABLE_SESION_PUBLICA, txtCodPublica);
            tbPbPublicacionesBll tbpubBll = new tbPbPublicacionesBll();
            setPublicacionSelecionada(tbpubBll.getByID(txtCodPublica));
            tbRepositorioBll repBll = new tbRepositorioBll();
            setRegImg(repBll.getPublica(getIdPublica()));


            try {
                System.out.println("URL FILE: " + getRegImg().getUrlFile());
                setUrlFile(getRegImg().getUrlFile());

                if (getRegImg().getTipoArchivo().equals("png")) {
                    InputStream is = new ByteArrayInputStream(getRegImg().getImgPublicacion());
                    setDbImage(new DefaultStreamedContent(is, "image/png"));
                    BufferedImage bufferedImg = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = bufferedImg.createGraphics();
                    g2.drawString("IMAGEN PUBLICACIÓN", 0, 10);
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImg, "png", os);
                    setGraphicText(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            setPubNext(tbpubBll.codNext(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
            setPubBack(tbpubBll.codBack(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));

            /*
            // botones siguiente, anterior
            System.out.println("ID PUBLICA NUM:" + getPublicacionSelecionada().getIdRegPublicacion());
            System.out.println("CODIGO PUBLICA: " + getPublicacionSelecionada().getCtrTipoPublica());
            System.out.println("COD PUBLICACION ANTERIOR:..............." + pubBack);
            System.out.println("COD PUBLICACION ACTUAL:..............." + idPublica);
            System.out.println("COD PUBLICACION SIGUIENTE:..............." + pubNext);
            */

            if (getPublicacionSelecionada().getIdMatricula() == null) {
                getPublicacionSelecionada().setIdMatricula("En Trámite");
            }
            if (getPubNext().length() > 0) {
                setExisteNext(true);
            } else {
                setExisteNext(false);
            }
            if (getPubBack().length() > 0) {
                setExistBack(true);
            } else {
                setExistBack(false);
            }
        /*
            } catch (Exception e) {
                e.printStackTrace();
            }
        */
        } else {
            this.callVisorNext();
        }
    }

    public VisorBean() {
        setPublicacionSelecionada(new tbPbPublicaciones());
        setRegImg(new tbRepositorio());
    }

    public String callVisorNext() {
        changeAnteriorSiguiente();
        String dataPost = "idPublica=" + pubNext;
        return "visor.xhtml?faces-redirect=true&" + dataPost;
    }

    /*
      public void callVisorNext() {
        idPublica=(String) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_PUBLICA);
        try {
          tbPbPublicacionesBll tbpubBll = new tbPbPublicacionesBll();
          setPublicacionSelecionada(tbpubBll.getByID(idPublica));
          setPubNext(tbpubBll.codNext(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
          setPublicacionSelecionada(tbpubBll.getByID(pubNext));
          UtilsPresentacion.setVariableSesion(UtilsPresentacion.VARIABLE_SESION_PUBLICA, pubNext);
          idPublica=pubNext;
          tbRepositorioBll repBll = new tbRepositorioBll();
          setRegImg(repBll.getPublica(getIdPublica()));
          setUrlFile(getRegImg().getUrlFile());

          if (getRegImg().getTipoArchivo().equals("png")) {
            InputStream is = new ByteArrayInputStream(getRegImg().getImgPublicacion());
            setDbImage(new DefaultStreamedContent(is, "image/png"));
            BufferedImage bufferedImg = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImg.createGraphics();
            g2.drawString("PRUEBA DE GENERACION  DINAMICA DE IMAGEN  PARA PUBLICACION", 0, 10);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImg, "png", os);
            setGraphicText(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"));
          }
          if (getPublicacionSelecionada().getIdMatricula() == null) {
            getPublicacionSelecionada().setIdMatricula("En Trámite");
          }
          setPubNext(tbpubBll.codNext(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
          setPubBack(tbpubBll.codBack(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
          if (getPubNext() != null || getPubNext().length() > 0) {
            setExisteNext(true);
          } else {
            setExisteNext(false);
          }
          if (getPubBack() != null || getPubBack().length() > 0) {
            setExistBack(true);
          } else {
            setExistBack(false);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      */
    public String callVisorAfter() {
        changeAnteriorSiguiente();
        String dataPost = "idPublica=" + pubBack;
        return "visor.xhtml?faces-redirect=true&" + dataPost;
    }

    /*
      public void callVisorAfter() {
        // pubNext = "456";
      }
    */

    public void changeAnteriorSiguiente() {
        // recuperar id publica actual
        idPublica = (String) UtilsPresentacion.getVariableSesion(UtilsPresentacion.VARIABLE_SESION_PUBLICA);
        //System.out.println("COD PUBLICACION ACTUAL:..............." + idPublica);
        try {
            tbPbPublicacionesBll tbpubBll = new tbPbPublicacionesBll();
            setPublicacionSelecionada(tbpubBll.getByID(idPublica));
            //setPubBack(tbpubBll.codBack(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));

            setPubNext(tbpubBll.codNext(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
            setPubBack(tbpubBll.codBack(getPublicacionSelecionada().getIdRegPublicacion(), getPublicacionSelecionada().getCtrTipoPublica()));
/*
            System.out.println("ID PUBLICA NUM:" + getPublicacionSelecionada().getIdRegPublicacion());
            System.out.println("CODIGO PUBLICA: " + getPublicacionSelecionada().getCtrTipoPublica());
            System.out.println("COD PUBLICACION ANTERIOR:..............." + pubBack);
            System.out.println("COD PUBLICACION ACTUAL:..............." + idPublica);
            System.out.println("COD PUBLICACION SIGUIENTE:..............." + pubNext);
*/
            // botones siguiente, anterior
            if (getPubNext() != null || getPubNext().length() > 0) {
                setExisteNext(true);
            } else {
                setExisteNext(false);
            }
            if (getPubBack() != null || getPubBack().length() > 0) {
                setExistBack(true);
            } else {
                setExistBack(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public tbPbPublicaciones getPublicacionSelecionada() {
        return publicacionSelecionada;
    }

    public void setPublicacionSelecionada(tbPbPublicaciones publicacionSelecionada) {
        this.publicacionSelecionada = publicacionSelecionada;
    }

    public tbRepositorio getRegImg() {
        return regImg;
    }

    public void setRegImg(tbRepositorio regImg) {
        this.regImg = regImg;
    }

    public String getIdPublica() {
        return idPublica;
    }

    public void setIdPublica(String idPublica) {
        this.idPublica = idPublica;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getPubNext() {
        return pubNext;
    }

    public void setPubNext(String pubNext) {
        this.pubNext = pubNext;
    }

    public String getPubBack() {
        return pubBack;
    }

    public void setPubBack(String pubBack) {
        this.pubBack = pubBack;
    }

    public boolean isExisteNext() {
        return existeNext;
    }

    public void setExisteNext(boolean existeNext) {
        this.existeNext = existeNext;
    }

    public boolean isExistBack() {
        return existBack;
    }

    public void setExistBack(boolean existBack) {
        this.existBack = existBack;
    }

    public boolean isRecarga() {
        return recarga;
    }

    public void setRecarga(boolean recarga) {
        this.recarga = recarga;
    }

    public StreamedContent getDbImage() {
        return dbImage;
    }

    public void setDbImage(StreamedContent dbImage) {
        this.dbImage = dbImage;
    }

    public StreamedContent getGraphicText() {
        return graphicText;
    }

    public void setGraphicText(StreamedContent graphicText) {
        this.graphicText = graphicText;
    }
}

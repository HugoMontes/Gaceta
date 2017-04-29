package bo.gaceta.rcb.presentacion.utils;

import bo.gaceta.rcb.bll.tbRepositorioBll;
import bo.gaceta.rcb.modelo.tbRepositorio;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class generateImage implements Serializable {

private String fileOut;

    public void generaFile(String txtTitulo, String txtPublicacion, String txtCuerpo, int width,int height) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);

        String html ="<h3>Gaceta Electronica del Registro de Comercio de Bolivia </h3>"+
                "<hr width=\"100%\">"+
                "<h4>Fecha de Publicación:" +fechaActual +"Código de Publicación:"+txtPublicacion+"  </h4>"+
                "<h4>"+txtTitulo+"</h4>" +
                "<hr width=\"80%\">"+
                "<div><p>"+txtCuerpo+"</p></div>";
        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);
        Graphics graphics = image.createGraphics();
        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath="";
            realPath=(String) servletContext.getRealPath("/").trim();
            fileOut=realPath+"/generador/"+txtPublicacion+".png";
            fileOut=fileOut.trim();
            ImageIO.write(image, "png", new File(fileOut));
            File f1=new File(fileOut.trim());
            FileInputStream fin=new FileInputStream(f1);
            byte [] b1=new byte[(int)f1.length()];
            fin.read(b1);
            fin.close();

            tbRepositorioBll regImg=new tbRepositorioBll();
            tbRepositorio registro=new tbRepositorio();
            registro.setIdPublicacion(txtPublicacion);
            registro.setImgPublicacion(b1);
            registro.setFechaGeneracion(now);
            registro.setUrlFile("http://www.gacetadecomercio.gob.bo/gaceta/repositorio/img/"+txtPublicacion+".png");
            registro.setTipoArchivo("png");
            regImg.addPublica(registro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generaConvocatoriaFile(String txtTitulo, String txtPublicacion, String txtCuerpo, String urlLogo,int width,int height) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);
        String html ="<h3>Gaceta Electronica del Registro de Comercio de Bolivia</h3>"+
                "<hr width=\"100%\">"+
                "<h4>Fecha de Publicación:" +fechaActual +"Código de Publicación:"+txtPublicacion+"  </h4>"+
                "<h4>"+txtTitulo+"</h4>" +
                "<div><img src=\"/var/www/gaceta/repositorio/fileLogo/"+urlLogo+"\" alt=\"Logotipo\" style=\"width:100px;height:88px;\"> </div>"+
                "<hr width=\"80%\">"+
                "<div><p>"+txtCuerpo+"</p></div>";
        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);
        Graphics graphics = image.createGraphics();
        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath="";
            realPath=(String) servletContext.getRealPath("/").trim();
            fileOut=realPath.trim()+"/generador/"+txtPublicacion+".png";
            fileOut=fileOut.trim();
            ImageIO.write(image, "png", new File(fileOut));
            File f1=new File(fileOut);
            FileInputStream fin=new FileInputStream(f1);
            byte [] b1=new byte[(int)f1.length()];
            fin.read(b1);
            fin.close();

            tbRepositorioBll regImg=new tbRepositorioBll();
            tbRepositorio registro=new tbRepositorio();
            registro.setIdPublicacion(txtPublicacion);
            registro.setImgPublicacion(b1);
            registro.setFechaGeneracion(now);
            registro.setUrlFile("http://www.gacetadecomercio.gob.bo/gaceta/repositorio/img/"+txtPublicacion+".png");
            registro.setTipoArchivo("png");
            regImg.addPublica(registro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generaFilePanel(String txtTitulo, String txtPublicacion, String txtCuerpo, int width,int height) throws Exception {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);

        String html ="<html><head><style type=\"text/css\">BODY{font-family: Helvetica;font-size=0.8em}</style></head>" +
              "<h3 align=\"center\">Gaceta Electronica del Registro de Comercio de Bolivia </h3>"+
              "<hr width=\"100%\">"+
              "<table style=\"width:100%\"><tr><td ><b>Fecha de Publicación:</b></td>"+fechaActual+"<td><b>Código de Publicación:</b></td><td>"+txtPublicacion+"</td><td></td></tr></table>"+
              //"<h4>Fecha de Publicación:" +fechaActual +"Código de Publicación:"+txtPublicacion+"  </h4>"+
              "<h4>"+txtTitulo+"</h4>" +
              "<hr width=\"100%\">"+
              "<div style=\"width:790px\"><p>"+txtCuerpo+"</p></div></html>";

        JLabel label = new JLabel(html);
        label.setSize(width, height);
        label.setBounds(0,0,width,height);

        BufferedImage image = new BufferedImage(
              label.getWidth(), label.getHeight(),
              BufferedImage.TYPE_INT_ARGB);

        {
            Graphics g = image.getGraphics();
            g.setColor(Color.BLACK);
            label.paint(g);
            g.dispose();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();

        tbRepositorioBll regImg=new tbRepositorioBll();
        tbRepositorio registro=new tbRepositorio();
        registro.setIdPublicacion(txtPublicacion);
        registro.setImgPublicacion(bytes);
        registro.setFechaGeneracion(now);
        registro.setUrlFile("http://www.gacetadecomercio.gob.bo/gaceta/repositorio/img/"+txtPublicacion+".png");
        registro.setTipoArchivo("png");
        regImg.addPublica(registro);
    }


    public void generaFilePdf(String txtPublicacion,String nameFile) throws Exception {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);
        tbRepositorioBll regImg=new tbRepositorioBll();
        tbRepositorio registro=new tbRepositorio();
        registro.setIdPublicacion(txtPublicacion);
        registro.setFechaGeneracion(now);
        registro.setUrlFile("http://200.105.134.21/gaceta/repositorio/pubmem/"+nameFile);
        registro.setTipoArchivo("pdf");
        regImg.addPublica(registro);
    }


    public String getFileOut() {
        return fileOut;
    }

    public void setFileOut(String fileOut) {
        this.fileOut = fileOut;
    }
}

package bo.gaceta.rcb.bll.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class generateImage implements Serializable {

    public byte []  generaFile(String txtTitulo, String txtPublicacion, String txtCuerpo, int width,int height) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);

        String html = "<h2>"+txtTitulo+"</h2><h3>Código de Publicación"+txtPublicacion+"</h3><p>"+txtCuerpo+"</p>";
        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);
        Graphics graphics = image.createGraphics();
        // Create an `JEditorPane` and invoke `print(Graphics)`
        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);
        try {
            ImageIO.write(image, "png", new File(txtPublicacion+".png"));

            File f1=new File(txtPublicacion+".png");
            FileInputStream fin=new FileInputStream(f1);
            byte [] b1=new byte[(int)f1.length()];
            fin.read(b1);
            fin.close();
            return  b1;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

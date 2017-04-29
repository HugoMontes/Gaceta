package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.bll.utils.CryptoUtils;


import bo.gaceta.rcb.bll.utils.EnvioMail;
import bo.gaceta.rcb.dao.tbUSLoginDao;
import bo.gaceta.rcb.modelo.TbTaCorrelativos;
import bo.gaceta.rcb.modelo.tbUSLogin;
import com.google.common.base.Strings;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class tbUSLoginBll implements Serializable {
    private final tbUSLoginDao LoginDao = new tbUSLoginDao();

    public List<tbUSLogin> list() throws Exception {
        return LoginDao.list();
    }

    public tbUSLogin iniciarSesion(String IdUsuario, String password) throws Exception {
        tbUSLoginDao usDao = new tbUSLoginDao();
        tbUSLogin usuario = usDao.getByLogin(IdUsuario);
        if (usuario == null) {
            throw new Exception("Cuenta , Matrícula de Comercio o Contraseña es incorrecta");
        }
        String campoCalculado = this.transformarPassword(IdUsuario, password);
        if (!usuario.getClaveIngreso().equals(campoCalculado)) {
            throw new Exception("Cuenta , Matrícula de Comercio o Contraseña es incorrecta");
        }
        usuario.setClaveIngreso("");
        return usuario;
    }

    public String transformarPassword(String usuario, String password) {
        String calculado = null;
        String serieGen="4NFhnDn79w";
        try {
            calculado = CryptoUtils.calculateHash(usuario.toUpperCase() + password + serieGen, "MD5");
            calculado = CryptoUtils.calculateHash(calculado, "SHA-1");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return calculado;
    }

    public Boolean cambioPassword(String usuario, String password,String passwordNew) {
        tbUSLoginDao usDao = new tbUSLoginDao();
        password=transformarPassword(usuario,password);
        passwordNew=transformarPassword(usuario,passwordNew);
        Boolean RespChange = usDao.changePass(usuario, password, passwordNew);
        return RespChange;
    }

    public String add(tbUSLogin obj) throws Exception {
        String resAdd = "";
        String sociedadConstituye;
        String passwordOriginal;
        if (this.validateAdd(obj)) {
            String CodigoEmpresa;
            String  passwordGenerado;
            if (obj.getIdMatriculaAsociada().equals("0")) {
                CodigoEmpresa = generadorUsuario();
                sociedadConstituye=obj.getSociedadConstituye();
                obj.setSociedadConstituye(sociedadConstituye.toUpperCase());
                obj.setTipoCuenta("2");
            } else {
                CodigoEmpresa = obj.getIdMatriculaAsociada();
                obj.setTipoCuenta("1");
            }
            obj.setIdLoginEmpresa(CodigoEmpresa);
            passwordGenerado=generaPassword();
            String campoCalculado = this.transformarPassword(obj.getIdLoginEmpresa(), passwordGenerado);
            obj.setClaveIngreso(campoCalculado);
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            obj.setFechaSolicitud(currentTime);
            obj.setCtrEstadoCuenta("0");
            resAdd = LoginDao.add(obj);
            EnvioMail sendMail = new EnvioMail();
            try {
                sendMail.envioMail(obj.getCorreoElectronico(), obj.getIdLoginEmpresa(), passwordGenerado);
            } catch (Exception ex1) {
                resAdd = resAdd + ", Sin notificación por correo electronico";
            }
            return resAdd;
        }
        throw new Exception("No se pudo registrar la Solicitud, Error:" + resAdd);
    }

    protected boolean validateAdd(tbUSLogin obj) throws Exception {
        return true;
    }


    public String generadorUsuario() throws Exception{
        String CodigoUsuario="";
        TbTaCorrelativosBll genBll=new TbTaCorrelativosBll();
        TbTaCorrelativos dtGenerador;
        dtGenerador=genBll.ObtieneCorr(1);
        String leftPaddedString = Strings.padStart(dtGenerador.getNroCorrelativo().toString(), 5, '0');
        CodigoUsuario=dtGenerador.getPref()+relleno()+leftPaddedString;

        return CodigoUsuario;
    }

    public String generaPassword() {
        String cadenaAleatoria = "";
        String cadenaPassword="";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < 8) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        cadenaPassword=cadenaAleatoria;
        return cadenaPassword;
    }

    public String relleno() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < 3) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    public Boolean updateLogo(String idUsuario,String pathLogo){
        tbUSLoginDao usDao = new tbUSLoginDao();
        return usDao.updateLogo(idUsuario,pathLogo);
    }

}

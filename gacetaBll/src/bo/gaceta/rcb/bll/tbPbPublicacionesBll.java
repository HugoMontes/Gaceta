package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbPbPublicacionesDao;
import bo.gaceta.rcb.modelo.TbTaCorrelativos;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class tbPbPublicacionesBll implements Serializable {

    private final tbPbPublicacionesDao PublicaDao = new tbPbPublicacionesDao();
    private Object FacesContext;

    public List<tbPbPublicaciones> listEmptyDay() throws Exception {
        return PublicaDao.listEmptyDay();
    }

    public List<tbPbPublicaciones> listEmptyDayTip(int tipoPub) throws Exception {
        return PublicaDao.listEmptyDayTip(tipoPub);
    }


    public List<tbPbPublicaciones> list() throws Exception {
        return PublicaDao.list();
    }

    public String codNext(int idPublicaNum,int codPublica ) throws Exception {
        return PublicaDao.getNextID(idPublicaNum,codPublica);
    }

    public String codBack(int idPublicaNum,int codPublica) throws Exception {
        return PublicaDao.getAntID(idPublicaNum,codPublica);
    }

    public List<tbPbPublicaciones> listPubToday() throws Exception {
        return PublicaDao.listPubToday();
    }

    public List<tbPbPublicaciones> listPubTodayTip(int tipoPub) throws Exception {
        return PublicaDao.listPubTodayTip(tipoPub);
    }

    public List<tbPbPublicaciones> listPubFiltroMatricula(String IdMatricula) throws Exception {
        return PublicaDao.listPubFiltroMatricula(IdMatricula);
    }

    public List<tbPbPublicaciones> listPubFiltroTxt(String txtBusqueda) throws Exception {
        return PublicaDao.listPubFiltroTxt(txtBusqueda);
    }

    public List<tbPbPublicaciones> listPubFiltroTxtTipo(String txtBusqueda,int tipPublica) throws Exception {
        return PublicaDao.listPubFiltroTxtTipo(txtBusqueda,tipPublica);
    }

    public List<tbPbPublicaciones> listPubFiltro(Date dateFiltra,Date dateFiltraFin) throws Exception {
        return PublicaDao.listPubFiltro(dateFiltra,dateFiltraFin);
    }

    public List<tbPbPublicaciones> listPubFiltro(Date dateFiltra,Date dateFiltraFin,int tipPublica) throws Exception {
        return PublicaDao.listPubFiltroTipo(dateFiltra,dateFiltraFin,tipPublica);
    }

    public List<tbPbPublicaciones> listPubFiltroDateMat(String idMatricula,Date dateFiltra,Date dateFiltraFin) throws Exception {
        return PublicaDao.listPubFiltroDateMat(idMatricula,dateFiltra,dateFiltraFin);
    }



    public List<tbPbPublicaciones> listPubFechaAprobacion(Date dateFiltra) throws Exception {
        return PublicaDao.listPubFechaAprobacion(dateFiltra);
    }

    public List<tbPbPublicaciones> listPubFiltroCont(String txtBusca) throws Exception {
        return PublicaDao.listPubFiltroCont(txtBusca);
    }

    public List<tbPbPublicaciones> listByEmpEst(String idEmpresa,String estado) throws Exception {
        return PublicaDao.listByEmpEst(idEmpresa,estado);
    }

    public List<tbPbPublicaciones> listByEmp(String idEmpresa) throws Exception {
        return PublicaDao.listByEmp(idEmpresa);
    }

    public List<tbPbPublicaciones> listPagoPendiente(String idEmpresa) throws Exception {
        return PublicaDao.listPagoPendiente(idEmpresa);
    }

    public tbPbPublicaciones getByID(String idPublicacion) throws Exception {
        return PublicaDao.getByID(idPublicacion);
    }

    public boolean registraPago(tbPbPublicaciones obj,String ctrEstado,String ctrDescripcion) throws Exception {
        obj.setCtrDisplay(ctrEstado);
        obj.setDesEstadoPublica(ctrDescripcion);
        return  PublicaDao.updateEstado(obj);
    }

    public String generaCodigoPublicacion() throws Exception {
        String CodigoPublicacion = "";
        TbTaCorrelativosBll genBll = new TbTaCorrelativosBll();
        TbTaCorrelativos dtGenerador;
        dtGenerador = genBll.ObtieneCorr(2);
        String leftPaddedString = Strings.padStart(dtGenerador.getNroCorrelativo().toString(), 5, '0');
        CodigoPublicacion = dtGenerador.getPref() + relleno()  + leftPaddedString;

        return CodigoPublicacion;
    }


    public String generaCodigoRevision() throws Exception {
        String CodigoPublicacion = "";
        TbTaCorrelativosBll genBll = new TbTaCorrelativosBll();
        TbTaCorrelativos dtGenerador;
        dtGenerador = genBll.ObtieneCorr(4);
        String leftPaddedString = Strings.padStart(dtGenerador.getNroCorrelativo().toString(), 5, '0');
        CodigoPublicacion = dtGenerador.getPref() + relleno()  + leftPaddedString;

        return CodigoPublicacion;
    }

    public String generaCodigoConvocatoria() throws Exception {
        String CodigoPublicacion = "";
        TbTaCorrelativosBll genBll = new TbTaCorrelativosBll();
        TbTaCorrelativos dtGenerador;
        dtGenerador = genBll.ObtieneCorr(3);
        String leftPaddedString = Strings.padStart(dtGenerador.getNroCorrelativo().toString(), 5, '0');
        CodigoPublicacion = dtGenerador.getPref()  + relleno()  + leftPaddedString;

        return CodigoPublicacion;
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

    public String add(tbPbPublicaciones obj) throws Exception {
        if (this.validateAdd(obj)) {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            String CodigoPublicacion;
            CodigoPublicacion = this.generaCodigoPublicacion();
            if (obj.getCtrDisplay().equals("0")) {
                obj.setDesEstadoPublica("En Revisión");
            }
            if (obj.getCtrDisplay().equals("1")) {
                obj.setDesEstadoPublica("Publicado");
            }
            obj.setIdMatricula(obj.getIdLoginEmpresa());
            obj.setIdPublicacion(CodigoPublicacion);
            obj.setFecPublica(currentTime);
            obj.setFecSolicitud(now);
            PublicaDao.add(obj);
            return CodigoPublicacion;
        }
        throw new Exception("No se pudo registrar la Publicacion");
    }

    public String addConv(tbPbPublicaciones obj) throws Exception {
        if (this.validateAdd(obj)) {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTime = new java.sql.Timestamp(now.getTime());
            String CodigoPublicacion;
            CodigoPublicacion = this.generaCodigoConvocatoria();
            obj.setIdPublicacion(CodigoPublicacion);
            obj.setFecPublica(currentTime);
            obj.setFecSolicitud(now);
            obj.setCtrTipoPublica(2);
            PublicaDao.add(obj);
            return CodigoPublicacion;
        }
        throw new Exception("No se pudo registrar la Convocatoria");
    }

    protected boolean validateAdd(tbPbPublicaciones obj) throws Exception {
        return true;
    }

}

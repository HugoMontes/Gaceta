package bo.gaceta.rcb.bll;


import bo.gaceta.rcb.bll.utils.CryptoUtils;
import bo.gaceta.rcb.dao.taEmpresaDao;
import bo.gaceta.rcb.modelo.taEmpresa;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static bo.gaceta.rcb.bll.utils.convertNum2LitSM.convertNumberToLetterSM;

public class taEmpresaBll implements Serializable {
    //static final Logger logger = LogManager.getLogger(tbUSLoginImpl.class.getName());
    private final taEmpresaDao EmpresaDao = new taEmpresaDao();
    private Object FacesContext;

    public String codigoEmpresa(String empresa, String usaurio) {
        String calculado = null;
        try {
            calculado = CryptoUtils.calculateHash(empresa.toUpperCase() + usaurio, "MD5");
            calculado = CryptoUtils.calculateHash(calculado, "SHA-1");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return calculado;
    }

    public String add(taEmpresa obj) throws Exception {
        if (this.validateAdd(obj)) {
            String CodigoEmpresa;
            if(obj.getDuracionNum() != null) {
                String anDur = obj.getDuracionNum().toString();
                obj.setDuracionText(convertNumberToLetterSM(anDur).toLowerCase());
            }
            CodigoEmpresa = obj.getCodigoEmpresa();
            EmpresaDao.add(obj);
            return CodigoEmpresa;
        }
        throw new Exception("No se pudo registrar la Empresa");
    }

    public void update(taEmpresa obj){
        try{
            EmpresaDao.update(obj);
        }
        catch (Exception ex){
            System.console().printf(ex.getMessage());
        }


    }

    public taEmpresa listByIdEmpresa(String IdEmpresa) throws Exception {
        taEmpresa resEmpresa = EmpresaDao.getByIdEmpresa(IdEmpresa);
        if (resEmpresa == null) {
            throw new Exception("El código de Empresa no existe");
        }
        return resEmpresa;
    }

    public taEmpresa getIdEmpresa(String IdEmpresa) throws Exception {
        taEmpresa resEmpresa = EmpresaDao.getByIdEmpresa(IdEmpresa);
        return resEmpresa;
    }

    public List<taEmpresa> listByUsuario(String idUsuario) throws Exception {
        List<taEmpresa> resEmpresa = EmpresaDao.getByIdUsuario(idUsuario);
        if (resEmpresa == null) {
            throw new Exception("El usuario no tiene emprendimientos creados");
        }
        return resEmpresa;
    }

    protected boolean validateAdd(taEmpresa obj) throws Exception {
        return true;
    }

    public boolean verifByIdEmpresa(String idEmpresa)
    {
        boolean resp;
        resp=EmpresaDao.verifByIdEmpresa(idEmpresa);
        return resp;
    }
}

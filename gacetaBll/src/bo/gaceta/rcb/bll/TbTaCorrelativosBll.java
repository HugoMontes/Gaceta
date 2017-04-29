package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.TbTaCorrelativosDao;
import bo.gaceta.rcb.modelo.TbTaCorrelativos;
import java.io.Serializable;


public class TbTaCorrelativosBll implements Serializable{
  private static final long serialVersionUID = -8224516731123010106L;
   private final TbTaCorrelativosDao perfilDao = new TbTaCorrelativosDao();
  
   
   public TbTaCorrelativos ObtieneCorr(Integer idCorr ) throws Exception
   {
     return perfilDao.getValCorr(idCorr);
   }
   
}

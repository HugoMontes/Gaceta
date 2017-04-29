
package bo.gaceta.rcb.bll.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {
     public static String calculateHash(String texto, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest cript = MessageDigest.getInstance(algorithm);
              cript.reset();
        cript.update(texto.getBytes("utf8"));        
        return new BigInteger(1, cript.digest()).toString(16);
     }
     
      public static String generaActivacion(String texto, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest cript = MessageDigest.getInstance(algorithm);
              cript.reset();
        cript.update(texto.getBytes("utf8"));        
        return new BigInteger(1, cript.digest()).toString(16);
     }
     
}

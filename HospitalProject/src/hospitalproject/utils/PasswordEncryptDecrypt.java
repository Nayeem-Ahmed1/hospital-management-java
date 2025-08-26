
package hospitalproject.utils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class PasswordEncryptDecrypt {
    
    private static String SECRET_KEY = "1234567890123456";
        
    
    public static String encrypt(String plainText) throws Exception{
        
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(),"AES");
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte[] encodedData = cipher.doFinal(plainText.getBytes());
        
        return Base64.getEncoder().encodeToString(encodedData);
    }
       
    
    public static String decrypt(String encodedStr) throws Exception{
        
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(),"AES");
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
        byte[] decodedData = cipher.doFinal(decodedBytes);
        
        return new String(decodedData);
        
    }
    
}

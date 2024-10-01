package backEndEncodingDecoding;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncrypAndDecryptUtility {

	public String encrypt(String input,String secretKey) throws Exception {
		
		SecretKeySpec secretkeyspec=new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivparameter=new IvParameterSpec("4234567890123456".getBytes());
		Cipher ci=Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.ENCRYPT_MODE,secretkeyspec,ivparameter );
		byte[] encrypted=ci.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
		
		
	}
	
	public String decrypt(String input,String secretKey)throws Exception{
		
		SecretKeySpec secretkeyspec=new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivparameter=new IvParameterSpec("4234567890123456".getBytes());
		Cipher ci=Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.DECRYPT_MODE,secretkeyspec,ivparameter );
		byte[] decrypted=ci.doFinal(Base64.getDecoder().decode(input));
		return new String(decrypted);
		
		
	}
}

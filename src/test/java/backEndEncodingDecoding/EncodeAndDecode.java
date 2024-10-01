package backEndEncodingDecoding;

import java.util.Base64;

import org.testng.annotations.Test;

public class EncodeAndDecode {

	@Test
	public void encodeData() {
		
		
		String encode = new String(Base64.getEncoder().encode("Sankar".getBytes()));
		
		System.out.println(encode);
		
		
	}
	@Test
	public void decode() {
		
		String decode = new String (Base64.getDecoder().decode("cm1neWFudHJhOnJtZ3lAOTk5OQ==".getBytes()));
		System.out.println(decode);
		
	}
	@Test
	public void encode_Decode()
	{
		
		 String encode = new String(Base64.getEncoder().encode("sankar:girija3770123".getBytes()));
		  System.out.println(encode);
		
		  
		  String decode=new String(Base64.getDecoder().decode(encode.getBytes()));
		  System.out.println(decode);
	}
	
}

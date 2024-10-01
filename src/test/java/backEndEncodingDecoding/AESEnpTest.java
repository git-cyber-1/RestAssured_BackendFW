package backEndEncodingDecoding;

import org.testng.annotations.Test;

public class AESEnpTest {

	@Test
	public void SampleTest()throws Exception {
	
	String privateKey="Ac03tEam@j!tu_#1";
	String data="{\"name\":\"sankar\",\"id\":\"tp-01\"}";

	EncrypAndDecryptUtility ed=new EncrypAndDecryptUtility();
	    String encrypt = ed.encrypt(data, privateKey);
	    System.out.println(ed.decrypt(encrypt, privateKey));
	
	
	}
}

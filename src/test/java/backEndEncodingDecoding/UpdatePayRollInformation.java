package backEndEncodingDecoding;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UpdatePayRollInformation {
/*
 * using AES Encryption technique
 */
	
	@Test
	public void payrollUpdate() throws Exception
	{
		
		
		EncrypAndDecryptUtility ed=new EncrypAndDecryptUtility();
		
      //got this json body from swagger document		
	  String jBody="{ \"employee\": { \"empId\":\"NH_00623\", \"designation\": \"Automation Engineer\", \"dob\": \"dd/MM/yyyy\", \"email\": \"whosshivansh1964@gmail.com\", \"empName\": \"Shivansh kaspadi_765\",\r\n"
	  		+ " \"experience\": 4.7, \"mobileNo\": \"9975144558\", \"project\": \"NH_PROJ_1002\", \"role\": \"string\", \"username\": “kaspadi_1870” },\r\n"
	  		+ " \"basicPlusVda\": 50000, \"hra\": 0, \"insurance\": 0, \"lta\": 0, \"lwf\": 0, \"netPay\": 26000, \"payroll_id\": 0, \"pf\": 0, \"pt\": 0, \r\n"
	  		+ "\"stat_bonus\": 0, \"status\": \"Active\" }";
		String jReqBody = ed.encrypt(jBody,"Ac03tEam@j!tu_#1");
		System.out.println(jReqBody);
		
		Response resp = given()
	   .body(jReqBody)
	   .when()
	   .put("http://49.249.28.218:8091/payroll");
	  resp .then()
	   .log().all();
		
		String respBody=ed.decrypt(resp.getBody().asString(),"Ac03tEam@j!tu_#1");
	    System.out.println(respBody);
	}
	
}

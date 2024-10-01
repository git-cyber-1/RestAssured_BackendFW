package mocking_Spark;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreditCardPaymentTest {

	@Test
	public void creditcard_Test() {
		
	JSONObject jobj=new JSONObject();
	jobj.put("creditcard","1234567891234");
	jobj.put("cvv","123");
	jobj.put("cardname","sankar");
		
		given()
		 .contentType(ContentType.JSON)
		 .body(jobj)
		.when()
		 .post("http://localhost:8889/credit-card")
		.then()
		 .log().all();
		
		
	}
	
	
}

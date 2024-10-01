package backEndEncodingDecoding;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;



public class Api_EncodeTest {

   @Test
   public void api_EncodeTest() {
	   
	    given()
	    .auth().digest("rmgyantra","rmgy@9999")
	    .log().all()
	   . when()
	    .get("http://49.249.28.218:8091/projects")
	    .then()
	    .log().all();
	   
	   
	   
	   
   }
	
}

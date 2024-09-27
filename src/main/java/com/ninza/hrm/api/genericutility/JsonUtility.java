package com.ninza.hrm.api.genericutility;

import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;





public class JsonUtility {
   
	FileUtility flib=new FileUtility();
	
	/**
	 * get the Jsondata from based in json complex xpath
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public String getDataOnJsonPath(Response resp,String jsonXpath) {
	List<Object> list=JsonPath.read(resp.asString(), jsonXpath);
	return list.get(0).toString();
		}
	/**
	 * get xml data from based on xml complex path
	 * @param resp
	 * @param xmplpath
	 * @return
	 */
	public String getDataOnxpathpath(Response resp,String xmlpath) {
		
		
		return resp.xmlPath().get(xmlpath);
		
	}
	
	/**
	 * verify the data in jsonbody based jsonpath
	 * @param resp
	 * @param jsonXpath
	 * @param expectedData
	 * @return
	 */
	
	
	public boolean verifyDataJsonPath(Response resp,String jsonXpath,String expectedData){
		
		List<String> list=JsonPath.read(resp.asString(),jsonXpath);
		boolean flag=false;
		for(String str:list) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData+"is available ====PASS");
				flag=true;
			}
		
	if(flag==false) {
		System.out.println(expectedData+"is not available ====fail");
	}
		
		}
		
		
		return flag;
		
		
	}
	
    public String getAcessToken() throws IOException {
    	  Response resp = given()
    			  .formParam("client_id",flib.getdatafromproperties("client_id"))
    			  .formParam("client_secret",flib.getdatafromproperties("client_secret"))
    			  .formParam("grant_type",flib.getdatafromproperties("grant_type"))
    			 .when()
    			   .post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
    			  resp.then()
    			      .log().all();
    			  
    			  //capture data from the response
    			  String token=resp.jsonPath().getString("access_token");
    			  return token;
    	
    
    	
    	
    	
    }
}
	
  

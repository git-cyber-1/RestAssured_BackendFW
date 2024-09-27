package com.ninza.hrm.api.projectTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.BaseClass.BaseClassApi;
import com.ninza.hrm.api.pojoclass.ActProject;
import com.ninza.hrm.constants.endpoints.IEndpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ProjectTest extends BaseClassApi {
	ActProject aproj;
	@Test
	public void addSingleProjectWithCreated() throws SQLException {
		//create an object for pojo class
		Random ran=new Random();
		int rannum=ran.nextInt(5000);
		 aproj=new ActProject("NiggaBro","NinzaHrm_"+rannum, "Created", 0);
		
		String expmsg="Successfully Added";
		String projectName="NinzaHrm_"+rannum;
		//verify project in API lAYER
		Response resp = given()
		  .contentType(ContentType.JSON)
		  .body(aproj)
		.when()
		   .post("http://49.249.28.218:8091/addProject");
		resp .then()
		  .assertThat().statusCode(201)
		  .assertThat().time(Matchers.lessThan(3000l))
		  .assertThat().contentType(ContentType.JSON)
		  .log().all();
	//String projectName=resp.jsonPath().get("projectName"); 
	String actmsg=resp.jsonPath().get("msg");
	Assert.assertEquals(expmsg, actmsg);
		
	//verify project in DB Layer
	boolean flag=false;
	   Driver dref=new Driver();
		DriverManager.registerDriver(dref);
		Connection con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root@%","root");
		      Statement stat = con.createStatement();
		      ResultSet result = stat.executeQuery("select * from project");
		
		      while(result.next()) {
		    	  if (result.getString(4).equals(projectName));
		    	  flag=true;
		    	  break;
		    	 
		      }
		con.close();
		Assert.assertTrue(flag,"Project in DB is not verified");
		
	}
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void addDuplicateProject() {
		
		given()
		 .contentType(ContentType.JSON)
		 .body(aproj)
		 .when()
		 .post("http://49.249.28.218:8091"+IEndpoints.ADD_PROJ)
		.then()
		.assertThat().statusCode(409)
		.log().all();
		
	}
	
	
	

}

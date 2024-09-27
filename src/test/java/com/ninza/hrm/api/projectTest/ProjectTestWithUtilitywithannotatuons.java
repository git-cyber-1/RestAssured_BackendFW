package com.ninza.hrm.api.projectTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.BaseClass.BaseClassApi;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ActProject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ProjectTestWithUtilitywithannotatuons extends BaseClassApi {
	ActProject aproj;
	JavaUtility jlib=new JavaUtility();
	FileUtility flib=new FileUtility();
	DataBaseUtility dlib=new DataBaseUtility();
	
	@Test
	public void addSingleProjectWithCreated() throws Throwable {
		//create an object for pojo class
		         String baseuri = flib.getdatafromproperties("BASEURI");
		 aproj=new ActProject("NiggaBro","NinzaHrm_"+jlib.getRandomNumber(), "Created", 0);
		
		String expmsg="Successfully Added";
		String projectName="NinzaHrm_"+jlib.getRandomNumber();
		//verify project in API lAYER
		Response resp = given()
		  .contentType(ContentType.JSON)
		  .body(aproj)
		.when()
		   .post(baseuri+"/addProject");
		resp .then()
		  .assertThat().statusCode(201)
		  .assertThat().time(Matchers.lessThan(3000l))
		  .assertThat().contentType(ContentType.JSON)
		  .log().all();
	//String projectName=resp.jsonPath().get("projectName"); 
	String actmsg=resp.jsonPath().get("msg");
	Assert.assertEquals(expmsg, actmsg);
		
	//verify project in DB Layer
	     
	   boolean flag = dlib.executeQueryverifyAndGetData("SELECT * from project",4,projectName);
	
		Assert.assertTrue(flag,"Project in DB is not verified");
		
	}
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void addDuplicateProject() throws IOException {
		
		String baseuri = flib.getdatafromproperties("BASEURI");
		
		given()
		 .contentType(ContentType.JSON)
		 .body(aproj)
		 .when()
		 .post(baseuri+"/addProject")
		.then()
		.assertThat().statusCode(409)
		.log().all();
		
	}
	
	
	

}

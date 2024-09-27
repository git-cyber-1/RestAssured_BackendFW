package com.ninza.hrm.api.EmployeeTest;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.api.pojoclass.ActProject;
import com.ninza.hrm.api.pojoclass.EmployeePojo;

import io.restassured.http.ContentType;


public class EmployeeTestWithOutEmail {
	ActProject aproj;
	EmployeePojo empObj;
	@Test
	public void addEmploye() throws SQLException {
		
		Random ran=new Random();
		int rannum=ran.nextInt(5000);
		 aproj=new ActProject("NiggaBro","NinzaHrm_"+rannum, "Created", 0);
		
		String expmsg="Successfully Added";
		String projectName="NinzaHrm_"+rannum;
		String userName="NiggaBro_"+rannum;
		//verify project in API lAYER
		given()
		  .contentType(ContentType.JSON)
		  .body(aproj)
		.when()
		   .post("http://49.249.28.218:8091/addProject")
		.then()
		  .assertThat().statusCode(201)
		  .assertThat().time(Matchers.lessThan(3000l))
		  .assertThat().contentType(ContentType.JSON)
		  .log().all();

		
	empObj =new EmployeePojo("AEE", "22/11/1997", "",userName, 5.3,"9938891607", projectName, "PRODUCTION ENGINEER", userName);
	
	given().contentType(ContentType.JSON)
	.body(empObj)
	.when()
	.post("http://49.249.28.218:8091/employees")
	.then()
	.assertThat().contentType(ContentType.JSON)
	.assertThat().statusCode(500)
	.log().all();
	
	
	
	
	

	}
	
	
	

}

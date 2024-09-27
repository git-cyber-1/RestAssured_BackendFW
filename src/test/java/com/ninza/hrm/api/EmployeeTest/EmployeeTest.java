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
import com.ninza.hrm.BaseClass.BaseClassApi;
import com.ninza.hrm.api.pojoclass.ActProject;
import com.ninza.hrm.api.pojoclass.EmployeePojo;
import com.ninza.hrm.constants.endpoints.IEndpoints;

import io.restassured.http.ContentType;


public class EmployeeTest extends BaseClassApi{
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
		  .spec(specReqobj)
		  .body(aproj)
		.when()
		   .post("http://49.249.28.218:8091"+IEndpoints.ADD_PROJ)
		.then()
		  .assertThat().statusCode(201)
		  .assertThat().time(Matchers.lessThan(3000l))
		  .spec(SpecResObject)
		  .log().all();

		
	empObj =new EmployeePojo("AEE", "22/11/1997", "abc@gmail.com",userName, 5.3,"9938891607", projectName, "PRODUCTION ENGINEER", userName);
	
	given()
	.spec(specReqobj)
	.body(empObj)
	.when()
	.post("http://49.249.28.218:8091"+IEndpoints.ADDEmp)
	.then()
	.spec(SpecResObject)
	.assertThat().statusCode(201)
	.and()
	.time(Matchers.lessThan(3000l))
	.log().all();
	
	
	
	
	
		
	boolean flag=false;
	   Driver dref=new Driver();
		DriverManager.registerDriver(dref);
		Connection con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
		      Statement stat = con.createStatement();
		      ResultSet result = stat.executeQuery("select * from employee");
		
		      while(result.next()) {
		    	  if (result.getString(5).equals(userName));
		    	  flag=true;
		    	  break;
		    	 
		      }
		con.close();
		Assert.assertTrue(flag,"Employee in DB is not verified");
		
	}
	
	
	

}

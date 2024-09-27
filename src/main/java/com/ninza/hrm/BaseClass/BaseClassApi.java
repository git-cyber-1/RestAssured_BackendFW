package com.ninza.hrm.BaseClass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClassApi {
	public static RequestSpecification specReqobj;
	public static ResponseSpecification SpecResObject;
	public JavaUtility jlib= new JavaUtility() ;
	public FileUtility flib=new FileUtility();
	public DataBaseUtility dlib=new DataBaseUtility();
	
	@BeforeSuite
	public void configBS() throws Throwable {
		
		dlib.getDBconnection();
		System.out.println("=============DB CONNECTION DONE=====================");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.setAuth(basic("username","password"));
		builder.addHeader("","");
		builder.setBaseUri(flib.getdatafromproperties("BASEURI"));
		specReqobj=builder.build();
		
		ResponseSpecBuilder resbuilder=new ResponseSpecBuilder();
		resbuilder.expectContentType(ContentType.JSON);
	    SpecResObject = resbuilder.build();
		
	}
	@AfterSuite
	public void configAS() throws Throwable {
		dlib.closeDBconnection();
		System.out.println("================DB CONNECTION CLOSED======================");
	}
	
	
}

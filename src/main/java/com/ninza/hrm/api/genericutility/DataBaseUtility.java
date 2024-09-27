package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * 
 * @author girija Sankar
 */

public class DataBaseUtility {
	public 	static Connection con = null;
	public	static ResultSet result=null;
	public  static FileUtility flib=new FileUtility();
		
	public static void getDBconnection() throws SQLException {
			
		Driver dref;
		try {
			   dref = new Driver();
				DriverManager.registerDriver(dref);
				con=DriverManager.getConnection(flib.getdatafromproperties("DBURL"),
						flib.getdatafromproperties("DB_USERNAME"),
						flib.getdatafromproperties("DB_PASSWORD"));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
       
		/**
		 * close the db connection
		 * @throws SQLException
		 */
		public void closeDBconnection() throws SQLException {
			
			try {
			con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
          /**
           * 
           * @param query
           * @return
           * @throws SQLException
           */
		public static ResultSet executeSelectQuery(String query) throws SQLException {
			try {
				result = con.createStatement().executeQuery(query);
			   
			} catch (Exception e) {
            e.printStackTrace();    
			}finally {
				
			}
			return result;
		}
		/**
		 * 
		 * @param query
		 * @return
		 * @throws Throwable 
		 */
		public boolean executeQueryverifyAndGetData(String query,int colIndex,String expectedData) throws Throwable {
		        boolean flag=false;
		        result = con.createStatement().executeQuery(query);
			   while(result.next()) {
				   if(result.getString(colIndex).equals(expectedData)) {
					   
					   flag=true;
					   break;
				   }
			   }
			   if (flag==true) {
				   System.out.println(expectedData+" data verified in data base table");
				   return true;
			   }
			   else {
				   System.out.println(expectedData+" data not verified in data base table");
				   return false;
			   }
			  
	}

		}

	
	
	
	


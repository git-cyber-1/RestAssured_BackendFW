package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	
	public String getdatafromproperties(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./config-environment/config_env.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data = pobj.getProperty(key);
         return data;
	}

	

}

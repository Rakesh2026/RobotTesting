package com.miko.genericUtils;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * This class used to read the data from property file
 * @author Rakesh
 */
public class PropertyFileUtil {

	/**
     * This method return the value associated with key in property file and all the key value are defined under folder Test data
     * with file name TestData.properties
     * @param key
     * @return value
     */
public static String getPropertiesData(String key) {
	
	 try {
         FileInputStream file = new FileInputStream("./src/main/resources/com.miko.testdata/CommonData.properties");
         Properties properties = new Properties();
         properties.load(file);
         return properties.getProperty(key);
     } catch (Exception e) {
         e.printStackTrace();
     }
     return "No Such Key in property file: " + key;
 }

/**
 * This method return the value associated with key in property file and all the key value are defined under folder Test data
 * with file name TestData.properties
 * @param FilePath
 * @param key
 * @return value
 */
public static String getPropertiesData(String path,String key) {

 try {
     FileInputStream file = new FileInputStream(path);
     Properties properties = new Properties();
     properties.load(file);
     return properties.getProperty(key);
 } catch (Exception e) {
     e.printStackTrace();
 }
 return "No Such Key in property file: " + key;
}

}

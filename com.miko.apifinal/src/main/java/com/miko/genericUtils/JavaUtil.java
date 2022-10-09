package com.miko.genericUtils;

import java.util.Random;

/**
 * This class contains generic methods related to java 
 * like generating random numbers .
 * @author Rakesh
 *
 */
public class JavaUtil {
	
	/**
	 * This method will generate the random number and
	 * returns it to the caller for every execution
	 * @return
	 */
	public int getRandomNumber(int num)
	{
		Random ran = new Random();
		return ran.nextInt(num);
	}

}

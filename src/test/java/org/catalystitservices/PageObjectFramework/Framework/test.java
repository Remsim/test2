package org.catalystitservices.PageObjectFramework.Framework;

import junit.framework.Assert;

import org.junit.Test;

public class test extends PageObjectTest {

	
	@Test
	public void test(){
	//	Settings set = new Settings();
		
	//	System.out.println(set.getScreenShotDir());
	
	SeleniumDriver.setDriver();
	Assert.assertEquals(1, 2);
	}
}

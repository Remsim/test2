package org.catalystitservices.PageObjectFramework.Framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDriver {
	
	protected static WebDriver _driver;
	private Settings setting;

	public static void setDriver()
	{
		try{
			_driver = new RemoteWebDriver(new URL(Settings.getAppiumServer()), constructCap());
			_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}catch(MalformedURLException e){
			Assert.fail("Invalid Appium Server");
		}
	}
	
	public static WebDriver getDrivr(){
		return _driver;
	}

	
	/**
	 * Returns a capability object from application information stored in Settings.java.    
	 * @return
	 */
	private static DesiredCapabilities constructCap(){
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");;
		cap.setCapability("deviceName","70fe59cd");
		//cap.setCapability("app",Settings.getApp());
		//cap.setCapability("app-package",Settings.getAppPkg());
		//cap.setCapability("app-activity", Settings.getAppActivity());
		return cap;
	}

	
}

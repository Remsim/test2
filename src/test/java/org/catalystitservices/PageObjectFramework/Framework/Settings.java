package org.catalystitservices.PageObjectFramework.Framework;


public class Settings {
	

	private static int _defaultTimeout = 10000;
	private static String _logDirectory = "Logs/";
	private static String _seleniumLog = "SeleniumLog";
	private static String _actionLog = "Actions";
	private static String _screenShotDir = _logDirectory + "Screenshots";
	private static boolean _logAllActions = true;
	
	
	private static String _appiumServer = "http://localhost:4723/wd/hub";
	private static String _app = "users/rmann/desktop/base.apk";
	private static String _appPkg;
	private static String _appActivty;
	
	
	
	
	public void setAppiumServer(String server){
		_appiumServer = server;
	}
	
	public void setApp(String app){
		_app = app;
	}
	
	public void setAppPkg(String pkg){
		_appPkg = pkg;
	}
	
	public void setAppActivity(String activity){
		_appActivty = activity;
	}
	
	public static String getAppiumServer(){
		return _appiumServer;
	}
	
	public static String getApp(){
		return _app;
	}
	
	public static String getAppPkg(){
		return _appPkg;
	}
	
	public static String getAppActivity(){
		return _appActivty;
	}
	
	public static String getScreenShotDir(){
		return _screenShotDir;
	}
	
    public static String getActionLogName()
    {
    	return _actionLog;
    }
	
    public static int getDefaultTimeout()
    {
    	return _defaultTimeout;
    }
    
    public static String getLogDirectory()
    {
    	return _logDirectory;
    }
    
    public static String getSeleniumLogName()
    {
    	return _seleniumLog;
    }
    
    public static boolean logAllActions()
    {
    	return _logAllActions;
    }
}

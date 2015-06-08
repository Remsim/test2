package org.catalystitservices.PageObjectFramework.Framework;

import junit.framework.TestResult;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PageObjectTest extends SeleniumDriver {
	
	private static String _logName = Settings.getSeleniumLogName();
	private static SeleniumLogger _logger = SeleniumLogger.getLogger(_logName);
	
	private static long _suiteStartTime;
	private static long _testStartTime;
	protected String result;
	
	@Rule public TestName name = new TestName();

	@BeforeClass
	public static void BeforeClass()
	{
		_logger.logStartTestSuite();
		_suiteStartTime = System.currentTimeMillis();
	}
	
	@Before
	public void BeforeEach()
	{
		
		SeleniumDriver.setDriver();
		_logger.logStartTest(name.getMethodName());
		_testStartTime = System.currentTimeMillis();
	}
	
	@After
	public void AfterEach()
	{
		
	}
	
	@Rule public TestWatcher watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			_logger.logFail(name.getMethodName());
			SeleniumLogger.screenShot(name.getMethodName());
		}
		
		@Override
		protected void succeeded(Description description) {
			_logger.logPass(name.getMethodName());
		}
		
		@Override
		protected void finished(Description description) {
			double elapsedTime = ((double)(System.currentTimeMillis() - _testStartTime))/1000;
			_logger.logTime("Elapsed Time", elapsedTime);
			_driver.quit();
		}
	};
	
	@AfterClass
	public static void AfterClass()
	{
		_logger.logFinishTestSuite();
		double totalTime = ((double)(System.currentTimeMillis() - _suiteStartTime))/1000;
		_logger.logTime("Total Time", totalTime);
		_logger.logDashedLine();
	}
}

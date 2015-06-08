package org.catalystitservices.PageObjectFramework.Framework;

import java.util.Collection;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/// <summary>
/// The Model Base for all Page Objects.
/// <para> </para>
/// <para>Any class implementing PageObjectModelBase should have a constructor like so:</para>
/// <para>public PageObjectNameGoesHere(IWebDriver driver) : base(driver)</para>
/// <para>{</para>
/// <para>    // Everything in here is optional</para>
/// <para>    _url = "https://www.google.com";</para>
/// <para>    _title = "Google";</para>
/// <para>    GoTo(_url, _title);</para>
/// <para>}</para>
/// </summary>
public class PageObject {
	
    // Driver and Page-specific stuff
	protected WebDriver _driver;
	protected String _url;
	protected String _title;
	
	// Config stuff
	private boolean _logActions = Settings.logAllActions();
	private String _actionLog = Settings.getActionLogName();
	
	private int _defaultTimeout = Settings.getDefaultTimeout();
	
	private SeleniumLogger _logger;
	
	public PageObject(WebDriver driver)
	{
		_driver = driver;
		
		if(_logActions)
		{
			_logger = SeleniumLogger.getLogger(_actionLog);
		}
	}
	
	// Shared XPaths
	protected static By body = By.xpath("//body");
	protected static By title = By.xpath("//title");


	
	/**
	 * Click the element at the given selector.
	 * @param by - the by selector for the given element
	 */
	protected void click(By by)
	{
        if (_logActions)
        {
            _logger.logMessage(String.format("Click: %s", by));
        }
		find(by).click();
	}

	/**
	 * Finds the element by the given selector
	 * @param by - the by selector for the given element.
	 * @return WebElement - the element found at the given by.
	 */
	protected WebElement find(By by)
	{
		long startTime = System.currentTimeMillis();
		
		while(_driver.findElements(by).size() == 0)
		{
			if (System.currentTimeMillis() - startTime > _defaultTimeout)
			{
                String errMsg = String.format(
						"Could not find element %s after %d seconds.",
						by,
						_defaultTimeout);
				throw new NoSuchElementException(errMsg);
			}
		}
		return _driver.findElement(by);
	}

	/**
	 * Finds all elements by the given selector.
	 * @param by - the by selector for the given element.
	 * @return Collection<WebElement> - a list of all the elements found at the given by.
	 */
	protected Collection<WebElement> findAll(By by)
	{
		return _driver.findElements(by);
	}

	/**
	 * Gets the text from the given locator.
	 * @param by - the by selector for the given element.
	 * @return String - the text at the given by.
	 */
	protected String getText(By by)
	{
		return find(by).getText();
	}


	/**
	 * Inputs data into an input box
	 * @param by - the by selector for the given element
	 * @param value - the text to input into the input box.
	 */
	protected void sendKeys(By by, String value)
	{
        if (_logActions)
        {
            _logger.logMessage(String.format("SndKy: %s", value));
            _logger.logMessage(String.format("   to: %s", by));
        }
		find(by).sendKeys(value);
	}

	/**
	 * Selects an option from a select box based on text
	 * @param by - the by selector for the given element
	 * @param optionText - the text to select by
	 * @throws InvalidSelectOptionException 
	 */
	protected void selectByText(By by, String optionText)
	{
        if (_logActions)
        {
            _logger.logMessage(String.format("Selct: %s", optionText));
            _logger.logMessage(String.format("   at: %s", by));
        }
		Select select = new Select(find(by));
		if (!select.equals(null))
		{
			try
			{
				select.selectByVisibleText(optionText);
			}
			catch (Exception ex)
			{
				String errMsg = String.format(
						"PageObject: There is no option '%s' in '%s'.",
						optionText, by);
				Assert.fail(errMsg);
			}
		}
		else
		{
			String errMsg = "Cannot find element " + by.toString();
			throw new NoSuchElementException(errMsg);
		}
	}


	/**
	 * Pauses play until a given element is no longer on the DOM.
	 * @param by - the by selector for the given element
	 */
	protected void waitForElementToBeDeleted(By by)
	{
		waitForElementToBeDeleted(by, _defaultTimeout);
	}

	/**
	 * Pauses play until a given element is no longer on the DOM.
	 * @param by - the by selector for the given element
	 * @param timeout (optional) - the time, in milliseconds, to wait for the element to be deleted.
	 * If no time is given for the timeout, will use the default timeout.
	 */
	protected void waitForElementToBeDeleted(By by, long timeout)
	{
		long startTime = System.currentTimeMillis();
		
		while(findAll(by).size() > 0)
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
				Assert.fail(String.format("Element '%s' was still visible after %s seconds",
						by.toString(),
						timeout / 1000));
			}
		}
	}
}


import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class LoginCarlookTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void loginCarlook() {
    // Test name: LoginCarlook
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("http://localhost:8080/");
    // 2 | setWindowSize | 1900x1020 | 
    driver.manage().window().setSize(new Dimension(1900, 1020));
    // 3 | click | id=gwt-uid-3 | 
    driver.findElement(By.id("gwt-uid-3")).click();
    // 4 | type | id=gwt-uid-3 | test@web.de
    driver.findElement(By.id("gwt-uid-3")).sendKeys("test@web.de");
    // 5 | type | id=gwt-uid-5 | Test1234
    driver.findElement(By.id("gwt-uid-5")).sendKeys("Test1234");
    // 6 | click | css=.v-slot:nth-child(7) > .v-button | 
    driver.findElement(By.cssSelector(".v-slot:nth-child(7) > .v-button")).click();
    // 7 | click | css=.v-verticallayout:nth-child(2) | 
    driver.findElement(By.cssSelector(".v-verticallayout:nth-child(2)")).click();
    // 8 | click | css=.v-menubar-menuitem | 
    driver.findElement(By.cssSelector(".v-menubar-menuitem")).click();
    // 9 | click | css=.v-menubar-menuitem-caption:nth-child(1) | 
    driver.findElement(By.cssSelector(".v-menubar-menuitem-caption:nth-child(1)")).click();
  }
}

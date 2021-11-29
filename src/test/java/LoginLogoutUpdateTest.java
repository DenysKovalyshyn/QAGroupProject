import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginLogoutUpdateTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/login");
        driver.manage().window().setSize(new Dimension(1024, 750));
        //driver.manage().window().maximize();

    }

    @Test
    public void Login() throws InterruptedException {
        //define elements
        WebElement LoginName = driver.findElement(By.id("txtUsername"));
        WebElement Password = driver.findElement(By.id("txtPassword"));
        WebElement SubmitButton = driver.findElement(By.id("btnLogin"));

        // login
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        LoginName.sendKeys("Admin");
        Password.sendKeys("admin123");
        SubmitButton.click();

        // checks
        WebElement PageElement = driver.findElement(By.id("MP_link"));
        Assert.assertEquals(PageElement.getAttribute("value"),"Marketplace");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        Thread.sleep(2000);

        // return to the previous page
        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        Thread.sleep(2000);

        // refreshing the page
        driver.navigate().refresh();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        WebElement LoginElement = driver.findElement(By.id("txtUsername"));
        Assert.assertEquals(LoginElement.getText(),"Username");
    }


    @AfterMethod
    public void teardown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }

}
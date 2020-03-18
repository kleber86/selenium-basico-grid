import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaGoogle {

    @Test
    public void testeFirefox(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testeChrome(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaGoogle {

    @Test
    public void testeFirefox(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testeChrome(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}

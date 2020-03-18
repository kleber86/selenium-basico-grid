import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaGoogle {

    @Test
    public void teste(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());
    }
}

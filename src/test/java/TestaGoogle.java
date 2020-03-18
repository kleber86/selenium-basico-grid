import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaGoogle {

    public static void main(String[] args){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com.br");
        System.out.println(driver.getTitle());
    }
}

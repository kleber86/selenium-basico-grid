import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    @Test
    public void deveinteragirComAlertSimples() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///c:/QA/treinamento/componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        Assert.assertEquals("Alert Simples", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
    }
}

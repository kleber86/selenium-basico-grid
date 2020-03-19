import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    private WebDriver driver;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveinteragirComAlertSimples() {
        driver.findElement(By.id("alert")).click();
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        Assert.assertEquals("Alert Simples", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
    }

    @Test
    public void deveinteragirComAlertComConfirmacao() {

        driver.findElement(By.id("confirm")).click();
        Alert confirma = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", confirma.getText());
        confirma.accept();
        Assert.assertEquals("Confirmado", confirma.getText());
        confirma.accept();

        driver.findElement(By.id("confirm")).click();
        confirma = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", confirma.getText());
        confirma.dismiss();
        Assert.assertEquals("Negado", confirma.getText());
        confirma.dismiss();
    }
    @Test
    public void deveinteragirComAlertPrompt() {

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("12");
        alerta.accept();
        Assert.assertEquals("Era 12?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
    }
}

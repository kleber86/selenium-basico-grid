import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TesteSincronismo {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("file:///home/kleber/treinamento/componentes.html");

        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    //@After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Deu certo?");
    }


}

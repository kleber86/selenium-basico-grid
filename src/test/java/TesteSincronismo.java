import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void deveInteragirComRespostaDemorada() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Deu certo?");
    }
}

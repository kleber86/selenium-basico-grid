import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestePrime {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveInteragirComRadioPrime(){
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));
        Assert.assertTrue(dsl.isCheckMarcado("j_idt721:console:0"));

        dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
        Assert.assertTrue(dsl.isCheckMarcado("j_idt721:console:1"));
    }

    @Test
    public void deveInteragirComSelectPrime(){
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");

        dsl.selecionarComboPrime("j_idt721:console", "Xbox One");

        Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt721:console_label"));
    }


}

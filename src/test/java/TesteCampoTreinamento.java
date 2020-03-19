import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {
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
    public void test() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        Assert.assertEquals("Kleber", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComTextArea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Aprendendo Automação\ncom Java!\n");
    }

    @Test
    public void deveInteragirComRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComCheckbox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
    }

    @Test
    public void deveInteragirComCombo() {
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        //combo.selectByIndex(3);
        //combo.selectByValue("especializacao");
        combo.selectByVisibleText("2o grau completo");

        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void deveVerificarValoresCombo() {
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {

        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> todasAsOpcoes = combo.getAllSelectedOptions();
        Assert.assertEquals(3, todasAsOpcoes.size());
    }

    @Test
    public void deveInteragirComBotao() {
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        driver.findElement(By.linkText("Voltar")).click();
        WebElement voltou = driver.findElement(By.id("resultado"));

        Assert.assertEquals("Voltou!", voltou.getText());
    }

    @Test
    public void deveBuscarTextoNaPagina() {
        //Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
    }

}

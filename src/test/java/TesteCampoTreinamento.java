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
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void testTextField() {
        dsl.escreve("elementosForm:nome", "Kleber");
        Assert.assertEquals("Kleber", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escreve("elementosForm:sugestoes", "Aprendendo Automação\ncom Java!\n");
        Assert.assertEquals("Aprendendo Automação\ncom Java!\n", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComCheckbox() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        dsl.isRadioMarcado("elementosForm:comidaFavorita:2");

        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo",dsl.obterValorCombo("elementosForm:escolaridade"));
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

        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);

        List<WebElement> todasAsOpcoes = combo.getAllSelectedOptions();
        Assert.assertEquals(3, todasAsOpcoes.size());
    }

    @Test
    public void deveInteragirComBotao() {
        dsl.clicarBotao("buttonSimple");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clicarNoLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextoNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }

}

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

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
    public void testeCadastroDesafio() {
        dsl.escreve("elementosForm:nome", "Kleber");
        dsl.escreve("elementosForm:sobrenome", "Nascimento");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.clicarBotao("elementosForm:cadastrar");


        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Kleber"));
        Assert.assertEquals("Sobrenome: Nascimento", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportes() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}


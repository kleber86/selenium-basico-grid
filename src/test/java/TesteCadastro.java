import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
    @Test
    public void testeCadastroDesafio() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");

        driver.findElement(By.id("elementosForm:sexo:0")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        WebElement opcao = driver.findElement(By.id("elementosForm:escolaridade"));
        Select opSelect = new Select(opcao);
        opSelect.selectByVisibleText("Superior");

        WebElement comboMult = driver.findElement(By.id("elementosForm:esportes"));
        Select opSelectMult = new Select(comboMult);
        opSelectMult.selectByVisibleText("Futebol");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Kleber"));
        Assert.assertEquals("Sobrenome: Nascimento", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Futebol", driver.findElement(By.id("descEsportes")).getText());

        driver.quit();
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarComidaVegetariana() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nascimento");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarEsportes() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");

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
        driver.quit();
    }
}


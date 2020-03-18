import org.junit.Assert;
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

    @Test
    public void test(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("C:\\QA\\treinamento\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Kleber");
        Assert.assertEquals("Kleber", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("C:\\QA\\treinamento\\componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Aprendendo Automação\ncom Java!\n");
        driver.quit();
    }
    @Test
    public void deveInteragirComRadioButton(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("C:\\QA\\treinamento\\componentes.html");

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComCheckbox(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("C:\\QA\\treinamento\\componentes.html");

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComCombo(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500,800));
        driver.get("file:///c:/QA/treinamento/componentes.html");

        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        //combo.selectByIndex(3);
        //combo.selectByValue("especializacao");
        combo.selectByVisibleText("2o grau completo");

        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void deveVerificarValoresCombo(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500,800));
        driver.get("file:///c:/QA/treinamento/componentes.html");

        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options){
            if(option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
        driver.quit();
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1500, 800));
        driver.get("file:///c:/QA/treinamento/componentes.html");

        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> todasAsOpcoes = combo.getAllSelectedOptions();
        Assert.assertEquals(3, todasAsOpcoes.size());
        driver.quit();
    }
}

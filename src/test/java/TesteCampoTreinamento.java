import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void deveInteragirComCheckBox(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("C:\\QA\\treinamento\\componentes.html");

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());

    }
}

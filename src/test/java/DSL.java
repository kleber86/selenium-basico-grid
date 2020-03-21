import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto){
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_valor){
        return driver.findElement(By.id(id_valor)).getAttribute("value");
    }
    public void clicarRadio(String id){
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id){
        return driver.findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor){
        WebElement elemento = driver.findElement(By.id(id));
        Select combo = new Select(elemento);
        combo.selectByVisibleText(valor);
    }
    public String obterValorCombo(String id){
        WebElement elemento = driver.findElement(By.id(id));
        Select combo = new Select(elemento);
        return combo.getFirstSelectedOption().getText();
    }
    public void clicarBotao(String id){
        driver.findElement(By.id(id)).click();
    }
    public void clicarNoLink(String linkTexto){
        driver.findElement(By.linkText(linkTexto)).click();
    }

    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }
}

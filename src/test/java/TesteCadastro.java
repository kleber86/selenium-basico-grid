import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("file:///home/kleber/Documents/treinamento/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveRealizarCadastroComSucesso(){
        page.setNome("Kleber");
        page.setSobreNome("Nascimento");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsporte("Natacao");
        page.cadastrar();

        Assert.assertTrue(page.getResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.getNomeCadastro().endsWith("Kleber"));

        Assert.assertEquals("Sobrenome: Nascimento", page.getSobreNomeCadastro());
        Assert.assertEquals("Sexo: Masculino", page.getSexoCadastro());
        Assert.assertEquals("Comida: Pizza", page.getComidaCadastro());
        Assert.assertEquals("Escolaridade: mestrado", page.getEscolaridadeCadastro());
        Assert.assertEquals("Esportes: Natacao", page.getEsporteCadastro());
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        dsl.clicarBotao("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        page.setNome("Kleber");
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        page.setNome("Kleber");
        page.setSobreNome("Nascimento");
        page.cadastrar();

        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        page.setNome("Kleber");
        page.setSobreNome("Nascimento");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        page.setNome("Kleber");
        page.setSobreNome("Nascimento");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setEsporte("Karate", "O que eh esporte?");
        page.cadastrar();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}

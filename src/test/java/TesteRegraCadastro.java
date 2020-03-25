import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TesteRegraCadastro {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Parameterized.Parameter
    public String nomeInformado;
    @Parameterized.Parameter(value = 1)
    public String sobreNomeInformado;
    @Parameterized.Parameter(value = 2)
    public String sexoInformado;
    @Parameterized.Parameter(value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String msg;

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

    @Parameterized.Parameters
    public static Collection<Object[]> getColletion(){

        return Arrays.asList(new Object[][]{
                {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Kleber", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Kleber", "Nascimento", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                {"Kleber", "Nascimento", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Kleber", "Nascimento", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }
    @Test
    public void deveValidarEsportistaIndeciso(){

        page.setNome(nomeInformado);
        page.setSobreNome(sobreNomeInformado);

        if(sexoInformado.equals("Masculino")) {
            page.setSexoMasculino();
        }if(sexoInformado.equals("Feminino")){
            page.setSexoFeminino();
        }

        if(comidas.contains("Carne")) page.setComidaCarne();
        if(comidas.contains("Pizza")) page.setComidaPizza();
        if(comidas.contains("Vegetariano")) page.setComidaVegetariano();

        page.setEsporte(esportes);

        page.cadastrar();
        System.out.println(msg);
        Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
    }
}

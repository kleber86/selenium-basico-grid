import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }


    public void setNome(String nome){
        dsl.escrever("elementosForm:nome", nome);
    }
    public void setSobreNome(String sobreNome){
        dsl.escrever("elementosForm:sobrenome", sobreNome);
    }
    public void setSexoMasculino(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }
    public void setSexoFeminino(){
        dsl.clicarRadio("elementosForm:sexo:1");
    }
    public void setComidaCarne(){
        dsl.clicarCheck("elementosForm:comidaFavorita:0");
    }
    public void setComidaFrango(){
        dsl.clicarCheck("elementosForm:comidaFavorita:1");
    }
    public void setComidaPizza(){
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
    }
    public void setComidaVegetariano(){
        dsl.clicarCheck("elementosForm:comidaFavorita:3");
    }
    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }
    public void setEsporte(String... valores){
        for(String valor:valores) {
            dsl.selecionarCombo("elementosForm:esportes", valor);
        }
    }
    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }
    public String getResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }
    public String getNomeCadastro(){
        return dsl.obterTexto("descNome");
    }
    public String getSobreNomeCadastro(){
        return dsl.obterTexto("descSobrenome");
    }
    public String getSexoCadastro(){
        return dsl.obterTexto("descSexo");
    }
    public String getComidaCadastro(){
        return dsl.obterTexto("descComida");
    }
    public String getEscolaridadeCadastro(){
        return dsl.obterTexto("descEscolaridade");
    }
    public String getEsporteCadastro(){
        return dsl.obterTexto("descEsportes");
    }

}

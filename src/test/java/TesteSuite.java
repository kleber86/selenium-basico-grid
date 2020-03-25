import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegraCadastro.class,
        TesteCampoTreinamento.class
})
public class TesteSuite {
}

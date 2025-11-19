package src.Tests;
import org.junit.Test;
import src.ConfiguracaoAgenda;
public class ConfiguracaoAgendaTest {
    @Test
    public void testChecaDatasVigenciaValidas() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 1, 1));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 12, 31));
        assert(config.checaDatasVigenciaValidas());
    }

    @Test
    public void testChecaDatasVigenciaInvalidas() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 12, 31));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 1, 1));
        assert(!config.checaDatasVigenciaValidas());
    }

    @Test
    public void testIsDataEntreVigencia() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 1, 1));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 12, 31));
        assert(config.isDataEntreVigencia(java.time.LocalDate.of(2023, 6, 15)));
        assert(!config.isDataEntreVigencia(java.time.LocalDate.of(2024, 1, 1)));
    }
}
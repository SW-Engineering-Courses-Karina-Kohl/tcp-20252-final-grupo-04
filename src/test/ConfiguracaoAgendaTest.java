
package src.test;
import src.model.config.*;

import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class ConfiguracaoAgendaTest {
    @Test
    public void testChecaDatasVigenciaValidas() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 1, 1));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 12, 31));
        assert(config.checaDatasVigencia());
    }

    @Test
    public void testChecaDatasVigenciaInvalidas() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 12, 31));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 1, 1));
        assert(!config.checaDatasVigencia());
    }

    @Test
    public void testIsDataEntreVigencia() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();
        config.setDataInicioVigencia(java.time.LocalDate.of(2023, 1, 1));
        config.setDataFimVigencia(java.time.LocalDate.of(2023, 12, 31));
        assert(config.isDataEntreVigencia(java.time.LocalDate.of(2023, 6, 15)));
        assert(!config.isDataEntreVigencia(java.time.LocalDate.of(2024, 1, 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiaComDiaSemanaIncompatível() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda();

        // DiaSemana criado para TERÇA mas associado à chave de SEGUNDA
        DiaSemana diaTerça = new DiaSemana(DayOfWeek.TUESDAY);
        diaTerça.adicionarTimeSlot(LocalTime.of(8, 0));

        config.setDia(DayOfWeek.MONDAY, diaTerça);
    }
}

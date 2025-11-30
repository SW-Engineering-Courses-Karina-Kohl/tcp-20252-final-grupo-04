package src.test;
import src.model.config.*;
import java.time.LocalDate;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ImpedimentoTest {
    @Test
    public void testCriarImpedimento() {
        LocalDateTime dataHora = LocalDateTime.of(2023, 10, 1, 10, 0);
        Impedimento impedimento = new Impedimento(dataHora);
        assert(impedimento.getDataHora().equals(dataHora));
    }

    @Test
    public void testConflitaCom() {
        LocalDateTime dataHora = LocalDateTime.of(2023, 10, 1, 10, 0);
        Impedimento impedimento = new Impedimento(dataHora);
        assert(impedimento.conflitaCom(dataHora));
        assert(!impedimento.conflitaCom(LocalDateTime.of(2023, 10, 1, 12, 0)));
    }

    @Test
    public void testGetDataEHorario() {
        LocalDate data = LocalDate.of(2023, 10, 1);
        LocalTime horario = LocalTime.of(10, 0);
        Impedimento impedimento = new Impedimento();
        impedimento.setDataHora(LocalDateTime.of(data, horario));
        assert(impedimento.getData().equals(data));
        assert(impedimento.getHorario().equals(horario));
    }

    @Test
    public void testConflitaComNull() {
        LocalDateTime dataHora = LocalDateTime.of(2023, 10, 1, 10, 0);
        Impedimento impedimento = new Impedimento(dataHora);
        assert(!impedimento.conflitaCom(null));
    }
}

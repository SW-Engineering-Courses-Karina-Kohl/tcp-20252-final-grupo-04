package src.Tests;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import src.TimeSlotEstudo;
import src.Impedimento;
public class TimeSlotEstudoTest {
    @Test
    public void testConflitaComImpedimento() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        Impedimento impedimento = new Impedimento();
        LocalDateTime inicioImpedimento = LocalDateTime.of(2023, 10, 1, 10, 0);
        impedimento.setDataHora(inicioImpedimento);

        assert(timeSlotEstudo.conflitaComImpedimento(impedimento));
    }

    @Test
    public void testIsAtividadeValida() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        Atividade atividade = new Atividade();

        assert(!timeSlotEstudo.isAtividadeValida(atividade));
    }
}
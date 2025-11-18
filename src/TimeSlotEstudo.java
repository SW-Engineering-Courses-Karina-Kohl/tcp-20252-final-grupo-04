import org.junit.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

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

        impedimento.setDataHora(LocalDateTime.of(2023, 10, 1, 12, 0));
        assert(!timeSlotEstudo.conflitaComImpedimento(impedimento));
    }

    @Test
    public void testAtividadeInvalida() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        Prova prova = new Prova("Prova de Matemática", 1, LocalDateTime.of(2023, 10, 5, 9, 0));

        assert(!timeSlotEstudo.atividadeValida(prova));
    }
}

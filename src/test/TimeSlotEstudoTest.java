package src.test;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
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
    public void testAtividadeValida() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        Disciplina disciplina = new Disciplina("Matemática", 1.5);
        Prova prova = new Prova("Prova de Matemática", LocalDate.of(2023, 10, 5), disciplina);

        assert(!timeSlotEstudo.atividadeValida(prova));
    }
}
package src.test;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import org.junit.Test;
import static org.junit.Assert.*;
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

        assertTrue("TimeSlot deveria conflitar com impedimento no mesmo horário", 
                  timeSlotEstudo.conflitaComImpedimento(impedimento));

        // Teste quando não há conflito
        impedimento.setDataHora(LocalDateTime.of(2023, 10, 1, 13, 0));
        assertFalse("TimeSlot não deveria conflitar com impedimento em horário diferente", 
                   timeSlotEstudo.conflitaComImpedimento(impedimento));
    }

    @Test
    public void testAtividadeValida() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        // Teste com atividade null
        assertFalse("Atividade null deveria ser inválida", 
                   timeSlotEstudo.atividadeValida(null));

        // Teste com atividade válida
        Disciplina disciplina = new Disciplina("Matemática", 1.5);
        Prova prova = new Prova("Prova de Matemática", 1, LocalDateTime.of(2023, 10, 5, 9, 0));
        assertTrue("Atividade com data limite posterior deveria ser válida", 
                  timeSlotEstudo.atividadeValida(prova));
    }
}

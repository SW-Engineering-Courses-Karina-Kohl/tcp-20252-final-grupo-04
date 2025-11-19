package src.test;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class TimeSlotEstudoTest {
    
    public static void main(String[] args) {
        TimeSlotEstudoTest test = new TimeSlotEstudoTest();
        test.testConflitaComImpedimento();
        test.testAtividadeValida();
        System.out.println("Todos os testes passaram!");
    }
    
    public void testConflitaComImpedimento() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        Impedimento impedimento = new Impedimento();
        LocalDateTime inicioImpedimento = LocalDateTime.of(2023, 10, 1, 10, 0);
        impedimento.setDataHora(inicioImpedimento);

        assert timeSlotEstudo.conflitaComImpedimento(impedimento) : "Deveria conflitar com impedimento";

        // Teste quando não há conflito
        impedimento.setDataHora(LocalDateTime.of(2023, 10, 1, 13, 0));
        assert !timeSlotEstudo.conflitaComImpedimento(impedimento) : "Não deveria conflitar com impedimento";
        
        System.out.println("testConflitaComImpedimento - PASSOU");
    }

    public void testAtividadeValida() {
        LocalDate dataInicioEstudo = LocalDate.of(2023, 10, 1);
        LocalTime horaInicioEstudo = LocalTime.of(10, 0);
        TimeSlotEstudo timeSlotEstudo = new TimeSlotEstudo(dataInicioEstudo, horaInicioEstudo);

        // Teste com atividade null
        assert !timeSlotEstudo.atividadeValida(null) : "Atividade null deveria ser inválida";

        // Teste com atividade válida (usando uma implementação concreta)
        Prova prova = new Prova("Prova de Matemática", 1, LocalDateTime.of(2023, 10, 5, 9, 0));
        assert timeSlotEstudo.atividadeValida(prova) : "Prova válida deveria ser aceita";
        
        System.out.println("testAtividadeValida - PASSOU");
    }
}

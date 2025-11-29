package src.test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.controller.atividades.AtribuidorAtividades;
import src.controller.atividades.CalculadoraPesoAtividades;
import src.model.entities.AgendaEstudos;
import src.model.entities.Disciplina;
import src.model.entities.TimeSlotEstudo;
import src.model.atividades.Exercicio;
import src.model.atividades.Prova;
import src.model.atividades.Trabalho;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Testes para a classe AtribuidorAtividades.
 */
public class AtribuidorAtividadesTest {


    @Test(expected = IllegalArgumentException.class)
    public void testAtribuir_ComAgendaNula() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        AgendaEstudos agenda = null;
        List<Disciplina> disciplinas = Collections.emptyList();

        try {
            atribuidor.atribuir(agenda, disciplinas);
        } catch (IllegalArgumentException e) {
            assertEquals("Agenda não pode ser nula", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAtribuir_ComListaDisciplinasNula() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        AgendaEstudos agenda = new AgendaEstudos();
        List<Disciplina> disciplinas = null;

        try {
            atribuidor.atribuir(agenda, disciplinas);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de disciplinas não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAtribuir_ComListaDisciplinasVazia() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        AgendaEstudos agenda = new AgendaEstudos();
        List<Disciplina> disciplinas = Collections.emptyList();

        try {
            atribuidor.atribuir(agenda, disciplinas);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de disciplinas não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testQuantidadeTimeSlotEstudosAntesDe_DataIgualPrimeiroDiaAgenda() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        LocalDate base = LocalDate.of(2026, 1, 1);
        List<TimeSlotEstudo> timeSlots = Arrays.asList(
                new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null),
                new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(8, 0)), null),
                new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(8, 0)), null)
        );

        // data = 2026-01-01 -> dia anterior = 2025-12-31
        // nenhum timeslot acontece antes -> resultado esperado: 0
        int qtd = atribuidor.quantidadeTimeSlotEstudosAntesDe(base, timeSlots);
        assertEquals(0, qtd);
    }

    @Test
    public void testQuantidadeTimeSlotEstudosAntesDe_DataNoMeioDaAgenda() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        LocalDate base = LocalDate.of(2026, 1, 1);
        List<TimeSlotEstudo> timeSlots = Arrays.asList(
                // 01/01
                new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null),
                // 02/01
                new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(8, 0)), null),
                // 03/01
                new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(8, 0)), null)
        );

        // data = 2026-01-03 -> dia anterior = 2026-01-02
        // deve contar timeslots de 01/01 e 02/01 => 2
        int qtd = atribuidor.quantidadeTimeSlotEstudosAntesDe(base.plusDays(2), timeSlots);
        assertEquals(2, qtd);
    }

    @Test
    public void testQuantidadeTimeSlotEstudosAntesDe_DataAntesDaAgenda() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();

        LocalDate base = LocalDate.of(2026, 1, 1);
        List<TimeSlotEstudo> timeSlots = Arrays.asList(
                new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null),
                new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(8, 0)), null)
        );

        // data = 2025-12-30 -> dia anterior = 2025-12-29
        // nenhum timeslot antes => 0
        int qtd = atribuidor.quantidadeTimeSlotEstudosAntesDe(base.minusDays(2), timeSlots);
        assertEquals(0, qtd);
    }

    @Test
    public void testAtribuir_UmaAtividadePreenchePeloMenosUmTimeSlot() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();
        atribuidor.setCalculadoraPesoAtividades(calculadora);

        // Agenda com 3 time slots em dias consecutivos
        AgendaEstudos agenda = new AgendaEstudos();
        LocalDate base = LocalDate.of(2026, 1, 1);

        TimeSlotEstudo ts1 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts2 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts3 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(8, 0)), null);

        agenda.addTimeSlotEstudo(ts1);
        agenda.addTimeSlotEstudo(ts2);
        agenda.addTimeSlotEstudo(ts3);

        // Uma disciplina com uma única atividade dentro da faixa de datas da agenda
        Disciplina disciplina = new Disciplina("TCP", 2.0);
        LocalDate dataLimite = base.plusDays(2); // dentro da agenda

        Prova prova = new Prova("Prova TCP", dataLimite, disciplina);
        disciplina.adicionarAtividade(prova);

        List<Disciplina> disciplinas = Collections.singletonList(disciplina);

    
        atribuidor.atribuir(agenda, disciplinas);

        // Verificação: ts1 e ts2 devem ter a prova atribuída
        assertEquals(prova, ts1.getAtividade());
        assertEquals(prova, ts2.getAtividade());
    }

    @Test
    public void testAtribuir_DuasAtividadesAparecemNaAgenda() {
        AtribuidorAtividades atribuidor = new AtribuidorAtividades();
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();
        atribuidor.setCalculadoraPesoAtividades(calculadora);

        // Agenda com 4 time slots em dias consecutivos
        AgendaEstudos agenda = new AgendaEstudos();
        LocalDate base = LocalDate.of(2026, 1, 1);

        TimeSlotEstudo ts1 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts2 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts3 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts4 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(3), LocalTime.of(8, 0)), null);

        agenda.addTimeSlotEstudo(ts1);
        agenda.addTimeSlotEstudo(ts2);
        agenda.addTimeSlotEstudo(ts3);
        agenda.addTimeSlotEstudo(ts4);

        // Uma disciplina com duas atividades, ambas dentro do período
        Disciplina disciplina = new Disciplina("TCP", 2.0);

        LocalDate dataLimite1 = base.plusDays(2);
        LocalDate dataLimite2 = base.plusDays(3);

        Exercicio exercicio = new Exercicio("Lista 1", dataLimite1, disciplina);
        Trabalho trabalho = new Trabalho("Trabalho 1", dataLimite2, disciplina);

        disciplina.adicionarAtividade(exercicio);
        disciplina.adicionarAtividade(trabalho);

        List<Disciplina> disciplinas = Collections.singletonList(disciplina);

        atribuidor.atribuir(agenda, disciplinas);

        // Verificações: cada atividade deve aparecer em pelo menos um timeslot
        boolean encontrouExercicio = false;
        boolean encontrouTrabalho = false;

        for (TimeSlotEstudo ts : agenda.getEstudos()) {
            if (ts.getAtividade() == exercicio) {
                encontrouExercicio = true;
            }
            if (ts.getAtividade() == trabalho) {
                encontrouTrabalho = true;
            }
        }

        assertTrue("Algum timeslot deveria ter o exercício atribuído", encontrouExercicio);
        assertTrue("Algum timeslot deveria ter o trabalho atribuído", encontrouTrabalho);
    }
}

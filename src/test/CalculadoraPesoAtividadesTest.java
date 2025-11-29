package src.test;

import org.junit.Test;
import static org.junit.Assert.*;

import src.controller.atividades.CalculadoraPesoAtividades;
import src.model.atividades.Atividade;
import src.model.atividades.Exercicio;
import src.model.atividades.Prova;
import src.model.atividades.Trabalho;
import src.model.entities.Disciplina;
import src.model.entities.TimeSlotEstudo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalculadoraPesoAtividadesTest {

    // --------- testes de validação de parâmetros ---------

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPeso_ComListaAtividadesNula() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();
        List<Atividade> atividades = null;
        List<TimeSlotEstudo> slots = Collections.emptyList();

        try {
            calculadora.calcularPeso(atividades, slots);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de atividades não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPeso_ComListaAtividadesVazia() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();
        List<Atividade> atividades = new ArrayList<>();
        List<TimeSlotEstudo> slots = new ArrayList<>();

        try {
            calculadora.calcularPeso(atividades, slots);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de atividades não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPeso_ComListaTimeSlotsNula() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();

        Disciplina TCP = new Disciplina("TCP", 2.0);
        Atividade Lab = new Exercicio("A1", LocalDate.now().plusDays(1), TCP);

        List<Atividade> atividades = new ArrayList<>();
        atividades.add(Lab);
        List<TimeSlotEstudo> slots = null;

        try {
            calculadora.calcularPeso(atividades, slots);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de time slots de estudo não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPeso_ComListaTimeSlotsVazia() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();

        Disciplina TCP = new Disciplina("TCP", 2.0);
        Atividade ProvaP1 = new Prova("P1",  LocalDate.now().plusDays(1), TCP);

        List<Atividade> atividades = new ArrayList<>();
        atividades.add(ProvaP1);
        List<TimeSlotEstudo> slots = new ArrayList<>();

        try {
            calculadora.calcularPeso(atividades, slots);
        } catch (IllegalArgumentException e) {
            assertEquals("Lista de time slots de estudo não pode ser nula ou vazia", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCalcularPeso_UmaAtividadeVariosTimeSlots() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();

        // Disciplina com prioridade conhecida
        Disciplina disciplina = new Disciplina("TCP", 2.0);

        LocalDate dataLimite = LocalDate.of(2026, 1, 3);
        Prova prova = new Prova("Prova 1", dataLimite, disciplina);

        List<Atividade> atividades = Collections.singletonList(prova);

        LocalDate date1 = LocalDate.of(2026, 1, 1);
        LocalDate date2 = LocalDate.of(2026, 1, 2);
        LocalDate date3 = LocalDate.of(2026, 1, 3);

        TimeSlotEstudo estudo1 = new TimeSlotEstudo(LocalDateTime.of(date1, LocalTime.of(8, 0)), null);
        TimeSlotEstudo estudo2 = new TimeSlotEstudo(LocalDateTime.of(date2, LocalTime.of(8, 0)), null);
        TimeSlotEstudo estudo3 = new TimeSlotEstudo(LocalDateTime.of(date3, LocalTime.of(8, 0)), null);

        List<TimeSlotEstudo> slots = Arrays.asList(estudo1, estudo2, estudo3);

        calculadora.calcularPeso(atividades, slots);

        // Esperado: usar a própria constante de tipo da classe + prioridade da disciplina
        double esperado = prova.getPesoTipo() * disciplina.getPrioridade() / 2.0;
        assertEquals(esperado, prova.getPesoCalculado(), 0.0001);
    }

    @Test
    public void testCalcularPeso_DuasAtividadesComPrazosDiferentes() {
        CalculadoraPesoAtividades calculadora = new CalculadoraPesoAtividades();

        Disciplina disciplina = new Disciplina("TCP", 3.0);

        LocalDate prazo1 = LocalDate.of(2026, 1, 2);
        LocalDate prazo2 = LocalDate.of(2026, 1, 3);

        Exercicio exercicio = new Exercicio("Exercicio 1", prazo1, disciplina);
        Trabalho trabalho = new Trabalho("Trabalho 1", prazo2, disciplina);

        List<Atividade> atividades = Arrays.asList(exercicio, trabalho);

        LocalDate date1 = LocalDate.of(2026, 1, 1);
        LocalDate date2 = LocalDate.of(2026, 1, 2);
        LocalDate date3 = LocalDate.of(2026, 1, 3);

        TimeSlotEstudo estudo1 = new TimeSlotEstudo(LocalDateTime.of(date1, LocalTime.of(8, 0)), null);
        TimeSlotEstudo estudo2 = new TimeSlotEstudo(LocalDateTime.of(date2, LocalTime.of(8, 0)), null);
        TimeSlotEstudo estudo3 = new TimeSlotEstudo(LocalDateTime.of(date3, LocalTime.of(8, 0)), null);

        List<TimeSlotEstudo> slots = Arrays.asList(estudo1, estudo2, estudo3);

        calculadora.calcularPeso(atividades, slots);

        double prioridadeDisciplina = disciplina.getPrioridade();

        double esperadoExercicio = exercicio.getPesoTipo() * prioridadeDisciplina / 1.0;
        double esperadoTrabalho  = trabalho.getPesoTipo()  * prioridadeDisciplina / 2.0;

        assertEquals(esperadoExercicio, exercicio.getPesoCalculado(), 0.0001);
        assertEquals(esperadoTrabalho,  trabalho.getPesoCalculado(),  0.0001);
    }
}

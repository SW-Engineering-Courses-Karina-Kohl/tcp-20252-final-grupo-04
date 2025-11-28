package src.test;

import org.junit.Test;
import static org.junit.Assert.*;

import src.controller.agenda.GeradorAgenda;
import src.model.config.ConfiguracaoAgenda;
import src.model.config.DiaSemana;
import src.model.config.Impedimento;
import src.model.entities.AgendaEstudos;
import src.model.entities.TimeSlotEstudo;

import java.time.*;
import java.util.List;

public class GeradorAgendaTest {

    @Test
    // Testar gerar() sem ter configurado o atributo configuracao
    public void testGerarComConfiguracaoNulaNoAtributo() {
        GeradorAgenda gerador = new GeradorAgenda();

        try {
            gerador.gerar();
            fail("Esperava IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Configuração não pode ser nula", e.getMessage());
        }
    }

    @Test
    // Testar gerar(ConfiguracaoAgenda configuracao) passando null
    public void testGerarComParametroConfiguracaoNulo() {
        GeradorAgenda gerador = new GeradorAgenda();

        try {
            gerador.gerar(null);
            fail("Esperava IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Configuração não pode ser nula", e.getMessage());
        }
    }

    @Test
    // Geração de agenda simples, 1 dia, 1 horário, sem impedimentos
    public void testGerarAgendaSimplesSemImpedimentos() {
        LocalDate data = LocalDate.of(2026, 1, 1); // 01/01/2026 (quinta)
        LocalTime horario = LocalTime.of(9, 0);

        ConfiguracaoAgenda configuracao = new ConfiguracaoAgenda();
        configuracao.setDataInicioVigencia(data);
        configuracao.setDataFimVigencia(data);

        DiaSemana quinta = new DiaSemana(DayOfWeek.THURSDAY);
        quinta.adicionarTimeSlot(horario);
        configuracao.setDia(DayOfWeek.THURSDAY, quinta);

        GeradorAgenda gerador = new GeradorAgenda();
        gerador.setConfiguracaoAgenda(configuracao); 

        AgendaEstudos agenda = gerador.gerar();

        List<TimeSlotEstudo> estudos = agenda.getEstudos();
        assertEquals(1, estudos.size());

        TimeSlotEstudo estudo = estudos.get(0);
        assertEquals(LocalDateTime.of(data, horario), estudo.getInicioEstudo());
        assertNull(estudo.getAtividade());
        assertTrue(estudo.isDisponivel());
    }

    @Test
    // Geração com vários dias e 2 horários por dia, sem impedimentos
    public void testGerarAgendaVariosDiasSemImpedimentos() {
        LocalDate inicio = LocalDate.of(2026, 1, 5); // segunda
        LocalDate fim = LocalDate.of(2026, 1, 7);    // quarta

        ConfiguracaoAgenda configuracao = new ConfiguracaoAgenda();
        configuracao.setDataInicioVigencia(inicio);
        configuracao.setDataFimVigencia(fim);

        DiaSemana segunda = new DiaSemana(DayOfWeek.MONDAY);
        segunda.adicionarTimeSlot(LocalTime.of(8, 0));
        segunda.adicionarTimeSlot(LocalTime.of(14, 0));
        configuracao.setDia(DayOfWeek.MONDAY, segunda);

        DiaSemana terca = new DiaSemana(DayOfWeek.TUESDAY);
        terca.adicionarTimeSlot(LocalTime.of(8, 0));
        terca.adicionarTimeSlot(LocalTime.of(14, 0));
        configuracao.setDia(DayOfWeek.TUESDAY, terca);

        DiaSemana quarta = new DiaSemana(DayOfWeek.WEDNESDAY);
        quarta.adicionarTimeSlot(LocalTime.of(8, 0));
        quarta.adicionarTimeSlot(LocalTime.of(14, 0));
        configuracao.setDia(DayOfWeek.WEDNESDAY, quarta);

        GeradorAgenda gerador = new GeradorAgenda();
        gerador.setConfiguracaoAgenda(configuracao);

        AgendaEstudos agenda = gerador.gerar();
        List<TimeSlotEstudo> estudos = agenda.getEstudos();

        // 3 dias * 2 horários = 6 timeslots
        assertEquals(6, estudos.size());

        // Verificação pontual: primeiro e último
        assertEquals(LocalDateTime.of(inicio, LocalTime.of(8, 0)), 
                     estudos.get(0).getInicioEstudo());
        assertEquals(LocalDateTime.of(inicio.plusDays(2), LocalTime.of(14, 0)), 
                     estudos.get(estudos.size() - 1).getInicioEstudo());
    }

    @Test
    // Geração com impedimento em um dos horários
    public void testGerarAgendaComImpedimento() {
        LocalDate data = LocalDate.of(2026, 1, 1); // quinta
        LocalTime horario1 = LocalTime.of(9, 0);
        LocalTime horario2 = LocalTime.of(14, 0);

        ConfiguracaoAgenda configuracao = new ConfiguracaoAgenda();
        configuracao.setDataInicioVigencia(data);
        configuracao.setDataFimVigencia(data);

        DiaSemana quinta = new DiaSemana(DayOfWeek.THURSDAY);
        quinta.adicionarTimeSlot(horario1);
        quinta.adicionarTimeSlot(horario2);
        configuracao.setDia(DayOfWeek.THURSDAY, quinta);

        // Impedimento exatamente no primeiro horário
        Impedimento impedimento = new Impedimento(LocalDateTime.of(data, horario1));
        configuracao.adicionarImpedimento(impedimento);

        GeradorAgenda gerador = new GeradorAgenda();
        gerador.setConfiguracaoAgenda(configuracao);

        AgendaEstudos agenda = gerador.gerar();
        List<TimeSlotEstudo> estudos = agenda.getEstudos();

        // Deve sobrar apenas o segundo horário
        assertEquals(1, estudos.size());
        assertEquals(LocalDateTime.of(data, horario2), estudos.get(0).getInicioEstudo());
    }
}

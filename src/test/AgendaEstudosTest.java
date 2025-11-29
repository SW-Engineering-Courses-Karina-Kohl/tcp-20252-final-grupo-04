package src.test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.model.entities.AgendaEstudos;
import src.model.entities.TimeSlotEstudo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Testes para a classe AgendaEstudos.
 */
public class AgendaEstudosTest {


    /**
     * setCronogramaCriado deve alterar o valor,
     * e os dois getters devem refletir o mesmo estado.
     */
    @Test
    public void testSetCronogramaCriadoAlteraEstado() {
        AgendaEstudos agenda = new AgendaEstudos();

        agenda.setCronogramaCriado(true);
        assertTrue("isCronogramaCriado deve refletir o valor true",
                agenda.isCronogramaCriado());

        agenda.setCronogramaCriado(false);
        assertFalse("isCronogramaCriado deve refletir o valor false",
                agenda.isCronogramaCriado());
    }

    /**
     * addTimeSlotEstudo deve adicionar o time slot na lista interna.
     */
    @Test
    public void testAddTimeSlotEstudoAdicionaNaLista() {
        AgendaEstudos agenda = new AgendaEstudos();

        LocalDateTime inicio = LocalDateTime.of(2026, 1, 1, 8, 0);
        TimeSlotEstudo ts = new TimeSlotEstudo(inicio, null);

        agenda.addTimeSlotEstudo(ts);

        List<TimeSlotEstudo> estudos = agenda.getEstudos();
        assertEquals("Depois de adicionar um time slot, o tamanho da lista deve ser 1",
                1, estudos.size());
        assertSame("O time slot armazenado deve ser o mesmo objeto passado para addTimeSlotEstudo",
                ts, estudos.get(0));
    }

    /**
     * Vários time slots adicionados devem manter a ordem de inserção
     * (aqui também estamos garantindo que a lista está em ordem de data/hora,
     * o que é pré-condição para os métodos que usam BinarySearchUtils).
     * 
     * Inerentemente testa se o método funciona com mais de um elemento.
     */
    @Test
    public void testAddVariosTimeSlotsMantemOrdem() {
        AgendaEstudos agenda = new AgendaEstudos();

        LocalDate base = LocalDate.of(2026, 1, 1);

        TimeSlotEstudo ts1 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null);              // 01/01 08h
        TimeSlotEstudo ts2 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(10, 0)), null); // 02/01 10h
        TimeSlotEstudo ts3 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(14, 0)), null); // 03/01 14h

        agenda.addTimeSlotEstudo(ts1);
        agenda.addTimeSlotEstudo(ts2);
        agenda.addTimeSlotEstudo(ts3);

        List<TimeSlotEstudo> estudos = agenda.getEstudos();

        assertEquals(3, estudos.size());
        assertSame("Primeiro elemento deve ser o primeiro inserido", ts1, estudos.get(0));
        assertSame("Segundo elemento deve ser o segundo inserido", ts2, estudos.get(1));
        assertSame("Terceiro elemento deve ser o terceiro inserido", ts3, estudos.get(2));
    }

    /**
     * getEstudosDia deve retornar apenas os time slots daquele dia,
     * ou lista vazia se não houver nenhum.
     */
    @Test
    public void testGetEstudosDiaRetornaApenasDoDia() {
        AgendaEstudos agenda = new AgendaEstudos();

        LocalDate base = LocalDate.of(2026, 1, 1);

        // 01/01 - dois time slots
        TimeSlotEstudo dia1_ts1 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null);
        TimeSlotEstudo dia1_ts2 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(14, 0)), null);

        // 02/01 - um time slot
        TimeSlotEstudo dia2_ts1 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(9, 0)), null);

        // 03/01 - dois time slots
        TimeSlotEstudo dia3_ts1 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(11, 0)), null);
        TimeSlotEstudo dia3_ts2 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(2), LocalTime.of(16, 0)), null);

        // Adicionar em ordem crescente de data/hora (pré-condição para BinarySearchUtils)
        agenda.addTimeSlotEstudo(dia1_ts1);
        agenda.addTimeSlotEstudo(dia1_ts2);
        agenda.addTimeSlotEstudo(dia2_ts1);
        agenda.addTimeSlotEstudo(dia3_ts1);
        agenda.addTimeSlotEstudo(dia3_ts2);

        // Testar o dia 02/01 (apenas 1 timeslot)
        List<TimeSlotEstudo> estudosDia2 = agenda.getEstudosDia(base.plusDays(1));
        assertEquals(1, estudosDia2.size());
        assertSame(dia2_ts1, estudosDia2.get(0));

        // Testar o dia 01/01 (2 timeslots)
        List<TimeSlotEstudo> estudosDia1 = agenda.getEstudosDia(base);
        assertEquals(2, estudosDia1.size());
        assertSame(dia1_ts1, estudosDia1.get(0));
        assertSame(dia1_ts2, estudosDia1.get(1));

        // Dia sem time slots (por exemplo, 05/01)
        List<TimeSlotEstudo> estudosDiaVazio = agenda.getEstudosDia(base.plusDays(4));
        assertTrue("Dia sem time slots deve retornar lista vazia", estudosDiaVazio.isEmpty());
    }

    /**
     * getEstudosSemana deve retornar os time slots no intervalo
     * [primeiroDia, primeiroDia + 6 dias], ou lista vazia se não houver nenhum
     * naquele período.
     */
    @Test
    public void testGetEstudosSemanaRetornaIntervaloCorreto() {
        AgendaEstudos agenda = new AgendaEstudos();

        // Semana começando em 2026-01-01
        LocalDate primeiroDia = LocalDate.of(2026, 1, 1);

        // Dentro da semana [01, 07]
        TimeSlotEstudo ts1 = new TimeSlotEstudo(LocalDateTime.of(primeiroDia, LocalTime.of(8, 0)), null);                 // 01/01
        TimeSlotEstudo ts2 = new TimeSlotEstudo(LocalDateTime.of(primeiroDia.plusDays(2), LocalTime.of(10, 0)), null);    // 03/01
        TimeSlotEstudo ts3 = new TimeSlotEstudo(LocalDateTime.of(primeiroDia.plusDays(6), LocalTime.of(18, 0)), null);    // 07/01

        // Fora da semana (antes e depois)
        TimeSlotEstudo tsAntesSemana = new TimeSlotEstudo(LocalDateTime.of(primeiroDia.minusDays(1), LocalTime.of(9, 0)), null); // 31/12
        TimeSlotEstudo tsDepoisSemana = new TimeSlotEstudo(LocalDateTime.of(primeiroDia.plusDays(7), LocalTime.of(9, 0)), null); // 08/01

        // Adicionar em ordem cronológica
        agenda.addTimeSlotEstudo(tsAntesSemana);
        agenda.addTimeSlotEstudo(ts1);
        agenda.addTimeSlotEstudo(ts2);
        agenda.addTimeSlotEstudo(ts3);
        agenda.addTimeSlotEstudo(tsDepoisSemana);

        List<TimeSlotEstudo> estudosSemana = agenda.getEstudosSemana(primeiroDia);

        // Esperamos pegar apenas ts1, ts2, ts3
        assertEquals(3, estudosSemana.size());
        assertSame(ts1, estudosSemana.get(0));
        assertSame(ts2, estudosSemana.get(1));
        assertSame(ts3, estudosSemana.get(2));
    }

    /**
     * Caso não existam time slots na semana solicitada, getEstudosSemana
     * deve retornar lista vazia.
     */
    @Test
    public void testGetEstudosSemanaSemTimeSlotsRetornaVazio() {
        AgendaEstudos agenda = new AgendaEstudos();

        // Todos os timeslots estão em fevereiro
        LocalDate base = LocalDate.of(2026, 2, 1);
        TimeSlotEstudo ts1 = new TimeSlotEstudo(LocalDateTime.of(base, LocalTime.of(8, 0)), null);
        TimeSlotEstudo ts2 = new TimeSlotEstudo(LocalDateTime.of(base.plusDays(1), LocalTime.of(10, 0)), null);

        agenda.addTimeSlotEstudo(ts1);
        agenda.addTimeSlotEstudo(ts2);

        // Semana de janeiro
        LocalDate semanaJaneiro = LocalDate.of(2026, 1, 1);
        List<TimeSlotEstudo> estudosSemana = agenda.getEstudosSemana(semanaJaneiro);

        assertTrue("Semana sem time slots deve retornar lista vazia", estudosSemana.isEmpty());
    }
}

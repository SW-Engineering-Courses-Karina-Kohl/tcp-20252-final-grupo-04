package src.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;

import src.controller.atividades.AtribuidorAtividades;
import src.model.atividades.Prova;
import src.model.atividades.Trabalho;
import src.model.entities.AgendaEstudos;
import src.model.entities.Aluno;
import src.model.entities.Disciplina;
import src.model.entities.TimeSlotEstudo;
import src.model.config.ConfiguracaoAgenda;
import src.model.config.DiaSemana;
import src.controller.agenda.GeradorAgenda;
import src.controller.atividades.CalculadoraPesoAtividades;

public class AlunoTest {

    private static class AtribuidorFake extends AtribuidorAtividades {
        boolean chamado;
        AgendaEstudos agendaRecebida;
        List<Disciplina> disciplinasRecebidas;

        @Override
        public void atribuir(AgendaEstudos agenda, List<Disciplina> disciplinas) {
            this.chamado = true;
            this.agendaRecebida = agenda;
            this.disciplinasRecebidas = disciplinas;
        }
    }

    @Test
    public void construtorComConfigInicializaCampos() {
        ConfiguracaoAgenda config = new ConfiguracaoAgenda(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));

        Aluno aluno = new Aluno(config);

        assertSame(config, aluno.getConfiguracaoAgenda());
        assertNotNull(aluno.getAgenda());
        assertTrue(aluno.getDisciplinas().isEmpty());
    }

    @Test
    public void construtorPadraoCriaConfiguracaoEAAgenda() {
        Aluno aluno = new Aluno();

        assertNotNull(aluno.getConfiguracaoAgenda());
        assertNotNull(aluno.getAgenda());
        assertTrue(aluno.getDisciplinas().isEmpty());
    }

    @Test
    public void adicionarERemoverDisciplinaAtualizaLista() {
        Aluno aluno = new Aluno();
        Disciplina disciplina = new Disciplina("TCP", 1.0);

        aluno.adicionarDisciplina(disciplina);
        assertEquals(1, aluno.getDisciplinas().size());
        assertTrue(aluno.getDisciplinas().contains(disciplina));

        aluno.removerDisciplina(disciplina);
        assertTrue(aluno.getDisciplinas().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAtribuidorAtividadesNaoAceitaNulo() {
        Aluno aluno = new Aluno();
        aluno.setAtribuidorAtividades(null);
    }

    @Test(expected = IllegalStateException.class)
    public void atribuirAtividadesAgendaSemAtribuidorLancaExcecao() {
        Aluno aluno = new Aluno();
        aluno.atribuirAtividadesAgenda();
    }

    @Test(expected = IllegalArgumentException.class)
    public void atribuirAtividadesAgendaComAtividadeForaDaVigenciaLanca() {
        LocalDate base = LocalDate.of(2025, 1, 1);
        ConfiguracaoAgenda config = new ConfiguracaoAgenda(base, base.plusDays(5));
        Aluno aluno = new Aluno(config);

        Disciplina disciplina = new Disciplina("TCP", 1.0);
        Prova prova = new Prova("Prova 1", base.plusDays(10), disciplina);
        disciplina.adicionarAtividade(prova);
        aluno.adicionarDisciplina(disciplina);

        aluno.setAtribuidorAtividades(new AtribuidorFake());

        aluno.atribuirAtividadesAgenda();
    }

    @Test
    public void atribuirAtividadesAgendaDelegadaAoAtribuidor() {
        LocalDate base = LocalDate.of(2025, 1, 1);
        ConfiguracaoAgenda config = new ConfiguracaoAgenda(base, base.plusDays(10));
        Aluno aluno = new Aluno(config);

        Disciplina disciplina = new Disciplina("TCP", 1.0);
        Prova prova = new Prova("Prova 1", base.plusDays(3), disciplina);
        disciplina.adicionarAtividade(prova);
        aluno.adicionarDisciplina(disciplina);

        AtribuidorFake atribuidor = new AtribuidorFake();
        aluno.setAtribuidorAtividades(atribuidor);

        aluno.atribuirAtividadesAgenda();

        assertTrue("atribuir deveria ser chamado", atribuidor.chamado);
        assertSame(aluno.getAgenda(), atribuidor.agendaRecebida);
        assertSame(aluno.getDisciplinas(), atribuidor.disciplinasRecebidas);
    }

    @Test
    public void fluxoCompletoUsuarioReal() {
        LocalDate inicio = LocalDate.of(2025, 1, 6); // segunda
        LocalDate fim = inicio.plusDays(4); // sexta
        ConfiguracaoAgenda config = new ConfiguracaoAgenda(inicio, fim);

        DiaSemana segunda = new DiaSemana(DayOfWeek.MONDAY);
        segunda.adicionarTimeSlot(LocalTime.of(8, 0));
        DiaSemana terca = new DiaSemana(DayOfWeek.TUESDAY);
        terca.adicionarTimeSlot(LocalTime.of(8, 0));
        DiaSemana quarta = new DiaSemana(DayOfWeek.WEDNESDAY);
        quarta.adicionarTimeSlot(LocalTime.of(8, 0));
        DiaSemana quinta = new DiaSemana(DayOfWeek.THURSDAY);
        quinta.adicionarTimeSlot(LocalTime.of(8, 0));
        DiaSemana sexta = new DiaSemana(DayOfWeek.FRIDAY);
        sexta.adicionarTimeSlot(LocalTime.of(8, 0));

        config.setDia(DayOfWeek.MONDAY, segunda);
        config.setDia(DayOfWeek.TUESDAY, terca);
        config.setDia(DayOfWeek.WEDNESDAY, quarta);
        config.setDia(DayOfWeek.THURSDAY, quinta);
        config.setDia(DayOfWeek.FRIDAY, sexta);

        GeradorAgenda gerador = new GeradorAgenda(config);
        AgendaEstudos agendaGerada = gerador.gerar();

        Disciplina disciplinaTCP = new Disciplina("TCP", 1.5);
        Disciplina disciplinaSD = new Disciplina("SD", 1.0);

        Prova provaTcp = new Prova("Prova TCP", inicio.plusDays(2), disciplinaTCP); // quarta
        Trabalho trabSd = new Trabalho("Trabalho SD", inicio.plusDays(4), disciplinaSD); // sexta

        disciplinaTCP.adicionarAtividade(provaTcp);
        disciplinaSD.adicionarAtividade(trabSd);

        Aluno aluno = new Aluno(config);
        aluno.setAgendaEstudos(agendaGerada);
        aluno.adicionarDisciplina(disciplinaTCP);
        aluno.adicionarDisciplina(disciplinaSD);

        AtribuidorAtividades atribuidor = new AtribuidorAtividades();
        atribuidor.setCalculadoraPesoAtividades(new CalculadoraPesoAtividades());
        aluno.setAtribuidorAtividades(atribuidor);

        aluno.atribuirAtividadesAgenda();

        List<TimeSlotEstudo> slots = aluno.getAgenda().getEstudos();
        assertFalse("Agenda gerada não deveria estar vazia", slots.isEmpty());

        // Cada atividade deve aparecer ao menos em um time slot
        assertTrue(
            "Algum timeslot deveria ter a prova atribuída",
            slots.stream().anyMatch(ts -> ts.getAtividade() == provaTcp)
        );
        assertTrue(
            "Algum timeslot deveria ter o trabalho atribuído",
            slots.stream().anyMatch(ts -> ts.getAtividade() == trabSd)
        );

        // Todos os time slots produzidos devem estar preenchidos com alguma atividade planejada
        assertTrue(
            "Nenhum time slot da agenda gerada deveria ficar vazio",
            slots.stream().allMatch(ts -> ts.getAtividade() != null)
        );

        // Atividades não devem ser agendadas após seus prazos
        assertTrue(
            "Nenhum time slot deveria ter atividade com prazo já vencido",
            slots.stream().allMatch(ts -> !ts.getInicioEstudo().toLocalDate().isAfter(ts.getAtividade().getDataLimite()))
        );
    }
}

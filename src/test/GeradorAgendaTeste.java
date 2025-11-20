package src.test;
import src.controller.agenda.GeradorAgenda;
import src.model.entities.AgendaEstudos;
import src.model.config.ConfiguracaoAgenda;
import src.model.config.Impedimento;
import src.model.config.DiaSemana;

import org.junit.Test;

public class GeradorAgendaTeste {
    
    @Test
    //Testar gerar() com configuração nula
    public void testGerarComConfiguracaoNula() {
        // Implementação do teste
        GeradorAgenda gerador = new GeradorAgenda();
        try {
            gerador.gerar();
            assert false : "Esperava IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Configuração não pode ser nula");
        }
    }

    @Test
    public void testGerarComConfiguracaoValida() {
        // Implementação do teste
        // Criar uma configuração válida
        ConfiguracaoAgenda configuracao = new ConfiguracaoAgenda();
        configuracao.setDataInicioVigencia(java.time.LocalDate.of(2026, 1, 1));
        configuracao.setDataFimVigencia(java.time.LocalDate.of(2026, 1, 7));
        
        // Chamar o método gerar e verificar se a agenda foi criada corretamente
        // (Detalhes da implementação dependem da classe ConfiguracaoAgenda)
    }

}

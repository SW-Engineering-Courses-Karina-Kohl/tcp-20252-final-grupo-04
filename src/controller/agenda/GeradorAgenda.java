package src.controller.agenda;
import src.model.entities.*;
import src.model.config.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import org.tinylog.Logger;

public class GeradorAgenda {
    private ConfiguracaoAgenda configuracao;

    public GeradorAgenda(ConfiguracaoAgenda configuracao)
    {
        this.setConfiguracaoAgenda(configuracao);
    }
    public GeradorAgenda()
    {
        this.configuracao = null;
    }
    public void setConfiguracaoAgenda(ConfiguracaoAgenda configuracao) {
        if(configuracao == null)
        {
            throw new IllegalArgumentException("Configuração não pode ser nula");            
        }
        this.configuracao = configuracao;
    }

    public AgendaEstudos gerar()
    {
        if(this.configuracao == null)
        {
            throw new IllegalArgumentException("Configuração não pode ser nula");            
        }
        return this.gerar(this.configuracao);
                
    }

    public AgendaEstudos gerar(ConfiguracaoAgenda configuracao)
    {
        if(configuracao == null)
        {
            throw new IllegalArgumentException("Configuração não pode ser nula");
        }        
        // Lógica de geração da agenda com base na configuração

        Logger.info("Iniciando geração da agenda de estudos...");
        AgendaEstudos agenda = new AgendaEstudos();

        // Usar um "contador" do tipo LocalDate para iterar pelos dias e para cada dia iterar pelos TimeSlots configurados
        LocalDate dataIteracao = configuracao.getDataInicioVigencia();

        // Manter um "ponteiro" para o array de impedimentos uma vez que ele está ordenado por data e hora
        Iterator<Impedimento> iteradorImpedimentos = configuracao.getImpedimentos().iterator();
        Impedimento impedimentoAtual = null;

        if(iteradorImpedimentos.hasNext())
        {
            impedimentoAtual = iteradorImpedimentos.next();
        }

        // Criar TimeSlotEstudo's a partir da dataInicioVigencia até dataFimVigencia
        while (configuracao.isDataEntreVigencia(dataIteracao) && !configuracao.getDataFimVigencia().isEqual(dataIteracao)) 
        {
            // Verificar se há estudos para o dia da semana e criar os TimeSlotEstudo's conforme os horários configurados
            if(configuracao.getDiaSemana(dataIteracao.getDayOfWeek()) == null || configuracao.getDiaSemana(dataIteracao.getDayOfWeek()).getHorarios().isEmpty())
            {
                // Avançar para o próximo dia
                dataIteracao = dataIteracao.plusDays(1);
                continue;
            }
            Iterator<LocalTime> iteradorHorariosDia = configuracao.getDiaSemana(dataIteracao.getDayOfWeek()).getHorarios().iterator();

            //Iterar pelos horários do dia
            while(iteradorHorariosDia.hasNext())
            {
                LocalTime horarioAtual = iteradorHorariosDia.next();

                // Verificar se existe algum impedimento marcado para o dia e hora atual
                if(impedimentoAtual != null && impedimentoAtual.conflitaCom(LocalDateTime.of(dataIteracao, horarioAtual)))
                {
                    impedimentoAtual = iteradorImpedimentos.hasNext() ? iteradorImpedimentos.next() : null;
                }else
                {
                    // Criar o TimeSlotEstudo e adicionar na agenda
                    agenda.addTimeSlotEstudo(new TimeSlotEstudo(LocalDateTime.of(dataIteracao, horarioAtual), null));
                }
            }
            // Avançar para o próximo dia
            dataIteracao = dataIteracao.plusDays(1);
            
        }
        Logger.info("Agenda gerada");
        agenda.setCronogramaCriado(true);
        return agenda;
        
    }
    
}

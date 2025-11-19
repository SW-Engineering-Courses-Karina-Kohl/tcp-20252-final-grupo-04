package src.controller;
import src.model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;

public class GeradorAgenda {
    public ConfiguracaoAgenda configuracao;

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

        AgendaEstudos agenda = new AgendaEstudos();

        // Usar um "contador" do tipo LocalDate para iterar pelos dias e para cada dia iterar pelos TimeSlots configurados
        LocalDateTime dataIteracao = configuracao.getDataInicioVigencia();

        // Manter um "ponteiro" para o array de impedimentos uma vez que ele está ordenado por data e hora
        Iterator<Impedimento> iteradorImpedimentos = configuracao.getImpedimentos().iterator();
        Impedimento impedimentoAtual = null;

        if(iteradorImpedimentos.hasNext())
        {
            impedimentoAtual = iteradorImpedimentos.next();
        }

        // Criar TimeSlotEstudo's a partir da dataInicioVigencia até dataFimVigencia
        while (configuracao.isDataEntreVigencia(dataIteracao.toLocalDate())) 
        {
            // Verificar o dia da semana e criar os TimeSlotEstudo's conforme os horários configurados
            Iterator<LocalTime> iteradorHorariosDia = configuracao.getDiaSemana(dataIteracao.getDayOfWeek()).getHorarios().iterator();

            //Iterar pelos horários do dia
            while(iteradorHorariosDia.hasNext())
            {
                LocalTime horarioAtual = iteradorHorariosDia.next();

                // Verificar se existe algum impedimento marcado para o dia e hora atual
                if(impedimentoAtual != null && impedimentoAtual.conflitaCom(dataIteracao))
                {
                    impedimentoAtual = iteradorImpedimentos.hasNext() ? iteradorImpedimentos.next() : null;
                }else
                {
                    // Criar o TimeSlotEstudo e adicionar na agenda
                    agenda.addTimeSlotEstudo(new TimeSlotEstudo(dataIteracao, null));
                }
            }
            // Avançar para o próximo dia
            dataIteracao = dataIteracao.plusDays(1);
            
        }
        return agenda;
        
    }
    
}

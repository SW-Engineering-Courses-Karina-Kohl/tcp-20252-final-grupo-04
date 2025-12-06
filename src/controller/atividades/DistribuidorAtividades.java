package src.controller.atividades;
import src.model.entities.*;
import src.model.allocation.*;

import java.util.*;

import org.tinylog.Logger;

public class DistribuidorAtividades 
{
    public void distribuir(List<AlocacaoAtividade> alocacoes, List<TimeSlotEstudo> timeSlotsDisponiveis)
    {
        // Implementar lógica de distribuição dos TimeSlotEstudo para as atividades conforme a quantidade arredondada
        // Enquanto houver timeSlotsDisponiveis e alocacoes com quantidade > 0
        if(alocacoes == null || alocacoes.isEmpty())
        {
            Logger.error("Erro ao distribuir atividades: Alocacoes esta vazio");
            throw new IllegalArgumentException("Alocacoes esta vazio");
        }

        Iterator<TimeSlotEstudo> iteratorTimeSlots = timeSlotsDisponiveis.iterator();
        int i = 0;
        while(iteratorTimeSlots.hasNext())
        {
            TimeSlotEstudo timeSlot = iteratorTimeSlots.next();
            while (!(alocacoes.get(i).getQuantidadeTimeSlotEstudosArredondada() > 0)) 
            {
                i = i + 1 < alocacoes.size() ? i + 1 : 0;
                
            }
            AlocacaoAtividade alocacaoAtual = alocacoes.get(i);
            // Atribuir atividade ao timeSlot
            timeSlot.setAtividade(alocacaoAtual.getAtividade());
            // Decrementar quantidade de timeSlots a serem atribuídos para a atividade
            alocacaoAtual.decrementarQuantidadeTimeSlotEstudos();
            i = i + 1 < alocacoes.size() ? i + 1 : 0;
                
        }
    }

}

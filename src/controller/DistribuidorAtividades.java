package src.controller;
import src.model.*;
import src.model.atividades.*;

import java.util.*;

public class DistribuidorAtividades 
{
    public void distribuir(List<AlocacaoAtividade> alocacoes, List<TimeSlotEstudo> timeSlotsDisponiveis)
    {
        // Implementar lógica de distribuição dos TimeSlotEstudo para as atividades conforme a quantidade arredondada
        // Enquanto houver timeSlotsDisponiveis e alocacoes com quantidade > 0
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

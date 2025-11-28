package src.controller.atividades;
import src.model.entities.*;
import src.model.atividades.*;
import java.util.Iterator;
import java.util.List;

public class CalculadoraPesoAtividades {
    
    //Atividades e timeSlotEstudos devem estar ordenados previamente
    public void calcularPeso(List<Atividade> atividades, List<TimeSlotEstudo> timeSlotEstudos) 
    {
        if(atividades == null || atividades.isEmpty()) {
            throw new IllegalArgumentException("Lista de atividades não pode ser nula ou vazia");
        }
        if(timeSlotEstudos == null || timeSlotEstudos.isEmpty()) {
            throw new IllegalArgumentException("Lista de time slots de estudo não pode ser nula ou vazia");
        }
        //contadorTimeSlots = 0
        //Para cada atividade na lista de atividades
            //Iterar sobre os timeSlotEstudos, enquanto a data do timeSlotEstudo for menor que a data de entrega da atividade atual
                //contadorTimeSlots++
            //O peso da atividade atual é calculado como constante_tipo * prioridade_peso da disciplina / contadorTimeSlots

        int contadorTimeSlots = 0;
        Iterator<TimeSlotEstudo> iterator = timeSlotEstudos.iterator();
        TimeSlotEstudo timeSlotEstudoAnalisado = iterator.next();
        for(Atividade atividade : atividades)
        {
            while(timeSlotEstudoAnalisado.getData().isBefore(atividade.getDataLimite()))
            {
                contadorTimeSlots++;
                if(!iterator.hasNext())
                {
                    break;
                }
                timeSlotEstudoAnalisado = iterator.next();
            }
            double pesoCalculado = atividade.getPesoTipo() * atividade.getDisciplina().getPrioridade() / contadorTimeSlots;
            atividade.setPesoCalculado(pesoCalculado);
        }
    }
}

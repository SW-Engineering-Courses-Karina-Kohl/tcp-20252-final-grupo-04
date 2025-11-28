package src.model.entities;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.*;
import src.utils.BinarySearchUtils;

public class AgendaEstudos 
{
    private ArrayList<TimeSlotEstudo> estudos;
    private boolean cronogramaCriado;

    public AgendaEstudos()
    {
        this.cronogramaCriado = false;
        this.estudos = new ArrayList<>();
    }

    public void setCronogramaCriado(boolean valor)
    {
        this.cronogramaCriado = valor;
    }

    public boolean isCronogramaCriado()
    {
        return this.cronogramaCriado;
    }

    public List<TimeSlotEstudo> getEstudosDia(LocalDate dia)
    {
        // Retorna a lista de TimeSlotEstudo para o dia especificado
        TimeSlotEstudo chaveInicio = new TimeSlotEstudo(dia.atStartOfDay(), null);
        int indiceInicio = BinarySearchUtils.lowerBound(estudos, chaveInicio, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        if(indiceInicio == estudos.size() || !estudos.get(indiceInicio).getInicioEstudo().toLocalDate().equals(dia))
        {
            return new ArrayList<>();
        }
        TimeSlotEstudo chaveFim = new TimeSlotEstudo(dia.atTime(23,59,59), null);
        int indiceFim = BinarySearchUtils.lastIndexLE(estudos, chaveFim, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        

        return estudos.subList(indiceInicio, indiceFim + 1);
    }

    public List<TimeSlotEstudo> getEstudosSemana(LocalDate primeiroDia)
    {
        // Retorna a lista de TimeSlotEstudo para a semana especificada
        TimeSlotEstudo chaveInicio = new TimeSlotEstudo(primeiroDia.atStartOfDay(), null);
        int indiceInicio = BinarySearchUtils.lowerBound(estudos, chaveInicio, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        if(indiceInicio == estudos.size() || !estudos.get(indiceInicio).getInicioEstudo().toLocalDate().isBefore(primeiroDia.plusDays(6)))
        {
            return new ArrayList<>();
        }
        TimeSlotEstudo chaveFim = new TimeSlotEstudo(primeiroDia.plusDays(6).atTime(23,59,59), null);
        int indiceFim = BinarySearchUtils.lastIndexLE(estudos, chaveFim, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        return estudos.subList(indiceInicio, indiceFim + 1);
    }
    public List<TimeSlotEstudo> getEstudos()
    {
        return this.estudos;
    }

    public void addTimeSlotEstudo(TimeSlotEstudo timeSlotEstudo)
    {
        this.estudos.add(timeSlotEstudo);
    }

}

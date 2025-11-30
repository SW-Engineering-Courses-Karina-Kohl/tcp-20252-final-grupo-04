package src.model.entities;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.*;
import src.utils.BinarySearchUtils;
import org.tinylog.Logger;

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
        if(dia == null)
        {
            Logger.error("Erro ao obter estudos do dia: dia nulo");
            throw new IllegalArgumentException("Dia não pode ser nulo");
        }
        // Retorna a lista de TimeSlotEstudo para o dia especificado
        TimeSlotEstudo chaveInicio = new TimeSlotEstudo(dia.atStartOfDay(), null);
        int indiceInicio = BinarySearchUtils.lowerBound(estudos, chaveInicio, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        Logger.debug("Índice início do dia: {}", indiceInicio);
        if(indiceInicio == estudos.size() || !estudos.get(indiceInicio).getInicioEstudo().toLocalDate().equals(dia))
        {
            return new ArrayList<>();
        }
        TimeSlotEstudo chaveFim = new TimeSlotEstudo(dia.atTime(23,59,59), null);
        int indiceFim = BinarySearchUtils.lastIndexLE(estudos, chaveFim, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        Logger.debug("Índice fim do dia: {}", indiceFim);

        return Collections.unmodifiableList(estudos.subList(indiceInicio, indiceFim + 1));
    }

    public List<TimeSlotEstudo> getEstudosSemana(LocalDate primeiroDia)
    {
        if(primeiroDia == null)
        {
            Logger.error("Erro ao obter estudos da semana: primeiro dia nulo");
            throw new IllegalArgumentException("Primeiro dia não pode ser nulo");
        }
        
        // Retorna a lista de TimeSlotEstudo para a semana especificada
        TimeSlotEstudo chaveInicio = new TimeSlotEstudo(primeiroDia.atStartOfDay(), null);
        int indiceInicio = BinarySearchUtils.lowerBound(estudos, chaveInicio, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        Logger.debug("Índice início da semana: {}", indiceInicio);
        if(indiceInicio == estudos.size() || !estudos.get(indiceInicio).getInicioEstudo().toLocalDate().isBefore(primeiroDia.plusDays(6)))
        {
            return new ArrayList<>();
        }
        TimeSlotEstudo chaveFim = new TimeSlotEstudo(primeiroDia.plusDays(6).atTime(23,59,59), null);
        int indiceFim = BinarySearchUtils.lastIndexLE(estudos, chaveFim, Comparator.comparing(TimeSlotEstudo::getInicioEstudo));
        Logger.debug("Índice fim da semana: {}", indiceFim);
        return Collections.unmodifiableList(estudos.subList(indiceInicio, indiceFim + 1));
    }
    public List<TimeSlotEstudo> getEstudos()
    {
        return Collections.unmodifiableList(this.estudos);
    }

    public void addTimeSlotEstudo(TimeSlotEstudo timeSlotEstudo)
    {
        if(timeSlotEstudo == null)
        {
            Logger.error("Erro ao adicionar TimeSlotEstudo: timeSlotEstudo nulo");
            throw new IllegalArgumentException("TimeSlotEstudo não pode ser nulo");
        }
        this.estudos.add(timeSlotEstudo);
    }

}

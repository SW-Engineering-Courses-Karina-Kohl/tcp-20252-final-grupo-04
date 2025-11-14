package src;
import java.util.List;
import java.time.*;
public class AgendaEstudos 
{
    private List<TimeSlotEstudo> estudos;
    private boolean cronogramaCriado;

    public AgendaEstudos()
    {
        this.cronogramaCriado = false;
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
        return null;
    }

    public List<TimeSlotEstudo> getEstudosSemana(LocalDate primeiroDia)
    {
        // Retorna a lista de TimeSlotEstudo para a semana especificada
        return null;
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

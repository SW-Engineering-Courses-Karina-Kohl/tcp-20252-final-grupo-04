package src;
import java.util.List;
public class AgendaEstudos
{
    private List<TimeSlotEstudo> estudos;
    private boolean cronogramaCriado;

    public AgendaEstudos()
    {
        this.cronogramaCriado = false;
    }

    public void addTimeSlotEstudo(TimeSlotEstudo timeSlotEstudo)
    {
        this.estudos.add(timeSlotEstudo);
    }

}

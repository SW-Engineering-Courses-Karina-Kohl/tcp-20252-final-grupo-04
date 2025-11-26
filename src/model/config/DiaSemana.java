package src.model.config;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class DiaSemana 
{
    private DayOfWeek diaSemana;
    private List<LocalTime> horarios;

    public DiaSemana(DayOfWeek diaSemana)
    {
        this.diaSemana = diaSemana;
        this.horarios = new ArrayList<>();
    }

    public void adicionarTimeSlot(LocalTime timeSlot)
    {
        this.horarios.add(timeSlot);
    }

    public List<LocalTime> getHorarios()
    {
        return this.horarios;
    }

    public DayOfWeek getDiaSemana()
    {
        return this.diaSemana;
    }
}
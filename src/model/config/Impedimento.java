package src.model.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Impedimento 
{
    private LocalDateTime dataHora;  
    
    public Impedimento(LocalDateTime dataHora)
    {
        this.dataHora = dataHora;
    }

    public Impedimento(){}

    public void setDataHora(LocalDateTime dataHora)
    {
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora()
    {
        return this.dataHora;
    }

    public boolean conflitaCom(LocalDateTime dataHora)
    {
        if(dataHora == this.dataHora)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public LocalDate getData()
    {
        return this.dataHora.toLocalDate();
    }

    public LocalTime getHorario()
    {
        return this.dataHora.toLocalTime();
    }
}



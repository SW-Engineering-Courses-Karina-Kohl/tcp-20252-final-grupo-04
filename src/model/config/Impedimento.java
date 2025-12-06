package src.model.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.tinylog.Logger;

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
        if(dataHora == null)
        {
            Logger.error("Data e hora fornecida para verificação de conflito é nula.");
            return false;
        }
        return this.dataHora.equals(dataHora);
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



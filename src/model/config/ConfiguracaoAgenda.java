package src.model.config;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ConfiguracaoAgenda {
    private List<DiaSemana> diasSemana;
    private LocalDateTime dataInicioVigencia;
    private LocalDateTime dataFimVigencia;
    private List<Impedimento> impedimentos;

    // Construtores
    public ConfiguracaoAgenda(LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
        this.dataInicioVigencia = dataInicioVigencia.atStartOfDay();
        this.dataFimVigencia = dataFimVigencia.atTime(23, 59);
        this.impedimentos = new ArrayList<>();
        this.diasSemana = new ArrayList<>();
    }

    public ConfiguracaoAgenda() { 
        this.dataInicioVigencia = null;
        this.dataFimVigencia = null;
        this.impedimentos = new ArrayList<>();
        this.diasSemana = new ArrayList<>();
    };

    // Getters e Setters
    public LocalDateTime getDataInicioVigencia() {
        return this.dataInicioVigencia;
    }
    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia.atStartOfDay();
    }
    public LocalDateTime getDataFimVigencia() {
        return this.dataFimVigencia;
    }
    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia.atTime(23, 59);
    }

    public List<Impedimento> getImpedimentos() {
        return this.impedimentos;
    }

    public void adicionarImpedimento(Impedimento impedimento) {
        this.impedimentos.add(impedimento);
    }

    public void setDia(DayOfWeek dia, DiaSemana diaSemana) { 
        this.diasSemana.add(dia.getValue() - 1, diaSemana);
    }

    public DiaSemana getDiaSemana(DayOfWeek dia) {       
        return diasSemana.get(dia.getValue() - 1);     
    }
 
    //Outros métodos

    public boolean checaDatasVigencia() {
        return dataInicioVigencia.isBefore(dataFimVigencia);
    }

    public boolean isDataEntreVigencia(LocalDate data) {
        LocalDateTime dataInicio = data.atStartOfDay();
        return dataInicio.isAfter(dataInicioVigencia) &&
               (dataInicio.isEqual(dataFimVigencia) || dataInicio.isBefore(dataFimVigencia));
    }

}

package src.model.config;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ConfiguracaoAgenda {
    private List<DiaSemana> diasSemana;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private List<Impedimento> impedimentos;

    // Construtores
    public ConfiguracaoAgenda(LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
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
    public LocalDate getDataInicioVigencia() {
        return this.dataInicioVigencia;
    }
    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }
    public LocalDate getDataFimVigencia() {
        return this.dataFimVigencia;
    }
    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
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
        return data.isAfter(dataInicioVigencia) &&
               (data.isEqual(dataFimVigencia) || data.isBefore(dataFimVigencia));
    }
}
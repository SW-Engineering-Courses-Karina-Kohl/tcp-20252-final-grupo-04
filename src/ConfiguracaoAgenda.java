package src;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ConfiguracaoAgenda {
    private DiaSemana[] dias = new DiaSemana[7];
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private List<Impedimento> impedimentos;
    private List<Disciplina> disciplinas;

    // Construtor
    public ConfiguracaoAgenda(LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.impedimentos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    // Getters e Setters
    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }
    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }
    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }
    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public void setDia(DayOfWeek dia, DiaSemana diaSemana)
    {
        dias[dia.getValue() - 1] = diaSemana;
    }

    //Outros métodos
    public void adicionarImpedimento(Impedimento impedimento) {
        this.impedimentos.add(impedimento);
    }
    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public AgendaEstudos gerarAgenda() {
        return null;
        
    }

    public boolean validarConfiguracao() {
        return true;
        
    }

}

package src;
import java.time.LocalDate;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    LocalDate dataLimite;
    double pesoCalculado;
    final double _PESO_TIPO;

    public Atividade( String nome, int prioridade, LocalDate dataLimite, Disciplina disciplina) {
        this.nome = nome;
        this.dataLimite = dataLimite;
        this.disciplina = disciplina;
        this.pesoCalculado = 0;
    }
    
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public String getNome() {
        return nome;
    }
    
    public double getPesoCalculado() {
        return pesoCalculado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDisciplina(Disciplina disciplina) {
         this.disciplina = disciplina;
    }
    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }
    public  void setPesoCalculado(double pesoCalculado){
        this.pesoCalculado = pesoCalculado;
    }

    public boolean checaDataLimiteEntreVigencia(ConfiguracaoAgenda confAgenda, LocalDate dataLimite){
        return true;
    }

    public abstract int getTotal();
    public abstract  double getPesoTipo();
    public abstract  double calculaPeso();
}

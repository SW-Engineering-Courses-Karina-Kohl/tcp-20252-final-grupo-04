package src.model.atividades;
import src.model.entities.Disciplina;
import java.time.LocalDateTime;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    int prioridade;
    LocalDateTime dataLimite;

    public Atividade(String nome, int prioridade, LocalDateTime dataLimite) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public  Disciplina getDisciplina(){

        return disciplina;
    }


    public void setDisciplina(Disciplina disciplina) {
         this.disciplina = disciplina;
    }
    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }

    public LocalDateTime getDataLimite() {
        return this.dataLimite;
    }

    public void setPesoCalculado(double pesoCalculado) {
    }
    //public boolean checaDataLimiteEntreVigencia(ConfiguracaoAgenda confAgenda, LocalDate dataLimite){
    //    return true;
    //}

    public abstract int getTotal();
    public abstract  double getPesoTipo();
    public abstract  double calculaPeso();
    public abstract double getPesoCalculado();
}

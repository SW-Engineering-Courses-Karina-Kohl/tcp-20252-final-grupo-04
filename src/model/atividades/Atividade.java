package src.model.atividades;
import src.model.entities.Disciplina;
import java.time.LocalDate;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    int prioridade;
    LocalDate dataLimite;
    double pesoCalculado;
    final int maxPrioridade = 5;

    public Atividade( String nome, int prioridade, LocalDate dataLimite, Disciplina disciplina) {
        if (nome == "" || nome == null){ 
            throw new IllegalArgumentException("Insira um nome válido para a atividade.");    
        }
        if (prioridade < 1 || prioridade > maxPrioridade){
            throw new IllegalArgumentException("A atividade deve possuir um grau de prioridade entre 1 e 5.");
        }
        if (dataLimite == null){
            throw new IllegalArgumentException("Insira uma data.");
        }
        else if (dataLimite.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data inserida deve ser uma data futura");
        }
        if (disciplina == null){
            throw new IllegalArgumentException("A atividade deve estar associada a uma disciplina.");
        }
        if(pesoCalculado < 0){
            throw new IllegalArgumentException("O peso calculado deve ser maior ou igual a zero.");
        }
        this.nome = nome;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.disciplina = disciplina;
        this.pesoCalculado = 0;
    }
    
    public LocalDate getDataLimite() {
        return dataLimite;
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
    public  Disciplina getDisciplina(){

        return disciplina;
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
    public abstract int getTotal();
    public abstract  double getPesoTipo();
}

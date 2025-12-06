package src.model.atividades;
import src.model.entities.Disciplina;
import java.time.LocalDate;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    LocalDate dataLimite;
    double pesoCalculado;
    static final int MAXPESO = 5;

    public Atividade( String nome, LocalDate dataLimite, Disciplina disciplina) {
        if (nome == "" || nome == null){ 
            throw new IllegalArgumentException("O nome de uma atividade não deve ser vazio!");    
        }
        if (dataLimite == null){
            throw new IllegalArgumentException("Insira uma data.");
        }
        if (disciplina == null){
            throw new IllegalArgumentException("A atividade deve estar associada a uma disciplina.");
        }
        this.nome = nome;
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
        if(pesoCalculado < 0 || pesoCalculado > MAXPESO){
            throw new IllegalArgumentException("O peso calculado deve estar entre 0 e " + MAXPESO +".");
        }
        this.pesoCalculado = pesoCalculado;
    }
    public abstract int getTotal();
    public abstract  double getPesoTipo();
}

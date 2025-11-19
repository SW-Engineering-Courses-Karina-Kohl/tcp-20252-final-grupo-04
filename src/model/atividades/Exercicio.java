package src.model.atividades;

import java.time.LocalDateTime;

public class Exercicio  extends Atividade {
    static int _totalExercicios;
    double pesoCalculado;

    public Exercicio(String nome, int prioridade, LocalDateTime dataLimite) {
        super(nome, prioridade, dataLimite);
    }

    
    @Override
    public int getTotal() {
        return 0;
    }

    @Override
    public double getPesoTipo() {
    return 0;
    }
    
    @Override
    public double calculaPeso() {
        return 0;
    }

    @Override 
    public double getPesoCalculado() {
        return pesoCalculado;
    }
    @Override
    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    @Override
    public void setPesoCalculado(double pesoCalculado) {
     this.pesoCalculado = pesoCalculado;    

    }
}

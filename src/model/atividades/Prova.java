package src.model.atividades;

import java.time.LocalDate;

import src.model.entities.Disciplina;

public class Prova extends Atividade {
    public static final double _PESO_TIPO = 0;
    static int _totalProvas;
    double pesoCalculado;

    public Prova(String nome, int prioridade, LocalDate dataLimite, Disciplina disciplina) {
        super(nome, prioridade, dataLimite, disciplina);
        _totalProvas++;
    }
    
    @Override
    public int getTotal() {
        return _totalProvas;
    }

    @Override
    public double getPesoTipo() {
        return _PESO_TIPO;
    }
}

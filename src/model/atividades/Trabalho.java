package src.model.atividades;
import java.time.LocalDate;

import src.model.entities.Disciplina;

public class Trabalho extends Atividade {
    public static final double _PESO_TIPO = 1.3;
    static int _totalTrabalhos;
    double pesoCalculado;

    public Trabalho(String nome, LocalDate dataLimite, Disciplina disciplina) {
        super(nome, dataLimite, disciplina);
        _totalTrabalhos++;
    }

    
    @Override
    public int getTotal() {
        return _totalTrabalhos;
    }

    @Override
    public double getPesoTipo() {
        return _PESO_TIPO;
    }

    
    
}

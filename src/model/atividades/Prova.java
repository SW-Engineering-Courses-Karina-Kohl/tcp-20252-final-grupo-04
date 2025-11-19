package src.model.atividades;

import java.time.LocalDateTime;

public class Prova extends Atividade {
    public static final double _PESO_TIPO = 0;
    static int _totalProvas;
    double pesoCalculado;

    public Prova(String nome, int prioridade, LocalDateTime dataLimite) {
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
    
    
}

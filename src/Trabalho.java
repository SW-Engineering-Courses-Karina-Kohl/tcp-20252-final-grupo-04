package src;

public class Trabalho extends Atividade {
    public static final double _PESO_TIPO = 0;
    static int _totalTrabalhos;
    double pesoCalculado;

    public Trabalho(String nome, int prioridade, java.time.LocalDate dataLimite, int totalPaginas) {
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

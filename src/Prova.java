package src;

public class Prova extends Atividade {
    public static final double _PESO_TIPO = 0;
    static int _totalProvas;
    double pesoCalculado;

    public Prova(String nome, int prioridade, java.time.LocalDate dataLimite) {
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

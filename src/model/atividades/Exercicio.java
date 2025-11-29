package src.model.atividades;
import java.time.LocalDate;
import src.model.entities.Disciplina;
public class Exercicio  extends Atividade {
    static int _totalExercicios;
    double pesoCalculado;
    static final double _PESO_TIPO = 1;

    public Exercicio(String nome, LocalDate dataLimite, Disciplina disciplina) {
        super(nome, dataLimite, disciplina);
        _totalExercicios++;
    }

    
    @Override
    public int getTotal() {
        return _totalExercicios;
    }

    @Override
    public double getPesoTipo() {
        return _PESO_TIPO;
    }

}

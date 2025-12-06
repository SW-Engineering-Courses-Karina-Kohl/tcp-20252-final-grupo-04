package src.test;
import src.model.atividades.*;
import src.model.entities.*;
import org.junit.Test;
import java.time.LocalDate;
public class DisciplinaTest {
    @Test
    public void testCriarDisciplina() {
        Disciplina disciplina = new Disciplina("Matemática", 1.0);
        assert(disciplina.getNome().equals("Matemática"));
        assert(disciplina.getPrioridade() == 1.0);
        assert(disciplina.getTotalDisciplinas() >= 1);
    }
    @Test
    public void testAdicionarAtividade() {
        Disciplina disciplina = new Disciplina("História", 2.0);
        Atividade atividade = new Prova("Prova de Revolução Francesa", LocalDate.of(2023, 11, 15), disciplina);
        disciplina.adicionarAtividade(atividade);
        assert(disciplina.getAtividades().size() == 1);
        }
    @Test
    public void testModificarDisciplina() {
        Disciplina disciplina = new Disciplina("Biologia", 1.5);
        disciplina.setNome("Biologia Avançada");
        disciplina.setPrioridade(2.5);
        assert(disciplina.getNome().equals("Biologia Avançada"));
        assert(disciplina.getPrioridade() == 2.5);
    }
}

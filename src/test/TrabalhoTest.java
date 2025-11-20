package src.test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import src.model.atividades.Trabalho;
import src.model.entities.Disciplina;

public class TrabalhoTest {
    @Test
    public void getTotalTest(){
        Disciplina  disciplina = new Disciplina("tes", 1);      
        Trabalho trab1 =  new Trabalho("test", 1, LocalDate.now(), disciplina);
        Trabalho trab2 =  new Trabalho("test!", 1, LocalDate.now(), disciplina);
        ArrayList<Trabalho> trabalhos =  new ArrayList<Trabalho>();
        trabalhos.add(trab1);
        trabalhos.add(trab2);
        assertEquals(trab1.getTotal(), trabalhos.size());
    }
}

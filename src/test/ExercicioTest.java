package src.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import src.model.atividades.Exercicio;
import src.model.entities.Disciplina;

public class ExercicioTest {
    @Test
    public void getTotalTest(){
        Disciplina  disciplina = new Disciplina("tes", 1);      
        Exercicio ex1 =  new Exercicio("test!", 1, LocalDate.now(), disciplina);
        Exercicio ex2 =  new Exercicio("test!", 1, LocalDate.now(), disciplina);
        ArrayList<Exercicio> exercicios =  new ArrayList<Exercicio>();
        exercicios.add(ex2);
        exercicios.add(ex1);
        assertEquals(ex1.getTotal(), exercicios.size());
    }
}

package src.test;
import src.model.atividades.Prova;
import src.model.entities.Disciplina;

import java.util.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;
// OBS: Como a maioria dos métodos em Prova são herdados de Atividade e não foram modificados,
// estes testes englobam os testes dos métodos herdados também, e nos seguintes apenas serão testados
// os métodos específicos de Trabalho e Exercício.
public class ProvaTest {
    @Test
    public void testCreateProva() {
        String name = "Prova de Matemática";
        Prova prova = new Prova(name,  java.time.LocalDate.of(2023, 10, 5), null);
        assertEquals(name, prova.getNome());
    }

    @Test
    public void testGetTotal() {
        ArrayList<Prova> provas = new ArrayList<>();
        Prova prova1 = new Prova("Prova 1",  java.time.LocalDate.of(2023, 10, 5), null);
        Prova prova2 = new Prova("Prova 2",  java.time.LocalDate.of(2023, 11, 5), null);
        provas.add(prova1);
        provas.add(prova2); 
        assertEquals(provas.size(), prova1.getTotal());
    }
    @Test
    public void testNameEmpty() {
        Disciplina ing =  new Disciplina("Test", 1);
        IllegalArgumentException thrown =  assertThrows(IllegalArgumentException.class, () -> {new Prova("",  LocalDate.now(), ing);}); 
        assertEquals(thrown.getMessage(), "O nome de uma atividade não deve ser vazio!");
        IllegalArgumentException thrownNull =  assertThrows(IllegalArgumentException.class, () -> {new Prova(null, LocalDate.now(), ing);}); 
        assertEquals(thrownNull.getMessage(), "O nome de uma atividade não deve ser vazio!");
    }


    @Test
    public void testEarlierDate(){
     Disciplina ing =  new Disciplina("Test", 1);
     IllegalArgumentException thrown =  assertThrows(IllegalArgumentException.class, () -> {new Prova("Test", LocalDate.of(1000, 11, 1) , ing);});   
     assertEquals(thrown.getMessage(), "A data inserida deve ser posterior à data atual.");

    }

    @Test
    public void testNullDIscipline(){
     Disciplina ing =  new Disciplina("Test", 1);
     IllegalArgumentException thrown =  assertThrows(IllegalArgumentException.class, () -> {new Prova("Test",  LocalDate.of(1000, 11, 1) , ing);});   
     assertEquals(thrown.getMessage(), "A data inserida deve ser posterior à data atual.");

    }

    @Test
    public void testPesoCalculado(){
    Disciplina ing =  new Disciplina("Test", 1);
    Prova prova =  new Prova("Test",  LocalDate.now() , ing);
    IllegalArgumentException thrown  =  assertThrows(IllegalArgumentException.class, () ->   {prova.setPesoCalculado(-1);});
    assertEquals(thrown.getMessage(), "O peso calculado deve ser maior ou igual a zero.");
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.ArrayList;
import java.util.List;
import model.Listazo;
import static model.Listazo.termekArak;
import static model.Listazo.termekNevek;
import static model.Listazo.termekOsszar;
import static model.Listazo.numValidator;
import model.Termekek;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roland
 */
public class ListazoTest {
    private Listazo testLista;
    private List<Integer> testArak;
    private List<String> testTermeknevek;
    private List<Termekek> testTermekek;
    
    public ListazoTest() {
    }
    
    @Before
    public void setUp() {
      testLista = new Listazo();
      testArak = new ArrayList();
      testTermeknevek = new ArrayList();
      testTermekek = new ArrayList();
      testArak.add(100);
      testTermeknevek.add("Termék1");
      Termekek termek1 = new Termekek("Term1",300);
      Termekek termek2 = new Termekek("Term2",400);
      testTermekek.add(termek1);
      testTermekek.add(termek2);
    }
    
    @Test
    public void testSetArlista(){
    testLista.setArlista(testArak);
    assertFalse(testLista.getArlista().isEmpty());
    }
    
    @Test
    public void testSetTermeklista(){
    testLista.setTermeklista(testTermeknevek);
    assertFalse(testLista.getTermeklista().isEmpty());
    }
    
     @Test
    public void testGetArlista(){
    testLista.getArlista().add(200);
    testArak=testLista.getArlista();
    assertTrue(testLista.getArlista().equals(testArak));
    }
    
    @Test
    public void testGetTermeklista(){
    testLista.getTermeklista().add("Termék2");
    testTermeknevek=testLista.getTermeklista();
    assertTrue(testLista.getTermeklista().equals(testTermeknevek));
    }
    
    @Test
    public void testTermekOsszarOne() {
    String mennyiseg = "10";
    testLista.getArlista().add(100);
    assertEquals("1000",termekOsszar(testLista.getArlista().size()-1,testLista.getArlista(),mennyiseg));
    }
    
    @Test 
    public void testTermekOsszarTwo(){
    String mennyiseg = "10";
    testLista.getArlista().add(100);
    testLista.getArlista().add(200);
    assertEquals("2000",termekOsszar(testLista.getArlista().size()-1,testLista.getArlista(),mennyiseg));    
    }
    
    @Test
    public void testTermekArak(){
        testLista.setArlista(termekArak(testTermekek));
        assertEquals(testLista.getArlista().size(),testTermekek.size());
    }
    
    @Test
    public void testTermekNevek(){
        testLista.setTermeklista(termekNevek(testTermekek));
        assertEquals(testLista.getTermeklista().size(),testTermekek.size());
    }
    
    @Test
    public void testFalseNumValidator(){
    assertFalse(numValidator("abdsfgc"));    
    }
    
    @Test
    public void testTrueNumValidator(){
    assertTrue(numValidator("120"));    
    }
    
}

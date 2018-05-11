/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * TermekekDAO interfészt implementáló osztály. 
 */

import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author roland
 */
public class TermekekDAOImpl implements TermekekDAO {
    private static final Logger logger=LoggerFactory.getLogger(TermekekDAOImpl.class);
    private EntityManager em;
    /**
     * Konstruktor létrehoz egy példányt a TermekekDAO-t implementáló osztályból.
     * @param em EntityManager egy példánya az adatbázis műveletekhez
     */
    public TermekekDAOImpl(EntityManager em) {
        this.em = em;
        logger.info("Létrejött egy TermekekDAO-t implementáló osztály példánya");
    } 
    
    @Override
    public void createTermek(String termek_nev, int ar) {
       em.getTransaction().begin();
        
        Termekek u = new Termekek(termek_nev,ar);
        
        em.persist(u);
    
        em.getTransaction().commit();
        logger.info("Lérehozunk egy terméket és az adatbázishoz adjuk");
    }

    @Override
    public Termekek readTermek(long id) {
        logger.info("Kiolvasunk egy terméket az adatbázisból");
        return em.find(Termekek.class, id);
    }

    @Override
    public void updateTermekek(Termekek t, String termek_nev, int ar) {
       em.getTransaction().begin();
        
        t.setTermek_nev(termek_nev);
        t.setAr(ar);
        
        em.getTransaction().commit();
        logger.info("Frissítünk egy terméket az adatbázisba");
    }

    @Override
    public void deleteTermekek(Termekek t) {
        em.getTransaction().begin();
        
        em.remove(t);
        
        em.getTransaction().commit();
        logger.info("Kitörlünk egy terméket az adatbázisból");
    }
    
    @Override
    public List<Termekek> all() {
       logger.info("Kimásoljuk az összes terméket az adatbázisból egy listába"); 
       return em.createQuery("SELECT t FROM Termekek t", Termekek.class).getResultList();
    } 
}

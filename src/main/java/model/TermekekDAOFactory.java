/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * Adatbázis kapcsolatot megvalósító szingleton osztály.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author roland
 */
public class TermekekDAOFactory implements AutoCloseable {
    
    private static TermekekDAOFactory instance;
    private static final Logger logger=LoggerFactory.getLogger(TermekekDAOFactory.class);
    
    private static EntityManager em;
    private static EntityManagerFactory f;
    /**
     * Ezzel biztosítjuk az szingleton osztály tulajdonságot.
     */
    static {
        instance = new TermekekDAOFactory();
        f = Persistence.createEntityManagerFactory("BEADANDO");
        em = f.createEntityManager();
        logger.info("Adatbázis kapcsolat létrejött");
    }
    /**
     * Konstruktor amely létrehoz egy példányt a TermkekDAOFActory-ból.
     */
    private TermekekDAOFactory() {
    logger.info("TermekekDAOFactory példánya létrejött");    
    }
    /**
     * Visszaadja a metódust amellyel létrehozhatjuk a TermekeDAOFactory példányát.
     * @return egy metódus amellyel által példányosíthatunk
     */
    public static TermekekDAOFactory getInstance() {
        return instance;
    }
    /**
     * Visszaad egy példányt a TermekekDAO interfészt impelementáló osztályából.
     * @return egy példány a TermekekDAOImpl osztályból
     */
    public TermekekDAO createTermekekDAO() {
        return new TermekekDAOImpl(em);
    }
    /**
     * Bezárja az adatbáziskapcsolatot megvalósító Factory-t és az Entity Manager-t.
     * @throws Exception kivétel amit akkor dob ha nem sikerül bezárni
     */
    @Override
    public void close() throws Exception {
        em.close();
        f.close();
        logger.info("Adatbázis kapcsolat bezárult");
    }
}

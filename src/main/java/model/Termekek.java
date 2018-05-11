/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * Entitás osztály, mely a Termekek adatbázistáblát reprezentálja.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author roland
 */
@Entity
@Table(name = "Termekek")
public class Termekek {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(name = "termek_nev")
    private String termek_nev;

    
    @Column(name = "ar")
    private int ar;
    /**
     * Konstruktor, mely létrehoz egy példányt a Termekek osztalyból. 
     */
    public Termekek(){
        
    }
    /**
     * Konstruktor, mely létrehoz egy példányt a Termekek osztályból az adott paraméterekkel.
     * @param termek_nev a termék neve
     * @param ar a termék ára
     */
    public Termekek(String termek_nev, int ar) {
        this.termek_nev = termek_nev;
        this.ar = ar;
    }
    /**
     * Visszaadja egy termék id-ját.
     * @return a termék id-ja
     */
    public long getId() {
        return id;
    }
    /**
     * Beállítja egy termék id-ját.
     * @param id a termék id-ja
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Visszaadja egy termék nevét.
     * @return a termék neve
     */
    public String getTermek_nev() {
        return termek_nev;
    }
    /**
     * Beállítja egy termék nevét.
     * @param termek_nev a termék neve
     */
    public void setTermek_nev(String termek_nev) {
        this.termek_nev = termek_nev;
    }
    /**
     * Visszaadja egy termék árát.
     * @return a termék ára
     */
    public int getAr() {
        return ar;
    }
    /**
     * Beállítja egy termék árát.
     * @param ar a termék ára
     */
    public void setAr(int ar) {
        this.ar = ar;
    }
    /**
     * Termekek osztály egy példánya stringként.
     * @return a példány string formában
     */
    @Override
    public String toString() {
        return "Termekek{" + "termek_nev=" + termek_nev + ", ar=" + ar + '}';
    }
    
    
    
}
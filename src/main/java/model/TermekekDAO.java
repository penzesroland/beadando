/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * Termekek táblán végezhető műveleteket megvalósító interfész.
 */

import java.util.List;

/**
 *
 * @author roland
 */
public interface TermekekDAO {
    /**
     * Készít egy új terméket amelyet a táblához ad.
     * @param termek_nev a termék neve
     * @param ar a termék ára
     */
    public void createTermek(String termek_nev,int ar);
    /**
     * Kiolvas egy terméket az id-ja alapján a táblából.
     * @param id a termék id-ja
     * @return a kiolvasott termék objektum
     */
    public Termekek readTermek(long id);
    /**
     * Frissít egy terméket a táblában.
     * @param t a módosítandó termék objektum
     * @param termek_nev a termék új neve
     * @param ar a termék új ára
     */
    public void updateTermekek(Termekek t, String termek_nev, int ar );
    /**
     * Kitöröl egy terméket a táblából.
     * @param t a törlendő termék objektum
     */
    public void deleteTermekek(Termekek t);
    /**
     * Kiszedi egy listába az összes példány objektumot a táblából.
     * @return a termék objektumokat tartalmazó lista
     */
    public List<Termekek> all ();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * Az adatbázisból kiolvasott termékeket ár illetve terméknév listákra bontó osztály.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author roland
 */
public class Listazo {
    private static final Logger logger=LoggerFactory.getLogger(Listazo.class);
    private List<String> termeklista = new ArrayList();
    private List<Integer> arlista = new ArrayList();
    /**
     * Konstruktor, mely létrehoz egy Listazo obejktumot.
     */
    public Listazo() {
        logger.info("Létrejön egy Listazó példány");
    }
    /**
     * Visszaadja a terméknevek listáját.
     * @return a terméknevek listája
     */
    public List<String> getTermeklista() {
        return termeklista;
    }
    /**
     * Beállítja a terméknevek listáját.
     * @param termeklista a terméknevek listája
     */
    public void setTermeklista(List<String> termeklista) {
        this.termeklista = termeklista;
    }
    /**
     * Visszaadja az árak listáját.
     * @return az árak listáját
     */
    public List<Integer> getArlista() {
        return arlista;
    }
    /**
     * Beállítja az árak listáját.
     * @param arlista az árak listája
     */
    public void setArlista(List<Integer> arlista) {
        this.arlista = arlista;
    }
    
    /**
     * Termekek osztály objektumait tartalmazó listából leválogatja a termékek nevét.
     * @param termekek Termekek osztály objektumait tartalmazó lista
     * @return egy lista az összes termék nevével
     */
    public static List<String> termekNevek (List<Termekek> termekek){
       logger.info("Létrejön egy lista a termékek nevével"); 
       return termekek.stream()
               .map((x)->x.getTermek_nev())
               .collect(Collectors.toList());
   }
   /**
    * Termekek osztály objektumait tartalmazó listából leválogatja a termékek nevét.
    * @param termekek Termekek osztály objektumait tartalmazó lista
    * @return egy lista az termék árával
    */
   public static List<Integer> termekArak (List<Termekek> termekek){
       logger.info("Létrejön egy lista a termékek áraival");
       return termekek.stream()
               .map((x)->x.getAr())
               .collect(Collectors.toList());
   }
   /**
    * Visszaadja a mennyiség és az ár összesített értékét szövegként.
    * @param index az kiválasztott termék indexe a termeklistából
    * @param ar az árakat tartalmazó lista
    * @param mennyiseg a beírt mennyiség szövegként
    * @return az összesített érték szövegként
    */
   public static String termekOsszar(int index,List<Integer> ar,String mennyiseg){
       logger.info("Összesítjük az árakat");
       int ossz =ar.get(index);
       ossz*=Integer.parseInt(mennyiseg);
       return Integer.toString(ossz);    
   }
  /**
   * Visszaadja a beírt mennyiségről hogy számmá alakítható-e. 
   * @param text a mennyiségként beírt szöveg
   * @return számmá alakítható-e
   */ 
  public static boolean numValidator(String text){
  try  
  {  
    int d = Integer.parseInt(text);  
  }  
  catch(NumberFormatException nfe)  
  {  
    logger.error("Nem lehet számmá alakítani a beírt szöveget");  
    return false;  
  }  
  return true;  
   }
    
}

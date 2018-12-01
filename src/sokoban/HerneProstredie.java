package sokoban;

import java.awt.image.BufferedImage;

/**
 * Trieda HerneProstredie reprezentuje vsetky objekty v hre. Dedia od nej triedy
 * Bednicka, MiestoKamDorucit, Hrac a Stena. 
 * Vie vratit pozicie x a y, nastavit tieto pozicie, nacitat obrazok daneho objektu a
 * overit, ci sa v danom smere (hore, dole, dolava, doprava) na vzdialenost
 * jedneho kliku od aktualnej pozicie nachadza nejaky objekt.
 * 
 * @author Hoskovec
 */
public class HerneProstredie {
    
    private final int medzera = 40;
    private int pozX;
    private int pozY;

    /**
     * Parametricky konstruktor, do ktoreho sa zadava pozicia x (= pozX) a 
     * pozicia y (= pozY).
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y
     */
    public HerneProstredie(int pozX, int pozY) {
        this.pozX = pozX;
        this.pozY = pozY;
    }
    
    public int pozX() {
        return this.pozX; 
    }
    
    public int pozY() {
        return this.pozY;
    }
    
    public void setPozX(int x) {
        this.pozX = x;
    }
    
    public void setPozY(int y) {
        this.pozY = y;
    }
    
    public BufferedImage nacitajObrazok() {
        return null;
    }
       
    /**
     * Nasledujuce metody vratia true, ak sa v danom smere
     * nachadza nejaky objekt, ktory dedi tuto triedu, 
     * vo vzdialenosti jedneho kliku od aktualnej pozicie.
     * 
     * @param herneProstredie
     * @return 
     */ 
    public boolean jeVlavoObjekt(HerneProstredie herneProstredie) {
        return (this.pozX() - this.medzera) == herneProstredie.pozX() && (this.pozY() == herneProstredie.pozY());
    }
    
    public boolean jeVpravoObjekt(HerneProstredie herneProstredie) {
        return (this.pozX() + this.medzera) == herneProstredie.pozX() && (this.pozY() == herneProstredie.pozY());
    }
    
    public boolean  jeNadObjekt(HerneProstredie herneProstredie) {
        return (this.pozX() == herneProstredie.pozX() && (this.pozY() - this.medzera) == herneProstredie.pozY()); 
    }
    
    public boolean jePodObjekt(HerneProstredie herneProstredie) {
        return (this.pozX() == herneProstredie.pozX() && (this.pozY() + this.medzera) == herneProstredie.pozY());
    }

}

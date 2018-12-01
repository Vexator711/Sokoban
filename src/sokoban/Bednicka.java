package sokoban;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Trieda Bednicka reprezentuje v hre bednicku, ktoru je potrebne presunut
 * na zadane miesto. Je potomok triedy HerneProstredie (dedi od tejto triedy), 
 * vie nacitat svoj obrazok (ako bude vyzerat) a vie sa premiestnit o zadane 
 * suradnice.
 * 
 * @author Hoskovec
 */
public class Bednicka extends HerneProstredie {
    
    /**
     * Parametricky konstruktor triedy Bednicka, ktory dedi parametre pozX
     * a pozY.
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y 
     */
    public Bednicka(int pozX, int pozY) {
        super(pozX, pozY);
    }
    
    /**
     * Tato metoda nacitava obrazok bednicky. "Overriduje" danu metodu v triede
     * HerneProstredie.
     * 
     * @return - obrazok bednicky
     */
    @Override
    public BufferedImage nacitajObrazok() {
        BufferedImage obrBednicky = null;
        try {
            obrBednicky = ImageIO.read(new File("bednicka.png"));
        } catch (IOException e) {
        }
        return obrBednicky;
    }
    
    /**
     * Tato metoda presuva bednicku o zadane suradnice x (= pozX) a y (= pozY).
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y
     */
    public void pohniSa(int pozX, int pozY) {
        int kamX = this.pozX() + pozX;
        int kamY = this.pozY() + pozY;
        this.setPozX(kamX);
        this.setPozY(kamY);
    }
    
}

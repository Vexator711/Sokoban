package sokoban;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Trieda Hrac reprezentuje hraca - sokobana. Je potomok triedy HerneProstredie
 * (dedi od tejto triedy), vie nacitat svoj obrazok (ako bude vyzerat) a vie
 * sa premiestnit od zadane suradnice.
 * 
 * @author Hoskovec
 */
public class Hrac extends HerneProstredie {

    /**
     * Parametricky konstruktor triedy Hrac, ktory dedi parametre pozX a pozY.
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y
     */
    public Hrac(int pozX, int pozY) {
        super(pozX, pozY);
    }
    
    /**
     * Tato metoda nacitava obrazok hraca - sokobana. "Overriduje" danu metodu
     * v triede HerneProstredie.
     * 
     * @return - obrazok sokobana 
     */
    @Override
    public BufferedImage nacitajObrazok() {
        BufferedImage obrHraca = null;
        try {
            obrHraca = ImageIO.read(new File("hrac.png"));
        } catch (IOException e) {
        }
        return obrHraca;
    }
    
    /**
     * Tato metoda presuva hraca - sokobana o zadane suradnice x (= pozX) a 
     * y (= pozY).
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

package sokoban;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Trieda Stena reprezentuje v hre stenu. Je potomok triedy HerneProstredie
 * (dedi od tejto triedy) a vie nacitat svoj obrazok.
 * 
 * @author Hoskovec
 */
public class Stena extends HerneProstredie {
    
    /**
     * Parametricky konstruktor triedy Stena, ktory dedi parametre pozX a 
     * pozY.
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y
     */
    public Stena(int pozX, int pozY) {
        super(pozX, pozY);
        
    }
    
    /**
     * Tato metoda nacitava obrazok steny. "Overriduje" danu metodu v triede
     * HerneProstredie.
     * 
     * @return - obrazok steny
     */
    @Override
    public BufferedImage nacitajObrazok() {
        BufferedImage obrSteny = null;
        try {
            obrSteny = ImageIO.read(new File("stena.png"));
        } catch (IOException e) {
        }
        return obrSteny;
    }
    
}

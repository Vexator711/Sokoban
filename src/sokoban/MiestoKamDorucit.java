package sokoban;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Trieda MiestoKamDorucit reprezentuje v hre dane miesto, kam je potrebne
 * bednicku presunut. Je potomok triedy HerneProstredie (dedi od tejto triedy) a
 * vie nacitat svoj obrazok.
 * 
 * @author Hoskovec
 */
public class MiestoKamDorucit extends HerneProstredie {
    
    /**
     * Parametricky konstruktor triedy MiestoKamDorucit, ktory dedi
     * parametre pozX a pozY.
     * 
     * @param pozX - suradnica x
     * @param pozY - suradnica y
     */
    public MiestoKamDorucit(int pozX, int pozY) {
        super(pozX, pozY);
    }
    
    /**
     * Tato metoda nacitava obrazok miesta, kam je potrebne presunut bednicku.
     * "Overriduje" danu metodu v triede HerneProstredie.
     * 
     * @return - obrazok miesta, kde treba dorucit bednicku
     */
    @Override
    public BufferedImage nacitajObrazok() {
        BufferedImage obrDoruceniaBednicky = null;
        try {
            obrDoruceniaBednicky = ImageIO.read(new File("dorucenieBednickySem.png"));
        } catch (IOException e) {
        }
        return obrDoruceniaBednicky;
    }
    
}

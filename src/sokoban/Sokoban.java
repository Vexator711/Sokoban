package sokoban;

import javax.swing.JFrame;

/**
 * Trieda Sokoban je hlavnou triedou hry Sokoban. Dedi od triedy JFrame,
 * vytvara okno, do ktoreho sa hra vykresluje.
 * 
 * @author Hoskovec
 */
public class Sokoban extends JFrame {
    
    private final int odsek = 20;

    /**
     * Bezparametricky konstruktor triedy Sokoban vola metodu na vytvorenie
     * okna, do ktoreho sa vykresli hra.
     */
    public Sokoban() {
        this.vytvorUI();
    }
    
    /**
     * Tato trieda vytvara okno JFrame, nastavuje mu rozne parametre - rozmery,
     * nazov, atd. Taktiez vytvara instanciu triedy Hra.
     */
    private void vytvorUI() {
        Hra hra = new Hra();
        add(hra);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(hra.getSirka() + this.odsek, hra.getVyska() + 2 * this.odsek);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
    }
    
    /**
     * Hlavna metoda, ktorou sa spusta cela hra.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Sokoban sokoban = new Sokoban();
        sokoban.setVisible(true);
    }
}

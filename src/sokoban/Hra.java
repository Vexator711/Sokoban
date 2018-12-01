package sokoban;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Trieda Hra nacita objekty (Bednicka, Stena, atd.) a vykresli ich. Tato 
 * trieda dedi od triedy JPanel. Tiez obsahuje vnorenu triedu OvladanieHry.
 * 
 * @author Hoskovec
 */
public class Hra extends JPanel {
    
    private int sirka = 0;
    private int vyska = 0;
    private final int medzera = 40;
    private final int odsek = 20;
    
    private Hrac sokoban;
    private Stena stena;
    private Bednicka bednicka;
    private MiestoKamDorucit semDorucit;
    
    private ArrayList<Stena> steny;
    private ArrayList<Bednicka> bednicky;
    private ArrayList<MiestoKamDorucit> miestaNaDorucenie;
    
    private final String hore = "hore";
    private final String dole = "dole";
    private final String dolava = "dolava";
    private final String doprava = "doprava";
    
    private boolean levelSpraveny = false;
    
    /**
     * Bezparametricky konstruktor vytvori ArrayListy danych objektov, vytvara
     * instanciu triedy, ktora berie vstup z klavesnice (aby bolo mozne
     * hru vobec ovladat) a nasledne vytvara hru.
     */
    public Hra() {
        this.steny = new ArrayList<Stena>();
        this.bednicky = new ArrayList<Bednicka>();
        this.miestaNaDorucenie = new ArrayList<MiestoKamDorucit>();
        
        addKeyListener(new OvladanieHry());
        setFocusable(true);
        this.vytvorHru();
    }

    public int getSirka() {
        return this.sirka;
    }

    public int getVyska() {
        return this.vyska;
    }
    
    /**
     * Tato metoda vytvara hru. Robi to tak, ze z lokalnej premennej 
     * "String level" nacitava objekty, ktore su v Stringu reprezentovane 
     * danymi znakmi (bodka, bodkociarka, atp.). Vytvorene objekty 
     * typov Stena, Bednicka a MiestoKamDorucit vklada do urcenych ArrayListov.
     */
    public void vytvorHru() {
        
        int x = this.odsek;
        int y = this.odsek;
        
        // pre nacitanie dalsieho levelu je potrebne skopirovat dany level
        // zo suboru levely.txt (nachadza sa v zlozke projektu)
        // a nahradit nim uz existujuci v lok. premennej "String level"
        
        String level =
              ".........\n"
            + ".;   §;..\n"
            + "..  § §@.\n"
            + "...§   §.\n"
            + ". . ;;  .\n"
            + "..  §; ;.\n"
            + ".........\n";
        
            
        for (int i = 0; i < level.length(); i++) {
            
            char znak = level.charAt(i);
            
            switch (znak) {
                case '\n':
                    y += this.medzera;
                    if (this.sirka < x) {
                        this.sirka = x;
                    }   
                    x = this.odsek;
                    break;
                case '@':
                    this.sokoban = new Hrac(x, y);
                    x += this.medzera;
                    break;
                case ' ':
                    x += this.medzera;
                    break;
                case '.':
                    this.stena = new Stena(x, y);
                    this.steny.add(this.stena);
                    x += this.medzera;
                    break;
                case ';':
                    this.semDorucit = new MiestoKamDorucit(x, y);
                    this.miestaNaDorucenie.add(this.semDorucit);
                    x += this.medzera;
                    break;
                case '§':
                    this.bednicka = new Bednicka(x, y);
                    this.bednicky.add(this.bednicka);
                    x += this.medzera;
                    break;
                default:
                    break;
            }
            this.vyska = y;
        }
        
    }
    
    /**
     * Tato metoda vykresluje hru pomocou GUI. Najskor prida vsetky prvky 
     * z ArrayListov typu Stena, MiestoKamDorucit a Bednicka a hraca do
     * jedneho ArrayListu. Nasledne sa tento ArrayList prehladava a podla 
     * toho, o aky objekt sa jedna, sa dany objekt vykresli s prislusnym
     * obrazkom na urcenej pozicii. Tato pozicia sa urci uz v predchadzajucej
     * metode "vytvorHru".
     * V pripade, ze premenna "levelSpraveny" vracia hodnotu true, vykresli
     * sa taktiez String, ktory hovori o vyhre, teda prejdeni levelu.
     * 
     * @param g 
     */
    public void vykresliHru(Graphics g) {
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        ArrayList<HerneProstredie> vsetkyObjekty = new ArrayList<HerneProstredie>();
        vsetkyObjekty.addAll(this.steny);
        vsetkyObjekty.addAll(this.miestaNaDorucenie);
        vsetkyObjekty.addAll(this.bednicky);
        vsetkyObjekty.add(this.sokoban);
        
        for (int i = 0; i < vsetkyObjekty.size(); i++) {
            HerneProstredie objekt = vsetkyObjekty.get(i);
            
            if (objekt instanceof Hrac) {
                g.drawImage(objekt.nacitajObrazok(), objekt.pozX() + 2, objekt.pozY() + 2, null);
            } else if (objekt instanceof Stena) {
                g.drawImage(objekt.nacitajObrazok(), objekt.pozX(), objekt.pozY(), null);
            } else if (objekt instanceof Bednicka) {
                g.drawImage(objekt.nacitajObrazok(), objekt.pozX(), objekt.pozY(), null);
            } else if (objekt instanceof MiestoKamDorucit) {
                g.drawImage(objekt.nacitajObrazok(), objekt.pozX(), objekt.pozY(), null);
            } 
        }
        
        if (this.levelSpraveny) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Vyhrali ste!", 125, 50);
        }
    }
    
    /**
     * "Overridnuta" metoda triedy JPanel. Vdaka nej sa cela hra zobrazi
     * na JPanel.
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.vykresliHru(g);
    }

    /**
     * Vnorena trieda OvladanieHry zabezpecuje, ze hru moze pouzivatel ovladat.
     * Na ovladanie hry sa pouzivaju sipky, stlacenim klavesu "R" sa level
     * restartuje.
     * Obsahuje metody, ktore overuju, ci je pohyb na dany smer vobec mozny,
     * teda ci nestoji v ceste stena, pripadne dve bednicky pri sebe, atd.
     * Tiez obsahuje metodu, ktora zistuje, ci uz nie je level spraveny, a
     * metodu na restartovanie levelu.
     * Na oznacenie atributov z triedy Hra bolo potrebne pouzit 
     * "Hra.this.<nazovAtributu>.
     * 
     * Dovodom na vnorenie tejto triedy bolo v mojom pripade to, ze takto moze
     * tato trieda vyuzivat metody aj od triedy KeyAdapter, aj od triedy
     * JPanel (metoda repaint). 
     */
    class OvladanieHry extends KeyAdapter {
        
        /**
         * "Overridnuta" metoda od triedy KeyAdapter. V pripade stlacenia
         * jednej zo sipok sa najprv pomocou dvoch dalsich metod overi, ci
         * je posun mozny, a ak je, tak sa sokoban posunie danym smerom.
         * V pripade stlacenia klavesu "R", sa level restartuje. Za kazdym 
         * vstupom od pouzivatela sa hra pomocou metody repaint prekresli.
         * 
         * @param e 
         */
        @Override
        public void keyPressed(KeyEvent e) {
            
            int klaves = e.getKeyCode();
        
            switch (klaves) {
                case KeyEvent.VK_LEFT:
                    if (this.checkSteny(Hra.this.sokoban, Hra.this.dolava)) {
                        return;
                    }
                    if (this.checkBednicky(Hra.this.dolava)) {
                        return;
                    }
                    Hra.this.sokoban.pohniSa(-Hra.this.medzera, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    if (this.checkSteny(Hra.this.sokoban, Hra.this.doprava)) {
                        return;
                    }
                    if (this.checkBednicky(Hra.this.doprava)) {
                        return;
                    }
                    Hra.this.sokoban.pohniSa(Hra.this.medzera, 0);
                    break;
                case KeyEvent.VK_UP:
                    if (this.checkSteny(Hra.this.sokoban, Hra.this.hore)) {
                        return;
                    }
                    if (this.checkBednicky(Hra.this.hore)) {
                        return;
                    }
                    Hra.this.sokoban.pohniSa(0, -Hra.this.medzera);
                    break;
                case KeyEvent.VK_DOWN:
                    if (this.checkSteny(Hra.this.sokoban, Hra.this.dole)) {
                        return;
                    }
                    if (this.checkBednicky(Hra.this.dole)) {
                        return;
                    }
                    Hra.this.sokoban.pohniSa(0, Hra.this.medzera);
                    break;
                case KeyEvent.VK_R:
                    this.restartLevelu();
                    break;
                default:
                    break;
            }
            repaint();
        }
        
        /**
         * Tato metoda zistuje, ci je nad, pod, nalavo alebo napravo od 
         * objektu typu HerneProstredie stena.
         * 
         * 
         * @param objekt - objekt triedy HerneProstredie
         * @param smer - hore, dole, dolava, doprava
         * @return 
         */
        public boolean checkSteny(HerneProstredie objekt, String smer) {
            switch (smer) {
                case hore:
                    for (int i = 0; i < Hra.this.steny.size(); i++) {
                        Stena sten = Hra.this.steny.get(i);
                        if (objekt.jeNadObjekt(sten)) {
                            return true;
                        }
                    }
                    return false;
                case dole:
                    for (int j = 0; j < Hra.this.steny.size(); j++) {
                        Stena sten = Hra.this.steny.get(j);
                        if (objekt.jePodObjekt(sten)) {
                            return true;
                        }
                    }
                    return false;
                case dolava:
                    for (int k = 0; k < Hra.this.steny.size(); k++) {
                        Stena sten = Hra.this.steny.get(k);
                        if (objekt.jeVlavoObjekt(sten)) {
                            return true;
                        }
                    }
                    return false;
                case doprava:
                    for (int l = 0; l < Hra.this.steny.size(); l++) {
                        Stena sten = Hra.this.steny.get(l);
                        if (objekt.jeVpravoObjekt(sten)) {
                            return true;
                        }
                    }
                    return false;
                default:
                    break;
            }
            return false;
        }
        
        /**
         * Tato, trochu komplexnejsia, metoda overuje, ci je mozny posun
         * bednicky do vsetkych styroch smerov. Najprv sa zisti, ci je pri
         * bednicke hrac - sokoban. Dalej, ak nie su vedla seba danym smerom 
         * dve bednicky a ak bednicke nebrani v ceste stena, bednicka sa 
         * posunie (sokoban ju potlaci :-) ) danym smerom na vzdialenost 
         * jedneho kliku od aktualnej pozicie.
         * Po kazdom presune bednicky sa metodou "jeLevelSpraveny" zistuje,
         * ci uz nie je nahodou level spraveny.
         * 
         * @param smer - hore, dole, dolava, doprava
         * @return 
         */
        public boolean checkBednicky(String smer) {
            
            switch (smer) {
                case hore:
                    for (int m = 0; m < Hra.this.bednicky.size(); m++) {
                        Bednicka bednick = Hra.this.bednicky.get(m);
                        
                        if (Hra.this.sokoban.jeNadObjekt(bednick)) {
                            for (int n = 0; n < Hra.this.bednicky.size(); n++) {
                                
                                Bednicka bed = Hra.this.bednicky.get(n);
                                if (!bednick.equals(bed)) {
                                    if (bednick.jeNadObjekt(bed)) {
                                        return true;
                                    }
                                }
                                if (this.checkSteny(bednick, Hra.this.hore)) {
                                    return true;
                                }
                            }
                            bednick.pohniSa(0, -Hra.this.medzera);
                            this.jeLevelSpraveny();
                        }
                    }
                    return false;
                case dole:
                    for (int o = 0; o < Hra.this.bednicky.size(); o++) {
                        Bednicka bednick = Hra.this.bednicky.get(o);
                        
                        if (Hra.this.sokoban.jePodObjekt(bednick)) {
                            for (int p = 0; p < Hra.this.bednicky.size(); p++) {
                                
                                Bednicka bed = Hra.this.bednicky.get(p);
                                if (!bednick.equals(bed)) {
                                    if (bednick.jePodObjekt(bed)) {
                                        return true;
                                    }
                                }
                                if (this.checkSteny(bednick, Hra.this.dole)) {
                                    return true;
                                }
                            }
                            bednick.pohniSa(0, Hra.this.medzera);
                            this.jeLevelSpraveny();
                        }
                    }
                    return false;
                case dolava:
                    for (int q = 0; q < Hra.this.bednicky.size(); q++) {
                        Bednicka bednick = Hra.this.bednicky.get(q);
                        
                        if (Hra.this.sokoban.jeVlavoObjekt(bednick)) {
                            for (int r = 0; r < Hra.this.bednicky.size(); r++) {
                                
                                Bednicka bed = Hra.this.bednicky.get(r);
                                if (!bednick.equals(bed)) {
                                    if (bednick.jeVlavoObjekt(bed)) {
                                        return true;
                                    }
                                }
                                if (this.checkSteny(bednick, Hra.this.dolava)) {
                                    return true;
                                }
                            }
                            bednick.pohniSa(-Hra.this.medzera, 0);
                            this.jeLevelSpraveny();
                        }
                    }
                    return false;
                case doprava:
                    for (int s = 0; s < Hra.this.bednicky.size(); s++) {
                        Bednicka bednick = Hra.this.bednicky.get(s);
                        
                        if (Hra.this.sokoban.jeVpravoObjekt(bednick)) {
                            for (int t = 0; t < Hra.this.bednicky.size(); t++) {
                                
                                Bednicka bed = Hra.this.bednicky.get(t);
                                if (!bednick.equals(bed)) {
                                    if (bednick.jeVpravoObjekt(bed)) {
                                        return true;
                                    }
                                }
                                if (this.checkSteny(bednick, Hra.this.doprava)) {
                                    return true;
                                }
                            }
                            bednick.pohniSa(Hra.this.medzera, 0);
                            this.jeLevelSpraveny();
                        }
                    }   break;
                default:
                    break;
            }
            return false;
        }

        /**
         * Tato metoda sluzi na zistenie, ci uz hrac vyhral - presiel level.
         * Najprv sa do premennej "pocetBedniciek" ulozi pocet objektov
         * ulozenych v ArrayListe typu Bednicka. Premenna "pocetUmiestnenychVCieli"
         * sa na zaciatku nastavi na 0. Nasledne sa zistuje, ci sa lubovolna 
         * bednicka nachadza na rovnakych suradniciach ako lubovolne miesto,
         * kam bednicku treba presunut. Ak su suradnice rovnake, premennej
         * "pocetUmiestnenychVCieli" sa pripocita 1.
         * No a nakoniec, ak sa pocet bedniciek v hre rovna poctu ulozenych
         * bedniciek na mieste, kam ich treba presunut, premenna "levelSpraveny"
         * sa nastavi na true a hra sa prekresli - repaint.
         * 
         */
        public void jeLevelSpraveny() {
            
            int pocetBedniciek = Hra.this.bednicky.size();
            int pocetUmiestnenychVCieli = 0;
            
            for (int i = 0; i < pocetBedniciek; i++) {
                Bednicka bednick = Hra.this.bednicky.get(i);
                for (int j = 0; j < pocetBedniciek; j++) {
                    MiestoKamDorucit ciel = Hra.this.miestaNaDorucenie.get(j);
                    
                    if (bednick.pozX() == ciel.pozX() && bednick.pozY() == ciel.pozY()) {
                        pocetUmiestnenychVCieli += 1;
                    }
                }
            }
            if (pocetBedniciek == pocetUmiestnenychVCieli) {
                Hra.this.levelSpraveny = true;
                repaint();
            }
        }
        
        /**
         * Tato jednoducha metoda restartuje level. Vymaze objekty z 
         * ArrayListov typu Bednicka, Stena a MiestoKamDorucit a zavola
         * metodu "vytvorHru".
         * V pripade, ze chceme restartnut level, ktory uz je spraveny, 
         * premenna "levelSpraveny" sa opat nastavi na false.
         */
        public void restartLevelu() {
            Hra.this.bednicky.clear();
            Hra.this.steny.clear();
            Hra.this.miestaNaDorucenie.clear();
            
            Hra.this.vytvorHru();
            if (Hra.this.levelSpraveny) {
                Hra.this.levelSpraveny = false;
            }
        }
    }
}


import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Guilherme
 */
public class StatusMenus {

    Image img, img2;
    TextField txtfield, txtfield2;
    int hptotal = 1, hpanterior = 1, mptotal = 1, mpanterior = 1, hp = 1, mp = 1;
    String lvl = "0";
    String vida = "0", mana = "0";
    Personagem p;
    int[] x = new int[4];
    int[] y = new int[4];
    int[] x1 = new int[4];
    int[] y1 = new int[4];

    public StatusMenus() {
        img2 = new ImageIcon(this.getClass().getResource("Sprites\\BarraVida5.png")).getImage();
    }

    public StatusMenus(int hp, int mp, int hp2, int mp2) {
        try {
            img2 = new ImageIcon(this.getClass().getResource("Sprites\\BarraVida5.png")).getImage();
            setHp(hp);
            setMp(mp);
            setHptotal(hp2);
            setMptotal(mp2);
            calcularVida();
            calcularMana();
        } catch (InterruptedException ex) {
            Logger.getLogger(StatusMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StatusMenus(Personagem p) {
        this.p = p;
        try {
            img2 = new ImageIcon(this.getClass().getResource("Sprites\\Outros\\BarraVida5.png")).getImage();
            setHp(p.getFicha().getHp());
            setMp(p.getFicha().getMp());
            setHptotal(p.getFicha().getHptotal());
            setMptotal(p.getFicha().getMptotal());
            calcularVida();
            calcularMana();
        } catch (InterruptedException ex) {
            Logger.getLogger(StatusMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void calcularVida() throws InterruptedException {

        if (this.hp > this.hptotal) {
            this.hptotal = this.hp;
        }

        int quantidade = (this.hp * 95) / this.hptotal;
        calculaStatusHP(quantidade);
        vida = this.hp + "/" + this.hptotal;
        this.hpanterior = hp;
    }

    public void calcularMana() throws InterruptedException {

        if (this.mp > this.mptotal) {
            this.mptotal = mp;
        }

        int quantidade = (mp * 95) / this.mptotal;
        calculaStatusMP(quantidade);
        this.mana = mp + "/" + this.mptotal;
        this.mpanterior = mp;

    }

    public void update() {
        setHp(p.getFicha().getHp());
        setHptotal(p.getFicha().getHptotal());
        setMp(p.getFicha().getMp());
        setMptotal(p.getFicha().getMptotal());
        try {
            calcularVida();
            calcularMana();
        } catch (InterruptedException ex) {
            Logger.getLogger(StatusMenus.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void calculaStatusHP(int hp) {
        this.x[0] = 72;
        this.x[1] = 68;
        this.x[2] = hp + 68;
        this.x[3] = hp + 72;
        this.y[0] = 62;
        this.y[1] = 71;
        this.y[2] = 71;
        this.y[3] = 62;
    }

    public void calculaStatusMP(int mp) {

        this.x1[0] = 67;
        this.x1[1] = 64;
        this.x1[2] = mp + 64;
        this.x1[3] = mp + 67;
        this.y1[0] = 72;
        this.y1[1] = 80;
        this.y1[2] = 80;
        this.y1[3] = 72;
    }

    public void calcularLvl(int lvl) {
        if (Integer.parseInt(this.lvl) > lvl) {
            return;
        } else {
            this.lvl = String.valueOf(lvl);
        }
    }

    public void pintarBarra(Graphics2D graphics2d) {
        try {
            calcularVida();
            calcularMana();
        } catch (InterruptedException ex) {
            Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        graphics2d.drawImage(this.img2, 20, 34, null);

        graphics2d.setColor(Color.RED);
        graphics2d.fillPolygon(this.x, this.y, 4);
        graphics2d.setColor(Color.BLUE);
        graphics2d.fillPolygon(this.x1, this.y1, 4);


        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(new Font("Arial Bold", Font.BOLD, 10));
        graphics2d.drawString(this.vida, 100, 69);
        graphics2d.drawString(this.mana, 100, 79);
        graphics2d.drawImage(this.img2, 20, 34, null);
        desenharLevel(graphics2d);
    }

    public void desenharLevel(Graphics2D graphics2d) {

        int lvl = Integer.parseInt(this.lvl);

        if (lvl < 10) {
            graphics2d.setColor(Color.decode("#996600"));
            graphics2d.drawString("lvl", 40, 60);
            graphics2d.setFont(new Font("Arial Bold", Font.BOLD, 18));
            graphics2d.drawString(this.lvl, 40, 75);

        } else if (lvl < 100) {
            graphics2d.setColor(Color.decode("#996600"));
            graphics2d.drawString("lvl", 40, 60);
            graphics2d.setFont(new Font("Arial Bold", Font.BOLD, 17));
            graphics2d.drawString(this.lvl, 38, 74);

        } else {
            graphics2d.setColor(Color.decode("#996600"));
            graphics2d.drawString("lvl", 40, 60);
            graphics2d.setFont(new Font("Arial Bold", Font.BOLD, 15));
            graphics2d.drawString(this.lvl, 33, 73);
        }
    }

    public int getHptotal() {
        return hptotal;
    }

    public void setHptotal(int hptotal) {
        this.hptotal = hptotal;
    }

    public int getMptotal() {
        return mptotal;
    }

    public void setMptotal(int mptotal) {
        this.mptotal = mptotal;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }
}

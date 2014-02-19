/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Yves
 */
public class Movimentos {

    private ArrayList<Sprite> Movimentos = new ArrayList();
    private Personagem p;
    private Sprite S;
    private int larguraInicial;
    private int alturaInicial;
    private int indiceAtaque;
    private Point.Float posiçãoInicial = new Point.Float();
    private int ladoBloqueado = -1;
    private Image image;
    private int c = 0; // contador de imagem atual
    private int c2 = 0; // contador de ciclos de imagem

    public Movimentos() {
    }

    public Movimentos(String s, Personagem p) {
        carregarMovimentos(s);
        this.p = p;
    }

    public void carregarMovimentos(String xmlFile) {
        //    System.out.println(this.Movimentos.size());
        this.Movimentos.clear();
        //  System.out.println(this.Movimentos.size());

        ArrayList<Image> Img = new ArrayList();
        Document d = lerxml(xmlFile);

        BufferedImage bimage = new BufferedImage(Integer.parseInt(d.getRootElement().getAttributeValue("width")),
                Integer.parseInt(d.getRootElement().getAttributeValue("height")),
                BufferedImage.TYPE_INT_ARGB_PRE);

        Graphics2D g2d = bimage.createGraphics();
        g2d.setClip(0, 0, bimage.getWidth(), bimage.getHeight());

        Image spritesheet = new ImageIcon(this.getClass().getResource("Sprites\\" + d.getRootElement().getAttributeValue("imagePath"))).getImage();
        g2d.drawImage(spritesheet, 0, 0, null);
        List<Element> i = d.getRootElement().getChildren();

        // carrega stand 1 =índice: 0=>
        for (int j = 0; j < 6; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stand1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        //<==

        // carrega stand 2 =índice: 1=>
        Img = new ArrayList();

        for (int j = 0; j < 6; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stand2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        // carrega walk1 =índice: 2=>
        Img = new ArrayList();
        for (int j = 0; j < 5; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("walk1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        //Carrega walk2 =índice: 3=>
        Img = new ArrayList();
        for (int j = 0; j < 5; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("walk2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);

        this.Movimentos.add(S);
        //<==
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        //Carrega jump1 =índice: 4=>
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("jump1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        // Carrega jump2 =índice: 5=>     
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("jump2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //System.out.println(this.Movimentos.size() + "/ " + Img.size());
        //Carrega attack1 = índice: 6=>
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stab1.1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        // System.out.println(this.Movimentos.size() + "/ " + Img.size());
        //Carrega attack2 = índice: 6=>
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stab1.2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //Carrega stabO1 = índice: 9=>
        Img = new ArrayList();
        for (int j = 0; j < 2; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("swing1.1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==

        //Carrega stabO1 = índice: 9=>
        Img = new ArrayList();
        for (int j = 0; j < 2; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("swing1.2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //Carrega stabO1 = índice: 10=>
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stabF.1 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
        //Carrega stabO2 = índice: 10=>
        Img = new ArrayList();
        for (int j = 0; j < 3; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("stabF.2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                }
            }
        }
        this.S = new Sprite(Img);
        this.Movimentos.add(S);
        //<==
    }

    public Image Jump(int lado) {
        if (lado == 0) {
            this.image = this.Movimentos.get(4).getImages().get(0);
        } else if (lado == 1) {
            this.image = this.Movimentos.get(5).getImages().get(0);
        }
        c++;
        return this.image;
    }

    public Image Stand(int lado) {
        if (c2 > 5) {
            c++;
            c2 = 0;
        }
        if (c > 4) {
            c = 0;
        }
        if (lado == 0) {
            this.image = this.Movimentos.get(0).getImages().get(c);
        } else if (lado == 1) {
            this.image = this.Movimentos.get(1).getImages().get(c);
        }
        c2++;
        return this.image;
    }

    public Image walkLeft() {
        if (c2 > 5) {
            c++;
            c2 = 0;
        }
        if (c > 3) {
            c = 0;
        }
        this.image = this.Movimentos.get(2).getImages().get(c);
        c2++;
        return this.image;
    }

    public Image walkRight() {
        if (c2 > 5) {
            c++;
            c2 = 0;
        }
        if (c > 3) {
            c = 0;
        }
        this.image = this.Movimentos.get(3).getImages().get(c);
        c2++;
        return this.image;
    }

    public Image cair(int lado) {
        if (lado == 0) {
            this.image = this.Movimentos.get(4).getImages().get(0);
        } else if (lado == 1) {
            this.image = this.Movimentos.get(5).getImages().get(0);
        }
        return this.image;
    }

//    public Image stabLeft(int lado) {
//        if (c2 > 5) {
//            c++;
//            c2 = 0;
//        }       if (c > Movimentos.get(6+lado).getImages().size()) {
//            c = 0;
//            p.atacandox = false;
//        }
//        System.out.println("Atacou!");
//        c2++;
//        return this.Movimentos.get(6 + i + lado).getImages().get(c);
//    }
    public Image atacar(int lado) {
        if (c2 > 5) {
            c++;
            c2 = 0;
        }
           System.out.println("c = " + c + " c2 = " + c2 +"ida"+ indiceAtaque);
        if (lado == 0 && this.c2 == 0) {
            //       System.out.println("Att posição");
            this.p.posicaoM.x = this.posiçãoInicial.x - (this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().get(c).getWidth(null) - larguraInicial);
            if (this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().get(c).getHeight(null) - alturaInicial > 0) {
                this.p.posicaoM.y = this.posiçãoInicial.y - (this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().get(c).getHeight(null) - alturaInicial);
            }
        }
        if (c > this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().size() - 1) {
            c = 0;
        }
        if (c == this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().size() / 2 && this.c2 == 3) {
            Colisão.colisaoPersonagem(this.p, p.getMonstros());
        }
        //  System.out.println("Atacou!");
        c2++;

        this.image = this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().get(c);
        if (this.c == this.Movimentos.get(6 + this.indiceAtaque + lado).getImages().size() - 1 && this.c2 == 5) {
            this.c = 0;
            this.c2 = 0;
            p.setAtacando(false);
            p.setPosicaoM(posiçãoInicial);
            // System.out.println("Ataque encerrado");
        }
        return this.image;

    }

    public void encerrarAtaque() {
        this.c = 0;
        this.c2 = 0;
        p.setAtacando(false);

    }

    public Document lerxml(String file) {

        File f = new File("src\\Sprites\\" + file);
        SAXBuilder sb = new SAXBuilder();
        Document d = null;
        try {
            d = sb.build(f);
            return d;


        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Movimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public ArrayList<Sprite> getMovimentos() {
        return Movimentos;
    }

    public int getLadoBloqueado() {
        return this.ladoBloqueado;
    }

    public void setLadoBloqueado(int ladoBloqueado) {
        this.ladoBloqueado = ladoBloqueado;
    }

    public Image getImage() {
        return image;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getLarguraInicial() {
        return larguraInicial;
    }

    public void setLarguraInicial(int larguraInicial) {
        this.larguraInicial = larguraInicial;
    }

    public int getAlturaInicial() {
        return alturaInicial;
    }

    public void setAlturaInicial(int alturaInicial) {
        this.alturaInicial = alturaInicial;
    }

    public int getIndiceAtaque() {
        return indiceAtaque;
    }

    public void setIndiceAtaque(int indiceAtaque) {
        this.indiceAtaque = indiceAtaque;
    }

    public Float getPosiçãoInicial() {
        return posiçãoInicial;
    }

    public void setPosiçãoInicial(Float posiçãoInicial) {
        this.posiçãoInicial = posiçãoInicial;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }
}

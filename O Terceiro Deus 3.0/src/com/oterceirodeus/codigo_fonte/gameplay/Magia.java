package com.oterceirodeus.codigo_fonte.gameplay;


import com.oterceirodeus.codigo_fonte.engine.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Henrique
 */
public class Magia {

    private ArrayList<Sprite> s = new ArrayList(); //BDD]
    private String nome;
    private Magia m;//BDD
    private boolean mobRange;//BDD
    private int tipo;//BDD
    private int lado;
    private int nextPoint;
    private int qtdDados;//BDD
    private Image im;
    private int c = 0;
    private int c2 = 0;
    private int a;//BDD
    private HashMap<String, Integer> focus = new HashMap<>();//BDD
    private float coeficienteAngular;
    private float alcanceX;//BDD
    private float alcanceY;//BDD
    private int repetições;//BDD
    private int dano;//BDD
    private int custo;//BDD
    private Personagem conjurador;
    private ArrayList<Personagem> alvos = new ArrayList();
    private ArrayList<Personagem> atingidos = new ArrayList();
    private Point.Float posicao = new Point.Float();
    private Point.Float[] trajetoria = new Point.Float[4];
    private float velocidade;
    private boolean executando = false;

    public Magia() {

        this.custo = 20;
        this.alcanceX = 90;
        this.alcanceY = 10;
        this.tipo = 1;
        this.velocidade = 15;
        this.repetições = 10;
        this.s = carregarMagia("..\\..\\assets\\sprites\\Magias\\FireDemon.xml");
        //// System.out.println("Fire Demon criado");
//        ArrayList<Image> Img = new ArrayList();
//        ImageIcon Icon = new ImageIcon();
//        Sprite sprite;
//        for (int j = 0; j < 4; j++) {
//            Icon = new ImageIcon(this.getClass().getResource("Sprites\\FD\\ball0_" + j + ".png"));
//            Img.add(Icon.getImage());
//        }
//        sprite = new Sprite(Img);
//        this.s.add(sprite);
//
//        ArrayList<Image> Img2 = new ArrayList();
//        ImageIcon Icon2 = new ImageIcon();
//        Sprite sprite2;
//        for (int j = 0; j < 4; j++) {
//            Icon2 = new ImageIcon(this.getClass().getResource("Sprites\\FD\\ball1_" + j + ".png"));
//            Img2.add(Icon2.getImage());
//        }
//        sprite2 = new Sprite(Img2);
//        this.s.add(sprite2);
//
//        ArrayList<Image> Img3 = new ArrayList();
//        ImageIcon Icon3 = new ImageIcon();
//        Sprite sprite3;
//        for (int j = 0; j < 9; j++) {
//            Icon3 = new ImageIcon(this.getClass().getResource("Sprites\\FD\\hit.0_" + j + ".png"));
//            Img3.add(Icon3.getImage());
//        }
//        sprite3 = new Sprite(Img3);
//        ArrayList<Sprite> ds = new ArrayList();
//        ds.add(sprite3);
//        ds.add(sprite3);
//
//        this.m = new Magia(ds, null, false, 0, 10, 0, 0, 20, 0, 0);

    }

    public Magia(String xml, Magia m, int tipo, int a, float alcanceX, int custo) {
        this.s = carregarMagia("Magias//" + xml + ".xml");
        this.im = this.s.get(0).getImages().get(0);
        this.nome = xml;
        this.m = m;
        this.repetições = 1;
        this.tipo = tipo;
        this.a = a;
        this.alcanceX = alcanceX;
        this.custo = custo;

    }

    public Magia(String xml, Magia m, boolean mobRange, int tipo, int a, float alcanceX, float alcanceY, int repetições, int custo, float velocidade) {
        this.s = carregarMagia("Magias//" + xml + ".xml");
        this.im = this.s.get(0).getImages().get(0);
        this.nome = xml;
        setFocus(2, 1, 0, 0, 0, 0);
        setQtdDados(2);
        this.m = m;
        this.mobRange = mobRange;
        this.tipo = tipo;
        this.a = a;
        this.alcanceX = alcanceX;
        this.alcanceY = alcanceY;
        this.repetições = repetições;
        this.custo = custo;
        this.velocidade = velocidade;
    }

    public ArrayList<Sprite> carregarMagia(String xmlFile) {
        ArrayList<Sprite> arraySprite = new ArrayList();
        ArrayList<Image> Img = new ArrayList();
        Document d = lerxml(xmlFile);

        //System.out.println(d.getRootElement().getName());

        BufferedImage bimage = new BufferedImage(Integer.parseInt(d.getRootElement().getAttributeValue("width")),
                Integer.parseInt(d.getRootElement().getAttributeValue("height")),
                BufferedImage.TYPE_INT_ARGB_PRE);

        //System.out.println(bimage.getWidth() + "/" + bimage.getHeight());

        Graphics2D g2d = bimage.createGraphics();
        g2d.setClip(0, 0, bimage.getWidth(), bimage.getHeight());

        Image spritesheet = new ImageIcon(this.getClass().getResource("..\\..\\assets\\sprites\\" + d.getRootElement().getAttributeValue("imagePath"))).getImage();
        g2d.drawImage(spritesheet, 0, 0, null);

        //System.out.println("bi criado com sucesso");

        List<Element> i = d.getRootElement().getChildren();
        // System.out.println(xmlFile + ":\n");
        // carrega side 1 =índice: 0=>
        for (int j = 0; j < 30; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("side1 (" + j + ")")) {
                    //System.out.println("Adicionou");
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                    // System.out.println("side1 (" + j + ")");
                }
            }
        }
        arraySprite.add(new Sprite(Img));

        //<==

        // carrega side 2 =índice: 1=>
        Img = new ArrayList();
        for (int j = 0; j < 30; j++) {
            for (Element e : i) {
                if (e.getAttributeValue("n").equals("side2 (" + j + ")")) {
                    Img.add(bimage.getSubimage(Integer.parseInt(e.getAttributeValue("x")),
                            Integer.parseInt(e.getAttributeValue("y")),
                            Integer.parseInt(e.getAttributeValue("w")),
                            Integer.parseInt(e.getAttributeValue("h"))));
                    // System.out.println("side2 (" + j + ")");
                }
            }
        }
        arraySprite.add(new Sprite(Img));
        //<==
        return arraySprite;
    }

    public Magia(ArrayList<Sprite> sprite, Magia m, boolean mobRange, int tipo, int a, float alcanceX, float alcanceY, int dano, int custo, float velocidade) {
        this.s = sprite;
        this.m = m;
        this.mobRange = mobRange;
        this.tipo = tipo;
        this.a = a;
        this.alcanceX = alcanceX;
        this.alcanceY = alcanceY;
        this.dano = dano;
        this.custo = custo;
        this.velocidade = velocidade;
    }

    public Magia(ArrayList<Sprite> sprite, Magia m, boolean mobRange, int tipo, int a, float alcanceX, float alcanceY, int dano, int custo, float velocidade, Point.Float p, int repetições) {

        this.posicao = p;
        this.s = sprite;
        this.m = m;
        this.mobRange = mobRange;
        this.tipo = tipo;
        this.a = a;
        this.alcanceX = alcanceX;
        this.alcanceY = alcanceY;
        this.dano = dano;
        this.custo = custo;
        this.velocidade = velocidade;
        this.repetições = repetições;
    }

    //terminado:
    public void calcularTrajetoria(Point.Float pontoInicial, float alcanceY, float alcanceX, int repetições, int lado) {
        this.trajetoria = new Point.Float[repetições];
        for (int i = 0; i < this.trajetoria.length; i++) {
            //System.out.println("Inicializou a trajetoria");
            this.trajetoria[i] = new Point.Float();
        }

        if (lado == 1) {
            //System.out.println("Trajetoria com lado = " + lado);
            this.trajetoria[0].x = pontoInicial.x + alcanceX;
            this.trajetoria[0].y = pontoInicial.y - alcanceY;
            //System.out.println("Trajetoria 0 criada");
            //System.out.println("x = " + this.trajetoria[0].x + " y = " + this.trajetoria[0].y);
            for (int i = 1; i < repetições; i++) {

                this.trajetoria[i].x = this.trajetoria[i - 1].x + alcanceX;
                if (i % 2 == 0) {
                    this.trajetoria[i].y = this.trajetoria[i - 1].y - (2 * alcanceY);
                } else {
                    this.trajetoria[i].y = this.trajetoria[i - 1].y + (2 * alcanceY);
                }
                //System.out.println("Trajetoria " + i + "criada");
                //System.out.println("x = " + this.trajetoria[i].x + " y = " + this.trajetoria[i].y);
            }
        } else if (lado == 0) {
            //System.out.println("Trajetoria com lado = " + lado);
            this.trajetoria[0].x = pontoInicial.x - alcanceX;
            this.trajetoria[0].y = pontoInicial.y - alcanceY;
            //System.out.println("Trajetoria 0 criada");
            //System.out.println("x = " + this.trajetoria[0].x + " y = " + this.trajetoria[0].y);
            for (int i = 1; i < repetições; i++) {
                this.trajetoria[i].x = this.trajetoria[i - 1].x - alcanceX;
                if (i % 2 == 0) {
                    this.trajetoria[i].y = this.trajetoria[i - 1].y - (2 * alcanceY);
                } else {
                    this.trajetoria[i].y = this.trajetoria[i - 1].y + (2 * alcanceY);
                }
                //System.out.println("Trajetoria " + i + "criada:");
                //System.out.println("x = " + this.trajetoria[i].x + " y = " + this.trajetoria[i].y);
            }

        }
    }

    public void iniciar(int lado, Point.Float p, int largura, int altura, Personagem conjurador) {
        this.lado = lado;
        this.conjurador = conjurador;
        this.executando = true;
        if (lado == 1) {//lado 1 = direita

            if (this.tipo == 0) {//tipo 0 = skill de area

                this.im = this.s.get(lado).getImages().get(0);
                this.posicao.x = p.x + largura + this.alcanceX;
                this.posicao.y = p.y - (this.im.getHeight(null) - altura);
                ////System.out.println("iniciou com lado = " + lado + " x = " + this.posicao.x + " y = " + this.posicao.y);
            } else if (this.tipo == 1) {//skill tipo 1 = skill com trajetoria

                this.im = this.s.get(lado).getImages().get(0);
                this.posicao.x = p.x + largura;
                this.posicao.y = p.y - ((this.im.getHeight(null) - altura) / 2);
                this.calcularTrajetoria(this.posicao, this.alcanceY, this.alcanceX, this.repetições, lado);
                this.coeficienteAngular = (this.posicao.y - this.trajetoria[this.nextPoint].y) / (this.posicao.x - this.trajetoria[this.nextPoint].x);
                ////System.out.println("iniciou com lado = " + lado + " x = " + this.posicao.x + " y = " + this.posicao.y);
            }
        } else if (lado == 0) {//lado 0 = esquerda
            if (this.tipo == 0) {//tipo 0 = skill de area

                this.im = this.s.get(lado).getImages().get(0);
                this.posicao.x = p.x - this.alcanceX - this.im.getWidth(null);
                this.posicao.y = p.y - (this.im.getHeight(null) - altura);
                ////System.out.println("iniciou com lado = " + lado + " x = " + this.posicao.x + " y = " + this.posicao.y);
            } else if (this.tipo == 1) {//skill tipo 1 = skill com trajetoria

                this.im = this.s.get(lado).getImages().get(0);
                this.posicao.x = conjurador.posicaoM.x - this.im.getWidth(null);
                this.posicao.y = p.y - ((this.im.getHeight(null) - altura) / 2);
                this.calcularTrajetoria(this.posicao, this.alcanceY, this.alcanceX, this.repetições, lado);
                this.coeficienteAngular = (this.posicao.y - this.trajetoria[this.nextPoint].y) / (this.posicao.x - this.trajetoria[this.nextPoint].x);
                ////System.out.println("iniciou com lado = " + lado + "x = " + this.posicao.x + "y = " + this.posicao.y);
            }
        }
    }
//fazer ajustes: e pensar num melhor metodo para pintar;

    public void executar() {
        // System.out.println("Execução:");
        if (this.c < this.s.get(lado).getImages().size()) {

            this.im = this.s.get(lado).getImages().get(this.c);
            ////System.out.println("executando DS:" + this.c + "/" + this.posicao.x + "/" + this.posicao.y);
            if (this.tipo == 0) {
                if (this.c == this.a) {
                    this.atingidos = Colisão.colisãoSkillArea(this, alvos);
                    for (Personagem a : this.atingidos) {
                        //R3DET.DanoMagiaInfringido(this, a.ficha);
                    }
                }
            } else if (this.tipo == 1) {
                if (this.posicao.equals(this.trajetoria[this.nextPoint])) {
                    //System.out.println("Proximo ponto");
                    if (this.nextPoint + 1 < this.trajetoria.length) {
                        //System.out.println("att coeficiente angular");
                        this.nextPoint++;
                        this.coeficienteAngular = (this.posicao.y - this.trajetoria[this.nextPoint].y) / (this.posicao.x - this.trajetoria[this.nextPoint].x);
                    } else {
                        this.executando = false;
                    }

                }
                ////System.out.println("Next point: "+this.nextPoint);

                if (this.lado == 1) {
                    if (this.posicao.x + this.velocidade > this.trajetoria[this.nextPoint].x) {
                        this.posicao.x = this.trajetoria[this.nextPoint].x;
                    } else {
                        this.posicao.x = this.posicao.x + this.velocidade;
                    }

                } else if (lado == 0) {
                    if (this.posicao.x - this.velocidade < this.trajetoria[this.nextPoint].x) {
                        this.posicao.x = this.trajetoria[this.nextPoint].x;
                    } else {
                        this.posicao.x = this.posicao.x - this.velocidade;
                    }

                }
                this.posicao.y = this.coeficienteAngular * (this.posicao.x - this.trajetoria[this.nextPoint].x) + this.trajetoria[this.nextPoint].y;
                Colisão.colisãoSkillTrajetoria(this, alvos);
            }
            if (c2 > 1) {
                c++;
                c2 = 0;
            }
            c2++;

        } else {
            if (this.tipo == 0) {
                this.encerrar();
            }

            c = 0;
        }
    }

    public void encerrar() {
        // System.out.println("Encerrou skill");
        this.nextPoint = 0;
        this.executando = false;
        if (this.m != null) {
            //System.out.println("DS iniciado");

            this.conjurador.executarSkill(this.m);
            if (this.lado == 1) {
                this.m.setPosicaoX((this.posicao.x + this.im.getWidth(null)) - (this.m.getIm().getWidth(null) / 2));
            } else if (this.lado == 0) {
                this.m.setPosicaoX(this.posicao.x - (this.m.getIm().getWidth(null) / 2));
            }

            this.m.setPosicaoY(this.posicao.y - (this.m.getIm().getHeight(null) / 2));
        }
    }

    public void setFocus(int ag, int te, int fo, int ar, int lu, int tr) {
        this.focus.put("Água", ag);
        this.focus.put("Terra", te);
        this.focus.put("Fogo", fo);
        this.focus.put("Ar", ar);
        this.focus.put("Luz", lu);
        this.focus.put("Trevas", te);
    }

    public int somaQtdDados() {
        Random r = new Random();
        int soma = 0;
        for (int i = 0; i < qtdDados; i++) {
            soma = soma + r.nextInt(6) + 1;
        }
        return soma;
    }

    public Document lerxml(String file) {

        File f = new File("src\\com\\oterceirodeus\\assets\\sprites\\" + file);
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

    public HashMap<String, Integer> getFocus() {
        return focus;
    }

    public void setFocus(HashMap<String, Integer> focus) {
        this.focus = focus;
    }

    public int getQtdDados() {
        return qtdDados;
    }

    public void setQtdDados(int qtdDados) {
        this.qtdDados = qtdDados;
    }

    public int getRepetições() {
        return repetições;
    }

    public void setRepetições(int repetições) {
        this.repetições = repetições;
    }

    public float getCoeficienteAngular() {
        return coeficienteAngular;
    }

    public void setCoeficienteAngular(float coeficienteAngular) {
        this.coeficienteAngular = coeficienteAngular;
    }

    public int getNextPoint() {
        return nextPoint;
    }

    public void setNextPoint(int nextPoint) {
        this.nextPoint = nextPoint;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public ArrayList<Sprite> getS() {
        return s;
    }

    public void setS(ArrayList<Sprite> s) {
        this.s = s;
    }

    public Float[] getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(Float[] trajetoria) {
        this.trajetoria = trajetoria;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Magia getM() {
        return m;
    }

    public void setM(Magia m) {
        this.m = m;
    }

    public boolean isMobRange() {
        return mobRange;
    }

    public void setMobRange(boolean mobRange) {
        this.mobRange = mobRange;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Personagem> getAtingidos() {
        return atingidos;
    }

    public void setAtingidos(ArrayList<Personagem> atingidos) {
        this.atingidos = atingidos;
    }

    public boolean isExecutando() {
        return executando;
    }

    public void setExecutando(boolean executando) {
        this.executando = executando;
    }

    public ArrayList<Personagem> getAlvos() {
        return alvos;
    }

    public Image getIm() {
        return im;
    }

    public void setIm(Image im) {
        this.im = im;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public float getAlcanceX() {
        return alcanceX;
    }

    public void setAlcanceX(float alcanceX) {
        this.alcanceX = alcanceX;
    }

    public float getAlcanceY() {
        return alcanceY;
    }

    public void setAlcanceY(float alcanceY) {
        this.alcanceY = alcanceY;
    }

    public Personagem getConjurador() {
        return conjurador;
    }

    public void setConjurador(Personagem conjurador) {
        this.conjurador = conjurador;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public Float getPosicao() {
        return posicao;
    }

    public void setPosicaoX(float x) {
        this.posicao.x = x;
    }

    public void setPosicaoY(float y) {
        this.posicao.y = y;
    }

    public void setPosicao(Point.Float posicao) {
        this.posicao = posicao;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public void setAlvosP(ArrayList<Personagem> p) {
        this.alvos = p;
    }

    public void setAlvos(ArrayList<NPC> monstros) {
        for (NPC n : monstros) {
            this.alvos.add((Personagem) n);
        }

    }
}

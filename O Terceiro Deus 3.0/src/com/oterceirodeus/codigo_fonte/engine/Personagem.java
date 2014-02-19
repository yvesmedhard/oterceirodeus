package com.oterceirodeus.codigo_fonte.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.oterceirodeus.codigo_fonte.gameplay.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

/**
 * @author Yves
 *
 */
public class Personagem {

    protected Ficha ficha;
    protected Movimentos movimentos;
    protected Cenario c;
    protected Inventario inventario;
    protected Image image;
    protected Point.Float velocidade = new Point.Float();
    public Point.Float posicaoM = new Point.Float();
    protected ArrayList<Magia> skills = new ArrayList();
    protected ArrayList<Magia> executando = new ArrayList();
    protected ArrayList<Personagem> monstros = new ArrayList();
    protected int lado = 1;
    protected float normal = 0f;
    protected boolean atacando = false;
    protected boolean ocupado = true;
    private Terreno plat;

    public Personagem() {
    }

    public Personagem(Ficha f, String movimentos) {

        this.ficha = f;
        this.movimentos = new Movimentos(movimentos, this);

    }

    public Personagem(Ficha f, Cenario c, String movimentos, ArrayList<Magia> a) {

        // System.out.println(this.skills.size());
        this.posicaoM = new Point.Float(0, 0);
        this.executando = a;
        this.ficha = f;
        this.c = c;
        this.movimentos = new Movimentos(movimentos, this);
        this.image = this.movimentos.getMovimentos().get(0).getImages().get(0);
//        this.inventario = new Inventario(0, 0, 3);
    }

    public void update(long gametime) {
        float time = 1f;

        if (this.posicaoM.y == 0) {
            this.velocidade.y = 0;
        }
        if (estaDentro(this.posicaoM.x, this.posicaoM.y)) {
            this.posicaoM.x = (this.posicaoM.x + (this.velocidade.x * time)); // formula: S = S0 + vt
            this.posicaoM.y = (this.posicaoM.y + (this.velocidade.y * time) + (float) (((getC().getGravidadedomapa() - normal) * Math.pow(time, 2)) * 0.5f));  // formula S = S0 + vt + at^2/2
            if (this.velocidade.y >= 4f) {
                this.velocidade.y = 4f;
            } else {
                this.velocidade.y = (this.velocidade.y + ((getC().getGravidadedomapa() - normal) * time)); // formula:  V = V0 + at
            }
        }
        testes();
//        System.out.println("Posição no mapa: (" + posicaoM.x + ";" + posicaoM.y + ")");
//        System.out.println("Velocidade: (" + velocidade.x + ";" + velocidade.y + ")");
//        System.out.println("Gravidade / normal: (" + getC().getGravidadedomapa() + ";" + normal + ")");

    }

// Inicio dos testes ==>
    public void testes() {
//Teste Stand
        if (this.atacando) {
            this.image = this.movimentos.atacar(lado);
        } else {
            if (velocidade.x == 0) {
                setImage(movimentos.Stand(lado));
            }
//Teste Walk Right (teste de permanencia em plataforma e colisão com personagem)
            if (velocidade.x > 0) {
                long i1 = System.currentTimeMillis();

                if (!Colisão.colisaoTerrenoX(this, c.getPlataformas())) {
                    setNormal(0);
                    setOcupado(true);
                    //  System.out.println(i1 -= System.currentTimeMillis());
                }
//            if (colisaopersonagem) {
//                movimentos.setLadoBloqueado(1);
//            } else {
                if (!ocupado && !atacando) {
                    setImage(movimentos.walkRight());
                }
            }
//Teste Walk Left (teste de permanencia em plataforma e colisão com personagem)
            if (velocidade.x < 0) {
                if (!Colisão.colisaoTerrenoX(this, c.getPlataformas())) {
                    setNormal(0);
                    setOcupado(true);
                }
//            if (colisaopersonagem) {
//                movimentos.setLadoBloqueado(0);
//            } else {
                if (!ocupado) {
                    setImage(movimentos.walkLeft());
                }
            }
//Teste Jump
            if (this.velocidade.y < 0) {
                this.setNormal(0);
                setImage(this.movimentos.Jump(lado));
            }
//Teste de Queda ( inclui imagem de queda, e colisão com terreno
            if (this.velocidade.y > 0) {
                setImage(movimentos.cair(lado));
                if (Colisão.colisaoTerrenoY(this, getC().getPlataformas())) {
                    setNormal(getC().getGravidadedomapa());
                    this.velocidade.y = 0;
                    this.setOcupado(false);
                }
            }
            getMovimentos().setLadoBloqueado(2);

            if (this.posicaoM.x <= 0) {
                getMovimentos().setLadoBloqueado(0);
                posicaoM.x = 0;
            }
            if (this.posicaoM.x >= (((getC().getMapa().getWidth()) * getC().getMapa().getTileWidth()) - image.getWidth(null))) {
                getMovimentos().setLadoBloqueado(1);
                this.posicaoM.x = (((getC().getMapa().getWidth()) * getC().getMapa().getTileWidth()) - image.getWidth(null));
            }
            if (this.posicaoM.y >= (((getC().getMapa().getHeight()) * getC().getMapa().getTileHeight()) - image.getHeight(null))) {
                this.posicaoM.y = (((getC().getMapa().getHeight()) * getC().getMapa().getTileHeight()) - image.getHeight(null));
                this.velocidade.y = (0);
                this.ocupado = false;
            }
        }
    }

// Fim dos testes ==>
    public void tomouDano(int dano) {
        getFicha().setHp(getFicha().getHp() - dano);
    }

    public void usouMana(int dano) {
        getFicha().setMp(getFicha().getMp() - dano);
    }

    public void executarAtaque(int i) {
        this.movimentos.setIndiceAtaque(i);
        this.movimentos.setLarguraInicial(this.image.getWidth(null));
        this.movimentos.setAlturaInicial(this.image.getHeight(null));
        this.movimentos.setC(0);
        this.movimentos.setC2(0);
        this.movimentos.getPosiçãoInicial().x = this.posicaoM.x;
        this.movimentos.getPosiçãoInicial().y = this.posicaoM.y;
        this.image = this.movimentos.atacar(lado);

    }

    public void executarSkill(Magia m) {
        m.setExecutando(true);
        this.executando.add(m);
        this.executando.get(this.executando.size() - 1).iniciar(this.lado, this.posicaoM, this.image.getWidth(null), this.image.getHeight(null), this);;
    }

    public void executarSkill(int i) {
        if (this.ficha.getMp() >= this.skills.get(i).getCusto()) {
            //System.out.println("Personagem executou DS/"+this.skills.get(i).getCusto());
            this.ficha.setMp(this.ficha.getMp() - this.skills.get(i).getCusto());
            //this.skills.get(i).setExecutando(true);
            Magia m = this.skills.get(i);
            //m.iniciar(this.lado, this.posicaoM, this.image.getWidth(null), this.image.getHeight(null), this);
            this.executando.add(m);
            this.executando.get(this.executando.size() - 1).setAlvosP(monstros);
            this.executando.get(this.executando.size() - 1).iniciar(this.lado, this.posicaoM, this.image.getWidth(null), this.image.getHeight(null), this);
            //this.executando.get(this.executando.size() - 1).iniciar(this.lado, this.posicaoM, this.image.getWidth(null), this.image.getHeight(null), this);
            //   System.out.println("skill iniciada");
        }

    }

    public boolean estaDentro(float x, float y) {
        if (x >= 0 && (x + image.getWidth(null)) <= (c.getMapa().getWidth() * c.getMapa().getTileWidth()) && (y + image.getHeight(null)) <= (c.getMapa().getHeight() * c.getMapa().getTileHeight())) {
            return true;
        } else {
            return false;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, (int) this.posicaoM.x, (int) this.posicaoM.y, null);
        //System.out.println("draw realizado");
    }

    public ArrayList<Magia> getExecutando() {
        return executando;
    }

    public void setExecutando(ArrayList<Magia> executando) {
        this.executando = executando;
    }

    public ArrayList<Magia> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Magia> skills) {
        this.skills = skills;
    }

    public Float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Float velocidade) {
        this.velocidade = velocidade;
    }

    public void setVelocidade(float x, float y) {
        this.velocidade.x = x;
        this.velocidade.y = y;
    }

    public void setVelocidadeX(float x) {
        this.velocidade.x = x;
    }

    public void setVelocidadeY(float y) {
        this.velocidade.y = y;
    }

    public Float getPosicaoM() {
        return posicaoM;
    }

//    public void setPosicaoM(Float posicaoM) {
//        this.posicaoM.x = posicaoM.x;
//        this.posicaoM.y = posicaoM.y;
//    }
    public void setPosicaoM(Float posicaoM) {
        this.posicaoM = posicaoM;
    }

    public void setPosicao(float x, float y) {
        this.posicaoM.x = x;
        this.posicaoM.y = y;
    }

    public void setPosicaoX(float x) {
        this.posicaoM.x = x;
    }

    public void setPosicaoY(float y) {
        this.posicaoM.y = y;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setAlvos(ArrayList<NPC> monstros) {
        for (NPC n : monstros) {
            this.monstros.add((Personagem) n);
        }
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isAtacando() {
        return atacando;
    }

    public void setAtacando(boolean atacando) {
        this.atacando = atacando;
    }

    public Cenario getC() {
        return c;
    }

    public void setC(Cenario c) {
        this.c = c;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public Movimentos getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(Movimentos movimentos) {
        this.movimentos = movimentos;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public float getNormal() {
        return normal;
    }

    public void setNormal(float normal) {
        this.normal = normal;
    }

    public Terreno getPlat() {
        return plat;
    }

    public void setPlat(Terreno plat) {
        this.plat = plat;
    }

    public ArrayList<Personagem> getMonstros() {
        return monstros;
    }
    
}

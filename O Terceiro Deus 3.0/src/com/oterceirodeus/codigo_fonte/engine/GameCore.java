package com.oterceirodeus.codigo_fonte.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import com.oterceirodeus.codigo_fonte.gameplay.*;

/**
 *
 * @author Yves
 */
public class GameCore {

    private MetodosdeEntrada me;
    private Personagem p;
    private ArrayList<NPC> npcs = new ArrayList();
    private Cenario c;
    private Tela screen;
    private Camera cam;
    private Player player;
    private boolean start = false;
    private boolean quit = false;
    private boolean fade = false;
    private float opacity = 0.01f;
    private StatusMenus bds;
    private Image loadscreen;
    private MapaMundo mapadomundo;
    private String mapadomundopadrao;
    private ArrayList<Magia> skills = new ArrayList();

    public GameCore() throws HeadlessException {
        //Inicializa Valores padrão

        loadscreen = new ImageIcon(this.getClass().getResource("..\\..\\assets\\sprites\\Outros\\Load.png")).getImage();
        mapadomundopadrao = "MapaDoReinado.tmx";

        //inicializa cenario metodos de entrada personaem, tela e Mapa do Mundo
        c = new Cenario(npcs, skills);
        me = new MetodosdeEntrada();
        screen = new Tela();
        cam = new Camera();
        mapadomundo = new MapaMundo(me, c);
        player = new Player();


        p = new Personagem(new Ficha(), c, "Movimentos\\Humano.xml", skills);
        
        p.getSkills().add(new Magia("FireDemon", new Magia("Explosion", null, 0, 3, 0, 0), false, 1, 0, 90, 10, 10, 2, 15));
        p.getSkills().add(new Magia("IceFall", null, 0, 6, 0, 5));
        p.getSkills().add(new Magia("Eruption", null, 0, 2, 70, 2));
        p.getSkills().add(new Magia("Corckscrew", null, true, 1, 0, 400, 0, 2, 7, 15));
        p.getSkills().add(new Magia("Genesis", null, 0, 13, 150, 4));

        bds = new StatusMenus(p);

        //Set valores iniciais para cada classe
        //Player
        c.setPlayer(this.player);
        mapadomundo.setPlayer(player);
        //<==
        c.setPrincipal(p);
        cam.setP(p);
        me.setP(p);
        me.setMdm(mapadomundo);
        me.setGc(this);
        this.screen.setKeyListener(me);
        this.screen.addMouseListener(me);
        this.screen.addMouseMotionListener(me);
        this.screen.addMouseWheelListener(me);
        mapadomundo.loadmapa(mapadomundopadrao);
        player.stopBackMusic();


    }

    public void gameLoop() {
        //Inicia o loop do jogo; condição Start = true (Necessário para criação de ficha)
        while (!start) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println("saiu do loop start");
        //deixa a tela visivel
        screen.setVisible(true);
        //Envia a tela para o modo full Screen
        this.screen.toFullscreen8x6();
        //cria a Buffer Strategy
        this.screen.setBStrategy();
        //Inicia o loop do jogo; condição p.quit = false (alterar para uma variavel da classe Game Core<Alterado!>)

        while (!isQuit()) {
            //Atualiza o jogo logicamente
            updateGame();
            //Pinta a tela do jogo
            drawGame();
            //Normalização da framerate
            try {
                Thread.sleep(42);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameCore.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("Loop Ativo");
        }
        //System.out.println("Loop Desativado");
        this.player.stopBackMusic();
        this.player.stopOtherMusic();
        this.screen.toWindow();
        this.screen.dispose();
    }

    public void inciarFicha() {
        final GameCore mf = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CriacaodoPersonagem(mf).setVisible(true);
            }
        });
    }

    public void updateGame() {
        if (mapadomundo.isAtivado() || c.isSair() || c.isLoading()/*|| p.getInventario().isInInventario()*/) {
        } else {
            //  Faz o update do personagem;
            p.update(1);
            cam.atualizarCamera();
            bds.update();
            //    Faz o update do npc (teste)
            for (int i = 0; i < this.c.getNpcArray().size(); i++) {
                //  System.out.println("chegou aki");
                this.c.getNpcArray().get(i).setJogador(p);
                this.c.getNpcArray().get(i).update(1);
                if (this.c.getNpcArray().get(i).getFicha().getHp() <= 0) {
                    this.c.getNpcArray().remove(i);
                }
            }
            if (p.getFicha().getHp() <= 0) {
                c.setSair(true);
                p.getC().setLoading(true);
                p.getFicha().setHp(p.getFicha().getHptotal());
                p.getFicha().setMp(p.getFicha().getMptotal());
                mapadomundo.setAtivado(true);
                mapadomundo.getPlayer().setBackMusic(mapadomundo.getMapaDoMundo().getProperties().getProperty("BackMusic")).play();
                p.getC().setSair(false);
                p.getC().setLoading(false);
            }
        }
    }

    public void drawGame() {
        if (c.isSair()) {
            Graphics2D g2d = (Graphics2D) screen.getBstrategy().getDrawGraphics();
            g2d.setColor(new Color(0f, 0f, 0f, 0.3f));
            g2d.fillRect(0, 0, 800, 600);
            g2d.setColor(Color.white);
            if (p.getFicha().getHp() > 0) {
                g2d.drawString("Deseja Sair?   Esc = Cancelar     Enter = Sair", 245, 290);
            }
            screen.getBstrategy().show();
        } else if (c.isLoading()) {
            Graphics2D g2d = (Graphics2D) screen.getBstrategy().getDrawGraphics();
            g2d.drawImage(this.loadscreen, 0, 0, null);
            player.stopBackMusic();
            player.stopOtherMusic();
            screen.getBstrategy().show();

        } else if (mapadomundo.isAtivado()) {
            Graphics2D g2d = (Graphics2D) screen.getBstrategy().getDrawGraphics();
            g2d.setClip(0, 0, 800, 600);
            mapadomundo.pintarMapa(g2d);
            screen.getBstrategy().show();

        }
//        else if (p.getInventario().isInInventario()) {
//            Graphics2D g2d = (Graphics2D) screen.getBstrategy().getDrawGraphics();
//            g2d.setClip(0, 0, 800, 600);
//            p.getInventario().pintar(g2d);
//            screen.getBstrategy().show();
//        } 
        else {
            //Cria o graphics2d baseado no back Buffer
            Graphics2D g2d1 = (Graphics2D) screen.getBstrategy().getDrawGraphics();
            //Cria a bufferedimage baseada no mapa
            BufferedImage bi = new BufferedImage(c.getMapa().getWidth() * c.getMapa().getTileWidth(), c.getMapa().getHeight() * c.getMapa().getTileHeight() + 4, 1);
            //System.out.println("Tamanho da bi: " + bi.getWidth() + ";" + bi.getHeight());
            //Gera o graphics2d a partir da bufferedimage(comodidade)
            Graphics2D g2d = bi.createGraphics();
            //Seta o clip do graphics (necessário para o draw do ortoonal renderer/ tiled
            g2d.setClip(0, 0, bi.getWidth(), bi.getHeight());
            //Limpa a tela
            g2d.clearRect(0, 0, bi.getWidth(), bi.getHeight());
            //Pinta a tilelayer do mapa
            c.pintarlayer(g2d, 0);
            //pinta a map object do mapa (testes)
            //c.pintarMapObject(g2d, c.getMapobjectArray());
            //pinta os personagens
            for (NPC npc : c.getNpcArray()) {
                npc.draw(g2d);
            }
            p.draw(g2d);
            //´pinta segunda layer;
            c.pintarlayer(g2d, 2);


            //pinta a skill (Beta)
            for (int i = 0; i < this.skills.size(); i++) {
                g2d.drawImage(this.skills.get(i).getIm(), (int) this.skills.get(i).getPosicao().x, (int) this.skills.get(i).getPosicao().y, null);
                if (this.skills.get(i).isExecutando()) {
                    System.out.println("Skill " + i + " executada");
                    this.skills.get(i).executar();
                } else {
                    System.out.println("Skill " + i + " removida");
                    this.skills.get(i).setNextPoint(0);
                    this.skills.get(i).encerrar();
                    this.skills.remove(i);
                }
            }
            //dispensa o Graphics2d da bufferedimge (é necessário?)
            g2d.dispose();
            //pinta a imagem que sera exibida na tela no backbuffer baseada na posição da camera
            g2d1.drawImage(bi.getSubimage((int) cam.getPosicaoT().x, (int) cam.getPosicaoT().y, 800, 600), 0, 0, null);
            //Pinta a barra de vida/mana (possivel pinta a interface)
            bds.pintarBarra(g2d1);
            //pinta na tela final com o backbuffer (double buffer)
            screen.getBstrategy().show();
        }
    }

    public void setP(Ficha ficha) {
        this.p.setFicha(ficha);
        System.out.println(ficha.getRaca().getNome() + "/ " + p.ficha.getRaca().getNome());
        System.out.println(ficha.getClasse().getNome() + "/ " + p.ficha.getClasse().getNome());
        switch (p.ficha.getRaca().getNome()) {

            case ("Humano"):
                switch (p.ficha.getClasse().getNome()) {
                    case ("Guerreiro"):
                        p.movimentos.carregarMovimentos("..\\..\\assets\\sprites\\Movimentos\\Humano.xml");
                        System.out.println("Guerreiro humano");
                        break;
                    case ("Mago"):
                        p.movimentos.carregarMovimentos("..\\..\\assets\\sprites\\Movimentos\\Mage.xml");
                        System.out.println("mago humano");
                        break;
                }
                break;
            case ("Elfo"):
                switch (p.ficha.getClasse().getNome()) {
                    case ("Guerreiro"):
                        p.movimentos.carregarMovimentos("..\\..\\assets\\sprites\\Movimentos\\Elf-Warrior-Normal.xml");
                        System.out.println("Guerreiro elfo");
                        break;
                    case ("Mago"):
                        p.movimentos.carregarMovimentos("..\\..\\assets\\sprites\\Movimentos\\Mage.xml");
                        System.out.println("mago humano");
                        break;
                }

        }
        mapadomundo.setAtivado(true);
        c.setLoading(false);
        player.playBackMusic();
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean isQuit() {
        return quit;
    }

    public boolean isFade() {
        return fade;
    }

    public void setFade(boolean fade) {
        this.fade = fade;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public Cenario getC() {
        return c;
    }

    public Camera getCam() {
        return cam;
    }

    public StatusMenus getBds() {
        return bds;
    }
}

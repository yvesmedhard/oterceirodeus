package com.oterceirodeus.codigo_fonte.gameplay;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.oterceirodeus.codigo_fonte.engine.*;
import jaco.mp3.player.MP3Player;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Yves
 */
public class MenuInicial extends JFrame implements KeyListener {

    private Graphics2D g2d;
    private Image image;
    private String opcao = "New Game";
    private int selecao = 0;
    private MP3Player mp3p;
    private boolean quit = false;
    private GameCore mf3d;

    public MenuInicial() throws HeadlessException {
        this.setLayout(null);
        this.setBounds(300, 150, 776, 502);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setOpacity(0);
        this.addKeyListener(this);
        mp3p = new MP3Player(new File("src\\com\\oterceirodeus\\assets\\audio\\dearlybeloved.mp3"));
        this.g2d = (Graphics2D) getGraphics();
        this.image = new ImageIcon(this.getClass().getResource("..\\..\\assets\\sprites\\Outros\\MenuInicial.png")).getImage();
        Font f = new Font("Arial Bold", 65, 30);
        this.g2d.setFont(f);
    }
    public int gameloop() {

        mp3p.play();
        mp3p.setRepeat(true);
        this.fadeIn();
        while (!quit) {

            this.g2d.drawImage(image, 0, 0, null);
            this.g2d.drawString(opcao, 400, 435);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Erro no loop : " + ex);
            }
        }
        mp3p.stop();
        return selecao;
    }

    public void fadeIn() {
        for (float i = 0; i < 1.0f; i += 0.007f) {
            try {
                Thread.sleep(42);
                this.g2d.drawImage(image, 0, 0, null);
                this.setOpacity(i);
                //System.out.println(this.getOpacity());
            } catch (Exception ex) {
                System.out.println("Erro no loop do FadeIn: " + ex);
                this.setOpacity(.99f);
            }
        }
        this.setOpacity(.99f);
    }

    public void select(int i) {
        if (i == 3) {
            selecao = 2;
            quit = true;
            return;
        }
        g2d.drawImage(image, 0, 0, null);
        switch (selecao) {
            case (0):
                selecao += i;
                opcao = "New Game";
                break;
            case (1):
                selecao += i;
                opcao = "Continue";
                break;
            case (2):
                selecao += i;
                opcao = "Exit";
                break;
        }
        if (selecao == -1) {
            selecao = 2;
        }
        if (selecao == 3) {
            selecao = 0;
        }
        switch (selecao) {
            case (0):
                opcao = "New Game";
                break;
            case (1):
                opcao = "Continue";
                break;
            case (2):
                opcao = "Exit";
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ENTER):
                quit = true;
                break;
            case (KeyEvent.VK_UP):
                select(-1);
                break;
            case (KeyEvent.VK_DOWN):
                select(1);
                break;
            case (KeyEvent.VK_ESCAPE):
                select(3);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //   throw new UnsupportedOperationException("Not supported yet.");
    }
}

package com.oterceirodeus.codigo_fonte.engine;


import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class Tela extends JFrame {

    private final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private final DisplayMode olddisplaymode = gd.getDisplayMode();
    private final DisplayMode newdisplaymode = new DisplayMode(800, 600, 32, 60);
    private BufferStrategy bstrategy;

    public Tela() throws HeadlessException {
        this.setLayout(null);
        this.setBounds(0, 0, 800, 600);
        this.setUndecorated(true);
    }

    public void setBStrategy() {
        this.createBufferStrategy(2);
        bstrategy = this.getBufferStrategy();
    }

    public void setKeyListener(MetodosdeEntrada keylistener) {
        this.addKeyListener(keylistener);
    }

    public void telaVisible(boolean visible) {
        this.setVisible(visible);
    }

    public boolean dChangeSuported() {
        return gd.isDisplayChangeSupported();
    }

    public void toFullscreen8x6() {
        try {
            gd.setFullScreenWindow(this);
            gd.setDisplayMode(newdisplaymode);
        } catch (Exception ex) {
            System.out.println("Erro ao Ir para modo FullScreen com resolução 800 x 600: " + ex);
            toWindow();
        }
    }

    public void resetScreen() {
        try {
            gd.setDisplayMode(olddisplaymode);
        } catch (Exception ex) {

            System.out.println("Erro ao Ir para modo FullScreen na resolução nativa: " + ex);
        }
    }

    public void toWindow() {
        try {
            gd.setFullScreenWindow(null);
        } catch (Exception ex) {
            System.out.println("Erro ao sair do modo FullScreen: " + ex);
        }
    }

    public void setBounds() {
        this.setBounds(0, 0, gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
    }

    public BufferStrategy getBstrategy() {
        return bstrategy;
    }

    public GraphicsDevice getGd() {
        return gd;
    }
}
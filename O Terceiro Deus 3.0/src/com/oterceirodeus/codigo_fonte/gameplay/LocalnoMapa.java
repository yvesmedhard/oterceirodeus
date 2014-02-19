package com.oterceirodeus.codigo_fonte.gameplay;


import java.awt.Dimension;
import java.awt.Point;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class LocalnoMapa {

    private int x, y, w, h;
    private String nome;
    private Properties properties;



    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public Point getPosicao() {
        return new Point(this.x, this.y);
    }
    
    public Dimension getSize(){
        return new Dimension(this.w, this.h);
    }

    public int getPosicaoX() {
        return this.x;
    }

    public int getPosicaoY() {
        return this.y;
    }

    public int getHeight() {
        return this.h;
    }

    public int getWidth() {
        return this.w;
    }
}

package com.oterceirodeus.codigo_fonte.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yves
 */
import com.oterceirodeus.codigo_fonte.gameplay.*;
public class Main {

    public static void main(String[] args) {
        GameCore m43d = new GameCore();
        MenuInicial mi = new MenuInicial();
        int i = mi.gameloop();
        mi.dispose();
        if(i == 0 ){
            m43d.inciarFicha();
            m43d.gameLoop();
        }        
    }
}

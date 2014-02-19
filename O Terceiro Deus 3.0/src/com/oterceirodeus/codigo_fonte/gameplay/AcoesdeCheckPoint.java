package com.oterceirodeus.codigo_fonte.gameplay;


import java.awt.Image;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;
import tiled.core.MapLayer;
import tiled.core.Tile;
import tiled.core.TileLayer;
import com.oterceirodeus.codigo_fonte.engine.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class AcoesdeCheckPoint {

    String função;
    String acao;

    public void executar(Personagem p, int obj, MapaMundo mapadomundo) {
        função = p.getC().getCheckpointArray().get(obj).getFuncao();
        acao = p.getC().getCheckpointArray().get(obj).getProperties().getProperty("MapName");
        switch (função) {
            case ("NovaFase"):
                p.getC().setLoading(true);
                p.setPosicao(100, 100);
                p.getC().novoCenario(acao);
                p.getC().setLoading(false);
                break;

            case ("SairMapa"):
                p.getC().setSair(true);
                p.getC().setLoading(true);
                p.getFicha().setHp(p.getFicha().getHptotal());
                p.getFicha().setMp(p.getFicha().getMptotal());
                mapadomundo.setAtivado(true);
                mapadomundo.getPlayer().setBackMusic(mapadomundo.getMapaDoMundo().getProperties().getProperty("BackMusic")).play();
                p.getC().setSair(false);
                p.getC().setLoading(false);
                break;

            case ("RestaurarHP/MP"):
                p.getFicha().setHp(p.getFicha().getHptotal());
                p.getFicha().setMp(p.getFicha().getMptotal());
                break;

            case ("RestaurarHP"):
                p.getFicha().setHp(p.getFicha().getHptotal());
                p.getFicha().setHp(p.getFicha().getHptotal());
                break;

            case ("RestaurarMP"):
                p.getFicha().setMp(p.getFicha().getMptotal());
                p.getFicha().setMp(p.getFicha().getMptotal());
                break;

            case ("Dano"):
                p.tomouDano(Integer.parseInt(p.getC().getCheckpointArray().get(obj).getProperties().getProperty("Dano")));
                Image image = new ImageIcon(this.getClass().getResource("Sprites\\Outros\\" + p.getC().getCheckpointArray().get(obj).getProperties().getProperty("NovaImagem"))).getImage();
                break;
        }
    }
}

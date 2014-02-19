package com.oterceirodeus.codigo_fonte.engine;


import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import com.oterceirodeus.codigo_fonte.gameplay.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Henrique
 */
public class Colisão {

    private int contador = 0;

    public static boolean colisaoTerrenoY(Personagem p, ArrayList<Terreno> t) {

        int contador = 0;
        while (contador < t.size()) {

            if (p.getPosicaoM().y + p.getImage().getHeight(null) >= t.get(contador).getY() - 2
                    && p.getPosicaoM().y + p.getImage().getHeight(null) <= t.get(contador).getY() + 3
                    && p.getPosicaoM().x + p.getImage().getWidth(null) >= t.get(contador).getX()
                    && p.getPosicaoM().x <= t.get(contador).getX() + t.get(contador).getLargura()) {
                p.setPlat(t.get(contador));
                p.setPosicaoY(t.get(contador).getY() - p.getImage().getHeight(null));
                return true;
            }
            contador++;
        }
        return false;
    }

    public static boolean caiuPlataforma(Personagem p) {
        if (p.getPlat() != null) {
            System.out.println(p.getPlat().getNome() + "/" + p.getPlat().getX());
            if (p.getPosicaoM().x + p.getImage().getWidth(null) < p.getPlat().getX()
                    || p.getPosicaoM().x > p.getPlat().getX() + p.getPlat().getLargura()) {

                p.setPlat(null);
                return true;
            }
        }
        return false;
    }

    public static boolean colisaoTerrenoX(Personagem p, ArrayList<Terreno> t) {

        int contador = 0;
        while (contador < t.size()) {

            if (p.getPosicaoM().y + p.getImage().getHeight(null) >= t.get(contador).getY() - 2
                    && p.getPosicaoM().y + p.getImage().getHeight(null) <= t.get(contador).getY() + 2
                    && p.getPosicaoM().x + p.getImage().getWidth(null) >= t.get(contador).getX()
                    && p.getPosicaoM().x <= t.get(contador).getX() + t.get(contador).getLargura()) {
                p.setPlat(null);
                return true;
            }
            contador++;
        }
        return false;
    }

    public static boolean colisaoObstaculo(Personagem a, ArrayList<Obstaculo> t) {
        Rectangle2D personagem = new Rectangle((int) a.getPosicaoM().x, (int) a.getPosicaoM().y, a.getImage().getWidth(null), a.getImage().getHeight(null));
        int contador = 0;
        while (contador < t.size()) {
            //////System.out.println("Teste de colisão com obstaculos: " + contador);
            Rectangle2D r2d = new Rectangle(t.get(contador).getX(), t.get(contador).getY(), t.get(contador).getLargura(), t.get(contador).getAltura());
            if (personagem.intersects(r2d)) {
                //////System.out.println("Colidiu com obstaculo: " + t.get(contador).getNome());
                return true;
            }
            contador++;
        }
        return false;
    }

    public static int colisaoCheckPoint(Personagem a, ArrayList<CheckPoint> cp) {
        Rectangle2D personagem = new Rectangle((int) a.getPosicaoM().x, (int) a.getPosicaoM().y, a.getImage().getWidth(null), a.getImage().getHeight(null));
        int contador = 0;
        while (contador < cp.size()) {
            Rectangle2D r2d = new Rectangle(cp.get(contador).getX(), cp.get(contador).getY(), cp.get(contador).getLargura(), cp.get(contador).getAltura());
            if (personagem.intersects(r2d)) {
                return contador;
            }
            contador++;
        }
        return -1;
    }

    public static void colisãoSkillTrajetoria(Magia m, ArrayList<Personagem> a) {
        // System.out.println("Iniciando teste de colisão para magia com trajetoria");
        System.out.println(a.size());
        for (Personagem p : a) {
            if (m.getLado() == 1) {
                //   System.out.println("Lado = 1");
                if (m.getPosicao().x + m.getIm().getWidth(null) >= p.getPosicaoM().x
                        && m.getPosicao().x + m.getIm().getWidth(null) <= p.getPosicaoM().x + m.getVelocidade() + 1) {
                    if ((m.getPosicao().y >= p.getPosicaoM().y && m.getPosicao().y <= p.getPosicaoM().y + p.getImage().getHeight(null))
                            || (m.getPosicao().y + m.getIm().getHeight(null) >= p.getPosicaoM().x
                            && m.getPosicao().y + m.getIm().getHeight(null) <= p.getPosicaoM().y + p.getImage().getHeight(null))) {
                        R3DET.DanoMagiaInfringido(m, p.getFicha());
                        //  System.out.println("Colidiu!");
                        if (!m.isMobRange()) {
                            m.setExecutando(false);
                        }
                        if (!m.getM().equals(null)) {
                            m.getConjurador().executarSkill(m.getM());
                            if (m.getLado() == 1) {
                                m.getM().setPosicaoX((m.getPosicao().x + m.getIm().getWidth(null)) - (m.getIm().getWidth(null) / 2));
                            } else if (m.getLado() == 0) {
                                m.getM().setPosicaoX(m.getPosicao().x - (m.getIm().getWidth(null) / 2));
                            }

                            m.getM().setPosicaoY(m.getPosicao().y - (m.getIm().getHeight(null) / 2));
                        }
                    }
                }
            } else if (m.getLado() == 0) {
                //System.out.println("Lado = 1");
                if (m.getPosicao().x <= p.getPosicaoM().x + p.getImage().getWidth(null)
                        && m.getPosicao().x >= p.getPosicaoM().x + p.getImage().getWidth(null) - m.getVelocidade() - 1) {
                    // System.out.println("Colidiu!");
                    if ((m.getPosicao().y >= p.getPosicaoM().y && m.getPosicao().y <= p.getPosicaoM().y + p.getImage().getHeight(null))
                            || (m.getPosicao().y + m.getIm().getHeight(null) >= p.getPosicaoM().y && m.getPosicao().y + m.getIm().getHeight(null) <= p.getPosicaoM().y + p.getImage().getHeight(null))) {
                        R3DET.DanoMagiaInfringido(m, p.getFicha());
                        if (!m.isMobRange()) {
                            m.setExecutando(false);
                        }
                        if (!m.getM().equals(null)) {
                            m.getM().iniciar(m.getLado(), m.getPosicao(), 0, 0, m.getConjurador());
                        }
                    }
                }
            }
        }
    }

    public static void colisãoSkillTrajetoria(Magia m, Personagem p) {


        if (m.getLado() == 1) {
            if (m.getPosicao().x + m.getIm().getWidth(null) >= p.getPosicaoM().x
                    && m.getPosicao().x + m.getIm().getWidth(null) < p.getPosicaoM().x + m.getVelocidade() - 1) {
                

                if (!m.isMobRange()) {
                    m.encerrar();
                }
                if (!m.getM().equals(null)) {
                    m.getConjurador().executarSkill(m.getM());
                    if (m.getLado() == 1) {
                        m.getM().setPosicaoX((m.getPosicao().x + m.getIm().getWidth(null)) - (m.getIm().getWidth(null) / 2));
                    } else if (m.getLado() == 0) {
                        m.getM().setPosicaoX(m.getPosicao().x - (m.getIm().getWidth(null) / 2));
                    }

                    m.getM().setPosicaoY(m.getPosicao().y - (m.getIm().getHeight(null) / 2));
                }
            }
        } else if (m.getLado() == 0) {
            if (m.getPosicao().x <= p.getPosicaoM().x + p.getImage().getWidth(null)
                    && m.getPosicao().x > p.getPosicaoM().x + p.getImage().getWidth(null) - m.getVelocidade() - 1) {

                if (!m.isMobRange()) {
                    m.encerrar();
                }
                if (!m.getM().equals(null)) {
                    m.getM().iniciar(m.getLado(), m.getPosicao(), 0, 0, m.getConjurador());
                }
            }
        }
    }

    public static ArrayList<Personagem> colisãoSkillArea(Magia s, ArrayList<Personagem> a) {
        ArrayList<Personagem> result = new ArrayList();

        for (Personagem p : a) {

            if (p.getPosicaoM().x >= s.getPosicao().x
                    && p.getPosicaoM().x <= s.getPosicao().x + s.getIm().getWidth(null)
                    && p.getPosicaoM().y >= s.getPosicao().y
                    && p.getPosicaoM().y <= s.getPosicao().y + s.getIm().getHeight(null)) {
                System.out.println("Colidiu");
                R3DET.DanoMagiaInfringido(s, p.getFicha());
                if (s.getM() != null) {
                    s.getM().iniciar(s.getLado(), s.getPosicao(), 0, 0, s.getConjurador());
                }

                result.add(p);

            }

        }

        return result;

    }
    public static boolean colisaoPersonagem(Personagem a, ArrayList<Personagem> b) {


//        if (a.isMudou()) {
//            return false;
//        }

        int contador = 0;

        while (contador < b.size()) {
            if (b.get(contador).getPosicaoM().x > a.getPosicaoM().x) {
                if (a.getPosicaoM().x + a.getImage().getWidth(null) >= b.get(contador).getPosicaoM().x
                        && a.getPosicaoM().y + a.getImage().getHeight(null) >= b.get(contador).getPosicaoM().y
                        && a.getPosicaoM().y <= b.get(contador).getPosicaoM().y + b.get(contador).getImage().getHeight(null)) {
                    System.out.println("Colisão 1");
                    R3DET.DanoForçaInfringido(a.getFicha(), b.get(contador).getFicha());
                    return true;
                }
            } else {
                if (a.getPosicaoM().x <= b.get(contador).getPosicaoM().x + b.get(contador).getImage().getWidth(null)
                        && a.getPosicaoM().y + a.getImage().getHeight(null) >= b.get(contador).getPosicaoM().y
                        && a.getPosicaoM().y <= b.get(contador).getPosicaoM().y + b.get(contador).getImage().getHeight(null)) {
                    System.out.println("Colisão 2");
                    R3DET.DanoForçaInfringido(a.getFicha(), b.get(contador).getFicha());
                    return true;
                }
            }
            contador++;
        }
        return false;

    }
}

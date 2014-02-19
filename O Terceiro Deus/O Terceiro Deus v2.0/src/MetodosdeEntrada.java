/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author Yves
 */
public class MetodosdeEntrada implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private Personagem p;
    private NPC n;
    private MapaMundo mdm;
    private GameCore gc;

    public void setGc(GameCore gc) {
        this.gc = gc;
    }

    public void setMdm(MapaMundo mdm) {
        this.mdm = mdm;
    }

    public void setP(Personagem p) {
        this.p = p;
    }

    public void setNpc(NPC n) {
        this.n = n;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (mdm.isAtivado()) {
            mapadoMundoKeyInComandsPressed(e);
        } else if (p.getInventario().isInInventario()) {
            inventarioKeyInComandsPressed(e);
        } else {
            personagemKeyInComandsPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mdm.isAtivado()) {
        } else {
            personagemKeyInComandsReleased(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mdm.isAtivado()) {
            mdm.mouseClick(e);
        } else if (p.getInventario().isInInventario()) {
            p.getInventario().inventarioMouseInComandsPressed(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //     throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (p.getInventario().isInInventario()) {
            p.getInventario().inventarioMouseInComandsMoved(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //     throw new UnsupportedOperationException("Not supported yet.");
    }

    private void inventarioKeyInComandsPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ESCAPE):
                p.getInventario().setInInventario(false);
        }
    }

    private void mapadoMundoKeyInComandsPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ESCAPE):
                if (mdm.isInreino()) {
                    if (p.getC().isSair()) {
                        p.getC().setSair(false);
                    } else {
                        p.getC().setSair(true);
                    }
                } else {
                    if (p.getC().isSair()) {
                        p.getC().setSair(false);
                    } else {
                        p.getC().setSair(true);
                    }
                }
                break;
            case (KeyEvent.VK_ENTER):
                if (mdm.isInreino()) {
                    if (p.getC().isSair()) {
                        p.getC().setLoading(true);
                        mdm.loadmapa("MapaDoReinado.tmx");
                        mdm.setInreino(false);
                        p.getC().setSair(false);
                        p.getC().setLoading(false);
                    }
                } else if (p.getC().isSair()) {
                    gc.setQuit(true);
                }
        }
    }

    private void personagemKeyInComandsReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_LEFT):
                p.setLado(0);
                p.setVelocidadeX(0);
                break;
            case (KeyEvent.VK_RIGHT):
                p.setLado(1);
                p.setVelocidadeX(0);
                break;
        }
    }

    private void personagemKeyInComandsPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case (KeyEvent.VK_I):
                p.getInventario().setInInventario(true);
                break;

            case (KeyEvent.VK_LEFT):
                p.setLado(0);
                if (p.getMovimentos().getLadoBloqueado() != 0) {
                    if (e.isShiftDown()) {
                        p.setVelocidadeX(-(p.getFicha().getVelocidade().x * 1.25f));
                        break;
                    }
                    p.setVelocidadeX(-(p.getFicha().getVelocidade().x));
                    ////System.out.println("left pressed");
                    break;
                }
                //System.out.println("lado bloqueado");
                break;

            case (KeyEvent.VK_RIGHT):
                p.setLado(1);
                if (p.getMovimentos().getLadoBloqueado() != 1) {
                    if (e.isShiftDown()) {
                        p.setVelocidadeX((p.getFicha().getVelocidade().x * 1.25f));
                        break;
                    }
                    p.setVelocidadeX((p.getFicha().getVelocidade().x));
                    ////System.out.println("Right pressed");
                    break;
                }
                //System.out.println("lado bloqueado");
                break;

            case (KeyEvent.VK_UP):
                if (!p.isOcupado()) {
                    p.setNormal(0);
                    p.setVelocidadeY(-(p.getFicha().getVelocidade().y));
                    p.setOcupado(true);
                }
                ////System.out.println("up pressed");
                break;
            case (KeyEvent.VK_DOWN):
                if (p.getPlat().getProperties().getProperty("Decivel").equals("true")) {
                    if (!p.isOcupado()) {
                        p.setNormal(0);
                        p.setVelocidadeY(4);
                        p.setOcupado(true);
                    }
                }
                ////System.out.println("up pressed");
                break;

            case (KeyEvent.VK_A):
                int i = Colisão.colisaoCheckPoint(p, p.getC().getCheckpointArray());
                if (i != (-1)) {
                    new AcoesdeCheckPoint().executar(p, i, mdm);
                }
                break;

            case (KeyEvent.VK_ESCAPE):
                if (p.getC().isSair()) {
                    p.getC().setSair(false);
                } else {
                    p.getC().setSair(true);

                }
                break;

            case (KeyEvent.VK_P):
                gc.getBds().calcularLvl(100);
                break;

            case (KeyEvent.VK_ENTER):
                if (p.getC().isSair()) {
                    p.getC().setLoading(true);
                    mdm.setAtivado(true);
                    mdm.getPlayer().setBackMusic(mdm.getMapaDoMundo().getProperties().getProperty("BackMusic")).play();
                    p.getC().setSair(false);
                    p.getC().setLoading(false);
                }
                break;
            case (KeyEvent.VK_Q):
                if (!p.getSkills().get(0).isExecutando()) {
                    p.executarSkill(0);
                }
                break;
            case (KeyEvent.VK_W):
                if (!p.getSkills().get(1).isExecutando()) {
                    p.executarSkill(1);
                }
                break;
            case (KeyEvent.VK_E):
                if (!p.getSkills().get(2).isExecutando()) {
                    p.executarSkill(2);
                }
                break;
            case (KeyEvent.VK_R):
                if (!p.getSkills().get(3).isExecutando()) {
                    p.executarSkill(3);
                }
                break;
            case (KeyEvent.VK_Z):
                if (!p.isAtacando()) {
                    p.setAtacando(true);
                    p.executarAtaque(0);
                }
                break;
            case (KeyEvent.VK_X):
                if (!p.isAtacando()) {
                    p.setAtacando(true);
                    p.executarAtaque(2);
                }
                break;
            case (KeyEvent.VK_C):
                if (!p.isAtacando()) {
                    p.setAtacando(true);
                    p.executarAtaque(4);
                }
                break;



        }
    }
}

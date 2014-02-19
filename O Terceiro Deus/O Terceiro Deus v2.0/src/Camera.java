
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class Camera {

    private Personagem p;
    private Point posicaoT = new Point(0, 0);
    private boolean camtravadaX = false;
    private boolean camtravadaY = false;

    public void atualizarCamera() {
        //Atualiza camera em X
        if (p.getPosicaoM().x < 800 || p.getPosicaoM().x > (p.getC().getMapa().getWidth() * p.getC().getMapa().getTileWidth() - 800)) {

            if (p.getPosicaoM().x < 800) {
                if (p.getPosicaoM().x < 400) {
                    this.posicaoT.x = 0;
                    camtravadaX = false;
                } else {
                    this.posicaoT.x =(int) p.getPosicaoM().x - 400;
                    camtravadaX = true;
                }
            } else {
                if (p.getPosicaoM().x > ((p.getC().getMapa().getWidth() * p.getC().getMapa().getTileWidth()) - 400)) {
                    this.posicaoT.x = (p.getC().getMapa().getWidth() * p.getC().getMapa().getTileWidth() - 800);
                    camtravadaX = false;
                } else {
                    this.posicaoT.x =(int) (p.getPosicaoM().x - 400);
                    camtravadaX = true;
                }
            }
        } else {
            this.posicaoT.x =(int) p.getPosicaoM().x - 400;            //camera no personagem em X
            camtravadaX = true;
        }
        //<<<<<<<<<<<<<<<

        //Atualiza camera em Y
        if (p.getPosicaoM().y < 600 || p.getPosicaoM().y > (p.getC().getMapa().getHeight() * p.getC().getMapa().getTileHeight() - 600)) {

            if (p.getPosicaoM().y < 600) {
                if (p.getPosicaoM().y < 300) {
                    this.posicaoT.y = 0;
                    camtravadaY = false;
                } else {
                    this.posicaoT.y =(int) p.getPosicaoM().y - 300;
                    camtravadaY = true;
                }
            } else {
                if (p.getPosicaoM().y > ((p.getC().getMapa().getHeight() * p.getC().getMapa().getTileHeight()) - 300)) {
                    this.posicaoT.y = (p.getC().getMapa().getHeight() * p.getC().getMapa().getTileHeight() - 600);
                    camtravadaY = false;
                } else {
                    this.posicaoT.y =(int) (p.getPosicaoM().y - 300);
                    camtravadaY = true;
                }
            }
        } else {
            this.posicaoT.y =(int) p.getPosicaoM().y - 300;            //camera no personagem em Y
            camtravadaY = true;
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
       // System.out.println(posicaoT + "/" + p.posicaoM);
    }

    public void setP(Personagem p) {
        this.p = p;
    }

    public Point getPosicaoT() {
        return posicaoT;
    }

    public boolean isCamtravadaX() {
        return camtravadaX;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.Point;
import java.util.Properties;

/**
 *
 * @author Yves
 */
public class CheckPoint {

    private Point posicao;
    private int x;
    private int y;
    private int altura;
    private int largura;
    private Image imagem;
    private String nome;
    private String type;
    private String funcao;
    private String MapName;
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }       

    public String getMapName() {
        return MapName;
    }

    public void setMapName(String MapName) {
        this.MapName = MapName;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setbounds() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }
}

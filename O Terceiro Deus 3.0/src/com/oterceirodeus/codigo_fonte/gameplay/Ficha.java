package com.oterceirodeus.codigo_fonte.gameplay;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Yves
 */
public class Ficha {

    private Point posicao = new Point();
    private Point.Float velocidade = new Point.Float();
    private Image image;
    private String nome;
    private String descricao;
    private Raça raca;
    private Classe classe;
    private Map<String, Integer> atributos = new LinkedHashMap<>();
    private Map<String, Integer> focus = new LinkedHashMap<>();
    private ArrayList<String> vantagens = new ArrayList<>();
    private ArrayList<String> desvantagens = new ArrayList<>();
    private Item armadura = new Item();
    private Item arma = new Item();
    private ArrayList<Item> itens = new ArrayList();
    private int hp, mp, hptotal, mptotal;

    public Ficha() {
        this.hptotal = 3000;
        this.hp = 3000;
        this.mp = 1000;
        this.mptotal = 1000;
        setPosicao(100, 100);
        setVelocidade(8, 20);
        setImage("..\\..\\assets\\sprites\\Outros\\Hitsugaia.png");
    }

    public Ficha(String nome, Raça raca, Classe classe) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        setPosicao(100, 100);
        setVelocidade(8, 20);
     //   setImage("Sprites\\Hitsugaia.png");

        addAtributosRaçaClasse();
    }

    public void addAtributosRaçaClasse() {   //Faz TODOS os cálculos colocando em dois atributos(Arma,Armadura) a soma do F,PdF,A,H,Focus,ResistenciaEfeito e Resistencia Dano da Raça e da Classe da personagem: Lembrando qe o resultado base é IMUTÁVEL(por isso executo o método uma vez), já qe os atributos adquiridos ao longo do jogo serão adicionados na ficha.

        for (String key : raca.getResistenciaDano().getTiposDano().keySet()) {
            this.armadura.getResistenciaDano().addValor(key, raca.getResistenciaDano().getTiposDano().get(key));
            this.armadura.getResistenciaDano().addValor(key, classe.getResistenciaDano().getTiposDano().get(key));
        }

        for (String key1 : raca.getResistenciaEfeitos().getEfeitos().keySet()) {
            for (String key2 : raca.getResistenciaEfeitos().getEfeitos().get(key1).getTiposDano().keySet()) {
                this.armadura.getResistenciaEfeitos().getEfeitos().get(key1).addValor(key2, raca.getResistenciaEfeitos().getEfeitos().get(key1).getTiposDano().get(key2));
            }
        }

        for (String key1 : classe.getResistenciaEfeitos().getEfeitos().keySet()) {
            for (String key2 : classe.getResistenciaEfeitos().getEfeitos().get(key1).getTiposDano().keySet()) {
                this.armadura.getResistenciaEfeitos().getEfeitos().get(key1).addValor(key2, classe.getResistenciaEfeitos().getEfeitos().get(key1).getTiposDano().get(key2));
            }
        }

        for (String key : raca.getFocus().keySet()) {
            this.focus.put(key, raca.getFocus().get(key) + classe.getFocus().get(key));
        }

        for (String key : raca.getAtributos().keySet()) {
            this.atributos.put(key, raca.getAtributos().get(key) + classe.getAtributos().get(key));

        }
        
        setResistencia(atributos.get("Resistência"), classe.getPvs(), classe.getPms());
        setHp(getHptotal());
        setMp(getMptotal());

        //System.out.println(this.getAtributos().get("Habilidade") + " " + this.getHp() + " " + this.getHptotal() + " " + this.getMp() + " " + this.getMptotal());
        for (String chave[] : raca.getVantagens()) {
            this.vantagens.add(chave[0]);
        }

        for (String chave[] : raca.getDesvantagens()) {
            this.desvantagens.add(chave[0]);
        }

        for (String chave[] : classe.getVantagens()) {
            this.vantagens.add(chave[0]);
        }

        for (String chave[] : classe.getDesvantagens()) {
            this.desvantagens.add(chave[0]);
        }
    }

    public void setRaca(Raça raca) {
        this.raca = raca;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Raça getRaca() {
        return raca;
    }

    public Classe getClasse() {
        return classe;
    }

    public Item getArma() {
        return arma;
    }

    public Item getArmadura() {
        return armadura;
    }

    public void setItem(Item item) {
        this.itens.set(item.getIdentificador(), item);
        addArmadura(item);
    }

    public void removerItem(Item item) {
        this.itens.set(this.itens.indexOf(item), null);
        removerArmadura(item);
    }

    public void addFocus(String nomefocus, int valorfocus) {
        this.focus.put(nomefocus, this.focus.get(nomefocus) + valorfocus);
    }

    public void addAtributos(String nomeatributo, int valoratributo) {
        this.atributos.put(nomeatributo, this.focus.get(nomeatributo) + valoratributo);
    }

    public void addVantagem(String vantagem) {
        vantagens.add(vantagem);
    }

    public void removerVantagem(String vantagem) {
        vantagens.remove(vantagens.indexOf(vantagem));
    }

    public ArrayList<String> getVantagens() {
        return vantagens;
    }

    public void addDesvantagens(String desvantagem) {
        this.desvantagens.add(desvantagem);
    }

    public void removerDesvatagem(String desvantagem) {
        this.desvantagens.remove(this.desvantagens.indexOf(desvantagem));
    }

    public ArrayList<String> getDesvantagens() {
        return desvantagens;
    }

    public Map<String, Integer> getAtributos() {
        return atributos;
    }

    public void setPosicao(int x, int y) {
        this.posicao.x = x;
        this.posicao.y = y;
    }

    public void setPosicaoX(int x) {
        this.posicao.x = x;
    }

    public void setPosicaoY(int y) {
        this.posicao.y = y;
    }

    public void setVelocidade(int x, int y) {
        this.velocidade.x = x;
        this.velocidade.y = y;
    }

    public void setResistencia(int resistencia, int multiplicadorVida, int multiplicadorMana) {
        //System.out.println(resistencia+ "/"+ multiplicadorVida+ "/" + multiplicadorMana);
        this.hptotal = resistencia * multiplicadorVida;
        this.mptotal = resistencia * multiplicadorMana;
        
        if(this.hptotal == 0){
            this.hptotal = 1;
        }
        if(this.mptotal == 0){
            this.mptotal = 1;
        }
    }

    public void addArmadura(Item item) {
        for (String set : item.getTipoDano().getTiposDano().keySet()) {
            this.arma.getTipoDano().addValor(set, item.getTipoDano().getTiposDano().get(set));
        }
        for (String set1 : item.getEfeitos().getEfeitos().keySet()) {
            for (String set2 : item.getEfeitos().getEfeitos().get(set1).getTiposDano().keySet()) {
                this.arma.getEfeitos().setarEfeito(set1, set2, item.getEfeitos().getEfeitos().get(set1).getTiposDano().get(set2));
            }
        }
        for (String set : item.getResistenciaDano().getTiposDano().keySet()) {
            this.armadura.getResistenciaDano().addValor(set, item.getResistenciaDano().getTiposDano().get(set));
        }
        for (String set1 : item.getResistenciaEfeitos().getEfeitos().keySet()) {
            for (String set2 : item.getResistenciaEfeitos().getEfeitos().get(set1).getTiposDano().keySet()) {
                this.armadura.getResistenciaEfeitos().setarEfeito(set1, set2, item.getResistenciaEfeitos().getEfeitos().get(set1).getTiposDano().get(set2));
            }
        }
    }

    public void removerArmadura(Item item) {
        for (String set : item.getTipoDano().getTiposDano().keySet()) {
            this.arma.getTipoDano().setValor(set, this.arma.getTipoDano().getTiposDano().get(set) - item.getTipoDano().getTiposDano().get(set));
        }
        for (String set1 : item.getEfeitos().getEfeitos().keySet()) {
            for (String set2 : item.getEfeitos().getEfeitos().get(set1).getTiposDano().keySet()) {
                this.arma.getEfeitos().setarEfeito(set1, set2, this.arma.getEfeitos().getEfeitos().get(set1).getTiposDano().get(set2) - item.getEfeitos().getEfeitos().get(set1).getTiposDano().get(set2));
            }
        }
        for (String set : item.getResistenciaDano().getTiposDano().keySet()) {
            this.armadura.getResistenciaDano().setValor(set, this.arma.getResistenciaDano().getTiposDano().get(set) - item.getTipoDano().getTiposDano().get(set));
        }
        for (String set1 : item.getResistenciaEfeitos().getEfeitos().keySet()) {
            for (String set2 : item.getResistenciaEfeitos().getEfeitos().get(set1).getTiposDano().keySet()) {
                this.armadura.getResistenciaEfeitos().setarEfeito(set1, set2, this.arma.getResistenciaEfeitos().getEfeitos().get(set1).getTiposDano().get(set2) - item.getEfeitos().getEfeitos().get(set1).getTiposDano().get(set2));
            }
        }
    }

    public Float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Float velocidade) {
        this.velocidade = velocidade;
    }

    public void setVelocidadeX(int x) {
        this.velocidade.x = x;
    }

    public void setVelocidadeY(int y) {
        this.velocidade.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage(String url) {
        this.image = new ImageIcon(this.getClass().getResource(url)).getImage();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            hp = 0;
        }
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        if (mp < 0) {
            mp = 0;
        }
        this.mp = mp;
    }

    public int getHptotal() {
        return hptotal;
    }

    private void setHptotal(int hptotal) {
        this.hptotal = hptotal;
    }

    public int getMptotal() {
        return mptotal;
    }

    private void setMptotal(int mptotal) {
        this.mptotal = mptotal;
    }

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


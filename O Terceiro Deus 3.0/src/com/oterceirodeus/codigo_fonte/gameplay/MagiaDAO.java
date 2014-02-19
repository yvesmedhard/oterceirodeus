package com.oterceirodeus.codigo_fonte.gameplay;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Daniela
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MagiaDAO {

    private String nomeMagia;
    private boolean mobRange;
    private int tipo;
    private int alcance;
    private int a;
    private int dano;
    private int custo;
    private String imagem;
    private String sprite;

    public MagiaDAO() {
        this.nomeMagia = "";
        this.mobRange = false;
        this.tipo = 0;
        this.alcance = 0;
        this.a = 0;
        this.dano = 0;
        this.custo = 0;
        this.imagem = "";
        this.sprite = "";
    }

    public MagiaDAO(String nomeMagia, boolean mobRange, int tipo, int alcance, int a, int dano, int custo,
            String imagem, String sprite) {
        this.nomeMagia = nomeMagia;
        this.mobRange = mobRange;
        this.tipo = tipo;
        this.alcance = alcance;
        this.a = a;
        this.dano = dano;
        this.custo = custo;
        this.imagem = imagem;
        this.sprite = sprite;
    }

    public void insert(MagiaDAO m) {
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
            PreparedStatement pS = c.prepareStatement("insert into magia values(?,?,?,?,?,?,?,?,?)");
            pS.setString(1, m.getNomeMagia());
            pS.setBoolean(2, m.isMobRange());
            pS.setInt(3, m.getTipo());
            pS.setInt(4, m.getAlcance());
            pS.setInt(5, m.getA());
            pS.setInt(6, m.getDano());
            pS.setInt(7, m.getCusto());
            pS.setString(8, m.getImagem());
            pS.setString(9, m.getSprite());


            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String nomeMagia) {
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
            PreparedStatement pS = c.prepareStatement("delete from magia where nomeMagia = ?");
            pS.setString(1, nomeMagia);

            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MagiaDAO procurarMagia(String nomeMagia) {
        MagiaDAO m = null;
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
            PreparedStatement pS = c.prepareStatement("select * from magia where nomeMagia = ?");

            pS.setString(1, nomeMagia);

            ResultSet r = pS.executeQuery();

            while (r.next()) {
                m = new MagiaDAO(r.getString("nomeMagia"), r.getBoolean("mobRange"), r.getInt("tipo"),
                        r.getInt("alcance"), r.getInt("a"), r.getInt("dano"), r.getInt("custo"), r.getString("imagem"),
                        r.getString("sprite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public String getNomeMagia() {
        return nomeMagia;
    }

    public void setNomeMagia(String nomeMagia) {
        this.nomeMagia = nomeMagia;
    }

    public boolean isMobRange() {
        return mobRange;
    }

    public void setMobRange(boolean mobRange) {
        this.mobRange = mobRange;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
    
    public static void main(String[] args){
        
    }
    
}

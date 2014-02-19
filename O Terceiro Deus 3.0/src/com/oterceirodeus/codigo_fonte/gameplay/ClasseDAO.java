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
import java.util.ArrayList;


public class ClasseDAO {
	private String nome;
	private String descricao;
	private int forca;
	private int hab;
	private int res;
	private int arm;
	private int pDF;
	private int agua;
	private int terra;
	private int fogo;
	private int ar;
	private int luz;
	private int trevas;
	private int pvs;
	private int pms;

	public ClasseDAO(){
		nome = "";
		descricao = "";
		forca = 0;
		hab = 0;
		res = 0;
		arm = 0;
		pDF = 0;
		agua = 0;
		terra = 0;
		fogo = 0;
		ar = 0;
		luz = 0;
		trevas = 0;
		pvs = 0;
		pms = 0;
	}
	
	public ClasseDAO(String nome, String descricao, int forca, int hab, int res, int arm, int pDF,
			int agua, int terra, int fogo, int ar, int luz, int trevas, int pvs, int pms){
		this.nome = nome;
		this.descricao = descricao;
		this.forca = forca;
		this.hab = hab;
		this.res = res;
		this.arm = arm;
		this.pDF = pDF;
		this.agua = agua;
		this.terra = terra;
		this.fogo = fogo;
		this.ar = ar;
		this.luz = luz;
		this.trevas = trevas;
		this.pvs = pvs;
		this.pms = pms;
	}
	
	public void insert(ClasseDAO r){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("insert into classe values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pS.setString(1, r.getNome());
			pS.setString(2, r.getDescricao());
			pS.setInt(3, r.getForca());
			pS.setInt(4, r.getHab());
			pS.setInt(5, r.getRes());
			pS.setInt(6, r.getArm());
			pS.setInt(7, r.getpDF());
			pS.setInt(8, r.getAgua());
			pS.setInt(9, r.getTerra());
			pS.setInt(10, r.getFogo());
			pS.setInt(11, r.getAr());
			pS.setInt(12, r.getLuz());
			pS.setInt(13, r.getTrevas());
			pS.setInt(14, r.getPvs());
			pS.setInt(15, r.getPms());
			
			pS.executeUpdate();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String nomeClasse){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("delete from classe where nome = ?");
			pS.setString(1, nomeClasse);
						
			pS.executeUpdate();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ClasseDAO procurarClasse(String classe){
		ClasseDAO r = null;
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("select * from classe where nome = ?");
			
			pS.setString(1, classe);
						
			ResultSet rs  = pS.executeQuery();
			
			while (rs.next()){
                r = new ClasseDAO(rs.getString("nome"),rs.getString("descricao"),rs.getInt("forca"),
                		rs.getInt("hab"),rs.getInt("res"),rs.getInt("arm"),rs.getInt("pDF"),
                		rs.getInt("agua"),rs.getInt("terra"),rs.getInt("fogo"),rs.getInt("ar"),
                		rs.getInt("luz"),rs.getInt("trevas"), rs.getInt("pvs"), rs.getInt("pms"));
             }
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public ArrayList<ClasseDAO> procurarClasses(String classe){
		ArrayList<ClasseDAO> r = new ArrayList<ClasseDAO>();
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("select * from classe where nome = ?");
			
			pS.setString(1, classe);
						
			ResultSet rs  = pS.executeQuery();
			
			while (rs.next()){
                r.add(new ClasseDAO(rs.getString("nome"),rs.getString("descricao"),rs.getInt("forca"),
                		rs.getInt("hab"),rs.getInt("res"),rs.getInt("arm"),rs.getInt("pDF"),
                		rs.getInt("agua"),rs.getInt("terra"),rs.getInt("fogo"),rs.getInt("ar"),
                		rs.getInt("luz"),rs.getInt("trevas"), rs.getInt("pvs"), rs.getInt("pms")));
             }
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	
	
	
	public ClasseDAO getClassePersonagem(String nomePers,String classe){
		ClasseDAO r = null;
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("select classe.nome,classe.descricao,classe.forca,classe.hab,classe.res,classe.arm,classe.pDF,classe.agua,classe.terra,classe.fogo,classe.ar,classe.luz,classe.trevas,classe.pvs,classe.pms from classe,personagemHasClasse where personagemHasClasse.nomePers = ? AND classe.nome = personagemHasRaca.nome");
			
			pS.setString(1, classe);
						
			ResultSet rs  = pS.executeQuery();
			
			while (rs.next()){
                r = new ClasseDAO(rs.getString("nome"),rs.getString("descricao"),rs.getInt("forca"),
                		rs.getInt("hab"),rs.getInt("res"),rs.getInt("arm"),rs.getInt("pDF"),
                		rs.getInt("agua"),rs.getInt("terra"),rs.getInt("fogo"),rs.getInt("ar"),
                		rs.getInt("luz"),rs.getInt("trevas"),rs.getInt("pvs"),rs.getInt("pms"));
             }
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public ArrayList<String> getVantagens(String nomeClasse){
		ArrayList<String> vantagens = new ArrayList<String>();
		
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("select nome2 from classeHasVantagem where nome = ?");
			
			pS.setString(1, nomeClasse);
						
			ResultSet rs  = pS.executeQuery();
			
			while (rs.next()){
                vantagens.add(rs.getString("nome2"));
             }
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vantagens;
	}
	
	public ArrayList<String> getDesvantagens(String nomeClasse){
		ArrayList<String> desvantagens = new ArrayList<String>();
		
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("select nome2 from classeHasDesvantagem where nome = ?");
			
			pS.setString(1, nomeClasse);
						
			ResultSet rs  = pS.executeQuery();
			
			while (rs.next()){
                desvantagens.add(rs.getString("nome2"));
             }
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return desvantagens;
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

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public int getHab() {
		return hab;
	}

	public void setHab(int hab) {
		this.hab = hab;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public int getArm() {
		return arm;
	}

	public void setArm(int arm) {
		this.arm = arm;
	}

	public int getpDF() {
		return pDF;
	}

	public void setpDF(int pDF) {
		this.pDF = pDF;
	}

	public int getAgua() {
		return agua;
	}

	public void setAgua(int agua) {
		this.agua = agua;
	}

	public int getTerra() {
		return terra;
	}

	public void setTerra(int terra) {
		this.terra = terra;
	}

	public int getFogo() {
		return fogo;
	}

	public void setFogo(int fogo) {
		this.fogo = fogo;
	}

	public int getAr() {
		return ar;
	}

	public void setAr(int ar) {
		this.ar = ar;
	}

	public int getLuz() {
		return luz;
	}

	public void setLuz(int luz) {
		this.luz = luz;
	}

	public int getTrevas() {
		return trevas;
	}

	public void setTrevas(int trevas) {
		this.trevas = trevas;
	}
	
	public int getPvs() {
		return pvs;
	}

	public void setPvs(int pvs) {
		this.pvs = pvs;
	}

	public int getPms() {
		return pms;
	}

	public void setPms(int pms) {
		this.pms = pms;
	}
}

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
import java.sql.SQLException;


public class MapaDAO {

        private String nomeMapa;
	private String caminhoMapa;
	private boolean eRPG;
	
	public MapaDAO(){
		this.nomeMapa = "";
		this.caminhoMapa = "";
		this.eRPG = false;
	}
	
	public MapaDAO(String nomeMusica, String caminhoMusica, boolean eRPG){
		this.nomeMapa = nomeMusica;
		this.caminhoMapa = caminhoMusica;
		this.eRPG = eRPG;
	}
	
	public void insert(MapaDAO m){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("insert into mapa values(?,?,?)");
			pS.setString(1, m.getNomeMapa());
			pS.setString(2, m.getCaminhoMapa());
			pS.setBoolean(3, m.iseRPG());
			
			pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNomeMapa() {
		return nomeMapa;
	}

	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}

	public String getCaminhoMapa() {
		return caminhoMapa;
	}

	public void setCaminhoMapa(String caminhoMapa) {
		this.caminhoMapa = caminhoMapa;
	}

	public boolean iseRPG() {
		return eRPG;
	}

	public void seteRPG(boolean eRPG) {
		this.eRPG = eRPG;
	}

	public void delete(String nomeMapa){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("delete from mapa where nomeMapa = ?");
			pS.setString(1, nomeMapa);
						
			pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}



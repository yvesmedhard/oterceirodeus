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


public class MusicaDAO {

	private String nomeMusica;
	private String caminhoMusica;
	
	public MusicaDAO(){
		this.nomeMusica = "";
		this.caminhoMusica = "";
	}
	
	public MusicaDAO(String nomeMusica, String caminhoMusica){
		this.nomeMusica = nomeMusica;
		this.caminhoMusica = caminhoMusica;
	}
	
	public void insert(MusicaDAO m){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("insert into musica values(?,?)");
			pS.setString(1, m.getNomeMusica());
			pS.setString(2, m.getCaminhoMusica());
			
			pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String nomeMusica){
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
			PreparedStatement pS = c.prepareStatement("delete from musica where nomeMusica = ?");
			pS.setString(1, nomeMusica);
						
			pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNomeMusica() {
		return nomeMusica;
	}
	public void setNomeMusica(String nomeMusica) {
		this.nomeMusica = nomeMusica;
	}
	public String getCaminhoMusica() {
		return caminhoMusica;
	}
	public void setCaminhoMusica(String caminhoMusica) {
		this.caminhoMusica = caminhoMusica;
	}
	
	

}



///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
///**
// *
// * @author Daniela
// */
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//
//public class ItemDAO {
//	
//	private String nome;
//	private String caminhoImagem;
//	private int bonus;
//	private String atributo;
//	
//	public ItemDAO(String nome, int bonus, String atributo, String caminhoImagem) {
//		this.nome = nome;
//		this.bonus = bonus;
//		this.atributo = atributo;
//		this.caminhoImagem = caminhoImagem;
//	}
//
//    ItemDAO(String string, int aInt, String string0, String string1, boolean aBoolean) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//	
//	public void insert(ItemDAO i){
//		try {
//			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
//			PreparedStatement pS = c.prepareStatement("insert into item values(?,?,?,?)");
//			pS.setString(1, i.getNome());
//			pS.setInt(2, i.getBonus());
//			pS.setString(3, i.getAtributo());
//			pS.setString(4, i.getCaminhoImagem());
//			
//			pS.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void delete(String nomeItem){
//		try {
//			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
//			PreparedStatement pS = c.prepareStatement("delete from item where nomeItem = ?");
//			pS.setString(1, nomeItem);
//						
//			pS.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public ItemDAO procurarItem(String nomeItem){
//		ItemDAO i = null;
//		try {
//			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:jogo", "sa", "");
//			PreparedStatement pS = c.prepareStatement("select * from personagem where nomeItem = ?");
//			
//			pS.setString(1, nomeItem);
//                        c.close();						
//			ResultSet r  = pS.executeQuery();
//			
//			while (r.next()){
//                i = new ItemDAO(r.getString("nomeItem"),r.getInt("bonusItem"),r.getString("atributoAlvo"),r.getString("caminhoImgItem"));
//             }
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//                
//		return i;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getCaminhoImagem() {
//		return caminhoImagem;
//	}
//
//	public void setCaminhoImagem(String caminhoImagem) {
//		this.caminhoImagem = caminhoImagem;
//	}
//
//	public int getBonus() {
//		return bonus;
//	}
//
//	public void setBonus(int bonus) {
//		this.bonus = bonus;
//	}
//
//	public String getAtributo() {
//		return atributo;
//	}
//
//	public void setAtributo(String atributo) {
//		this.atributo = atributo;
//	}
//	
//}


import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme
 */
public class TiposDano{
    private HashMap<String, Integer> TiposDano = new HashMap();//Usado na Arma ou Magia
    

     public TiposDano() {
        TiposDano.put("Água", 0);
        TiposDano.put("Terra", 0);
        TiposDano.put("Fogo", 0);
        TiposDano.put("Ar", 0);
        TiposDano.put("Luz", 0);
        TiposDano.put("Trevas", 0);
        TiposDano.put("Normal", 0);
    }
  
    public void setTipoDano(int ag, int te, int fo, int ar, int lu, int tr, int normal) {
        TiposDano.put("Água", ag);
        TiposDano.put("Terra", te);
        TiposDano.put("Fogo", fo);
        TiposDano.put("Ar", ar);
        TiposDano.put("Luz", lu);
        TiposDano.put("Trevas", tr);
        TiposDano.put("Normal", normal);
    }
    
    public void addValor(String tipo, int intensidade){
        TiposDano.put(tipo, TiposDano.get(tipo)+ intensidade);
    }
    
    public void setValor(String tipo, int intensidade){
        TiposDano.put(tipo,intensidade);
    }
    
    public void removerValor(String tipo){
        TiposDano.put(tipo, 0);
    }
    
    public HashMap<String, Integer> getTiposDano() {
        return TiposDano;
    }

    
    
//    public static void main (String[] args){
//        TiposDanoEfeito tipoDano = new TiposDanoEfeito("Queimadura");
//        if (tipoDano.efeito == "Queimadura"){
//            String obs = "Não contém efeito algum";
//            System.out.println(obs);
//        }
//    }
}
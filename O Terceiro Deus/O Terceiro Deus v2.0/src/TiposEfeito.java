
import java.util.HashMap;
import java.util.LinkedHashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme
 */
public class TiposEfeito {
    private HashMap<String,TiposDano> Efeitos = new HashMap();

    public TiposEfeito() {
        Efeitos.put("Investida", new TiposDano());
    }
     public void setTipoEfeito(String nome, int ag, int te, int fo, int ar, int lu, int tr, int normal) {
        
        Efeitos.get(nome).getTiposDano().put("Água", ag);
        Efeitos.get(nome).getTiposDano().put("Terra", te);
        Efeitos.get(nome).getTiposDano().put("Fogo", fo);
        Efeitos.get(nome).getTiposDano().put("Ar", ar);
        Efeitos.get(nome).getTiposDano().put("Luz", lu);
        Efeitos.get(nome).getTiposDano().put("Trevas", tr);
        Efeitos.get(nome).getTiposDano().put("Normal", normal);
    }
     
    public void setarEfeito(String nomeEfeito, String tipoDano, int valor){
       TiposDano setEfeito = Efeitos.get(nomeEfeito);
       setEfeito.setValor(tipoDano, valor);
       Efeitos.put(nomeEfeito, setEfeito);
    }
        
    public void addEfeito(String nomeEfeito, String tipoDano, int valor){
       TiposDano setEfeito = Efeitos.get(nomeEfeito);
       if(setEfeito != null){
            setEfeito.addValor(tipoDano, valor);
            Efeitos.put(nomeEfeito, setEfeito);
       }
    }
    
    public void removerEfeito(String nomeEfeito){
       Efeitos.put(nomeEfeito, new TiposDano());
    }

    public HashMap<String, TiposDano> getEfeitos() {
        return Efeitos;
    }
    
    public static void main(String[]args){
        TiposEfeito teste = new TiposEfeito();
        teste.addEfeito("Investida","Água", 0);
    }
}

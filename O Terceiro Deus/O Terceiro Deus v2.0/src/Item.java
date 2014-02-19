
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme
 */

/*
0 - Máscara
1 - Brincos
2 - Elmo
3 - Cota
4 - Calça
5 - Sapatos
6 - Luvas
7 - Capa
8 - Escudo
9 - Arma(geral)
10 - Espada de uma mão
13 - Machado de uma mão
14 - Maça de uma mão
15 - Adaga/Cortador 
16 - Segunda Espada
17 - Cetro
18 - Báculo
19 - Espada duas mãos
20 - Machado de duas mãos
21 - Maça de duas mãos
22 - Lança
23 - Lança com Objeto Afiado na ponta
24 - Arco
25 - Besta
26 - Besta dupla
27 - Garras
28 - Soqueira
29 - Armas
30 - Atirador de canhão
31 - Pá
32 - Chave inglesa
33 - Katana
34 - Leque
6 - Overall
*/
public class Item implements Comparable<Item> {
    private Image imgItemInventario;
    private String nome;
    private String descrição;
    private TiposDano tipoDano = new TiposDano();
    private TiposDano resistenciaDano = new TiposDano();
    private TiposEfeito Efeitos = new TiposEfeito();
    private TiposEfeito resistenciaEfeitos = new TiposEfeito();
    private int peso;
    private int slot;
    private int identificador;
    
    
    public Item(){
        
    }
    public Item(int identificador, String nome, String descrição, Image imgItemInventario, int peso, int slot ) {
        this.identificador = identificador;
        this.nome = nome;
        this.descrição = descrição;
        this.imgItemInventario = imgItemInventario;
        this.peso = peso;
        this.slot = slot;
        //24 armas
        //3 acessorios
        //8 equips
    }

    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public TiposDano getResistenciaDano() {
        return resistenciaDano;
    }

    public TiposDano getTipoDano() {
        return tipoDano;
    }

    public TiposEfeito getEfeitos() {
        return Efeitos;
    }

    public TiposEfeito getResistenciaEfeitos() {
        return resistenciaEfeitos;
    }

    public String getDescrição() {
        return descrição;
    }

    public int getPeso() {
        return peso;
    }

    public int getSlot() {
        return slot;
    }
    
    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Image getImgItemInventario() {
        return imgItemInventario;
    }

    public void setImgItemInventario(Image imgItemInventario) {
        this.imgItemInventario = imgItemInventario;
    }

    public static void main(String[] args){
        Item item = new Item(0, "Máscara de Fogo", "Máscara que concede ao guerreiro a fúria dos Titãns", new ImageIcon().getImage(), 10, 2);

        item.getTipoDano().setValor("Agua", 7);
        item.getTipoDano().setValor("Terra", 2);
        item.getTipoDano().setValor("Fogo", 2);
        item.getTipoDano().setValor("Ar", 5);
        item.getTipoDano().setValor("Luz", 3);
        item.getTipoDano().setValor("Trevas", 1);
        item.getTipoDano().setValor("Normal", 0);
    }

    @Override
    public int compareTo(Item o) {
        if(identificador < o.getIdentificador()){
            return -1;
        }if (identificador > o.getIdentificador()){
            return 1;
        }else{
            return 0;
        }
            
    }
}

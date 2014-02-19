package com.oterceirodeus.codigo_fonte.gameplay;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Guilherme
 */
public class Inventario {

    private Graphics2D g;
    boolean inInventario = false;
    private int DISTANCIAX;
    private int DISTANCIAY;
    private ArrayList<Image> imagemAbas = new ArrayList();
    private int indiceInventario;
    private ArrayList< ArrayList<Rectangle>> status = new ArrayList<>();
    private ArrayList< ArrayList<Rectangle>> equipamentos = new ArrayList<>();
    private ArrayList< ArrayList<Rectangle>> itens = new ArrayList<>();
    private ArrayList< ArrayList<Rectangle>> habilidades = new ArrayList<>();
    private ArrayList<Rectangle> abasRetangulos;
    private ArrayList<Item> arrayItensReserva = new ArrayList<>();
    private ArrayList<Item> arrayItensVestes = new ArrayList<>();
    private ArrayList<Polygon> setas = new ArrayList<>();
    private Image imagem;
    private boolean entrou = false;
    private Point ponto;
    private Image bg;
    private Point clicou = new Point(0, 0);
    private boolean descartar = false;
    private Rectangle descarte;
    private int pintaLinhas = 0;
    private boolean setaBaixo = false;
    private boolean setaCima = false;
    private int somaPeso = 0;
    private int somaSlot = 0;
    private int peso;
    private int slot;

    public Inventario(int distX, int distY, int F) {
        //\\\\  ||

        this.g = g;
        this.DISTANCIAX = distX;
        this.DISTANCIAY = distY;

        setCapacidadeCarga(F);

        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("C:\\Users\\Yves-Medhard\\Documents\\NetBeansProjects\\oterceirodeus\\O Terceiro Deus 3.0\\src\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Status.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("..\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Equipamento.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("..\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Itens.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("..\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Habilidades.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("..\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Equipamento2.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("src\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\Aba2_Inventario_Itens2.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("src\\com\\oterceirodeus\\assets\\sSprites\\Inventario\\Abas\\Aba2_Inventario_Habilidades2.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("src\\com\\oterceirodeus\\assets\\sprites\\Inventario\\Abas\\SetaCima.png")).getImage());
        this.imagemAbas.add(new ImageIcon(this.getClass().getResource("src\\com\\oterceirodeus\\assets\\sSprites\\Inventario\\Abas\\SetaBaixo.png")).getImage());
        setarImagem(1);

        this.abasRetangulos = addRetangulos(1, 4, 25, 25, 187, 187, 187, 40);

        this.descarte = new Rectangle(DISTANCIAX + 633, DISTANCIAY + 83, 105, 22);

        int w[] = {DISTANCIAX + 597, DISTANCIAX + 584, DISTANCIAX + 572, DISTANCIAX + 584};
        int r[] = {DISTANCIAY + 121, DISTANCIAY + 120, DISTANCIAY + 121, DISTANCIAY + 97};
        this.setas.add(new Polygon(w, r, 4));
        int x[] = {DISTANCIAX + 572, DISTANCIAX + 584, DISTANCIAX + 597, DISTANCIAX + 584};
        int y[] = {DISTANCIAY + 400, DISTANCIAY + 445, DISTANCIAY + 400, DISTANCIAY + 400};
        this.setas.add(new Polygon(x, y, 4));

        ArrayList<Rectangle> statusVestes = addRetangulosPontos(criaPontos1());
        status.add(statusVestes);

        ArrayList<Rectangle> statusVestes1 = addRetangulos(1, 3, 561, 120, 64, 65, 50, 50);
        status.add(statusVestes1);

        ArrayList<Rectangle> statusVestes2 = addRetangulos(2, 3, 561, 206, 64, 65, 50, 50);
        status.add(statusVestes2);

        ArrayList<Rectangle> statusVestes3 = addRetangulos(1, 3, 561, 356, 64, 65, 50, 50);
        statusVestes3.add(new Rectangle(DISTANCIAX + 43, DISTANCIAY + 98, 50, 50));
        status.add(statusVestes3);

        ArrayList<Rectangle> equipamentosVestes = addRetangulosPontos(criaPontos1());
        equipamentos.add(equipamentosVestes);//1

        ArrayList<Rectangle> equipamentosReserva = addRetangulos(5, 5, 430, 115, 65, 60, 50, 50);
        equipamentos.add(equipamentosReserva);//0

        ArrayList<Rectangle> itensVestes = addRetangulos(1, 3, 110, 188, 71, 60, 50, 50);
        itensVestes.add(new Rectangle(DISTANCIAX + 58, DISTANCIAY + 348, 50, 50));
        itens.add(itensVestes);

        ArrayList<Rectangle> itensReserva = addRetangulos(5, 5, 430, 115, 65, 60, 50, 50);
        itens.add(itensReserva);

        ArrayList<Rectangle> habilidadesVestes2 = addRetangulos(1, 3, 111, 137, 64, 65, 50, 50);
        habilidades.add(habilidadesVestes2);

        ArrayList<Rectangle> habilidadesVestes1 = addRetangulos(2, 3, 111, 253, 64, 65, 50, 50);
        habilidades.add(habilidadesVestes1);

        ArrayList<Rectangle> habilidadesReserva = addRetangulos(5, 5, 430, 115, 65, 60, 50, 50);
        habilidades.add(habilidadesReserva);

        arrayItensReserva = criarItens1();

        arrayItensVestes = criarItens2();

        somaTotalPeso(arrayItensVestes, arrayItensReserva);
        somaTotalSlot(arrayItensReserva);

        bg = new ImageIcon(this.getClass().getResource("..\\..\\assets\\sprites\\fundoBranco.png")).getImage();

        //  backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    }

    public void setCapacidadeCarga(int f) {
        slot = f * 10;
        peso = f * 10;
    }

    public ArrayList<Item> criarItens1() {
        ArrayList<Item> itens = new ArrayList<>();
        Item a = new Item(0, "Elmo do Infinito1", "Aumentará a concentra-/ção do guerreiro", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Elmo\\elmo1.png")).getImage(), 1, 1);
        a.getTipoDano().setTipoDano(4, 5, 7, 1, 0, 8, 9);
        a.getResistenciaDano().setTipoDano(1, 2, 3, 4, 5, 6, 7);
        itens.add(a);
        itens.add(new Item(0, "Elmo do Além", "Aumentará seu poder", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Elmo\\elmo2.png")).getImage(), 1, 1));

        itens.add(new Item(1, "Brincos do Infinito", "Aquele que a possuir/terá uma orelha de fogo", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Brincos\\brincos1.png")).getImage(), 2, 2));
        itens.add(new Item(1, "Brincos da Guerra", "Dá poderes do Deus/da Guerra", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Brincos\\brincos2.png")).getImage(), 2, 5));
        itens.add(new Item(1, "Brincos Fantasmas", "Aquele que a possuir/terá uma ghost orelha", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Brincos\\brincos3.png")).getImage(), 2, 2));
        itens.add(new Item(1, "Brincos Fênix", "Aquele que a possuir/terá o poder da ressureição", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Brincos\\brincos5.png")).getImage(), 2, 2));

        itens.add(new Item(2, "Luvas de Motoqueiro", "Essas Luvas aumentam /a sua proteção contra o frio", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Luvas\\luvas1.png")).getImage(), 2, 2));
        itens.add(new Item(2, "Luvas de Metal", "Luvas que protegem/contra possíveis/disparos de flechas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Luvas\\luvas2.png")).getImage(), 2, 2));
        itens.add(new Item(2, "Luvas de Pedra", "Luvas que aumenta/força de soco", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Luvas\\luvas4.png")).getImage(), 2, 2));

        itens.add(new Item(3, "Capa da Invisibilidade", "Aquele que a possuir/ ficará invisível", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Capa\\capa1.png")).getImage(), 2, 2));
        itens.add(new Item(3, "Capa de Voar", "Use ela, então começará/a levitar", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Capa\\capa2.png")).getImage(), 2, 2));
        itens.add(new Item(3, "Capa de Borracha", "Isolante natural", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Capa\\capa4.png")).getImage(), 2, 2));

        itens.add(new Item(4, "Sapatos do Infinito", "Keep Walking to Infinite", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Botas\\botas1.png")).getImage(), 2, 2));
        itens.add(new Item(4, "Botas de Couro", "Retiradas do Gato de/Botas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Botas\\botas2.png")).getImage(), 2, 2));
        itens.add(new Item(4, "Botas de Cowboy", "Fique no estilo/faroeste", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Botas\\botas4.png")).getImage(), 2, 2));

        itens.add(new Item(5, "Calça de Pano", "Calça feita de linha", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Calça\\calça1.png")).getImage(), 2, 2));
        itens.add(new Item(5, "Calça de Alumínio", "Calça feita de/Alumínio", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Calça\\calça2.png")).getImage(), 2, 2));
        itens.add(new Item(5, "Calça de Moleton", "Calça feita para/esquentar", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Calça\\calça4.png")).getImage(), 2, 2));

        itens.add(new Item(6, "Espada de Lâmina Invertida", "Uma vez utilizada/por Hitokiri Batousai", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Espada\\espada1.png")).getImage(), 2, 2));
        itens.add(new Item(6, "Espada de Artur", "Escalibur : A espada/que tudo corta", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Espada\\espada2.png")).getImage(), 2, 2));
        itens.add(new Item(6, "Espada Tessaiga", "Toma o poder do último/ oponente vencido", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Espada\\espada4.png")).getImage(), 2, 2));

        itens.add(new Item(7, "Cota de Aço", "Impenetrável", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Cota\\cota1.png")).getImage(), 2, 2));
        itens.add(new Item(7, "Cota de Bronze", "Com meia lua + ->/, solta meteoros de pégasus", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Cota\\cota2.png")).getImage(), 2, 2));
        itens.add(new Item(7, "Cota de Prata", "Cota feita com a/prata e o poder/das espadas perdidas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Cota\\cota4.png")).getImage(), 2, 2));

        itens.add(new Item(8, "Escudo de Madeira", "Escudo feito de/árvores centenárias", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Escudo\\escudo1.png")).getImage(), 2, 2));
        itens.add(new Item(8, "Escudo de Aço", "Escudo feito de/panelas velhas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Escudo\\escudo2.png")).getImage(), 2, 2));
        itens.add(new Item(8, "Escudo de Madeira", "Fraco contra Fogo", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Escudo\\escudo4.png")).getImage(), 2, 2));

        itens.add(new Item(9, "Máscara do Infinito", "Aquele que a possuir/terá a cara tampada", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Mascara\\mascara1.png")).getImage(), 2, 2));
        itens.add(new Item(9, "Máscara Fantasma", "Aquele que a possuir/terá um bigode trasparente", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Mascara\\mascara2.png")).getImage(), 1, 2));
        itens.add(new Item(9, "Máscara Nariz de Palhaço", "Aquele que a possuir/terá um nariz de palhaço", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Mascara\\mascara4.png")).getImage(), 1, 3));

        return itens;
    }

    public ArrayList<Item> criarItens2() {
        ArrayList<Item> itens = new ArrayList<>();

        itens.add(new Item(0, "Elmo do Infinito", "Aumentará a concentra-/ção do guerreiro", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Elmo\\elmo3.png")).getImage(), 2, 2));

        itens.add(new Item(1, "Brincos da Guerra", "Dá poderes do Deus/da Guerra", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Brincos\\brincos4.png")).getImage(), 2, 2));

        itens.add(new Item(2, "Luvas de Metal", "Luvas que protegem/contra possíveis/disparos de flechas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Luvas\\luvas3.png")).getImage(), 2, 2));

        itens.add(new Item(3, "Capa de Voar", "Use ela, então começará/a levitar", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Capa\\capa3.png")).getImage(), 2, 2));

        itens.add(new Item(4, "Botas de Couto", "Retiradas do Gato de/Botas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Botas\\botas3.png")).getImage(), 2, 2));

        itens.add(new Item(5, "Calça de Alumínio", "Calça feita de Alumínio", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Calça\\calça3.png")).getImage(), 2, 2));

        itens.add(new Item(6, "Espada de Artur", "Escalibur : A espada que/tudo corta", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Espada\\espada3.png")).getImage(), 2, 2));

        itens.add(new Item(7, "Cota de Aço", "Impenetrável", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Cota\\cota3.png")).getImage(), 2, 2));

        itens.add(new Item(8, "Escudo de Aço", "Escudo feito de/panelas velhas", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Escudo\\escudo3.png")).getImage(), 2, 2));

        itens.add(new Item(9, "Máscara Fantasma", "Aquele que a possuir/terá um bigode trasparente", new ImageIcon(this.getClass().getResource("Sprites\\Inventario\\Mascara\\mascara3.png")).getImage(), 2, 2));

        return itens;
    }

    public void somaTotalPeso(ArrayList<Item> itens1, ArrayList<Item> itens2) {
        int soma1 = 0;
        int soma2 = 0;

        for (int i = 0; i < itens1.size(); i++) {
            soma1 += itens1.get(i).getPeso();
        }

        for (int j = 0; j < itens2.size(); j++) {
            soma2 += itens2.get(j).getPeso();
        }

        somaPeso = soma1 + soma2;
    }

    public void somaTotalSlot(ArrayList<Item> itens1) {
        int soma1 = 0;
        for (int i = 0; i < itens1.size(); i++) {
            soma1 += itens1.get(i).getSlot();
        }
        somaSlot = soma1;
    }

    public ArrayList<Rectangle> addRetangulos(int linhas, int colunas, int distBordax, int distBorday, int distRetx, int distRety, int altura, int largura) {
        ArrayList<Rectangle> r = new ArrayList<>();
        for (int j = 0; j < linhas; j++) {
            for (int i = 0; i < colunas; i++) {
                r.add(new Rectangle(DISTANCIAX + distBordax + (i * distRetx), DISTANCIAY + distBorday + (j * distRety), altura, largura));
            }
        }
        return r;
    }

    public ArrayList<Point> criaPontos1() {
        ArrayList<Point> pontos = new ArrayList<>();
        pontos.add(new Point(112, 98));
        pontos.add(new Point(85, 175));
        pontos.add(new Point(60, 255));
        pontos.add(new Point(76, 358));
        pontos.add(new Point(185, 360));
        pontos.add(new Point(275, 360));
        pontos.add(new Point(315, 295));
        pontos.add(new Point(323, 213));
        pontos.add(new Point(324, 147));
        pontos.add(new Point(250, 100));
        return pontos;
    }

    public ArrayList<Rectangle> addRetangulosPontos(ArrayList<Point> pontos) {
        ArrayList<Rectangle> r = new ArrayList<>();
        for (int i = 0; i < pontos.size(); i++) {
            r.add(new Rectangle(DISTANCIAX + pontos.get(i).x, DISTANCIAY + pontos.get(i).y, 50, 50));
        }
//       r.add(new Rectangle(DISTANCIAX + 112,DISTANCIAY + 98, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 85,DISTANCIAY + 175, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 60,DISTANCIAY + 255, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 76,DISTANCIAY + 358, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 185,DISTANCIAY + 360, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 275,DISTANCIAY + 360, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 315,DISTANCIAY + 295, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 323,DISTANCIAY + 213, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 324,DISTANCIAY + 147, 50, 50));
//       r.add(new Rectangle(DISTANCIAX + 250,DISTANCIAY + 100, 50, 50));

        return r;
    }

    public void pintarRetangulos(int linhas, int colunas, int distBordax, int distBorday, int distRetx, int distRety, int larguraRet, int alturaRet, Graphics bgg) {
        for (int j = 0; j < linhas; j++) {
            for (int i = 0; i < colunas; i++) {
                bgg.drawRect(distBordax + (i * distRetx), distBorday + (j * distRety), larguraRet, alturaRet);
            }
        }
    }

    public void pintarEquipamentos(int começo, int linhas, int colunas, int distBordax, int distBorday, int distRetx, int distRety, int larguraRet, int alturaRet, ArrayList<Item> itens1, Graphics bbg) {
        int fim = itens1.size();
        if (começo < fim) {
            for (int j = 0; j < linhas; j++) {
                for (int i = 0; i < colunas; i++) {
                    bbg.drawImage(itens1.get(começo).getImgItemInventario(), distBordax + (i * distRetx), distBorday + (j * distRety), larguraRet, alturaRet, null);
                    começo++;
                    if (começo >= fim) {
                        j = linhas;
                        i = colunas;
                    }
                }
            }
        }
    }

    public void pintarEquipamentosPontos(ArrayList<Point> pontos, ArrayList<Item> itens, Graphics bbg) {
        ArrayList<Rectangle> r = new ArrayList<>();
        for (int i = 0; i < pontos.size(); i++) {
            bbg.drawImage(itens.get(i).getImgItemInventario(), pontos.get(i).x, pontos.get(i).y, 50, 50, null);
        }
    }

    public int procuraIndice(ArrayList<Item> itens, int indice) {
        for (int j = 0; j < itens.size(); j++) {
            if (itens.get(j).getIdentificador() == indice) {
                return j;
            }
        }
        return -1;
    }

    public void checarColisaoAbas(MouseEvent e) {
        for (int i = 0; i < abasRetangulos.size(); i++) {
            if (abasRetangulos.get(i).contains(e.getLocationOnScreen())) {
                setarImagem(i);
                descartar = false;
            }
        }
        if (descarte.contains(e.getLocationOnScreen())) {
            switch (indiceInventario) {
                case 1:
                    if (descartar) {
                        descartar = false;
                        setarImagem(1);
                    } else {
                        descartar = true;
                        setarImagem(4);
                    }
                    break;
                case 2:
                    if (descartar) {
                        descartar = false;
                        setarImagem(2);
                    } else {
                        descartar = true;
                        setarImagem(5);
                    }
                    break;
                case 3:
                    if (descartar) {
                        descartar = false;
                        setarImagem(3);
                    } else {
                        descartar = true;
                        setarImagem(6);
                    }
                    break;
            }
        }
    }

    public boolean checarColisaoRetangulos(MouseEvent e, ArrayList< ArrayList<Rectangle>> retangulos) {
        for (int i = 0; i < retangulos.size(); i++) {
            for (int j = 0; j < retangulos.get(i).size(); j++) {
//                 //System.out.println( retangulos.get(i).get(j) );
                if (retangulos.get(i).get(j).contains(e.getLocationOnScreen())) {
//                    //System.out.println("ENCONTROU");
                    clicou = new Point(i, j);
                    return true;
                }
            }
        }
        return false;
//         //System.out.println();
    }

    public void setarImagem(int indice) {
        imagem = imagemAbas.get(indice);

        if (indice != 4 && indice != 5 && indice != 6) {
            indiceInventario = indice;
        }
    }

    public void pintar(Graphics2D g2d) {
        Graphics2D bbg = g2d;

      //  bbg.drawImage(bg, 0, 0, null);
        bbg.drawImage(imagem, 25, 25, null);
        pintarEscopo(bbg);
        if (indiceInventario == 0) {
            setaBaixo = false;
        }
        if (indiceInventario == 1) {
            pintarEquipamentos(pintaLinhas, 5, 5, 430, 115, 65, 60, 50, 50, arrayItensReserva, bbg);
            pintarEquipamentosPontos(criaPontos1(), arrayItensVestes, bbg);
            if (arrayItensReserva.size() > 25) {
                if (setaCima) {
                    bbg.drawImage(imagemAbas.get(7), 573, 100, null);
                }
                bbg.drawImage(imagemAbas.get(8), 573, 400, null);
                setaBaixo = true;
            } else {
                setaBaixo = false;
                setaCima = false;
                pintaLinhas = 0;
            }

            if (entrou == true) {

                bbg.setColor(Color.BLACK);
                if (clicou.x == 0) {
                    bbg.drawString(arrayItensVestes.get(clicou.y).getNome(), 95, 470);
                    pintarDescricao(bbg, arrayItensVestes, clicou.y);
                    pintarDanos(bbg, arrayItensVestes, clicou.y);
                } else if (clicou.x == 1) {
                    if (clicou.y + pintaLinhas < arrayItensReserva.size()) {
                        bbg.drawString(arrayItensReserva.get(clicou.y + pintaLinhas).getNome(), 95, 470);
                        pintarDescricao(bbg, arrayItensReserva, clicou.y + pintaLinhas);
                        pintarDanos(bbg, arrayItensReserva, clicou.y + pintaLinhas);
                    }
                }
            }
        }
        if (indiceInventario == 2) {
            setaBaixo = false;
        }
        if (indiceInventario == 3) {
            setaBaixo = false;
        }

        //   g.drawImage(backBuffer, 0, 0, null);
    }

    public void pintarDescricao(Graphics bbg, ArrayList<Item> itens, int indice) {
        String[] a = itens.get(indice).getDescrição().split("/");
        for (int i = 0; i < a.length; i++) {
            bbg.drawString(a[i], 115, 510 + (i * 15));
        }
    }

    public void pintarDanos(Graphics bbg, ArrayList<Item> itens, int indice) {

        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Água").toString(), 430, 450);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Terra").toString(), 430, 468);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Fogo").toString(), 430, 486);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Ar").toString(), 430, 504);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Luz").toString(), 430, 522);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Trevas").toString(), 430, 540);
        bbg.drawString(itens.get(indice).getTipoDano().getTiposDano().get("Normal").toString(), 430, 558);

        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Água").toString(), 640, 450);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Terra").toString(), 640, 468);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Fogo").toString(), 640, 486);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Ar").toString(), 640, 504);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Luz").toString(), 640, 522);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Trevas").toString(), 640, 540);
        bbg.drawString(itens.get(indice).getResistenciaDano().getTiposDano().get("Normal").toString(), 640, 558);

        bbg.drawString(String.valueOf(itens.get(indice).getPeso()), 715, 470);
        bbg.drawString(String.valueOf(itens.get(indice).getSlot()), 715, 525);
    }

    public void pintarEscopo(Graphics bbg) {

        bbg.setColor(Color.BLUE);
        bbg.setFont(new Font("Myriad Pro", 0, 15));
        bbg.drawString("Peso: ", 430, 100);
        bbg.drawString("Slots: ", 530, 100);
        bbg.setColor(Color.BLACK);
        bbg.drawString(String.valueOf(somaPeso) + "/" + String.valueOf(peso), 470, 100);
        bbg.drawString(String.valueOf(somaSlot) + "/" + String.valueOf(slot), 570, 100);

        bbg.setColor(Color.BLUE);
        bbg.setFont(new Font("Myriad Pro", 0, 15));
        bbg.drawString("Nome: ", 50, 470);
        bbg.drawString("Descrição: ", 50, 510);

//        bbg.setFont(new Font("Myriad Pro",0,17));
//        bbg.setColor(Color.BLUE);
        bbg.drawString("Danos: ", 286, 505);

        bbg.setColor(Color.BLACK);
//        bbg.setFont(new Font("Myriad Pro",0,15));
        bbg.drawString("Água: ", 350, 450);
        bbg.drawString("Terra: ", 350, 468);
        bbg.drawString("Fogo: ", 350, 486);
        bbg.drawString("Ar: ", 350, 504);
        bbg.drawString("Luz: ", 350, 522);
        bbg.drawString("Trevas: ", 350, 540);
        bbg.drawString("Normal: ", 350, 558);

//        bbg.setFont(new Font("Myriad Pro", 0, 17));
        bbg.setColor(Color.BLUE);
        bbg.drawString("Resistências: ", 460, 505);

        bbg.setColor(Color.BLACK);
//        bbg.setFont(new Font("Myriad Pro", 0, 15));
        bbg.drawString("Água: ", 560, 450);
        bbg.drawString("Terra: ", 560, 468);
        bbg.drawString("Fogo: ", 560, 486);
        bbg.drawString("Ar: ", 560, 504);
        bbg.drawString("Luz: ", 560, 522);
        bbg.drawString("Trevas: ", 560, 540);
        bbg.drawString("Normal: ", 560, 558);

//        bbg.setFont(new Font("Myriad Pro", 0, 17));
        bbg.setColor(Color.BLUE);
        bbg.drawString("Peso: ", 670, 470);
        bbg.drawString("Slots: ", 670, 525);
    }

  

    public void inventarioMouseInComandsPressed(MouseEvent e) {
        checarColisaoAbas(e);
        if (setaBaixo) {
            if (setas.get(1).contains(e.getLocationOnScreen())) {
                pintaLinhas += 5;
                setaCima = true;
            }
        }
        if (setaCima) {
            if (setas.get(0).contains(e.getLocationOnScreen())) {
                pintaLinhas -= 5;
                if (pintaLinhas <= 0) {
                    pintaLinhas = 0;
                    setaCima = false;
                }
            }
        }
        //System.out.println(setaCima);

        switch (indiceInventario) {
            case 0:
                //System.out.println(checarColisaoRetangulos(e, status));
                break;
            case 1:
                if (checarColisaoRetangulos(e, equipamentos)) {
                    if (clicou.x == 0 && clicou.y < arrayItensVestes.size() && arrayItensVestes.get(clicou.y).getNome() != null) {
                        if (!descartar) {
                            arrayItensReserva.add(0, this.arrayItensVestes.get(clicou.y));

                        }
                        this.arrayItensVestes.set(clicou.y, new Item());
                        entrou = false;
                        somaTotalPeso(arrayItensReserva, arrayItensVestes);
                        somaTotalSlot(arrayItensReserva);

                    } else if (clicou.x == 1) {
                        if (clicou.y + pintaLinhas < arrayItensReserva.size()) {
                            if (!descartar) {
                                Item a = arrayItensVestes.get(arrayItensReserva.get(clicou.y + pintaLinhas).getIdentificador());
                                arrayItensVestes.set(arrayItensReserva.get(clicou.y + pintaLinhas).getIdentificador(), this.arrayItensReserva.get(clicou.y + pintaLinhas));
                                arrayItensReserva.remove(clicou.y + pintaLinhas);
                                if (a.getNome() != null) {
//                                  arrayItensReserva.add(clicou.y, a);
                                    arrayItensReserva.add(0, a);

                                }
                                somaTotalSlot(arrayItensReserva);
                                //System.out.println(arrayItensReserva.size());
                            } else {
                                arrayItensReserva.remove(clicou.y + pintaLinhas);
                                entrou = false;
                                somaTotalPeso(arrayItensReserva, arrayItensVestes);
                                somaTotalSlot(arrayItensReserva);
                            }
                        }
                    }
                }

                break;
            case 2:
                //System.out.println(checarColisaoRetangulos(e, itens));
                break;
            case 3:
                //System.out.println(checarColisaoRetangulos(e, habilidades));
                break;
        }
        //System.out.println(clicou);
        //System.out.println(clicou.y + pintaLinhas);
    }

    public void inventarioMouseInComandsMoved(MouseEvent e) {
        if (indiceInventario == 0) {
//            Point i = checarColisaoRetangulos(e, status);
//            //System.out.println(i);
        }
        if (indiceInventario == 1) {
            if (checarColisaoRetangulos(e, equipamentos)) {
                if (clicou.x == 0 && arrayItensVestes.get(clicou.y).getNome() != null) {
                    entrou = true;
                } else if (clicou.x == 1 && clicou.y + pintaLinhas < arrayItensReserva.size()) {
                    entrou = true;
                    //System.out.println(arrayItensReserva.get(clicou.y + pintaLinhas).getNome());
                }
            } else {
                entrou = false;
            }
//            if(ponto.x != -1){
//                this.entrou = true;
//            }else{
//                this.entrou = false;
//            }

        }
        if (indiceInventario == 2) {
//            Point i = checarColisaoRetangulos(e, itens);
//            //System.out.println(i);
        }
        if (indiceInventario == 3) {
//            Point i = checarColisaoRetangulos(e, habilidades);
//            //System.out.println(i);
        }
    }

    public boolean isInInventario() {
        return inInventario;
    }

    public void setIndiceInventario(int indiceInventario) {
        this.indiceInventario = indiceInventario;
    }

    public int getIndiceInventario() {
        return indiceInventario;
    }

    public void setInInventario(boolean inInventario) {
        this.inInventario = inInventario;
    }
}
//
//public static void main(String[] args) {
//        testeMouse teste = new testeMouse();
//    }
//class testeMouse extends JFrame {
//
//    private Graphics2D grafico;
//    private int DISTANCIAX = 300;
//    private int DISTANCIAY = 100;
//
//    public testeMouse() {
//        setTitle("Inventario");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        setBounds(DISTANCIAX, DISTANCIAY, 800, 600);
//
//        setResizable(false);
//        setVisible(true);
//        this.grafico = (Graphics2D) getGraphics();
//
//        Inventario invent = new Inventario(grafico, DISTANCIAX, DISTANCIAY, 5);
//
//        addMouseListener(invent);
//        addMouseMotionListener(invent);
//        addKeyListener(invent);
//
//        while (true) {
//            if (invent.isInInventario()) {
//                invent.pintar();
//
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(testeMouse.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            } else {
//                this.dispose();
//            }
//        }
//
//    }
//    public static void main(String []args){
//        Inventario invent = new Inventario();
//        
//    }
//

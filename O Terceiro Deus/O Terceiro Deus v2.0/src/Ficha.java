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
        setImage("Sprites\\Hitsugaia.png");
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

class Raça {

    private String nome;
    private String descricao;
    private Map<String, Integer> atributos = new LinkedHashMap<>();
    private Map<String, Integer> focus = new LinkedHashMap<>();
    private ArrayList<String[]> vantagens = new ArrayList<>();
    private ArrayList<String[]> desvantagens = new ArrayList<>();
    private ArrayList<Classe> classes = new ArrayList<>();
    private TiposDano resistenciaDano = new TiposDano();// valores negativos demonstran vulnerabilidade, positivos resistencia. 0 é o normal
    private TiposEfeito resistenciaEfeitos = new TiposEfeito();// valores negativos demonstran vulnerabilidade, positivos resistencia. 0 é o normal
    private int custo;

    public Raça() {
        setAtributos(10, 10, 10, 10, 10);
        setFocus(10, 10, 10, 10, 10, 10);

    }

    public TiposDano getResistenciaDano() {
        return resistenciaDano;
    }

    public TiposEfeito getResistenciaEfeitos() {
        return resistenciaEfeitos;
    }

    public Map<String, Integer> getAtributos() {
        return atributos;
    }

    public void setAtributos(int For, int Hab, int Res, int Arm, int PdF) {
        this.atributos.put("Força", For);
        this.atributos.put("Habilidade", Hab);
        this.atributos.put("Resistência", Res);
        this.atributos.put("Armadura", Arm);
        this.atributos.put("PdF", PdF);
    }

    public Map<String, Integer> getFocus() {
        return focus;
    }

    public void setFocus(int ag, int te, int fo, int ar, int lu, int tr) {
        this.focus.put("Água", ag);
        this.focus.put("Terra", te);
        this.focus.put("Fogo", fo);
        this.focus.put("Ar", ar);
        this.focus.put("Luz", lu);
        this.focus.put("Trevas", te);
    }

    public ArrayList<Raça> gerarRacas() {
        ArrayList<Raça> arrayRacas = new ArrayList();

        //////Humano
        Raça c = new Raça();
        c.setNome("Humano");
        c.setDescricao("No Reinado de Arton, a supremacia dos humanos é total. Quase todos os reinos têm uma população humana que supera todas as outras raças juntas. As únicas exeções são Tapista e Doherimm, reinos respectivamente governados por minotauros e anões.\n"
                + "O tipo humano mais comum no Reinado é a raça branca, originária de Lamnor, o continente onde a civilização teve origem. Os homens medem entre 1,60 e 1,80m de altura, e pesam entre 60 e 80kg. As mulheres são significativamente mais baixas e leves. Cabelos pretos e lisos são mais comuns, mas eles também são encontrados encaracolados e nas cores castanha, ruiva ou loura(rara). Olhos castanhos ou presos tão frequentes, enquanto olhos azuis e verdes são raros. A pele é moreno-clara, mas pode ser muito pálida ou escura."
                + "\nRepresentandes da raça negra são originários de tribos barbáras que antes habitavam o território do Reinado. com a colonização, estas tribos foram extintas, expulsas ou assimiladas como parte do povo - e hoje podem ser encontrados ocupando qualquer cargo ou exercendo qualquer atividade destinada a humanos. Os negros tendem a ser um pouco mais altos e fortes, com o cabelo liso ou encaracoolado. Existem aqueles de pele negra com olhos e/ou cabelos claros, embora tais indivíduos sejam muito raros. Na Grande Savana ao norte do Reinado ainda existem tribos em estado selvagem."
                + "zn Nem todos os bárbaros nativos são negros. As Montanhas Uivantes são até hoje habitadas por humanos de pele branca, olhos claros e cabelos de cores selvagensm que vão do vermelho ao ouro. eles são conhecidos como 'bárbaros do gelo', e raramente abandonam seu reino. Temos ainda os bárbaros originários das Montanhas Snguinárias - povo com pele cor de bronze, olhos e cabelos escuros. Estes são muitos raros no Reinado."
                + "\n Representantes da raça amarela de Tamu-ra são raros, restritos ao bairro de Nitamu-ra em Valkaria. Eles ão em média mais baixos que os brancos, mas alguns podem ser muitos altos. Seus olhos amendoados são muitas vezes confundidos com olhos élficos (um comentário que não costuma agradar muito os elfos), de forma que um tamuraniano com orelhas ocultas pode ser fazer passar por elfo facilmente. Têm cabelos negros e lisos, e olhos negros - mas alguns membros desta raça apresentam olhos e cabelos de cores muito estranhas");
        c.setVantagens("Multiclasse", "Diferente de anões ou minotauros, todos os humanos podem seguir qualquer profissão que desejem, a não ser que não tenham os requesitos para tal. ");
        c.setDesvantagens("Nenhuma", "A raça humana foi criada pela deusa Valkaria para superar até mesmo os deuses, e por isso não tem qualquer tipo de desvantagem natural, porém tambem não tem nenhuma vantagem como as outras raças");
        c.setAtributos(0, 0, 0, 0, 0);
        c.setFocus(0, 0, 0, 0, 0, 0);
        c.setCusto(0);
        c.resistenciaDano.setTipoDano(0, 0, 0, 0, 0, 0, 0);
        c.resistenciaEfeitos.setTipoEfeito("Investida", 0, 0, 0, 0, 0, 0, 1);

        
            c.classes.add(new Classe().gerarClasses().get(0));
            c.classes.add(new Classe().gerarClasses().get(1));
      
        arrayRacas.add(c);

        //////Elfo
        Raça c2 = new Raça();
        c2.setNome("Elfo");
        c2.setDescricao("Os elfos de Arton são uma raça muito antiga. Eles têm as orelhas pontiagudas, olhos amendoados, traços delicados, afinidade com a natureza, talento para a mágica e vida muito longa. Exceto por esses detalhes são fisicamente muito parecidos com humanos. Têm a mesma altura e peso - e, embora sejam normalmente mais delgados, alguns se mostram tão musculosos e massivos quanto qualquer humano. Podem mostrar também uma vasta coleção de traços exóticos, como cabelos de cores estranhas, olhos felinos, patas, cauda..."
                + "\nO elfo nativo de Arton costuma atingir 250 anos de idade em média três vezes mais que um humano - e a mesma longevidade dos anões. Essa vida longa exerce forte influência sobre sua personalidade e ajuda a explicar seu comportamento. Elfor apreciam os prazeres da vida mais lentamente, e por isso são exigentes quanto à comida, roupa, beleza, artes e prazeres em geral. Apenas os maiores amigos e inimigos são lembrados por eles( afinal, é impossível lembrar de todo mundo que você conhece em cem ou duzentos anos...). Todas essas coisas costumam fazer com que os elfos sejam vistos pelos humanos como frívolos, distantes e até arrogantes."
                + "\nNo Reinado atual, os elfos vivem em condições miseráveis. Isso começou quando a primeira cidade élfica, Lenórienn, foi erguida no continente de Lamnor em pleno território hobgoblin, dando início a uma série secular de conflitos que ficaria conhecida como a Infinita Guerra. Em sua arrogância, os elfos jamais aceitaram qualquer acordo ou aliança om outras 'raças inferiores' - provocando o ressentimento dos reinos humanos, que assinaram então o Tradado de Lamnor: todos passariam a ignorar os elfos, sem jamais interferir em seus assuntos."
                + "\nInfelizmente para os elfos, surgiu o vilão Thwor Ironfist - a Foice de Ragnar, o líder previsto pelas profecias dos bugbears. Ele unificou todos os goblinóides em um exército invencivel que ficarica conhecido como a Aliança Negra. Diante de tal força, Lenórienn caiu. Quase todos os seus habitantes foram mortos. Caravanas de sobreviventes fugiram de Lamnor e vieram para o Reinado e outros pontos  do mundo onde vivem humilhados entre os humanos ou em perigrinação constante, jamais se estabelecendo em um mesmo lugar. Não existe mais uma nação élfica em nenhum ponto de Arton, e no Reinado eles são tão poucos que não chegam a formar 1% da popução em quase todos os reinos."
                + "\n A maior parte dos elfos não gonsegue deixar de lado sua antiga arrogtância, jamais perdendo uma chance de depreciar os humanos( o que não é muito saudável quando você vive na terra DELES). Outro, no entantom, empenham-se em cruzadas pessoais para que os elfos e humanos sejam amigos. De qualquer forma, a convivência com os humanos - o resultado da união entre um humanos e uma elfa ou um elfo e uma humana."
                + "\nEmbora tenham sido criados pela deusa Glórien, os elfos atuais estão se afastando dela, magoados com sua situação. Eles estão se voltando para Allihanna, Khalmyr, Lena, Marah e outros. Um meio-elfo clérigo pode servir a qualquer divindade que aceite apenas humanos e/ou elfos. Meio-elfos também podem ser paladinos");
        c2.setVantagens("Raça Pura", "Elfos Puros recebem um bônus de H+1 e FA +1 quando usam espadas longas ou arcos.");
        c2.setVantagens("Visão na penumbra", "Elfos são capazes de ver no escuro com perfeição, mas não na escuridão total: para isso deve existir uma iluminação mínima, por menor que seja");
        c2.setDesvantagens("Resistência baixa", "Elfos sempre fazem seus testes de Resistência com um redutor de -1. Isso não afeta sua Resistência verdadeira nem seus pondos de Vida ou Magia");
        c2.setAtributos(1, 1, 0, 0, 0);
        c2.setFocus(0, 0, 0, 0, 0, 0);
        c2.setCusto(2);
        c2.resistenciaDano.setTipoDano(0, 0, 0, 0, 0, 0, 1);
        c2.resistenciaEfeitos.setTipoEfeito("Investida", 0, 0, 0, 0, 0, 0, 1);
        arrayRacas.add(c2);
        c2.classes.add(new Classe().gerarClasses().get(0));
        return arrayRacas;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
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

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(Classe classe) {
        this.classes.add(classe);
    }

    public ArrayList<String[]> getVantagens() {
        return vantagens;
    }

    public void setVantagens(String nome, String descricao) {
        String[] vantagem = new String[2];
        vantagem[0] = nome;
        vantagem[1] = descricao;
        this.vantagens.add(vantagem);
    }

    public ArrayList<String[]> getDesvantagens() {
        return desvantagens;
    }

    public void setDesvantagens(String nome, String descricao) {
        String[] desvantagem = new String[2];
        desvantagem[0] = nome;
        desvantagem[1] = descricao;
        this.desvantagens.add(desvantagem);
    }
}

class Classe {

    private String nome;
    private String descricao;
    private Map<String, Integer> atributos = new LinkedHashMap<>();
    private Map<String, Integer> focus = new LinkedHashMap<>();
    private ArrayList<String[]> vantagens = new ArrayList<>();
    private ArrayList<String[]> desvantagens = new ArrayList<>();
    private int custo;
    private TiposDano resistenciaDano = new TiposDano();// valores negativos demonstran vulnerabilidade, positivos resistencia. 0 é o normal
    private TiposEfeito resistenciaEfeitos = new TiposEfeito();// valores negativos demonstran vulnerabilidade, positivos resistencia. 0 é o normal
    private int pvs = 3, pms = 3;

    public Classe() {
        setAtributos(0, 0, 100, 0, 0);
        setFocus(0, 0, 0, 0, 0, 0);

    }

    public TiposDano getResistenciaDano() {
        return resistenciaDano;
    }

    public void setResistenciaDano(TiposDano resistenciaDano) {
        this.resistenciaDano = resistenciaDano;
    }

    public TiposEfeito getResistenciaEfeitos() {
        return resistenciaEfeitos;
    }

    public void setResistenciaEfeitos(TiposEfeito resistenciaEfeitos) {
        this.resistenciaEfeitos = resistenciaEfeitos;
    }

    public Map<String, Integer> getAtributos() {
        return atributos;
    }

    public void setAtributos(int For, int Hab, int Res, int Arm, int PdF) {
        this.atributos.put("Força", For);
        this.atributos.put("Habilidade", Hab);
        this.atributos.put("Resistência", Res);
        this.atributos.put("Armadura", Arm);
        this.atributos.put("PdF", PdF);
    }

    public Map<String, Integer> getFocus() {
        return focus;
    }

    public void setFocus(int ag, int te, int fo, int ar, int lu, int tr) {
        this.focus.put("Água", ag);
        this.focus.put("Terra", te);
        this.focus.put("Fogo", fo);
        this.focus.put("Ar", ar);
        this.focus.put("Luz", lu);
        this.focus.put("Trevas", te);
    }
    //Comentei porqe tava dando erros aqui.
//    public ArrayList<Classe> getClassesfrombdd(){
//        ArrayList<ClasseDAO> classes = new ArrayList();
//        ArrayList<Classe> arrayClasse = new ArrayList();
//        classes = new ClasseDAO().getClasses();
//        for(ClasseDAO cdao: classes){
//          Classe c = new Classe();
//           c.setNome(cdao.getNome());
//           c.setDescricao(cdao.getDescricao());
//           c.setVantagens(cdao.getVantagens());
//           c.setDesvantagens(cdao.getDesvantagens());
//           c.setAtributos(cdao.getForca(), cdao.getHab(), cdao.getRes(), cdao.getArm(), cdao.getpDF());
//           c.setFocus(cdao.getAgua(), cdao.getTerra(), cdao.getFogo(), cdao.getAr(), cdao.getLuz(), cdao.getTrevas());
//           c.setPms(cdao.getPms());
//           c.setPvs(cdao.getPvs());
//           c.TipodeDano = cdao.gettiposdedano;
//           c.Efeitos
//            
//        }
//        
//        return classes;
//    }

    public ArrayList<Classe> gerarClasses() {
        ArrayList<Classe> arrayClasse = new ArrayList();

        //////Guerreiro
        Classe c = new Classe();
        c.setNome("Guerreiro");
        c.setDescricao("Este é o típico jovem que se arma com espada e escudo e abandona sua vila na companhia de colegas aventureiros. É a forma mais simples e conhecida de herói aventureiro, o tipo mais comum em Arton. "
                + "\n Não é preciso muita coisa para ser um Guerreiro: basta uma arma e a disposição para usá-la. Todasas raças possuem guerreiros. Será rara uma aldeia ou vila — por menor que seja — sem pelo menos um Guerreiro entre seus moradores.");
        c.setVantagens("Ataque multiplo", "Com treino um guerreiro pode realizar mais de um ataque ao mesmo tempo. \nEsta vantagem permite melhorar seus movimentos");
        c.setDesvantagens("Furia Guerreira", "Quando fica com menos de 10% da sua vida, o guerreiro entra em estado de furia e não pensa direito ao atacar. Nesse estado o guerreiro não pode usar magia ou realizar qualquer movimento que use Mana, mas fica com sua força e armadura aumentadas");
        c.setAtributos(2, 0, 0, 1, 0);
        c.setFocus(0, 0, 0, 0, 0, 0);
        c.setCusto(3);
        c.setPms(3);
        c.setPvs(5);
        c.resistenciaDano.setTipoDano(0, 0, 0, 0, 0, 0, 0);
        c.resistenciaEfeitos.setTipoEfeito("Investida", 0, 0, 0, 0, 0, 0, 1);
        arrayClasse.add(c);

        //////Mago
        Classe c2 = new Classe();
        c2.setNome("Mago");
        c2.setDescricao("A conquista de poder mágico é difícil, exigindo anos de estudo e empenho. Ser capaz de fazer mágica já é uma grande vitória. Assim, muitos Magos não se tornam especialistas neste ou naquele tipo de magia, preferindo manter sua versatilidade."
                + "\n O Mago Comum também é aquele que ainda não teve contato com as formas mais sofisticadas de magia. Pode ser o aluno que ainda não recebeu todos os ensinamentos de seu mestre, o único Mago de uma pequena comunidade isolada, ou um jovem que descobriu um velho grimório entre os pertences da família — alguém que esteja apenas começando sua jornada no mundo da mágica. "
                + "\nQuando passam a conhecer melhor todas as formas de magia, Magos Comuns tendem a se tornar especialistas. ");
        c2.setVantagens("Arcano", "Todos os magos tem alguma vantagem mágica, e por isso começam com 1 em todos os caminhos da magia");
        c2.setDesvantagens("Feitiche", "Poderes mágicos são dificeis de controlar, e por isso a maioria dos magos usa algum tipo de condutor para canalizar este poder. Este condutor pode ser uma varinha, um cajado, um simbolo sagrado ou uma vara. O Mago tem de estar com este condotor para realizar magia do contrario não conseguirá o fazer.");
        c2.setAtributos(0, 2, 0, 0, 0);
        c2.setFocus(1, 1, 1, 1, 1, 1);
        c2.setCusto(5);
        c2.setPms(6);
        c2.setPvs(3);
        c2.resistenciaDano.setTipoDano(0, 0, 0, 0, 0, 0, 0);
        c2.resistenciaEfeitos.setTipoEfeito("Investida", 0, 0, 0, 0, 0, 0, 1);
        arrayClasse.add(c2);
        
        Classe c3 = new Classe();
        c3.setNome("Buccaneer");
        c3.setDescricao("Ataca com garras, mestre no combate corpo a corpe e com garras");
        c3.setVantagens("Garras", "fodas");
        c3.setDesvantagens("Nenhuma","é isso ai");
        c3.setAtributos(5, 5, 5, 5, 5);
        c3.setFocus(5, 5, 5, 5, 5, 5);
        c3.setCusto(5);
        c3.setPms(6);
        c3.setPvs(3);
        c3.resistenciaDano.setTipoDano(5, 5,5, 5, 5, 5, 5);
        c3.resistenciaEfeitos.setTipoEfeito("Investida", 5, 5, 5, 5, 5, 5, 5);
        arrayClasse.add(c3);
        return arrayClasse;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
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

    public ArrayList<String[]> getVantagens() {
        return vantagens;
    }

    public void setVantagens(String nome, String descricao) {
        String[] vantagem = new String[2];
        vantagem[0] = nome;
        vantagem[1] = descricao;
        this.vantagens.add(vantagem);
    }

    public ArrayList<String[]> getDesvantagens() {
        return desvantagens;
    }

    public void setDesvantagens(String nome, String descricao) {
        String[] desvantagem = new String[2];
        desvantagem[0] = nome;
        desvantagem[1] = descricao;
        this.desvantagens.add(desvantagem);
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oterceirodeus.codigo_fonte.gameplay;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Yves-Medhard
 */
public class Raça {

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
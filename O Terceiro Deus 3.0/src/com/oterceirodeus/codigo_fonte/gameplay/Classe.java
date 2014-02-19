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
public class Classe {

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


import java.awt.Graphics2D;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Yves
 */
public class NPC extends Personagem {

    private Personagem jogador;
    private int ação;
    private int indiceAção;
    private boolean concluido = true;

    public NPC() {
    }

    public void setPropriedades(Personagem npc) {
        super.executando = npc.executando;
        super.setSkills(npc.skills);
        super.setC(npc.getC());
        super.setFicha(npc.getFicha());
        super.setImage(npc.getImage());
        super.setMovimentos(npc.getMovimentos());
    }

    public NPC(Personagem npc, Personagem jogador) {
        this.jogador = jogador;
        super.monstros.add(this.jogador);
        setPropriedades(npc);
        this.ficha.setMp(1000);
        super.skills.add(new Magia("Genesis", null, 0, 13, 150, 4));
    }

    public void decidirAção() {

        Random r = new Random();
        System.out.println(skills.size());
        if (skills.size() > 0) {
            this.ação = r.nextInt(2);
        } else {
            this.ação = 0;
        }
        this.concluido = false;

        if (this.ação == 1 && super.skills.size() > 0) {// usar magia
            this.indiceAção = r.nextInt(super.skills.size());
        } else {
            this.ação = 0;
          //  int a =0; //(this.movimentos.getMovimentos().size() - '6) / 2;
            this.indiceAção = 0;// r.nextInt(a);
            if (this.indiceAção == 0) {
                this.indiceAção = 0;
            } else if (this.indiceAção == 1) {
                this.indiceAção = 2;
            } else {
                this.indiceAção = 4;
            }
        }
        System.out.println(ação + "/" + indiceAção);
        this.concluido = false;
    }

    public void seguirPlayer() {
        if (concluido) {
            this.decidirAção();
        }
//        System.out.println("posição jogador = "+ jogador.posicaoM);
        // System.out.println("posição npc = " + super.posicaoM);
        if (jogador.posicaoM.x >= super.posicaoM.x) {
            super.lado = 1;
        } else {
            super.lado = 0;
        }

        if (this.ação == 0) {
            if (jogador.posicaoM.x - (super.posicaoM.x + super.image.getWidth(null)) < 300 && jogador.posicaoM.x - (super.posicaoM.x + super.image.getWidth(null)) >= 0) {

                if (jogador.posicaoM.x - (super.posicaoM.x + super.image.getWidth(null)) <= 4) {
                    if (!super.atacando) {
                        this.executarAtaque(indiceAção);                        
                    }               
                    
                } else {
                    if ((super.posicaoM.y >= this.jogador.getPosicaoM().y
                            && super.posicaoM.y <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))
                            || (super.posicaoM.y + super.image.getWidth(null) >= this.jogador.getPosicaoM().y
                            && super.posicaoM.y + super.image.getWidth(null) <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))) {
                        super.velocidade.x = super.ficha.getVelocidade().x;
                       super.movimentos.encerrarAtaque();
                        this.concluido = true;
                        super.lado = 1;
                    }
                }
            } else if (super.posicaoM.x - (jogador.posicaoM.x + jogador.image.getWidth(null)) < 300 && super.posicaoM.x - (jogador.posicaoM.x + jogador.image.getWidth(null)) >= 0) {
                if (super.posicaoM.x - (jogador.posicaoM.x + jogador.image.getWidth(null)) <= 4) {
                    if (!super.atacando) {
                        this.executarAtaque(indiceAção);
                    }
                    //System.out.println("Atacou!");
                    //super.ocupado = true;
                   
                } else {
                    if ((super.posicaoM.y >= this.jogador.getPosicaoM().y
                            && super.posicaoM.y <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))
                            || (super.posicaoM.y + super.image.getWidth(null) >= this.jogador.getPosicaoM().y
                            && super.posicaoM.y + super.image.getWidth(null) <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))) {
                        super.velocidade.x = -super.ficha.getVelocidade().x;
                        super.lado = 0;
                        super.movimentos.encerrarAtaque();
                        this.concluido = true;
                    }
                }

            } else {
                super.movimentos.encerrarAtaque();
                 this.concluido = true;
                super.setVelocidadeX(0);
            }
        } else {
            System.out.println("Ação 1");
            if (super.lado == 1) {

                if ((super.posicaoM.y >= this.jogador.getPosicaoM().y
                        && super.posicaoM.y <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))
                        || (super.posicaoM.y + super.image.getWidth(null) >= this.jogador.getPosicaoM().y
                        && super.posicaoM.y + super.image.getWidth(null) <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))) {
                    if (jogador.posicaoM.x <= super.posicaoM.x + super.image.getWidth(null) + (super.skills.get(indiceAção).getAlcanceX() * super.skills.get(indiceAção).getRepetições()) + super.skills.get(indiceAção).getS().get(0).getImages().get(0).getWidth(null)) {

                        this.concluido = true;
                        //super.getSkills().get(indiceAção).iniciar(lado, posicaoM, super.image.getWidth(null), super.image.getHeight(null), this);
                        if (!super.skills.get(indiceAção).isExecutando()) {
                            System.out.println("Usou skill");
                            this.executarSkill(this.indiceAção);
                            super.velocidade.x = 0;
                        }
                    } else if (jogador.posicaoM.x <= super.posicaoM.x + super.image.getWidth(null) + (super.skills.get(indiceAção).getAlcanceX() * super.skills.get(indiceAção).getRepetições()) + super.skills.get(indiceAção).getS().get(0).getImages().get(0).getWidth(null) + 100) {
                        super.velocidade.x = super.ficha.getVelocidade().x;
                        
                      
                    }
                }
            } else {
                if ((super.posicaoM.y >= this.jogador.getPosicaoM().y
                        && super.posicaoM.y <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))
                        || (super.posicaoM.y + super.image.getWidth(null) >= this.jogador.getPosicaoM().y
                        && super.posicaoM.y + super.image.getWidth(null) <= this.jogador.getPosicaoM().y + this.jogador.getImage().getWidth(null))) {
                    if (jogador.posicaoM.x + jogador.image.getWidth(null) >= super.posicaoM.x - (super.skills.get(indiceAção).getAlcanceX() * super.skills.get(indiceAção).getRepetições()) - super.skills.get(indiceAção).getS().get(0).getImages().get(0).getWidth(null)) {

                        //super.getSkills().get(indiceAção).iniciar(lado, posicaoM, super.image.getWidth(null), super.image.getHeight(null), this);
                        if (!super.skills.get(indiceAção).isExecutando()) {
                            System.out.println("Usou skill");
                            this.executarSkill(this.indiceAção);
                            super.velocidade.x = 0;
                        }
                        this.concluido = true;
                    } else if (jogador.posicaoM.x + jogador.image.getWidth(null) >= super.posicaoM.x - (super.skills.get(indiceAção).getAlcanceX() * super.skills.get(indiceAção).getRepetições()) - super.skills.get(indiceAção).getIm().getWidth(null) - 100) {
                        super.velocidade.x = -super.ficha.getVelocidade().x;
                    }
                }

            }

        }
    }

    public double calcularDistancia() {
        double result;
        result = Math.sqrt(Math.pow(this.posicaoM.x - this.jogador.posicaoM.x, 2) + Math.pow(this.posicaoM.y - this.jogador.posicaoM.y, 2));
        return result;
    }

    @Override
    public void update(long gametime) {
        float time = 1f;
        seguirPlayer();

        if (super.posicaoM.y == 0) {
            super.velocidade.y = 0;
        }
        if (estaDentro(super.posicaoM.x, super.posicaoM.y)) {
            super.posicaoM.x = (super.posicaoM.x + (super.velocidade.x * time)); // formula: S = S0 + vt

            super.posicaoM.y = (super.posicaoM.y + (super.velocidade.y * time) + (float) (((getC().getGravidadedomapa() - normal) * Math.pow(time, 2)) * 0.5f));  // formula S = S0 + vt + at^2/2
            if (this.velocidade.y >= 4f) {
                this.velocidade.y = 4f;
            } else {
                this.velocidade.y = this.velocidade.y + (((getC().getGravidadedomapa() - normal) * time)); // formula:  V = V0 + at
            }
        }
        testes();
    }

    @Override
    public void testes() {
//Teste Stand
        if (super.atacando) {
            System.out.println("atacou");
            this.image = super.movimentos.atacar(indiceAção);
        }
        if (velocidade.x == 0 && !atacando) {
            setImage(movimentos.Stand(lado));
        }
//Teste Walk Right (teste de permanencia em plataforma e colisão com personagem)
        if (velocidade.x > 0) {
            setLado(1);
            if (!Colisão.colisaoTerrenoX(this, c.getPlataformas())) {
                setNormal(0);
            }
//            if (colisaopersonagem) {
//                movimentos.setLadoBloqueado(1);
//            } else {
            if (!ocupado && !atacando) {
                setImage(movimentos.walkRight());
            }
        }
//Teste Walk Left (teste de permanencia em plataforma e colisão com personagem)
        if (velocidade.x < 0 ) {
            setLado(0);
            if (!Colisão.colisaoTerrenoX(this, getC().getPlataformas())) {
                setNormal(0);
            }
//            if (colisaopersonagem) {
//                movimentos.setLadoBloqueado(1);
//            } else {
            if (!ocupado && !atacando) {
                setImage(movimentos.walkLeft());
            }
        }

//Teste Jump
        if (this.velocidade.y < 0) {
            this.setNormal(0);
            setImage(this.movimentos.Jump(lado));
        }
//Teste de Queda ( inclui imagem de queda, e colisão com terreno
        if (this.velocidade.y > 0) {
            setImage(movimentos.cair(lado));
            if (Colisão.colisaoTerrenoY(this, getC().getPlataformas())) {
                setNormal(getC().getGravidadedomapa());
                this.velocidade.y = 0;
                this.setOcupado(false);
            }
        }

        getMovimentos().setLadoBloqueado(2);

        if (this.posicaoM.x <= 0) {
            getMovimentos().setLadoBloqueado(0);
            posicaoM.x = 0;
        }
        if (this.posicaoM.x >= (((getC().getMapa().getWidth()) * getC().getMapa().getTileWidth()) - image.getWidth(null))) {
            getMovimentos().setLadoBloqueado(1);
            this.posicaoM.x = (((getC().getMapa().getWidth()) * getC().getMapa().getTileWidth()) - image.getWidth(null));
        }
        if (this.posicaoM.y >= (((getC().getMapa().getHeight()) * getC().getMapa().getTileHeight()) - image.getHeight(null))) {
            this.posicaoM.y = (((getC().getMapa().getHeight()) * getC().getMapa().getTileHeight()) - image.getHeight(null));
            this.velocidade.y = (0);
            this.ocupado = false;
        }

    }

    public Personagem getNpc() {
        return this;
    }

    public int getAção() {
        return ação;
    }

    public void setAção(int ação) {
        this.ação = ação;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public int getIndiceAção() {
        return indiceAção;
    }

    public void setIndiceAção(int indiceAção) {
        this.indiceAção = indiceAção;
    }

    public Personagem getJogador() {
        return jogador;
    }

    public void setJogador(Personagem jogador) {
        this.jogador = jogador;
    }
}

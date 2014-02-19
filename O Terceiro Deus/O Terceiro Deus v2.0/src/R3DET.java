
import java.util.HashMap;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Guilherme
 */
public class R3DET {

    public R3DET() {
    }

    public static void DanoForçaInfringido(Ficha ataque, Ficha defesa) {
        int SomaTiposDano = ComparaAtributos(ataque.getArma().getTipoDano().getTiposDano(), defesa.getArmadura().getResistenciaDano().getTiposDano());
        Random random = new Random();
        int d = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        int dano = (d + ataque.getAtributos().get("Força") + ataque.getAtributos().get("Habilidade")) - (d2 + defesa.getAtributos().get("Armadura") + defesa.getAtributos().get("Habilidade")) + SomaTiposDano;
        if (dano < 0) {
            dano = 0;
        }
        defesa.setHp(defesa.getHp() - dano);
    }

    public static void DanoPdFInfringido(Ficha ataque, Ficha defesa) {
        int SomaTiposDano = ComparaAtributos(ataque.getArma().getTipoDano().getTiposDano(), defesa.getArmadura().getResistenciaDano().getTiposDano());
        Random random = new Random();
        int d = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        int dano = (d + ataque.getAtributos().get("PdF") + ataque.getAtributos().get("Habilidade")) - (d2 + defesa.getAtributos().get("Armadura") + defesa.getAtributos().get("Habilidade")) + SomaTiposDano;
        if (dano < 0) {
            dano = 0;
        }
        defesa.setHp(defesa.getHp() - dano);
    }

    public static void DanoMagiaInfringido(Magia ataque, Ficha defesa) {
        int danoReal = ComparaAtributos(ataque.getFocus(), defesa.getArmadura().getResistenciaDano().getTiposDano());
        float porcentagem = PorcentagemResistencia(ataque.getFocus(), danoReal);
        Random random = new Random();
        int d = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        int b = ataque.somaQtdDados();
        int somaQtdDados = (int) (b * porcentagem);
        int dano = (d + ataque.getConjurador().getFicha().getAtributos().get("Habilidade") + somaQtdDados) - (d2 + defesa.getAtributos().get("Armadura") + defesa.getAtributos().get("Habilidade")) + danoReal;
        if (dano < 0) {
            dano = 0;
        }
        defesa.setHp(defesa.getHp() - dano);
    }

    public static int ComparaAtributos(HashMap<String, Integer> hashDanos, HashMap<String, Integer> hashResistencia) {
        TiposDano comparado = new TiposDano();

        for (String key : hashDanos.keySet()) {
            int compara = hashDanos.get(key) - hashResistencia.get(key);
            if (compara < 0) {
                comparado.setValor(key, 0);
            } else {
                comparado.setValor(key, compara);
            }
        }
        return SomarAtributos(comparado);
    }

    public static int SomarAtributos(TiposDano calcular) {
        int soma = 0;
        for (String key : calcular.getTiposDano().keySet()) {
            soma = soma + calcular.getTiposDano().get(key);
        }
        return soma;
    }

    public static float PorcentagemResistencia(HashMap<String, Integer> hashAtaque, float hashDano) {
        float somaAtaque = 0f;
        for (String key : hashAtaque.keySet()) {
            somaAtaque = somaAtaque + hashAtaque.get(key);
        }
        if (somaAtaque == 0) {
            return 0f;
        }
        float a = hashDano / somaAtaque;
        return a;
    }

    public static int IntensidadeEfeito(Ficha ataque, Ficha defesa, String efeito) {
        int intensidadeEfeito = ComparaAtributos(ataque.getArma().getEfeitos().getEfeitos().get(efeito).getTiposDano(), defesa.getArmadura().getResistenciaEfeitos().getEfeitos().get(efeito).getTiposDano());

        return intensidadeEfeito;
    }

    public static void main(String[] args) {
    }
}

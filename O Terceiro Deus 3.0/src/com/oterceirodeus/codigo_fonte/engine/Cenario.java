package com.oterceirodeus.codigo_fonte.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.oterceirodeus.codigo_fonte.gameplay.Raça;
import com.oterceirodeus.codigo_fonte.gameplay.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.TileLayer;
import tiled.io.TMXMapReader;
import tiled.view.OrthogonalRenderer;

/**
 *
 * @author Yves
 */
public class Cenario {

    private ArrayList<Terreno> terrenoArray = new ArrayList();
    private ArrayList<Obstaculo> obstaculoArray = new ArrayList();
    private ArrayList<CheckPoint> checkpointArray = new ArrayList();
    private ArrayList<LocalnoMapa> localnomapaArray = new ArrayList();
    private ArrayList<MapObject> mapobjectArray = new ArrayList();
    private ArrayList<NPC> npcArray;
    private ArrayList<Magia> magiasArray;
    private Personagem principal;
    private Map mapa = null;
    private float gravidade = .8f;
    private Player player;
    private boolean loading = true, sair;

    public Cenario() {
    }

    public Cenario(ArrayList<NPC> npcs, ArrayList<Magia> magiasArray) {
        this.npcArray = npcs;
        this.magiasArray = magiasArray;
        this.principal = principal;
    }

    public void novoCenario(String mapaname) {
        limparArrays();

        Map mapa = lermapa(mapaname);
        this.mapobjectArray = lerMapObject(mapa.getLayer(1));
        
        this.principal.setPosicaoM(getPosicaoInicial(mapobjectArray));
        this.terrenoArray = CriarTerreno(mapobjectArray);
        this.checkpointArray = CriarCheckpoints(mapobjectArray);
        this.obstaculoArray = CriarObstaculos(mapobjectArray);
        this.npcArray = CriarNpcs(mapobjectArray);
        this.principal.setAlvos(npcArray);
//        if (posicaoinicial != null) {
//             posicaoinicial;
//        } else {
//            this.principal.posicaoM = new Point.Float(0, 0);
//        }
        if (mapa.getProperties().containsKey("BackMusic")) {
            player.setBackMusic(mapa.getProperties().getProperty("BackMusic")).play();
        }
        this.mapa = mapa;
    }

    public Map lermapa(String filename) {
        TMXMapReader TMXR = new TMXMapReader();
        Map mapa = null;
        try {
            mapa = TMXR.readMap("src\\com\\oterceirodeus\\assets\\maps\\" + filename);
        } catch (Exception ex) {
            Logger.getLogger(Cenario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
    }

    public ArrayList<MapObject> lerMapObject(MapLayer ml) {
        ObjectGroup og;
        ArrayList<MapObject> mapobjectarray = new ArrayList();
        try {
            og = (ObjectGroup) ml.clone();
            mapobjectarray = ogTomo(og);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Cenario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapobjectarray;
    }

    private void limparArrays() {
        this.npcArray.clear();
        this.terrenoArray.clear();
        this.obstaculoArray.clear();
        this.checkpointArray.clear();
    }

    public float getGravidadedomapa() {
        if (this.mapa.getProperties().containsKey("Gravidade")) {
            return Float.parseFloat(this.mapa.getProperties().getProperty("Gravidade"));
        }
        return gravidade;
    }

    public void pintarlayer(Graphics2D g, int layer) {
        OrthogonalRenderer or;
        TileLayer tl = null;
        try {
            or = new OrthogonalRenderer(this.mapa);
            tl = (TileLayer) this.mapa.getLayer(layer);
            or.paintTileLayer(g, tl);
        } catch (Exception ex) {
            ////System.out.println("Erro ao pintar a layer: " + ex);
        }
    }

    public void pintarMapObject(Graphics2D g, ArrayList<MapObject> moarray) {
        g.setColor(Color.ORANGE);
        for (MapObject mo : moarray) {
            g.drawRect(mo.getX(), mo.getY(), mo.getWidth(), mo.getHeight());
        }
    }

    public ArrayList<MapObject> ogTomo(ObjectGroup og) {
        ArrayList<MapObject> mo = new ArrayList();
        // int i = 0;
        do {
            mo.add(og.getObjects().next());
            og.removeObject(mo.get(mo.size() - 1));
            ////System.out.println("pegou objeto" + i);
            //   ++i;
        } while (og.getObjects().hasNext());
        ////System.out.println("Objetos adicionados: " + (i + 1));
        return mo;
    }

    public ArrayList<Terreno> CriarTerreno(ArrayList<MapObject> mo) {
        // int i = 0;
        ArrayList<Terreno> terrenoarray = new ArrayList();

        for (MapObject o : mo) {
            if (o.getType().equals("Plataforma")) {
                Terreno t = new Terreno();
                t.setX(o.getX());
                t.setY(o.getY());
                t.setAltura(o.getHeight());
                t.setLargura(o.getWidth());
                t.setNome(o.getName());
                t.setType(o.getType());
                t.setImagem(o.getImage(0));
                t.setProperties(o.getProperties());
                terrenoarray.add(t);
                ////System.out.println("Terreno: " + i + " " + t.getNome());
                //      ++i;
            }
        }
        return terrenoarray;
        ////System.out.println("Total de terrenos no Array: " + (i) + "\n");
    }

    public ArrayList<Obstaculo> CriarObstaculos(ArrayList<MapObject> mo) {
        //int i = 0;
        ArrayList<Obstaculo> obstaculoarray = new ArrayList();

        for (MapObject ob : mo) {
            if (ob.getType().equals("Obstaculo")) {
                Obstaculo o = new Obstaculo();
                o.setX(ob.getX());
                o.setY(ob.getY());
                o.setAltura(ob.getHeight());
                o.setLargura(ob.getWidth());
                o.setNome(ob.getName());
                o.setType(ob.getType());
                o.setImagem(ob.getImage(0));
                obstaculoarray.add(o);
                ////System.out.println("Obstaculo: " + i + " " + o.getNome());
                //       ++i;
            }
        }
        return obstaculoarray;
        ////System.out.println("Total de obstoculos no Array: " + (i + 1) + "\n");
    }

    public ArrayList<CheckPoint> CriarCheckpoints(ArrayList<MapObject> mo) {
        // int i = 0;
        ArrayList<CheckPoint> checkpointarray = new ArrayList();

        for (MapObject ob : mo) {
            if (ob.getType().equals("CheckPoint")) {
                CheckPoint cp = new CheckPoint();
                cp.setX(ob.getX());
                cp.setY(ob.getY());
                cp.setAltura(ob.getHeight());
                cp.setLargura(ob.getWidth());
                cp.setNome(ob.getName());
                cp.setType(ob.getType());
                cp.setImagem(ob.getImage(0));
                cp.setFuncao(ob.getProperties().getProperty("Função"));
                cp.setMapName(ob.getProperties().getProperty("MapName"));
                cp.setProperties(ob.getProperties());

                checkpointarray.add(cp);
                ////System.out.println("CheckPoints: " + i + " " + cp.getNome());
                //       ++i;
            }
        }
        return checkpointarray;
        ////System.out.println("Total de ChackPoints no Array: " + (i + 1) + "\n");
    }

    public ArrayList<LocalnoMapa> CriarLocalnoMapa(ArrayList<MapObject> mo) {
        //      int i = 0;
        ArrayList<LocalnoMapa> localnomapaarray = new ArrayList();
        for (MapObject ob : mo) {
            if (ob.getType().equals("LocalnoMapa")) {
                LocalnoMapa lnm = new LocalnoMapa();
                lnm.setPosicao(ob.getX(), ob.getY());
                lnm.setSize(ob.getWidth(), ob.getHeight());
                lnm.setNome(ob.getName());
                lnm.setProperties(ob.getProperties());
                localnomapaarray.add(lnm);
                //           System.out.println("Local no mapa: " + i + " " + lnm.getNome());
                //            ++i;
            }
        }
        //    System.out.println("Total de ChackPoints no Array: " + (i) + "\n");
        return localnomapaarray;
    }

    public ArrayList<NPC> CriarNpcs(ArrayList<MapObject> mo) {
        int i = 0;
        ArrayList<NPC> npcsmapaarray = new ArrayList();
        for (MapObject ob : mo) {
            if (ob.getType().equals("Npc")) {
                Classe classe = null;
                Raça raca = null;
                for (Classe c : new Classe().gerarClasses()) {
                    if (c.getNome().equals(ob.getProperties().getProperty("Classe"))) {
                        classe = c;
                    }
                }
                for (Raça r : new Raça().gerarRacas()) {
                    if (r.getNome().equals(ob.getProperties().getProperty("Raça"))) {
                        raca = r;
                    }
                }
                NPC npc = new NPC(new Personagem(new Ficha(ob.getName(), raca, classe), this, ob.getProperties().getProperty("Xml"), magiasArray), principal);
                npc.posicaoM = new Point.Float(ob.getX(), ob.getY());
                npcsmapaarray.add(npc);
                i++;
            }
        }
        System.out.println("Total de Npcs: " + i);
        return npcsmapaarray;
    }

    public Point.Float getPosicaoInicial(ArrayList<MapObject> mo) {
        for (MapObject ob : mo) {
            if (ob.getType().equals("PosiçãoInicial")) {
                System.out.println(ob.getX() + "/" + ob.getY());
                return new Point.Float(ob.getX(), ob.getY());
            }
        }
        return null;
    }

    public ArrayList<LocalnoMapa> getLocalnomapaArray() {
        return localnomapaArray;
    }

    public ArrayList<Terreno> getPlataformas() {
        return this.terrenoArray;
    }

    public ArrayList<Obstaculo> getObstaculoArray() {
        return obstaculoArray;
    }

    public ArrayList<CheckPoint> getCheckpointArray() {
        return checkpointArray;
    }

    public ArrayList<NPC> getNpcArray() {
        return npcArray;
    }

    public Map getMapa() {
        return mapa;
    }

    public ArrayList<MapObject> getMapobjectArray() {
        return mapobjectArray;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isSair() {
        return sair;
    }

    public void setSair(boolean sair) {
        this.sair = sair;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPrincipal(Personagem principal) {
        this.principal = principal;
    }
}

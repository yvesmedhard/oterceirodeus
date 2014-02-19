
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tiled.core.Map;
import tiled.core.TileLayer;
import tiled.view.OrthogonalRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class MapaMundo {

    private boolean ativado, inreino;
    private ArrayList<LocalnoMapa> locais = new ArrayList();
    private MetodosdeEntrada me;
    private Cenario c;
    private Map mapaDoMundo;
    private Player player;

    public MapaMundo(MetodosdeEntrada me, Cenario c) {
        this.me = me;
        this.c = c;
    }

    public void loadmapa(String s) {
        mapaDoMundo = c.lermapa(s);
        setLocaisnoMapa();
        if (mapaDoMundo.getProperties().containsKey("BackMusic")) {
            player.setBackMusic(mapaDoMundo.getProperties().getProperty("BackMusic")).play();
        }
    }

    public void mouseClick(MouseEvent me) {
        for (LocalnoMapa lnm : this.locais) {
            Rectangle local = new Rectangle(lnm.getPosicao(), lnm.getSize());
            if (local.contains(me.getLocationOnScreen())) {
                activate(lnm);
                player.setOtherMusic("MouseClick1.mp3").play();
                return;
            }
            player.setOtherMusic("Error.mp3").play();
        }
    }

    public void activate(LocalnoMapa lnm) {
        if (lnm.getProperties().get("Acessivel").equals("true")) {
            if (lnm.getProperties().getProperty("Função").equals("AbrirReino")) {
                c.setLoading(true);
                this.loadmapa(lnm.getProperties().getProperty("MapName"));
                setAtivado(true);
                setInreino(true);
                c.setLoading(false);
                player.setOtherMusic("SecretFound.mp3").play();
            } else {
                c.setLoading(true);
                c.novoCenario(lnm.getProperties().getProperty("MapName"));
                setAtivado(false);
                c.setLoading(false);
                player.setOtherMusic("AutoCastButtonClick1.mp3").play();
            }
        }
    }

    public void setLocaisnoMapa() {
        this.locais = c.CriarLocalnoMapa(c.lerMapObject(mapaDoMundo.getLayer(1)));
    }

    public void pintarMapa(Graphics2D g2d) {
        OrthogonalRenderer or;
        TileLayer tl = null;
        try {
            or = new OrthogonalRenderer(this.mapaDoMundo);
            tl = (TileLayer) this.mapaDoMundo.getLayer(0).clone();
            or.paintTileLayer(g2d, tl);
        } catch (Exception ex) {
            //    System.out.println("Erro ao pintar a layer: " + ex);
        }
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    public boolean isInreino() {
        return inreino;
    }

    public void setInreino(boolean inreino) {
        this.inreino = inreino;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMapaDoMundo() {
        return mapaDoMundo;
    }
}

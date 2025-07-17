package engine.world;

import engine.entities.Entity;
import engine.entities.Player;
import engine.gfx.Camera;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class World {
    private TileCluster[][] clusters;
    private Player player;
    private List<Entity> entities;
    private static int MAP_WIDTH = 3, MAP_HEIGHT = 3;

    public World() {
        entities = new ArrayList<>();
        clusters = new TileCluster[MAP_WIDTH][MAP_HEIGHT];
        for(int i = 0; i < MAP_WIDTH; i++) {
            for(int j = 0; j < MAP_HEIGHT; j++) {
                Vector3f pos = new Vector3f(
                        i*(TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE),
                        j*(TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE),
                        0
                );
                clusters[i][j] = new TileCluster(pos,1);
            }
        }
        player = new Player(new Vector3f(TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE*MAP_WIDTH/2,TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE*MAP_HEIGHT/2,0.0f));
        Camera.setFallowedEntity(player);
    }

    public void tick(double dt) {
        player.tick(dt);
        for (Entity e : entities) e.tick(dt);
    }

    public void render() {
        int a = 0;
        if (player.getPos().x < 0)
            a++;
        int b = 0;
        if (player.getPos().y < 0)
            b++;
        int x = (int) (player.getPos().x/(TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE*2)) -a;
        int y = (int) (player.getPos().y/(TileCluster.CLUSTER_LENGTH*Tile.DEFAULT_TILE_SIZE*2)) -b;

        for(int i = x-1; i <= x+1; i++) {
            for(int j = y-1; j <= y+1; j++) {
                if(i >= 0 && i < MAP_WIDTH && j >= 0 && j < MAP_HEIGHT)
                    clusters[i][j].render();
            }
        }
        player.render();
        for (Entity e : entities) e.render();
    }
}

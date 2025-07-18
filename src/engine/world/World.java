package engine.world;

import engine.entities.Player;
import engine.entities.emenyes.Enemy;
import engine.entities.emenyes.SmallTank;
import engine.entities.projectiles.Projectile;
import engine.gfx.Camera;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private TileCluster[][] clusters;
    private Player player;
    private List<Projectile> bullets;
    private List<Enemy> enemies;
    private int timeTillNextSpawn;

    private static final int MAP_WIDTH = 3;
    private static final int MAP_HEIGHT = 3;
    private static float maxDistFormPlayer = 1600.0f, minDistFromPlayer = 800.0f;

    public World() {
        bullets = new ArrayList<>();
        timeTillNextSpawn = 0;
        enemies = new ArrayList<>();
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
        for (Projectile e : bullets) e.tick(dt);
        for (Enemy e : enemies) e.tick(dt);
        timeTillNextSpawn--;
        spawnEnemy();
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
        for (Projectile e : bullets) e.render();
        for (Enemy e : enemies) e.render();
        bullets.removeIf(projectile -> projectile.getLifeSpan() <= 0);
        enemies.removeIf(projectile -> projectile.getHP() <= 0);
        player.render();
    }

    public void addProjectile(Projectile e) {
        bullets.add(e);
    }

    public List<Projectile> getBullets() {
        return bullets;
    }

    public Player getPlayer() {
        return player;
    }

    public void spawnEnemy() {
        if (timeTillNextSpawn <= 0) {
            Random random = new Random();
            timeTillNextSpawn += random.nextInt(10, 100);
            if (enemies.size() <= 30){
                Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(random.nextFloat(0, 360)));
                Vector3f v = new Vector3f(0, random.nextFloat(minDistFromPlayer, maxDistFormPlayer), 0).mul(m).add(player.getPos());
                enemies.add(new SmallTank(v));
            }
        }
    }
}

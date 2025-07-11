package engine.world;

import org.joml.Vector3f;

public class World {
    private TileCluster t;

    public World() {
        t = new TileCluster(new Vector3f(),0);
    }

    public void tick(double dt) {}

    public void render() {
        t.render();
    }
}

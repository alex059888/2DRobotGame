package engine.world;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class TileCluster {
    private Tile[][] tiles;
    private Vector3f pos;
    private Mesh mesh;
    private int texId;

    public static final int CLUSTER_LENGTH = 16;

    public TileCluster(Vector3f pos, int texId) {
        tiles = new Tile[CLUSTER_LENGTH][CLUSTER_LENGTH];
        this.texId = texId;
        this.pos = pos;
        int ind = 0;
        for (int i = 0; i < CLUSTER_LENGTH; i++) {
            for (int j = 0; j < CLUSTER_LENGTH; j++) {
                tiles[i][j] = new Tile(new Vector2i(0, 0), texId,
                        new Vector3f(i*Tile.DEFAULT_TILE_SIZE, j*Tile.DEFAULT_TILE_SIZE, 0), ind);
                ind++;
            }
        }
        genMesh();
    }

    public void genMesh() {
        List<Float> vbos = new ArrayList<>();
        List<Integer> ebos = new ArrayList<>();
        for (int i = 0; i < CLUSTER_LENGTH; i++) {
            for (int k = 0; k < CLUSTER_LENGTH; k++) {
                vbos.addAll(tiles[i][k].getVbos());
                ebos.addAll(tiles[i][k].getEbos());
            }
        }

        float[] v = new float[vbos.size()];
        int[] e = new int[ebos.size()];

        for(int i = 0; i < vbos.size(); i++)
            v[i] = vbos.get(i);
        for(int i = 0; i < ebos.size(); i++)
            e[i] = ebos.get(i);

        mesh = new Mesh(v,e);
    }

    public void tick(double dt) {}

    public void render() {
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }

    public Matrix4f transform() {
        Matrix4f transform = new Matrix4f();
        transform.identity();

        transform.translate(new Vector3f(pos.x,pos.y,pos.z));
        transform.scale(1);

        return transform;
    }
}

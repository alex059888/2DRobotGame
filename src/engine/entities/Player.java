package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.util.Handler;
import org.joml.Vector2f;

public class Player extends Entity{
    private Mesh mesh;

    public Player(Vector2f pos) {
        super(pos);
        float[] verts = {
                //x y   z r g b a uv
                -10,-10,2,1,1,1,1,-1,-1,
                -10, 10,2,1,1,1,1,-1, 1,
                10, 10, 2,1,1,1,1, 1, 1,
                10,-10, 2,1,1,1,1, 1,-1
        };
        int[] ebos = {
                0,1,2,2,1,3
        } ;
        mesh = new Mesh(verts,ebos);
    }

    public Player() {
        super(new Vector2f());
        float[] verts = {
                //x y   z r g b a uv
                -10,-10,2,1,1,1,1,0,0,
                -10, 10,2,1,1,1,1,0,1,
                10, 10, 2,1,1,1,1,1,1,
                10,-10, 2,1,1,1,1,1,0
        };
        int[] ebos = {
                0,1,2,2,1,3
        } ;
        mesh = new Mesh(verts,ebos);
    }

    @Override
    public void tick(double dt) {
    }

    @Override
    public void render() {
        Handler.getCurentShader().setTransform(transform());
        mesh.render();
    }
}

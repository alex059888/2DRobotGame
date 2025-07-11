package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.util.Handler;
import org.joml.Vector3f;

public class Player extends Entity{
    private Mesh mesh;

    public Player(Vector3f pos) {
        super(pos);
        float[] verts = {
                //x   y   z r g b a u v
                -32,-32,0,1,1,1,1,0,1,
                -32, 32,0,1,1,1,1,0,0,
                 32, 32,0,1,1,1,1,1,0,
                 32,-32,0,1,1,1,1,1,1
        };
        int[] ebos = {
                0,1,2,2,3,0
        } ;
        mesh = new Mesh(verts,ebos);
    }

    public Player() {
        super(new Vector3f());
        float[] verts = {
                //x   y   z r g b a u v
                -32,-32,0,1,1,1,1,0,1,
                -32, 32,0,1,1,1,1,0,0,
                32, 32,0,1,1,1,1,1,0,
                32,-32,0,1,1,1,1,1,1
        };
        int[] ebos = {
                0,1,2,2,3,0
        } ;
        mesh = new Mesh(verts,ebos);
    }

    @Override
    public void tick(double dt) {
    }

    @Override
    public void render() {
        Handler.getCurentShader().setTransform(transform());
        //mesh.render();
    }
}

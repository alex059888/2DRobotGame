package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.io.KeyListener;
import engine.util.Handler;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity{
    private Mesh mesh;
    private int texId;
    private float speed = 5;

    public Player(Vector3f pos, int texId) {
        super(pos);
        this.texId = texId;
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
        if(KeyListener.isKeyPressed(GLFW_KEY_UP))
            pos.y+=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_DOWN))
            pos.y-=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_LEFT))
            pos.x-=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT))
            pos.x+=speed*dt;
    }

    @Override
    public void render() {
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }
}

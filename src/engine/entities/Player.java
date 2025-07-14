package engine.entities;

import engine.gfx.Camera;
import engine.gfx.Window;
import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.io.KeyListener;
import engine.io.MouseListener;
import engine.util.Handler;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity{
    private Mesh mesh;
    private static int texId = 0;
    private float speed = 5;
    private static Vector2f texPos = new Vector2f(1,0);

    public Player(Vector3f pos) {
        super(pos);
        float[] verts = {
                //x   y   z r g b a u v
                -32,-32,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize(), 64),
                -32, 32,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 64),
                 32, 32,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 64),
                 32,-32,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize(), 64)
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

        Vector2f p = new Vector2f(MouseListener.getX(), MouseListener.getY());
        System.out.println(p.toString());
        rot = Math.toDegrees(Math.atan2(p.x,p.y));
        if(rot > 360) rot -= 360;
        if(rot < 0) rot += 360;
    }

    @Override
    public void render() {
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }
}

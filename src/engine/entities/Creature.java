package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Creature extends Entity{
    protected Mesh mesh;
    protected static int texId = 0;
    protected float speed = 5;
    protected Vector2f texPos = new Vector2f(1,0);

    public Creature(Vector3f pos) {
        super(pos);
        setIndependentRotation(true);
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
    public void render() {
        super.render();
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
    }
}

package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Weapon extends Entity{
    private Mesh mesh;
    protected static int texId = 0;
    protected Vector2f texPos = new Vector2f(2,0);

    public Weapon(Vector3f pos, Vector3f origin) {
        super(pos);
        posFromOrigin = new Vector3f(pos.x - origin.x, pos.y - origin.y, pos.z - origin.z);
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
        super.tick(dt);
    }

    @Override
    public void render() {
        super.render();
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }
}

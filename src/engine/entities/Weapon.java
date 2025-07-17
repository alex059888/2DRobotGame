package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Weapon extends Entity{
    private Mesh mesh;
    protected static int texId = 4;
    protected Vector2f texPos;
    protected int shotDelay;
    protected int currentShotDelay;
    protected int projectileId;

    public Weapon(Vector3f pos, Vector3f origin, Vector2f texPos, int shotDelay, int projectileId) {
        super(pos, EntityType.WEAPON);
        this.shotDelay = shotDelay;
        this.projectileId = projectileId;
        currentShotDelay = 0;
        this.texPos = texPos;
        posFromOrigin = new Vector3f(pos.x - origin.x, pos.y - origin.y, pos.z - origin.z);
        float[] verts = {
                //x   y   z r g b a u v
                -64,-64,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize(), 64),
                -64, 64,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 64),
                64, 64,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize(), 64),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 64),
                64,-64,0,1,1,1,1,
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
        if(currentShotDelay > 0) currentShotDelay--;
    }

    @Override
    public void render() {
        super.render();
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
    }

    public void shot() {
        if (currentShotDelay <= 0) {
            currentShotDelay = shotDelay;

        }
    }
}

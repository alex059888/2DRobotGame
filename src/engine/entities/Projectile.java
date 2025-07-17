package engine.entities;

import engine.entities.projectiles.SmallBullet;
import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class Projectile extends Entity{
    protected Mesh mesh;
    protected static int texId = 2;
    protected float speed;
    protected Vector2f texPos;

    public List<Projectile> projectiles = List.of(
            new SmallBullet() //0
    );

    public Projectile(int id, Vector3f pos, float rot) {
        super(new Vector3f(pos), EntityType.BULLET);
        this.rot = rot;
        speed = projectiles.get(id).speed;
        mesh = projectiles.get(id).mesh;
    }

    public Projectile(float speed, Vector2f texPos) {
        super(new Vector3f(), EntityType.BULLET);
        this.texPos = texPos;
        this.speed = speed;
        float[] verts = {
                //x   y   z r g b a u v
                -8,-8,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 8),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize(), 8),
                -8, 8,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize(), 8),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 8),
                8, 8,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize(), 8),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize(), 8),
                8,-8,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize(), 8),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize(), 8)
        };
        int[] ebos = {
                0,1,2,2,3,0
        } ;
        mesh = new Mesh(verts, ebos);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
        Vector3f walk = new Vector3f(0, (float) (speed*dt),0).mul(m);
        pos.add(walk);
    }
}

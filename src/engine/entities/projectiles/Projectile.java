package engine.entities.projectiles;

import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector3f;

public class Projectile extends Entity {
    protected float speed;
    protected int lifeSpan;

    public Projectile(ProjectileType type, Vector3f pos, float rot) {
        super(new Vector3f(pos), EntityType.BULLET, 8, type.getTexPos(), 2);
        this.rot = rot;
        lifeSpan = type.getLifeSpan();
        speed = type.getSpeed();
        independentRotation = true;
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
        lifeSpan--;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}

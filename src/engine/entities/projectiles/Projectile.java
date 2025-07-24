package engine.entities.projectiles;

import engine.entities.Creature;
import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Projectile extends Entity {
    protected float speed;
    protected int lifeSpan, dmg;
    protected EntityType shotter;
    protected float collRad;

    public Projectile(ProjectileType type, Vector3f pos, float rot, EntityType shotter) {
        super(new Vector3f(pos), EntityType.BULLET, 8, type.getTexPos(), 2);
        this.rot = rot;
        this.shotter = shotter;
        lifeSpan = type.getLifeSpan();
        speed = type.getSpeed();
        collRad = type.getCollRad();
        this.dmg = type.getDmg();
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

    public void collide(Creature creature) {
        float dist = new Vector2f(creature.getPos().x-pos.x,creature.getPos().y-pos.y).length();
        if ((dist-creature.getCollRad()-collRad)<=0 && shotter != creature.getType()) {
            creature.damage(dmg);
            lifeSpan = 0;
        }
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public EntityType getShotter() {
        return shotter;
    }

    public float getCollRad() {
        return collRad;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}

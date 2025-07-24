package engine.entities.weapons;

import engine.entities.Entity;
import engine.entities.EntityType;
import engine.entities.projectiles.Projectile;
import engine.entities.projectiles.ProjectileType;
import engine.entities.template.SlotType;
import engine.util.Handler;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Weapon extends Entity {
    protected int currentShotDelay;
    protected ProjectileType projectile;
    protected Vector3f distFromCenter;
    protected SlotType slotType;
    protected String tag;
    protected EntityType shotter;

    public Weapon(Vector3f pos, Vector2f texPos, ProjectileType projectile, Vector3f distFromCenter, SlotType slotType, String tag, EntityType shotter) {
        super(pos, EntityType.WEAPON,64,texPos,4);
        this.shotter = shotter;
        this.tag = tag;
        this.slotType = slotType;
        this.distFromCenter = distFromCenter;
        this.projectile = projectile;
        currentShotDelay = 0;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        if(currentShotDelay > 0) currentShotDelay--;
    }

    @Override
    public void render() {
        super.render();
    }

    public void shot(int dmgMul) {
        if (currentShotDelay <= 0) {
            currentShotDelay = projectile.getDelay();
            Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
            Vector3f cp = new Vector3f(distFromCenter).mul(m);
            Projectile p = new Projectile(projectile, new Vector3f(pos).add(cp),rot,shotter);
            p.setDmg(p.getDmg()*dmgMul);
            Handler.getWorld().addProjectile(p);
        }
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

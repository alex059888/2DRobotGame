package engine.entities.powerups;

import engine.entities.Creature;
import engine.entities.Entity;
import engine.entities.EntityType;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class Powerup extends Entity {
    protected float rad;
    protected int lifeSpan;

    public static Powerup getPowerup(int nr, Vector3f pos) {
        return switch (nr) {
            case 1 -> new Shield(pos);
            default -> new Health(pos);
        };
    }

    public Powerup(Vector3f pos, Vector2f texPos, float rad) {
        super(pos, EntityType.USABLE, new Vector2i(32), texPos, 10);
        this.rad = rad;
        lifeSpan = 1200;
    }

    public void onContact() {
        lifeSpan = 0;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        lifeSpan--;
        collide(Handler.getWorld().getPlayer());
    }

    public float getRad() {
        return rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void collide(Creature creature) {
        float dist = new Vector2f(creature.getPos().x-pos.x,creature.getPos().y-pos.y).length();
        if ((dist-creature.getCollRad()-rad)<=0) {
            onContact();
        }
    }
}

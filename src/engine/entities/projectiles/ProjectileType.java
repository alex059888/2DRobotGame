package engine.entities.projectiles;

import org.joml.Vector2f;

public enum ProjectileType {
    SMALL_BULLET(15, new Vector2f(1,0), 300,1,10,4),
    MEDIUM_BULLET(10, new Vector2f(0,0), 400,5,40,4);

    private final int speed;
    private final Vector2f texPos;
    private final int lifeSpan;
    private final int dmg;
    private final int delay;
    private final float collRad;

    ProjectileType(int speed, Vector2f texPos, int lifeSpan, int dmg, int delay, float collRad) {
        this.speed = speed;
        this.texPos = texPos;
        this.lifeSpan = lifeSpan;
        this.dmg = dmg;
        this.delay = delay;
        this.collRad = collRad;
    }

    public float getCollRad() {
        return collRad;
    }

    public int getDelay() {
        return delay;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public Vector2f getTexPos() {
        return texPos;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}

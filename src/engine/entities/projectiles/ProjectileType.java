package engine.entities.projectiles;

import org.joml.Vector2f;

public enum ProjectileType {
    SMALL_BULLET(20, new Vector2f(1,0), 400),
    MEDIUM_BULLET(15, new Vector2f(0,0), 600);

    private final int speed;
    private final Vector2f texPos;
    private final int lifeSpan;

    ProjectileType(int speed, Vector2f texPos, int lifeSpan) {
        this.speed = speed;
        this.texPos = texPos;
        this.lifeSpan = lifeSpan;
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

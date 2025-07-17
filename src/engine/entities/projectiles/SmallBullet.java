package engine.entities.projectiles;

import engine.entities.Projectile;
import org.joml.Vector2f;

public class SmallBullet extends Projectile {
    public SmallBullet() {
        super(20, new Vector2f(1,0));
    }
}

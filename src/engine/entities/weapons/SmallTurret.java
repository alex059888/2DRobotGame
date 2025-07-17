package engine.entities.weapons;

import engine.entities.projectiles.ProjectileType;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SmallTurret extends Weapon {
    public SmallTurret(Vector3f pos, Vector3f origin) {
        super(pos, origin, new Vector2f(1,0),5, ProjectileType.SMALL_BULLET, new Vector3f(0,18,0));
        independentRotation = true;
    }
}

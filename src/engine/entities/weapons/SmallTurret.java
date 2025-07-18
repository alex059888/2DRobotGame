package engine.entities.weapons;

import engine.entities.projectiles.ProjectileType;
import engine.entities.template.SlotType;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SmallTurret extends Weapon {
    public SmallTurret(Vector3f pos) {
        super(pos, new Vector2f(1,0), ProjectileType.SMALL_BULLET, new Vector3f(0,18,0), SlotType.SMALL, "Small Turret");
        independentRotation = true;
    }
}

package engine.entities.weapons;

import engine.entities.EntityType;
import engine.entities.projectiles.ProjectileType;
import engine.entities.template.SlotType;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class LargeTurret extends Weapon {
    public LargeTurret(Vector3f pos, EntityType shotter) {
        super(pos, new Vector2f(0,0), ProjectileType.MEDIUM_BULLET,new Vector3f(0,36,0), SlotType.LARGE, "Large Turret", shotter);
        independentRotation = true;
    }
}

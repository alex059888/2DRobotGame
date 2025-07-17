package engine.entities.weapons;

import engine.entities.Weapon;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class LargeTurret extends Weapon {
    public LargeTurret(Vector3f pos, Vector3f origin) {
        super(pos, origin, new Vector2f(0,0), 10,0);
        independentRotation = true;
    }
}

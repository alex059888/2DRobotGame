package engine.entities;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class BlanckEntity extends Entity{
    public BlanckEntity() {
        super(new Vector3f(), EntityType.PLAYER, new Vector2i(), new Vector2f(), 0);
    }
}

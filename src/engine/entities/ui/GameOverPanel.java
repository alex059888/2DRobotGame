package engine.entities.ui;

import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class GameOverPanel extends Entity {
    public GameOverPanel(Vector3f pos) {
        super(pos, EntityType.UI, new Vector2i(128,64), new Vector2f(2,0), 5);
    }
}

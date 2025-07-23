package engine.entities.ui;

import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class UIEntity extends Entity {
    protected Vector2i subTexLength;

    public UIEntity(Vector3f pos, Vector2i subTexLength, Vector2f texPos, int texID) {
        super(pos, EntityType.UI, subTexLength, texPos,texID);
        this.subTexLength = subTexLength;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}

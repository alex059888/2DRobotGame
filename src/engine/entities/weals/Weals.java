package engine.entities.weals;

import engine.entities.Entity;
import engine.entities.EntityType;
import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Weals extends Entity {
    protected float speed;

    public Weals(Vector3f pos, Vector3f origin, Vector2f texPos) {
        super(pos, EntityType.LEGS,64,texPos,3);
        posFromOrigin = new Vector3f(pos.x - origin.x, pos.y - origin.y, pos.z - origin.z);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

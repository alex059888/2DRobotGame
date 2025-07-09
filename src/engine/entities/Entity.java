package engine.entities;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public abstract class Entity {
    protected Vector2f pos;
    protected float rot, scale;

    public Entity(Vector2f pos) {
        this.pos = pos;
        rot = 0;
        scale = 1;
    }

    public Matrix4f transform() {
        Matrix4f transform = new Matrix4f();
        transform.identity();

        transform.rotationZ(rot);
        transform.translate(new Vector3f(pos.x,pos.y,1));
        transform.scale(scale);

        return transform;
    }

    public abstract void tick(double dt);

    public abstract void render();

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}

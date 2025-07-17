package engine.entities;

import org.joml.*;
import org.joml.Math;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    protected Vector3f pos, posFromOrigin;
    protected float rot, scale, globalRot;
    protected List<Weapon> weapons;
    protected boolean independentRotation = false;
    protected EntityType type;

    public Entity(Vector3f pos, EntityType type) {
        this.pos = pos;
        this.type = type;
        posFromOrigin = new Vector3f();
        rot = 0;
        scale = 1;
        weapons = new ArrayList<>();
    }

    public Matrix4f transform() {
        Matrix4f transform = new Matrix4f();
        transform.identity();

        transform.translate(new Vector3f(pos.x,pos.y,1));
        transform.rotateZ(Math.toRadians(globalRot));
        transform.scale(scale);

        return transform;
    }

    public void lookAt(Vector2f p) {
        Vector2f v = new Vector2f(pos.x-p.x,pos.y-p.y);
        rot = Math.atan2(v.x,v.y)+180;
    }

    public void tick(double dt) {
        if (independentRotation)
            globalRot = rot;
        for(Entity e : weapons) {
            Vector3f cp = new Vector3f(e.posFromOrigin);
            Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
            cp.mul(m);
            cp.add(pos);
            e.pos = cp;
            e.globalRot = rot+e.rot;
            e.tick(dt);
        }
    }

    public void render() {
        for(Entity e : weapons)
            e.render();
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
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

    public void addWeapon(Weapon w) {
        weapons.add(w);
    }

    public Weapon getWeapon(int id) {
        return weapons.get(id);
    }

    public boolean isIndependentRotation() {
        return independentRotation;
    }

    public void setIndependentRotation(boolean independentRotation) {
        this.independentRotation = independentRotation;
    }

    public Vector3f getPosFromOrigin() {
        return posFromOrigin;
    }

    public void setPosFromOrigin(Vector3f posFromOrigin) {
        this.posFromOrigin = posFromOrigin;
    }

    public float getGlobalRot() {
        return globalRot;
    }

    public void setGlobalRot(float globalRot) {
        this.globalRot = globalRot;
    }
}

package engine.gfx;

import engine.entities.BlanckEntity;
import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private static Entity fallowedEntity = new BlanckEntity();
    private static Matrix4f ortho;

    public static Matrix4f getOrtho(int width, int height, float zFar, float zNear) {
        return new Matrix4f().ortho((float) -width /2, (float) width /2, (float) -height /2, (float) height /2,zNear,zFar);
    }

    public static Matrix4f getView() {
        Vector3f up = new Vector3f(0,1f,0);
        Vector3f front = new Vector3f(0,0,-1);
        return new Matrix4f().lookAt(fallowedEntity.getPos(),
                front.add(fallowedEntity.getPos().x, fallowedEntity.getPos().y,0),
                up);
    }

    public static Entity getFallowedEntity() {
        return fallowedEntity;
    }

    public static void setFallowedEntity(Entity fallowedEntity) {
        Camera.fallowedEntity = fallowedEntity;
    }

    public static Matrix4f getOrtho() {
        return ortho;
    }

    public static void setOrtho(Matrix4f ortho) {
        Camera.ortho = ortho;
    }
}

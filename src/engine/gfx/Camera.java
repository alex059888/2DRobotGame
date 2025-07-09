package engine.gfx;

import engine.entities.Entity;
import org.joml.Matrix4f;

public class Camera {
    private static Entity fallowedEntity;
    private static Matrix4f ortho;

    public static Matrix4f getOrtho(int width, int height, float zFar, float zNear) {
        Matrix4f o = new Matrix4f();
        o.m00((float) 2 /width);
        o.m11((float) 2 /height);
        o.m22((float) -2 /(zFar-zNear));
        o.m23(-(zFar + zNear) /(zFar - zNear));
        o.m33(1);
        return  o;
    }

    public static Matrix4f getView() {
        Matrix4f o = new Matrix4f();
        if (fallowedEntity != null) {
            o.m30(-fallowedEntity.getPos().x);
            o.m31(-fallowedEntity.getPos().y);
        }
        o.m00(1);
        o.m11(1);
        o.m22(1);
        o.m33(1);

        return o;
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

package engine.entities;

import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.*;
import org.joml.Math;

public class Entity {
    protected Vector3f pos;
    protected float rot, globalRot;
    protected boolean independentRotation = false;
    protected EntityType type;
    protected Vector2f texPos;
    protected Mesh mesh;
    protected int texId;
    protected Vector2i subTexLength;
    protected Vector3f scale;

    public Entity(Vector3f pos, EntityType type, int subTexLength, Vector2f texPos, int texId) {
        this.pos = pos;
        this.type = type;
        this.texPos = texPos;
        this.texId = texId;
        this.subTexLength = new Vector2i(subTexLength);
        rot = 0;
        scale = new Vector3f(1);
        mesh = genMesh(texPos);
    }

    public Entity(Vector3f pos, EntityType type, Vector2i subTexLength, Vector2f texPos, int texId) {
        this.pos = pos;
        this.type = type;
        this.texPos = texPos;
        this.texId = texId;
        this.subTexLength = subTexLength;
        rot = 0;
        scale = new Vector3f(1);
        mesh = genMesh(texPos);
    }

    protected Mesh genMesh(Vector2f texPos) {
        float[] verts = {
                //x   y   z r g b a u v
                -subTexLength.x,-subTexLength.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                -subTexLength.x, subTexLength.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                subTexLength.x, subTexLength.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                subTexLength.x,-subTexLength.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize().y, subTexLength.y)
        };
        int[] ebos = {
                0,1,2,2,3,0
        } ;
        return new Mesh(verts, ebos);
    }

    protected Mesh genMesh(Vector2f texPos, Vector2f size) {
        float[] verts = {
                //x   y   z r g b a u v
                -size.x,-size.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                -size.x, size.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                size.x, size.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y,Texture.getTexture(texId).getTexSize().y, subTexLength.y),
                size.x,-size.y,0,1,1,1,1,
                Texture.getTexCoord(texPos.x+1,Texture.getTexture(texId).getTexSize().x, subTexLength.x),
                Texture.getTexCoord(texPos.y+1,Texture.getTexture(texId).getTexSize().y, subTexLength.y)
        };
        int[] ebos = {
                0,1,2,2,3,0
        } ;
        return new Mesh(verts, ebos);
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
    }

    public void render() {
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        mesh.render();
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

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public boolean isIndependentRotation() {
        return independentRotation;
    }

    public void setIndependentRotation(boolean independentRotation) {
        this.independentRotation = independentRotation;
    }

    public float getGlobalRot() {
        return globalRot;
    }

    public void setGlobalRot(float globalRot) {
        this.globalRot = globalRot;
    }

    public EntityType getType() {
        return type;
    }

    public void setScaleX(float x) {
        scale.x = x;
    }

    public void setScaleY(float y) {
        scale.y = y;
    }

    public void setScaleZ(float z) {
        scale.z = z;
    }
}

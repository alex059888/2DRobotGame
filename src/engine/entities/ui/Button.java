package engine.entities.ui;

import engine.gfx.Camera;
import engine.gfx.mesh.Mesh;
import engine.gfx.textures.Texture;
import engine.io.MouseListener;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class Button extends UIEntity {
    private Mesh mesh2;

    private boolean onPoint = false, meshA2 = false;
    protected float adjXL = 0, adjXR = 0, adjYU = 0, adjYD = 0;
    protected int delay;

    public Button(Vector3f pos, Vector2f texPos, Vector2f texPos2) {
        super(pos, new Vector2i(128,64), texPos, 5);
        mesh2 = genMesh(texPos2);
        delay = 0;
    }


    @Override
    public void tick(double dt) {
        super.tick(dt);
        Vector2f v = new Vector2f(MouseListener.getX() + Camera.getFallowedEntity().getPos().x - (float) Handler.getWindow().getWidth() / 2,
                MouseListener.getY() + Camera.getFallowedEntity().getPos().y - (float) Handler.getWindow().getHeight() / 2);
        if (v.x > (pos.x - subTexLength.x * scale.x - adjXL) * ((float) Handler.getWindow().getWidth() / Handler.getWindow().getDefaultWidth()) &&
                v.x < (pos.x + subTexLength.x * scale.x - adjXR) * ((float) Handler.getWindow().getWidth() / Handler.getWindow().getDefaultWidth()) &&
                v.y > (-pos.y - subTexLength.y * scale.y - adjYU) * ((float) Handler.getWindow().getHeight() / Handler.getWindow().getDefaultHeight()) &&
                v.y < (-pos.y + subTexLength.y * scale.y - adjYD) * ((float) Handler.getWindow().getHeight() / Handler.getWindow().getDefaultHeight())) {
            if (MouseListener.mouseButtonDown(0) && delay <= 0)
                doAction();
            else if (MouseListener.mouseButtonDown(0)) delay = 2;
            onPoint = true;
        } else
            onPoint = false;
        if (onPoint && !meshA2) {
            meshA2 = true;
        } else if (!onPoint && meshA2) {
            meshA2 = false;
        }
        delay--;
    }

    @Override
    public void render() {
        Handler.getCurentShader().setTransform(transform());
        Texture.getTexture(texId).bind();
        if (meshA2) mesh2.render();
        else mesh.render();
    }

    protected void doAction() {
        delay = 2;
    }

    protected void setAdjusters(float adjXL, float adjXR, float adjYU, float adjYD) {
        this.adjXL = adjXL;
        this.adjXR = adjXR;
        this.adjYU = adjYU;
        this.adjYD = adjYD;
    }
}

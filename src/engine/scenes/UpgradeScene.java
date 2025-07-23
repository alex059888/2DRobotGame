package engine.scenes;

import engine.entities.BlanckEntity;
import engine.entities.ui.*;
import engine.gfx.Camera;
import engine.gfx.shaders.DefaultShader;
import engine.gfx.shaders.Shader;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class UpgradeScene extends Scene{
    private DefaultShader defaultShader;
    private Button backBtn;
    private UIEntity bcg;

    public UpgradeScene() {
        this.defaultShader = new DefaultShader();
        Camera.setFallowedEntity(new BlanckEntity());
        bcg = new BackGround(new Vector3f());
        backBtn = new BackBtn(new Vector3f(-256,-256,0));
    }

    @Override
    public void tick(double dt) {
        bcg.tick(dt);
        backBtn.tick(dt);
    }

    @Override
    public void render() {
        defaultShader.bind();
        defaultShader.setTexture("tex", 0);
        Texture.getTexture(0).bind();
        glActiveTexture(GL_TEXTURE0);
        defaultShader.setProjection(Camera.getOrtho());
        defaultShader.setView(Camera.getView());
        Handler.setCurentShader(defaultShader);
        //aici da render la entitati
        backBtn.render();
        bcg.render();
        Shader.unBind();
    }
}

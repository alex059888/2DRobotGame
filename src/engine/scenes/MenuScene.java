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

public class MenuScene extends Scene{
    private DefaultShader defaultShader;
    private Button playBtn, quitBtn,upgBtn;
    private UIEntity bcg;

    public MenuScene() {
        this.defaultShader = new DefaultShader();
        Camera.setFallowedEntity(new BlanckEntity());
        playBtn = new PlayButton(new Vector3f(0, 160,0));
        quitBtn = new QuitBtn(new Vector3f(0,-160,0));
        upgBtn = new UpgradeBtn(new Vector3f(0,0,0));
        bcg = new BackGround(new Vector3f());
    }

    @Override
    public void tick(double dt) {
        playBtn.tick(dt);
        quitBtn.tick(dt);
        upgBtn.tick(dt);
        bcg.tick(dt);
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
        playBtn.render();
        upgBtn.render();
        quitBtn.render();
        bcg.render();
        Shader.unBind();
    }
}

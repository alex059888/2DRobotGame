package engine.scenes;

import engine.entities.BlanckEntity;
import engine.entities.Entity;
import engine.entities.ui.*;
import engine.gfx.Camera;
import engine.gfx.shaders.DefaultShader;
import engine.gfx.shaders.Shader;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class GameOverScene extends Scene {
    private DefaultShader defaultShader;
    private Button menu, quit, restart;
    private UIEntity bcg;
    private Entity score, gmOver;

    public GameOverScene() {
        this.defaultShader = new DefaultShader();
        Camera.setFallowedEntity(new BlanckEntity());
        bcg = new BackGround(new Vector3f());
        menu = new MenuBtn(new Vector3f(0,-256,0));
        quit = new QuitBtn(new Vector3f(-256,-256,0));
        restart = new RestartBtn(new Vector3f(256,-256,0));
        score = new ScorePanel(new Vector3f(0,0,0));
        gmOver = new GameOverPanel(new Vector3f(0,256,0));
    }

    @Override
    public void tick(double dt) {
        bcg.tick(dt);
        menu.tick(dt);
        quit.tick(dt);
        restart.tick(dt);
        score.tick(dt);
        gmOver.tick(dt);
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
        menu.render();
        gmOver.render();
        score.render();
        quit.render();
        restart.render();
        bcg.render();
        Shader.unBind();
    }
}

package engine.scenes;

import engine.entities.Player;
import engine.gfx.Camera;
import engine.gfx.shaders.DefaultShader;
import engine.gfx.shaders.Shader;
import engine.gfx.Texture;
import engine.util.Handler;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class GameScene extends Scene{
    private Shader defaultShader;
    private Player player;

    public GameScene() {
        this.defaultShader = new DefaultShader();
        player = new Player(new Vector2f(0.0f,0.0f));
    }

    @Override
    public void tick(double dt) {
        player.tick(dt);
        player.setScale(player.getScale()+.01f);
    }

    @Override
    public void render() {
        defaultShader.bind();
        defaultShader.setTexture("tex", Texture.getTexture(0).getId());
        glActiveTexture(GL_TEXTURE0);
        defaultShader.setProjection(Camera.getOrtho());
        defaultShader.setView(Camera.getView());
        Handler.setCurentShader(defaultShader);
        //aici da render la entitati
        player.render();
        Shader.unBind();
    }
}

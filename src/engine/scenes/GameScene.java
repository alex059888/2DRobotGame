package engine.scenes;

import engine.entities.Player;
import engine.gfx.Camera;
import engine.gfx.shaders.DefaultShader;
import engine.gfx.shaders.Shader;
import engine.gfx.Texture;
import engine.util.Handler;
import engine.world.World;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class GameScene extends Scene{
    private Shader defaultShader;
    private Player player;
    private World world;

    public GameScene() {
        this.defaultShader = new DefaultShader();
        player = new Player(new Vector3f(0.0f,0.0f,0.0f));
        world = new World();
        Camera.setFallowedEntity(player);
    }

    @Override
    public void tick(double dt) {
        player.tick(dt);
        world.tick(dt);
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
        world.render();
        Shader.unBind();
    }
}

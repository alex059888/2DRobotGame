package engine.scenes;

import engine.gfx.Camera;
import engine.gfx.shaders.DefaultShader;
import engine.gfx.shaders.Shader;
import engine.gfx.textures.Texture;
import engine.util.Handler;
import engine.world.World;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class GameScene extends Scene{
    private Shader defaultShader;
    private World world;

    public GameScene() {
        this.defaultShader = new DefaultShader();
        world = new World();
    }

    @Override
    public void tick(double dt) {
        world.tick(dt);
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
        world.render();
        Shader.unBind();
    }
}

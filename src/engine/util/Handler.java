package engine.util;

import engine.gfx.shaders.Shader;
import engine.scenes.Scene;

public class Handler {
    private static Scene currentScene;
    private static Shader curentShader;

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        Handler.currentScene = currentScene;
    }

    public static Shader getCurentShader() {
        return curentShader;
    }

    public static void setCurentShader(Shader curentShader) {
        Handler.curentShader = curentShader;
    }
}

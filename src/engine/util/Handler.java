package engine.util;

import engine.gfx.Window;
import engine.gfx.shaders.Shader;
import engine.scenes.Scene;

public class Handler {
    private static Scene currentScene;
    private static Shader curentShader;
    private static Window window;

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

    public static Window getWindow() {
        return window;
    }

    public static void setWindow(Window window) {
        Handler.window = window;
    }
}

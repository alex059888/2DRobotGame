package engine.util;

import engine.gfx.Window;
import engine.gfx.shaders.Shader;
import engine.scenes.Scene;
import engine.world.World;
import main.Game;

public class Handler {
    private static Scene currentScene;
    private static Shader curentShader;
    private static Window window;
    private static World world;
    private static int score = 0;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Handler.score = score;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static Game game;

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Handler.game = game;
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

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World world) {
        Handler.world = world;
    }

    public static void addScore(int score) {
        Handler.score += score;
    }
}

package main;

import engine.gfx.Camera;
import engine.gfx.Window;
import engine.io.MouseListener;
import engine.scenes.GameScene;
import engine.util.Handler;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Game {
    private Window window;
    private Vector4f refreshColor;
    public static double FPS = 60;

    private void init() {
        glfwInit();
        refreshColor = new Vector4f(0);
        window = new Window(1280,720,"RobotGame");

        window.makeContextCurrent();
        glfwSwapInterval(1);
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glEnable(GL_BLEND);
        glCullFace(GL_FRONT);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        Camera.setOrtho(Camera.getOrtho(window.getWidth(),window.getHeight(),100f,-100f));
        Handler.setWindow(window);

        Handler.setCurrentScene(new GameScene());
    }

    public void run() {
        init();
        loop();
        stop();
    }

    private void loop() {
        double timePerFrame = 1 / FPS;
        double dt = 0;
        double now, lastTime = (double) System.nanoTime()/1000000000;
        double cdt;

        while (!window.shouldClose()) {
            now = (double) System.nanoTime()/1000000000;
            cdt = (now - lastTime) / timePerFrame;
            dt += cdt;
            lastTime = now;

            if(dt >= 1.0f) {
                glfwPollEvents();
                tick(dt);
                render();
                dt=0.0f;
            }
        }
    }

    private void tick(double dt) {
        Handler.getCurrentScene().tick(dt);
        MouseListener.endFrame();
    }
    private void render() {
        glClearColor(refreshColor.x,refreshColor.y,refreshColor.z,refreshColor.w);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        Handler.getCurrentScene().render();

        glfwSwapBuffers(window.getWindow());
    }

    private void stop() {
        glfwFreeCallbacks(window.getWindow());
        glfwDestroyWindow(window.getWindow());
        glfwTerminate();
    }

    public Window getWindow() {
        return window;
    }
}

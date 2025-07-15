package engine.entities;

import engine.io.KeyListener;
import engine.io.MouseListener;
import engine.util.Handler;
import org.joml.*;
import org.joml.Math;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Creature{

    public Player(Vector3f pos) {
        super(pos);
        addEntity(new Weapon(new Vector3f(pos.x, pos.y, pos.z),pos));
    }

    @Override
    public void tick(double dt) {
        if(KeyListener.isKeyPressed(GLFW_KEY_UP))
            pos.y+=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_DOWN))
            pos.y-=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_LEFT))
            pos.x-=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT))
            pos.x+=speed*dt;

        Vector2f p = new Vector2f(MouseListener.getX() - (float) Handler.getWindow().getWidth() /2, MouseListener.getY() - (float) Handler.getWindow().getHeight() /2);
        rot = Math.toDegrees(Math.atan2(p.x,p.y))+180;
        super.tick(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}

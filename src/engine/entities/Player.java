package engine.entities;

import engine.entities.weapons.LargeTurret;
import engine.entities.weapons.SmallTurret;
import engine.entities.weapons.Weapon;
import engine.io.KeyListener;
import engine.io.MouseListener;
import engine.util.Handler;
import org.joml.*;
import org.joml.Math;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Creature{

    public Player(Vector3f pos) {
        super(pos, new Vector2f(1,0), EntityType.PLAYER);
        addWeapon(new LargeTurret(new Vector3f(pos.x, pos.y-24, pos.z),pos));
        addWeapon(new SmallTurret(new Vector3f(pos.x+16, pos.y+28, pos.z),pos));
        addWeapon(new SmallTurret(new Vector3f(pos.x-16, pos.y+28, pos.z),pos));
    }

    @Override
    public void tick(double dt) {
        Vector3f walk = new Vector3f(0,0,0);
        if(KeyListener.isKeyPressed(GLFW_KEY_UP) || KeyListener.isKeyPressed(GLFW_KEY_W))
            walk.y+=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_DOWN) || KeyListener.isKeyPressed(GLFW_KEY_S))
            walk.y-=speed*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_LEFT) || KeyListener.isKeyPressed(GLFW_KEY_A))
            rot += 2*dt;
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT) || KeyListener.isKeyPressed(GLFW_KEY_D))
            rot -= 2*dt;
        Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
        walk.mul(m);
        pos.add(walk);

        Vector2f p = new Vector2f(MouseListener.getX() - (float) Handler.getWindow().getWidth() /2, MouseListener.getY() - (float) Handler.getWindow().getHeight() /2);
        for (Weapon e : weapons)
            e.rot = Math.toDegrees(Math.atan2(p.x,p.y))+180;

        if (MouseListener.mouseButtonDown(0)) {
            for (Weapon e : weapons) e.shot();
        }
        super.tick(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}

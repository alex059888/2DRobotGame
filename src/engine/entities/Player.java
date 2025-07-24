package engine.entities;

import engine.entities.template.Template;
import engine.io.KeyListener;
import engine.io.MouseListener;
import engine.util.Handler;
import org.joml.*;
import org.joml.Math;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Creature{
    public static int mBP = 100, delayConsBP = 2;
    private int boostPoints, cdc;
    private boolean rest;

    public Player(Vector3f pos) {
        super(pos, new Vector2f(1,0), EntityType.PLAYER, 100,32, 100);

        template = Template.genTemplate("Small Tank", this);
        template.setWeapon("Large Turret",0, EntityType.PLAYER);
        template.setWeapon("Small Turret",1, EntityType.PLAYER);
        template.setWeapon("Small Turret",2, EntityType.PLAYER);
        boostPoints = mBP;
        cdc = 0;
        rest = false;
        template.setDmgMul(4);
    }

    @Override
    public void tick(double dt) {
        Vector3f walk = new Vector3f(0,0,0);
        if(KeyListener.isKeyPressed(GLFW_KEY_UP) || KeyListener.isKeyPressed(GLFW_KEY_W))
            walk.y+= (float) (speed*dt);
        if(KeyListener.isKeyPressed(GLFW_KEY_DOWN) || KeyListener.isKeyPressed(GLFW_KEY_S))
            walk.y-= (float) (speed*dt);
        if(KeyListener.isKeyPressed(GLFW_KEY_LEFT) || KeyListener.isKeyPressed(GLFW_KEY_A))
            rot += (float) (2*dt);
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT) || KeyListener.isKeyPressed(GLFW_KEY_D))
            rot -= (float) (2*dt);
        if((KeyListener.isKeyPressed(GLFW_KEY_RIGHT_SHIFT) || KeyListener.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) && boostPoints > 0 && !rest && walk.y > 0) {
            walk.y *= 2;
            cdc++;
            if (cdc > delayConsBP) cdc = 0;
            if (cdc == delayConsBP) {
                cdc-=delayConsBP;
                boostPoints--;
            }
            if (boostPoints <= 0)
                rest = true;
        } else {
            cdc++;
            if (cdc >= delayConsBP * 2 && !(KeyListener.isKeyPressed(GLFW_KEY_RIGHT_SHIFT) || KeyListener.isKeyPressed(GLFW_KEY_LEFT_SHIFT))) {
                cdc = 0;
                boostPoints++;
            }
            if (boostPoints > 20)
                rest = false;
        }
        Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
        walk.mul(m);
        pos.add(walk);

        Vector2f p = new Vector2f(MouseListener.getX() - (float) Handler.getWindow().getWidth() /2, MouseListener.getY() - (float) Handler.getWindow().getHeight() /2);
        template.setRotations(Math.toDegrees(Math.atan2(p.x,p.y))+180);
        if (MouseListener.mouseButtonDown(0)) {
            template.shot();
        }
        super.tick(dt);
    }

    @Override
    public void render() {
        super.render();
    }

    public void addHealth(int health) {
        hp += health;
        if (hp > maxHP) hp = maxHP;
    }

    public void addShield(int shield) {
        this.shield += shield;
        if (this.shield > maxShield) this.shield = maxShield;
    }
}

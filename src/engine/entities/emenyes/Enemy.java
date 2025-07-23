package engine.entities.emenyes;

import engine.entities.Creature;
import engine.entities.EntityType;
import engine.util.Handler;
import engine.world.World;
import org.joml.*;
import org.joml.Math;

import java.util.Random;

public class Enemy extends Creature {
    protected Vector2f targetPos;
    protected int score;

    public Enemy(Vector3f pos, Vector2f texPos, int MHP, float collRad, int score) {
        super(pos, texPos, EntityType.ENEMY, MHP, collRad,0);
        setRandTargetPos();
        this.score = score;
    }

    @Override
    public void tick(double dt) {
        Vector2f distToPlayer = new Vector2f( //dist to player
                Handler.getWorld().getPlayer().getPos().x-pos.x,
                Handler.getWorld().getPlayer().getPos().y-pos.y
        );
        float lookAtPlayer = Math.toDegrees(-Math.atan2(distToPlayer.x,distToPlayer.y)); //rotation to look at player
        template.setRotations(lookAtPlayer);
        if (distToPlayer.length() < 550)
            template.shot();
        super.tick(dt);

        Vector3f walk = new Vector3f(0,0,0);

        Vector2f lookAtTarget = new Vector2f(targetPos).sub(new Vector2f(pos.x, pos.y));//dist to target pos
        if(lookAtTarget.length() > 100)
            walk.y+= (float) (speed*dt);
        if (distToPlayer.length() > 300)
            setRandTargetPos();
        else targetPos = new Vector2f(Handler.getWorld().getPlayer().getPos().x, Handler.getWorld().getPlayer().getPos().y);
        float targetRotation = Math.toDegrees(-Math.atan2(lookAtTarget.x,lookAtTarget.y))-rot;
        if(targetRotation > 0f)
            rot += (float) (2*dt);
        if(targetRotation < -0f)
            rot -= (float) (2*dt);
        Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(rot));
        walk.mul(m);
        pos.add(walk);
        if (hp <= 0)
            onDeath();
    }

    public Vector2f getTargetPos() {
        return targetPos;
    }

    public void setTargetPos(Vector2f targetPos) {
        this.targetPos = targetPos;
    }

    public void setRandTargetPos() {
        Random random = new Random();
        float rn = random.nextFloat(-World.getMaxDistFormPlayer(), World.getMaxDistFormPlayer());
        float ru = random.nextFloat(-World.getMaxDistFormPlayer(), World.getMaxDistFormPlayer());

        targetPos = new Vector2f(rn,ru).add(new Vector2f(Handler.getWorld().getPlayer().getPos().x,Handler.getWorld().getPlayer().getPos().y));
    }

    public void onDeath() {
        Handler.addScore(score);
    }
}

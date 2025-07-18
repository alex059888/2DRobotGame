package engine.entities.emenyes;

import engine.entities.Creature;
import engine.entities.EntityType;
import engine.util.Handler;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Enemy extends Creature {
    public Enemy(Vector3f pos, Vector2f texPos, int MHP) {
        super(pos, texPos, EntityType.ENEMY, MHP);
    }

    @Override
    public void tick(double dt) {
        Vector2f p = new Vector2f(
                Handler.getWorld().getPlayer().getPos().x-pos.x,
                Handler.getWorld().getPlayer().getPos().y-pos.y
        );
        template.setRotations(Math.toDegrees(-Math.atan2(p.x,p.y)));
        if (p.length() < 550)
            template.shot();
        super.tick(dt);
    }
}

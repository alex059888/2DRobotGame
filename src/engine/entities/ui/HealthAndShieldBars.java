package engine.entities.ui;

import engine.entities.Entity;
import engine.entities.EntityType;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class HealthAndShieldBars extends Entity {
    private Entity health, shield;

    public HealthAndShieldBars(Vector3f pos) {
        super(pos, EntityType.UI, new Vector2i(128,64), new Vector2f(0,0), 9);
        health = new Entity(pos,EntityType.UI,new Vector2i(120,24),new Vector2f(0,0),8);
        shield = new Entity(pos,EntityType.UI,new Vector2i(120,24),new Vector2f(0,1),8);
        health.setScale(new Vector3f(1,1.01f,1));
        shield.setScale(new Vector3f(1,1.01f,1));
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        pos = new Vector3f(Handler.getWorld().getPlayer().getPos()).add(new Vector3f(-512,-296,0));
        int pHealth = Handler.getWorld().getPlayer().getHp();
        int pMaxHealth = Handler.getWorld().getPlayer().getMaxHP();
        int pShield = Handler.getWorld().getPlayer().getShield();
        int pMaxShield = Handler.getWorld().getPlayer().getMaxShield();
        float precent = (float) pHealth /pMaxHealth;
        float shieldPrec = (float) pShield/pMaxShield;
        health.setPos(new Vector3f(pos).add(new Vector3f(-(120*(1-precent)),32,0)));
        shield.setPos(new Vector3f(pos).add(new Vector3f(-(120*(1-shieldPrec)),-32,0)));
        health.setScaleX(precent);
        shield.setScaleX(shieldPrec);
        health.tick(dt);
        shield.tick(dt);
    }

    @Override
    public void render() {
        super.render();
        health.render();
        shield.render();
    }
}

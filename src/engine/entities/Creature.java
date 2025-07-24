package engine.entities;

import engine.entities.template.Template;
import engine.entities.weals.Shenile;
import engine.entities.weals.Weals;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Creature extends Entity{
    protected float speed = 5;
    protected Weals weals;
    protected Template template;
    protected int hp, maxHP, shield, maxShield;
    protected float collRad;
    protected int delayTilDmg;

    public Creature(Vector3f pos, Vector2f texPos, EntityType type, int maxHP, float collRad, int maxShield) {
        super(pos, type,64,texPos, 3);
        setIndependentRotation(true);
        weals = new Shenile(pos);
        this.maxHP = maxHP;
        hp = maxHP;
        this.collRad = collRad;
        shield = maxShield;
        this.maxShield = maxShield;
        delayTilDmg = 0;
    }

    @Override
    public void render() {
        template.render();
        super.render();
        weals.render();
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        template.tick(dt);
        weals.tick(dt);
        weals.setGlobalRot(globalRot);
        delayTilDmg--;
        if (delayTilDmg < 0) delayTilDmg = 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public float getCollRad() {
        return collRad;
    }

    public static boolean isCollision(Creature creature, Creature creature1) {
        float dist = new Vector2f(creature.getPos().x-creature1.getPos().x,creature.getPos().y-creature1.getPos().y).length();
        return (dist-creature.getCollRad()-creature1.getCollRad())<=0;
    }

    public void damage(int dmg) {
        shield -= dmg;
        if (shield < 0) {
            hp += shield;
            shield = 0;
        }
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getMaxShield() {
        return maxShield;
    }

    public void setMaxShield(int maxShield) {
        this.maxShield = maxShield;
    }

    public void collide(Creature creature) {
        Vector2f v = new Vector2f(creature.getPos().x-pos.x,creature.getPos().y-pos.y);
        float dist = v.length();
        if ((dist-creature.getCollRad()-collRad)<=0) {
            pos.add(new Vector3f(-v.x/20,-v.y/20,0));
            creature.pos.add(new Vector3f(v.x/30,v.y/30,0));
            if (creature.type != EntityType.ENEMY && delayTilDmg <= 0) {
                damage(20);
                creature.damage(10);
                delayTilDmg = 10;
            }
        }
    }
}

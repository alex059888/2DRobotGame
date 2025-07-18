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
    protected int HP, MHP;

    public Creature(Vector3f pos, Vector2f texPos, EntityType type, int MHP) {
        super(pos, type,64,texPos, 3);
        setIndependentRotation(true);
        weals = new Shenile(pos);
        this.MHP = MHP;
        HP = MHP;
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
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMHP() {
        return MHP;
    }

    public void setMHP(int MHP) {
        this.MHP = MHP;
    }
}

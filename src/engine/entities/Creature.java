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

    public Creature(Vector3f pos, Vector2f texPos, EntityType type) {
        super(pos, type,64,texPos, 3);
        setIndependentRotation(true);
        weals = new Shenile(pos);
    }

    @Override
    public void render() {
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
}

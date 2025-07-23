package engine.entities.emenyes;

import engine.entities.EntityType;
import engine.entities.template.Template;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SmallSandTank extends Enemy{
    public SmallSandTank(Vector3f pos) {
        super(pos, new Vector2f(0,0), 80, 32,5);

        template = Template.genTemplate("Small Tank",this);
        template.setWeapon("Small Turret",1, EntityType.ENEMY);
        template.setWeapon("Small Turret",2, EntityType.ENEMY);
    }
}

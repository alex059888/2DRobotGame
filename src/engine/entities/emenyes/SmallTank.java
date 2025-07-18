package engine.entities.emenyes;

import engine.entities.template.Template;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SmallTank extends Enemy{
    public SmallTank(Vector3f pos) {
        super(pos, new Vector2f(1,0), 80);

        template = Template.genTemplate("Small Tank",this);
        template.setWeapon("Large Turret",0);
    }
}

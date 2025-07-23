package engine.entities.ui;

import engine.entities.Entity;
import engine.entities.EntityType;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class Number extends Entity {

    public Number(Vector3f pos, Vector2f texPos) {
        super(pos, EntityType.UI, new Vector2i(10,16), texPos, 7);
    }

    public static Number getNumber(int num, Vector3f pos) {
        return switch (num) {
            case  1 -> new Number(pos,new Vector2f(9,0));
            case  2 -> new Number(pos,new Vector2f(8,0));
            case  3 -> new Number(pos,new Vector2f(7,0));
            case  4 -> new Number(pos,new Vector2f(6,0));
            case  5 -> new Number(pos,new Vector2f(5,0));
            case  6 -> new Number(pos,new Vector2f(4,0));
            case  7 -> new Number(pos,new Vector2f(3,0));
            case  8 -> new Number(pos,new Vector2f(2,0));
            case  9 -> new Number(pos,new Vector2f(1,0));
            default -> new Number(pos,new Vector2f(0,0));
        };
    }
}

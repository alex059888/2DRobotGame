package engine.entities;

import engine.entities.template.Slot;
import engine.entities.template.SlotType;
import engine.entities.template.Template;
import engine.entities.weapons.LargeTurret;
import engine.entities.weapons.SmallTurret;
import engine.entities.weapons.Weapon;
import org.joml.Vector3f;

public class EntityGenCarac {

    public static Template templates(Template template) {
        return templates(template.getTag());
    }

    public static Template templates(String tag) {
        return switch (tag) {
            case "Small Tank" -> new Template(new Slot[]{
                    new Slot(new Vector3f(0, -24, 0), SlotType.LARGE),
                    new Slot(new Vector3f(16, 28, 0), SlotType.SMALL),
                    new Slot(new Vector3f(-16, 28, 0), SlotType.SMALL)
            }, null, "Small Tank");
            default -> null;
        };
    }

    public static Weapon genWeapon(Weapon weapon, Vector3f pos) {
        return genWeapon(weapon.getTag(), pos);
    }

    public static Weapon genWeapon(String weapon, Vector3f pos) {
        return switch (weapon) {
            case "Small Turret" -> new SmallTurret(pos);
            case "Large Turret" -> new LargeTurret(pos);
            default -> null;
        };
    }
}

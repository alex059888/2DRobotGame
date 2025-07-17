package engine.entities.template;

import engine.entities.Entity;
import engine.entities.weapons.Weapon;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.util.Map;

public class Template {
    private Slot[] slots;
    private Entity holder;

    public static Map<String,Template> templates = Map.of("Small Tank",
            new Template(new Slot[]{
                    new Slot(new Vector3f(0,-24,0), SlotType.LARGE),
                    new Slot(new Vector3f(16,28,0), SlotType.SMALL),
                    new Slot(new Vector3f(-16,28,0), SlotType.SMALL)
            }, null)
    );

    public static Template getTemplate(String template) {
        return templates.get(template);
    }

    public Template(Template template, Entity holder) {
        slots = new Slot[template.getSlots().length];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = template.getSlots()[i];
        }
        this.holder = holder;
    }

    public Template(String template, Entity holder) {
        slots = new Slot[templates.get(template).getSlots().length];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = templates.get(template).getSlots()[i];
        }
        this.holder = holder;
    }

    public Template(Slot[] slots, Entity holder) {
        this.slots = slots;
        this.holder = holder;
    }

    public Template(int slotNr, Entity holder) {
        slots = new Slot[slotNr];
        this.holder = holder;
    }

    public void setSlot(Slot slot, int id) {
        if(id >= 0 && id < slots.length)
            slots[id] = slot;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    public void tick(double dt) {
        for (Slot s : slots)
            if (s != null) {
                if (s.getWeapon() != null) {
                    Vector3f cp = new Vector3f(s.getPosFromOrg());
                    Matrix3f m = new Matrix3f().identity().rotateZ(Math.toRadians(holder.getRot()));
                    cp.mul(m);
                    cp.add(holder.getPos());
                    s.getWeapon().setPos(cp);
                    s.getWeapon().setGlobalRot(holder.getRot() + s.getWeapon().getRot());
                    s.tick(dt);
                }
            }
    }

    public void render() {
        for (Slot s : slots)
            if (s != null)
                s.render();
    }

    public void shot() {
        for (Slot s : slots)
            if (s != null)
                s.shot();
    }

    public void setRotations(float rot) {
        for (Slot s : slots)
            if (s != null)
                if (s.getWeapon() != null)
                    s.getWeapon().setRot(rot);
    }

    public Entity getHolder() {
        return holder;
    }

    public boolean setWeapon(Weapon weapon, int id) {
        if (id >= 0 && id < slots.length) {
            if (weapon.getSlotType() != slots[id].getType())
                return false;
            slots[id].setWeapon(weapon);
            return true;
        }
        return false;
    }

    public boolean setWeapon(String weapon, int id) {
        if (id >= 0 && id < slots.length) {
            Weapon temp = Weapon.genWeapon(weapon, holder.getPos());
            if (temp != null) {
                if (temp.getSlotType() != slots[id].getType())
                    return false;
                slots[id].setWeapon(temp);
                return true;
            }
        }
        return false;
    }
}

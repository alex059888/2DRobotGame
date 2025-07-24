package engine.entities.template;

import engine.entities.Entity;
import engine.entities.EntityGenCarac;
import engine.entities.EntityType;
import engine.entities.weapons.Weapon;
import org.joml.Math;
import org.joml.Matrix3f;
import org.joml.Vector3f;

public class Template {
    private Slot[] slots;
    private Entity holder;
    private String tag;
    private int dmgMul;

    public static Template genTemplate(String tag, Entity holder) {
        Template t = EntityGenCarac.templates(tag);
        t.setHolder(holder);
        return t;
    }

    public Template(Slot[] slots, Entity holder, String tag) {
        this.slots = slots;
        this.tag =tag;
        this.holder = holder;
        dmgMul = 1;
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
                s.shot(dmgMul);
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

    public boolean setWeapon(String weapon, int id, EntityType shotter) {
        if (id >= 0 && id < slots.length) {
            Weapon temp = EntityGenCarac.genWeapon(weapon, holder.getPos(), shotter);
            if (temp != null) {
                if (temp.getSlotType() != slots[id].getType())
                    return false;
                slots[id].setWeapon(temp);
                return true;
            }
        }
        return false;
    }

    public void setHolder(Entity holder) {
        this.holder = holder;
    }

    public String getTag() {
        return tag;
    }

    public int getDmgMul() {
        return dmgMul;
    }

    public void setDmgMul(int dmgMul) {
        this.dmgMul = dmgMul;
    }
}

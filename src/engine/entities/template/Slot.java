package engine.entities.template;

import engine.entities.EntityType;
import engine.entities.weapons.Weapon;
import org.joml.Vector3f;

import javax.management.ObjectInstance;

public class Slot {
    private Vector3f posFromOrg;
    private Weapon weapon;
    private SlotType type;

    public Slot(Vector3f posFromOrg, SlotType type, Weapon weapon) {
        this.posFromOrg = posFromOrg;
        this.weapon = weapon;
        this.type = type;
        weapon.setPos(posFromOrg);
    }

    public Slot(Slot slot) {
        this.posFromOrg = new Vector3f(slot.getPosFromOrg());
        this.weapon = null;
        this.type = slot.type;
    }

    public Slot(Vector3f posFromOrg, SlotType type) {
        this.posFromOrg = posFromOrg;
        this.type = type;
    }

    public Vector3f getPosFromOrg() {
        return posFromOrg;
    }

    public void setPosFromOrg(Vector3f posFromOrg) {
        this.posFromOrg = posFromOrg;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void tick(double dt) {
        if (weapon != null)
            weapon.tick(dt);
    }

    public void render() {
        if (weapon != null)
            weapon.render();
    }

    public SlotType getType() {
        return type;
    }

    public void setType(SlotType type) {
        this.type = type;
    }

    public void shot() {
        if (weapon != null)
            weapon.shot();
    }
}

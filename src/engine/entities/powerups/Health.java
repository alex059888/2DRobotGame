package engine.entities.powerups;

import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Health extends Powerup{
    public Health(Vector3f pos) {
        super(pos, new Vector2f(0,0), 14);
    }

    @Override
    public void onContact() {
        super.onContact();
        Handler.getWorld().getPlayer().addHealth(50);
    }
}

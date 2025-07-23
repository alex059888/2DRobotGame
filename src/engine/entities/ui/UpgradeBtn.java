package engine.entities.ui;

import engine.scenes.UpgradeScene;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class UpgradeBtn extends Button{
    public UpgradeBtn(Vector3f pos) {
        super(pos, new Vector2f(0,3), new Vector2f(1,3));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.setCurrentScene(new UpgradeScene());
    }
}

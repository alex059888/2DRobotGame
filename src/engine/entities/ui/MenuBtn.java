package engine.entities.ui;

import engine.scenes.MenuScene;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class MenuBtn extends Button{
    public MenuBtn(Vector3f pos) {
        super(pos, new Vector2f(0,2), new Vector2f(1,2));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.setCurrentScene(new MenuScene());
    }
}

package engine.entities.ui;

import engine.scenes.MenuScene;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class BackBtn extends Button{
    public BackBtn(Vector3f pos) {
        super(pos, new Vector2f(0,4), new Vector2f(1,4));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.setCurrentScene(new MenuScene());
    }
}

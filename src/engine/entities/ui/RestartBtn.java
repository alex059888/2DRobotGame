package engine.entities.ui;

import engine.scenes.GameScene;
import engine.scenes.MenuScene;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class RestartBtn extends Button{
    public RestartBtn(Vector3f pos) {
        super(pos, new Vector2f(0,6), new Vector2f(1,6));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.setCurrentScene(new GameScene());
    }
}

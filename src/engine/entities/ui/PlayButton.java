package engine.entities.ui;

import engine.scenes.GameScene;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class PlayButton extends Button{
    public PlayButton(Vector3f pos) {
        super(pos, new Vector2f(0,0), new Vector2f(1,0));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.setCurrentScene(new GameScene());
    }
}

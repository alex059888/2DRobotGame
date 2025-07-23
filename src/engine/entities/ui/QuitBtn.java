package engine.entities.ui;

import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class QuitBtn extends Button{
    public QuitBtn(Vector3f pos) {
        super(pos,new Vector2f(0,1),new Vector2f(1,1));
    }

    @Override
    protected void doAction() {
        super.doAction();
        Handler.getGame().close();
    }
}

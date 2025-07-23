package engine.entities.ui;

import engine.entities.Entity;
import engine.entities.EntityType;
import engine.util.Handler;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class ScorePanel extends Entity {
    private List<Number> numbers;

    public ScorePanel(Vector3f pos) {
        super(pos, EntityType.UI, new Vector2i(128,64), new Vector2f(3,0), 5);
        numbers = new ArrayList<>();
        do {
            int num = Handler.getScore()%10;
            numbers.add(Number.getNumber(num,new Vector3f(pos).add(new Vector3f(102-20*numbers.size(),-32,0))));
            Handler.setScore(Handler.getScore()/10);
        } while (Handler.getScore() > 0);
    }

    @Override
    public void render() {
        for (Number n : numbers)
            n.render();
        super.render();
    }
}

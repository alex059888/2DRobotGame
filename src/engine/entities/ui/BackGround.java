package engine.entities.ui;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class BackGround extends UIEntity{
    public BackGround(Vector3f pos) {
        super(pos, new Vector2i(1,1), new Vector2f(0,0), 6);
        mesh = genMesh(texPos, new Vector2f(640,360));
    }
}

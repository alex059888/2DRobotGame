package engine.gfx.textures;

import org.joml.Vector2i;

public class TerrainTex extends Texture{
    public TerrainTex(int id) {
        super("./res/textures/Terrain.png",new Vector2i(512), id);
    }
}

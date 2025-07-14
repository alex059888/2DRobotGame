package engine.world;

import engine.gfx.textures.Texture;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private List<Float> vbos;
    private List<Integer> ebos;
    private int index;

    public static float DEFAULT_TILE_SIZE = 64.0f;

    public Tile(Vector2i texPos, int texId, Vector3f pos, int index) {
        vbos = genVbo(texPos, texId, pos);
        ebos = genEbos(index);
        this.index = index;
    }

    private List<Float> genVbo(Vector2i texPos, int texId, Vector3f pos) {
        float[] verts = {
                //x y z r g b a u v
                -(DEFAULT_TILE_SIZE /2)+pos.x,-(DEFAULT_TILE_SIZE /2)+pos.y,pos.z,1,1,1,1,
                Texture.getTexCoord((float) texPos.x,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                Texture.getTexCoord((float) texPos.y+1,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                -(DEFAULT_TILE_SIZE /2)+pos.x, (DEFAULT_TILE_SIZE /2)+pos.y,pos.z,1,1,1,1,
                Texture.getTexCoord((float) texPos.x,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                Texture.getTexCoord((float) texPos.y,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                (DEFAULT_TILE_SIZE /2)+pos.x, (DEFAULT_TILE_SIZE /2)+pos.y,pos.z,1,1,1,1,
                Texture.getTexCoord((float) texPos.x+1,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                Texture.getTexCoord((float) texPos.y,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                (DEFAULT_TILE_SIZE /2)+pos.x,-(DEFAULT_TILE_SIZE /2)+pos.y,pos.z,1,1,1,1,
                Texture.getTexCoord((float) texPos.x+1,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE),
                Texture.getTexCoord((float) texPos.y+1,Texture.getTexture(texId).getTexSize(), (int) DEFAULT_TILE_SIZE)
        };
        List<Float> f = new ArrayList<>();
        for (float c : verts)
            f.add(c);
        return f;
    }

    private List<Integer> genEbos(int index) {
        int[] e = new int[6];
        e[0] = index*4;
        e[1] = index*4+1;
        e[2] = e[3] = index*4+2;
        e[4] = index*4+3;
        e[5] = index*4;
        List<Integer> c = new ArrayList<>();
        for(int i : e)
            c.add(i);
        return c;
    }

    public List<Integer> getEbos() {
        return ebos;
    }

    public List<Float> getVbos() {
        return vbos;
    }

    public int getIndex() {
        return index;
    }
}

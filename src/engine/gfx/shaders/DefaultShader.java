package engine.gfx.shaders;

import org.joml.Matrix4f;

public class DefaultShader extends Shader{
    private final String frag = "DefaultFrag.glsl", vert = "DefaultVert.glsl";

    public DefaultShader() {
        addFragmentShader(loadShader(frag));
        addVertexShader(loadShader(vert));
        compileShader();
    }

    @Override
    public void bindAttributes() {
        addUniform("projection");
        addUniform("transform");
        addUniform("view");
    }

    @Override
    public void setTransform(Matrix4f transform) {
        setUniforms("transform",transform);
    }

    @Override
    public void setProjection(Matrix4f projection) {
        setUniforms("projection", projection);
    }

    @Override
    public void setView(Matrix4f view) {
        setUniforms("view", view);
    }
}

#version 460
#extension GL_ARB_separate_shader_objects : enable

in vec4 fColor;
in vec2 fTexCoords;

out vec4 color;

uniform sampler2D tex;

void main() {
    vec4 texture = texture(tex, fTexCoords);
    vec4 texColor = texture * fColor;
    if(texColor.w == 0)
        discard;
    color = texColor;
}

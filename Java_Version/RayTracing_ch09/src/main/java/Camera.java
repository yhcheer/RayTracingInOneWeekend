public class Camera
{
    public Camera() {
        lower_left = new Vec3(-2.0f, -1.0f, -1.0f);
        horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        vertical = new Vec3(0.0f, 2.0f, 0.0f);
        origin = new Vec3(0.0f, 0.0f, 0.0f);
    }

    public Ray GetRay(float u, float v)
    {
        return new Ray(origin, lower_left.Add(horizontal.Scale(u)).Add(vertical.Scale(v)));
    }

    private Vec3 lower_left;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 origin;
}
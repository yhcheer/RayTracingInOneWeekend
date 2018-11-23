public class Camera
{
    private Vec3 lower_left;    //画布左下角点
    private Vec3 horizontal;    //宽
    private Vec3 vertical;      //高
    private Vec3 origin;        //相机原点

    public Camera() {
        lower_left = new Vec3(-2.0f, -1.0f, -1.0f);
        horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        vertical = new Vec3(0.0f, 2.0f, 0.0f);
        origin = new Vec3(0.0f, 0.0f, 0.0f);
    }

    /**
     *
     * @param u 距离lower_left的横向距离
     * @param v 距离lower_left的纵向距离
     * @return 光线向量
     */
    public Ray GetRay(float u, float v)
    {
        return new Ray(origin, lower_left.Add(horizontal.Scale(u)).Add(vertical.Scale(v)));
    }
}
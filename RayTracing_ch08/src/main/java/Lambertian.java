public class Lambertian extends Material {

    Vec3 albedo;    //材料系数

    public Lambertian() {
    }
    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    /**
     *
     * @param r 光线
     * @param rec 碰撞点
     * @param attenuation 衰减系数
     * @param scattered 反射光线
     * @return 是否
     */
    public boolean scatter(Ray r, HitRecord rec, Wrapper wrapper) {
        Vec3 target = rec.p.Add(rec.normal).Add(randomInUnitSphere());  //相对位置->绝对位置 （p + N） + S
        wrapper.scattered = new Ray(rec.p, target.Subtract(rec.p));     //源点p 方向->ps
        wrapper.attenuation = albedo;
        return true;
    }
}

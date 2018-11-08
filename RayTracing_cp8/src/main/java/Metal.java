public class Metal extends Material{

    Vec3 albedo;    //反射率


    public Metal() {
    }

    public Metal(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray r, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 ref = reflect(r.direction(), rec.normal.normalize());
        scattered = new Ray(rec.p, ref);    //p->ref
        attenuation = albedo;
        return (ref.dot(rec.normal) > 0);
    }

    /**
     * 推导出反射光线向量
     * @param v 入射光线
     * @param n 撞击点的法向量（单位向量）
     * @return 反射光线
     */
    Vec3 reflect(Vec3 v, Vec3 n)
    {
        return v.Subtract(n.Scale(v.dot(n)*2));
        //return v - 2 * dot(v, n)*n;
    }
}

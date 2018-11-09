public class Metal extends Material{

    Vec3 albedo;    //反射率
    float fuzz;     //镜面模糊

    public Metal() {
    }

    public Metal(Vec3 albedo, float f) {
        this.albedo = albedo;
        if(f < 1){
            this.fuzz = f;
        }
        else {
            this.fuzz = 1;
        }
    }

    @Override
    public boolean scatter(Ray r, HitRecord rec, Wrapper wrapper) {
        Vec3 ref = reflect(r.direction(), rec.normal.normalize());
        wrapper.scattered = new Ray(rec.p, ref.Add(randomInUnitSphere().Scale(fuzz)));    //p->ref
        wrapper.attenuation = albedo;
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

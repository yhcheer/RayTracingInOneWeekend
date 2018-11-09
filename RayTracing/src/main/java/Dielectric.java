public class Dielectric extends Material{

    float ref_idx; //ref_idx是指光密介质的折射指数和光疏介质的折射指数的比值

    public Dielectric() {
    }
    public Dielectric(float ref_idx) {
        this.ref_idx = ref_idx;
    }

    @Override
    public boolean scatter(Ray r, HitRecord rec, Wrapper wrapper) {
        Vec3 outward_normal;    //入射时的法向量
        Vec3 reflected = reflect(r.direction(), rec.normal);
        float ni_over_nt; //sin_a2 / sin_a1 折射介质的折射指数和入射介质的入射指数的比值
        float reflect_prob; //反射系数
        float cosine;
        wrapper.attenuation = new Vec3(1,1,1);

        if (r.direction().dot(rec.normal) > 0) {
            // 空气->球 光疏->光密
            // 法向量取个反
            outward_normal = rec.normal.Scale(-1);
            ni_over_nt = ref_idx;
            cosine = r.direction().dot(rec.normal) / ( r.direction().length() * rec.normal.length() );      //入射角余弦
        }
        else {
            // 球->空气
            outward_normal = rec.normal;
            ni_over_nt = 1.0f / ref_idx;
            cosine = -r.direction().dot(rec.normal) / ( r.direction().length() * rec.normal.length() );      //入射角余弦
        }
        if (refract(r.direction(), outward_normal, ni_over_nt, wrapper)) {
            //发生了折射 计算反射系数
            reflect_prob = schlick(cosine, ref_idx);
//            wrapper.scattered = new Ray(rec.p, wrapper.refracted); //若没有考虑全反射 则此处直接输出折射光线
        }
        else {
            //计算折射光线方向向量的函数返回false，即出现全反射。
//            wrapper.scattered = new Ray(rec.p, reflected);
            reflect_prob = 1.0f;
//            return false;
        }
        /*产生一个（0，1）的随机数，如果随机数小于反射系数，则设置为反射光线，反之，设置为折射光线。
            也就是只有反射光线或折射光线中的一个咯，为什么？不是说好反射光线和折射光线都有吗？
            考虑到一个像素点被设置为采样100次，这100次中反射光线的条数基本和reflect_prob的值正相关，
            所以，100次的平均值也就是该像素点出反射光线和折射光线的叠加*/
        if (Math.random() < reflect_prob) {
            wrapper.scattered = new Ray(rec.p, reflected);
        }
        else {
            wrapper.scattered = new Ray(rec.p, wrapper.refracted);
        }
        return true;
    }

    /**
     *
     * @param v 单位入射光线向量
     * @param n 单位法向量
     * @param nt 折射介质/入射介质
     * @param wrapper 包装类 传递折射光线向量
     * @return 是否有折射
     */
    public boolean refract(Vec3 v, Vec3 n, float nt, Wrapper wrapper){
        Vec3 uv = v.normalize();
        float cos_a1 = -1.0f * uv.dot(n);
        float temp = 1.0f - nt*nt*(1.0f-cos_a1*cos_a1);
        if(temp > 0.0f){
            wrapper.refracted = uv.Scale(nt).Add(n.Scale((float)(nt*cos_a1 - Math.sqrt(temp))));
            return true;
        }
        else {
            return false;
        }
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

    /**
     * 反射系数的求解 逼近公式
     * @param cosine 入射角余弦
     * @param ref_idx n2/n1
     * @return 反射系数
     */
    float schlick(float cosine, float ref_idx) {
        float r0 = (1-ref_idx) / (1+ref_idx);
        r0 = r0*r0;
        return (float)(r0 + (1-r0)*Math.pow((1 - cosine),5));
    }
}

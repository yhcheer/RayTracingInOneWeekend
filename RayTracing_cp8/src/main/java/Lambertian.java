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
    public boolean scatter(Ray r, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 target = rec.p.Add(rec.normal).Add(randomInUnitSphere());  //相对位置->绝对位置 （p + N） + S
        scattered = new Ray(rec.p, target.Subtract(rec.p));             //源点p 方向->ps
        attenuation = albedo;
        return true;
    }

    /**
     * 生成一个单位球内的随机坐标
     * @return 单位球内的随机坐标
     */
    public Vec3 randomInUnitSphere(){
        Vec3 p;
        do{
            //随机坐标 区间[-1,+1]
            p =new Vec3((float)(Math.random()), (float)(Math.random()), (float)(Math.random())).Scale(2.0f).Subtract(new Vec3(1.0f, 1.0f, 1.0f));
        }while (p.dot(p) >= 1.0f);  //如果坐标在球内则采用，否则再次生成
        return p;
    }
}

public abstract class Material {
    public abstract boolean scatter(Ray r, HitRecord rec, Vec3 attenuation, Ray scattered);

    public Vec3 randomInUnitSphere(){
        Vec3 p;
        do{
            //随机坐标 区间[-1,+1]
            p =new Vec3((float)(Math.random()), (float)(Math.random()), (float)(Math.random())).Scale(2.0f).Subtract(new Vec3(1.0f, 1.0f, 1.0f));
        }while (p.dot(p) >= 1.0f);  //如果坐标在球内则采用，否则再次生成
        return p;
    }
}

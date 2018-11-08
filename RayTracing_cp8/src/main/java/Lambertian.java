public class Lambertian extends Material {
    Vec3 albedo;

    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    public boolean scatter(Ray r, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 target = rec.p.Add(rec.normal).Add(randomInUnitSphere());  //相对位置->绝对位置 （p + N） + S
        scattered = new Ray(rec.p, target.Subtract(rec.p));             //源点p 方向->ps
        attenuation = this.albedo;
        return true;
    }
}

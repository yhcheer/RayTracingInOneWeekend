public abstract class Material {
    public abstract boolean scatter(Ray r, HitRecord rec, Vec3 attenuation, Ray scattered);

}

public abstract class Hitable
{
    public abstract boolean hit(final Ray r, float t_min, float t_max, HitRecord rec);
}

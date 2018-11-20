public class Wrapper{
    Ray scattered;      //反射光线
    Vec3 attenuation;   //材料系数

    public Wrapper() {
        scattered = new Ray();
        attenuation = new Vec3();
    }
}
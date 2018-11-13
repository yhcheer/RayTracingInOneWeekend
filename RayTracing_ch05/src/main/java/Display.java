import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Display {
    private int width;   //长
    private int height;  //高
    private String title;//标题

    private Vec3 lower_left = new Vec3(-2.0f, -1.0f, -1.0f);
    private Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
    private Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
    private Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);


    Hitable world;



    /**
     * 设置保存路径及图片名
     * @return 要保存的图片名
     */
    static String init(){
        SimpleDateFormat df = new SimpleDateFormat("HH_mm_ss");
        String outputPath = "C:\\Users\\yh\\Documents\\CG\\result\\";
        String pictureName = outputPath + "Chapter5_" + df.format(new Date()) + ".ppm";
        return pictureName;
    }



    public Display() {
        this(200, 100, "Ray Tracer");
    }

    public Display (int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        ConsoleProgress cpb = new ConsoleProgress(0, width*height, 100, '=');
        String pictureName = Display.init();
        System.out.println("pictureName:" + pictureName);

        //多个球体的信息
        List<Hitable> objList = new ArrayList<Hitable>();
        objList.add(new Sphere(new Vec3(0.0f,0.0f,-1.0f), 0.5f));
        objList.add(new Sphere(new Vec3(0.3f,0.0f,-1.0f), 0.3f));
        objList.add(new Sphere(new Vec3(0.0f,-100.5f,-1.0f), 100f));
        world = new HitableList(objList);

        try{
            FileWriter fw = new FileWriter(pictureName);
            fw.write("P3\n" + width + " " + height + "\n255\n");
            int index = 0;
            for(int j = height - 1; j >= 0; j--){
                for(int i = 0; i < width; i++){
                    float u = (float)i/(float)width;
                    float v = (float)j/(float)height;
                    Ray r = new Ray(origin, lower_left.Add(horizontal.Scale(u)).Add(vertical.Scale(v))); //每一条光线
                    Vec3 col = color(r);    //根据每个像素点上色
                    index += 1;
                    int ir = (int)(255.59f*col.x());
                    int ig = (int)(255.59f*col.y());
                    int ib = (int)(255.59f*col.z());
                    fw.write(ir + " " + ig + " " + ib + "\n");
                    if(index % 100 == 0){
                        cpb.show(index);
                    }
                }
            }
            fw.close();
        }catch (Exception e){
            System.out.println("GG!");
        }
    }

    public float hitSphere(final Vec3 center, float radius, final Ray r)
    {
        Vec3 oc = r.origin().Subtract(center);      //oc = A-C
        float a = r.direction().dot(r.direction()); //a = B·B
        float b = 2.0f * oc.dot(r.direction());     //b = 2B·oc
        float c = oc.dot(oc) - radius * radius;     //c = oc^2 - R^2
        float discriminant = b*b - 4*a*c;
        if (discriminant < 0)
        {
            return -1.0f;
        }
        else
        {
            return (-b - (float)Math.sqrt(discriminant)) / (2.0f * a);
        }
    }

    public Vec3 color(Ray r)
    {
        HitRecord rec = new HitRecord();
        if(world.hit(r, 0.0f, Float.MAX_VALUE, rec)){
            //有撞击点，按撞击点法向量代表的颜色绘制
            return new Vec3(rec.normal.x()+1,rec.normal.y()+1,rec.normal.z()+1).Scale(0.5f);
        }
        else{
            //没有撞击点，绘制背景
            Vec3 unit_dir = r.direction().normalize();  //单位方向向量
            float t = 0.5f * (unit_dir.y() + 1.0f);     //原本范围为[-1,1]调整为[0,1]
            return new Vec3(1.0f, 1.0f, 1.0f).Scale(1.0f - t).Add(new Vec3(0.5f, 0.7f, 1.0f).Scale(t));
            //返回背景(1.0-t)*vec3(1.0, 1.0, 1.0) + t*vec3(0.5, 0.7, 1.0); 沿着y轴线性插值，返回的颜色介于白色与天蓝色之间
        }
    }
}

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Display {
    private int width;   //长
    private int height;  //高
    private String title;//标题

    /**
     * 设置保存路径及图片名
     * @return 要保存的图片名
     */
    static String init(){
        SimpleDateFormat df = new SimpleDateFormat("HH_mm_ss");
        String outputPath = "C:\\Users\\yh\\Documents\\CG\\result\\";
        String pictureName = outputPath + "Chapter8_" + df.format(new Date()) + ".ppm";
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
        List<Hitable> objList = new ArrayList<Hitable>(4);
        objList.add(new Sphere(new Vec3(0.0f,0.0f,-1.0f), 0.5f, new Lambertian(new Vec3(0.8f, 0.3f, 0.3f))));
        objList.add(new Sphere(new Vec3(0.0f,-100.5f,-1.0f), 100f, new Lambertian(new Vec3(0.8f, 0.8f, 0.0f))));
        objList.add(new Sphere(new Vec3(1,0,-1), 0.5f, new Metal(new Vec3(0.8f, 0.6f, 0.2f))));
        objList.add(new Sphere(new Vec3(-1,0,-1), 0.5f, new Metal(new Vec3(0.8f, 0.8f, 0.8f))));


        Hitable world = new HitableList(objList);
        Camera camera = new Camera();
        int ns = 100; //采样次数 消锯齿
        try{
            FileWriter fw = new FileWriter(pictureName);
            fw.write("P3\n" + width + " " + height + "\n255\n");
            int index = 0;
            for(int j = height - 1; j >= 0; j--){
                for(int i = 0; i < width; i++){
                    Vec3 col = new Vec3(0,0,0);
                    for(int s = 0; s < ns; s++){
                        float u = (float)(i + Math.random())/(float)width; //添加随机数 消锯齿
                        float v = (float)(j + Math.random())/(float)height;
                        Ray r = camera.GetRay(u, v);
                        col = col.Add(color(r,world, 0));      //根据每个像素点上色 累加
                    }
                    col = col.Scale(1.0f/(float)ns);        //除以采样次数 平均化
                    col = new Vec3((float)Math.sqrt(col.x()), (float)Math.sqrt(col.y()), (float)Math.sqrt(col.z())); //gamma矫正
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
            System.out.println("GG!"+e);
        }
    }


    public Vec3 color(Ray r, Hitable world, int depth)
    {
        HitRecord rec = new HitRecord();
        if(world.hit(r, 0.001f, Float.MAX_VALUE, rec)){
            Ray scattered = new Ray();
            Vec3 attenuation = new Vec3();
            if(depth < 50 && rec.matPtr.scatter(r, rec, attenuation, scattered)){
                return color(scattered, world, depth+1).Multiply(attenuation);
            }else{
                return new Vec3(0,0,0);
            }
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

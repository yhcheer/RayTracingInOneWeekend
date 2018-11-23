import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String pictureName = outputPath + "Chapter1_" + df.format(new Date()) + ".ppm";
        return pictureName;
    }

    public Display() {
        this(200, 100, "Ray Tracer");
    }

    public Display (int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        ConsoleProgress cpb = new ConsoleProgress(0, width*height, 20, '=');
        String pictureName = Display.init();
        System.out.println("pictureName:" + pictureName);

        try{
            FileWriter fw = new FileWriter(pictureName);
            fw.write("P3\n" + width + " " + height + "\n255\n");
            int index = 0;
            for(int j = height - 1; j >= 0; j--){
                for(int i = 0; i < width; i++){
                    float r = (float)i/(float)width;
                    float g = (float)j/(float)height;
                    float b = 0.2f;
                    index += 1;
                    int ir = (int)(255.59f*r);
                    int ig = (int)(255.59f*g);
                    int ib = (int)(255.59f*b);
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
}
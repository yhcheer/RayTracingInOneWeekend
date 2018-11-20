public class RayTracing {

    public static void main(String[] args){
        long startTime=System.currentTimeMillis();   //获取开始时间
        Display TracingDisplay = new Display(200,100,"Ray Tracer"); //1980*1080p
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

}

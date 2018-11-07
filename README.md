# RayTracingInOneWeekend

细读《Ray tracing in one weekend》，想用JAVA写一个光线追踪渲染器，还在施工中。

http://yhcheer.com/2018/10/20/ray-tarcing/

生成的图片为ppm格式，可用下面几行python脚本手动转成jpg格式。

```python
import PythonMagick as PM
img = PM.Image(r"C:\Users\yh\Documents\CG\result\Chapter6_20_39_01.ppm")
img.write(r"C:\Users\yh\Documents\CG\result\Cp6.jpg")
```

各章节对应效果图一览：

Chapter1：

![cp1](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp1.jpg)

Chapter3：

![Cp3](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp3.jpg)

Chapter4：

![CP4](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp4.jpg)

Chapter5-1：

![cp5-1](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp5_1.jpg)

Chapter5-2：

![](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp5_3.jpg)


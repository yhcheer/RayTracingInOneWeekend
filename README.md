# RayTracingInOneWeekend

细读《Ray tracing in one weekend》，想用JAVA写一个光线追踪渲染器，还在施工中。

http://yhcheer.com/2018/10/20/ray-tarcing/

生成的图片为ppm格式，可用下面示例的几行python代码手动转成jpg格式。

```python
import PythonMagick as PM
img = PM.Image(r"C:\Users\yh\Documents\CG\result\Chapter6_20_39_01.ppm")
img.write(r"C:\Users\yh\Documents\CG\result\Cp6.jpg")
```

各章节对应效果图一览：

Chapter1：绘制一张图

![cp1](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp1.jpg)

Chapter3：绘制背景图

![Cp3](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp3.jpg)

Chapter4：光线碰撞一个球体

![CP4](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp4.jpg)

Chapter5-1：光线碰撞球体返回的颜色

![cp5-1](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp5_1.jpg)

Chapter5-2：多个球体共存

![](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp5_3.jpg)

Chapter6：抗锯齿效果

![Cp6](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp6.jpg)

Chapter7：漫反射

![](https://raw.githubusercontent.com/yhcheer/RayTracingInOneWeekend/master/image/Cp7.jpg)

Chapter8：镜面反射（加模糊处理）

![]()


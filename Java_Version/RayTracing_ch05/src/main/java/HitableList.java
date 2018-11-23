import java.util.ArrayList;
import java.util.List;

public class HitableList extends Hitable
{
    List<Hitable> list;

    public HitableList() {
        list = new ArrayList<Hitable>();
    }
    public HitableList(List<Hitable> list) {
        this.list = list;
    }

    /**
     * 判断列表里的任意一个球是否撞击
     * @param r 光线
     * @param t_min 范围
     * @param t_max 范围
     * @param rec 撞击点
     * @return 任意一个球是否撞击
     */
    @Override
    public boolean hit(Ray r, float t_min, float t_max, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        float closestSoFar = t_max;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).hit(r, t_min, closestSoFar, tempRec))
            {
                hitAnything = true;
                closestSoFar = tempRec.t;       //每当有撞击点，tmax就会减为最近的一次撞击点
                rec.t = tempRec.t;
                rec.normal = tempRec.normal;
                rec.p = tempRec.p;
            }
        }
        return hitAnything;
    }

}
import java.util.ArrayList;
import java.util.List;


public class TestGeneric1 {
   public static void main(String args[]) {
	   List<Fruit> list=new ArrayList<Fruit>();
	   list.add(new Apple(true,false,100));
	   list.add(new Fruit(false));
	   System.out.println(list.get(0).getClass().getName());
	   System.out.println(list.get(1).getClass().getName());
	   System.out.println(((Apple)(list.get(0))).weight+" "+list.get(0).getClass().getName());
	   System.out.println(((Apple)(list.get(1))).weight+" "+list.get(1).getClass().getName());
   }
}

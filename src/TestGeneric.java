import java.util.ArrayList;
import java.util.List;


public class TestGeneric {
    public static void main(String args[]) {
    	//List<Fruit> fList=new ArrayList<Fruit>();
    	//fList.add(new Fruit(false));
    	List<Apple> aList=new ArrayList<Apple>();
    	aList.add(new Apple(false,true,19));
    	List<? extends Fruit> list=new ArrayList<Strawberry>();
    	list=aList;
    	Fruit f=(Apple)(list.get(0));
    	System.out.println(f.isMature);
    	System.out.println("###################################");
    	System.out.println(list.get(0).isMature);
        System.out.println(((Apple)(list.get(0))).isMature+"&&&&&&&&&&&&"+((Apple)(list.get(0))).weight);
        
    	System.out.println("**************************************");
    	
    	List<? super Fruit> lists=new ArrayList<Fruit>();
    	lists.add(new Fruit(true));
        lists.add(new Apple(false,true,10));
    	System.out.println(((Fruit)(lists.get(0))).isMature);
    	System.out.println(((Apple)(lists.get(1))).isMature+"%%%%%%%%%%%%%"+((Apple)(lists.get(1))).weight);
    	//list.add(new Apple(true,10));
    	//System.out.println(((Apple)(list.get(0))).weight);
     	//Apple apple1=(Apple)(list.get(0));
     	//System.out.println(apple1.weight);
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@");
    	Apple a=new Apple(true,false,6);
    	System.out.println(((Fruit)a).isMature);
    }
}

class Fruit {
	public boolean isMature;
	
	public Fruit(boolean isMature) {
		this.isMature=isMature;
	}
}

class Apple extends Fruit {
	public boolean isMature;
	public int weight;
	
	public Apple(boolean old,boolean isMature,int weight) {
		super(old);
		this.isMature=isMature;
		this.weight=weight;
	}
}

class Strawberry extends Fruit {
	public boolean isMature;
	public double price;
	
	public Strawberry(boolean old,boolean isMature,double price) {
		super(old);
		this.isMature=isMature;
		this.price=price;
	}
}
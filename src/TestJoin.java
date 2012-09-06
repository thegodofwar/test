class A implements Runnable {
  String name; // name of thread

  Thread t;

  A(String threadname) {
    name = threadname;
    t = new Thread(this, name);
    System.out.println("New thread: " + t);
    t.start();
  }

  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println(name + ": " + i);
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      System.out.println(name + " interrupted.");
    }
    System.out.println(name + " exiting.");
  }
}

public class TestJoin {
  public static void main(String args[]) {
    A ob1 = new A("One");
    A ob2 = new A("Two");
    A ob3 = new A("Three");

    System.out.println("Thread One is alive: " + ob1.t.isAlive());
    System.out.println("Thread Two is alive: " + ob2.t.isAlive());
    System.out.println("Thread Three is alive: " + ob3.t.isAlive());

    try {
      System.out.println("Waiting for threads to finish.");
      ob1.t.join(200);
      ob2.t.join();
      ob3.t.join(50);
    } catch (Exception e) {
      System.out.println("Main thread Interrupted");
    }

    System.out.println("Thread One is alive: " + ob1.t.isAlive());
    System.out.println("Thread Two is alive: " + ob2.t.isAlive());
    System.out.println("Thread Three is alive: " + ob3.t.isAlive());

    System.out.println("Main thread exiting.");
  }
}
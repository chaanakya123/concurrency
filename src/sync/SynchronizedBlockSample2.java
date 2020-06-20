package sync;

public class SynchronizedBlockSample2 {
    static class Display {
        private static void loop() {
            for (int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized static void loop1(){
            System.out.println("loop 1 : " + Thread.currentThread().getName());
            loop();
        }

        public static void loop2(){
            System.out.println("loop 2 : " + Thread.currentThread().getName());
            loop();
        }
    }

    static class MyRunnable1 implements Runnable {
        Display d;

        public MyRunnable1(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop1();
        }
    }

    static class MyRunnable2 implements Runnable {
        Display d;

        public MyRunnable2(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (Display.class) {
                d.loop2();
            }
        }
    }

    static class MyRunnable3 implements Runnable {
        Display d;

        public MyRunnable3(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (Display.class) {
                d.loop1();
            }
        }
    }

    static class MyRunnable4 implements Runnable {
        Display d;

        public MyRunnable4(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (Display.class) {
                d.loop2();
            }
        }
    }

  /*
        -> When an object lock is held, only synchronized methods cannot be executed.
        Other methods of that object can be executed in parallel.

        -> When a class level lock is held, only static synchronized methods, or static methods with synchronized(Class) blocks,
        cannot be executed. Other methods of that object can be executed in parallel.

        -> When mulriple objects are involved, synchronization of normal methods make no sense.
           But synchronization of static methods have to be taken care of

   */

    public static void main(String[] args) throws InterruptedException {
        Display d1 = new Display();
        Display d2 = new Display();

        // main thread

        // synchronized method
        Thread child1 = new Thread(new MyRunnable1(d1), "Child 1");
        Thread child2 = new Thread(new MyRunnable1(d2), "Child 2");

        // synchronized (this)
        Thread child3 = new Thread(new MyRunnable2(d1), "Child 3");
        Thread child4 = new Thread(new MyRunnable2(d2), "Child 4");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
    }
}

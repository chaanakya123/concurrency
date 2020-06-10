package sync;

public class SynchronizedStaticMethod4 {
    static class Display {
        public synchronized static void loop1(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 1 ends");
        }

        public synchronized static void loop2(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 2 ends");
        }

        public static void loop3(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 3 ends");
        }

        public synchronized void loop4(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 4 ends");
        }

        public void loop5(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 5 ends");
        }
    }

    static class MyRunnable1 implements Runnable {
        Display d;

        public MyRunnable1(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop1();
        }
    }

    static class MyRunnable2 implements Runnable {
        Display d;

        public MyRunnable2(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop2();
        }
    }

    static class MyRunnable3 implements Runnable {
        Display d;

        public MyRunnable3(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop3();
        }
    }

    static class MyRunnable4 implements Runnable {
        Display d;

        public MyRunnable4(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop4();
        }
    }

    static class MyRunnable5 implements Runnable {
        Display d;

        public MyRunnable5(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop5();
        }
    }

    /*
        If synchronized is used with a static method, then the current thread will acquire
         a class level lock. So no other thread can run any other synchronized static methods until the lock is released.

         But other static methods, normal synchronized methods, normal methods can be executed by other threads. Here child3 is running loop3 static method in parallel
         with child1/child2 . But child1 or child2 cannot run in parallel, as they are trying to run
         synchronized static methods. Only the thread which acquires the class level lock can run the static
         synchronized methods
     */
    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();

        // main thread
        Thread child1 = new Thread(new MyRunnable1(d), "Child 1");
        Thread child2 = new Thread(new MyRunnable2(d), "Child 2");
        Thread child3 = new Thread(new MyRunnable3(d), "Child 3");
        Thread child4 = new Thread(new MyRunnable4(d), "Child 4");
        Thread child5 = new Thread(new MyRunnable5(d), "Child 5");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
        child5.start();
    }
}

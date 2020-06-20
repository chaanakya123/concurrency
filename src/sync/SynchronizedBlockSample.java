package sync;

public class SynchronizedBlockSample {
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

        public synchronized void loop1(){
            System.out.println("loop 1 : " + Thread.currentThread().getName());
            loop();
        }

        public synchronized void loop2(){
            System.out.println("loop 2 : " + Thread.currentThread().getName());
            loop();
        }

        public void loop3(){
            synchronized (this){
                System.out.println("loop 3 : " + Thread.currentThread().getName());
                loop();
            }
        }

        public void loop4(){
            synchronized (this){
                System.out.println("loop 4 : " + Thread.currentThread().getName());
                loop();
            }
        }

        public void loop5(){
            System.out.println("loop 5 : " + Thread.currentThread().getName());
            loop();
        }

        public void loop6(){
            System.out.println("loop 6 : " + Thread.currentThread().getName());
            loop();
        }

        public synchronized static void loop7(){
            System.out.println("loop 7 : " + Thread.currentThread().getName());
            loop();
        }

        public synchronized static void loop8(){
            System.out.println("loop 8 : " + Thread.currentThread().getName());
            loop();
        }

        public static void loop9(){
            System.out.println("loop 9 : " + Thread.currentThread().getName());
            loop();
        }

        public static void loop10(){
            System.out.println("loop 10 : " + Thread.currentThread().getName());
            loop();
        }

        public void loop11(){
            System.out.println("loop 11 : " + Thread.currentThread().getName());
            loop();
        }

        public void loop12(){
            System.out.println("loop 12 : " + Thread.currentThread().getName());
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
            d.loop2();
        }
    }

    static class MyRunnable3 implements Runnable {
        Display d;

        public MyRunnable3(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop3();
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

    static class MyRunnable6 implements Runnable {
        Display d;

        public MyRunnable6(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop6();
        }
    }

    static class MyRunnable11 implements Runnable {
        Display d;

        public MyRunnable11(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (d) {
                d.loop11();
            }
        }
    }

    static class MyRunnable12 implements Runnable {
        Display d;

        public MyRunnable12(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (d) {
                d.loop12();
            }
        }
    }

    static class MyRunnable7 implements Runnable {
        Display d;

        public MyRunnable7(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop7();
        }
    }

    static class MyRunnable8 implements Runnable {
        Display d;

        public MyRunnable8(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop8();
        }
    }

    static class MyRunnable9 implements Runnable {
        Display d;

        public MyRunnable9(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (Display.class) {
                Display.loop9();
            }
        }
    }

    static class MyRunnable10 implements Runnable {
        Display d;

        public MyRunnable10(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            synchronized (Display.class) {
                Display.loop10();
            }
        }
    }

  /*
     -> child 1,2,3,4,11,12 cannot run in parallel, because when one thread holds the object lock on d, others
        cannot execute it
        When an object lock is held, only synchronized methods cannot be executed.
        Other methods of that object can be executed in parallel.

     -> child 7,8,9,10 cannot run in parallel, because when one thread holds the class level lock on d, others
        cannot execute it.
        When a class level lock is held, only static synchronized methods, or static methods with synchronized(Class) blocks,
        cannot be executed. Other methods of that object can be executed in parallel.

   */

    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();

        // main thread

        // synchronized method
        Thread child1 = new Thread(new MyRunnable1(d), "Child 1");
        Thread child2 = new Thread(new MyRunnable2(d), "Child 2");

        // synchronized (this)
        Thread child3 = new Thread(new MyRunnable3(d), "Child 3");
        Thread child4 = new Thread(new MyRunnable4(d), "Child 4");

        // normal
        Thread child5 = new Thread(new MyRunnable5(d), "Child 5");
        Thread child6 = new Thread(new MyRunnable6(d), "Child 6");

        // static synchronized method
        Thread child7 = new Thread(new MyRunnable7(d), "Child 7");
        Thread child8 = new Thread(new MyRunnable8(d), "Child 8");

        // synchronized(Display.class)
        Thread child9 = new Thread(new MyRunnable9(d), "Child 9");
        Thread child10 = new Thread(new MyRunnable10(d), "Child 10");

        // synchronized(d)
        Thread child11 = new Thread(new MyRunnable11(d), "Child 11");
        Thread child12 = new Thread(new MyRunnable12(d), "Child 12");


        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
        child11.start();
        child12.start();

        child5.start();
        child6.start();

        child7.start();
        child8.start();
        child9.start();
        child10.start();

    }
}

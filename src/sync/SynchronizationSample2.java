package sync;

public class SynchronizationSample2 {
    static class Display {
        public synchronized void loop1(){
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void loop2(){
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void loop3(){
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyRunnable1 implements Runnable {
        Display d;

        public MyRunnable1() {
            super();
        }

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

        public MyRunnable2() {
            super();
        }

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

        public MyRunnable3() {
            super();
        }

        public MyRunnable3(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop3();
        }
    }

    /*
        When a thread tries to execute a synchronized method, it acquires object level lock on that object.
        So no other thread can execute any other synchronized methods on that object. They can however execute normal methods
     */
    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();

        // main thread
        // So here one of child1 or child2, child3 can run in parallel.
        // But child1, child2 cannot run in parallel
        Thread child1 = new Thread(new MyRunnable1(d), "Child 1");
        Thread child2 = new Thread(new MyRunnable2(d), "Child 2");
        Thread child3 = new Thread(new MyRunnable3(d), "Child 3");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
    }
}

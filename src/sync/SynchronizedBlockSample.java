package sync;

public class SynchronizedBlockSample {
    static class Display {
        public void loop(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            synchronized (this){
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
    }

    static class MyRunnable implements Runnable {
        Display d;

        public MyRunnable() {
            super();
        }

        public MyRunnable(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();

        // main thread
        Thread child1 = new Thread(new MyRunnable(d), "Child 1");
        Thread child2 = new Thread(new MyRunnable(d), "Child 2");
        Thread child3 = new Thread(new MyRunnable(d), "Child 3");
        Thread child4 = new Thread(new MyRunnable(d), "Child 4");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
    }
}

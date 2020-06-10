package sync;


public class NoSynchronizationOnDifferentObjects {
    static class Display {
        public synchronized void loop(){
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
        // main thread

        Display d1 = new Display();
        Display d2 = new Display();
        Display d3 = new Display();
        Display d4 = new Display();

        Thread child1 = new Thread(new MyRunnable(d1), "Child 1");
        Thread child2 = new Thread(new MyRunnable(d2), "Child 2");
        Thread child3 = new Thread(new MyRunnable(d3), "Child 3");
        Thread child4 = new Thread(new MyRunnable(d4), "Child 4");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
    }
}

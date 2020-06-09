package basic;

public class SampleThread {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    public static void main(String[] args) {
        // main thread
        Thread child1 = new Thread(new MyRunnable());
        child1.setName("Child 1");

        Thread child2 = new Thread(new MyRunnable());
        child2.setName("Child 2");

        // will start new threads
        child1.start();
        child2.start();

        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

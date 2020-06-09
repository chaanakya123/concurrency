package deadlock;

public class DeadLockSample {
    static class MyRunnable implements Runnable {
        public static Thread mt;

        @Override
        public void run() {
            try {
                mt.join();
                for (int i = 0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread child = new Thread(new MyRunnable());
        MyRunnable.mt = Thread.currentThread();
        child.start();

        try {
            child.join();
            for (int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

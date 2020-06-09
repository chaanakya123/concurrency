package deadlock;

public class WaitOnItself {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Child 1 complete");
        }
    }

    static class MyRunnable1 implements Runnable {
        @Override
        public void run() {
            // waiting on the same thread. results in a deadlock
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    /**
     * If thread t1 has to wait until t2 completes, then t2.join() has to be called inside t1.
     * 1. join()
     * 2. join(ms)
     * 3. join(ms,ns)
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // main thread
        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable1(), "Child 2");

        /**
         * child 1 and main will complete its execution, but child 2 will keep waiting
         * for itself to complete
         */
        child1.start();
        child2.start();
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            Thread.sleep(100);
        }

        System.out.println("main complete");
    }
}

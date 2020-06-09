public class YieldSample {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                Thread.yield();
            }
        }
    }

    /**
     * yield gives a hint to JVM that it can execute an other thread. It depends on JVM
     * whether to do it or not.
     * The current thread will then be moved back to ready queue, and again waits for its chance
     * @param args
     */
    public static void main(String[] args) {
        // main thread
        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable(), "Child 2");

        // will start new threads
        child1.start();
        child2.start();
    }
}

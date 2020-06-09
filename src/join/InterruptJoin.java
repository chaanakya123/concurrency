package join;

public class InterruptJoin {
    static class MyRunnable implements Runnable {
        public static Thread mt;
        @Override
        public void run() {
            try{
                mt.join();
                for (int i = 0; i < 10; i++){
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(200);
                }
            }
            catch (InterruptedException ie){
                System.out.println(String.format("I (%s) am interrupted", Thread.currentThread().getName()));
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
        MyRunnable.mt = Thread.currentThread();

        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable(), "Child 2");

        // will start new threads
        child1.start();
        child2.start();

        child1.interrupt();
        child2.interrupt();

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }

        System.out.println("main complete");
    }
}

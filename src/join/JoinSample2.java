package join;

public class JoinSample2 {
    static class MyRunnable implements Runnable {
        private Thread mt;

        MyRunnable() {
            super();
        }

        MyRunnable(Thread parent) {
            super();
            this.mt = parent;
        }

        @Override
        public void run() {
            try{
                mt.join();
                for (int i = 0; i < 10; i++){
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException ie){
                System.out.println(String.format("I (%s) am interrupted", Thread.currentThread().getName()));
            }
            System.out.println(Thread.currentThread().getName() + " is complete");
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
        Thread mainThread = Thread.currentThread();

        Thread child3 = new Thread(new MyRunnable(mainThread), "Child 3");
        Thread child2 = new Thread(new MyRunnable(child3), "Child 2");
        Thread child1 = new Thread(new MyRunnable(child2), "Child 1");

        //mainThread.

        // will start new threads
        child1.start();
        child2.start();
        child3.start();

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            Thread.sleep(100);
        }

        System.out.println("main complete");
    }
}

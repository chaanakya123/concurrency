package join;

public class ChildCallingJoinOnParent {
    static class MyRunnable implements Runnable {
        public static Thread mt;
        @Override
        public void run() {
            // wait for parent thread to finish
            try {
                mt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

        // assign main thread to the static variable in MyRunnable
        MyRunnable.mt = Thread.currentThread();

        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable(), "Child 2");

        // will start new threads
        child1.start();
        child2.start();

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            Thread.sleep(200);
        }
        System.out.println("main complete");
    }
}

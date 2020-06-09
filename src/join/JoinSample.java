package join;

public class JoinSample {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
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
        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable(), "Child 2");
        Thread child3 = new Thread(new MyRunnable(), "Child 3");
        Thread child4 = new Thread(new MyRunnable(), "Child 4");

        // will start new threads
        child1.start();
        child2.start();

        // waits indefinitely for the completion of child1, child2
        child1.join();
        child2.join();

        // execution would be here only after completion of child1, child2
        final long startTime = System.currentTimeMillis();

       // start child 3 and child 4
        child3.start();
        child4.start();

        // wait 1 sec for child 3 to complete (Child 3 might or might not complete its execution. But we only wait for 1sec)
        child3.join(500);
        // wait 1 sec for child 4 to complete (Child 4 might or might not complete its execution. But we only wait for 1sec)
        child4.join(500);

        // execution would reach here only after waiting 500ms each for child3 or child 4.
        // So it will definitely take 500ms + 500ms to reach here after calling join

        final long endTime = System.currentTimeMillis();
        // the difference should denitely be >= 1000, as we are waiting 500ms for child3
        // and 500ms for child4
        System.out.println("time the main thread waited to start executing the loop: " + (endTime - startTime));

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }

        System.out.println("main complete");
    }
}

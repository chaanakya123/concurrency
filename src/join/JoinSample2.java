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

        // child 3 will wait for main thread to complete
        Thread child3 = new Thread(new MyRunnable(mainThread), "Child 3");

        // child 2 will wait for child 3 to complete
        Thread child2 = new Thread(new MyRunnable(child3), "Child 2");

        // child 2 will wait for child 1 to complete
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
/*
main : 0
main : 1
main : 2
main : 3
main : 4
main : 5
main : 6
main : 7
main : 8
main : 9
main complete
Child 3 : 0
Child 3 : 1
Child 3 : 2
Child 3 : 3
Child 3 : 4
Child 3 : 5
Child 3 : 6
Child 3 : 7
Child 3 : 8
Child 3 : 9
Child 3 is complete
Child 2 : 0
Child 2 : 1
Child 2 : 2
Child 2 : 3
Child 2 : 4
Child 2 : 5
Child 2 : 6
Child 2 : 7
Child 2 : 8
Child 2 : 9
Child 2 is complete
Child 1 : 0
Child 1 : 1
Child 1 : 2
Child 1 : 3
Child 1 : 4
Child 1 : 5
Child 1 : 6
Child 1 : 7
Child 1 : 8
Child 1 : 9
Child 1 is complete

 */
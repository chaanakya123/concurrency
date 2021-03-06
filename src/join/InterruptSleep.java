package join;

public class InterruptSleep {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try{
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
        Thread child1 = new Thread(new MyRunnable(), "Child 1");
        Thread child2 = new Thread(new MyRunnable(), "Child 2");
        Thread child3 = new Thread(new MyRunnable(), "Child 3");
        Thread child4 = new Thread(new MyRunnable(), "Child 4");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();

        child1.interrupt();
        child2.interrupt();
        child3.interrupt();
        child4.interrupt();

        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }

        System.out.println("main complete");
    }
}
/*
Child 2 : 0
Child 4 : 0
Child 1 : 0
main : 0
Child 3 : 0
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
I (Child 3) am interrupted
I (Child 2) am interrupted
I (Child 4) am interrupted
I (Child 1) am interrupted
 */
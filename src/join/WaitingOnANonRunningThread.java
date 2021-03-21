package join;

public class WaitingOnANonRunningThread {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try{
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

    public static void main(String[] args) throws InterruptedException {
        // main thread
        Thread mainThread = Thread.currentThread();
        Thread child = new Thread(new MyRunnable(), "Child 1");

        // main thread has to wait until child completes.
        /*
            Join keeps checking if the thread is alive. And if it is alive, will keep waiting
            for it to complete. Here since the thread is not even started, the thread is not alive.
            So the is wasted, and the execution continues
         */
        child.join();

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
 */
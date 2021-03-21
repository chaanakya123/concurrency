package basic;

public class SampleThreadCallingRun {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    public static void main(String[] args) {
        // main thread
        Thread child1 = new Thread(new MyRunnable());
        child1.setName("Child 1");

        Thread child2 = new Thread(new MyRunnable());
        child2.setName("Child 2");

        // calling run is just like ny other method call. It will not initialize new threads
        child1.run();
        child2.run();

        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
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
 */

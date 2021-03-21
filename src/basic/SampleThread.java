package basic;

public class SampleThread {
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

        // will start new threads
        child1.start();
        child2.start();

        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

/* Output

Child 1 : 0
Child 2 : 0
main : 0
Child 2 : 1
Child 1 : 1
Child 2 : 2
main : 1
Child 2 : 3
Child 1 : 2
Child 2 : 4
main : 2
Child 2 : 5
Child 1 : 3
Child 2 : 6
main : 3
Child 2 : 7
Child 1 : 4
Child 2 : 8
main : 4
Child 2 : 9
Child 1 : 5
main : 5
Child 1 : 6
main : 6
main : 7
main : 8
main : 9
Child 1 : 7
Child 1 : 8
Child 1 : 9

 */

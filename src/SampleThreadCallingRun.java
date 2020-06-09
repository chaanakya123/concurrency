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

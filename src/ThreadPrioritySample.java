public class ThreadPrioritySample {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    /**
     * priority range : (1(MIN_PRIORITY) to 10(MAX_PRIORITY))
     * Thread with high priority have a higher chance of getting executed first
     *
     * Note : Some OS might not provide proper support for thread priority
     * @param args
     */
    public static void main(String[] args) {
        Thread child1 = new Thread(new MyRunnable());
        child1.setName("Child 1");
        child1.setPriority(Thread.MAX_PRIORITY);

        Thread child2 = new Thread(new MyRunnable());
        child2.setName("Child 2");
        child2.setPriority(Thread.MIN_PRIORITY);

        child1.start();
        child2.start();

        // main thread
        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

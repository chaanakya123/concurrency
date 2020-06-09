public class ThreadPriorityInheritance {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // priority of this would be inherited from parent thread (child 2). So default priority is 6
            Thread t1 = new Thread(() -> {}, Thread.currentThread().getName() + " -> child 1");
            System.out.println(t1.getName() + " : " + t1.getPriority());

            // priority of this would be inherited from parent thread (child 2). So default priority is 6
            Thread t2 = new Thread(() -> {
                // create a new thread
                // default priorty would be 7
                Thread t21 = new Thread(() -> {},Thread.currentThread().getName() + " -> child 1");
                System.out.println(t21.getName() + " : " + t21.getPriority());

                // create a new thread
                // default priorty would be 7
                Thread t22 = new Thread(() -> {
                    // create a new thread
                    // default priority would be 8 (from parent)
                    Thread t221 = new Thread(() -> {},Thread.currentThread().getName() + " -> child 1");
                    System.out.println(t221.getName()+ " : " + t221.getPriority());

                },Thread.currentThread().getName() + " -> child 2");
                t22.setPriority(8);
                System.out.println(t22.getName() + " : " + t22.getPriority());
                t22.start();

            }, Thread.currentThread().getName() + " -> child 2");
            t2.setPriority(7);
            System.out.println(t2.getName() + " : " + t2.getPriority());

            t2.start();
        }
    }

    /**
     * priority range : (1(MIN_PRIORITY) to 10(MAX_PRIORITY))
     * Default priority of main thread is 5. For the child threads, the default priority would be inherited
     * from its direct parent thread. It can be changed later on using setPriority method
     *
     * Note :  Priority is also for thread, not for runnable objects
     * @param args
     */
    public static void main(String[] args) {
        // default priority of main thread is 5
        System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getPriority());
        // priority is inherited from main thread, so its 5
        Thread child1 = new Thread(new MyRunnable(), Thread.currentThread().getName() + "-> child 1");
        System.out.println(child1.getName() + " : " + child1.getPriority());

        Thread child2 = new Thread(new MyRunnable(), Thread.currentThread().getName() + "-> child 2");
        child2.setPriority(6);
        System.out.println(child2.getName() + " : " + child2.getPriority());
        child2.start();
    }
}

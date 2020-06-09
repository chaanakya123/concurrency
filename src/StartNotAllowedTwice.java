public class StartNotAllowedTwice {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread child1 = new Thread(new MyRunnable());
        child1.setName("Child 1");

        child1.start();

        // we will get a Run time exception saying IllegelThreadStateException
        child1.start();
    }
}

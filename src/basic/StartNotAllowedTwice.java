package basic;

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

/*
Exception in thread "main" java.lang.IllegalThreadStateException
	at java.base/java.lang.Thread.start(Thread.java:792)
	at basic.StartNotAllowedTwice.main(StartNotAllowedTwice.java:20)
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
 */
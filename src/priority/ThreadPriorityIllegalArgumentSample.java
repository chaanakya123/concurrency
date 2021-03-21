package priority;

public class ThreadPriorityIllegalArgumentSample {
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    /**
     * priority range : [1,10]
     * if priority is out of this range, there will be a run time 'IllegalArgumentException'
     * @param args
     */
    public static void main(String[] args) {
        Thread child = new Thread(new MyRunnable(), "Child");

        // main thread
        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        // Exception in thread "main" java.lang.IllegalArgumentException
        //child.setPriority(15);
        //child.setPriority(0);
        child.setPriority(-1);
        child.start();
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
Exception in thread "main" java.lang.IllegalArgumentException
	at java.base/java.lang.Thread.setPriority(Thread.java:1137)
	at priority.ThreadPriorityIllegalArgumentSample.main(ThreadPriorityIllegalArgumentSample.java:28)
 */
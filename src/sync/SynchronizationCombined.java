package sync;

public class SynchronizationCombined {
    static class Display {
        public synchronized static void loop1(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 1 ends");
        }

        public synchronized static void loop2(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 2 ends");
        }

        public static void loop3(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 3 ends");
        }

        public synchronized void loop4(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 4 ends");
        }
        public synchronized void loop5(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 5 ends");
        }

        public void loop6(){
            System.out.println("current Thread : " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop 6 ends");
        }
    }

    static class MyRunnable1 implements Runnable {
        Display d;

        public MyRunnable1(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop1();
        }
    }

    static class MyRunnable2 implements Runnable {
        Display d;

        public MyRunnable2(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop2();
        }
    }

    static class MyRunnable3 implements Runnable {
        Display d;

        public MyRunnable3(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            Display.loop3();
        }
    }

    static class MyRunnable4 implements Runnable {
        Display d;

        public MyRunnable4(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop4();
        }
    }

    static class MyRunnable5 implements Runnable {
        Display d;

        public MyRunnable5(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop5();
        }
    }

    static class MyRunnable6 implements Runnable {
        Display d;

        public MyRunnable6(Display d){
            this.d = d;
        }

        @Override
        public void run() {
            d.loop6();
        }
    }

    /*
        If synchronized is used with a static method, then the current thread will acquire
         a class level lock. So no other thread can run any other synchronized static methods until the lock is released.

         But other static methods, normal synchronized methods, normal methods can be executed by other threads. Here child3 is running loop3 static method in parallel
         with child1/child2 . But child1 or child2 cannot run in parallel, as they are trying to run
         synchronized static methods. Only the thread which acquires the class level lock can run the static
         synchronized methods
     */
    public static void main(String[] args) throws InterruptedException {
        // main thread
        Display d = new Display();

        // here threads [1,3,4,6], [1,3,5,6] [2,3,4,6], [2,3,5,6] can run in parallel respectively.
        // But [1,2], [4,5] cannot run in parallel respectively

        /*
            When child1 tries to execute a static synchronized method, it acquires a class level lock.
            So now until the lock is released by child1, child2 cant execute its synchronized static method.
            But simultaneously child3, child 4 or child5, child6 can execute alongside child1

            Now again, when child4 tries to execute its synchronized method, it acquires a object level lock
            on Display object d. So child 5 cannot execute any synchronized methods until the lock is released
         */
        // synchronized static
        Thread child1 = new Thread(new MyRunnable1(d), "Child 1");
        Thread child2 = new Thread(new MyRunnable2(d), "Child 2");

        // static
        Thread child3 = new Thread(new MyRunnable3(d), "Child 3");

        // synchronized
        Thread child4 = new Thread(new MyRunnable4(d), "Child 4");
        Thread child5 = new Thread(new MyRunnable5(d), "Child 5");

        //normal
        Thread child6 = new Thread(new MyRunnable6(d), "Child 6");

        // will start new threads
        child1.start();
        child2.start();
        child3.start();
        child4.start();
        child5.start();
        child6.start();

        System.out.println("main ends");
    }
}

/*
main ends
current Thread : Child 4
current Thread : Child 3
current Thread : Child 6
current Thread : Child 1
Child 1 : 0
Child 3 : 0
Child 4 : 0
Child 6 : 0
Child 6 : 1
Child 3 : 1
Child 1 : 1
Child 4 : 1
Child 6 : 2
Child 4 : 2
Child 3 : 2
Child 1 : 2
Child 4 : 3
Child 3 : 3
Child 1 : 3
Child 6 : 3
Child 4 : 4
Child 3 : 4
Child 6 : 4
Child 1 : 4
Child 4 : 5
Child 6 : 5
Child 1 : 5
Child 3 : 5
Child 4 : 6
Child 3 : 6
Child 6 : 6
Child 1 : 6
Child 3 : 7
Child 1 : 7
Child 4 : 7
Child 6 : 7
Child 3 : 8
Child 1 : 8
Child 4 : 8
Child 6 : 8
Child 4 : 9
Child 6 : 9
Child 1 : 9
Child 3 : 9
loop 1 ends
loop 4 ends
loop 6 ends
loop 3 ends
current Thread : Child 2
current Thread : Child 5
Child 2 : 0
Child 5 : 0
Child 2 : 1
Child 5 : 1
Child 5 : 2
Child 2 : 2
Child 2 : 3
Child 5 : 3
Child 2 : 4
Child 5 : 4
Child 2 : 5
Child 5 : 5
Child 5 : 6
Child 2 : 6
Child 2 : 7
Child 5 : 7
Child 2 : 8
Child 5 : 8
Child 2 : 9
Child 5 : 9
loop 5 ends
loop 2 ends
 */

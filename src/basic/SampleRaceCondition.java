package basic;

import java.util.concurrent.atomic.AtomicInteger;

public class SampleRaceCondition {
	int counter = 0;
	volatile int volatileCounter = 0;
	AtomicInteger atomicCounter = new AtomicInteger(0);

	class MyThread1 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("[Thread 1] incrementing counter to " + (++counter));
				System.out.println("[Thread 1] incrementing volatile counter to " + (++volatileCounter));
				System.out.println("[Thread 1] incrementing atomic counter to " + (atomicCounter.incrementAndGet()));
			}
		}
	}

	class MyThread2 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("[Thread 2] incrementing counter to " + (++counter));
				System.out.println("[Thread 2] incrementing volatile counter to " + (++volatileCounter));
				System.out.println("[Thread 2] incrementing atomic counter to " + (atomicCounter.incrementAndGet()));
			}
		}
	}

	public void test() {
		new Thread(new MyThread1()).start();
		new Thread(new MyThread2()).start();
	}

	public static void main(String[] args) {
		new SampleRaceCondition().test();
	}
}

/*
[Thread 2] incrementing counter to 2
[Thread 1] incrementing counter to 1
[Thread 2] incrementing volatile counter to 1
[Thread 1] incrementing volatile counter to 2
[Thread 2] incrementing atomic counter to 1
[Thread 1] incrementing atomic counter to 2
[Thread 2] incrementing counter to 3
[Thread 1] incrementing counter to 4
[Thread 2] incrementing volatile counter to 3
[Thread 1] incrementing volatile counter to 4
[Thread 2] incrementing atomic counter to 3
[Thread 1] incrementing atomic counter to 4
[Thread 2] incrementing counter to 5
[Thread 1] incrementing counter to 6
[Thread 2] incrementing volatile counter to 5
[Thread 1] incrementing volatile counter to 6
[Thread 2] incrementing atomic counter to 5
[Thread 1] incrementing atomic counter to 6
[Thread 2] incrementing counter to 7
[Thread 1] incrementing counter to 8
[Thread 1] incrementing volatile counter to 8
[Thread 2] incrementing volatile counter to 7
[Thread 1] incrementing atomic counter to 7
[Thread 2] incrementing atomic counter to 8
[Thread 1] incrementing counter to 9
[Thread 2] incrementing counter to 10
[Thread 1] incrementing volatile counter to 9
[Thread 2] incrementing volatile counter to 10
[Thread 1] incrementing atomic counter to 9
[Thread 2] incrementing atomic counter to 10
[Thread 1] incrementing counter to 11
[Thread 2] incrementing counter to 12
[Thread 1] incrementing volatile counter to 11
[Thread 2] incrementing volatile counter to 12
[Thread 1] incrementing atomic counter to 11
[Thread 2] incrementing atomic counter to 12
[Thread 1] incrementing counter to 13
[Thread 2] incrementing counter to 14
[Thread 1] incrementing volatile counter to 13
[Thread 2] incrementing volatile counter to 14
[Thread 2] incrementing atomic counter to 14
[Thread 1] incrementing atomic counter to 13
[Thread 2] incrementing counter to 15
[Thread 2] incrementing volatile counter to 15
[Thread 1] incrementing counter to 16
[Thread 2] incrementing atomic counter to 15
[Thread 1] incrementing volatile counter to 16
[Thread 2] incrementing counter to 17
[Thread 1] incrementing atomic counter to 16
[Thread 2] incrementing volatile counter to 17
[Thread 1] incrementing counter to 18
[Thread 2] incrementing atomic counter to 17
[Thread 1] incrementing volatile counter to 18
[Thread 2] incrementing counter to 19
[Thread 1] incrementing atomic counter to 18
[Thread 1] incrementing counter to 20
[Thread 2] incrementing volatile counter to 19
[Thread 1] incrementing volatile counter to 20
[Thread 2] incrementing atomic counter to 19
[Thread 1] incrementing atomic counter to 20

 */
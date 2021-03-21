package basic;

public class SingletonRaceCondition {
	static class Singleton {
		private static Singleton fSingleton;
		private Singleton() {

		}

		// check and act race condition
		public static Singleton get() {
			if(fSingleton == null) {
				fSingleton = new Singleton();
				System.out.println("Singleton initialized by " + Thread.currentThread().getName());
			}
			return fSingleton;
		}
	}

	// lazy loading
	static class ThreadSafeSingleton {
		private static ThreadSafeSingleton fSingleton;
		private ThreadSafeSingleton() {

		}

		public static ThreadSafeSingleton get() {
			if(fSingleton == null) { // say thread 1, 2 enter at the same time
				synchronized (ThreadSafeSingleton.class) { // only thread 1 or 2 gets the lock
					/*
					 inside the synchronized block, we need to check for null again.
					 Say thread 1,2 enters the first null check at the same time. Say Thread 1 got the lock
					 Thread 1 initializes the singleton and releases the lock.
					 Because Thread 2 already is inside the first if, it will fetch the lock, gets inside the synchronized block
					 and initializes Singleton again.

					 So we need to add null check again inside the singleton, so that Thread 2 will not initialize again.
					 */
					if(fSingleton == null) {
						fSingleton = new ThreadSafeSingleton();
						System.out.println("Thread safe singleton initialized by " + Thread.currentThread().getName());
					}
				}
			}
			return fSingleton;
		}
	}

	class MyThread1 implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}
			Singleton.get();
			ThreadSafeSingleton.get();
		}
	}

	class MyThread2 implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}
			Singleton.get();
			ThreadSafeSingleton.get();
		}
	}

	public void test() {
		new Thread(new MyThread2()).start();
		new Thread(new MyThread1()).start();
	}


	public static void main(String[] args) {
		new SingletonRaceCondition().test();
	}
}

/*
Singleton initialized by Thread-0
Singleton initialized by Thread-1
Thread safe singleton initialized by Thread-0

 */

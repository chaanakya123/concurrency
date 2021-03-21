package basic;

import java.util.Hashtable;

public class ReadModifyUpdateRaceCondition {
	private final Hashtable<String, String> hashTable = new Hashtable<>();
	public ReadModifyUpdateRaceCondition() {
	}

	class MyThread1 implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}

			if(!hashTable.contains("str")) {
				hashTable.put("str", "str");
				System.out.println("[Thread 1] :" + hashTable.toString());
			}
		}
	}

	class MyThread2 implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}
			if(!hashTable.contains("str")) {
				hashTable.put("str", "str");
				System.out.println("[Thread 2] :" + hashTable.toString());
			}
		}
	}

	class MyThread3 implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}

			// this will fix the read modify update race condition
			synchronized (hashTable) {
				if(!hashTable.contains("str")) {
					hashTable.put("str", "str");
					System.out.println("[Thread 2] :" + hashTable.toString());
				}
			}
		}
	}

	public void test() {
		new Thread(new MyThread1()).start();
		new Thread(new MyThread2()).start();
	}

	public static void main(String[] args) {
		new ReadModifyUpdateRaceCondition().test();
	}
}

/*
[Thread 1] :{str=str}
[Thread 2] :{str=str}
 */
package java8.MultiThreading.waitAndNotify;

class SharedResource {
    private int counter = 0;

    // Method to increment the counter
    public synchronized void increment() {
        counter++;
        System.out.println("Counter incremented to: " + counter);
        // Notify any waiting threads that the counter has changed
        notify();
    }

    // Method to wait until the counter reaches a certain value
    public synchronized void waitForCounter(int target) {
        while (counter < target) {
            try {
                System.out.println("Waiting for counter to reach: " + target);
                // Wait until notified by another thread
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Counter reached: " + target);
    }
}

public class WaitAndNotifyTest {

	public static void main(String[] args) {

		SharedResource sharedResource = new SharedResource();

        // Thread to increment the counter
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
                try {
                    Thread.sleep(500); // Sleep for a short while to simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread to wait for the counter to reach 3
        Thread waitThread = new Thread(() -> {
            sharedResource.waitForCounter(3);
        });

        // Start both threads
        incrementThread.start();
        waitThread.start();

        // Wait for both threads to complete
        try {
            incrementThread.join();
            waitThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread finished");
		
	}

}

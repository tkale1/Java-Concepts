package T3_Join_And_Synchronize;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@code synchronized} ("only let one thread in here at a time".) and {@code join} ("wait until
 * thread on which join has called finished") keyword.
 */
public class Worker {

    private int count = 0;

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }

    public synchronized void increment(String threadName) throws InterruptedException {
        count++;
        //Thread.sleep(1000);
        System.out.println("Thread in Progress: " + threadName + " and count is: " + count);
    }

    public void doWork() {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        increment(Thread.currentThread().getName());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        increment(Thread.currentThread().getName());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread2.start();

        /**
         * Join Threads: Finish until thread finishes execution, then progress
         * the code.
         */
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ignored) {}
        System.out.println("Count is: " + count);
    }
}

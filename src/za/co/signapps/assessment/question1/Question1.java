package za.co.signapps.assessment.question1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Question1 {

    private int count = 1;
    private static final String THREAD_NAME_KEY = "thread-";
    private static final int NUMBER_OF_THREADS = 20;

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        question1.print();
    }

    public void printT() {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            if (!threadName.contains(THREAD_NAME_KEY.concat(String.valueOf(count)))) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("hello ".concat("T").concat(String.valueOf(count).concat(" ").concat(Thread.currentThread().getName())));
            if (threadName.contains(THREAD_NAME_KEY.concat(String.valueOf(2)))) {
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            notifyAll();

        }
    }

    public void print() {
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            executor.submit(this::printT);
        }
        executor.shutdown();
    }
}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NummererteTraader {
    public static void main(String[] args) {
        Runnable jobb = new NummerertJobb();

        Thread[] traader = new Thread[100];

        for (int i = 0; i < traader.length; i++) {
            traader[i] = new Thread(jobb);
            traader[i].start();
        }

        try {
            for(int i = 0; i < traader.length; i++) {
                traader[i].join();
            }
        } catch(InterruptedException e) {}

        System.out.println(NummerertJobb.antallTraader);
    }
}

class NummerertJobb implements Runnable {
    public static int antallTraader = 0;
    private Lock laas = new ReentrantLock();

    public void run() {
        laas.lock();
        try {
            int id = antallTraader++;
        } finally {
            laas.unlock();
        }
        System.out.println("Jeg er traad " + id);
    }
}

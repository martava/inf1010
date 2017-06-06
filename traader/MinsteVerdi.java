import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MinsteVerdi {
    private static final int ANTALL_TRAADER = 100;

    public static void main(String[] args) {
        String[] ord = lesFil(args[0]);

        MinsteMonitor monitor = new MinsteMonitor();

        Thread[] arbeidere = new Thread[ANTALL_TRAADER];
        int ordPerTraad = ord.length / ANTALL_TRAADER;
        for (int i = 0; i < ANTALL_TRAADER; i++) {
            int fraIndeks = i * ordPerTraad;

            int tilIndeks = (i == ANTALL_TRAADER-1) ?
                    ord.length : fraIndeks + ordPerTraad;
            // Linjene ovenfor tilsvarer dette:
            /*int tilIndeks = fraIndeks + ordPerTraad;
            if (i == ANTALL_TRAADER-1) {
                tilIndeks = ord.length;
            }*/
            Runnable deloppgave = new FinnMinste(monitor, ord, fraIndeks, tilIndeks);
            arbeidere[i] = new Thread(deloppgave);
        }

        for (int i = 0; i < ANTALL_TRAADER; i++) {
            arbeidere[i].start();
        }

        try {
            for (int i = 0; i < ANTALL_TRAADER; i++) {
                arbeidere[i].join();
            }
        } catch (InterruptedException e) {}
        System.out.println(monitor.hentMinste());

    }

    /**
     * Les fil inn i en String[].
     */
    private static String[] lesFil(String navn) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(navn));
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke apne fil: " + navn);
            System.exit(1);
        }
        ArrayList<String> ord = new ArrayList<>();
        while (scan.hasNext())
            ord.add(scan.next());
        return ord.toArray(new String[0]);
    }
}


class FinnMinste implements Runnable {
    private final MinsteMonitor monitor;
    private final String[] array;
    private final int fraIndeks, tilIndeks;

    public FinnMinste(MinsteMonitor monitor, String[] array,
                    int fraIndeks, int tilIndeks) {
        this.monitor = monitor;
        this.array = array;
        this.fraIndeks = fraIndeks;
        this.tilIndeks = tilIndeks;
    }

    public void run() {
        String minste = array[fraIndeks];
        for (int i = fraIndeks + 1; i < tilIndeks; i++) {
            String ord = array[i];
            if (ord.compareTo(minste) < 0) {
                minste = ord;
            }
        }
        monitor.giMinste(minste);
    }
}




class MinsteMonitor {
    private final Lock laas = new ReentrantLock();
    private String minste;

    public String hentMinste() { return minste; }

    public void giMinste(String liten) {
        System.out.println("Mottok: " + liten);
        laas.lock();
        try {
            if (minste == null || liten.compareTo(minste) < 0) {
                minste = liten;
            }
        } finally {
            laas.unlock();
        }
    }
}

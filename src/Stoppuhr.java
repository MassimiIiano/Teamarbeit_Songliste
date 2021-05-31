
import java.io.*;

public class Stoppuhr {
    private static final int MAX = 10000000;
    private static long[] zeit = new long[MAX];
    private static int i = 0;
    private long start;
    private long stop;
    private String dateiname;

    // public static void main(String[] args) {
    //     long start = new java.util.GregorianCalendar().getTimeInMillis();
    //     StoppuhrTest.pause(2000);
    //     long stop = new java.util.GregorianCalendar().getTimeInMillis();
    //     System.out.println(start + "\n" + stop + "\n" + (stop - start));
    // }

    /**
     * Schreibt die gespeicherten Zeiten in eine Datei
     * 
     * @return 0 wenn alles gut geht, 1 wenn etwas nicht stimmt
     */
    public int schreibeZeiten() {
        int ret = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.dateiname));

            for (long l : zeit) {
                writer.write(l + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ret = 1;
        }
        // catch (FileNotFoundException e) {
        // System.out.println("datei nicht gefunden");
        // }
        return ret;
    }

    /**
     * startet die zeitaufnahme
     */
    public void starteStoppuhr() {
        start = new java.util.GregorianCalendar().getTimeInMillis();
    }

    /**
     * Speichert die vergangene zeit
     */
    public void stoppeStoppuhr() {
        stop = new java.util.GregorianCalendar().getTimeInMillis();
        zeit[i] = stop - start;
        // zeit[i] = stop - start;
        i++;
    }

    /**
     * Löscht alle gespeicherten zeiten
     */
    public void clearZeiten() {
        i = 0;
        zeit = new long[MAX];
    }

    public long[] getZeiten() {
        return zeit;
    }

    public String getDateiname() {
        return dateiname;
    }

    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

    public long getGestoppteZeit() {
        return stop - start;
    }

    // public void setGestoppteZeit(long gestoppteZeit) {
    // this.gestoppteZeit = gestoppteZeit;
    // }

}

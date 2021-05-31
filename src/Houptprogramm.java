import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Houptprogramm {

    private static final long MAX_ARRAY_LEN = 1000000;
    private static final int MAX_ARRAY_VALUE = 1000000;
    private static final String SEP = ";";

    public static void main(String[] args) {
        String ziel = "C:\\Users\\massi\\Documents\\Work\\SCHULE_JAVA\\Test\\src\\test.csv";
        Stoppuhr uhr = new Stoppuhr();

        int[] arr = new int[(int) MAX_ARRAY_LEN];
        int[] arr_lengths;
        long[] zeiten_minsort, zeiten_heapsort;

        int ex = 1;
        int len = 1;
        int count = 0;

        uhr.clearZeiten();
        while (len < MAX_ARRAY_LEN) {
            for (int i = 1; i < 10; i++) {
                arr = GenArrays.genIntArr(len * i, 0, MAX_ARRAY_VALUE);
                uhr.starteStoppuhr();
                Minimumsuche.minimumsuche(arr);
                uhr.stoppeStoppuhr();
                count++;
            }
            len = (int) Math.round(Math.pow(10, ex));
            ex++;
        }
        zeiten_minsort = uhr.getZeiten();

        arr_lengths = new int[count];
        count = 0;
        len = 1;
        ex = 1;
        uhr.clearZeiten();
        while (len < MAX_ARRAY_LEN) {
            for (int i = 1; i < 10; i++) {
                arr = GenArrays.genIntArr(len * i, 0, MAX_ARRAY_VALUE);
                uhr.starteStoppuhr();
                Heapsort.heapSort(arr);
                uhr.stoppeStoppuhr();
                arr_lengths[count] = len * i;
                count++;
            }
            len = (int) Math.round(Math.pow(10, ex));
            ex++;
        }
        zeiten_heapsort = uhr.getZeiten();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
            writer.write("Len Array;Zeit Minimumsuche;Zeit Heapsort\n");
            // if (zeiten_heapsort.length == zeiten_minsort.length && zeiten_heapsort.length == arr_lengths.length)
                for (int i = 0; i < arr_lengths.length; i++)
                    writer.write(arr_lengths[i] + SEP + zeiten_minsort[i] + SEP + zeiten_heapsort[i] + '\n');

            writer.close();
        } catch (IOException e) {
            System.out.println("Datei nicht angelegt");
        }

        /*
         * for (int i = 0; i < MAX_ARRAY_LEN; i++) { Stoppuhr uhr = new Stoppuhr();
         * int[] list = GenArrays.genIntArr(, 0, 100); int[] list_minimumsuche =
         * list.clone(); int[] list_headsort = list.clone();
         * 
         * try { BufferedWriter writer = new BufferedWriter(new FileWriter(ziel)); }
         * catch (IOException e) { System.out.println("Datei nicht angelegt"); } }
         * 
         * /* for (int i : list) System.out.print(i + " "); System.out.println();
         * 
         * uhr.starteStoppuhr();
         * 
         * Minimumsuche.minimumsuche(list_minimumsuche);
         * 
         * uhr.stoppeStoppuhr(); System.out.println("Minimumsuche: " +
         * uhr.getGestoppteZeit());
         * 
         * for (int i : list_minimumsuche) System.out.print(i + " ");
         * System.out.println();
         * 
         * 
         * uhr.starteStoppuhr();
         * 
         * Heapsort.heapSort(list_headsort);
         * 
         * uhr.stoppeStoppuhr();
         * 
         * System.out.println("Heapsort: " + uhr.getGestoppteZeit());
         * 
         * for (int i : list_headsort) System.out.print(i + " "); System.out.println();
         */

    }
}

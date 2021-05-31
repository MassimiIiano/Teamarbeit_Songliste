public class GenArrays {

    public static int[] genIntArr(int elemente, int min, int max) {
        int[] ret = new int[elemente];

        for (int i = 0; i < ret.length; i++)
            ret[i] = (int) (Math.random() * (max - min) + min);

        return ret;
    }

}

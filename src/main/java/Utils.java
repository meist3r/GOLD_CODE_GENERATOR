public class Utils {
    public static int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        return reversedArray;
    }

    public static double[] intToDouble(int[] x){
        double[] y = new double[x.length];

        for(int i=0;i<x.length;i++){
            y[i] = x[i];
        }

        return y;
    }

    public static int[] extractSeed(String seed) {
        int[] result = new int[seed.length()];
        for (int i = 0; i < seed.length(); i++) {
            char c = seed.charAt(i);
            if (c == '0') {
                result[i] = 0;
            } else if (c == '1') {
                result[i] = 1;
            } else {
                return null;
            }
        }
        return result;
    }
}

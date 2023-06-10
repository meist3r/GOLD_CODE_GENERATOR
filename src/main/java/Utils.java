import java.io.FileWriter;
import java.io.IOException;

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

    public static int saveStringToFile(String filename, String data){
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public static String intArrayToString(int[] data){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        return sb.toString();
    }



}

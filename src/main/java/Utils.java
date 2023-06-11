import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        for (int value : data) {
            sb.append(value);
        }
        return sb.toString();
    }

    public static int[] extractMSeq(String s){
        List<Integer> numbers = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            numbers.add(number);
        }

        int[] array1 = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array1[i] = numbers.get(i);
        }

        return array1;
    }

    public static int maxIntArr(int [] arr){
        if(arr.length==0) return 0;
        int res = arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>res){
                res = arr[i];
            }
        }
        return res;

    }

    public static int[] generateSeed(int maxLen) {
        int[] seed1 = new int[maxLen];
        for (int i = 0; i < maxLen; i++) {
            seed1[i] = 0;
        }
        seed1[maxLen - 1] = 1;

        return seed1;
    }

    public static boolean validatePolynomialInput(String input){
        String regex = "^[\\d,\\s]+$";

        return input.matches(regex);
    }

    public static boolean validateSeedInput(String input){
        String result = input.replace("0", "").replace("1", "");
        return result.length() == 0;
    }

    public static boolean validateMSequence(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (!list.contains(num)&&num!=0) {
                list.add(num);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return arr.length == result.length;

    }



}

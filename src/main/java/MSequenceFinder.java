import java.util.Arrays;

public class MSequenceFinder {

    private static int MAX_LFSR = 6;
    private static int[] Seed1;
    private static int[] Seed2;

    public static void main(String[] args) {
        int combinations = 1 << MAX_LFSR;
        int optimals = 0;
        for (int l1 = 1; l1 < combinations; l1++) {
            for (int l2 = l1; l2 < combinations; l2++) {
                int[] mSeq1 = getMSequence(l1);
                int[] mSeq2 = getMSequence(l2);
                int aMax1 = Utils.maxIntArr(mSeq1);
                int aMax2 = Utils.maxIntArr(mSeq2);

                int seedLen = Math.max(aMax1,aMax2);
                Seed1 = Utils.generateSeed(seedLen);
                Seed2 = Utils.generateSeed(seedLen);
                Generator g = new Generator(mSeq1, mSeq2, Seed1, Seed2);
                Validator validator = new Validator(g);
                boolean preferred = validator.isPreferredSequences();
                if(preferred){
                    System.out.println("Optymalna para znaleziona: " + Arrays.toString(getMSequence(l1)) + " | " + Arrays.toString(getMSequence(l2)));
                    optimals++;
                }

            }
        }
        System.out.println("Znaleziono " + optimals + " optymalnych par");

    }

    private static int[] getMSequence(int i) {
        int[] bitResult = new int[MAX_LFSR];
        int index = 0;
        int ones = 0;
        for (int j = MAX_LFSR - 1; j >= 0; --j) {
            int bit = (i >> j) & 1;
            if (bit == 0) {
                bitResult[j] = 0;
            } else {
                bitResult[j] = 1;
                ones++;
            }

        }
        int[] result = new int[ones];
        for (int j = MAX_LFSR - 1; j >= 0; --j) {
            if (bitResult[j] == 1) {
                result[ones - index - 1] = j + 1;
                index++;
            }
        }
        return result;
    }
}

import java.util.ArrayList;

public class Validator {
    private final Generator generator;
    private final int[] autoCorrelation;
    private final int[] mSequencesCorrelation;
    private final int[] mSequence1AutoCorrelation;
    private final int[] mSequence2AutoCorrelation;
    private final boolean isPreferredSequences;

    /**
     * Validator used to check whether a given generator uses preferred pair m-sequences.
     */
    public Validator(Generator g) {
        generator = g;
        autoCorrelation = autoCorrelation();
        mSequencesCorrelation = mSequenceCorrelation();
        mSequence1AutoCorrelation = mSequenceAutoCorrelation(1);
        mSequence2AutoCorrelation = mSequenceAutoCorrelation(2);
        isPreferredSequences = preferredSequence();
    }


    public Generator getGenerator() {
        return generator;
    }

    public int[] getAutoCorrelation() {
        return autoCorrelation;
    }

    public int[] getMSequencesCorrelation() {
        return mSequencesCorrelation;
    }

    public int[] getMSequencesAutoCorrelation(int id) {
        return id == 1 ? mSequence1AutoCorrelation : mSequence2AutoCorrelation;
    }

    public boolean isPreferredSequences() {
        return isPreferredSequences;
    }


    /**
     * Calculates auto-correlation of the generator's output of an optimal sequence's length.
     */
    private int[] autoCorrelation() {
        int[] x = new int[generator.getLengthOfOptimalGoldCode()];
        for (int i = 0; i < generator.getLengthOfOptimalGoldCode(); i++) {
            x[i] = generator.generate();
        }
        generator.resetLFSR();
        return correlation(x, x);
    }

    /**
     * Calculates cross-correlation values of LFSRs outputs of an optimal sequence's length.
     */
    private int[] mSequenceCorrelation() {

        int len = generator.getLengthOfOptimalGoldCode();
        int[] x = new int[len];
        int[] y = new int[len];

        for (int i = 0; i < len; i++) {
            x[i] = generator.getM1().pop();
            y[i] = generator.getM2().pop();
        }
        generator.resetLFSR();
        return correlation(x, y);
    }

    /**
     * Calculates auto-correlation values of LFSR output of an optimal sequence's length.
     */
    private int[] mSequenceAutoCorrelation(int id) {

        int len = generator.getLengthOfOptimalGoldCode();
        int[] x = new int[len];

        for (int i = 0; i < len; i++) {
            x[i] = id == 1 ? generator.getM1().pop() : generator.getM2().pop();
        }
        generator.resetLFSR();
        return correlation(x, x);
    }

    /**
     * M-sequences are preferred for a specific generator when cross-correlation values of LFSRs outputs are three-valued.
     */
    private boolean preferredSequence() {
        int x, y, t;
        x = generator.getLengthOfOptimalGoldCode();
        y = generator.getSizeOfLFSR();

        if(y%2==0&&y%4!=2){
            return false;
        }

        if (y % 2 == 0) {
            t = (int) (1 + Math.pow(2, (y + 2) / 2));
        } else {
            t = (int) (1 + Math.pow(2, (y + 1) / 2));
        }
        ArrayList<Integer> e = new ArrayList<>();
        e.add(-t);
        e.add(-1);
        e.add(t - 2);
        for (int i : mSequencesCorrelation) {
            if (e.contains(i)) {
            } else return false;

        }
        generator.resetLFSR();
        return generator.getLengthOfGoldCode() == x;
    }

    /**
     * Method is used to calculate correlation values between two datasets.
     *
     * @param x dataset 1
     * @param y dataset 2
     * @return array of correlation values
     */
    private int[] correlation(int[] x, int[] y) {
        int k2 = generator.getLengthOfOptimalGoldCode();

        int[] rangeOfKs = new int[k2 + 1];
        for (int i = 0; i < rangeOfKs.length; i++) {
            rangeOfKs[i] = i;
        }

        int[] Rxy = new int[rangeOfKs.length];


        int q = x.length / y.length;
        int r = x.length % y.length;
        int[] extendedY = new int[x.length];
        for (int i = 0; i < q; i++) {
            System.arraycopy(y, 0, extendedY, i * y.length, y.length);
        }
        System.arraycopy(y, 0, extendedY, q * y.length, r);

        for (int i = 0; i < rangeOfKs.length; i++) {
            int agreements = 0;
            int disagreements = 0;
            for (int j = 0; j < x.length; j++) {
                if (x[j] == extendedY[j]) {
                    agreements++;
                } else {
                    disagreements++;
                }
            }
            x = leftShift(x);
            Rxy[i] = agreements - disagreements;
        }


        return Rxy;


    }

    private static int[] leftShift(int[] array) {
        int[] shiftedArray = new int[array.length];
        System.arraycopy(array, 1, shiftedArray, 0, array.length - 1);
        shiftedArray[array.length - 1] = array[0];
        return shiftedArray;
    }


}

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Validator {
    private Generator generator;
    private int[] autoCorrelation;
    private int[] mSequencesCorrelation;
    private boolean isPreferredSequences;

    /**
     * Validator used to check whether a given generator uses preferred pair m-sequences.
     */
    public Validator(Generator g){
        generator=g;
        autoCorrelation = autoCorrelation();
        mSequencesCorrelation = mSequenceCorrelation();
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

    public boolean isPreferredSequences() {
        return isPreferredSequences;
    }


    /**
     * Calculates auto-correlation of the generator's output of an optimal sequence's length.
     */
    private int[] autoCorrelation(){
        int[] x = new int[generator.getLengthOfOptimalGoldCode()];
        for (int i = 0; i<generator.getLengthOfOptimalGoldCode(); i++){
            x[i] = generator.generate();
        }
        generator.resetLFSR();
        return correlation(x,x);
    }

    /**
     * Calculates cross-correlation values of LFSRs outputs of an optimal sequence's length.
     */
    private int[] mSequenceCorrelation() {

        int len = generator.getLengthOfOptimalGoldCode();
        int[] x = new int[len];
        int[] y = new int[len];

        for (int i=0; i<len;i++){
            x[i] = generator.getM1().pop();
            y[i] = generator.getM2().pop();
        }
        generator.resetLFSR();
        return correlation(x,y);
    }

    /**
     * M-sequences are preferred for a specific generator, when cross-correlation values of LFSRs outputs are three-valued.
     */
    private boolean preferredSequence(){
        int x,y,t;
        x = generator.getLengthOfOptimalGoldCode();
        y = generator.getSizeOfLSFR();
        if(x%2==0){
            t = (int) (1+Math.pow(2,(y+2)/2));
        }else{
            t = (int) (1+Math.pow(2,(y+1)/2));
        }
        ArrayList<Integer> e = new ArrayList<Integer>();
        e.add(-t);
        e.add(-1);
        e.add(t-2);
        for (int i: mSequencesCorrelation) {
            if (e.contains(i)){}
            else{return false;}
        }
        return true;
    }


    private int[] correlation(int[] x, int[] y){


        int L = x.length;
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


    public static void main(String[] args){
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{2,5};

        Generator g = new Generator(pair1,pair2,seed1,seed2);
        Validator validator = new Validator(g);
        System.out.println(validator.isPreferredSequences());



    }
}

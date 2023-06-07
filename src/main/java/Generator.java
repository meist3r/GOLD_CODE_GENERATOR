import org.apache.commons.math3.stat.StatUtils;

import java.util.Arrays;

public class Generator {
    private LFSR m1;
    private LFSR m2;
    private int sizeOfLSFR;
    private final int[] initialSeed1, initialSeed2;


    public Generator(int[] mseq1, int[] mseq2, int[] seed1, int[] seed2)  {
        try {
            if (seed1.length != seed2.length){throw new Exception("Different seed lengths.");}
            if (mseq1.length == 0 || mseq2.length==0 ) {throw new Exception("Pair/s empty!");}
            for (int x:mseq1) {
                if (x>seed1.length){
                    throw new Exception("Invalid sequences in mseq1.");
                }
            }
            for (int x:mseq2) {
                if (x>seed2.length){
                    throw new Exception("Invalid sequences in mseq2.");
                }
            }
        }catch (Exception e) {e.printStackTrace();}

        sizeOfLSFR = seed1.length;
        for (int i = 0;i<mseq1.length;i++) {
            mseq1[i] -= 1;
        }
        for (int i = 0;i<mseq2.length;i++) {
            mseq2[i] -= 1;
        }

        initialSeed1 = seed1.clone();
        initialSeed2 = seed2.clone();


        m1 = new LFSR(seed1, mseq1);
        m2 = new LFSR(seed2, mseq2);


    }


    /**
     * @return next output bit of the Gold Code sequence.
     */
    public int generate(){
        int out1,out2,result;

        out1 = m1.pop();
        out2 = m2.pop();
        result = out1 ^ out2;

        return result;
    }

    /**
     * Length of the Gold Code sequence for optimal m-sequences is 2^n - 1.
     */
    public int getLengthOfOptimalGoldCode(){
        return (int) (Math.pow(2,sizeOfLSFR)-1);

    }

    /**
     * Length of the Gold Code sequence.
     */
    public int getLengthOfGoldCode(){
        int[] startingSeed1 =  m1.getReg().clone();
        int[] startingSeed2 =  m2.getReg().clone();
        int len = 0;
        do{
            generate();
            len++;
        }while(!Arrays.equals(startingSeed1, m1.getReg()) && !Arrays.equals(startingSeed2, m2.getReg()));

        resetLFSR();
        return len;
    }

    /**
     * @return Size of LFSRs inside the generator.
     */
    public int getSizeOfLSFR() {
        return sizeOfLSFR;
    }

    public LFSR getM1() {
        return m1;
    }

    public LFSR getM2() {
        return m2;
    }

    public int[] getInitialSeed1() {
        return initialSeed1;
    }

    public int[] getInitialSeed2() {
        return initialSeed2;
    }

    public void resetLFSR(){
        m1.setReg(initialSeed1.clone());
        m2.setReg(initialSeed2.clone());
    }





}





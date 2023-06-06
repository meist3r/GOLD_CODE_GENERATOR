public class Generator {
    private LFSR m1;
    private LFSR m2;
    private int sizeOfLSFR;
    private int[] initialSeed1, initialSeed2;



    public Generator(int[] pair1, int[] pair2, int[] seed1, int[] seed2)  {
        try {
            if (seed1.length != seed2.length){throw new Exception("Different seed lengths.");}
            if (pair1.length == 0 || pair2.length==0 ) {throw new Exception("Pair/s empty!");}
            for (int x:pair1) {
                if (x>seed1.length){
                    throw new Exception("Invalid sequences in pair1.");
                }
            }
            for (int x:pair2) {
                if (x>seed2.length){
                    throw new Exception("Invalid sequences in pair2.");
                }
            }
        }catch (Exception e) {e.printStackTrace();}

        sizeOfLSFR = seed1.length;
        for (int i = 0;i<pair1.length;i++) {
            pair1[i] -= 1;
        }
        for (int i = 0;i<pair2.length;i++) {
            pair2[i] -= 1;
        }

        initialSeed1 = seed1.clone();
        initialSeed2 = seed2.clone();


        m1 = new LFSR(seed1, pair1);
        m2 = new LFSR(seed2, pair2);


    }


    public int getSizeOfLSFR() {
        return sizeOfLSFR;
    }

    public int getLengthOfOptimalGoldCode(){
        return (int) (Math.pow(2,sizeOfLSFR)-1);

    }

    public int generate(){
        int out1,out2,result;

        out1 = m1.pop();
        out2 = m2.pop();
        result = out1 ^ out2;

        return result;
    }

    public LFSR getM1() {
        return m1;
    }

    public LFSR getM2() {
        return m2;
    }

    public void resetLFSR(){
        m1.setReg(initialSeed1.clone());
        m2.setReg(initialSeed2.clone());
    }

}





public class LFSR{
    private int len;        // length of the lfsr
    private int[] reg;      // register
    private int[] mseq;     // indexes of bits to calculate feedback

    /**
     * @param seed initial seed
     * @param msequence indexes of bits taken to calculate feedback
     */
    public LFSR (int[] seed, int[] msequence){
        reg = seed;
        len = seed.length;
        mseq = msequence;
    }


    public void setReg(int[] reg) {
        this.reg = reg;
    }

    public int[] getReg() {
        return reg;
    }

    public int getLen() {
        return len;
    }

    /**
     * @return LFSR output bit
     */
    public int pop(){
        int output = reg[len-1];

        int res = reg[mseq[0]];
        for (int i = 1; i < mseq.length; i++) {
            res = res ^ reg[mseq[i]];
        }

        for (int i =len-1; i > 0; i--){
            reg[i] = reg[i-1];
        }
        reg[0] = res;

        return output;
    }

}
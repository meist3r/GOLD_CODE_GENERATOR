public class LFSR{
    private int len;        // length of the lfsr
    private int[] reg;      // register
    private int[] polynomial;     // indexes of bits to calculate feedback

    /**
     * @param seed initial seed
     * @param pol polynomial used to caluclate feedback
     */
    public LFSR (int[] seed, int[] pol){
        reg = seed;
        len = seed.length;
        polynomial = pol;
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
     * Shift the LFSR by one bit.
     * @return LFSR output bit
     */
    public int pop(){
        int output = reg[len-1];

        int res = reg[polynomial[0]];
        for (int i = 1; i < polynomial.length; i++) {
            res = res ^ reg[polynomial[i]];
        }

        for (int i =len-1; i > 0; i--){
            reg[i] = reg[i-1];
        }
        reg[0] = res;

        return output;
    }

}
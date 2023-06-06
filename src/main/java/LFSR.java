public class LFSR{
    private int len;
    private int[] reg;
    private int[] xxxxxx;

    public LFSR (int[] seed, int[] pair){
        reg = seed;
        len = seed.length;
        xxxxxx = pair;
    }

    public int getLen() {
        return len;
    }

    public int pop(){
        int output = reg[len-1];

        int res = reg[xxxxxx[0]];
        for (int i = 1; i < xxxxxx.length;i++) {
            res = res ^ reg[xxxxxx[i]];
        }

        for (int i =len-1; i > 0; i--){
            reg[i] = reg[i-1];
        }
        reg[0] = res;

        return output;
    }

    public int[] getReg() {
        return reg;
    }
}
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import java.lang.ref.ReferenceQueue;
import java.util.Arrays;

//import static java.math.BigInteger.leftShift;


public class Generator {
    private LFSR m1;
    private LFSR m2;
    private int sizeOfLSFR;
    private int[] initialSeed1, initialSeed2;



    public Generator(int[] pair1, int[] pair2, int[] seed1, int[] seed2)  {
        try {
            if (seed1.length != seed2.length){throw new Exception("Different seed lengths.");}
            for (int x:pair1) {
                if (x>seed1.length-1){
                    throw new Exception("Invalid sequences in pair1.");
                }
            }
            for (int x:pair2) {
                if (x>seed2.length-1){
                    throw new Exception("Invalid sequences in pair2.");
                }
            }
        }catch (Exception e) {e.printStackTrace();}

        sizeOfLSFR = seed1.length;


        initialSeed1 = seed1.clone();
        initialSeed2 = seed2.clone();


        m1 = new LFSR(seed1, pair1);
        m2 = new LFSR(seed2, pair2);


    }

    public LFSR getM1() {
        return m1;
    }
    public LFSR getM2() {
        return m2;
    }

    public int getSizeOfLSFR() {
        return sizeOfLSFR;
    }

    public int getLengthOfGoldCode(){
        return (int) (Math.pow(2,sizeOfLSFR)-1);

    }

    public int generate(){
        int out1,out2,result;

        out1 = m1.pop();
        out2 = m2.pop();
        result = out1 ^ out2;

        return result;
    }

    public void resetLFSR(){
        m1.setReg(initialSeed1);
        m2.setReg(initialSeed2);
    }

    private double sqr(double x) {
        return x * x;
    }

    public void fftAutoCorrelation(double [] x, double [] ac) {
        int n = x.length-1;
        // Assumes n is even.
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        fft.realForward(x);
        ac[0] = sqr(x[0]);
        ac[0] = 0;  // For statistical convention, zero out the mean
        ac[1] = sqr(x[1]);
        for (int i = 2; i < n; i += 2) {
            ac[i] = sqr(x[i]) + sqr(x[i+1]);
            ac[i+1] = 0;
        }
        DoubleFFT_1D ifft = new DoubleFFT_1D(n);
        ifft.realInverse(ac, true);
         //For statistical convention, normalize by dividing through with variance
        for (int i = 1; i < n; i++)
            ac[i] /= ac[0];
        ac[0] = 1;
        for (int i = 0; i < ac.length; i++) {
           ac[i] =  Math.floor(ac[i] * 100)/100;
        }
    }



    public int[] sequenceCorrelation() {

        int len = getLengthOfGoldCode();
        int[] x = new int[len];
        int[] y = new int[len];

        for (int i=0; i<len;i++){
            x[i] = m1.pop();
            y[i] = m2.pop();
        }
        System.out.println("x " + Arrays.toString(x));
        System.out.println("y " + Arrays.toString(y));

        int L = x.length;
        int k1 = 0;
        int k2 = getLengthOfGoldCode();

        int[] rangeOfKs = new int[k2 - k1 + 1];
        for (int i = 0; i < rangeOfKs.length; i++) {
            rangeOfKs[i] = k1 + i;
        }

        int[] Rxy = new int[rangeOfKs.length];

        if (k1 != 0) {
            int start = (L + k1) % L;
            int finalIndex = (L + k1 - 1) % L;
            int[] shiftedX = new int[L];
            System.arraycopy(x, start, shiftedX, 0, L - start);
            System.arraycopy(x, 0, shiftedX, L - start, finalIndex + 1);
            x = shiftedX;
        }

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


    public double[] fftCrossCorrelation(double [] x,double[] y){
        int n = x.length-1;

        double nigger = 1/n;

        int agreements;
        int disagreements;

        double[] result = new double[n];

        for (int i=0;i<n;i++) {
            agreements = calculateAgreements(x, y);
            disagreements = calculateDisgreements(x, y);
            result[i] = nigger *(agreements-disagreements);
            shiftByOne(x);
        }


        return result;
    }

    public int calculateAgreements(double [] x,double[] y){
        int agree = 0;
        for(int i=0; i<x.length;i++){
            if (x[i] == y[i]){
                agree++;
            }
        }

        return agree;
    }

    public int calculateDisgreements(double [] x,double[] y){
        int disagree = 0;
        for(int i=0; i<x.length;i++){
            if (x[i] != y[i]){
                disagree++;
            }
        }

        return disagree;
    }

    public void shiftByOne(double [] x){
        double last = x[x.length-1];
        for (int i =x.length-1; i > 0; i--){
            x[i] = x[i-1];
        }
        x[0] = last;
    }


    public static void main(String[] args){
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{1,2,3,4};
        pair2 = new int[]{1,4};

        Generator g = new Generator(pair1,pair2,seed1,seed2);
        System.out.println(g.getLengthOfGoldCode());
        g.resetLFSR();
        System.out.println("cross corelation" + Arrays.toString(g.sequenceCorrelation()));


    }









}





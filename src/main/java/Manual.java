public class Manual {
    public static void main(String[] args){
        int[] seed1,seed2;
        int[] pair1, pair2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        pair1 = new int[]{2,3,4,5};
        pair2 = new int[]{2,5};
        Generator g = new Generator(pair1,pair2,seed1,seed2);
        Validator validator = new Validator(g);
        boolean preferred = validator.isPreferredSequences();
        System.out.println("Para wielomianow jest: " + (preferred ? "OPTYMALNA" : "NIEOPTYMALNA"));

        int[] mSeqCorArr = validator.getMSequencesCorrelation();
        LineChart.generateLineChart(Utils.intToDouble(mSeqCorArr), "Korelacja krzyżowa");

        int[] mSeqAutoCorArr = validator.getAutoCorrelation();
        LineChart.generateLineChart(Utils.intToDouble(mSeqAutoCorArr), "Autokorelacja");


    }

}

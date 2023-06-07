public class Manual {
    public static void main(String[] args){
        int[] seed1, seed2, polynomial1, polynomial2;
        seed1 = new int[]{0,0,0,0,1};
        seed2 = new int[]{0,0,0,0,1};
        polynomial1 = new int[]{2,3,4,5};
        polynomial2 = new int[]{2,5};
        Generator g = new Generator(polynomial1,polynomial2,seed1,seed2);
        Validator validator = new Validator(g);
        boolean preferred = validator.isPreferredSequences();
        System.out.println("Para wielomianow jest: " + (preferred ? "OPTYMALNA" : "NIEOPTYMALNA"));
        System.out.println("Dlugosc kodu Golda: " + g.getLengthOfGoldCode());

        int[] mSeqCorArr = validator.getMSequencesCorrelation();
        LineChart.generateLineChart(Utils.intToDouble(mSeqCorArr), "Korelacja krzyżowa");

        int[] AutoCorArr = validator.getAutoCorrelation();
        LineChart.generateLineChart(Utils.intToDouble(AutoCorArr), "Autokorelacja");

        int[] mSeq1AutoCorArr = validator.getMSequencesAutoCorrelation(1);
        LineChart.generateLineChart(Utils.intToDouble(mSeq1AutoCorArr), "Autokorelacja pierwszego wielomianu");

        int[] mSeq2AutoCorArr = validator.getMSequencesAutoCorrelation(2);
        LineChart.generateLineChart(Utils.intToDouble(mSeq2AutoCorArr), "Autokorelacja drugiego wielomianu");


    }

}

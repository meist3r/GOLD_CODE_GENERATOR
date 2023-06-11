public class Manual {
    public static void main(String[] args){
        int[] seed1, seed2, polynomial1, polynomial2;
        seed1 = Utils.generateSeed(6);
        seed2 = Utils.generateSeed(6);
        polynomial1 = new int[]{1,2,5,6};
        polynomial2 = new int[]{1,6};
        Generator g = new Generator(polynomial1,polynomial2,seed1,seed2);
        Validator validator = new Validator(g);
        boolean preferred = validator.isPreferredSequences();
        System.out.println("Para wielomianow jest: " + (preferred ? "OPTYMALNA" : "NIEOPTYMALNA"));
        System.out.println("Dlugosc kodu Golda: " + g.getLengthOfGoldCode());
        System.out.println("Optymalna dlugosc kodu Golda: " + g.getLengthOfOptimalGoldCode());

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
